# Introdução

### 1.1 Propósito do Documento de Requisitos
O sistema automatiza o cálculo e a emissão de uma folha de pagamento de funcionários. A folha de pagamento é baseada no contrato CLT, onde temos condições e requisitos mais claros e determinados para projetar. Outros regimes, como não-CLT (PJ, estagiários) estão fora do escopo do sistema. 

### 1.2 Escopo do Produto
Para a primeira fase do projeto, o sistema contará com: 
* **Gestão de Cadastros:** Permitirá a inclusão, leitura e atualização de dados de funcionários, através de uma interface em comum, mantendo obrigatoriamente um histórico versionado de alterações salariais. A gestão também conta com a entrada e gestão de planilhas.
* **Histórico das Folhas de Pagamento:** Interface para visualizar as antigas folhas de pagamento referente aos meses anteriores.
 * **Lançamentos de exceção (mensais):** Interfaces para o lançamento de eventos (variáveis) que impactam o mês corrente, ex: horas extras, bônus, faltas, atrasos, atestados diversos. A entrada de exceção deverá ser feita de forma mensal. Lançamentos de exceções não alteram meses já fechados.
* **Processamento e fechamento**: Após o processamento, executará o cálculo matemático de transformação, considerando as exceções, proventos e a subtração de descontos (caso existam) registrados para o mês corrente.
* **Geração de Artefatos**: Compilará os dados processados para a emissão visual e exportável do demonstrativo de pagamento individual (Holerite\Contra-Cheque). Assim como a permissão de consultas referentes a meses anteriores já fechados. As consultas abrangem um período mensal fixo. 
* **Gestão de Rubricas Fixas e Variáveis:** Calculará o valor da diária de trabalho ("Valor dia") com base no salário contratual e processará a concessão de benefícios fixos (ex: Cesta Básica) e variáveis (ex: Participação nos Lucros e Resultados - PRL).
* **Provisões e Encargos:** Engloba o cálculo automatizado de provisões trabalhistas essenciais exibidas no fechamento mensal, especificamente a proporcionalidade ou valor integral de **13º Salário**, **férias + ⅓** e o recolhimento do **FGTS (com a multa de 40% embutida ou provisionada).

### 1.3 Definições, acrônimos e abreviações
Para garantir a compreensão uniforme deste documento por leitores de diferentes áreas (técnicas e de negócios), os seguintes acrônimos e termos legislativos são definidos:
* **CLT:** Consolidação das Leis do Trabalho. Legislação brasileira que 
  regulamenta as relações de trabalho e dita as regras de remuneração 
  aplicáveis a este sistema.
* **FGTS:** Fundo de Garantia do Tempo de Serviço.
* **PLR:** Participação nos Lucros e Resultados. Também referenciada como 
  PRL em alguns contextos. Não integra a base de cálculo de INSS, FGTS 
  ou férias (Lei nº 10.101/2000).
* **13º Salário:** Gratificação natalina compulsória prevista 
  na Lei nº 4.090/1962.
* **Rubrica:** Código ou categoria contábil que identifica a natureza de um valor na folha de pagamento (ex: Rubrica de Salário Base, Rubrica de Hora Extra).
* **Holerite / Contra-cheque:** O demonstrativo impresso ou digital que detalha todas as rubricas de proventos e descontos que compuseram o salário líquido do colaborador em uma competência.
* **Provento:** Qualquer valor financeiro de natureza creditícia que é somado ao salário do colaborador (ex: bônus, comissões).
- **Desconto:** Qualquer valor financeiro de natureza debitaria que é subtraído do salário do colaborador (ex: faltas, atrasos).

## 1.5 Estruturação do Documento de Requisitos 
Este documento segue o padrão IEEE 830 (Software Requirements Specification) adaptado ao contexto do projeto SFP-CLT. Está organizado em quatro seções principais e um índice, conforme descrito abaixo:

**Seção 1 — Introdução:** Apresenta o propósito do documento, o escopo do produto, o glossário de termos e acrônimos, as referências utilizadas e esta descrição estrutural.

**Seção 2 — Descrição Geral:** Descreve o sistema em alto nível: sua perspectiva de produto, principais funcionalidades, perfis de usuário, restrições gerais e premissas que condicionam o desenvolvimento.

**Seção 3 — Requisitos Específicos:** Núcleo do documento. Subdivide-se em:
- **3.1 Requisitos de Interface com o Usuário (GUI):** Define os padrões visuais e comportamentais de todas as telas do sistema, incluindo login, navegação, cadastros, lançamentos, processamento, holerite e histórico.
- **3.2 Requisitos Funcionais:** Especifica o comportamento funcional do sistema agrupado por módulo: cadastros, apuração de frequência, processamento de proventos e descontos, encargos e provisões, saídas e auditoria. Cada requisito é rastreável a uma regra de negócio (RN-XX).
- **3.3 Requisitos Não Funcionais:** Estabelece restrições de qualidade mensuráveis nas dimensões de desempenho, segurança, confiabilidade, usabilidade, disponibilidade, manutenibilidade, portabilidade e conformidade legal.
- **3.4 Requisitos de Dados:** Define o modelo relacional de dados, as regras de integridade referencial e as políticas de segurança e retenção de dados.

**Seção 4 — Apêndices:** Material de referência complementar que suporta a Seção 3, incluindo a tabela completa de rubricas padrão (4.1), os modelos de fórmulas de cálculo com exemplos numéricos (4.2), o glossário expandido (4.3), a matriz de rastreabilidade entre requisitos, módulos e telas (4.4) e a tabela consolidada de regras de negócio RN-01 a RN-15 (4.5).

**Seção 5 — Índice:** Lista estruturada de todas as seções e subseções do documento com suas respectivas numerações, para navegação rápida.

**Convenções adotadas neste documento:**
- Requisitos funcionais são identificados no formato **X.Y.Z.N**, onde X.Y.Z indica a seção e N é o número sequencial do requisito dentro dela.
- Regras de negócio são referenciadas pelo código **[RN-XX]** e detalhadas nos Apêndices (seção 4.5).
- Fórmulas de cálculo são identificadas pelo código **[F-XX]** e detalhadas na seção 4.2.
- Itens marcados com `*****` ou `VER` indicam pontos ainda em aberto, pendentes de decisão pela equipe.

# 2. Descrição Geral

### 2.1 - Perspectiva do Produto
* O sistema é uma aplicação desktop standalone desenvolvida em Java, voltada para o escritório independentes ou para departamentos inclusos na empresa (Recursos Humanos ou contabilidade). 
- A interface do sistema é desenvolvida através do Java Desktop (biblioteca Swing ou JavaFX). 
- A aplicação deve ser executada localmente na máquina do usuário, conectada a um banco de dados, podendo, ou não, estar na mesma máquina (instalação local) ou em algum servidor na rede interna da empresa (com a integração sendo responsável pela empresa).   

### 2.2 Funcionalidades do Produto
As principais funções esperadas pelo sistema são:
- **Cálculo Automático**: O processamento do salário bruto em salário líquido.
- **Gestão de Ponto/Exceções**: Gestão do relógio de ponto dos funcionários, contendo as horas extras de trabalho, faltas e atrasos.
- **Provisões Trabalhistas**: Cálculo mensal proventos e exceções.
- **Emissão de Documentos**: Geração do Holerite e, respectivamente, a sua emissão como arquivo PDF.

### 2.3 Características do Usuário
O sistema tem como usuário principal os analista de escritórios de RH/DP (integrados ou não na empresa alvo) , que já possuem o conhecimento das regras da CLT, mas que buscam agilidade no cálculo manual. 

### 2.4 Restrições Gerais
* **Legislação**: O sistema deve seguir estritamente a tabela vigente da CLT (2024/2025/2026).
* **Segurança**: Dados salariais são protegidos pela LGPD (Lei Geral de Proteção de Dados). O acesso deve ser restrito por login e senha.
* **Conformidade**: O sistema não permite a alteração de folhas de meses já encerrados para garantir a integridade contábil.

### 2.5 Suposições e Dependências
É esperado que o usuário possua acesso a um computador suficientemente capaz de executar o sistema e de manter o banco de dados do sistema. Se espera que o usuário tenha noção prévia (mínima) a respeito das leis trabalhistas do contrato CLT e de tributos de exceções (FGTS, etc.).

O sistema depende estritamente do cadastramento mensal de "exceções" (faltas/extras) antes do fechamento da folha, assim como uma conexão estável com o banco de dados.  

Ao emitir a folha de pagamento, assume-se que o usuário esteja em prol da verdade, não se sujeitando a emissão de contratos fantasmas (que não existam), falsa verdade, ou qualquer tipo de divergência em desacordo com a lei.

# 3. Requisitos Específicos

### 3.1 Requisitos de Interface com o Usuário (GUI)

### 3.1.1 Padrões Gerais da Interface
Todos os seguintes requisitos se aplicam a todas as telas do sistema e são referentes a nomenclatura \[3.1.1]\:
1. O sistema deve possuir uma interface gráfica.
2. Todas as telas devem exibir, no cabeçalho, o nome do sistema : "SFP-CLT".
3. Todas as telas devem exibir, no cabeçalho, o nome do usuário.
4. A paleta de cores, tipografia e espaçamento devem ser padronizados e consistentes em todas as telas da aplicação, seguindo um guia de estilo definido pré-definido pelo sistema (paleta de cores, tipografia e espaçamento).
5. Todos os formulários devem indicar visualmente os campos obrigatórios, utilizando marcador de asterisco ( * ) em vermelho ao lado do rótulo.
6. O sistema deve exibir mensagem de confirmação com opções de "Confirmar" e "Cancelar", antes de executar qualquer operação irreversível.
7. O sistema deve exibir mensagens de erro em linguagem clara e objetiva, sem códigos de exceção Java expostos ao usuário.
8. Campos monetários devem ser formatados automaticamente com separador de milhar (ponto) e duas casas decimais (ex: R$ 3.500,00).
9. Campos de data devem utilizar máscara DD/MM/AAAA e validar se a data informada é uma data calendário válida.
10. O sistema deve manter a resolução mínima de tela de 1280 x 720 pixels como referência de layout.
11. A interface deve permitir acesso às funcionalidades do sistema.

### 3.1.2 Tela de Login e Autenticação
Todos os seguintes requisitos se aplicam a todas as telas do sistema e são referentes a nomenclatura \[3.1.2]\:
1. O sistema deve apresentar uma tela de login como tela inicial obrigatória, bloqueando o acesso a qualquer outra funcionalidade antes da autenticação do usuário 
2. A tela de login deve conter o campo "Usuário" com as seguintes especificações:
	1. Deve ser em formato de texto;
	2. Restringido para no máximo 50 caracteres;
	3. Preenchimento obrigatório.
3. A tela de login deve conter o campo "Senha" contendo as seguintes especificações :
	1. Deve ser em formato de texto;
	2. Não visível para usuário, ex: ocultado com asteriscos;
	3. Preenchimento obrigatório.
4. A tela de login deve exibir o logotipo ou nome do sistema.
5. A tela de login deve exibir a versão atual do software.
6. O sistema deve registrar um histórico (log) de segurança contendo todas as tentativas de acesso (bem-sucedida ou não). O histórico deve conter:
	1. Nome do usuário;
	2. Data de entrada;
	3. Hora de entrada;
	4. Informar se o acesso foi bem-sucedido ou não.
7. O campo de senha não deve permitir copiar o conteúdo via atalho de teclado (Ctrl+C) ou menu de contexto.
8. A sessão do usuário deve ser encerrada automaticamente após 30 minutos de inatividade, redirecionando para a tela de login com mensagem explicativa.
9. O sistema deve oferecer opção "Sair" acessível em todas as telas para encerramento manual da sessão com segurança.

### 3.1.3 Menu Principal e sua Navegação
Todos os seguintes requisitos se aplicam a todas as telas do sistema e são referentes a nomenclatura \[3.1.3]\:
1. Após autenticação bem-sucedida, o sistema deve exibir um menu principal com acesso a todos os módulos habilitados para o perfil do usuário logado.
2. O menu principal deve conter as seguintes entradas: 
	1. Cadastros de funcionários;
	2. Planilha para o relógio de ponto dos funcionários;
	3. Lançamento de exceções; 
	4. Emissão da folha de pagamento do mês atual; 
	5. Histórico;
	6. Sair.
3. Itens de menu restritos ao perfil de Administrador devem ser desabilitados para os usuários.
4. O menu deve apresentar um indicador visual do módulo atualmente ativo (item selecionado destacado).
5. O sistema deve exibir um dashboard, de forma resumida, contedo: 
	1. O mês de competência corrente; 
	2. A quantidade de funcionários ativos cadastrados; 
	3. O status da folha do mês corrente ("Aberta" ou "Fechada").

### 3.1.4 Tela de Cadastro de Funcionários
Todos os seguintes requisitos se aplicam a todas as telas do sistema e são referentes a nomenclatura \[3.1.4]\:
1. O sistema deve apresentar tela de cadastro de funcionários com os seguintes campos obrigatórios: 
	1. Nome completo do funcionário, com o tipo do campo sendo texto e contendo no máximo 150 caracteres; 
	2. CPF (máscara 000.000.000-00, único no sistema); 
	3. Data de Nascimento (DD/MM/AAAA);
	4. Data de Admissão (DD/MM/AAAA);
	5. Cargo, com tipo texto de no máximo 100 caracteres;
	6. Salário Base (valor monetário, mínimo R$ 1.518,00 conforme piso CLT 2025).
2. O sistema deve apresentar os seguintes campos opcionais para cada funcionário:  
	1. Número de Dependentes (inteiro ≥ 0)
	2. Banco e Conta para depósito salarial.
3. O campo CPF deve ser validado pelo algoritmo de dígitos verificadores da Receita Federal. O sistema deve rejeitar CPFs com todos os dígitos iguais.
4. O sistema deve impedir o cadastro de dois funcionários com o mesmo CPF, exibindo mensagem de erro específica.
5. A tela de listagem de funcionários deve apresentar tabela com colunas: 
	1. Nome;
	2. CPF (parcialmente ocultado: \*\*\*XXX-XX);
	3. Cargo;
	4. Salário Base;
	5. Status (Ativo/Inativo).
6. A listagem deve permitir busca por CPF.
7. O sistema deve permitir inativar um funcionário (demissão), registrando a data de desligamento. 
8. Funcionários inativos não devem participar do processamento da folha de meses posteriores à data de desligamento.
9. O sistema não deve permitir a exclusão permanente de registros de funcionários, apenas a inativação, para preservar o histórico de folhas passadas.


## 3.2 Requisitos Funcionais
### 3.2.1 - Cadastros e Configurações
Este módulo engloba os requisitos referentes ao registro e a manutenção dos dados empresariais do sistema: empresa, funcionários, usuários e parâmetros fiscais. A integridade dos dados cadastrais é pré-requisito para a correção de todos os cálculos subsequentes.

#### 3.2.1.1 Cadastro da Empresa

1. O cadastro deve incluir obrigatoriamente: 
	1. Razão Social, compreendendo os seguintes requisitos:
		1. Deve ser em formato texto;
		2. Deve conter no máximo 150 caracteres.
	2. CNPJ;	
	3. Endereço, contendo os seguintes campos:
		1. Logradouro; 
		2. Número;
		3. Complemento; 
		4. Bairro;
		5. Cidade;
		6. UF;
		7. CEP
	4. Nome do Responsável Legal.
2. O CNPJ deve ser validado pelo algoritmo oficial de dígitos verificadores da Receita Federal; 
3. O sistema deve armazenar os seguintes parâmetros fiscais utilizados nos cálculos da folha:	
	1. Alíquota de FGTS: valor percentual 8,00%.	
	2. Horas mensais contratuais (padrão: 220 horas).	
	3. Percentual de adicional de hora extra padrão (50%), fixo por legislação.	
	4. Percentual de adicional de hora extra especial (100%): aplicável a domingos, feriados e período noturno contínuo, conforme CLT Art. 59-A.	
	5. Valor mensal da Cesta Básica: valor monetário (R$), podendo ser zero.	

#### 3.2.1.2 Cadastro de Funcionários

1. O sistema deve permitir o cadastro, a edição e a inativação de funcionários.
2. A exclusão permanente de funcionários de registros não é permitida.
3. Campos obrigatórios para cadastro de funcionários: 
	1. Nome Completo;
	2. CPF;
	3. Data de Nascimento;
	4. Data de Admissão;
	5. Cargo; 
	6. Salário Base Contratual;
	7. Banco;
	8. Conta-corrente para depósito.
4. Campos opcionais:
	1. Número de Dependentes;
	2. E-mail pessoal e/ou institucional.
5. O sistema deve garantir unicidade do CPF.
6. O salário base do funcionário deve ser maior e/ou igual ao salário mínimo nacional vigente. 
7. O sistema deve rejeitar valores abaixo do piso, exibindo mensagem com o valor mínimo aceitável.
8. O sistema deve manter histórico completo de alterações salariais. Cada entrada do histórico deve registrar: 
	1. Salário anterior;
	2. Novo salário; 
	3. Data de alteração; 
	4. Usuário que alterou.
9. O sistema deve identificar o mês de admissão de cada funcionário para aplicar cálculo proporcional automaticamente na primeira folha processada após admissão.
10. A inativação de um funcionário deve registrar obrigatoriamente a data de desligamento. 
11. O sistema deve calcular e exibir automaticamente (no cadastro do funcionário) o tempo de empresa em anos e meses completos com base na data de admissão.

#### 3.2.1.3 Cadastro de Rubricas

1. O sistema deve manter uma tabela de rubricas (códigos de itens da folha) que classifica cada valor como provento ou desconto, fixo ou variável.
2. Cada rubrica deve conter: 
	1. Código único (numérico de 3 dígitos); 
	2. Natureza (provento ou desconto); 
	3. Tipo (Fixo ou Variável); 
	4. Incidência (incide INSS, incide FGTS ou incide IRRF).
3. O sistema já deve incluir as rubricas esperadas (padronizadas) pré-cadastradas conforme apresentadas no Apêndice 4.1. 
4. As rubricas de códigos 001 a 005 são consideradas obrigatórias e não podem ser excluídas nem ter seu código, natureza ou flags de incidência alterados. 
5. As rubricas de códigos 006 a 008 e 101 a 106 podem ser excluídas pelo Administrador, desde que não estejam referenciadas em nenhuma folha já processada.
6. *O Administrador pode criar rubricas personalizadas (código ≥ 500) para benefícios ou descontos específicos da empresa.*

#### 3.2.1.4 Cadastro de Usuários do Sistema

1. O sistema deve permitir o cadastro de usuários.
2. Para o usuário "Administrador", são incluídos:
	1. Acesso total; 
	2. Configuração de parâmetros;
	3. Gestão de usuários, englobando:
		1. Adicionar usuário;
		2. Remover usuário;
3. O usuário "Comum", terá o acesso operacional: 
	1. Cadastros;
	2. Lançamentos; 
	3. Processamento;
	4. Emissão dos holerites.
4. Deve existir ao menos um usuário com perfil de "Administrador" ativo a todo momento.
5. Nenhuma senha deve ser armazenada ou exibida em texto plano em qualquer circunstância.
