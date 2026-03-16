# Introdução

## 1.1 Propósito do Documento de Requisitos (motivações, público-alvo,...)
O sistema automatiza o cálculo e a emissão de uma folha de pagamento de funcionários. A folha de pagamento é baseada no contrato CLT, onde temos condições e requisitos mais claros e determinados para projetar. Outros regimes, como não-CLT (PJ, estagiários) e alterações contratuais do CLT estão fora do escopo do sistema. 

## 1.2 Escopo do produto (oq faz, oq não faz e desc a aplicação) 
Para a primeira fase do projeto, o sistema contará com: 
* **Histórico das Folhas de Pagamento:** Fornecerá uma interface para visualizar as antigas folhas de pagamento referente aos meses anteriores.
* **Horas Computadas:** O tempo de trabalho máximo de qualquer tipo de funcionário será de oito (8) horas diárias para cinco (5) dias na semana - segunda, terça, quarta, quinta e sexta-feira. 
* **Gestão de Cadastros:** Permitirá a inclusão, leitura e atualização de dados de funcionários, mantendo obrigatoriamente um histórico versionado de alterações salariais. A gestão também conta com a entrada e gestão de planilhas.
* **Lançamentos de exceção (mensais):** Fornecerá uma interfaces para o lançamento de eventos (variáveis) que impactam o mês corrente, e.g., horas extras, bônus, faltas, atrasos, atestados diversos. A entrada de exceção deverá ser feita de forma mensal. Lançamentos de excessões não alteram meses já fechados, implica um histórico auditável.
* **Processamento Lógico:** Após o processamento, executará o cálculo matemático de transformação do salário bruto em salário líquido, aplicando as equações de soma de proventos e subtração de descontos registrados para o mês corrente.
* **Geração de Artefatos:** Compilará os dados processados para a emissão visual e exportável do demonstrativo de pagamento individual (holerite).
* **Gestão de Rubricas Fixas e Variáveis:** Calculará o valor da diária de trabalho ("Valor dia") com base no salário contratual e processará a concessão de benefícios fixos (ex: Cesta Básica) e variáveis (ex: Participação nos Lucros e Resultados - PRL).
* **Motor de Provisões e Encargos:** Diferente de um sistema básico, este MVP já englobará o cálculo automatizado de provisões trabalhistas essenciais exibidas no fechamento mensal, especificamente a proporcionalidade ou valor integral de **13º Salário**, **Férias + ⅓** e o recolhimento do **FGTS (com a multa de 40% embutida ou provisionada)**.
* **Geração de saídas:** A emissão do demonstrativo de pagamento individual, e.g., Holerite (contra-cheque). Permite consultas referentes a meses anteriores já fechados. As consultas abrangem um período mensal fixo. 
* **O que não será feito agora:** Por enquanto, não haverá implementação de exceção (mensais) como: fonte de impostos que não constam no fluxo atual aprovado (como tabelas progressivas de IRRF e INSS), benefícios variáveis (e.g, coparticipação em plano de saúde) e opções de relatório para cada setor da "empresa", não realizará pagamentos para regimes não-CLT (PJ, estagiários).

## 1.3 - Definições, acrônimos e abreviações
Para garantir a compreensão uniforme deste documento por leitores de diferentes áreas (técnicas e de negócios), os seguintes termos são definidos:

* **CLT:** Consolidação das Leis do Trabalho. Legislação brasileira que regulamenta as relações de trabalho e dita as regras de remuneração aplicáveis a este sistema.
* **FGTS:** Fundo de Garantia do Tempo de Serviço.
* **PRL:** Participação nos Lucros e Resultados (frequentemente tratada como PLR na legislação).
* **13º Salário:** Gratificação natalina compulsória.
* **Rubrica:** Código ou categoria contábil que identifica a natureza de um valor na folha de pagamento (ex: Rubrica de Salário Base, Rubrica de Hora Extra).
* **Provento:** Qualquer valor financeiro de natureza creditícia que é somado ao salário do colaborador (ex: bônus, comissões).
* **Desconto:** Qualquer valor financeiro de natureza debitaria que é subtraído do salário do colaborador (ex: faltas, atrasos).
* **Holerite / Contra-cheque:** O demonstrativo impresso ou digital que detalha todas as rubricas de proventos e descontos que compuseram o salário líquido do colaborador em uma competência.

## 1.4 - Referências
* As informações referentes ao regime CLT foram retiradas do documento: Consolidação das Leis do Trabalho - CLT e normas correlatas. Originária da Constituição da República Federatica do Brasil

## 1.5 - Estruturação do Documento de Requisitos (estrutura/organização)




# 2 Descrição Geral

## 2.1 Perspectiva do Produto  Relacionamento: sistema, usuário, hardware, software, comunicação.
* O sistema é uma aplicação Web Standalone voltada para o departamento de Recursos Humanos (RH) ou contabilidade.
- Interface: Navegador web (Chrome, Firefox, Edge).
- Hardware: Servidor em nuvem para processamento dos cálculos e armazenamento de banco de dados.
- Comunicação: Protocolo HTTPS para garantir a segurança dos dados sensíveis (salários e CPFs).]

## 2.2 Funcionalidades do Produto
* As principais funções que o software deve executar são:
- Cálculo Automático: Processar Salário Bruto -> Líquido.
- Gestão de Ponto/Exceções: Importação de horas extras, faltas e atrasos.
- Provisões Trabalhistas: Cálculo mensal de reserva para 13º e Férias.
- Emissão de Documentos: Geração de PDF do Holerite.
- Auditoria: Log de alterações salariais para conformidade legal.

## 2.3 Características do Usuário
* Analista de RH/Departamento Pessoal: Usuário principal. Possui conhecimento das regras da CLT, mas busca agilidade no cálculo manual.
* Administrador do Sistema: Responsável por cadastrar usuários e gerenciar permissões de acesso.

## 2.4 Restrições Gerais  Limitações de hardware, considerações sobre segurança, ...
* Legislação: O sistema deve seguir estritamente a tabela vigente da CLT (2024/2025/2026).
* Segurança: Dados salariais são protegidos pela LGPD (Lei Geral de Proteção de Dados). O acesso deve ser restrito por login e senha.
* Conformidade: O sistema não permite a alteração de folhas de meses já encerrados para garantir a integridade contábil.

## 2.5 Suposições e Dependências - Máquina específica, sistema operacional, ...
* Supõe-se que o usuário possua acesso estável à internet.
* O sistema depende da alimentação mensal das "exceções" (faltas/extras) antes do fechamento da folha.

# 3 Requisitos Específicos

## 3.1 Requisitos de Interface com o Usuário (GUI)
Esta seção detalha os requisitos da interface gráfica do sistema desktop, organizada por tela ou módulo de interação. Cada requisito deve ser verificável por inspeção ou teste funcional da interface.
### 3.1.1 Padrões Gerais da Interface
Os seguintes requisitos se aplicam a todas as telas do sistema:
- 3.1.1.1 - O sistema deve possuir uma interface gráfica.

* 3.1.1.1 - Todas as telas devem exibir, no cabeçalho, o nome do sistema : "SFP-CLT".

* 3.1.1.1 - Todas as telas devem exibir, no cabeçalho, o nome do usuário.

* 3.1.1.1 - Todas as telas devem exibir, no cabeçalho, o perfil de acesso ativo.

* 3.1.1.1 - A paleta de cores, tipografia e espaçamento devem ser padronizados e consistentes em todas as telas da aplicação, seguindo um guia de estilo definido pela equipe. ******Definir o guia de estilo - paleta de cores, tipografia e espaçamento

* 3.1.1.1 - Todos os formulários devem indicar visualmente os campos obrigatórios, utilizando marcador de asterisco ( * ) em vermelho ao lado do rótulo.

* 3.1.1.1 - O sistema deve exibir mensagem de confirmação (diálogo modal com opções "Confirmar" e "Cancelar") antes de executar qualquer operação destrutiva ou irreversível, como fechamento de folha, exclusão de cadastro ou alteração de salário.

* 3.1.1.1 - O sistema deve exibir mensagens de erro em linguagem clara e objetiva, sem códigos de exceção Java expostos ao usuário.

* 3.1.1.1 - Campos monetários devem ser formatados automaticamente com separador de milhar (ponto) e duas casas decimais (ex.: R$ 3.500,00) ao perder o foco (evento onFocusLost).

* 3.1.1.1 - Campos de CPF devem aplicar máscara de entrada no formato 000.000.000-00 em tempo real durante a digitação.

* 3.1.1.1 - Campos de data devem utilizar máscara DD/MM/AAAA e validar se a data informada é uma data calendário válida.

* 3.1.1.1 - O sistema deve manter a resolução mínima de tela de 1280 x 720 pixels como referência de layout, garantindo que todos os elementos sejam exibidos sem sobreposição ou truncamento.

- 3.1.1.2 - A interface deve permitir acesso às funcionalidades do sistema.

### 3.1.2 Tela de Login e Autenticação
- 3.1.1.1 - O sistema deve apresentar uma tela de login como tela inicial obrigatória, bloqueando o acesso a qualquer outra funcionalidade antes da autenticação bem-sucedida.

- 3.1.1.1 - A tela de login deve conter o campo "Usuário" contendo:
	- 3.1.1.1 - O campo deve ser em formato de texto.
	- 3.1.1.1 - O campo deve restringir para no máximo 50 caracteres.
	- 3.1.1.1 - O campo de ser de preenchimento obrigatório.

- 3.1.1.1 - A tela de login deve conter o campo "Senha" contendo:
	- 3.1.1.1 - O campo deve ser em formato de texto.
	- 3.1.1.1 - O campo deve ser ocultado com asteriscos.
	- 3.1.1.1 - O campo deve ser ed preenchimento obrigatório.

- 3.1.1.1 - A tela de login deve exibir o logotipo ou nome do sistema.

- 3.1.1.1 - A tela de login deve exibir a versão atual do software.

- 3.1.1.1 - Após 3 (três) tentativas consecutivas de login inválidas com o mesmo nome de usuário, o sistema deve bloquear temporariamente aquele usuário por 5 (cinco) minutos, exibindo mensagem informativa com o tempo restante de bloqueio.

- 3.1.1.1 - O sistema deve registrar em log de segurança toda tentativa de acesso (bem-sucedida ou não). O log deve conter: nome de usuário informado, data, hora e resultado (sucesso/falha).
	- 3.1.1.1 - Nome de usuário.
	- 3.1.1.1 - Data de entrada.
	- 3.1.1.1 - hora de entrada.
	- 3.1.1.1 - Informar se o acesso foi bem sucedido ou não.

- 3.1.1.1 - O campo de senha não deve permitir copiar o conteúdo via atalho de teclado (Ctrl+C) ou menu de contexto.

- 3.1.1.1 - A sessão do usuário deve ser encerrada automaticamente após 30 minutos de inatividade, redirecionando para a tela de login com mensagem explicativa.

- 3.1.1.1 - O sistema deve oferecer opção "Sair" acessível em todas as telas para encerramento manual da sessão com segurança.

### 3.1.3 Menu Principal e Navegação
- 3.1.1.1 - Após autenticação bem-sucedida, o sistema deve exibir um menu principal com acesso a todos os módulos habilitados para o perfil do usuário logado.

- 3.1.1.1 - O menu principal deve conter, no mínimo, as seguintes entradas: 
	- 3.1.1.1 - Cadastros; 
	- 3.1.1.1 - Lançamento de Excessão; 
	- 3.1.1.1 - Folha de Pagamento do mês atual; 
	- 3.1.1.1 - Histórico; 
	- 3.1.1.1 - Sair.

- 3.1.1.1 - Itens de menu restritos ao perfil Administrador devem desabilitados para usuários do perfil Analista de RH.

- 3.1.1.1 - O menu deve apresentar indicador visual do módulo atualmente ativo (item selecionado destacado).

- 3.1.1.1 - O sistema deve exibir no dashboard, de forma resumida: 
	- 3.1.1.1 - O mês de competência corrente, 
	- 3.1.1.1 - A quantidade de funcionários ativos cadastrados 
	- 3.1.1.1 - Status da folha do mês corrente ("Aberta" ou "Fechada").
	
### 3.1.4 Tela de Cadastro de Funcionários
- 3.1.1.1 - O sistema deve apresentar tela de cadastro de funcionários com os seguintes campos obrigatórios: Nome completo (texto, máx. 150 caracteres), CPF (máscara 000.000.000-00, único no sistema), Data de Nascimento (DD/MM/AAAA), Data de Admissão (DD/MM/AAAA), Cargo (texto, máx. 100 caracteres) e Salário Base (valor monetário, mínimo R$ 1.518,00 conforme piso CLT 2025).

- 3.1.1.1 - O sistema deve apresentar os seguintes campos opcionais:  Número de Dependentes (inteiro ≥ 0), Banco e Conta para depósito salarial.

- 3.1.1.1 - O campo CPF deve ser validado pelo algoritmo de dígitos verificadores da Receita Federal. O sistema deve rejeitar CPFs com todos os dígitos iguais (ex.: 111.111.111-11).

- 3.1.1.1 - O sistema deve impedir o cadastro de dois funcionários com o mesmo CPF, exibindo mensagem de erro específica e mantendo os dados no formulário para correção.

- 3.1.1.1 - A tela de listagem de funcionários deve apresentar tabela com colunas: Nome, CPF (parcialmente ocultado: ***.***.XXX-XX), Cargo, Salário Base e Status (Ativo/Inativo).

- 3.1.1.1 - A listagem deve permitir busca por Nome ou CPF.

- 3.1.1.1 - O sistema deve permitir inativar um funcionário (demissão), registrando a data de desligamento. 

- 3.1.1.1 - Funcionários inativos não devem participar do processamento da folha de meses posteriores à data de desligamento.

- 3.1.1.1 - O sistema não deve permitir a exclusão permanente de registros de funcionários, apenas a inativação, para preservar o histórico de folhas passadas.

### 3.1.5 Tela de Cadastro da Empresa
- 3.1.1.1 - O sistema deve apresentar tela de configuração dos dados da empresa com os seguintes campos obrigatórios: Razão Social (texto), CNPJ (máscara 00.000.000/0000-00), Endereço completo e Responsável Legal (nome).

- 3.1.1.1 - O CNPJ deve ser validado pelo algoritmo de dígitos verificadores da Receita Federal.

- 3.1.1.1 - Alterações nos parâmetros fiscais da empresa devem exigir confirmação com diálogo modal e devem ser registradas em log de auditoria com o usuário e data da alteração.

### 3.1.6 Tela de Lançamentos de Exceção Mensais
- 3.1.1.1 - O sistema deve apresentar tela de lançamentos mensais, acessível somente quando a folha do mês corrente estiver com status "Aberta".

- 3.1.1.1 - A tela deve exibir o mês e ano de competência corrente de forma destacada, impedindo ambiguidade sobre o período de referência dos lançamentos.

- 3.1.1.1 - O sistema deve permitir selecionar um funcionário ativo por meio de campo de busca antes de registrar qualquer lançamento.

- 3.1.1.1 - Para cada funcionário selecionado, o sistema deve permitir o registro dos seguintes tipos de evento:
	- 3.1.1.1 - Horas Extras: quantidade de horas (decimal, ex.: 2,5), tipo (50% ou 100%) e data de ocorrência. O sistema deve calcular e exibir o valor monetário correspondente em tempo real.
	- 3.1.1.1 - Faltas Injustificadas: quantidade de dias inteiros ou fração (em horas). O sistema deve calcular e exibir o desconto correspondente em tempo real.
	- 3.1.1.1 - Atrasos: quantidade de minutos e data de ocorrência. O sistema deve calcular o desconto proporcional em tempo real.
	- 3.1.1.1 - Atestado Médico: data de início, data de fim e arquivo digitalizado (PDF ou imagem, opcional). Atestados justificam ausências e neutralizam o desconto de falta.
	- 3.1.1.1 - Bônus / Gratificação: valor monetário livre e descrição textual (obrigatória, máx. 200 caracteres).

- 3.1.1.1 - O sistema deve impedir o lançamento de eventos em meses cuja folha já esteja com status "Fechada", exibindo mensagem explicativa ao usuário.

- 3.1.1.1 - O sistema deve permitir a edição e exclusão de lançamentos do mês corrente enquanto a folha estiver aberta. Após o fechamento, os lançamentos tornam-se somente leitura.

### 3.1.7 Tela de Processamento e Fechamento da Folha

- 3.1.1.1 - O sistema deve apresentar tela de processamento da folha com listagem de todos os funcionários ativos e seus respectivos status de processamento (Pendente, Calculado, Erro).

- 3.1.1.1 - O sistema deve disponibilizar botão "Processar Folha" que executa o cálculo para todos os funcionários ativos de forma sequencial ou paralela. O progresso deve ser exibido via barra de progresso com o nome do funcionário em processamento.

- 3.1.1.1 - Após o processamento, o sistema deve exibir resumo tabular contendo, para cada funcionário: Nome, Salário Bruto, Total de Proventos, Total de Descontos, Salário Líquido e Status.

- 3.1.1.1 - O sistema deve permitir re-processar a folha enquanto estiver com status "Aberta", sobrescrevendo os valores calculados anteriormente após confirmação do usuário.

- 3.1.1.1 - O sistema deve disponibilizar botão "Fechar Folha" que torna o mês de competência imutável. Antes de executar o fechamento, deve exibir diálogo de confirmação com a seguinte mensagem explícita: "Esta ação é irreversível. A folha de [Mês/Ano] será fechada e não poderá ser alterada. Confirma?".

- 3.1.1.1 - O botão "Fechar Folha" deve estar desabilitado enquanto existir algum funcionário ativo com status "Pendente" ou "Erro" no processamento.

- 3.1.1.1 - Após o fechamento, o sistema deve gerar e exibir automaticamente o relatório de provisões do mês, com os valores calculados de 13º Salário, Férias + ⅓ e FGTS para cada funcionário.

### 3.1.8 Tela de Visualização e Emissão de Holerite

- 3.1.1.1 - O sistema deve apresentar tela de holerite com seletor de funcionário e seletor de mês/ano de competência. Apenas meses com folha fechada devem estar disponíveis no seletor.

- 3.1.1.1 - O holerite exibido deve conter obrigatoriamente os seguintes campos, em conformidade com o art. 464 da CLT:
	- 3.1.1.1 - Dados da empresa: Razão Social e CNPJ.
	- 3.1.1.1 - Dados do funcionário: Nome, CPF (parcialmente ocultado), Cargo, Data de Admissão e PIS/PASEP.
	- 3.1.1.1 - Mês e ano de competência.
	- 3.1.1.1 - Tabela de proventos: código da rubrica, descrição, referência e valor.	
	- 3.1.1.1 - Tabela de descontos: código da rubrica, descrição, referência e valor.
	- 3.1.1.1 - otais: Total de Proventos, Total de Descontos e Salário Líquido (em destaque).

- 3.1.1.1 - Linha de assinatura do colaborador (para versão impressa).

- 3.1.1.1 - O sistema deve disponibilizar botão "Exportar PDF" que gera o holerite em formato PDF/A, com nome de arquivo padrão no formato: HOLERITE_[CPF]_[AAAAMM].pdf.

- 3.1.1.1 - O sistema deve permitir a visualização de holerites de meses anteriores fechados, sem limite de período para consulta.

- 3.1.1.1 - O holerite em PDF deve ser gerado sem a linha de assinatura (campo preenchido com "Documento Digital — Dispensa Assinatura") quando exportado eletronicamente.

### 3.1.9 Tela de Histórico de Folhas de Pagamento

- 3.1.1.1 - O sistema deve apresentar tela de histórico com listagem de todas as folhas já processadas, organizadas por mês/ano em ordem cronológica decrescente.

- 3.1.1.1 - Para cada folha listada, devem ser exibidos: Mês/Ano de Competência, Status (Aberta / Fechada), Data de Fechamento, Quantidade de Funcionários Processados e Total Líquido da Folha.

- 3.1.1.1 - A tela deve permitir filtragem por intervalo de datas (De: Mês/Ano — Até: Mês/Ano).

- 3.1.1.1 - O sistema deve permitir a seleção de uma folha fechada para visualização detalhada, exibindo o resumo individual de cada funcionário naquela competência.

	- 3.1.1.1 - O sistema não deve exibir botões de edição ou processamento para folhas com status "Fechada", apenas o botão "Visualizar".

### 3.1.2 Interface de Hardware
* 3.1.2.1 - O sistema deve ser executado em computadores pessoais compatíveis com a plataforma Java.

- 3.1.2.3 - O sistema deve funcionar em equipamentos com capacidade mínima suficiente para execução da Java Virtual Machine (JVM).

### 3.1.4 Interface de Comunicação
- 3.1.4.1 - O sistema deve permitir leitura e gravação de dados em banco de dados utilizado pela aplicação.

- 3.1.4.2 - O sistema deve permitir importação e exportação de arquivos utilizados no processamento da folha de pagamento.

- 3.1.4.3 - O sistema deve permitir geração de arquivos para integração com sistemas externos, como sistemas bancários ou governamentais.

- 3.1.4.4 - O sistema deve permitir troca de dados entre módulos internos do sistema.

## 3.2 Requisitos Funcionais
### 3.2.1 - Cadastros
* 3.2.1.1 - O sistema deve permitir o cadastro da empresa.
	- 3.2.1.1.1 - O sistema deve permitir o cadastro de dados do empregador.
	- 3.2.1.1.2 - O sistema deve permitir o cadastro de CNPJ.
	- 3.2.1.1.3 - O sistema deve permitir o cadastro de alíquotas tributárias.
	- 3.2.1.1.4 - O sistema deve permitir o cadastro de informações fiscais.
* 3.2.1.2 - O sistema deve permitir o cadastro do usuários administrativos para operação do sistema.

* 3.2.1.3 - O sistema deve permitir o cadastro do usuário de recursos humanos para operação do sistema.

* 3.2.1.4 - O sistema deve permitir o cadastro de proventos ou descontos.
	- 3.2.1.4.1 - O sistema deve permitir o cadastro de horas extras.
	- 3.2.1.4.2 - O sistema deve permitir o cadastro de bônus.
	- 3.2.1.4.3 - O sistema deve permitir o cadastro de atestados.
	
* 3.2.1.5 - O sistema deve permitir o cadastro de funcionários.
	- 3.2.1.5.1 - O sistema deve permitir o cadastro de nome.
	- 3.2.1.5.2 - O sistema deve permitir o cadastro de CPF.
	- 3.2.1.5.3 - O sistema deve permitir o cadastro de salário base.
	- 3.2.1.5.4 - O sistema deve permitir o cadastro de cargo.
	- 3.2.1.5.5 - O sistema deve permitir o cadastro de dependentes.

### 3.2.2- Apuração de frequência
* 3.2.2.1 - O sistema deve registrar dias trabalhados pelo funcionário.

- 3.2.2.2 - O sistema deve registrar de faltas e atrasos no período de apuração.

- 3.2.2.3 - Cálculo automático de horas extras registradas.

- 3.2.2.4 - Cálculo de descanso semanal remunerado (DSR).

- 3.2.2.5 - Importação de dados de frequência provenientes de sistemas de ponto.

- 3.2.2.6 - Registro de justificativas para ausências ou atrasos.

### 3.2.3 - Processamento de Proventos
- 3.2.3.1 - Cálculo do salário base mensal ou proporcional ao período trabalhado.

- 3.2.3.2 - Cálculo de adicionais legais (insalubridade, periculosidade e adicional noturno).

- 3.2.3.3 - Cálculo de comissões ou premiações variáveis.

- 3.2.3.4 - Pagamento de adiantamento salarial (vale quinzenal).

- 3.2.3.5 - Cálculo de benefícios concedidos pela empresa.

- 3.2.3.6 - Registro de gratificações ou bônus eventuais.

### 3.2.4 - Processamento de Descontos
- 3.2.4.1 - Cálculo do desconto de INSS (Previdência Social).

- 3.2.4.2 - Cálculo do desconto de IRRF (Imposto de Renda Retido na Fonte).

- 3.2.4.3 - Desconto de benefícios como vale-transporte, vale-refeição e plano de saúde.

- 3.2.4.4 - Desconto de faltas e atrasos registrados.

- 3.2.4.5 - Descontos referentes a pensão alimentícia ou outros encargos judiciais.

- 3.2.4.6 - Descontos relacionados a adiantamentos ou empréstimos.
### 3.2.5 - Encargos e Obrigações Acessórias
- 3.2.5.1 - Cálculo do FGTS (Fundo de Garantia do Tempo de Serviço).

- 3.2.5.2 - Cálculo de INSS patronal sobre a folha de pagamento.

- 3.2.5.3 - Geração de eventos e informações compatíveis com o sistema e-Social.

- 3.2.5.4 - Registro de encargos trabalhistas relacionados ao período.

- 3.2.5.5 - Consolidação de dados para envio aos sistemas governamentais.
### 3.2.6 Saídas e Relatórios
-  3.2.6.1 - Cálculo do salário líquido após aplicação de proventos e descontos.

- 3.2.6.2 - Geração do holerite (recibo de pagamento) para cada funcionário.

- 3.2.6.3 - Geração de arquivo de remessa bancária para pagamento de salários.

- 3.2.6.4 - Emissão de relatório de resumo da folha de pagamento.

- 3.2.6.5 - Consulta ao histórico de folhas processadas.

- 3.2.6.6 - Exportação de relatórios em formatos digitais (PDF ou planilha).
## 3.3 Requisitos não Funcionais
### 3.3.1 Desempenho

- 3.3.1.1 - O sistema deve processar a folha de pagamento em tempo adequado (máximo 5 minutos para até 100 funcionários).

- 3.3.1.2- O processamento deve suportar múltiplos funcionários simultaneamente (até 500 funcionários em lote).

- 3.3.1.3 - As operações de consulta devem apresentar resultados em tempo reduzido (menos de 2 segundos).

### 3.3.2 Segurança
- 3.3.2.1 - O sistema deve exigir autenticação para acesso às funcionalidades.

- 3.3.2.2 - As senhas dos usuários devem ser armazenadas de forma criptografada.

- 3.3.2.3 - O sistema deve registrar tentativas de acesso inválidas.

- 3.3.2.4 - O sistema deve garantir proteção de dados pessoais conforme a LGPD.

### 3.3.3 Confiabilidade
- 3.3.3.1 - O sistema deve manter integridade dos dados armazenados.

- 3.3.3.2 - Alterações relevantes devem ser registradas em logs de auditoria.

- 3.3.3.3 - O sistema deve garantir consistência dos cálculos da folha.

### 3.3.4 Usabilidade
- 3.3.4.1 - O sistema deve possuir interface clara e intuitiva para usuários do setor de RH.

- 3.3.4.2 - As telas devem apresentar organização lógica das funcionalidades.

- 3.3.4.3 - O sistema deve fornecer mensagens informativas em caso de erro.

### 3.3.5 Disponibilidade
- 3.3.5.1- O sistema deve estar disponível durante o horário de operação da empresa.

- 3.3.5.2 - O sistema deve minimizar períodos de indisponibilidade.

### 3.3.6 Manutenibilidade

- 3.3.6.1 - O sistema deve possuir estrutura modular para facilitar manutenção.

- 3.3.6.2 - O código do sistema deve seguir padrões de desenvolvimento definidos.

## 3.4 Requisitos de Dados

### 3.4.1 Modelo de Dados
- O sistema utilizará um banco de dados relacional (ex: PostgreSQL ou MySQL) para armazenar informações de funcionários, salários, lançamentos, etc.
- Tabelas principais: 
  - Funcionários (dados pessoais, salário base, cargo)
  - Salários (histórico versionado de alterações salariais)
  - Lançamentos (exceções mensais: horas extras, faltas, bônus)
  - Holerites (registros de folhas processadas)
  - Usuários (para autenticação e permissões)

### 3.4.2 Integridade e Segurança de Dados
- O sistema deve garantir a integridade referencial entre tabelas.
- Backups automáticos diários devem ser realizados.
- Dados sensíveis (salários, CPFs) devem ser criptografados no banco de dados.

# 4 Apêndices
* Tabela de Rubricas Padrão: Lista de códigos (Ex: 001 - Salário Base, 002 - Hora Extra 50%, 003 - INSS, 004 - IRRF).
* Modelos de Fórmulas: Exemplos de cálculos:
  - Salário Líquido = Salário Base + Proventos - Descontos
  - FGTS = 8% do Salário Base
  - INSS = Tabela progressiva conforme legislação vigente
* Diagramas UML: Ver arquivo UML_teste.excalidraw para diagramas de classes e sequências.

# 5 Índice
1. Introdução
   1.1 Propósito do Documento de Requisitos
   1.2 Escopo do produto
   1.3 Definições, acrônimos e abreviações
   1.4 Referências
   1.5 Estruturação do Documento de Requisitos
2. Descrição Geral
   2.1 Perspectiva do Produto
   2.2 Funcionalidades do Produto
   2.3 Características do Usuário
   2.4 Restrições Gerais
   2.5 Suposições e Dependências
3. Requisitos Específicos
   3.1 Requisitos de Interface
   3.2 Requisitos Funcionais
   3.3 Requisitos não Funcionais
   3.4 Requisitos de Dados
4. Apêndices
5. Índice







