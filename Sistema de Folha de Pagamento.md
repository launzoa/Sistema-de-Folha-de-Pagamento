# Introdução
### 1.1 Propósito do Documento de Requisitos
O sistema automatiza o cálculo e a emissão de uma folha de pagamento de funcionários em um contexto empresarial. A folha de pagamento é baseada no regime/contrato CLT. Outros regimes, como não-CLT (PJ, estagiários) estão fora do escopo do sistema, devido as suas condições e requisitos serem menos claros e determinados, variando de contrato a contrato. 

### 1.2 Escopo do Produto
Para a primeira fase do projeto, o sistema contará com: 
* **Gestão de cadastros:** Permitirá o cadastro/inclusão, leitura e atualização de dados de funcionários, através de uma interface em comum, mantendo obrigatoriamente um histórico versionado de alterações salariais. A gestão também conta com a entrada e gestão de planilhas.
* **Histórico das Folhas de Pagamento:** Interface para a visualização das antigas folhas de pagamento referente aos meses anteriores.
* **Relógio de ponto:** Interface para o cadastramento do horário de trabalho diário de um funcionário de acordo com a Regra de Tolerância de 5 minutos para mais ou para menos em cada batida entrada, almoço, volta do almoço e saída.  
* **Lançamentos de exceções (mensais):** Interfaces para o lançamento de eventos (variáveis) que impactam o mês corrente, ex: horas extras, bônus, faltas, atrasos, atestados diversos. A entrada de exceção deverá ser feita de forma mensal. Lançamentos de exceções não alteram meses já fechados.
* **Processamento e fechamento**: Após o processamento, executará o cálculo matemático de transformação, considerando as exceções, proventos e a subtração de descontos (caso existam), registrados para o mês corrente.
* **Geração de artefatos**: Compilará os dados processados para a emissão visual e exportável do demonstrativo de pagamento individual (Holerite\Contra-Cheque). 
* **Consulta de Holerites**: Será permitido as consultas referentes ao Holerite de meses anteriores já fechados. 
* **Gestão de Rubricas fixas e variáveis:** Com base no salário contratual e nas rubricas e/ou exceções, processará a concessão de benefícios fixos (ex: Cesta Básica) e variáveis (ex: Participação nos Lucros e Resultados - PRL).
* **Provisões e Encargos:** Engloba o cálculo automatizado de provisões trabalhistas essenciais exibidas no fechamento mensal, especificamente a proporcionalidade ou valor integral de **13º Salário**, **férias + ⅓** e o recolhimento do **FGTS (com a multa de 40% embutida ou provisionada).

### 1.3 Definições, acrônimos e abreviações
Para garantir a compreensão uniforme deste documento por leitores de diferentes áreas (técnicas e de negócios), os seguintes acrônimos e termos legislativos são definidos:
* **CLT:** Consolidação das Leis do Trabalho. Legislação brasileira que regulamenta as relações de trabalho e dita as regras de remuneração aplicáveis a este sistema.
* **FGTS:** Fundo de Garantia do Tempo de Serviço.
* **PLR:** Participação nos Lucros e Resultados. Também referenciada como PRL em alguns contextos. Não integra a base de cálculo de INSS, FGTS ou férias (Lei nº 10.101/2000).
* **13º Salário:** Gratificação natalina compulsória prevista na Lei nº 4.090/1962.

## 1.4 Referências
* As informações referentes ao regime CLT foram retiradas do documento: Consolidação das Leis do Trabalho - CLT e normas correlatas. Originária da Constituição da República Federativa do Brasil

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
* O sistema é uma aplicação desktop standalone desenvolvida em Java, voltada para o escritório independentes ou para departamentos inclusos na empresa (recursos humanos ou contabilidade). 
- A interface do sistema é desenvolvida através do Java Desktop (biblioteca Swing). 
- A aplicação deve ser executada localmente na máquina do usuário, conectada a um banco de dados, podendo, ou não, estar na mesma máquina (instalação local) ou em algum servidor na rede interna da empresa (com a integração sendo responsável pela empresa).   

### 2.2 Funcionalidades do Produto
As principais funções esperadas pelo sistema contam com:
- **Cálculo automático**: O processamento do salário bruto em salário líquido.
- **Gestão de ponto/exceções**: Gestão do relógio de ponto dos funcionários, contendo as horas extras de trabalho, faltas e atrasos.
- **Provisões trabalhistas**: Cálculo mensal de proventos e exceções.
- **Emissão de documentos**: Geração do Holerite e, respectivamente, a emissão do documento como um arquivo PDF.

### 2.3 Características do Usuário
O sistema tem como usuário principal os analista de escritórios de recursos humanos (RH) e/ou departamento pessoal (DP), integrados ou não na empresa, que já possuem o conhecimento das regras da CLT, mas que estão buscando agilidade tanto no cálculo, quanto no processamento manual. 

### 2.4 Restrições Gerais
* **Legislação**: O sistema deve seguir estritamente a tabela vigente da CLT (2024/2025/2026).
* **Segurança**: Dados salariais são protegidos pela LGPD (Lei Geral de Proteção de Dados). O acesso deve ser restrito por login e senha.
* **Conformidade**: O sistema não permite a alteração de folhas de meses já encerrados para garantir a integridade contábil.
* **Tecnologia**: O sistema deve ser desenvolvido integralmente em Java (versão 11 ou superior), sem dependência de navegador web ou servidor de aplicação externo para sua operação.

### 2.5 Suposições e Dependências
É esperado que o usuário possua acesso a um computador suficientemente capaz de executar o sistema e de manter o banco de dados do sistema. Se espera que o usuário tenha noção prévia (mínima) a respeito das leis trabalhistas do contrato CLT e de tributos de exceções (FGTS, etc.).

O sistema depende estritamente do cadastramento mensal de "exceções" (faltas/extras) antes do fechamento da folha, assim como uma conexão estável com o banco de dados.  

Ao emitir a folha de pagamento, assume-se que o usuário esteja em prol da verdade, não se sujeitando a emissão de contratos fantasmas (que não existam), falsa verdade, ou qualquer tipo de divergência em desacordo com a lei.

# 3. Requisitos Específicos

### 3.1 Requisitos de Interface com o Usuário (GUI)

### 3.1.1 Padrões Gerais da Interface
Todos os seguintes requisitos se aplicam a todas as telas do sistema.
* 3.1.1.1 - O sistema deve possuir uma interface gráfica.
* 3.1.1.2 - Todas as telas devem exibir, no cabeçalho, o nome do sistema : "SFP-CLT".
* 3.1.1.3 - Todas as telas devem exibir, no cabeçalho, o nome do usuário.
* 3.1.1.4 - A paleta de cores, tipografia e espaçamento devem ser padronizados e consistentes em todas as telas da aplicação, seguindo um guia de estilo definido pré-definido pelo sistema (paleta de cores, tipografia e espaçamento).
* 3.1.1.5 - Todos os formulários devem indicar visualmente os campos obrigatórios, utilizando marcador de asterisco ( * ) em vermelho ao lado do rótulo.
* 3.1.1.6 - O sistema deve exibir mensagem de confirmação com opções de "Confirmar" e "Cancelar", antes de executar qualquer operação irreversível.
* 3.1.1.7 - O sistema deve exibir mensagens de erro em linguagem clara e objetiva, sem códigos de exceção Java expostos ao usuário.
* 3.1.1.8 - Campos monetários devem ser formatados automaticamente com separador de milhar (ponto) e duas casas decimais (ex: R$ 3.500,00).
* 3.1.1.9 - Campos de data devem utilizar máscara DD/MM/AAAA e validar se a data informada é uma data calendário válida.
* 3.1.1.10 - O sistema deve manter a resolução mínima de tela de 1280 x 720 pixels como referência de layout.
* 3.1.1.11 - A interface deve permitir acesso às funcionalidades do sistema.

### 3.1.2 Tela de Login e Autenticação
Todos os seguintes requisitos se aplicam as telas de login e autenticação inicial do sistema: 
* 3.1.2.1 - O sistema deve apresentar uma tela de login como tela inicial obrigatória, bloqueando o acesso a qualquer outra funcionalidade antes da autenticação do usuário 
* 3.1.2.2 - A tela de login deve conter o campo "Usuário" com as seguintes especificações:
	1. O campo deve ser em formato de texto;
	2. O campo é restringido para no máximo 50 caracteres;
	3. O preenchimento é obrigatório.
* 3.1.2.3 - A tela de login deve conter o campo "Senha" contendo as seguintes especificações :
	1. O campo deve ser em formato de texto;
	2. O campo não deve ser visível para usuário (ocultado por asteriscos, ex: \*\*\*\*);
	3. O preenchimento é obrigatório.
* 3.1.2.4 - A tela de login deve exibir o logotipo ou nome do sistema.
* 3.1.2.5 - A tela de login deve exibir a versão atual do software.
* 3.1.2.6 - O sistema deve registrar um histórico (log) de segurança contendo todas as tentativas de acesso (bem-sucedida ou não). O histórico deve conter:
	1. Nome do usuário;
	2. Data de entrada;
	3. Hora de entrada;
	4. Informar se o acesso foi bem-sucedido ou não.
* *3.1.2.7* - O campo de senha não deve permitir copiar o conteúdo via atalho de teclado (Ctrl+C) ou menu de contexto.
* *3.1.2.8* - A sessão do usuário deve ser encerrada automaticamente após 30 minutos de inatividade, redirecionando para a tela de login com mensagem explicativa.
* 3.1.2.9 - O sistema deve oferecer opção "Sair" acessível em todas as telas para encerramento manual da sessão com segurança.

### 3.1.3 Menu Principal e sua Navegação
Todos os seguintes requisitos se aplicam as telas para o menu principal e sua navegação:
* 3.1.3.1 - Após autenticação bem-sucedida, o sistema deve exibir um menu principal com acesso a todos os módulos habilitados para o perfil do usuário logado.
* 3.1.3.2 - O menu principal deve conter as seguintes entradas: 
	1. Cadastros de funcionários;
	2. Planilha para o relógio de ponto dos funcionários;
	3. Lançamento de exceções; 
	4. Emissão da folha de pagamento do mês atual; 
	5. Histórico;
	6. Sair.
* 3.1.3.3 - Itens de menu restritos ao perfil de Administrador devem ser desabilitados para os usuários.
* 3.1.3.4 - O menu deve apresentar um indicador visual do módulo atualmente ativo (item selecionado destacado).
* 3.1.3.5 - O sistema deve exibir um dashboard, de forma resumida, abrangendo: 
	1. O mês de competência corrente; 
	2. A quantidade de funcionários ativos cadastrados; 
	3. O status da folha do mês corrente ("Aberta" ou "Fechada").

### 3.1.4 Tela de Cadastro de Funcionários
Todos os seguintes requisitos se aplicam as telas de cadastro dos funcionários:
* 3.1.4.1 - O sistema deve apresentar tela de cadastro de funcionários com os seguintes campos obrigatórios: 
	1. Nome completo do funcionário, com o tipo do campo sendo texto e contendo no máximo 150 caracteres; 
	2. CPF (máscara 000.000.000-00, único no sistema); 
	3. Data de Nascimento (DD/MM/AAAA);
	4. Data de Admissão (DD/MM/AAAA);
	5. Cargo, com tipo texto de no máximo 100 caracteres;
	6. Salário Base (valor monetário, mínimo R$ 1.518,00 conforme piso CLT 2025).
* 3.1.4.2 - O sistema deve apresentar os seguintes campos opcionais para cada funcionário:  
	1. Número de Dependentes (inteiro ≥ 0)
	2. Banco e Conta para depósito salarial.
* 3.1.4.3 - O campo CPF deve ser validado pelo algoritmo de dígitos verificadores da Receita Federal. O sistema deve rejeitar CPFs com todos os dígitos iguais.
* 3.1.4.4 - O sistema deve impedir o cadastro de dois funcionários com o mesmo CPF, exibindo mensagem de erro específica.
* 3.1.4.5 - A tela de listagem de funcionários deve apresentar tabela com colunas: 
	1. Nome;
	2. CPF (parcialmente ocultado: \*\*\*XXX-XX);
	3. Cargo;
	4. Salário Base;
	5. Status (Ativo/Inativo).
* 3.1.4.6 - A listagem deve permitir busca por CPF.
* 3.1.4.7 - O sistema deve permitir inativar um funcionário (demissão), registrando a data de desligamento. 
* 3.1.4.8 - Funcionários inativos não devem participar do processamento da folha de meses posteriores à data de desligamento.
* 3.1.4.9 - O sistema não deve permitir a exclusão permanente de registros de funcionários, apenas a inativação, para preservar o histórico de folhas passadas.


### 3.1.5 Tela de Cadastro da Empresa
Todos os seguintes requisitos se aplicam a tela de cadastro da empresa:
- 3.1.5.1 - O sistema deve apresentar tela de configuração dos dados da empresa com os seguintes campos obrigatórios: 
	1. Razão Social (texto); 
	2. CNPJ (máscara 00.000.000/0000-00);
	3. Endereço completo e Responsável Legal (nome).
- 3.1.5.2 - O CNPJ deve ser validado pelo algoritmo de dígitos verificadores da Receita Federal.
- 3.1.5.3 - Alterações nos parâmetros fiscais da empresa devem exigir confirmação com diálogo modal e devem ser registradas em log de auditoria com o usuário e data.

### 3.1.6 Tela de Lançamentos de Exceção Mensais
Todos os seguintes requisitos se aplicam a tela de lançamento de exceções mensais:
- 3.1.6.1 - O sistema deve apresentar tela de lançamentos mensais, acessível somente quando a folha do mês corrente estiver com status "Aberta".
- 3.1.6.2 - A tela deve exibir o mês e ano de competência corrente de forma destacada, impedindo ambiguidade sobre o período de referência dos lançamentos.
- 3.1.6.3 - O sistema deve permitir selecionar um funcionário ativo por meio de campo de busca antes de registrar qualquer lançamento.
- 3.1.6.4 - Para cada funcionário selecionado, o sistema deve permitir o registro dos seguintes tipos de evento:
	1. **Horas Extras:** Quantidade de horas extras, escrito em minutos (ex.: 80) e data de ocorrência. O sistema deve calcular e exibir o valor monetário.
	2. **Faltas Injustificadas:** Quantidade de dias inteiros ou fração (em horas). O sistema deve calcular e exibir o desconto correspondente em tempo real.
	3. **Atrasos:** Quantidade de minutos e data de ocorrência. O sistema deve calcular o desconto proporcional em tempo real.
	4. **Atestado Médico:** Data de início, data de fim e arquivo digitalizado (PDF). Atestados justificam ausências e neutralizam o desconto de falta.
	5. **Bônus / Gratificação:** Valor monetário livre e descrição textual (obrigatória, máx. 200 caracteres).
- 3.1.6.5 - O sistema deve impedir o lançamento de eventos em meses cuja folha já esteja com status "Fechada", exibindo mensagem explicativa ao usuário.
- 3.1.6.6 - O sistema deve permitir a edição e exclusão de lançamentos do mês corrente enquanto a folha estiver aberta.

### 3.1.7 Tela de Processamento e Fechamento da Folha
Todos os seguintes requisitos se aplicam a tela de processamento e fechamento da folha de pagamento:
- 3.1.7.1 - O sistema deve apresentar tela de processamento da folha com listagem de todos os funcionários ativos e seus respectivos status de processamento.
- 3.1.7.2 - O sistema deve disponibilizar botão "Processar Folha" que executa o cálculo para todos os funcionários ativos de forma sequencial ou paralela.
- 3.1.7.3 - Após o processamento, o sistema deve exibir resumo tabular contendo, para cada funcionário: 
	1. Nome; 
	2. Salário Bruto;
	3. Total de Proventos; 
	4. Total de Descontos;
	5. Salário Líquido;
	6. Status.
* 3.1.7.4 - O sistema deve permitir re-processar a folha enquanto estiver com status "Aberta", sobrescrevendo os valores calculados anteriormente após confirmação.
- 3.1.7.5 - O sistema deve disponibilizar botão "Fechar Folha" que torna o mês de competência imutável.
- 3.1.7.6 - O botão "Fechar Folha" deve estar desabilitado enquanto existir algum funcionário ativo com status "Pendente" ou "Erro" no processamento.
- 3.1.7.7 - Após o fechamento, o sistema deve gerar e exibir automaticamente o relatório de provisões do mês, com 13º Salário, Férias + ⅓ e FGTS para cada funcionário.

### 3.1.8 Tela de Visualização e Emissão de Holerite
Todos os seguintes requisitos se aplicam a tela de visualização e a emissão da folha de pagamento:
- 3.1.8.1 - O sistema deve apresentar tela de holerite com seletor de funcionário e seletor de mês/ano de competência. Apenas meses com folha fechada devem estar disponíveis.
- 3.1.8.2 - O holerite exibido deve conter obrigatoriamente os seguintes campos, em conformidade com o art. 464 da CLT:
	1. Dados da empresa: Razão Social e CNPJ.
	2. Dados do funcionário: Nome, CPF (parcialmente ocultado), Cargo, Data de Admissão e PIS/PASEP.
	3. Mês e ano de competência.
	4. Tabela de proventos: código da rubrica, descrição, referência e valor.	
	5. Tabela de descontos: código da rubrica, descrição, referência e valor.
	6. Totais: Total de Proventos, Total de Descontos e Salário Líquido (em destaque).
- 3.1.8.3 - Linha de assinatura do colaborador (para versão impressa).
- 3.1.8.4 - O sistema deve disponibilizar botão "Exportar PDF" que gera o holerite em formato PDF/A.
- 3.1.8.5 - O sistema deve permitir a visualização de holerites de meses anteriores fechados, sem limite de período para consulta.
- 3.1.8.6 - O holerite em PDF deve ser gerado sem a linha de assinatura (campo preenchido com "Documento Digital — Dispensa Assinatura") quando exportado eletronicamente.

### 3.1.9 Tela de Histórico de Folhas de Pagamento
- 3.1.9.1 - O sistema deve apresentar tela de histórico com listagem de todas as folhas já processadas, organizadas por mês/ano em ordem cronológica decrescente.
- 3.1.9.2 - Para cada folha listada, devem ser exibidos: Mês/Ano de Competência, Status, Data de Fechamento, Quantidade de Funcionários Processados e Total Líquido da Folha.
- 3.1.9.3 - A tela deve permitir filtragem por intervalo de datas (De: Mês/Ano — Até: Mês/Ano).
- 3.1.9.4 - O sistema deve permitir a seleção de uma folha fechada para visualização detalhada, exibindo o resumo individual de cada funcionário naquela competência.
	1. O sistema não deve exibir botões de edição ou processamento para folhas com status "Fechada", apenas o botão "Visualizar".

## 3.2 Requisitos Funcionais

### 3.2.1 Cadastro da Empresa
Todos os seguintes requisitos se aplicam ao cadastro e configuração da empresa e seus parâmetros:
- 3.2.1.1 - O cadastro da empresa deve incluir obrigatoriamente: 
	- Razão Social (texto com até 150 caracteres);
	- CNPJ;
	- Endereço Completo (Logradouro, Número, Complemento, Bairro, Cidade, UF, CEP);
	- Nome do Responsável Legal.
- 3.2.1.2 - O CNPJ inserido deve ser validado obrigatoriamente pelo algoritmo oficial de dígitos verificadores da Receita Federal.
- 3.2.1.3 - O sistema deve rejeitar CNPJs com todos os dígitos iguais (ex.: 11.111.111/1111-11), mesmo que o cálculo matemático os valide.
- 3.2.1.4 - O sistema deve armazenar os seguintes parâmetros fiscais: Alíquota de FGTS (8,00%), Horas mensais contratuais (padrão 220 horas), Adicional de hora extra padrão (50%), Adicional de hora extra especial (100%) e Valor mensal da Cesta Básica (R$).

### 3.2.2 Cadastro de Funcionários
Todos os seguintes requisitos se aplicam ao cadastro, manutenção e inativação de funcionários:
- 3.2.2.1 - O sistema deve permitir o cadastro, a edição e a inativação de funcionários sob o regime CLT.
- 3.2.2.2 - O sistema não deve permitir a exclusão permanente de registros de funcionários.
- 3.2.2.3 - O sistema deve exigir o preenchimento dos seguintes campos obrigatórios: 
	- Nome Completo; 
	- CPF; 
	- Data de Nascimento;
	- Data de Admissão; 
	- Cargo;
	- Salário Base Contratual;
	- Banco;
	- Conta-corrente.
- 3.2.2.4 - O sistema deve apresentar os seguintes campos opcionais: 
	- Número de Dependentes (inteiro ≥ 0, padrão 0);
	- E-mail institucional. 
- 3.2.2.5 - O sistema deve garantir a unicidade do CPF, impedindo que dois funcionários (ativos ou inativos) compartilhem o mesmo documento. 
- 3.2.2.6 - O Salário Base registrado deve ser maior ou igual ao salário mínimo nacional vigente.
- 3.2.2.7 - O sistema deve rejeitar inserções de salário base abaixo do piso, exibindo uma mensagem de alerta com o valor mínimo aceitável.
- 3.2.2.8 - O sistema deve manter um histórico contínuo de alterações salariais, registrando o salário anterior, o novo salário, a data de alteração e o usuário responsável pela mudança.
- 3.2.2.9 - O sistema deve identificar o mês de admissão para aplicar a proporcionalidade de dias trabalhados na primeira folha processada.
- 3.2.2.10 - A ação de inativação de um funcionário deve exigir o registro obrigatório da data de desligamento.
- 3.2.2.11 - O sistema deve exibir no perfil do funcionário o cálculo automatizado do seu tempo de empresa em anos e meses completos.

### 3.2.3 Cadastro de Rubricas
Todos os seguintes requisitos se aplicam à gestão e categorização de rubricas:
- 3.2.3.1 - O sistema deve manter uma tabela de rubricas classificando cada entrada como provento ou desconto e como fixa ou variável.
- 3.2.3.2 - Cada rubrica deve conter as seguintes informações: 
	- Código numérico único de 3 dígitos;
	- Natureza (Provento/Desconto);
	- Tipo (Fixo/Variável);
	- Incidência (INSS, FGTS, IRRF).
- 3.2.3.3 - O sistema deve trazer as rubricas padrão pré-cadastradas conforme o Apêndice 4.1.
- 3.2.3.4 - O sistema deve bloquear a edição de código, natureza, incidência ou a exclusão das rubricas obrigatórias de códigos 001 a 005.
- 3.2.3.5 - O sistema deve permitir que o Administrador exclua as rubricas padrão de 006 a 008 e 101 a 106, contanto que não estejam vinculadas a folhas já processadas.
- 3.2.3.6 - O sistema deve permitir que o Administrador cadastre rubricas personalizadas (código ≥ 500) para compor benefícios ou descontos específicos.

### 3.2.4 Cadastro de Usuários do Sistema
Todos os seguintes requisitos se aplicam ao acesso e perfis dos operadores do sistema:
- 3.2.4.1 - O sistema deve permitir o cadastro de contas de usuários para acesso à aplicação.
- 3.2.4.2 - O sistema deve dispor do perfil "Administrador", garantindo acesso total às configurações de parâmetros, além de permitir adicionar ou remover analistas de RH.
- 3.2.4.3 - O sistema deve dispor do perfil "Analista de RH", garantindo acesso operacional limitado a cadastros, lançamentos, processamento de folhas e emissão de holerites.
- 3.2.4.4 - O sistema deve garantir que, a todo momento, exista no mínimo um usuário com o perfil Administrador ativo.
- 3.2.4.5 - O sistema não deve armazenar, nem exibir, senhas em formato de texto plano em nenhuma circunstância.

### 3.2.5 Calendário e Dias Úteis
Todos os seguintes requisitos se aplicam às regras de apuração de dias do mês de competência:
- 3.2.5.1 - O sistema deve calcular os dias úteis mensais considerando o intervalo de segunda a sexta-feira, excluindo sábados e domingos.
- 3.2.5.2 - O sistema não deve descontar feriados nacionais automaticamente.
- 3.2.5.3 - O sistema deve utilizar a totalidade de dias úteis apurados como divisor para obter o valor-dia nos cálculos de descontos de faltas.

### 3.2.6 Registro de Faltas Injustificadas
Todos os seguintes requisitos se aplicam ao processamento de abstenções não justificadas:
- 3.2.6.1 - O sistema deve permitir lançamentos de faltas em dias inteiros ou em frações de horas atrelados a um funcionário no mês ativo.
- 3.2.6.2 - O sistema deve calcular o desconto de faltas integrais multiplicando o valor diário pela quantidade de dias ausentes.
- 3.2.6.3 - O sistema deve calcular o desconto de faltas fracionadas multiplicando o Valor_Hora pela quantidade de horas ausentes.
- 3.2.6.4 - O sistema deve bloquear o lançamento de faltas que superem a quantidade de dias úteis apurados para o mês de competência.

### 3.2.7 Registro de Atrasos
Todos os seguintes requisitos se aplicam ao controle de atrasos no registro de ponto:
- 3.2.7.1 - O sistema deve permitir o lançamento de atrasos computados em minutos, vinculando-os a uma data do mês corrente.
- 3.2.7.2 - O sistema deve calcular o impacto financeiro do atraso multiplicando o Valor_Hora pela fração de minutos (Minutos_Atraso ÷ 60).
- 3.2.7.3 - O sistema deve acumular os lançamentos de atraso de um mesmo funcionário durante o mês, somando todos os minutos.
- 3.2.7.4 - O sistema deve aplicar a regra de tolerância da CLT e isentar de desconto atrasos limitados a 10 minutos diários, informando visualmente essa isenção.

### 3.2.8 Descanso Semanal Remunerado (DSR)
Todos os seguintes requisitos se aplicam às penalidades aplicadas ao DSR:
- 3.2.8.1 - O sistema deve remover o direito ao DSR da semana correspondente em que ocorrer um lançamento de falta injustificada.
- 3.2.8.2 - O sistema deve cruzar as datas das faltas cadastradas para definir automaticamente o total de DSRs perdidos no mês.
- 3.2.8.3 - O sistema deve calcular o valor da perda de DSR multiplicando o Valor_Dia pela quantidade de DSRs revogados.
- 3.2.8.4 - O sistema deve exibir o total descontado em rubrica própria e destacada no holerite (Código 104 — Desconto DSR).

### 3.2.9 Registro de Atestados Médicos
Todos os seguintes requisitos se aplicam à inserção de atestados para abono de ponto:
- 3.2.9.1 - O sistema deve permitir a inclusão de atestados apontando a data de início e fim, o tipo da justificativa e, de forma opcional, o código CID.
- 3.2.9.2 - O sistema deve processar o atestado de modo a impedir o desconto de falta nos dias amparados, assegurando também o direito ao DSR daquela semana.
- 3.2.9.3 - O sistema deve isentar o desconto por até 15 dias ininterruptos resguardados pelo atestado.
- 3.2.9.4 - O sistema deve emitir um aviso visual caso o atestado lançado supere o limite de 15 dias corridos consecutivos.
- 3.2.9.5 - O sistema deve prever a inserção opcional de um arquivo digital (PDF com até 5MB) anexo ao atestado.

### 3.2.10 Registro de Horas Extras
Todos os seguintes requisitos se aplicam ao lançamento e cálculo de tempo excedente trabalhado:
- 3.2.10.1 - O sistema deve permitir lançar as horas extras em decimais (ex.: 1,5), exigindo a data e a especificação do adicional (50% ou 100%).
- 3.2.10.2 - O sistema deve emitir alerta não impeditivo caso o montante de horas extras no mês ultrapasse o limite definido em lei (2h diárias vezes os dias úteis).
- 3.2.10.3 - O sistema deve realizar e expor instantaneamente o cálculo monetário das horas extras no momento do lançamento.

### 3.2.11 Salário Base e Proporcionalidade
Todos os seguintes requisitos se aplicam aos processos de transformação de proventos:
- 3.2.11.1 - O sistema deve aplicar o valor total do salário contratual caso o funcionário tenha prestado serviço o mês inteiro.
- 3.2.11.2 - O sistema deve calcular o salário em proporção aos dias úteis trabalhados caso o funcionário inicie ou seja desligado durante a fluência do mês.

### 3.2.12 Horas Extras — Processamento
Todos os seguintes requisitos se aplicam à consolidação das horas excedentes na folha:
- 3.2.12.1 - O sistema deve centralizar todos os registros de hora extra do mês e calcular a conversão monetária separada por faixa (50% e 100%).
- 3.2.12.2 - O sistema deve incorporar os valores de horas extras ao salário bruto, integrando a base calculável para descontos de INSS e encargos de FGTS.
- 3.2.12.3 - O sistema deve exibir no holerite as faixas de horas extras de forma dissociada, alocando a rubrica 002 para adicionais de 50% e a 003 para 100%.

### 3.2.13 Bônus e Gratificações
Todos os seguintes requisitos se aplicam ao cálculo de valores bonificados no mês:
- 3.2.13.1 - O sistema deve compilar os bônus incluídos nas exceções e agrupá-los na rubrica 004, somando o montante ao salário bruto mensal.
- 3.2.13.2 - O sistema deve notificar o usuário administrador caso seja detectada a concessão do bônus ao mesmo funcionário por três meses consecutivos.

### 3.2.14 Cesta Básica
Todos os seguintes requisitos se aplicam ao controle de concessão da cesta básica:
- 3.2.14.1 - O sistema deve aplicar o benefício da cesta básica de forma mensal usando o valor fixado nas configurações da empresa (rubrica 005), ignorando-o da base do INSS ou FGTS.
- 3.2.14.2 - O sistema deve garantir que o valor da cesta básica seja pago de forma íntegra a novos admitidos que entrarem no transcorrer do mês.

### 3.2.15 Participação nos Lucros e Resultados (PLR)
Todos os seguintes requisitos se aplicam à verba da PLR:
- 3.2.15.1 - O sistema deve isolar o valor variável da PLR, abstendo-se de utilizá-lo como base de incidência para férias, INSS, FGTS e 13º.
- 3.2.15.2 - O sistema deve gravar o montante bruto livre de impostos sobre a PLR, identificando o repasse no holerite pela rubrica 006.

### 3.2.16 Composição do Salário Bruto
- 3.2.16.1 - O sistema deve realizar a totalização das verbas, consolidando o Salário Bruto pela soma de todos os proventos que geram base de cálculo tributária.

### 3.2.17 Desconto de INSS
Todos os seguintes requisitos se aplicam às rotinas da contribuição previdenciária:
- 3.2.17.1 - O sistema deve calcular o montante de INSS cruzando o salário bruto (subtraído de rubricas isentas) com as faixas progressivas vigentes estipuladas por portaria.
- 3.2.17.2 - O sistema deve apresentar no demonstrativo do funcionário a quantia abatida, o salário que serviu como base e o reflexo percentual da alíquota alcançada.

### 3.2.18 Desconto de Faltas, Atrasos e DSR
Todos os seguintes requisitos se aplicam aos reflexos financeiros das ausências e atrasos:
- 3.2.18.1 - O sistema deve agrupar os valores descontáveis gerados pela frequência irregular do funcionário e retê-los do cálculo da folha mensal.
- 3.2.18.2 - O sistema deve fragmentar cada evento de ausência nas rubricas correspondentes em vez de sumarizar um desconto único no holerite.

### 3.2.19 Outros Descontos Variáveis
Todos os seguintes requisitos se aplicam a outras cobranças incidentes no mês:
- 3.2.19.1 - O sistema deve suportar as exclusões manuais feitas pelo departamento (ex.: adiantamento), solicitando valor, descrição e rubrica cabível.
- 3.2.19.2 - O sistema deve travar os descontos mensais caso a soma supere os ganhos apurados, barrando a confecção de salários líquidos negativos.

### 3.2.20 Composição do Salário Líquido
- 3.2.20.1 - O sistema deve fechar a equação contábil apontando o Salário Líquido como a diferença exata entre os proventos globais e o saldo de descontos gerais.

### 3.2.21 Encargos de FGTS
Todos os seguintes requisitos se aplicam às taxas pagas pela empresa relativas ao fundo de garantia:
- 3.2.21.1 - O sistema deve extrair 8% da base de recolhimento para definir o FGTS de competência do mês. A base engloba Salário Base, Bônus com incidência e Horas Extras.
- 3.2.21.2 - O sistema não deve deduzir o encargo de FGTS em hipótese alguma do holerite repassado ao trabalhador.
- 3.2.21.3 - O sistema deve provisionar e acumular as taxas calculadas todo mês para compor os painéis de auditoria financeira da empresa.

### 3.2.22 Provisão de 13º Salário
Todos os seguintes requisitos se aplicam ao acúmulo da gratificação natalina:
- 3.2.22.1 - O sistema deve calcular virtualmente a quantia proporcional devida do 13º com base no mês finalizado.
- 3.2.22.2 - O sistema deve acumular essa fatia mês a mês desde o ingresso do trabalhador, zerando o medidor na entrada de um novo ano-calendário.
- 3.2.22.3 - O sistema deve projetar em relatório interno da empresa o peso unitário do mês junto ao montante acumulado até o momento.

### 3.2.23 Provisão de Férias e Adicional
Todos os seguintes requisitos se aplicam à estimativa contábil de férias:
- 3.2.23.1 - O sistema deve fatiar o acerto de férias e englobar no cômputo o peso extra de ⅓ garantido legalmente.
- 3.2.23.2 - O sistema deve somar gradativamente os valores provisionados mantendo o ciclo fechado em 12 meses a contar da data em que o funcionário for contratado.

### 3.2.24 Relatório de Encargos e Provisões
Todos os seguintes requisitos se aplicam ao faturamento global gerado pela folha da empresa:
- 3.2.24.1 - O sistema deve produzir, no encerramento da competência, um mapa destrinchando mês a mês a carga de FGTS, as parcelas do 13º e a reserva de Férias para todos os ativos.
- 3.2.24.2 - O sistema deve somar integralmente a projeção geral da empresa e exibir a totalidade acumulada desses encargos.
- 3.2.24.3 - O sistema deve permitir que o gestor transforme os dados em um arquivo PDF final.

### 3.2.25 Emissão de Holerite Individual
Todos os seguintes requisitos se aplicam à documentação expedida em face do colaborador:
- 3.2.25.1 - O sistema deve estruturar o layout em três seções primárias contemplando: Cabeçalho com dados contratuais e sigilo de CPF; Tabela detalhada de pagamentos/retenções; Totais cruzados acrescidos do campo para assinatura.
- 3.2.25.2 - O sistema deve converter obrigatoriamente o recibo na arquitetura PDF/A e intitulá-lo sob a máscara de arquivo `HOLERITE[CPF-sem-formatação][AAAAMM].pdf`.
- 3.2.25.3 - O sistema deve franquear o acesso irrestrito para emissões tardias de meses já finalizados sem promover ou autorizar modificação na memória do documento.

### 3.2.26 Resumo da Folha de Pagamento
Todos os seguintes requisitos se aplicam ao documento espelho das finanças processadas:
- 3.2.26.1 - O sistema deve elaborar a tabela de síntese da folha listando nome, função e a trindade financeira (bruto, desconto e líquido).
- 3.2.26.2 - O sistema deve somar e grafar as colunas exibindo os gastos universais e preparar o documento para formato viável ao cruzamento com escritórios contábeis.

### 3.2.27 Integração para Remessa Bancária
Todos os seguintes requisitos se aplicam à listagem demandada por gerências de contas corporativas:
- 3.2.27.1 - O sistema deve agrupar as informações bancárias (CPF, Banco, Agência, Conta e montante a depositar) e exportar as linhas tabeladas em PDF utilizando criptografia de caracteres UTF-8.

### 3.2.28 Histórico e Consulta de Processamentos
Todos os seguintes requisitos se aplicam aos dados armazenados sobre as antigas transações do sistema:
- 3.2.28.1 - O sistema deve eternizar os ciclos mensais no banco de dados, ignorando a possibilidade de eliminação automática por limite de tempo.
- 3.2.28.2 - O sistema deve encapsular o retrato exato do trabalhador no instante em que o ciclo se encerrar para impedir que aumentos futuros distorçam as emissões antigas do documento.
- 3.2.28.3 - O sistema deve filtrar o acesso dos meses arquivados habilitando a busca unificada por intermédio de datas (início e fim) ou focada num colaborador determinado.

### 3.2.29 Histórico de Ações do Administrador 
Todos os seguintes requisitos se aplicam aos processos rastreáveis pela plataforma:
- 3.2.29.1 - O sistema deve conter um histórico contendo todas as ações feitas pelo administrador.
- 3.2.29.2 - O acesso ao histórico de ações do administrador é deve ser permitido apenas ao administrador.
- 3.2.29.3 - O histórico de ações do administrador deve informar as seguintes informações:
	- Alterações feitas nos cadastros dos usuários do sistema, ex: remoção de um antigo usuário ou a adição de um novo usuário;
	- Alterações nas informações críticas da empresa, ex: mudança no endereço da empresa.

### 3.2.30 Quadro Consolidado de Regras de Negócio (RN)
- 3.2.30.1 - O sistema deve manter e respeitar o quadro consolidado com todas as regras de negócio derivadas da legislação para viabilizar rastreabilidade e testes.

## 3.3 Requisitos não Funcionais

### 3.3.1 Desempenho
Todos os seguintes requisitos se aplicam à fluidez, capacidade técnica e eficiência temporal do software, baseando-se no hardware de referência (Processador dual-core 2,0 GHz, 4 GB RAM, Windows 10 e disco de leitura):
- 3.3.1.1 - O sistema deve tracionar os cálculos processando uma base de até 50 funcionários sob o limiar temporal máximo de 15 segundos.
- 3.3.1.2 - O sistema deve ampliar a janela limite, aceitando processar planilhas preenchidas por até 200 colaboradores em no máximo 45 segundos.
- 3.3.1.3 - O sistema deve entregar a listagem na tela após interações de consulta na margem fixa de 3 segundos (suportando teto de 500 registros ativos/inativos).
- 3.3.1.4 - O sistema deve finalizar o tempo total da operação de fechamento e conversão isolada de um recibo PDF em até 5 segundos de espera na máquina de referência.
- 3.3.1.5 - O sistema deve inicializar na retaguarda o framework principal e acender na tela o dashboard em um teto restrito a 8 segundos.
- 3.3.1.6 - O sistema deve gerenciar uma arquitetura interna propícia para reter simultaneamente 500 perfis salvos e preservar um backlog com até 60 exercícios contábeis.

### 3.3.2 Segurança 
Todos os seguintes requisitos se aplicam à política de acesso e tratamento da sensibilidade das informações conforme a Lei Geral de Proteção de Dados:
- 3.3.2.1 - O sistema deve blindar a entrada demandando aprovação de senhas em todas as sessões e distribuir funções limitadas a partir da estirpe do perfil do operador logado.
- 3.3.2.2 - O sistema deve derrubar a sessão atual forçando novo reingresso em casos atípicos onde o ambiente permanecer paralisado por meia hora ininterrupta.
- 3.3.2.3 - O sistema deve aplicar o congelamento transitório impedindo a conta de submeter chaves por 5 minutos assim que a terceira rejeição seguida for detectada.
- 3.3.2.4 - O sistema deve cifrar integralmente o cofre das senhas usando tecnologia bcrypt aplicada sob nível modular 10 (fator de custo).
- 3.3.2.5 - O sistema deve repelir botões ou e-mails dedicados à recuperação da chave atual, restando o "Reset" do administrador como o único protocolo alternativo.
- 3.3.2.6 - O sistema deve proibir na inclusão, ou nas atualizações periódicas, registros compostos por sequências inferiores a 8 toques, forçando a mescla do repertório contendo letra (maiúscula/minúscula) e números.
- 3.3.2.7 - O sistema deve estampar os documentos expostos encriptando o miolo do registro de Cadastro Físico (`***.***.XXX-XX`).
- 3.3.2.8 - O sistema deve mapear nos trilhos de rastreamento toda requisição de exportação originada da base de finanças alinhada ao relógio (data/hora).
- 3.3.2.9 - O sistema deve trabalhar encapsulado retendo no perímetro as tabelas sem escoar ou repassar tráfego confidencial do domínio para provedores web espelhados ou bases externas.
- 3.3.2.10 - O sistema deve resguardar as pontes de comunicação do arquivo interno, desautorizando o trâmite contendo usuários atrelados às engrenagens abertas em blocos literais.
- 3.3.2.11 - O sistema deve criar canal de rastreio à parte do arquivo rotineiro em formato "apenas inserção", cimentando de modo imutável falhas contínuas de portas e acessos maliciosos na plataforma.

### 3.3.3 Confiabilidade 
Todos os seguintes requisitos se aplicam à robustez das regras, contorno dos imprevistos infraestruturais e integridade contábil das somatórias geradas:
- 3.3.3.1 - O sistema deve sustentar todo encadeamento matemático com precisão travada obrigatoriamente em duas dezenas para centavos.
- 3.3.3.2 - O sistema deve promover consistência assegurando cálculos de resultado idempotente caso não haja mudança manual de parâmetros da folha anterior ao novo fechamento.
- 3.3.3.3 - O sistema deve encapsular o giro de fechamento sobre trilhos da arquitetura transacional (ACID), estornando apenas o lote afetado caso trave a base de determinado trabalhador para não corromper o montante fechado de forma devida.
- 3.3.3.4 - O sistema deve realizar uma verificação (scan) em todo o rol ativo paralisando os fechamentos em caso de anomalias detectadas no momento de partida.
- 3.3.3.5 - O sistema deve empacotar via auto-função e gravar um clone espelho assinado da base terminada na máscara pré-configurada ( `SFPCLT_BACKUP_[AAAAMM]-[YYYYMMDD_HHMMSS]` ) atrelado sempre de forma compulsória no segundo que precede o veredito final do mês em questão.
- 3.3.3.6 - O sistema deve disparar alerta se o diretório configurado pelo backup não for localizado ou não exibir privilégios para liberação da escrita.
- 3.3.3.7 - O sistema deve gerenciar ausências vitais emitindo pop-up de aguardo atrelado a 30 segundos de pausa assim que as conexões subjacentes que mantém a mesa rodando evaporarem, forçando o restabelecimento imediato em vez do encerramento forçado da máquina central.
- 3.3.3.8 - O sistema deve identificar e dialogar abertamente quando a impressão local dos PDFs estagnar, ou os clusters dedicados na máquina acusarem exaustão de armazenamento de discos sem derrubar ou corromper subitamente as abas da plataforma ativa.
- 3.3.3.9 - O sistema deve traçar resumos criptográficos na vertente da cadeia SHA-256 e agrupar com o saldo das quantias elaboradas ao selar as auditorias.
- 3.3.3.10 - O sistema deve precaver as manutenções, perdas ou encerramentos repentinos preservando a gaveta dos apontamentos mensais, resgatando a inserção em andamento do usuário tão logo o próximo retorno seja garantido no software.

### 3.3.4 Usabilidade 
Todos os seguintes requisitos se aplicam aos padrões de interação simplificada aos usuários leigos e fluxos de interfaces contínuas:
- 3.3.4.1 - O sistema deve proporcionar ambientação simples e indutiva propiciando a feitura veloz para operadores sem extensa curva teórica com teto referencial delimitado nas equipes avaliativas contendo 10 perfis a gerar.
- 3.3.4.2 - O sistema deve transcrever o teor das irregularidades e dos bugs contornados num idioma acessível dispensando blocos alfanuméricos provindos nas cadeias originais do Java (Exceptions).
- 3.3.4.3 - O sistema deve apresentar barricada secundária requisitando chancela por botão de "Confirmar" ou "Cancelar" nos gatilhos imutáveis como supressão e exclusão direta nas engrenagens e reset imposto de conta por autoridade do domínio master.
- 3.3.4.4 - O sistema deve garantir flexibilidade total amparando trocas do curso visual exclusivamente nas chaves contíguas originadas no periférico sem restrição compulsória às delimitações do mouse guiando-se pelas premissas tabulares contínuas do formulário nativo.
- 3.3.4.5 - O sistema deve sincronizar matrizes padronizadas nas abas distribuídas.
- 3.3.4.6 - O sistema deve estampar as credenciais que ditam a seção contínua atrelando o calendário vigente nos cabeçalhos universais ou alocações restritas nos rodapés.

### 3.3.5 Disponibilidade 
Todos os seguintes requisitos se aplicam à resiliência nos ciclos empresariais:
- 3.3.5.1 - O sistema deve estar liberado e sem engasgos nas rotinas do expediente comum.
- 3.3.5.2 - O sistema deve restringir as revisões obrigatórias em programações alocadas exclusivamente na zona desocupada com antecedência obrigatória afixada em 24h para os ativos.
- 3.3.5.3 - O sistema deve estampar aviso indicativo caso os dados de conexão subjacentes se ausentem do radar provisório impossibilitando arranques manuais.

### 3.3.6 Manutenibilidade 
Todos os seguintes requisitos se aplicam às boas práticas de arquitetura exigidas na construção técnica:
- 3.3.6.1 - O sistema deve separar as responsabilidades programáticas nos blocos obrigatórios conhecidos como Apresentação (View), Negócio (Service) e Persistência (Repository/DAO).
- 3.3.6.2 - O sistema deve abstrair bases sensíveis ao calendário governamental (salário mínimo, recolhimentos do FGTS, impostos na margem ou percentual da tabela noturna/extra), viabilizando sua atualização anual através da operação direta sem dependências ou obrigatoriedade contínua ligada à classe técnica recompilada.
- 3.3.6.3 - O sistema deve englobar pilhas operacionais prontas com testes integráveis baseados nas ferramentas (Gradle/Maven).
- 3.3.6.4 - O sistema deve organizar a confecção orgânica nos históricos mantendo retenção dos registros pelo lapso fechado nos últimos trinta dias sobre o formato nomeado de `sfpclt-YYYY-MM-DD.log`.
- 3.3.6.5 - O sistema deve exibir as falhas primárias nas mensagens agrupadas com as trilhas contíguas rastreadas via classe base sob subscrições apontadas (StackTrace).
- 3.3.6.6 - O sistema deve segmentar suas partes nas rotinas (processamentos e auditoria) sobre domínios desacoplados e apartados funcionalmente.
- 3.3.6.7 - O sistema deve englobar manuais restritos detalhados descrevendo sua arquitetura distribuída junto com pontes contíguas na sua instalação final.

### 3.3.7 Portabilidade 
Todos os seguintes requisitos se aplicam à pluralidade e conformação com infraestruturas em ambientes heterogêneos de mercado:
- 3.3.7.1 - O sistema deve ancorar as execuções suportadas com a dependência prévia atrelada ao núcleo JVM em ramificação base 11 ou graus sucessivos para Windows 10/11, Ubuntu Linux 20/22.04 e ramificações base Intel x64 ou série M do macOS12+.
- 3.3.7.2 - O sistema deve exigir prerrogativas ou trâmites focados contendo os papéis administrativos exclusivamente no rito de instalação inaugural abstendo as cobranças na sua condução cotidiana e usual nas empresas normais.
- 3.3.7.3 - O sistema deve acoplar a matriz de compilações atrelando dependências nos empacotadores dedicados na linha Jpackage sem obrigar download periférico via pacotes avulsos nos formatos suportados `.msi/.exe`, pacotes nativos do debian/redhat ou pacotes maçã `.dmg`.
- 3.3.7.4 - O sistema deve possuir todo repertório redacional ajustado nos dicionários normatizados para as cadeias do idioma português de matiz brasileiro (pt-BR).
- 3.3.7.5 - O sistema deve conferir garantia no resultado gráfico compatibilizando espelhos nos suítes LibreOffice/Excel ou provedores Adobe DC.

### 3.3.8 Conformidade Legal e Regulatória
Todos os seguintes requisitos se aplicam ao embasamento de regras judiciais no processamento interno:
- 3.3.8.1 - O sistema deve transcrever e seguir sem oscilação ou divergência todas as taxas e margens aprovadas na competência real que transita o evento abstendo projeções baseadas no mês da execução local.
- 3.3.8.2 - O sistema deve manter intocada toda e qualquer alavanca de reajuste nas apurações saldadas preteritamente com chave virtual ativada, bloqueando manipulações sob balanças contábeis retroativas.
- 3.3.8.3 - O sistema deve estampar no miolo processual dos contracheques originais expedidos por funcionários ativos, estritamente e na totalidade, todos os regimentos dispostos formalmente no Artigo 464 chancelado via Legislação Central de Trabalho do país vigente (CLT).
- 3.3.8.4 - O sistema deve eternizar os repositórios finalizados e lacrados do processamento da matriz pelo curso limite travado em quinquênio visando conformidade irrestrita.
- 3.3.8.5 - O sistema deve possibilitar as readequações provindas via legislação nacional publicizada na margem contínua transcorrida por cinco dias limitados desde sua efetiva ratificação pelo corpo de ofício da união.
- 3.3.8.6 - O sistema deve apontar na grade da engrenagem do encerramento um sinalizador inconfundível apontando a tabela mestre escolhida na formulação total da remuneração atual de folha.

## 3.4 Requisitos de Dados

### 3.4.1 Modelo de Dados
Todos os seguintes requisitos se aplicam ao conjunto e arquitetura transacional que salva o estado da plataforma:
- 3.4.1.1 - O sistema deve englobar motores baseados no rito relacional (MySQL/MariaDB) que amparam blocos textuais extensos para estocar de forma perpétua as instâncias primárias.
- 3.4.1.2 - O sistema deve estruturar a malha definindo domínios restritos à matriz de Pessoal ("Funcionários").
- 3.4.1.3 - O sistema deve dispor das referências contratuais atreladas em tabela atípica de ("Salários") compondo cronologias isoladas sob versão atrelada à subidas no cargo.
- 3.4.1.4 - O sistema deve prever alocações rotativas destinadas na grade das incidências das métricas apontadas mensalmente via tabela nomeada em ("Lançamentos").
- 3.4.1.5 - O sistema deve reservar os reflexos imutáveis mensais atrelados ao final em depósitos sob o nome ("Holerites").
- 3.4.1.6 - O sistema deve reter a governança contendo todas as alocações da entrada dos credenciados junto dos papéis concedidos retidos nas abas atreladas a ("Usuários").

### 3.4.2 Integridade e Segurança de Dados
Todos os seguintes requisitos se aplicam à sustentabilidade primária visando proteção sob ataque contínuo e danos ao arquivo físico:
- 3.4.2.1 - O sistema deve estipular regras no domínio de persistência coibindo falhas nos registros (chaves estrangeiras estritas para não fragmentar as entidades filhas ou perdas de arquivos sem encadeamento retroativo associado).
- 3.4.2.2 - O sistema deve agrupar protocolos paralelos executáveis engajando coletas diárias copiando as estruturas de segurança para precaver esvaziamento total.
- 3.4.2.3 - O sistema deve criptografar os conteúdos expostos a sigilo e reserva de lei, disfarçando de modo integral bases indexáveis que identificam o alvo salarial isolado assim como seu CPF nativo gravado perante o SGBD mantenedor do banco relacional.

# 4. Apêndices 