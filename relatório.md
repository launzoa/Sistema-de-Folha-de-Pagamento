O Sistema de Folha de Pagamento (SFP) foi desenvolvido adotando conceitos maduros de engenharia de software, fortemente inspirado pela **Arquitetura Limpa (Clean Architecture)** e **Domain-Driven Design (DDD)**.
A estrutura de pacotes no diretório `src/main/java/com/sfp` isola responsabilidades, garantindo que a Interface Gráfica (UI), a Regra de Negócio (Domain/Application) e o Banco de Dados (Infrastructure) operem de forma desacoplada.

O projeto é dividido em camadas, onde cada camada é responsável por uma funcionalidade específica. Ao entrar em quase qualquer pacote (ex: `com.sfp.folha` ou `com.sfp.funcionario`), é encontrado a mesma subdivisão em 4 subpacotes clássicos. Esta é a assinatura arquitetural do projeto:

- **`domain` (Domínio):** O centro do sistema. Contém as Entidades (`Funcionario.java`, `Holerite.java`) e as *Interfaces* de Repositório (`FuncionarioRepository.java`). O domínio não conhece banco de dados nem telas.
- **`application` (Aplicação):** Contém os Casos de Uso, Serviços e o Motor de Cálculos (ex: `ProcessadorDeFolha`). É aqui que a mágica da regra de negócio acontece. Orquestra o domínio.
- **`infrastructure` (Infraestrutura):** A camada suja. Aqui ficam as classes que implementam as interfaces do domínio para acessar o MySQL (`MySQLFuncionarioRepository.java`). Usa puramente JDBC.
- **`ui` (Interface Gráfica):** Os *Controllers* do JavaFX (`TelaFuncionarioController.java`). Apenas capturam cliques, chamam a camada de `application` ou `domain` e atualizam a tela.

# Roadmap Sugestivo de Estudo

Para entender o código sem se perder na complexidade do Motor de Folha, recomendo seguir a análise do sistema nesta exata ordem:

### Etapa 1: As Fundações (`core` e `utils`)

Comece entendendo como o sistema respira.

- Navegue até `com.sfp.core.database.ConexaoBD` e `ServicoDatabase`. Estude como a conexão com o banco MySQL é estabelecida e como scripts diretos são executados.
- Veja `com.sfp.core.ui.MainController`: O esqueleto do sistema. Veja como ele carrega os FXMLs nas áreas centrais (`carregarTela()`) sem recarregar a janela inteira e como aplica o Tema Escuro.
- Olhe o pacote `com.sfp.utils`, que concentra formatadores de CPF, CNPJ e Moedas para a UI.

### Etapa 2: Módulos de Cadastro Simples (O Feijão com Arroz)

Antes de ver os cálculos matemáticos, estude o fluxo básico de um CRUD em camadas.

- Vá para `com.sfp.funcionario` e `com.sfp.empresa`.
- Olhe o `domain/Funcionario.java` (Entidade pura).
- Depois vá para a `infrastructure/MySQLFuncionarioRepository.java` e entenda o uso dos `PreparedStatement`.
- Por fim, veja como o `ui/TelaFuncionarioController.java` chama os repositórios para preencher a *TableView* do JavaFX.
- *Nota:* Padrões de Projeto: Aqui você observa o Padrão **Repository** em sua forma clássica, ocultando a complexidade do SQL da interface.

### Etapa 3: Autenticação, Segurança e Rastreamento

Entenda como o estado (sessão) se mantém ativo durante a execução.

- Vá para `com.sfp.autenticacao`. Veja como o `ControladorAutenticacao` faz a validação da senha consultando a infraestrutura de Usuários.
- Vá para `com.sfp.auditoria`. O `ServicoAuditoria` usa o padrão **Singleton** (com métodos estáticos de contexto) para gravar no banco quem fez o quê. Quase todos os *Controllers* de UI das outras etapas chamam essa classe quando o usuário salva ou deleta algo.

### Etapa 4: Configurações de Negócio (`rubrica`)

A ponte entre o cadastro simples e o cálculo.

- Estude `com.sfp.rubrica`. Aqui você verá como a configuração de Proventos/Descontos e incidências (INSS/IRRF/FGTS) funciona no código. Essa parametrização é a alma das calculadoras matemáticas.

### Etapa 5: O Coração do Sistema (`folha` - Application)

Este é o ápice do projeto, onde a maior complexidade e os Padrões de Projeto de cálculo residem. Vá diretamente ao subpacote `com.sfp.folha.application`:

- **Padrão Strategy:** Abra o pacote `calculadoras`. Cada imposto ou lei foi quebrado em uma classe isolada (ex: `CalculadoraINSS.java`, `CalculadoraIRRF.java`). Todas podem possuir interfaces em comum. Elas encapsulam a variação progressiva do Brasil de forma elegante.
- **Padrão Facade / Orquestrador:** Abra o `ProcessadorDeFolha.java`. Estude como ele atua como um maestro. Ele não calcula nada sozinho; ele recebe um funcionário, chama os Lançamentos e manda as `calculadoras` executarem as lógicas em cascata para gerar o objeto final `Holerite.java`.
- **Relatórios:** Estude o `GeradorHoleritePDF` e `GeradorRelatorioGeralPDF`. Veja como eles utilizam a biblioteca iTextPDF para renderizar matrizes de dados provindas da etapa anterior.

### Etapa 6: A Conclusão (Integração na `TelaHoleriteController`)

Após entender as peças isoladas (o banco, o CRUD, e as Calculadoras de Folha), analise o gigantesco `com.sfp.folha.ui.TelaHoleriteController.java`.

- Observe como, no evento `mudarCompetencia()` ou ao iniciar a tela, ele usa o **Repository** para buscar lançamentos, injeta no **ProcessadorDeFolha** (Strategy/Facade), e exibe os resultados líquidos na Interface em tempo real, além de orquestrar a exportação via PDF.

---

## 3. Principais Padrões de Projeto (Design Patterns) Utilizados

1. **Repository Pattern:** Usado maciçamente (`MySQLXXXXRepository`) para separar a lógica de acesso a dados da lógica de negócios.
2. **Strategy Pattern:** Usado nas calculadoras (`CalculadoraINSS`, `CalculadoraFGTS`) para permitir que diferentes regras tributárias sejam aplicadas sem encher o código principal com `if/else`.
3. **MVC / MVP Adaptado:** Com o JavaFX, a view (`.fxml`) e o Controller (`.java`) estão bem divididos, sendo que o Controller atua como mediador entre a tela e o Service/Application Layer.
4. **Facade Pattern:** O `ProcessadorDeFolha` é uma fachada amigável que esconde a enorme complexidade de calcular salário base proporcional, somar proventos variados, deduzir faltas e aplicar as faixas estaduais.
5. **Singleton (Estado de Sessão):** `ServicoAuditoria.setUsuarioAtual()` guarda o estado global do usuário conectado durante a vida útil da janela do JavaFX.
