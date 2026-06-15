# Documentação de Decisão e How-To: Arquitetura da Folha de Pagamento

## O Objetivo

Modelar o núcleo do sistema: o cálculo do processo de folha de pagamento (UC-10), focando na alta coesão e no baixo acoplamento para lidar com regras fiscais voláteis (INSS, IRRF, horas extras, etc.). O objetivo é garantir que o processamento seja escalável e de fácil manutenção frente a mudanças de legislação.

## A Primeira Decisão (Arquitetura/Design)

- **Atores:** O sistema é acionado exclusivamente por atores humanos (Administrador e Analista de RH). Não há disparo automático externo no momento, simplificando a interface de gatilho do processamento.
- **Fronteiras:**
  - **Entradas:** Mês de competência, dados do funcionário (incluindo *Salário Base* - atenção, não o líquido, que é nossa saída!), e as exceções mensais/benefícios mapeados pelas Rubricas e Parâmetros Legais.
  - **Saída:** O demonstrativo de pagamento (Holerite), com o Salário Líquido apurado após a aplicação da cadeia de eventos (proventos e descontos).
- **Isolamento da Complexidade (Padrão Strategy + Polymorphism - GRASP):** Decidimos que a entidade `Rubrica` funcionará como um cadastro de configuração (dados) que se conecta aos Parâmetros Legais. Contudo, para não inflar a entidade `Rubrica` com lógica complexa, e para não criar um `ProcessadorFolha` (God Class) cheio de "IFs" baseados nos códigos das rubricas, utilizaremos o padrão **Strategy (GoF)** aliado ao padrão **Polymorphism (GRASP)** para a execução do cálculo.

## Estrutura Detalhada (How-To)

1. **Modelagem dos Dados:** Teremos a classe `Funcionario` (com seu Salário Base), a entidade `Rubrica` (cadastro contendo Natureza, Tipo e Incidência) e os `ParametrosLegais` (tabelas do INSS, IRRF).
2. **Motor de Cálculo (Strategy):** Criaremos uma interface genérica chamada `RegraDeCalculo`. Para cada tipo de imposto ou benefício complexo, criaremos uma classe específica (ex: `CalculadoraINSS`, `CalculadoraHoraExtra`) que implementa essa interface.
3. **Inversão de Controle:** A lógica não fica presa na entidade de dados. O `ProcessadorDeFolha` age como o orquestrador (Controller).

## Conexão das Partes

O `ProcessadorDeFolha` recebe o `Funcionario` e a lista de exceções do mês. Em vez de perguntar "Essa rubrica é de INSS? Se sim, calcule assim", o processador delega: ele itera sobre as instâncias de `RegraDeCalculo` passadas a ele, executando o método `calcular()`. Cada estratégia sabe exatamente como ir nos `ParametrosLegais`, buscar a alíquota correta e retornar o valor correspondente (Provento ou Desconto).

## Aplicação Prática

O Analista de RH seleciona o mês e clica em "Processar". O Controller busca os dados do `Funcionario`, constrói a lista de `Rubricas` do mês, e o `ProcessadorDeFolha` executa a cadeia de cálculo de forma polimórfica. Ao final, soma-se os Proventos, subtraem-se os Descontos e gera-se a entidade `Holerite` contendo o Salário Líquido e o detalhamento pronto para exibição e exportação em PDF.

## A Segunda Decisão (Estratégia de Testes e TDD)

- **O Foco Inicial:** Em vez de testar a folha inteira e gerar um forte acoplamento no primeiro momento, decidimos isolar o problema e atacar as regras específicas de alto nível.
- **A Fronteira:** O teste unitário nascerá validando as regras do **Salário Proporcional**, tendo como base o Salário Mínimo (piso nacional da CLT).
- **O Cenário (Happy Path):** Trabalhador em cenário ideal (presente todos os dias úteis do mês, sem faltas, sem horas extras). Isso garante que a fundação matemática de "dias trabalhados vs. dias úteis" e a barreira do piso salarial estejam 100% sólidas antes de inserirmos a complexidade dos descontos (INSS, etc).

## A Terceira Decisão (Segurança Financeira com BigDecimal)
- **O Problema:** Durante a implementação do `Salário Proporcional`, lidamos com divisões monetárias. A divisão do salário base pelos dias úteis pode gerar dízimas infinitas (ex: 1518 / 21 = 72,2857...).
- **A Decisão:** No Java, é estritamente proibido usar `double` ou `float` para lidar com dinheiro. Padronizamos o uso do `BigDecimal` com construtores baseados em `String` (ex: `new BigDecimal("1518.00")`).
- **How-To de Divisão:** Toda divisão dentro da nossa engine de regras deverá, obrigatoriamente, especificar a escala (2 casas decimais) e a regra de arredondamento `RoundingMode.HALF_UP` para evitar a quebra da aplicação com `ArithmeticException`.

## A Quarta Decisão (Persistência e Padrão Repository)
- **O Problema:** Como salvar dados no banco MySQL sem acoplar a nossa regra de negócios ao banco de dados?
- **A Decisão:** Utilizar o padrão **Repository (Pure Fabrication do GRASP)** aliado ao **Data Access Object (DAO)** utilizando JDBC puro (`java.sql.*`).
- **How-To:** O domínio define uma Interface (ex: `FaixaINSSRepository`), mas não sabe como ela é implementada. A camada de infraestrutura cria a classe `MySQLFaixaINSSRepository` que realiza a conexão `try-with-resources` e executa as queries. 
- **Prevenção de Bugs:** Proibimos a prática de "Exception Swallowing" (engolir exceções). Se um `SQLException` ocorre, o repositório relança como `RuntimeException` para que o Controller avise o usuário na tela, ao invés de falhar silenciosamente no terminal.

## A Quinta Decisão (Interface Gráfica com JavaFX)
- **O Problema:** Como organizar telas, estilos e navegação na aplicação Desktop.
- **A Decisão:** Utilizar o padrão **MVC** físico, separando a *View* (arquivos FXML em `src/main/resources`) do *Controller* (classes Java que capturam os dados da tela). Adotamos um design em *Dark Mode* padronizado via CSS (`style.css`).
- **How-To de Navegação:** Ao invés de trocar a cena inteira (o que quebra o fluxo de trabalho de RH), optamos por criar **novas Janelas (`Stage`)** sobrepostas para os formulários de cadastro (ex: `CadastroFuncionarioView.fxml` e `ParametrosView.fxml`). Isso mantém o Motor de Cálculo sempre ativo e em segundo plano.
