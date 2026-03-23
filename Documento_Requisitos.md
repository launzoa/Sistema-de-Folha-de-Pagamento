# Introdução

## 1.1 Propósito do Documento de Requisitos
O sistema automatiza o cálculo e a emissão de uma folha de pagamento de funcionários. A folha de pagamento é baseada no contrato CLT, onde temos condições e requisitos mais claros e determinados para projetar. Outros regimes, como não-CLT (PJ, estagiários) e alterações contratuais do CLT estão fora do escopo do sistema. 

## 1.2 Escopo do produto 
Para a primeira fase do projeto, o sistema contará com: 
* **Histórico das Folhas de Pagamento:** Fornecerá uma interface para visualizar as antigas folhas de pagamento referente aos meses anteriores.
* **Horas Computadas:** O tempo de trabalho máximo de qualquer tipo de funcionário será de oito (8) horas diárias para cinco (5) dias na semana - segunda, terça, quarta, quinta e sexta-feira. 
* **Gestão de Cadastros:** Permitirá a inclusão, leitura e atualização de dados de funcionários, mantendo obrigatoriamente um histórico versionado de alterações salariais. A gestão também conta com a entrada e gestão de planilhas.
* **Lançamentos de exceção (mensais):** Fornecerá uma interfaces para o lançamento de eventos (variáveis) que impactam o mês corrente, e.g., horas extras, bônus, faltas, atrasos, atestados diversos. A entrada de exceção deverá ser feita de forma mensal. Lançamentos de exceções não alteram meses já fechados, implica um histórico auditável.
* **Processamento Lógico:** Após o processamento, executará o cálculo matemático de transformação do salário bruto em salário líquido, aplicando as equações de soma de proventos e subtração de descontos registrados para o mês corrente.
* **Geração de Artefatos:** Compilará os dados processados para a emissão visual e exportável do demonstrativo de pagamento individual (holerite).
* **Gestão de Rubricas Fixas e Variáveis:** Calculará o valor da diária de trabalho ("Valor dia") com base no salário contratual e processará a concessão de benefícios fixos (ex: Cesta Básica) e variáveis (ex: Participação nos Lucros e Resultados - PRL).
* **Motor de Provisões e Encargos:** Diferente de um sistema básico, este MVP já englobará o cálculo automatizado de provisões trabalhistas essenciais exibidas no fechamento mensal, especificamente a proporcionalidade ou valor integral de **13º Salário**, **Férias + ⅓** e o recolhimento do **FGTS (com a multa de 40% embutida ou provisionada)**.
* **Geração de saídas:** A emissão do demonstrativo de pagamento individual, e.g., Holerite (contra-cheque). Permite consultas referentes a meses anteriores já fechados. As consultas abrangem um período mensal fixo. 
* **O que não será feito agora:** Por enquanto, não haverá implementação de exceção (mensais) como: fonte de impostos que não constam no fluxo atual aprovado (como tabelas progressivas de IRRF e INSS), benefícios variáveis (e.g, coparticipação em plano de saúde) e opções de relatório para cada setor da "empresa", não realizará pagamentos para regimes não-CLT (PJ, estagiários).

## 1.3 Definições, acrônimos e abreviações
Para garantir a compreensão uniforme deste documento por leitores de diferentes 
áreas (técnicas e de negócios), os seguintes acrônimos e termos legislativos 
são definidos:

* **CLT:** Consolidação das Leis do Trabalho. Legislação brasileira que 
  regulamenta as relações de trabalho e dita as regras de remuneração 
  aplicáveis a este sistema.
* **FGTS:** Fundo de Garantia do Tempo de Serviço.
* **PLR:** Participação nos Lucros e Resultados. Também referenciada como 
  PRL em alguns contextos. Não integra a base de cálculo de INSS, FGTS 
  ou férias (Lei nº 10.101/2000).
* **13º Salário:** Gratificação natalina compulsória prevista 
  na Lei nº 4.090/1962.
* Os termos técnicos e operacionais utilizados ao longo deste documento — como: Competência, Folha Aberta, Folha Fechada, Snapshot, DSR, Admissão Proporcional, Valor-Dia, Valor-Hora, Rubrica, Provento, Desconto, Holerite, INSS, IRRF, PLR e outros — estão definidos de forma completa e com base legal no Apêndice 4.3 — Glossário Expandido.

## 1.4 Referências
* As informações referentes ao regime CLT foram retiradas do documento: Consolidação das Leis do Trabalho - CLT e normas correlatas. Originária da Constituição da República Federativa do Brasil
## 1.5 Estruturação do Documento de Requisitos 
Este documento segue o padrão IEEE 830 (Software Requirements Specification) adaptado ao contexto do projeto SFP-CLT. Está organizado em quatro seções principais e um índice, conforme descrito abaixo:

**Seção 1 — Introdução:** Apresenta o propósito do documento, o escopo do produto, o glossário de termos e acrônimos, as referências utilizadas e esta descrição estrutural. Destina-se a orientar qualquer leitor antes da leitura técnica.
**Seção 2 — Descrição Geral:** Descreve o sistema em alto nível: sua perspectiva de produto, principais funcionalidades, perfis de usuário, restrições gerais e premissas que condicionam o desenvolvimento. Não detalha regras de negócio, apenas contextualiza o produto.
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

# 2 Descrição Geral

## 2.1 Perspectiva do Produto
* O sistema é uma aplicação desktop standalone desenvolvida em Java, voltada para o departamento de Recursos Humanos (RH) ou contabilidade. 
- Interface: Java Desktop (Swing ou JavaFX). 
- Hardware: A aplicação é executada localmente na máquina do usuário, conectando-se a um banco de dados que pode estar na mesma máquina (instalação local) ou em servidor na rede interna da empresa.
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
## 2.4 Restrições Gerais
* Legislação: O sistema deve seguir estritamente a tabela vigente da CLT (2024/2025/2026).
* Segurança: Dados salariais são protegidos pela LGPD (Lei Geral de Proteção de Dados). O acesso deve ser restrito por login e senha.
* Conformidade: O sistema não permite a alteração de folhas de meses já encerrados para garantir a integridade contábil.
* Tecnologia: O sistema deve ser desenvolvido integralmente em Java (versão 11 ou superior), sem dependência de navegador web ou servidor de aplicação externo para sua operação.
## 2.5 Suposições e Dependências
* Supõe-se que o usuário possua acesso estável à internet.
* O sistema depende da alimentação mensal das "exceções" (faltas/extras) antes do fechamento da folha.

# 3 Requisitos Específicos

## 3.1 Requisitos de Interface com o Usuário (GUI)
### 3.1.1 Padrões Gerais da Interface
Os seguintes requisitos se aplicam a todas as telas do sistema:
-  3.1.1.2 - O sistema deve possuir uma interface gráfica.
*  3.1.1.3 - Todas as telas devem exibir, no cabeçalho, o nome do sistema : "SFP-CLT".
*  3.1.1.4 - Todas as telas devem exibir, no cabeçalho, o nome do usuário.
*  3.1.1.5 - Todas as telas devem exibir, no cabeçalho, o perfil de acesso ativo.
* 3.1.1.6 - A paleta de cores, tipografia e espaçamento devem ser padronizados e consistentes em todas as telas da aplicação, seguindo um guia de estilo definido. ******Definir o guia de estilo - paleta de cores, tipografia e espaçamento
* 3.1.1.7 - Todos os formulários devem indicar visualmente os campos obrigatórios, utilizando marcador de asterisco ( * ) em vermelho ao lado do rótulo.
* 3.1.1.8 - O sistema deve exibir mensagem de confirmação (diálogo modal com opções "Confirmar" e "Cancelar") antes de executar qualquer operação irreversível.
* 3.1.1.9 - O sistema deve exibir mensagens de erro em linguagem clara e objetiva, sem códigos de exceção Java expostos ao usuário.
* 3.1.1.10 - Campos monetários devem ser formatados automaticamente com separador de milhar (ponto) e duas casas decimais (ex.: R$ 3.500,00) ao perder o foco.
* 3.1.1.11 - Campos de CPF devem aplicar máscara de entrada no formato 000.000.000-00 em tempo real durante a digitação.
* 3.1.1.12 - Campos de data devem utilizar máscara DD/MM/AAAA e validar se a data informada é uma data calendário válida.
* 3.1.1.13 - O sistema deve manter a resolução mínima de tela de 1280 x 720 pixels como referência de layout.
- 3.1.1.14 - A interface deve permitir acesso às funcionalidades do sistema.

### 3.1.2 Tela de Login e Autenticação
- 3.1.1.1 - O sistema deve apresentar uma tela de login como tela inicial obrigatória, bloqueando o acesso a qualquer outra funcionalidade antes da autenticação.
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
- 3.1.1.1 - O sistema deve registrar em log de segurança toda tentativa de acesso (bem-sucedida ou não). O log deve conter:
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
- 3.1.1.1 - O sistema deve apresentar tela de cadastro de funcionários com os seguintes campos obrigatórios: 
	- 3.1.1.1 - Nome completo (texto, máx. 150 caracteres); 
	- 3.1.1.1 - CPF (máscara 000.000.000-00, único no sistema); 
	- 3.1.1.1 - Data de Nascimento (DD/MM/AAAA);
	- 3.1.1.1 - Data de Admissão (DD/MM/AAAA);
	- 3.1.1.1 - Cargo (texto, máx. 100 caracteres);
	- 3.1.1.1 - Salário Base (valor monetário, mínimo R$ 1.518,00 conforme piso CLT 2025).
- 3.1.1.1 - O sistema deve apresentar os seguintes campos opcionais:  Número de Dependentes (inteiro ≥ 0), Banco e Conta para depósito salarial.
- 3.1.1.1 - O campo CPF deve ser validado pelo algoritmo de dígitos verificadores da Receita Federal. O sistema deve rejeitar CPFs com todos os dígitos iguais.
- 3.1.1.1 - O sistema deve impedir o cadastro de dois funcionários com o mesmo CPF, exibindo mensagem de erro específica.
- 3.1.1.1 - A tela de listagem de funcionários deve apresentar tabela com colunas: Nome, CPF (parcialmente ocultado: ***.***.XXX-XX), Cargo, Salário Base e Status (Ativo/Inativo).
- 3.1.1.1 - A listagem deve permitir busca por CPF.
- 3.1.1.1 - O sistema deve permitir inativar um funcionário (demissão), registrando a data de desligamento. 
- 3.1.1.1 - Funcionários inativos não devem participar do processamento da folha de meses posteriores à data de desligamento.
- 3.1.1.1 - O sistema não deve permitir a exclusão permanente de registros de funcionários, apenas a inativação, para preservar o histórico de folhas passadas.

### 3.1.5 Tela de Cadastro da Empresa
- 3.1.1.1 - O sistema deve apresentar tela de configuração dos dados da empresa com os seguintes campos obrigatórios: 
	- 3.1.1.1 - Razão Social (texto), 
	- 3.1.1.1 - CNPJ (máscara 00.000.000/0000-00), 
	- 3.1.1.1 - Endereço completo e Responsável Legal (nome).
- 3.1.1.1 - O CNPJ deve ser validado pelo algoritmo de dígitos verificadores da Receita Federal.
- 3.1.1.1 - Alterações nos parâmetros fiscais da empresa devem exigir confirmação com diálogo modal e devem ser registradas em log de auditoria com o usuário e data.

### 3.1.6 Tela de Lançamentos de Exceção Mensais
- 3.1.1.1 - O sistema deve apresentar tela de lançamentos mensais, acessível somente quando a folha do mês corrente estiver com status "Aberta".
- 3.1.1.1 - A tela deve exibir o mês e ano de competência corrente de forma destacada, impedindo ambiguidade sobre o período de referência dos lançamentos.
- 3.1.1.1 - O sistema deve permitir selecionar um funcionário ativo por meio de campo de busca antes de registrar qualquer lançamento.
- 3.1.1.1 - Para cada funcionário selecionado, o sistema deve permitir o registro dos seguintes tipos de evento:
	- 3.1.1.1 - Horas Extras: quantidade de horas (decimal, ex.: 2,5), tipo (50% ou 100%) e data de ocorrência. O sistema deve calcular e exibir o valor monetário.
	- 3.1.1.1 - Faltas Injustificadas: quantidade de dias inteiros ou fração (em horas). O sistema deve calcular e exibir o desconto correspondente em tempo real.
	- 3.1.1.1 - Atrasos: quantidade de minutos e data de ocorrência. O sistema deve calcular o desconto proporcional em tempo real.
	- 3.1.1.1 - Atestado Médico: data de início, data de fim e arquivo digitalizado (PDF). Atestados justificam ausências e neutralizam o desconto de falta.
	- 3.1.1.1 - Bônus / Gratificação: valor monetário livre e descrição textual (obrigatória, máx. 200 caracteres).
- 3.1.1.1 - O sistema deve impedir o lançamento de eventos em meses cuja folha já esteja com status "Fechada", exibindo mensagem explicativa ao usuário.
- 3.1.1.1 - O sistema deve permitir a edição e exclusão de lançamentos do mês corrente enquanto a folha estiver aberta.

### 3.1.7 Tela de Processamento e Fechamento da Folha
- 3.1.1.1 - O sistema deve apresentar tela de processamento da folha com listagem de todos os funcionários ativos e seus respectivos status de processamento.
- 3.1.1.1 - O sistema deve disponibilizar botão "Processar Folha" que executa o cálculo para todos os funcionários ativos de forma sequencial ou paralela.
- 3.1.1.1 - Após o processamento, o sistema deve exibir resumo tabular contendo, para cada funcionário: 
	- 3.1.1.1 - Nome;
	- 3.1.1.1 - Salário Bruto;
	- 3.1.1.1 - Total de Proventos; 
	- 3.1.1.1 - Total de Descontos;
	- 3.1.1.1 - Salário Líquido;
	- 3.1.1.1 - Status.
- 3.1.1.1 - O sistema deve permitir re-processar a folha enquanto estiver com status "Aberta", sobrescrevendo os valores calculados anteriormente após confirmação.
- 3.1.1.1 - O sistema deve disponibilizar botão "Fechar Folha" que torna o mês de competência imutável.
- 3.1.1.1 - O botão "Fechar Folha" deve estar desabilitado enquanto existir algum funcionário ativo com status "Pendente" ou "Erro" no processamento.
- 3.1.1.1 - Após o fechamento, o sistema deve gerar e exibir automaticamente o relatório de provisões do mês, com 13º Salário, Férias + ⅓ e FGTS para cada funcionário.

### 3.1.8 Tela de Visualização e Emissão de Holerite
- 3.1.1.1 - O sistema deve apresentar tela de holerite com seletor de funcionário e seletor de mês/ano de competência. Apenas meses com folha fechada devem estar disponíveis.
- 3.1.1.1 - O holerite exibido deve conter obrigatoriamente os seguintes campos, em conformidade com o art. 464 da CLT:
	- 3.1.1.1 - Dados da empresa: Razão Social e CNPJ.
	- 3.1.1.1 - Dados do funcionário: Nome, CPF (parcialmente ocultado), Cargo, Data de Admissão e PIS/PASEP.
	- 3.1.1.1 - Mês e ano de competência.
	- 3.1.1.1 - Tabela de proventos: código da rubrica, descrição, referência e valor.	
	- 3.1.1.1 - Tabela de descontos: código da rubrica, descrição, referência e valor.
	- 3.1.1.1 - Totais: Total de Proventos, Total de Descontos e Salário Líquido (em destaque).
- 3.1.1.1 - Linha de assinatura do colaborador (para versão impressa).
- 3.1.1.1 - O sistema deve disponibilizar botão "Exportar PDF" que gera o holerite em formato PDF/A.
- 3.1.1.1 - O sistema deve permitir a visualização de holerites de meses anteriores fechados, sem limite de período para consulta.
- 3.1.1.1 - O holerite em PDF deve ser gerado sem a linha de assinatura (campo preenchido com "Documento Digital — Dispensa Assinatura") quando exportado eletronicamente.

### 3.1.9 Tela de Histórico de Folhas de Pagamento
- 3.1.1.1 - O sistema deve apresentar tela de histórico com listagem de todas as folhas já processadas, organizadas por mês/ano em ordem cronológica decrescente.
- 3.1.1.1 - Para cada folha listada, devem ser exibidos: Mês/Ano de Competência, Status, Data de Fechamento, Quantidade de Funcionários Processados e Total Líquido da Folha.
- 3.1.1.1 - A tela deve permitir filtragem por intervalo de datas (De: Mês/Ano — Até: Mês/Ano).
- 3.1.1.1 - O sistema deve permitir a seleção de uma folha fechada para visualização detalhada, exibindo o resumo individual de cada funcionário naquela competência.
	- 3.1.1.1 - O sistema não deve exibir botões de edição ou processamento para folhas com status "Fechada", apenas o botão "Visualizar".

## 3.2 Requisitos Funcionais
### 3.2.1 - Cadastros e Configurações
Este módulo engloba os requisitos referentes ao registro e manutenção dos dados mestres do sistema: empresa, funcionários, usuários e parâmetros fiscais. A integridade dos dados cadastrais é pré-requisito para a correção de todos os cálculos subsequentes.
#### 3.2.1.1 Cadastro da Empresa
**3.2.1.1.1 —** O cadastro deve incluir obrigatoriamente: 
* **3.2.1.1.1 —** Razão Social compreendendo os seguintes requisitos:
	* **3.2.1.1.1 —** Deve ser em forma texto;
	* **3.2.1.1.1 —** Deve conter no máximo 150 caracteres.
* **3.2.1.1.1 —** CNPJ.
* **3.2.1.1.1 —** Endereço contendo os seguintes campos:
	* **3.2.1.1.1 —** Logradouro; 
	* **3.2.1.1.1 —** Número;
	* **3.2.1.1.1 —** Complemento; 
	* **3.2.1.1.1 —** Bairro;
	* **3.2.1.1.1 —** Cidade;
	* **3.2.1.1.1 —** UF;
	* **3.2.1.1.1 —** CEP
* **3.2.1.1.1 —** Nome do Responsável Legal.
**3.2.1.1.2 —** O CNPJ deve ser validado pelo algoritmo oficial de dígitos verificadores da Receita Federal.
**3.2.1.1.2 —** O sistema deve rejeitar CNPJs com todos os dígitos iguais (ex.: 11.111.111/1111-11) mesmo que passem na verificação matemática.
**3.2.1.1.3 —** O sistema deve armazenar os seguintes parâmetros fiscais utilizados nos cálculos da folha:
	**3.2.1.1.3 —** Alíquota de FGTS: valor percentual 8,00%.
	**3.2.1.1.3 —** Horas mensais contratuais: inteiro positivo (padrão: 220 horas). Representa o divisor para cálculo do valor-hora.
	**3.2.1.1.3 —** Percentual de adicional de hora extra padrão (50%), fixo por legislação.
	**3.2.1.1.3 —** Percentual de adicional de hora extra especial (100%): aplicável a domingos, feriados e período noturno contínuo, conforme CLT Art. 59-A.
	**3.2.1.1.3 —** Valor mensal da Cesta Básica: valor monetário (R$), podendo ser zero.

#### 3.2.1.2 Cadastro de Funcionários
**3.2.1.2 —** O sistema deve permitir o cadastro, a edição e a inativação de funcionários CLT.
**3.2.1.2 —** A exclusão permanente de funcionários de registros não é permitida.
**3.2.1.2.1 —** Campos obrigatórios para cadastro de funcionários: 
	**3.2.1.2.1 —** Nome Completo;
	**3.2.1.2.1 —** CPF;
	**3.2.1.2.1 —** Data de Nascimento;
	**3.2.1.2.1 —** Data de Admissão;
	**3.2.1.2.1 —** Cargo; 
	**3.2.1.2.1 —** Salário Base Contratual;
	**3.2.1.2.1 —** Banco;
	**3.2.1.2.1 —**  Conta-corrente para depósito.
**3.2.1.2.2 —** Campos opcionais: Número de Dependentes (inteiro ≥ 0, padrão 0) e e-mail institucional.
**3.2.1.2.3 —** O sistema deve garantir unicidade do CPF: nenhum dois funcionários — ativos ou inativos — podem compartilhar o mesmo CPF.
**3.2.1.2.4 —** O Salário Base deve ser ≥ ao salário mínimo nacional vigente. 
**3.2.1.2.1 —** O sistema deve rejeitar valores abaixo do piso, exibindo mensagem com o valor mínimo aceitável.

|                                                                                                                                                                                                                                                                                                         |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Regra de Negócio — Piso Salarial [RN-01]**<br><br>Salário Base ≥ Salário Mínimo Nacional vigente<br><br>Referência 2025: R$ 1.518,00 (Decreto nº 12.302/2024)<br><br>O valor do piso deve ser armazenado como parâmetro configurável<br><br>e atualizado pelo Administrador a cada reajuste anual. |

**3.2.1.2.5 —** O sistema deve manter histórico completo de alterações salariais. Cada entrada do histórico deve registrar: 
	**3.2.1.2.5 —** Salário anterior;
	**3.2.1.2.5 —** Novo salário; 
	**3.2.1.2.5 —** Data de alteração; 
	**3.2.1.2.5 —** Usuário que alterou.
**3.2.1.2.6 —** O sistema deve identificar o mês de admissão de cada funcionário para aplicar cálculo proporcional automaticamente na primeira folha processada após admissão.
**3.2.1.2.7 —** A inativação de um funcionário deve registrar obrigatoriamente a data de desligamento. 
**3.2.1.2.8 —** O sistema deve calcular e exibir automaticamente, no cadastro do funcionário, o tempo de empresa em anos e meses completos com base na data de admissão.

#### 3.2.1.3 Cadastro de Rubricas
**3.2.1.3 —** O sistema deve manter uma tabela de rubricas (códigos de itens da folha) que classifica cada valor como provento ou desconto, fixo ou variável.
**3.2.1.3.1 —** Cada rubrica deve conter: 
	**3.2.1.3.1 —** Código único (numérico, 3 dígitos), 
	**3.2.1.3.1 —** Natureza (Provento / Desconto), 
	**3.2.1.3.1 —** Tipo (Fixo / Variável) e 
	**3.2.1.3.1 —** Incidência (flags: incide INSS, incide FGTS, incide IRRF).
**3.2.1.3.2 —** O sistema deve incluir as rubricas padrão pré-cadastradas conforme Apêndice 4.1. 
	**3.2.1.3.2 —** As rubricas de códigos 001 a 005 são consideradas obrigatórias e não podem ser excluídas nem ter seu código, natureza ou flags de incidência alterados. 
	**3.2.1.3.2 —** As rubricas de códigos 006 a 008 e 101 a 106 podem ser excluídas pelo Administrador, desde que não estejam referenciadas em nenhuma folha já processada.
**3.2.1.3.3 —** O Administrador pode criar rubricas personalizadas (código ≥ 500) para benefícios ou descontos específicos da empresa.

#### 3.2.1.4 Cadastro de Usuários do Sistema
**3.2.1.4 —** O sistema deve permitir o cadastro de usuários .
**3.2.1.4.1 —** Os perfis disponíveis são: 
	**3.2.1.4.1 —** Administrador — acesso total, incluindo configuração de parâmetros e gestão de usuários. A gestão de usuários engloba:
		**3.2.1.4.1 —** Adicionar analista de RH;
		**3.2.1.4.1 —** Remover analista de RH;
	**3.2.1.4.1 —** Analista de RH — acesso operacional: 
		**3.2.1.4.1 —** cadastros, 
		**3.2.1.4.1 —** lançamentos, 
		**3.2.1.4.1 —** processamento e 
		**3.2.1.4.1 —** emissão de holerites.
**3.2.1.4.2 —** Deve existir ao menos um usuário com perfil Administrador ativo em todo momento.
**3.2.1.4.3 —** Nenhuma senha deve ser armazenada ou exibida em texto plano em qualquer circunstância.

### 3.2.2- Apuração de frequência
Este módulo define as regras de registro e cálculo dos eventos de frequência que impactam a folha do mês corrente. Todos os lançamentos de frequência são mensais, vinculados à competência aberta, e se tornam somente leitura após o fechamento da folha.
#### 3.2.2.1 Calendário e Dias Úteis
**3.2.2.1 —** O sistema deve calcular, para cada mês de competência, considerando dias úteis como segunda a sexta-feira, excluindo sábados e domingos.
	**3.2.2.1.1 —** Feriados nacionais não são descontados automaticamente pelo sistema nesta versão (MVP). 
	**3.2.2.1.2 —** O total de dias úteis é utilizado como denominador no cálculo do valor-dia (desconto de falta).

|                                                                                                                                                                                                           |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Fórmula — Valor do Dia de Trabalho [RN-02]**<br><br>ValorDia = SalárioBase ÷ DiasÚteisMês<br><br>Exemplo: Salário Base = R$ 3.000,00 \| Dias Úteis = 22<br><br>Valor_Dia = 3.000,00 ÷ 22 = R$ 136,36 |

#### 3.2.2.2 Registro de Faltas Injustificadas
**3.2.2.2 —** O sistema deve permitir o lançamento de faltas injustificadas em dias inteiros ou fração de dia (em horas), associadas a um funcionário e ao mês de competência corrente.
	**3.2.2.2.1 —** O desconto por falta injustificada de dia inteiro deve ser calculado como: Desconto_Falta = Valor_Dia × Nº_Dias_Faltados.
	**3.2.2.2.2 —** O desconto por fração de dia: Desconto_Fração = Valor_Hora × Nº_Horas_Faltadas, onde Valor_Hora = Salário_Base ÷ Horas_Mensais_Contratuais.
	**3.2.2.2.3 —** O sistema deve impedir o lançamento de faltas em quantidade superior ao total de dias úteis do mês de competência.

|                                                                                                                                                                                                                                                                                                                  |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Fórmula — Desconto por Falta [RN-03]**<br><br>Desconto_Falta_Dia = (Salário_Base ÷ Dias_Úteis) × Qtd_Dias<br><br>Desconto_Falta_Horas = (Salário_Base ÷ Horas_Mensais) × Qtd_Horas<br>Exemplo (dia): Salário = R$ 2.200,00 \| Dias Úteis = 22 \| 2 faltas<br><br>Desconto = (2.200,00 ÷ 22) × 2 = R$ 200,00 |

|                                                                                                                                                                                        |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| ✅ **Exemplo: Falta com DSR afetado**<br><br>Faltas injustificadas também afetam o DSR (Descanso Semanal Remunerado).<br><br>A perda do DSR é calculada automaticamente conforme RN-05. |
|                                                                                                                                                                                        |

#### 3.2.2.3 Registro de Atrasos
**3.2.2.3 —** O sistema deve permitir o lançamento de atrasos em minutos, associados a uma data específica dentro do mês de competência corrente. **VER SE VAI CALCULAR ATRASO DE MINUTOS TBM OU VAI ARREDONDAR**
	**3.2.2.3.1 —** O desconto por atraso deve ser calculado proporcionalmente ao valor da hora: Desconto_Atraso = Valor_Hora × (Minutos_Atraso ÷ 60).
	**3.2.2.3.2 —** O sistema deve permitir o lançamento de múltiplos atrasos para o mesmo funcionário no mesmo mês, acumulando o total de minutos.
	**3.2.2.3.3 —** Atrasos de até 10 minutos diários são tolerados pela CLT (Art. 58, § 1º). 
	**3.2.2.3.3 —** O sistema deve identificar atrasos dentro da tolerância e não gerar desconto para esses casos, exibindo indicador visual ao operador.

|                                                                                                                                                                                                                                                                                                                                                       |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Fórmula — Desconto por Atraso [RN-04]**<br><br>Valor_Hora = Salário_Base ÷ Horas_Mensais_Contratuais<br><br>Desconto_Atraso = Valor_Hora × (Total_Minutos_Atraso ÷ 60)<br><br>Exemplo: Salário = R$ 2.640,00 \| Horas mensais = 220<br><br>Valor_Hora = 2.640,00 ÷ 220 = R$ 12,00<br><br>Atraso de 45 min: 12,00 × (45÷60) = R$ 9,00 de desconto |

#### 3.2.2.4 Descanso Semanal Remunerado (DSR)
**3.2.2.4 —** O sistema deve calcular o impacto das faltas injustificadas sobre o Descanso Semanal Remunerado (DSR), conforme Lei nº 605/1949.
	**3.2.2.4.1 —** O funcionário perde o direito ao DSR da semana em que ocorreu a falta injustificada. 
	**3.2.2.4.1 —** O sistema deve calcular quantos domingos/DSR são afetados com base nas datas das faltas lançadas.
	**3.2.2.4.2 —** O valor do desconto de DSR deve ser calculado como: Desconto_DSR = Valor_Dia × Nº_DSR_Perdidos.
	**3.2.2.4.3 —** O total de DSR perdidos deve ser exibido no resumo do holerite como rubrica destacada (código 104 — Desconto DSR).

|                                                                                                                                                                                                                                                                                                                         |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Regra de Negócio — DSR [RN-05]**<br><br>Para cada semana com ≥ 1 falta injustificada:<br><br>→ 1 DSR (domingo/dia de descanso) é perdido<br>Desconto_DSR = Valor_Dia × Nº_Semanas_com_Falta<br><br>Exemplo: 2 faltas em semanas distintas → 2 DSR perdidos<br><br>Valor_Dia = R$ 100,00 → Desconto_DSR = R$ 200,00 |

#### 3.2.2.5 Registro de Atestados Médicos
**3.2.2.5 —** O sistema deve permitir o registro de atestados médicos que justificam ausências, 
**3.2.2.5 —** O atestado deve impedir que os dias cobertos gerem desconto de falta e não afetam o DSR da semana correspondente.
	**3.2.2.5.1 —** Cada atestado deve conter: data de início, data de fim, tipo (médico / odontológico / outro), CID opcional (texto livre, máx. 10 caracteres).
	**3.2.2.5.3 —** Os primeiros 15 dias de afastamento por doença são de responsabilidade do empregador (CLT Art. 476). O sistema deve:
		**3.2.2.5.3 —** Neutralizar automaticamente o desconto de falta para todos os dias cobertos por atestado médico, até o limite de 15 dias corridos consecutivos.
		**3.2.2.5.3 —** Não afetar o DSR das semanas cobertas pelo atestado dentro desse período.
		**3.2.2.5.3 —** Exibir alerta ao operador quando o atestado registrado ultrapassar 15 dias corridos consecutivos.
	**3.2.2.5.4 —** O upload de arquivo digitalizado do atestado (PDF, JPG ou PNG, máx. 5 MB) é opcional nesta versão, ficando o controle documental a cargo do operador. *****VER SE VAI TER ISSO

#### 3.2.2.6 Registro de Horas Extras
**3.2.2.6 —** O sistema deve permitir o lançamento de horas extras por funcionário, com data de ocorrência, quantidade de horas (decimal, ex.: 1,5) e tipo.
**3.2.2.6.1 —** Tipos de horas extras disponíveis: 
	**3.2.2.6.1 —** 50% — dias úteis (segunda a sábado); 
	**3.2.2.6.1 —** 100% — domingos, feriados ou quando determinado por acordo coletivo.
**3.2.2.6.2 —** O sistema deve alertar, sem bloquear, quando o total mensal de horas extras de um funcionário ultrapassar o limite legal.
	**3.2.2.6.2 —** O limite legal é de 2 horas por dia × dias úteis do mês (CLT Art. 59).
**3.2.2.6.3 —** O valor bruto de cada hora extra deve ser calculado conforme RN-06 e exibido em tempo real ao operador durante o lançamento.

|                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Fórmula — Horas Extras [RN-06]**<br><br>Valor_Hora_Normal = Salário_Base ÷ Horas_Mensais_Contratuais<br><br>Valor_HE_50% = Valor_Hora_Normal × 1,50 × Qtd_Horas<br><br>Valor_HE_100% = Valor_Hora_Normal × 2,00 × Qtd_Horas<br><br>Exemplo: Salário = R$ 3.300,00 \| Horas mensais = 220<br><br>Valor_Hora = 3.300 ÷ 220 = R$ 15,00<br><br>2,5h de HE 50%: 15,00 × 1,50 × 2,5 = R$ 56,25<br><br>1,0h de HE 100%: 15,00 × 2,00 × 1,0 = R$ 30,00 |

### 3.2.3 - Processamento de Proventos
Este módulo descreve o cálculo de todos os valores de natureza creditícia que compõem o salário bruto do funcionário. O salário bruto é a base de cálculo para os encargos (FGTS) e pode ser a base para descontos (INSS).
#### 3.2.3.1 Salário Base e Proporcionalidade
**3.2.3.1 —** O sistema deve calcular o salário base mensal de cada funcionário, aplicando proporcionalidade dos dias trabalhados.
	**3.2.3.1.1 —** Para funcionários que trabalharam o mês completo, o salário base mensal é igual ao salário base contratual cadastrado.
	**3.2.3.1.2 —** Para funcionários admitidos após o primeiro dia útil do mês, o sistema deve calcular o salário proporcional conforme RN-07.
	**3.2.3.1.3 —** Para funcionários desligados antes do último dia útil do mês, o sistema deve aplicar a mesma lógica proporcional conforme RN-07.

|                                                                                                                                                                                                                                                                                                                                                 |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Fórmula — Salário Proporcional [RN-07]**<br><br>Salário_Proporcional = (Salário_Base ÷ Dias_Úteis_do_Mês) × Dias_Trabalhados<br><br>Exemplo: Admissão em 10/03 \| Dias úteis de março = 21<br><br>Dias trabalhados a partir do dia 10 = 16<br><br>Salário Base = R$ 4.400,00<br><br>Salário_Proporcional = (4.400 ÷ 21) × 16 = R$ 3.352,38 |

#### 3.2.3.2 Horas Extras — Processamento
**3.2.3.2 —** O sistema deve consolidar todos os lançamentos de horas extras do mês de competência.
**3.2.3.2 —** O sistema deve calcular o total de proventos de horas extras por tipo de adicional, aplicando as fórmulas definidas em RN-06.
	**3.2.3.2.1 —** O valor total de horas extras deve ser somado ao salário base para compor o salário bruto. Elas são base de incidência de INSS e FGTS.
	**3.2.3.2.2 —** O sistema deve listar as horas extras e o valor delas no holerite separadas por tipo (rubrica 002 para 50% e rubrica 003 para 100%).

#### 3.2.3.3 Bônus e Gratificações
**3.2.3.3 —** O sistema deve processar bônus e gratificações eventuais lançados no módulo de exceções, somando-os ao salário bruto do mês de competência.
	**3.2.3.3.1 —** Bônus lançados devem ser classificados com rubrica 004 (Bônus/Gratificação). 
	**3.2.3.3.2 —** O sistema deve sinalizar ao operador quando um mesmo bônus for lançado por 3 meses consecutivos, conforme CLT Art. 457 § 1º.

#### 3.2.3.4 Cesta Básica
**3.2.3.4 —** O sistema deve processar automaticamente o benefício de Cesta Básica com base no valor configurado no cadastro da empresa.
	**3.2.3.4.1 —** A Cesta Básica é uma rubrica fixa mensal (código 005).
	**3.2.3.4.1 —** A cesta básica não integra a base de cálculo de INSS ou FGTS, visto que possui natureza indenizatória.
	**3.2.3.4.2 —** Funcionários ingressantes no meio do mês recebem a Cesta Básica integral.

#### 3.2.3.5 Participação nos Lucros e Resultados (PLR)
**3.2.3.5 —** O sistema deve processar a PLR quando lançada como evento variável, aplicando as regras de incidência específicas desta rubrica.
	**3.2.3.5.1 —** A PLR não integra a remuneração para efeitos de cálculo de INSS, FGTS, férias ou 13º salário, conforme Lei nº 10.101/2000, Art. 3º.
	**3.2.3.5.2 —** A PLR deve ser exibida no holerite como rubrica destacada (código 006), com valor bruto.
	**3.2.3.5.3 —** O sistema registra o valor bruto da PLR sem reter imposto sobre a PLR.

#### 3.2.3.6 Composição do Salário Bruto
**3.2.3.6 —** O sistema deve calcular o Salário Bruto como a soma de todos os proventos que compõem a base de cálculo dos encargos, fórmula RN-08.

|                                                                                                                                                                                                                                                                                                                             |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Fórmula — Salário Bruto [RN-08]**<br><br>Salário_Bruto = Salário_Base_Proporcional<br><br>+ Total_Horas_Extras<br><br>+ Total_Bônus_com_Incidência<br><br>+ Cesta_Básica (não integra base INSS/FGTS)<br><br>+ PLR (não integra base INSS/FGTS)<br><br>Base_INSS_FGTS = Salário_Base_Prop. + HE + Bônus_com_Incidência |

|   |
|---|
|⚠️ Restrição: O salário bruto exibido no holerite inclui todos os proventos. A base de cálculo de INSS e FGTS exclui rubricas indenizatórias (Cesta Básica, PLR). O sistema deve calcular e armazenar ambos os valores separadamente.|

### 3.2.4 - Processamento de Descontos
Este módulo define os descontos que devem ser subtraídos do salário bruto para apuração do salário líquido. Os descontos são divididos em legais (INSS) e variáveis (faltas, atrasos).
#### 3.2.4.1 Desconto de INSS
**3.2.4.1 —** O sistema deve calcular o desconto de INSS aplicando a tabela progressiva vigente sobre a Base de Cálculo INSS (salário bruto excluídas as rubricas não incidentes).
	**3.2.4.1.2 —** O cálculo deve ser progressivo por faixa (modelo de cálculo progressivo, não alíquota única), conforme Portaria MPS vigente para 2025.
	**3.2.4.1.3 —** O valor calculado do INSS deve ser exibido no holerite com a base de cálculo utilizada e a alíquota efetiva resultante (desconto total / base × 100).

|                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| 📐 **Tabela INSS 2025 — Contribuição Progressiva [RN-09]**<br><br>Faixa 1: até R$ 1.518,00 → alíquota 7,5%<br><br>Faixa 2: R$ 1.518,01 – R$ 2.793,88 → alíquota 9,0%<br><br>Faixa 3: R$ 2.793,89 – R$ 4.190,83 → alíquota 12,0%<br><br>Faixa 4: R$ 4.190,84 – R$ 8.157,41 → alíquota 14,0%<br><br>Teto INSS 2025: R$ 8.157,41 (salários acima do teto<br><br>pagam INSS apenas sobre o valor do teto).<br><br>Desconto_INSS = Σ (Faixa_i × Alíquota_i) |

|   |
|---|
|✅ **Exemplo: Cálculo INSS Progressivo — Salário R$ 5.000,00**<br><br>Faixa 1: R$ 1.518,00 × 7,5% = R$ 113,85<br><br>Faixa 2: R$ 1.275,88 × 9,0% = R$ 114,83 (2.793,88 – 1.518,00)<br><br>Faixa 3: R$ 1.396,95 × 12,0% = R$ 167,63 (4.190,83 – 2.793,88)<br><br>Faixa 4: R$ 809,17 × 14,0% = R$ 113,28 (5.000,00 – 4.190,83)<br><br>TOTAL INSS = R$ 509,59|

#### 3.2.4.3 Desconto de Faltas, Atrasos e DSR
**3.2.4.3 —** O sistema deve consolidar todos os descontos de frequência (faltas, atrasos, DSR perdido) calculados no módulo 3.2.2 e incluí-los na folha.
	**3.2.4.3.1 —** Desconto total de frequência = Desconto_Faltas + Desconto_Atrasos + Desconto_DSR.
	**3.2.4.3.2 —** Cada item deve aparecer como rubrica separada no holerite, com código e valor individual.

#### 3.2.4.4 Outros Descontos Variáveis
**3.2.4.4 —** O sistema deve suportar o lançamento de descontos variáveis adicionais no módulo de exceções, como adiantamentos ou outras deduções autorizadas.
	**3.2.4.4.1 —** Descontos variáveis devem ser vinculados a uma rubrica cadastrada, com descrição e valor informados pelo operador.
	**3.2.4.4.2 —** O sistema deve impedir que o total de descontos supere o total de proventos, resultando em salário líquido negativo. 

|                                                                                                                                                                                                                                                                                                                                                                                                             |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Restrição Legal — Limite de Descontos [RN-10]**<br><br>CLT Art. 462: É vedado ao empregador efetuar qualquer desconto<br><br>nos salários do empregado, salvo quando este resultar de<br><br>adiantamentos, dispositivos de lei ou contrato coletivo.<br><br>Regra do sistema: Salário_Líquido ≥ 0,00<br><br>Se Σ Descontos > Σ Proventos → bloquear processamento<br><br>e exibir alerta ao operador. |

#### 3.2.4.5 Composição do Salário Líquido
**3.2.4.5 —** O sistema deve calcular o Salário Líquido como resultado final do processamento da folha, conforme RN-11.

|                                                                                                                                                                                                                                                                                                                   |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Fórmula — Salário Líquido [RN-11]**<br><br>Salário_Líquido = Σ Proventos − Σ Descontos<br><br>Onde:<br><br>Σ Proventos = Sal.Base_Prop. + HE + Bônus + Cesta_Básica + PLR<br><br>Σ Descontos = INSS + Faltas + Atrasos + DSR + Outros_Descontos<br><br>Restrição: Salário_Líquido ≥ R$ 0,00 (nunca negativo) |

### 3.2.5 - Encargos e Provisões Trabalhistas
Este módulo define os cálculos dos encargos sobre a folha (FGTS) e das provisões mensais para obrigações trabalhistas futuras (13º Salário e Férias). Esses valores não impactam o salário líquido do funcionário.
#### 3.2.5.1 FGTS — Fundo de Garantia do Tempo de Serviço
**3.2.5.1 —** O sistema deve calcular o FGTS mensal de cada funcionário com base na alíquota configurada (padrão 8%) aplicada sobre a Base de Cálculo FGTS.
	**3.2.5.1.1 —** A Base de Cálculo FGTS inclui: Salário Base Proporcional + Horas Extras + Bônus com incidência.
	**3.2.5.1.2 —** O FGTS é encargo do empregador e não é deduzido do salário líquido do funcionário.
	**3.2.5.1.3 —** O valor calculado de FGTS deve ser acumulado mensalmente para geração de relatório de encargos do período.

|                                                                                                                                                                                                                                                                                             |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Fórmula — FGTS Mensal [RN-12]**<br><br>FGTS_Mensal = Base_Cálculo_FGTS × 8%<br><br>Exemplo: Base FGTS = R$ 3.500,00<br><br>FGTS_Mensal = 3.500,00 × 0,08 = R$ 280,00<br><br>Multa rescisória (40% do FGTS acumulado) é provisionada<br><br>mas não calculada neste módulo — ver RN-14. |

#### 3.2.5.2 Provisão de 13º Salário
**3.2.5.2 —** O sistema deve calcular mensalmente a provisão de 13º Salário de cada funcionário.
	**3.2.5.2.1 —** A provisão mensal de 13º deve ser calculada conforme RN-13.
	**3.2.5.2.2 —** O sistema deve acumular a provisão de 13º desde o mês de admissão do funcionário, reiniciando o acumulado em janeiro de cada ano.
	**3.2.5.2.3 —** O relatório de provisões deve exibir, para cada funcionário, a provisão do mês corrente e o acumulado no ano.

|                                                                                                                                                                                                                                                                                                                                                                                       |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **Fórmula — Provisão Mensal de 13º Salário [RN-13]**<br><br>Provisão_13_Mês = Base_Cálculo_13 ÷ 12<br><br>Base_Cálculo_13 = Salário_Base + médias de HE habituais<br><br>(nesta versão MVP: Base = Salário_Base apenas)<br><br>Provisão_Acumulada_13 = Σ Provisão_13_Mês (jan a dez)<br><br>Exemplo: Salário Base = R$ 3.000,00<br><br>Provisão_13_Mês = 3.000,00 ÷ 12 = R$ 250,00 |

#### 3.2.5.3 Provisão de Férias e Adicional de ⅓
**3.2.5.3 —** O sistema deve calcular mensalmente a provisão de férias acrescida do adicional constitucional de ⅓ (um terço), CLT Art. 129 e Art. 7º, XVII da CF/88.
	**3.2.5.3.1 —** A provisão de férias deve ser calculada conforme RN-14.
	**3.2.5.3.2 —** O sistema deve acumular a provisão de férias desde a data de admissão do funcionário, reiniciando após 12 meses.

|                                                                                                                                                                                                                                                                                                                                                                                                                    |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| 📐 **Fórmula — Provisão Mensal de Férias + ⅓ [RN-14]**<br><br>ProvisãoFériasMês = (SalárioBase ÷ 12) × (4 ÷ 3)<br><br>Detalhado:<br><br>FériasMês = SalárioBase ÷ 12<br><br>Adicional⅓ = FériasMês ÷ 3<br><br>Provisãototal = FériasMês + Adicional_⅓<br><br>Exemplo: Salário Base = R$ 3.600,00<br><br>Férias_Mês = 3.600 ÷ 12 = R$ 300,00<br><br>Adicional = 300 ÷ 3 = R$ 100,00<br><br>Provisão = R$ 400,00/mês |

#### 3.2.5.4 Relatório de Encargos e Provisões
**3.2.5.4 —** O sistema deve gerar, ao fechar a folha, um relatório consolidado de encargos e provisões com os seguintes dados:
	**3.2.5.4.1 —** Por funcionário: FGTS do mês, Provisão 13º do mês, Provisão Férias + ⅓ do mês, e acumulados no ano.
	**3.2.5.4.2 —** Totais gerais da empresa: soma de FGTS, soma de Provisão 13º e soma de Provisão Férias + ⅓ de todos os funcionários ativos.
	**3.2.5.4.3 —** O relatório deve ser exportável em PDF.

### 3.2.6 Saídas e Relatórios
Este módulo define os artefatos gerados pelo sistema após o processamento da folha: holerites, relatórios de resumo, exportações e registros de auditoria. Todos os artefatos de folhas fechadas são somente leitura.
#### 3.2.6.1 Holerite Individual
**3.2.6.1 —** O sistema deve gerar o holerite individual de cada funcionário para cada mês de competência processado, exigidas pelo Art. 464 da CLT.
	**3.2.6.1.1 —** O holerite deve ser composto por três blocos obrigatórios:
		**3.2.6.1.1.1 —** Cabeçalho: Razão Social e CNPJ da empresa, Nome do funcionário, CPF mascarado, Cargo, Data de Admissão, e Mês/Ano de competência.
		**3.2.6.1.1.1 —** Corpo: Tabela de Proventos (código, descrição, referência/quantidade, valor) e Tabela de Descontos (igual a tabela de proventos). 
		**3.2.6.1.1.1 —** Rodapé: Total Bruto de Proventos, Total de Descontos, Salário Líquido, e linha de assinatura.
	**3.2.6.1.2 —** O holerite gerado em PDF deve ter nome de arquivo padronizado: **HOLERITE[CPF-sem-formatação][AAAAMM].pdf**
	**3.2.6.1.3 —** O sistema deve permitir a reimpressão/re-exportação do holerite de qualquer mês fechado, sem limite de período, sem alterar nenhum dado.
	
#### 3.2.6.2 Relatório de Resumo da Folha
**3.2.6.2 —** O sistema deve gerar, após o fechamento da folha, um relatório mensal contendo: Nome, Cargo, Salário Bruto, Total de Descontos, Salário Líquido e Status.
	**3.2.6.2.1 —** O relatório deve exibir totais gerais ao final: soma de Salários Brutos, soma de Descontos e soma de Salários Líquidos dos funcionários.
	**3.2.6.2.2 —** O relatório deve ser exportável em PDF (layout formatado) para importação em sistemas de contabilidade externos.

#### 3.2.6.3 Exportação para Pagamento Bancário
**3.2.6.3 —** O sistema deve gerar um arquivo de remessa simplificado, contendo: CPF, Nome, Banco, Agência, Conta e Valor Líquido.
	**3.2.6.3.1 —** O arquivo deve ser gerado em formato PDF estruturado, contendo os dados organizados em tabela legível, com suporte à codificação UTF-8.

#### 3.2.6.4 Histórico e Consulta de Folhas Anteriores
**3.2.6.4 —** O sistema deve manter o histórico completo de todas as folhas processadas e fechadas, sem limite de período de retenção.
	**3.2.6.4.1 —** Folhas fechadas devem ser acessíveis para consulta, mesmo que salários ou cadastros tenham sido alterados posteriormente.
	**3.2.6.4.2 —** O sistema deve implementar snapshot dos dados no momento do fechamento da folha: nome, salário base e rubricas utilizadas devem ser gravados junto à folha, não apenas referenciados, garantindo imutabilidade histórica. **NÃO ENTENDI**
	**3.2.6.4.3 —** A consulta ao histórico deve permitir filtro por intervalo de competências (mês/ano de até mês/ano) e por funcionário específico.

|   |
|---|
|📐 **Regra de Imutabilidade — Folha Fechada [RN-15]**<br><br>Após o fechamento:<br><br>→ Nenhum dado da folha pode ser alterado<br><br>→ Nenhuma rubrica pode ser adicionada ou removida<br><br>→ Nenhum lançamento de exceção pode ser editado<br><br>→ O banco de dados deve rejeitar qualquer UPDATE/DELETE<br><br>na tabela de folhas com status = 'FECHADA'<br><br>via constraint de banco de dados (trigger ou CHECK).|

#### 3.2.6.5 Log de Auditoria
**3.2.6.5 —** O sistema deve manter um log de auditoria imutável que registra todas as operações de escrita realizadas na aplicação.
	**3.2.6.5.1 —** Cada entrada do log deve conter: ID sequencial, data e hora, usuário, operação realizada (ex.: CADASTRO_FUNCIONARIO, FECHAMENTO_FOLHA, ALTERACAO_SALARIO), entidade afetada (tabela + ID do registro) e resumo da mudança (valor anterior e novo valor, quando aplicável). **DEVE SER COMPLICADO DE FAZER, REVER**
	**3.2.6.5.2 —** O log deve ser append-only: nenhum registro pode ser alterado ou excluído por nenhum usuário, incluindo o Administrador. 
	**3.2.6.5.3 —** O sistema deve disponibilizar tela de consulta ao log de auditoria, acessível somente Administrador, com filtros por período, usuário e tipo de operação.
	**3.2.6.5.4 —** Tentativas de login inválidas devem ser registradas no log de segurança, contendo: login informado, data e hora.
### 3.2.7 Quadro Consolidado de Regras de Negócio (RN)
A tabela abaixo consolida todas as regras de negócio derivadas da legislação e referenciadas nos requisitos desta seção, para facilitar rastreabilidade e testes.
  **TEM Q ARRUMAR ESSA FORMATAÇÃO DA TABELA**

| ID RN | Descrição Resumida                                                   | Base Legal                | Módulo |
| ----- | -------------------------------------------------------------------- | ------------------------- | ------ |
| RN-01 | Salário Base ≥ Piso Salarial Nacional (R$ 1.518,00 em 2025)          | Decreto 12.302/2024       | 3.2.1  |
| RN-02 | Valor_Dia = Salário_Base ÷ Dias_Úteis_do_Mês                         | CLT Art. 64               | 3.2.2  |
| RN-03 | Desconto por falta = Valor_Dia × Qtd_Dias (ou horas × Valor_Hora)    | CLT Art. 131              | 3.2.2  |
| RN-04 | Desconto por atraso = Valor_Hora × (min ÷ 60); tolerância 10 min/dia | CLT Art. 58 §1º           | 3.2.2  |
| RN-05 | DSR perdido por falta injustificada: 1 DSR por semana com falta      | Lei 605/1949              | 3.2.2  |
| RN-06 | HE 50% = VH × 1,5 × h; HE 100% = VH × 2,0 × h                        | CLT Art. 59               | 3.2.2  |
| RN-07 | Salário Proporcional = (Sal. Base ÷ Dias Úteis) × Dias Trabalhados   | CLT Art. 487              | 3.2.3  |
| RN-08 | Salário Bruto = Sal. Base Prop. + HE + Bônus (incid.) + Cesta + PLR  | CLT Art. 457              | 3.2.3  |
| RN-09 | INSS progressivo por faixa (tabela 2025); teto R$ 8.157,41           | Portaria MPS 2025         | 3.2.4  |
| RN-10 | Salário Líquido ≥ R$ 0,00; desconto não pode superar proventos       | CLT Art. 462              | 3.2.4  |
| RN-11 | Salário Líquido = Σ Proventos − Σ Descontos                          | CLT Art. 464              | 3.2.4  |
| RN-12 | FGTS = Base_FGTS × 8%; encargo do empregador                         | Lei 8.036/90 Art. 15      | 3.2.5  |
| RN-13 | Provisão 13º/mês = Salário_Base ÷ 12                                 | Lei 4.090/62              | 3.2.5  |
| RN-14 | Provisão Férias/mês = (Sal. Base ÷ 12) × (4/3)                       | CLT Art. 129 / CF Art. 7º | 3.2.5  |
| RN-15 | Folha fechada é imutável: nenhum dado pode ser alterado              | CLT Art. 464 / LGPD       | 3.2.6  |
## 3.3 Requisitos não Funcionais
### 3.3.1 Desempenho 
Os requisitos desta categoria estabelecem limites quantitativos para tempos de resposta, capacidade de processamento e uso de recursos computacionais. Todos os valores são medidos na configuração de hardware mínima especificada em 3.3.1.1, sob carga típica de operação.
**3.3.1.1 —** Configuração de hardware : processador dual-core 2,0 GHz (x64), 4 GB de RAM (2 GB disponíveis para a JVM), sistema operacional Windows 10 e disco de leitura.
**3.3.1.2 —** O sistema deve processar a folha de pagamento completa de até 50 funcionários ativos em no máximo 15 segundos.
	**3.3.1.2.1 —** Para folhas com 51 a 200 funcionários, o tempo máximo permitido é de 45 segundos.
**3.3.1.3 —** Operações de consulta e leitura devem retornar resultados e renderizar a tela em no máximo 3 segundos, com até 500 funcionários cadastrados (ativos e inativos).
**3.3.1.4 —** A geração e exportação do arquivo PDF de um holerite individual deve ser concluída em no máximo 5 segundos, com a abertura do diálogo de seleção de destino.
**3.3.1.6 —** A inicialização da aplicação deve ocorrer em no máximo 8 segundos na configuração de hardware de referência.
**3.3.1.8 —** O sistema deve suportar o cadastro de até 500 funcionários (ativos e inativos) e o armazenamento de até 60 meses de histórico de folhas .

| Operação                                       | Limite Máximo | Condição de Medição                         |
| ---------------------------------------------- | ------------- | ------------------------------------------- |
| Processamento da folha — até 50 funcionários   | 15 s          | Hardware de referência, carga normal        |
| Processamento da folha — 51 a 200 funcionários | 45 s          | Hardware de referência, carga normal        |
| Consultas e listagens                          | 3 s           | Base com até 500 funcionários               |
| Exportação de PDF (holerite individual)        | 5 s           | Arquivo local, disco de referência          |
| Inicialização da aplicação                     | 8 s           | Após SO já iniciado, hardware de referência |

### 3.3.2 Segurança (Security)
Os requisitos desta categoria protegem dados pessoais e salariais — classificados como dados sensíveis pela LGPD (Lei nº 13.709/2018) — e garantem que apenas usuários autorizados realizem operações no sistema. 

#### 3.3.2.1 Autenticação e Controle de Acesso
**3.3.2.1 —** O sistema deve exigir autenticação por login e senha em toda sessão. 
	**3.3.2.1.1 —** O sistema deve implementar controle de acesso baseado em perfil, garantindo que cada perfil acesse somente as funcionalidades autorizadas.
	**3.3.2.1.2 —** Após 30 minutos de inatividade do usuário, a sessão deve ser encerrada automaticamente e a tela de login deve ser reapresentada.
	**3.3.2.1.3 —** Após 3 tentativas consecutivas de login com senha incorreta para o mesmo usuário, a conta deve ser bloqueada temporariamente por 5 minutos.**Ver esse requisito**
		**3.3.2.1.3.1 -**  O sistema deve registrar o bloqueio no log de segurança com timestamp e nome do usuário.

|   |
|---|
|🔒 **Controle de Acesso — Regras Absolutas**<br><br>→ Usuário perfil 'Analista de RH' NÃO pode:<br><br>• Acessar tela de gestão de usuários<br><br>• Alterar parâmetros fiscais da empresa<br><br>• Visualizar ou exportar o log de auditoria<br><br>• Reabrir folha fechada<br><br>→ Nenhum usuário pode:<br><br>• Excluir registros de log de auditoria<br><br>• Alterar dados de folha com status FECHADA<br><br>• Visualizar senha de outro usuário (hash é irreversível)|
#### 3.3.2.2 Armazenamento de Credenciais
**3.3.2.2 —** As senhas de todos os usuários devem ser armazenadas no banco de dados exclusivamente como hash bcrypt, com fator de custo:
	**3.3.2.2.1 —** Mínimo de 10, equivalente a aproximadamente 100 ms de processamento por tentativa de autenticação na configuração de hardware de referência.
	**3.3.2.2.1 —** Nenhuma senha deve ser armazenada, transmitida ou exibida em texto plano em qualquer circunstância.
	**3.3.2.2.2 —** O sistema não deve implementar funcionalidade de "recuperar senha" que exiba ou envie a senha atual — apenas redefinição com nova senha via Administrador.
	**3.3.2.2.3 —** As novas senhas criadas devem atender: 
		**3.3.2.2.3 —** Mínimo de 8 caracteres, 
		**3.3.2.2.3 —** Ao menos uma letra maiúscula, 
		**3.3.2.2.3 —** Ao menos uma letra minúscula, 
		**3.3.2.2.3 —** Ao menos um dígito numérico. 
		**3.3.2.2.3 —** O sistema deve validar.

#### 3.3.2.3 Proteção de Dados Pessoais (LGPD)
**3.3.2.3 —** O sistema deve tratar dados pessoais e salariais dos funcionários conforme os princípios da LGPD (Lei nº 13.709/2018), garantindo controle de acesso e rastreabilidade.
	**3.3.2.3.1 —** Campos de CPF devem ser exibidos mascarados (***.***.XXX-XX) em todas as telas de listagem e consulta. 
	**3.3.2.3.1 —** O CPF completo deve ser visível somente nas telas de cadastro e edição individual, e somente para o perfil Administrador e Analista de RH autenticados.
	**3.3.2.3.2 —** Dados de salário devem ser visíveis somente para usuários autenticados com perfil autorizado. 
	**3.3.2.3.3 —** O sistema deve registrar em log de auditoria toda exportação de dados (PDF), identificando o usuário, data, hora e tipo de exportação realizada.
	**3.3.2.3.4 —** O sistema não deve transmitir dados de funcionários para qualquer servidor externo. 

|   |
|---|
|📋 **Base Legal — LGPD (Lei nº 13.709/2018)**<br><br>Art. 6º — Princípios do tratamento de dados: finalidade,<br><br>adequação, necessidade, livre acesso, qualidade dos dados,<br><br>transparência, segurança, prevenção, não discriminação.<br><br>Art. 46 — Agentes de tratamento devem adotar medidas de<br><br>segurança técnicas e administrativas aptas a proteger<br><br>dados pessoais de acessos não autorizados.<br><br>Art. 50 — Boa prática: documentar e implementar programa<br><br>de governança de dados pessoais.|

#### 3.3.2.4 Comunicação e Armazenamento Seguro
**3.3.2.4 —** A comunicação entre a aplicação desktop e o banco de dados em deve ser em rede local .
	**3.3.2.4.1 —** Strings de conexão com o banco de dados (incluindo usuário e senha do SGBD) não devem ser armazenadas em texto plano em arquivos de configuração. 
	**3.3.2.4.2 —** Arquivos de backup do banco de dados gerados pelo sistema (ver 3.3.3.5) devem ser armazenados em diretório com permissões de leitura restritas ao usuário do sistema operacional que executa a aplicação.
#### 3.3.2.5 Registro de Segurança
**3.3.2.5 —** O sistema deve manter um log de segurança separado do log de auditoria funcional, registrando exclusivamente eventos de acesso e autenticação.
	**3.3.2.5.1 —** O log de segurança deve registrar: login bem-sucedido ou falho, bloqueio de conta, encerramento de sessão e redefinição de senha.
	**3.3.2.5.2 —** Cada entrada deve conter: timestamp (data e hora), tipo de evento, nome de usuário informado e identificador da estação de trabalho (endereço IP local).
	**3.3.2.5.3 —** O log de segurança deve ser somente-inserção (append-only), sem interface de exclusão disponível para nenhum perfil de usuário.

### 3.3.3 Confiabilidade (Reliability)
Os requisitos desta categoria garantem que o sistema produza resultados corretos, consistentes e recuperáveis, mesmo diante de falhas. A confiabilidade é crítica para um sistema de folha de pagamento, visto que erros de cálculo têm impacto legal e financeiro direto.
**3.3.3.1 —** Todos os cálculos monetários devem ser realizados internamente com precisão mínima de 2 (duas) casas decimais. 

|                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| 📏 **Precisão de Cálculo — Critério de Aceitação**<br><br>Tipo interno: java.math.BigDecimal (escala mínima: 4 dígitos)<br><br>Arredondamento: RoundingMode.HALF_UP (ex.: R$ 136,365 → R$ 136,37)<br><br>NÃO usar: double, float ou int para valores monetários<br><br>Teste: processar folha com valores conhecidos e comparar<br><br>resultado com cálculo manual — desvio admitido: R$ 0,01<br><br>(diferença de centavo por arredondamento é aceitável). |

**3.3.3.2 —** O sistema deve garantir que o resultado do processamento da folha seja idempotente.
**3.3.3.3 —** O sistema deve utilizar transações de banco de dados com propriedades ACID para todas as operações de escrita relacionadas ao processamento da folha. 
**3.3.3.3 —** Em caso de erro, o sistema deve executar rollback completo dos dados desse funcionário sem afetar os demais já processados com sucesso.
	**3.3.3.3.1 —** Ao final de cada processamento:— com sucesso ou com erro parcial — o sistema deve exibir relatório de resultado discriminando:
		**3.3.3.3.1 —** Funcionários processados com sucesso;
		**3.3.3.3.1 —** Funcionários com erro (com descrição do motivo);
		**3.3.3.3.1 —** Funcionários não processados.
**3.3.3.4 —** O sistema deve validar a integridade dos dados antes de iniciar o processamento da folha. 
	**3.3.3.4 —** Deve listar todos os problemas encontrados em tela de pré-validação e impedir o processamento até que sejam corrigidos.
**3.3.3.5 —** O sistema deve gerar automaticamente um backup do banco de dados ao término de cada fechamento, antes de alterar o status da folha para "Fechada".
	**3.3.3.5.2 —** O sistema deve alertar caso o diretório configurado não exista ou não tenha permissão de escrita.
	**3.3.3.5.1 —** O arquivo de backup deve seguir a nomenclatura: **SFPCLT_BACKUP_[AAAAMM]-[YYYYMMDD_HHMMSS]
3.3.3.6 —** O sistema deve detectar e tratar as seguintes condições de erro de infraestrutura, exibindo mensagem clara ao usuário:
	**3.3.3.6 —**  Perda de conexão com o banco de dados durante operação: exibir mensagem, aguardar 30 segundos e tentar reconexão automática antes de encerrar.
	**3.3.3.6 —**  Falta de espaço em disco durante exportação de PDF: exibir mensagem específica indicando o problema e o caminho de destino. **VER ISSO**
	**3.3.3.6 —**  Falha de impressora durante impressão de holerite: exibir mensagem de erro com opção de tentar novamente ou cancelar, sem travar a aplicação. **VER ISSO**
**3.3.3.7 —** Toda operação de fechamento de folha deve ser registrada em log de auditoria com: 
	**3.3.3.7 —** data/hora de conclusão;
	**3.3.3.7 —** usuário responsável;
	**3.3.3.7 —** quantidade de funcionários processados; 
	**3.3.3.7 —** hash SHA-256 do arquivo de backup gerado.
**3.3.3.9 —** Em caso de encerramento inesperado da aplicação, os dados de lançamentos em andamento  devem ser preservados, com notificação ao usuário no próximo acesso.

### 3.3.4 Usabilidade (Usability)
Os requisitos desta categoria garantem que a interface seja eficiente, compreensível e tolerante a erros para o perfil de usuário definido.
**3.3.4.1 —** Um usuário recém-treinado no sistema deve ser capaz de executar o ciclo completo de fechamento de folha de uma folha de 10 funcionários.

|   |
|---|
|✅ **Critério de Aceitação — Teste de Usabilidade**<br><br>Metodologia: teste com 3 usuários reais do perfil Analista de RH<br><br>Tarefa: folha de 10 funcionários com 2 HE, 1 falta e 1 atestado<br><br>Critério de aprovação: ≥ 2 de 3 usuários concluem em ≤ 20 min<br><br>sem ajuda e sem cometer erros irreversíveis.|

**3.3.4.3 —** Mensagens de erro de validação devem ser exibidas próximas ao campo inválido com texto em linguagem clara, sem termos técnicos ou códigos de exceção Java. 

|   |
|---|
|✅ **Exemplos de Mensagens de Erro Adequadas vs. Inadequadas**<br><br>✗ INADEQUADO: 'java.lang.NumberFormatException at line 247'<br><br>✗ INADEQUADO: 'Erro de validação no campo ID=3'<br><br>✓ ADEQUADO: 'CPF inválido. Verifique os dígitos e tente novamente.'<br><br>✓ ADEQUADO: 'Salário base deve ser no mínimo R$ 1.518,00 (piso 2025).'<br><br>✓ ADEQUADO: 'Data de admissão não pode ser futura.'|

**3.3.4.4 —** Operações destrutivas ou irreversíveis devem sempre ser precedidas de diálogo de confirmação modal com dois botões claramente distintos: confirmação e cancelamento.
	**3.3.4.4.1 —** Exemplos de operações que exigem confirmação modal: fechamento de folha, exclusão de lançamento de exceção, redefinição de senha de usuário.
**3.3.4.6 —** O sistema deve suportar navegação completa por teclado em todos os formulários, seguindo a ordem lógica dos campos.
**3.3.4.7 —** O sistema deve manter consistência visual e comportamental em toda a aplicação: mesmos padrões de cores para status.
**3.3.4.8 —** O sistema deve exibir no rodapé ou cabeçalho de cada tela: o nome do usuário logado, o perfil de acesso ativo e o mês de competência corrente.

### 3.3.5 Disponibilidade (Availability)
**3.3.5.1 —** O sistema deve estar operacional e disponível para uso durante todo o horário de expediente da empresa.

|                                                                                                                                                                                                                                                                                                                                                     |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📏 **Cálculo de Disponibilidade — Janela de Operação**<br><br>Janela mensal: 13h/dia × 22 dias úteis = 286 horas/mês<br><br>Meta de disponibilidade: 99,0%<br><br>Tempo máximo de indisponibilidade NÃO planejada: ~171 min/mês<br><br>Manutenções planejadas: não contabilizadas no cálculo,<br><br>desde que comunicadas com 24h de antecedência. |

**3.3.5.2 —** Manutenções programadas devem ser comunicadas aos usuários com antecedência de 24 horas e preferencialmente agendadas fora do horário de expediente.
**3.3.5.4 —** Em caso de indisponibilidade do banco de dados, o sistema deve exibir mensagem de erro clara, indicando que o banco de dados está inacessível.

###  3.3.6 Manutenibilidade (Maintainability)
**3.3.6.1 —** O sistema deve ser estruturado obrigatoriamente em arquitetura de três camadas separadas e independentes:
	**3.3.6.1 —** Camada de Apresentação (View);
	**3.3.6.1 —** Camada de Negócio (Service/Domain);
	**3.3.6.1 —** Camada de Persistência (Repository/DAO).
**3.3.6.2 —** Todas as tabelas de parâmetros legais sujeitas a atualização anual devem ser armazenadas como dados configuráveis. São obrigatoriamente configuráveis:
	**3.3.6.2 —** Tabela de faixas e alíquotas de INSS (atualizada por portaria ministerial anual).
	**3.3.6.2 —** Valor do salário mínimo nacional (Decreto presidencial anual).
	**3.3.6.2 —** Alíquota de FGTS (Lei 8.036/90, Art. 15 — atualmente 8%).
	**3.3.6.2 —** Percentuais de horas extras padrão (CLT Art. 59 — 50% e 100%).
	**3.3.6.2.1 —** A atualização desses parâmetros pelo Administrador não deve exigir parada do sistema, recompilação de código ou intervenção técnica.

|   |
|---|
|✅ **Exemplo — Atualização de Tabela INSS sem deploy**<br><br>Cenário: Portaria MPS atualiza faixas de INSS em janeiro de 2027.<br><br>Ação esperada: Administrador acessa 'Configurações > Tabela INSS',<br><br>edita as faixas e alíquotas na interface, salva e o sistema<br><br>já usa os novos valores no próximo processamento.<br><br>NÃO é necessário: editar código-fonte, recompilar ou reinstalar.|

**3.3.6.3 —** Os testes devem ser automatizados e executáveis via ferramenta de build (Maven ou Gradle).
**3.3.6.6 —** O sistema deve gerar logs de aplicação, em arquivo de texto rotativo, com nomenclatura sfpclt-YYYY-MM-DD.log, retendo os últimos 30 dias de histórico. 
	**3.3.6.6 —** Os logs devem incluir: timestamp, nível, classe de origem e mensagem descritiva. Logs de nível ERROR devem incluir stack trace completo.
	**3.3.6.6.1 —** Logs de aplicação são distintos dos logs de auditoria funcional (Seção 3.2.6.5) e dos logs de segurança (Seção 3.3.2.5). 
**3.3.6.7 —** Cada módulo do sistema (cadastro, processamento, relatórios) deve ser desenvolvido como componente independente. 
**3.3.6.8 —** O sistema deve incluir documentação técnica mínima: diagrama de arquitetura em camadas, instruções de instalação, configuração e atualização.
### 3.3.7 Portabilidade (Portability)
**3.3.7.1 —** O sistema deve executar nos seguintes sistemas operacionais, com JVM 11 ou superior instalada:
	**3.3.7.1 —** Windows 10 (64 bits) e Windows 11.
	**3.3.7.1 —** Ubuntu Linux 20.04 LTS e 22.04 LTS (64 bits).
	**3.3.7.1 —** macOS 12 (Monterey) ou superior, arquitetura Intel x64 e Apple Silicon (ARM64 via JVM compatível).
**3.3.7.2 —** A instalação inicial pode requerer privilégios elevados, mas a operação diária deve funcionar com conta de usuário padrão.
**3.3.7.3 —** O sistema deve ser empacotado para distribuição como instalador nativo para cada plataforma suportada, gerado com a ferramenta jpackage do JDK:
	**3.3.7.3 —** Windows: arquivo instalador .msi ou .exe com JRE embutido (bundled JRE), eliminando dependência de JVM instalada previamente pelo usuário.
	**3.3.7.3 —** Linux: pacote .deb (Debian/Ubuntu) ou .rpm (RedHat/Fedora) com JRE embutido.
	**3.3.7.3 —**  macOS: arquivo .dmg com aplicativo .app e JRE embutido.
**3.3.7.4 —** Nesta versão, apenas o idioma Português Brasileiro (pt-BR) é suportado.
**3.3.7.5 —** PDF deve ser compatível com: Microsoft Excel 2016+, LibreOffice Calc 7+, Adobe Acrobat Reader DC e leitores de PDF padrão do sistema operacional.
### 3.3.8 Conformidade Legal e Regulatória
**3.3.8.1 —** O sistema deve implementar os cálculos de folha de pagamento em conformidade estrita com a legislação CLT vigente.
	**3.3.8.1 —** Usando os valores de tabelas (INSS, piso salarial, horas extras) válidos para aquele ano — não os do ano corrente.

|   |
|---|
|📋 **Base Legal — Cálculos da Folha de Pagamento**<br><br>CLT Decreto-Lei 5.452/1943 e alterações:<br><br>Art. 58 — Duração normal do trabalho (8h/dia, 44h/semana)<br><br>Art. 59 — Horas extras (limite 2h/dia, adicional mínimo 50%)<br><br>Art. 64 — Cálculo do salário-dia<br><br>Art. 129 — Férias anuais (30 dias corridos)<br><br>Art. 457 — Composição da remuneração<br><br>Art. 462 — Vedação a descontos não autorizados<br><br>Art. 464 — Recibo de pagamento (holerite obrigatório)<br><br>Lei 8.036/1990 — FGTS (8% sobre remuneração mensal)<br><br>Lei 4.090/1962 — Gratificação natalina (13º Salário)<br><br>Lei 605/1949 — Repouso Semanal Remunerado (DSR)|

**3.3.8.2 —** O sistema deve garantir a imutabilidade contábil das folhas de pagamento já encerradas.
**3.3.8.3 —** O holerite gerado pelo sistema deve conter todas as informações exigidas pelo Art. 464 da CLT para ter validade como recibo de pagamento.
**3.3.8.4 —** O sistema deve manter registros de folhas fechadas pelo prazo mínimo de 5 anos, determinado pelo Art. 11 da CLT e pelo Art. 195 do Código Tributário Nacional.
**3.3.8.5 —** O sistema deve estar preparado para receber atualizações de tabelas legais (INSS, piso salarial) em até 5 dias úteis após a publicação oficial.
**3.3.8.6 —** O sistema deve exibir, em local visível na tela de processamento da folha, o ano de referência das tabelas legais utilizadas no cálculo.
### 3.3.9 Quadro Consolidado de Requisitos Não Funcionais
A tabela abaixo resume todos os requisitos não funcionais com seus critérios de aceitação objetivos, facilitando a elaboração do plano de testes de homologação.

| ID RNF    | Categoria        | Critério de Aceitação (verificável)                                | Prioridade |
| --------- | ---------------- | ------------------------------------------------------------------ | ---------- |
| 3.3.1.2   | Desempenho       | Folha 50 func. processada em ≤ 15 s                                | Alta       |
| 3.3.1.3   | Desempenho       | Consultas retornam em ≤ 3 s (base 500 func.)                       | Alta       |
| 3.3.1.4   | Desempenho       | Exportação PDF holerite em ≤ 5 s                                   | Média      |
| 3.3.1.6   | Desempenho       | Inicialização da aplicação em ≤ 8 s                                | Média      |
| 3.3.1.7   | Desempenho       | RAM ≤ 512 MB (normal) \| ≤ 1 GB (pico de processamento)            | Média      |
| 3.3.2.1   | Segurança        | Nenhum acesso sem autenticação; perfis isolados                    | Alta       |
| 3.3.2.1.3 | Segurança        | Bloqueio de conta após 3 tentativas em 5 min                       | Alta       |
| 3.3.2.1.2 | Segurança        | Sessão encerrada após 30 min de inatividade                        | Alta       |
| 3.3.2.2   | Segurança        | Senhas em bcrypt com custo ≥ 10; sem armazenamento em texto plano  | Alta       |
| 3.3.2.3.1 | Segurança        | CPF mascarado em listagens; conformidade com LGPD                  | Alta       |
| 3.3.3.1   | Confiabilidade   | Uso de BigDecimal (4 casas); arredondamento HALF_UP                | Alta       |
| 3.3.3.2   | Confiabilidade   | Processamento idempotente (mesmo resultado em execuções repetidas) | Alta       |
| 3.3.3.3   | Confiabilidade   | Transações ACID com rollback por funcionário                       | Alta       |
| 3.3.3.5   | Confiabilidade   | Backup automático a cada fechamento de folha                       | Alta       |
| 3.3.4.1   | Usabilidade      | Usuário treinado executa ciclo completo em ≤ 20 min                | Média      |
| 3.3.4.3   | Usabilidade      | Mensagens de erro claras, sem exposição de stack trace             | Média      |
| 3.3.5.1   | Disponibilidade  | Disponibilidade ≥ 99% no horário de expediente                     | Alta       |
| 3.3.6.1   | Manutenibilidade | Arquitetura em 3 camadas (View/Service/DAO)                        | Alta       |
| 3.3.6.2   | Manutenibilidade | Tabelas legais configuráveis no BD sem necessidade de recompilação | Alta       |
| 3.3.6.3   | Manutenibilidade | Cobertura de testes unitários ≥ 70% na camada de negócio           | Média      |
| 3.3.6.6   | Manutenibilidade | Logs rotativos com retenção de 30 dias                             | Média      |
| 3.3.7.1   | Portabilidade    | Execução em Windows 10/11, Ubuntu 20/22 e macOS 12+                | Alta       |
| 3.3.8.2   | Conformidade     | Folha fechada imutável na aplicação e no banco de dados            | Alta       |
| 3.3.8.4   | Conformidade     | Retenção mínima de 5 anos para folhas fechadas                     | Alta       |
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
## **4.1 Tabela de Rubricas Padrão**
**Grupo A — Proventos**

| Cód. | Descrição              | Tipo     | Base INSS | Base FGTS | Observação                                                      |
| ---- | ---------------------- | -------- | --------- | --------- | --------------------------------------------------------------- |
| 001  | Salário Base           | Fixo     | Sim       | Sim       | Proporcional na admissão/demissão no mês                        |
| 002  | Hora Extra 50%         | Variável | Sim       | Sim       | Adicional mínimo de 50% sobre o valor-hora — CLT Art. 59        |
| 003  | Hora Extra 100%        | Variável | Sim       | Sim       | Aplicável a domingos, feriados ou acordo coletivo — CLT Art. 59 |
| 004  | Bônus / Gratificação   | Variável | Sim       | Sim       | Incidência conforme configuração individual da rubrica          |
| 005  | Cesta Básica           | Fixo     | Não       | Não       | Natureza indenizatória; valor configurável na empresa           |
| 006  | PLR                    | Variável | Não       | Não       | Lei 10.101/2000; não integra base de encargos                   |
| 007  | DSR Remunerado         | Fixo     | Sim       | Sim       | Descanso Semanal Remunerado — Lei 605/1949                      |
| 008  | Adiantamento Quinzenal | Variável | Não       | Não       | Abatido no fechamento como desconto (rubrica 105)               |

**Grupo B — Descontos**

| Cód. | Descrição                  | Tipo       | Cálculo (referência)          | Observação                                   |
| ---- | -------------------------- | ---------- | ----------------------------- | -------------------------------------------- |
| 101  | Falta Injustificada        | Variável   | Valor_Dia × Nº dias           | Afeta também o DSR da semana — CLT Art. 131  |
| 102  | Atraso                     | Variável   | Valor_Hora × (min ÷ 60)       | Tolerância de 10 min/dia — CLT Art. 58 §1º   |
| 103  | INSS                       | Automático | Tabela progressiva 2025       | Contribuição previdenciária — Lei 8.212/91   |
| 104  | DSR Perdido                | Variável   | Valor_Dia × Nº DSR perdidos   | 1 DSR por semana com falta injustificada     |
| 105  | Abatimento de Adiantamento | Variável   | Valor adiantado (rubrica 008) | Deduzido no fechamento do mês                |
| 106  | IRRF                       | Automático | Tabela progressiva (fora MVP) | Reservado; exibido como R$ 0,00 nesta versão |

**Grupo C — Encargos e Provisões (responsabilidade do empregador)**

| Cód. | Descrição             | Periodicidade | Cálculo (referência)        | Observação                                            |
| ---- | --------------------- | ------------- | --------------------------- | ----------------------------------------------------- |
| 201  | FGTS Mensal           | Mensal        | Base_FGTS × 8%              | Depósito em conta vinculada — Lei 8.036/90            |
| 202  | Provisão 13º Salário  | Mensal        | Salário_Base ÷ 12           | Acumulado de jan–dez; pago em nov/dez — Lei 4.090/62  |
| 203  | Provisão Férias + 1/3 | Mensal        | (Salário_Base ÷ 12) × (4/3) | Período aquisitivo de 12 meses — CLT Art. 129         |
| 204  | Multa FGTS 40%        | Rescisão      | FGTS_Acumulado × 40%        | Provisionada; paga apenas na demissão sem justa causa |

|   |
|---|
|📌 **Rubricas personalizadas (código ≥ 500)**<br><br>O Administrador pode criar rubricas para benefícios ou descontos<br><br>específicos da empresa. Campos obrigatórios:<br><br>• Código único (numérico, ≥ 500)<br><br>• Descrição (máx. 60 caracteres)<br><br>• Natureza: Provento ou Desconto<br><br>• Tipo: Fixo ou Variável<br><br>• Flags de incidência: incide INSS? \| incide FGTS? \| incide IRRF?|

## **4.2 Modelos de Fórmulas de Cálculo**
### 4.2.1 Apuração de Frequência

|                                                                                                                                                                                                                      |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-01 — Valor do Dia de Trabalho [RN-02]**<br><br>ValorDia = SalárioBase ÷ Dias_Úteis_do_Mês<br><br>Exemplo: Salário Base = R$ 3.300,00 \| Dias úteis de março = 21<br><br>Valor_Dia = 3.300,00 ÷ 21 = R$ 157,14 |

|                                                                                                                                                                                                                                                                  |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-02 — Valor da Hora de Trabalho**<br><br>Valor_Hora = Salário_Base ÷ Horas_Mensais_Contratuais<br><br>(padrão: 220 horas = 8h/dia × 22 dias médios — CLT Art. 58)<br><br>Exemplo: Salário Base = R$ 3.300,00<br><br>Valor_Hora = 3.300,00 ÷ 220 = R$ 15,00 |

|                                                                                                                                                                                                                                                                                          |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-03 — Desconto por Falta Injustificada [RN-03]**<br><br>Desconto_Falta_Dia = Valor_Dia × Qtd_Dias_Faltados<br><br>Desconto_Falta_Horas = Valor_Hora × Qtd_Horas_Faltadas<br><br>Exemplo: 2 faltas inteiras \| Valor_Dia = R$ 157,14<br><br>Desconto_Falta = 157,14 × 2 = R$ 314,28 |

|                                                                                                                                                                                                                                                                                                                                      |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| 📐 **F-04 — Desconto por Atraso (tolerância: 10 min/dia) [RN-04]**<br><br>Desconto_Atraso = Valor_Hora × (Total_Minutos_Excedentes ÷ 60)<br><br>Exemplo: atraso de 45 min \| Valor_Hora = R$ 15,00<br><br>Desconto_Atraso = 15,00 × (45 ÷ 60) = R$ 11,25<br><br>Obs.: minutos dentro da tolerância de 10 min/dia não geram desconto. |

|                                                                                                                                                                                                                                        |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-05 — Desconto de DSR Perdido [RN-05]**<br><br>Desconto_DSR = Valor_Dia × Nº_Semanas_com_Falta_Injustificada<br><br>Exemplo: 2 faltas em semanas distintas \| Valor_Dia = R$ 157,14<br><br>Desconto_DSR = 157,14 × 2 = R$ 314,28 |

|                                                                                                                                                                                                                                                                                                                           |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-06 — Horas Extras [RN-06]**<br><br>HE_50% = Valor_Hora × 1,50 × Qtd_Horas<br><br>HE_100% = Valor_Hora × 2,00 × Qtd_Horas<br><br>Exemplo: 3h de HE 50% + 1h de HE 100% \| Valor_Hora = R$ 15,00<br><br>HE_50% = 15,00 × 1,50 × 3 = R$ 67,50<br><br>HE_100% = 15,00 × 2,00 × 1 = R$ 30,00<br><br>Total HE = R$ 97,50 |

### 4.2.2 Composição do Salário

|                                                                                                                                                                                                                                                                                 |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-07 — Salário Proporcional [RN-07]**<br><br>Salário_Proporcional = (Salário_Base ÷ Dias_Úteis) × Dias_Trabalhados<br><br>Exemplo: Admissão em 10/03 \| Dias úteis de março = 21 \| Dias trabalhados = 16<br><br>Salário_Proporcional = (4.400,00 ÷ 21) × 16 = R$ 3.352,38 |

|                                                                                                                                                                                                                                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-08 — Salário Bruto [RN-08]**<br><br>Salário_Bruto = Salário_Base_Prop.<br><br>+ Total_Horas_Extras<br><br>+ Total_Bônus_com_Incidência<br><br>+ Cesta_Básica (NÃO incide INSS/FGTS)<br><br>+ PLR (NÃO incide INSS/FGTS)<br><br>Base_INSS_FGTS = Sal.Base_Prop. + HE + Bônus_com_Incidência |

|                                                                                                                                                                                                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-09 — Salário Líquido [RN-11]**<br><br>Salário_Líquido = Σ Proventos − Σ Descontos<br><br>Σ Proventos = Sal.Base_Prop. + HE + Bônus + Cesta + PLR + DSR<br><br>Σ Descontos = INSS + Faltas + Atrasos + DSR_Perdido + Outros<br><br>Restrição legal: Salário_Líquido ≥ R$ 0,00 (CLT Art. 462 [RN-10]) |

### 4.2.3 Desconto de INSS — Tabela Progressiva 2025

|Faixa|De (R$)|Até (R$)|Alíquota|Observação|
|---|---|---|---|---|
|1ª|0,01|1.518,00|7,5%||
|2ª|1.518,01|2.793,88|9,0%||
|3ª|2.793,89|4.190,83|12,0%||
|4ª|4.190,84|8.157,41|14,0%||
|Teto|8.157,42|—|—|Contribui até o limite de R$ 8.157,41|

|   |
|---|
|✅ **Cálculo INSS progressivo — Base de cálculo R$ 5.000,00**<br><br>1ª faixa: R$ 1.518,00 × 7,5% = R$ 113,85<br><br>2ª faixa: R$ 1.275,88 × 9,0% = R$ 114,83 (2.793,88 − 1.518,00)<br><br>3ª faixa: R$ 1.396,95 × 12,0% = R$ 167,63 (4.190,83 − 2.793,88)<br><br>4ª faixa: R$ 809,17 × 14,0% = R$ 113,28 (5.000,00 − 4.190,83)<br><br>─────────────────────────────────────────────<br><br>TOTAL INSS = R$ 509,59 \| Alíquota efetiva: 10,19%|

### 4.2.4 Encargos e Provisões do Empregador

|                                                                                                                                                                                                                                              |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-10 — FGTS Mensal [RN-12]**<br><br>FGTS_Mensal = Base_FGTS × 8%<br><br>Exemplo: Base FGTS = R$ 3.500,00<br><br>FGTS_Mensal = 3.500,00 × 0,08 = R$ 280,00<br><br>Obs.: encargo do empregador; NÃO é deduzido do salário do funcionário. |

|                                                                                                                                                                                                                                                                     |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-11 — Provisão Mensal de 13º Salário [RN-13]**<br><br>Provisão_13_Mês = Salário_Base ÷ 12<br><br>Provisão_13_Acumulada = Σ Provisão_13_Mês (janeiro a dezembro)<br><br>Exemplo: Salário Base = R$ 3.600,00<br><br>Provisão_13_Mês = 3.600,00 ÷ 12 = R$ 300,00 |

|                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 📐 **F-12 — Provisão Mensal de Férias + ⅓ Constitucional [RN-14]**<br><br>ProvisãoFériasMês = (SalárioBase ÷ 12) × (4 ÷ 3)<br><br>Detalhado:<br><br>FériasMês = SalárioBase ÷ 12<br><br>Adicional⅓ = FériasMês ÷ 3<br><br>ProvisãoTotal = FériasMês + Adicional⅓<br><br>Exemplo: Salário Base = R$ 3.600,00<br><br>Férias_Mês = 3.600 ÷ 12 = R$ 300,00<br><br>Adicional = 300 ÷ 3 = R$ 100,00<br><br>Provisão = R$ 400,00 / mês |

## **4.3 Glossário Expandido**

|Termo / Sigla|Definição|Base Legal / Ref.|
|---|---|---|
|Competência|Mês e ano de referência da folha de pagamento.|—|
|Folha Aberta|Folha do mês corrente cujos lançamentos ainda podem ser editados.|—|
|Folha Fechada|Folha encerrada e imutável após o fechamento.|CLT Art. 464|
|Snapshot|Cópia imutável dos dados da folha gravada no fechamento.|—|
|Admissão Proporcional|Cálculo do salário do mês de entrada, proporcional aos dias trabalhados.|CLT Art. 487|
|DSR|Descanso Semanal Remunerado. Um dia de descanso por semana pago.|Lei 605/1949|
|INSS|Instituto Nacional do Seguro Social. Contribuição previdenciária.|Lei 8.212/91|
|IRRF|Imposto de Renda Retido na Fonte. Fora do escopo do MVP.|Lei 7.713/88|
|PLR / PRL|Participação nos Lucros e Resultados. Não integra base de encargos.|Lei 10.101/2000|
|13º Salário|Gratificação natalina obrigatória, paga em novembro e dezembro.|Lei 4.090/1962|
|Férias + 1/3|30 dias de férias anuais acrescidas de 1/3 de adicional constitucional.|CLT Art. 129 / CF Art. 7º|
|FGTS|Fundo de Garantia do Tempo de Serviço. Depósito mensal de 8%.|Lei 8.036/1990|
|Valor-Dia|Salário Base dividido pelo número de dias úteis do mês.|CLT Art. 64|
|Valor-Hora|Salário Base dividido pelas horas mensais contratuais (padrão 220h).|CLT Art. 58|
|ACID|Atomicidade, Consistência, Isolamento e Durabilidade (transações de BD).|—|
|RBAC|Role-Based Access Control. Controle de acesso baseado em perfil.|—|
|JVM|Java Virtual Machine. Ambiente de execução Java (versão 11+).|—|
|LGPD|Lei Geral de Proteção de Dados Pessoais.|Lei 13.709/2018|
|BigDecimal|Tipo Java de precisão arbitrária para cálculos monetários.|—|
|bcrypt|Algoritmo de hash de senha com custo configurável (mínimo 10).|OWASP / RNF 3.3.2|

## **4.4 Matriz de Rastreabilidade — Requisitos × Módulos × Telas**

|Req. Funcional|Módulo|Tela GUI|Regras de Negócio|
|---|---|---|---|
|3.2.1 — Cadastros|Cadastros e Configurações|3.1.4 / 3.1.5 / 3.1.10|RN-01|
|3.2.2 — Apuração de Frequência|Apuração de Frequência|3.1.6|RN-02, RN-03, RN-04, RN-05, RN-06|
|3.2.3 — Proventos|Processamento de Proventos|3.1.7|RN-07, RN-08|
|3.2.4 — Descontos|Processamento de Descontos|3.1.7|RN-09, RN-10, RN-11|
|3.2.5 — Encargos / Provisões|Encargos e Provisões|3.1.7 (relatório)|RN-12, RN-13, RN-14|
|3.2.6 — Saídas e Relatórios|Saídas e Auditoria|3.1.8 / 3.1.9|RN-15|
## **4.5 Tabela Consolidada de Regras de Negócio (RN-01 a RN-15)**

|ID|Descrição|Fórmula / Critério|Base Legal|
|---|---|---|---|
|RN-01|Piso salarial|Salário Base ≥ R$ 1.518,00 (2025)|Decreto 12.302/2024|
|RN-02|Valor-Dia|Salário Base ÷ Dias Úteis|CLT Art. 64|
|RN-03|Desconto por Falta|Valor_Dia × Quantidade de Dias|CLT Art. 131|
|RN-04|Desconto por Atraso|Valor_Hora × (min ÷ 60); tolerância 10 min|CLT Art. 58 §1º|
|RN-05|DSR perdido|Valor_Dia × Nº de semanas com falta|Lei 605/1949|
|RN-06|Hora extra 50% / 100%|VH × 1,5 × h / VH × 2,0 × h|CLT Art. 59|
|RN-07|Salário proporcional|(Salário Base ÷ Dias Úteis) × Dias Trabalhados|CLT Art. 487|
|RN-08|Salário Bruto|Soma dos proventos com incidência|CLT Art. 457|
|RN-09|INSS progressivo|Tabela 2025 (teto R$ 8.157,41)|Portaria MPS 2025|
|RN-10|Salário Líquido ≥ R$ 0,00|Soma dos proventos ≥ soma dos descontos|CLT Art. 462|
|RN-11|Salário Líquido|Soma dos proventos − soma dos descontos|CLT Art. 464|
|RN-12|FGTS Mensal|Base_FGTS × 8%|Lei 8.036/90 Art. 15|
|RN-13|Provisão 13º salário / mês|Salário Base ÷ 12|Lei 4.090/1962|
|RN-14|Provisão Férias + 1/3 / mês|(Salário Base ÷ 12) × (4/3)|CLT Art. 129 / CF Art. 7º|
|RN-15|Folha fechada imutável|Constraint no BD + regra de negócio|CLT Art. 464 / LGPD|
# 5 Índice
1. Introdução
   - 1.1 Propósito do Documento de Requisitos
   - 1.2 Escopo do Produto
   - 1.3 Definições, Acrônimos e Abreviações
   - 1.4 Referências
   - 1.5 Estruturação do Documento de Requisitos

2. Descrição Geral
   - 2.1 Perspectiva do Produto
   - 2.2 Funcionalidades do Produto
   - 2.3 Características do Usuário
   - 2.4 Restrições Gerais
   - 2.5 Suposições e Dependências

3. Requisitos Específicos
   - 3.1 Requisitos de Interface com o Usuário (GUI)
     - 3.1.1 Padrões Gerais da Interface
     - 3.1.2 Tela de Login e Autenticação
     - 3.1.3 Menu Principal e Navegação
     - 3.1.4 Tela de Cadastro de Funcionários
     - 3.1.5 Tela de Cadastro da Empresa
     - 3.1.6 Tela de Lançamentos de Exceção Mensais
     - 3.1.7 Tela de Processamento e Fechamento da Folha
     - 3.1.8 Tela de Visualização e Emissão de Holerite
     - 3.1.9 Tela de Histórico de Folhas
   - 3.2 Requisitos Funcionais
     - 3.2.1 Cadastros e Configurações
       - 3.2.1.1 Cadastro da Empresa
       - 3.2.1.2 Cadastro de Funcionários
       - 3.2.1.3 Cadastro de Rubricas
       - 3.2.1.4 Cadastro de Usuários do Sistema
     - 3.2.2 Apuração de Frequência
       - 3.2.2.1 Calendário e Dias Úteis
       - 3.2.2.2 Registro de Faltas Injustificadas
       - 3.2.2.3 Registro de Atrasos
       - 3.2.2.4 Descanso Semanal Remunerado (DSR)
       - 3.2.2.5 Registro de Atestados Médicos
       - 3.2.2.6 Registro de Horas Extras
     - 3.2.3 Processamento de Proventos
       - 3.2.3.1 Salário Base e Proporcionalidade
       - 3.2.3.2 Horas Extras — Processamento
     - 3.2.4 Processamento de Descontos
     - 3.2.5 Encargos e Provisões do Empregador
     - 3.2.6 Saídas e Auditoria
     - 3.2.7 Quadro Consolidado de Regras de Negócio (RN-01 a RN-15)
   - 3.3 Requisitos Não Funcionais
     - 3.3.1 Desempenho
     - 3.3.2 Segurança
       - 3.3.2.1 Autenticação e Controle de Acesso
       - 3.3.2.2 Armazenamento de Credenciais
       - 3.3.2.3 Proteção de Dados Pessoais (LGPD)
       - 3.3.2.4 Comunicação e Armazenamento Seguro
       - 3.3.2.5 Registro de Segurança
     - 3.3.3 Confiabilidade
     - 3.3.4 Usabilidade
     - 3.3.5 Disponibilidade
     - 3.3.6 Manutenibilidade
     - 3.3.7 Portabilidade
     - 3.3.8 Conformidade Legal e Regulatória
     - 3.3.9 Quadro Consolidado de Requisitos Não Funcionais
   - 3.4 Requisitos de Dados
     - 3.4.1 Modelo de Dados
     - 3.4.2 Integridade e Segurança de Dados

4. Apêndices
   - 4.1 Tabela de Rubricas Padrão
   - 4.2 Modelos de Fórmulas de Cálculo
     - 4.2.1 Apuração de Frequência
     - 4.2.2 Composição do Salário
     - 4.2.3 Desconto de INSS — Tabela Progressiva 2025
     - 4.2.4 Encargos e Provisões do Empregador
   - 4.3 Glossário Expandido
   - 4.4 Matriz de Rastreabilidade — Requisitos × Módulos × Telas
   - 4.5 Tabela Consolidada de Regras de Negócio (RN-01 a RN-15)
5. Índices