# 1. Introdução
## 1.1 Propósito do Documento de Requisitos
Este documento especifica os requisitos de software do sistema SFP, destinado à equipe de desenvolvimento, ao cliente e aos avaliadores do projeto, servindo como contrato técnico e base para testes de validação.
O sistema automatiza o cálculo e a emissão de folha de pagamento de funcionários contratados sob regime CLT. A folha de pagamento é baseada na Consolidação das Leis do Trabalho, onde as condições e requisitos são claros e determinados para projetar. Outros regimes, como não-CLT (PJ, estagiários) e alterações contratuais especiais do CLT estão fora do escopo do sistema.
## 1.2 Escopo do Produto
Para a primeira fase do projeto, o sistema contará com: 
* **Gestão de cadastros:** Permitirá a inclusão, leitura e atualização de dados de funcionários, mantendo obrigatoriamente um histórico versionado de alterações salariais. 
* **Lançamentos de Exceções (mensais):** Interface para o lançamento de eventos variáveis que impactam o mês corrente: horas extras, bônus, faltas, atrasos e atestados. A entrada de exceção é feita de forma mensal. Lançamentos de exceção não alteram meses já fechados.
* **Histórico das Folhas de Pagamento:** Interface para a visualização de folhas de pagamento referentes a meses anteriores encerrados.
* **Histórico e Auditoria:** Consulta de folhas de meses anteriores (fechadas) e rastreabilidade total de ações de usuários via Log de Auditoria.
* **Processamento Lógico:** Após o processamento, executa o cálculo matemático de transformação do salário bruto em salário líquido, aplicando as equações de soma de proventos e subtração de descontos registrados para o mês corrente.
* **Gestão de Rubricas:** Controle de benefícios fixos (Cesta Básica) e variáveis (PLR) com configuração de incidência tributária (INSS, FGTS, IRRF).
* **Motor de Provisões e Encargos:** Cálculo automatizado de provisões essenciais exibidas no fechamento mensal: 13º Salário, Férias + ⅓ , IRRF e o recolhimento do FGTS.
* **Geração de Saídas:** Compila os dados processados para a emissão visual e exportação do demonstrativo de pagamento individual (Holerite) em PDF/A.

**Fora do escopo desta versão:**
* Benefícios variáveis com coparticipação (plano de saúde, odontológico); 
* Relatórios por setor/departamento; 
* Pagamentos para regimes não-CLT (PJ, estagiários); 
* Relógio de ponto eletrônico.

## 1.3 Definições, acrônimos e abreviações
Para garantir a compreensão uniforme deste documento por leitores de diferentes áreas (técnicas e de negócios), os seguintes acrônimos e termos legislativos são definidos:
* **CLT:** Consolidação das Leis do Trabalho. Legislação brasileira que regulamenta as relações de trabalho e dita as regras de remuneração aplicáveis a este sistema.
* **FGTS:** Fundo de Garantia do Tempo de Serviço.
* **PLR:** Participação nos Lucros e Resultados. 
* **13º Salário:** Gratificação natalina compulsória prevista na Lei nº 4.090/1962.

Os demais termos técnicos e operacionais utilizados — como: Competência, Folha Aberta, Folha Fechada, Valor-Dia, Valor-Hora, Rubrica, Provento, Desconto, Holerite, INSS, PLR e outros — estão definidos de forma completa no Apêndice 4.3 — Glossário Expandido.

## 1.4 Referências
- Consolidação das Leis do Trabalho — CLT e normas correlatas. Originária da Constituição da República Federativa do Brasil.

## 1.5 Estruturação do Documento de Requisitos 
Este documento segue o padrão IEEE 830 (Software Requirements Specification) adaptado ao contexto do projeto SFP.
* **Seção 1 — Introdução:** Apresenta o propósito, escopo, glossário, referências e esta descrição estrutural.
* **Seção 2 — Descrição Geral:** Descreve o sistema em alto nível: perspectiva de produto, funcionalidades, perfis de usuário, restrições gerais e premissas.
* **Seção 3 — Requisitos Específicos:** Núcleo do documento. Subdivide-se em:
	- **3.1 Requisitos de Interface com o Usuário (GUI):** Define os padrões visuais e comportamentais de todas as telas do sistema.
	- **3.2 Requisitos Funcionais:** Especifica o comportamento funcional por módulo. Cada requisito é rastreável a uma regra de negócio (RN-XX).
	- **3.3 Requisitos Não Funcionais:** Estabelece restrições de qualidade nas dimensões de desempenho, segurança, confiabilidade, usabilidade, disponibilidade, manutenibilidade, portabilidade e conformidade legal.
	- **3.4 Requisitos de Dados:** Define o modelo relacional de dados, as regras de integridade referencial e as políticas de segurança e retenção de dados.
- **Seção 4 — Apêndices:** Material de referência complementar, incluindo tabela de rubricas (4.1), fórmulas de cálculo (4.2), glossário expandido (4.3), matriz de rastreabilidade (4.4) e tabela consolidada de regras de negócio RN-01 a RN-15 (4.5).
- **Seção 5 — Índice:** Lista estruturada de todas as seções e subseções.


**Convenções adotadas neste documento:**
- Requisitos funcionais: formato **X.Y.Z.N** (seção + número sequencial).
- Regras de negócio: código **[RN-XX]**, detalhadas no Apêndice 4.5.
- Fórmulas de cálculo: código **[F-XX]**, detalhadas na seção 4.2.  
- Casos de uso: código **UC-XX**, conforme tabela em UC_Revisao.md.

# 2. Descrição Geral
## 2.1 - Perspectiva do Produto
O sistema é uma aplicação desktop standalone desenvolvida em Java, voltada ao departamento de Recursos Humanos (RH) ou contabilidade.
- **Interface:** Java Desktop (biblioteca Swing).
- **Hardware:** A aplicação é executada localmente na máquina do usuário, conectando-se a um banco de dados que pode estar na mesma máquina (instalação local) ou em servidor na rede interna da empresa.

## 2.2 Funcionalidades do Produto
As principais funções esperadas pelo sistema contam com:
- **Cálculo Automático**: O processamento do salário bruto em salário líquido.
- **Gestão de Exceções**: Registro mensal de horas extras, faltas, atrasos e atestados.
- **Provisões Trabalhistas**: Cálculo mensal de proventos trabalhistas, ex: cálculo mensal de reserva para 13º e Férias + ⅓.
- **Emissão de documentos**: Geração do Holerite e, respectivamente, a emissão do documento como um arquivo PDF.
- **Auditoria:** Log de alterações salariais e operações sensíveis para conformidade legal.

## 2.3 Características do Usuário
- **Analista de RH / Departamento Pessoal:** Usuário principal. Possui conhecimento das regras da CLT e busca agilidade no cálculo.
- **Administrador do Sistema:** Responsável por cadastrar usuários, gerenciar permissões, rubricas e parâmetros fiscais.

## 2.4 Restrições Gerais
* **Legislação**: O sistema deve seguir estritamente a tabela vigente da CLT (2025/2026).
* **Segurança**: Dados salariais são protegidos pela LGPD (Lei Geral de Proteção de Dados - Lei nº 13.709/2018). O acesso deve ser restrito por login e senha.
* **Conformidade**: O sistema não permite a alteração de folhas de meses já encerrados para garantir a integridade contábil.
* **Tecnologia**: O sistema deve ser desenvolvido integralmente em Java (versão 11 ou superior), sem dependência de navegador web ou servidor de aplicação externo para sua operação.

## 2.5 Suposições e Dependências
- Supõe-se que o usuário possua acesso estável à rede local para conexão com o banco de dados.
- Espera-se que o usuário esteja acessando o sistema por meio de um computador que atenda os requisitos mínimos de: Processador Intel i3 4160; 4GB de memória RAM, Placa de vídeo NVIDIA Geforce GTX 750; 100GB de armazenamento.
- O sistema depende da alimentação mensal das exceções (faltas/extras) antes do fechamento da folha.

# 3. Requisitos Específicos

## 3.1 Requisitos de Interface com o Usuário (GUI)

### 3.1.1 Padrões Gerais da Interface
Os seguintes requisitos aplicam-se a todas as telas do sistema:
- **3.1.1.1** — O sistema deve possuir uma interface gráfica desenvolvida com a biblioteca Java Swing.
- **3.1.1.2** — Todas as telas devem exibir, na barra lateral (sidebar), o nome do sistema: **"SFP"**.
- **3.1.1.3** — Todas as telas devem exibir, no cabeçalho superior, o nome do usuário autenticado.
- **3.1.1.4** — Todas as telas devem exibir, no cabeçalho superior, o perfil de acesso ativo (ex.: "Admin", "Usuário").
- **3.1.1.5** — A paleta de cores, tipografia e espaçamento devem ser padronizados e consistentes em toda a aplicação.
- **3.1.1.6** — Todos os formulários devem indicar visualmente os campos obrigatórios com marcador de asterisco (\*) em vermelho ao lado do rótulo.
- **3.1.1.7** — O sistema deve exibir mensagem de confirmação (diálogo modal com botões "Confirmar" e "Cancelar") antes de executar qualquer operação irreversível.
- **3.1.1.8** — O sistema deve exibir mensagens de erro em linguagem clara e objetiva, sem códigos de exceção Java expostos ao usuário.
- **3.1.1.9** — Campos monetários devem ser formatados automaticamente com separador de milhar (ponto) e duas casas decimais (ex.: R$ 3.500,00).
- **3.1.1.10** — Campos de CPF devem aplicar máscara de entrada no formato `000.000.000-00` em tempo real durante a digitação.
- **3.1.1.11** — Campos de data devem utilizar máscara `DD/MM/AAAA` e validar se a data informada é uma data calendário válida.
- **3.1.1.12** — O sistema deve manter a resolução mínima de tela de **1280 × 720 pixels** como referência de layout. 
- **3.1.1.13** — A interface deve permitir acesso a todas as funcionalidades habilitadas para o perfil do usuário autenticado.

### 3.1.2 Tela de Login e Autenticação
Todos os seguintes requisitos se aplicam as telas de login e autenticação inicial do sistema: 
* **3.1.2.1** — O sistema deve apresentar a tela de Login como tela inicial obrigatória, bloqueando o acesso a qualquer outra funcionalidade antes da autenticação.  
- **3.1.2.2** — A tela de Login deve exibir o logotipo ou nome do sistema ("SFP") e a versão atual do software.
- **3.1.2.3** — A tela de Login deve conter o campo **"Usuário"** com os seguintes requisitos:
    - **3.1.2.3.1** — Formato texto, máximo de 50 caracteres.
    - **3.1.2.3.2** — Preenchimento obrigatório.
- **3.1.2.4** — A tela de Login deve conter o campo **"Senha"** com os seguintes requisitos:
    - **3.1.2.4.1** — Formato texto, ocultado com asteriscos.
    - **3.1.2.4.2** — Preenchimento obrigatório.
    - **3.1.2.4.3** — O campo não deve permitir copiar o conteúdo via atalho de teclado (Ctrl+C) ou menu de contexto.
- ***3.1.2.5*** — A sessão do usuário deve ser encerrada automaticamente após 30 minutos de inatividade, redirecionando para a tela de Login com mensagem explicativa.
- **3.1.2.6** — O sistema deve oferecer a opção "Sair" acessível em todas as telas para encerramento manual da sessão.
- **3.1.2.7** — O sistema deve registrar em log de segurança toda tentativa de acesso (bem-sucedida ou não), contendo: nome de usuário, data, hora e resultado (sucesso/falha).

### 3.1.3 Menu Principal e Navegação (Dashboard)
Todos os seguintes requisitos se aplicam as telas para o menu principal e navegação:
- **3.1.3.1** — Após autenticação bem-sucedida, o sistema deve exibir o Dashboard como tela padrão, com a barra lateral (sidebar) de navegação persistente.
- **3.1.3.2** — A barra lateral deve ser organizada em três grupos de menu, exibidos conforme o perfil do usuário:
    - **PRINCIPAL:** Dashboard, Funcionários, Rubricas.
    - **OPERAÇÃO:** Lançamentos de Exceção, Folha do Mês, Holerite, Histórico.
    - **ADMINISTRAÇÃO** _(somente perfil Admin):_ Empresa, Usuários, Log de Auditoria.
- **3.1.3.3** — Itens de menu restritos ao perfil Administrador devem estar ocultos ou desabilitados para o perfil de Analista.
- **3.1.3.4** — O menu deve apresentar indicador visual do módulo atualmente ativo (item com realce de cor de fundo diferenciado).
- **3.1.3.5** — O Dashboard deve exibir, de forma resumida, os seguintes cartões de informação:
    - **3.1.3.5.1** — Competência corrente (mês e ano).
    - **3.1.3.5.2** — Quantidade de funcionários ativos cadastrados.
    - **3.1.3.5.3** — Status da folha do mês corrente ("Aberta" ou "Fechada").
    - **3.1.3.5.4** — Total Bruto da folha do mês corrente.
- ***3.1.3.6*** — O Dashboard deve exibir um painel de avisos do sistema, com alertas de anomalias de processamento e prazo de lançamentos.
- **3.1.3.7** — O Dashboard deve exibir uma prévia tabular da folha corrente (funcionário, cargo, salário base, status) e um resumo de provisões trabalhistas do mês (FGTS, 13º, Férias).

### 3.1.4 Tela de Gerenciamento de Funcionários
Todos os seguintes requisitos se aplicam as telas de gerenciamento dos funcionários:
- **3.1.4.1** — O sistema deve apresentar uma tela de listagem de funcionários com campo de busca por nome ou CPF e botão **"+ Novo Funcionário"**.
- **3.1.4.2** — A listagem deve exibir tabela com as colunas: ícone de avatar, Nome Completo, CPF (parcialmente ocultado: `***.XXX.XXX-**`), Cargo, Data de Admissão, Salário Base, Status (Ativo/Inativo) e coluna de Ações.
- **3.1.4.3** — Registros de funcionários inativos devem ser visualmente diferenciados na tabela (ex.: linha com cor de fundo e texto atenuados).
- **3.1.4.4** — O sistema deve apresentar formulário de cadastro/edição de funcionário com os seguintes campos obrigatórios:
    - **3.1.4.4.1** — Nome Completo (texto, máx. 150 caracteres).
    - **3.1.4.4.2** — CPF (máscara `000.000.000-00`, único no sistema).
    - **3.1.4.4.3** — Data de Nascimento (DD/MM/AAAA).
    - **3.1.4.4.4** — Data de Admissão (DD/MM/AAAA).
    - **3.1.4.4.5** — Cargo (texto, máx. 100 caracteres).      
    - **3.1.4.4.6** — Salário Base (valor monetário, mínimo R$ 1.621,00 conforme piso CLT 2026).
    - **3.1.4.4.7** — Banco e Conta-corrente para depósito salarial.
- **3.1.4.5** — Os seguintes campos são opcionais: Número de Dependentes (inteiro ≥ 0), e-mail institucional.
- **3.1.4.6** — O campo CPF deve ser validado pelo algoritmo de dígitos verificadores da Receita Federal. O sistema deve rejeitar CPFs inválidos, ex: CPFs com todos os dígitos iguais.
- **3.1.4.7** — O sistema deve impedir o cadastro de dois funcionários com o mesmo CPF, exibindo mensagem de erro específica.
- **3.1.4.8** — O sistema deve permitir inativar um funcionário (demissão), registrando obrigatoriamente a data de desligamento. A exclusão permanente não é permitida.
- **3.1.4.9** — O sistema deve exibir o tempo de empresa (anos e meses completos) calculado automaticamente com base na data de admissão.
- **3.1.4.10** — A tela de edição deve exibir o histórico de alterações salariais do funcionário (salário anterior, novo salário, data e usuário responsável).

### 3.1.5 Tela de Cadastro da Empresa
Todos os seguintes requisitos se aplicam a tela de cadastro da empresa:
- **3.1.5.1** — O sistema deve apresentar tela de configuração dos dados da empresa com os seguintes campos obrigatórios:
    - **3.1.5.1.1** — Razão Social (texto, máx. 150 caracteres).
    - **3.1.5.1.2** — CNPJ (máscara `00.000.000/0000-00`).
    - **3.1.5.1.3** — Endereço completo (logradouro, número, complemento, bairro, cidade, UF, CEP).
    - **3.1.5.1.4** — Nome do Responsável Legal.
- **3.1.5.2** — O CNPJ deve ser validado pelo algoritmo oficial de dígitos verificadores da Receita Federal. O sistema deve rejeitar CNPJs com todos os dígitos iguais.
- **3.1.5.3** — A tela deve exibir e permitir a edição dos parâmetros fiscais utilizados nos cálculos da folha:
    - **3.1.5.3.1** — Alíquota de FGTS (padrão: 8,00%).
    - **3.1.5.3.2** — Horas mensais contratuais (padrão: 220h).
    - **3.1.5.3.3** — Valor mensal da Cesta Básica (R$, podendo ser zero).
    - **3.1.5.3.4** — Percentual de adicional de hora extra padrão (50%) e especial (100%).        
- **3.1.5.4** — Alterações nos parâmetros fiscais devem exigir confirmação via diálogo modal e devem ser registradas em log de auditoria com usuário e data.

### 3.1.6 Tela de Gerenciamento de Rubricas
Todos os seguintes requisitos se aplicam a tela de gerenciamento de rubricas:
- **3.1.6.1** — O sistema deve apresentar tela de gerenciamento de rubricas com tabela exibindo as colunas: Código, Descrição, Natureza (Provento / Desconto), Tipo (Fixo / Variável) e flags de Incidência (INSS, FGTS, IRRF).
- **3.1.6.2** — A tabela deve diferenciar visualmente rubricas padrão (não editáveis) de rubricas personalizadas da empresa.
- **3.1.6.3** — O sistema deve disponibilizar botão **"+ Nova Rubrica"** para criação de rubricas personalizadas (código ≥ 500), acessível somente ao perfil Administrador.
- **3.1.6.4** — O formulário de criação/edição de rubrica deve conter: Código (numérico, 3 dígitos), Descrição (texto, máx. 100 caracteres), Natureza, Tipo e checkboxes de Incidência.
- **3.1.6.5** — As rubricas de código 001 a 005 devem ser exibidas como somente leitura, sem botão de exclusão ou edição de campos sensíveis.

### 3.1.7 Tela de Lançamentos de Exceção Mensais
Todos os seguintes requisitos se aplicam a tela de lançamento de exceções mensais:
* **3.1.7.1** — O sistema deve apresentar a tela de lançamentos mensais acessível somente quando a folha do mês corrente estiver com status **"Aberta"**.  
- **3.1.7.2** — A tela deve exibir o mês e ano de competência corrente de forma destacada, impedindo ambiguidade sobre o período de referência dos lançamentos.
- **3.1.7.3** — O sistema deve permitir selecionar um funcionário ativo por meio de campo de busca antes de registrar qualquer lançamento.
- **3.1.7.4** — Para cada funcionário selecionado, o sistema deve permitir o registro dos seguintes tipos de evento:
    - **3.1.7.4.1** — **Horas Extras:** quantidade de horas (decimal, ex.: 2,5), tipo (50% ou 100%) e data de ocorrência. O valor monetário deve ser calculado e exibido em tempo real.
    - **3.1.7.4.2** — **Faltas Injustificadas:** quantidade de dias inteiros ou fração (em horas). O desconto correspondente deve ser calculado e exibido em tempo real.
    - **3.1.7.4.3** — **Atrasos:** quantidade de minutos e data de ocorrência. O desconto proporcional deve ser calculado e exibido em tempo real.
    - **3.1.7.4.4** — **Atestado Médico:** data de início, data de fim e opcionalmente arquivo digitalizado (PDF). Atestados justificam ausências e neutralizam o desconto de falta.
    - **3.1.7.4.5** — **Bônus / Gratificação:** valor monetário livre e descrição textual (obrigatória, máx. 200 caracteres).
- **3.1.7.5** — O sistema deve impedir o lançamento de eventos em meses cuja folha já esteja com status "Fechada", exibindo mensagem explicativa ao usuário.
- **3.1.7.6** — O sistema deve permitir a edição e exclusão de lançamentos do mês corrente enquanto a folha estiver aberta.

### 3.1.8 Tela de Visualização e Emissão de Holerite
Todos os seguintes requisitos se aplicam a tela de visualização e a emissão da folha de pagamento:
- **3.1.8.1** — O sistema deve apresentar a tela de processamento da folha com listagem de todos os funcionários ativos e seus respectivos status de processamento.
- **3.1.8.2** — O sistema deve disponibilizar o botão **"Processar Folha"** que executa o cálculo para todos os funcionários ativos.
- **3.1.8.3** — Após o processamento, o sistema deve exibir resumo tabular contendo, para cada funcionário:
    - **3.1.8.3.1** — Nome.
    - **3.1.8.3.2** — Salário Bruto.
    - **3.1.8.3.3** — Total de Proventos.
    - **3.1.8.3.4** — Total de Descontos.
    - **3.1.8.3.5** — Salário Líquido.
    - **3.1.8.3.6** — Status de processamento (Processado / Pendente / Erro).
- **3.1.8.4** — O sistema deve permitir re-processar a folha enquanto o status for "Aberta", sobrescrevendo os valores calculados anteriormente após confirmação modal.
- **3.1.8.5** — O sistema deve disponibilizar o botão **"Fechar Folha"** que torna o mês de competência imutável.
- **3.1.8.6** — O botão "Fechar Folha" deve estar desabilitado enquanto existir algum funcionário com status "Pendente" ou "Erro" no processamento.
- **3.1.8.7** — Após o fechamento, o sistema deve gerar e exibir automaticamente o relatório de provisões do mês, com 13º Salário, Férias + ⅓ e FGTS por funcionário.



### 3.1.9 Tela de Visualização e Emissão de Holerite
- **3.1.9.1** — O sistema deve apresentar tela de holerite com seletor de funcionário e seletor de mês/ano de competência. Apenas meses com folha fechada devem estar disponíveis para seleção.
- **3.1.9.2** — O holerite exibido deve conter obrigatoriamente os seguintes campos:
    - **3.1.9.2.1** — Dados da empresa: Razão Social e CNPJ.
    - **3.1.9.2.2** — Dados do funcionário: Nome, CPF (parcialmente ocultado), Cargo e Data de Admissão.
    - **3.1.9.2.3** — Mês e ano de competência.
    - **3.1.9.2.4** — Tabela de proventos: código da rubrica, descrição, referência e valor.
    - **3.1.9.2.5** — Tabela de descontos: código da rubrica, descrição, referência e valor.
    - **3.1.9.2.6** — Totais: Total de Proventos, Total de Descontos e Salário Líquido em destaque.
    - **3.1.9.2.7** — Linha de assinatura do colaborador (na versão impressa).
- **3.1.9.3** — O sistema deve disponibilizar botão **"Exportar PDF"** que gera o holerite em formato PDF/A.
- **3.1.9.4** — O holerite exportado em PDF deve substituir a linha de assinatura pela legenda: _"Documento Digital — Dispensa Assinatura"_.
- **3.1.9.5** — O sistema deve permitir a visualização de holerites de qualquer mês anterior fechado, sem limite de período para consulta.

### 3.1.10 Tela de Histórico de Folhas de Pagamento
- **3.1.10.1** — O sistema deve apresentar tela de histórico com listagem de todas as folhas já processadas, organizadas por mês/ano em ordem cronológica decrescente.
- **3.1.10.2** — Para cada folha listada, devem ser exibidos: Mês/Ano de Competência, Status, Data de Fechamento, Quantidade de Funcionários Processados e Total Líquido da Folha.
- **3.1.10.3** — A tela deve permitir filtragem por intervalo de datas (De: Mês/Ano — Até: Mês/Ano).
- **3.1.10.4** — O sistema deve permitir a seleção de uma folha fechada para visualização detalhada, exibindo o resumo individual de cada funcionário naquela competência.
- **3.1.10.5** — O sistema não deve exibir botões de edição ou reprocessamento para folhas com status "Fechada" — somente o botão "Visualizar".

### 3.1.11 Tela de Gerenciamento de Usuários do Sistema
- **3.1.11.1** — O sistema deve apresentar tela de gerenciamento de usuários acessível exclusivamente pelo perfil Administrador.
- **3.1.11.2** — A tela deve exibir tabela com os usuários cadastrados: Nome, Login, Perfil (Admin / Analista de RH) e Status (Ativo / Inativo).
- **3.1.11.3** — O sistema deve disponibilizar botão **"+ Novo Usuário"** com formulário contendo: Nome completo, Login (máx. 50 caracteres), Perfil e Senha inicial.
- **3.1.11.4** — O Administrador deve poder inativar um usuário e redefinir sua senha manualmente, com confirmação via diálogo modal em ambas as operações.
- **3.1.11.5** — Deve existir sempre ao menos um usuário com perfil Administrador ativo. O sistema deve bloquear a inativação do último Administrador.

### 3.1.12 Tela de Log de Auditoria
- **3.1.12.1** — O sistema deve apresentar tela de log de auditoria acessível exclusivamente pelo perfil Administrador.   
- **3.1.12.2** — A tela deve exibir tabela com as entradas do log: ID sequencial, Data/Hora, Usuário, Tipo de Operação, Entidade Afetada e Resumo da Mudança.
- **3.1.12.3** — A tela deve disponibilizar filtros por: período (data inicial e final), usuário e tipo de operação.
- **3.1.12.4** — O log deve ser exibido em modo somente leitura. Nenhum botão de exclusão ou edição deve estar disponível.

## 3.2 Requisitos Funcionais
### 3.2.1 Cadastros e Configurações
Este módulo engloba os requisitos de registro e manutenção dos dados mestres do sistema: empresa, funcionários, rubricas, usuários e parâmetros fiscais. A integridade dos dados cadastrais é pré-requisito para a correção de todos os cálculos subsequentes.

#### 3.2.1.1 Cadastro da Empresa
* **3.2.1.1.1 —** O cadastro deve incluir obrigatoriamente:
	- Razão Social (texto, máx. 150 caracteres).
	- CNPJ (com validação por dígitos verificadores da Receita Federal).
	- Endereço completo (logradouro, número, complemento, bairro, cidade, UF, CEP).
	- Nome do Responsável Legal.
- **3.2.1.1.2 —** O sistema deve rejeitar CNPJs com todos os dígitos iguais (ex.: `11.111.111/1111-11`), mesmo que passem na verificação matemática. 
- **3.2.1.1.3 —** O sistema deve armazenar os seguintes parâmetros fiscais:
	- Alíquota de FGTS: valor percentual, padrão 8,00%.
	- Horas mensais contratuais: inteiro positivo, padrão 220 horas (divisor para cálculo do valor-hora).
	- Percentual de adicional de hora extra padrão (50%), fixo por legislação.
	- Percentual de adicional de hora extra especial (100%): aplicável a domingos, feriados e período noturno contínuo.
	- Valor mensal da Cesta Básica (R$, podendo ser zero).

#### 3.2.1.2 Cadastro de Funcionários
* **3.2.1.2.1 —** O sistema deve permitir o cadastro, a edição e a inativação de funcionários CLT. A exclusão permanente de registros **não é permitida**.
* **3.2.1.2.2 —** Campos obrigatórios para cadastro:
	- Nome Completo;
	- CPF; 
	- Data de Nascimento; 
	- Data de Admissão; 
	- Cargo; 
	- Salário Base Contratual; 
	- Banco; 
	- Conta-corrente para depósito.
- **3.2.1.2.3 —** Campos opcionais: Número de Dependentes (inteiro ≥ 0, padrão 0) e e-mail institucional.
- **3.2.1.2.4 —** O sistema deve garantir unicidade do CPF: nenhum dos funcionários — ativos ou inativos — pode compartilhar o mesmo CPF.
- **3.2.1.2.5 —** O Salário Base deve ser ≥ ao salário mínimo nacional vigente. O sistema deve rejeitar valores abaixo do piso, exibindo mensagem com o valor mínimo aceitável.
- **3.2.1.2.6 —** O sistema deve manter histórico completo de alterações salariais. Cada entrada deve registrar: salário anterior, novo salário, data de alteração e usuário responsável.
- **3.2.1.2.7 —** O sistema deve identificar o mês de admissão de cada funcionário para aplicar cálculo proporcional automaticamente na primeira folha.
- **3.2.1.2.8 —** A inativação de um funcionário deve registrar obrigatoriamente a data de desligamento.
- **3.2.1.2.9 —** O sistema deve calcular e exibir automaticamente o tempo de empresa (anos e meses completos) com base na data de admissão.

#### 3.2.1.3 Cadastro de Rubricas
* **3.2.1.3.1 —** O sistema deve manter uma tabela de rubricas que classifica cada valor como provento ou desconto, fixo ou variável.
* **3.2.1.3.2 —** Cada rubrica deve conter: Código único (numérico, 3 dígitos), Natureza (Provento / Desconto), Tipo (Fixo / Variável) e Incidência (flags: incide INSS, incide FGTS, incide IRRF).
* **3.2.1.3.3 —** O sistema deve incluir as rubricas padrão pré-cadastradas conforme Apêndice 4.1. As rubricas de códigos 001 a 005 são obrigatórias e não podem ser excluídas nem ter seu código, natureza ou flags de incidência alterados.
* **3.2.1.3.4 —** As rubricas de códigos 006 a 008 e 101 a 106 podem ser excluídas pelo Administrador, desde que não estejam referenciadas em nenhuma folha já processada.
* **3.2.1.3.5 —** O Administrador pode criar rubricas personalizadas (código ≥ 500) para benefícios ou descontos específicos da empresa.

#### 3.2.1.4 Cadastro de Usuários do Sistema
* **3.2.1.4.1 —** O sistema deve permitir o cadastro e gerenciamento de usuários, exclusivamente pelo Administrador.
* **3.2.1.4.2 —** Os perfis disponíveis são:
	- **Administrador:** acesso total, incluindo configuração de parâmetros, gestão de usuários, rubricas e log de auditoria.
	- **Analista de RH:** acesso operacional — cadastros de funcionários, lançamentos, processamento e emissão de holerites.
- **3.2.1.4.3 —** Deve existir ao menos um usuário com perfil Administrador ativo em todo momento.
- **3.2.1.4.4 —** Nenhuma senha deve ser armazenada ou exibida em texto plano em qualquer circunstância.

#### 3.2.1.5 Atualização de Parâmetros Legais
* **3.2.1.5.1 —** O sistema deve permitir ao Administrador atualizar as alíquotas e tabelas legais (faixas de INSS, piso salarial, FGTS) sem necessidade de recompilar o sistema.
* **3.2.1.5.2 —** As atualizações de parâmetros legais devem ser registradas em log de auditoria com data, hora e usuário responsável.

### 3.2.2 Apuração de Frequência
Este módulo define as regras de registro e cálculo dos eventos de frequência que impactam a folha do mês corrente. Todos os lançamentos são mensais, vinculados à competência aberta, e se tornam somente leitura após o fechamento da folha.

#### 3.2.2.1 Calendário e Dias Úteis
* **3.2.2.1.1 —** O sistema deve calcular, para cada mês de competência, os dias úteis como segunda a sexta-feira, excluindo sábados e domingos.
	- Feriados nacionais não são descontados automaticamente pelo sistema.
	- O total de dias úteis é utilizado como denominador no cálculo do Valor-Dia.

#### 3.2.2.2 Registro de Faltas Injustificadas
* **3.2.2.2.1 —** O sistema deve permitir o lançamento de faltas injustificadas em dias inteiros ou fração de dia (em horas), associadas a um funcionário e ao mês de competência corrente.
	- Desconto por falta de dia inteiro: `Desconto_Falta = Valor_Dia × Nº_Dias_Faltados`.
	- Desconto por fração de dia: `Desconto_Fração = Valor_Hora × Nº_Horas_Faltadas`, onde `Valor_Hora = Salário_Base ÷ Horas_Mensais_Contratuais`.
- **3.2.2.2.2 —** O sistema deve impedir o lançamento de faltas em quantidade superior ao total de dias úteis do mês de competência.

#### 3.2.2.3 Registro de Atrasos
* **3.2.2.3.1 —** O sistema deve permitir o lançamento de atrasos em minutos, associados a uma data específica dentro do mês de competência corrente.
	- Desconto proporcional: `Desconto_Atraso = Valor_Hora × (Minutos_Atraso ÷ 60)`.
- **3.2.2.3.2 —** O sistema deve permitir o lançamento de múltiplos atrasos para o mesmo funcionário no mesmo mês, acumulando o total de minutos.
- **3.2.2.3.3 —** Atrasos de até 10 minutos diários são tolerados pela CLT (Art. 58, § 1º). O sistema deve identificar atrasos dentro da tolerância e não gerar desconto, exibindo indicador visual ao operador.

#### 3.2.2.4 Descanso Semanal Remunerado (DSR)
* **3.2.2.4.1 —** O sistema deve calcular o impacto das faltas injustificadas sobre o Descanso Semanal Remunerado (DSR), conforme Lei nº 605/1949. O funcionário perde o DSR de cada semana em que ocorreu falta injustificada.
* **3.2.2.4.2 —** O sistema deve calcular quantos DSR são afetados com base nas datas das faltas lançadas.
* **3.2.2.4.3 —** Valor do desconto de DSR: `Desconto_DSR = Valor_Dia × Nº_DSR_Perdidos`.
* **3.2.2.4.4 —** O total de DSR perdidos deve ser exibido no holerite como rubrica destacada (código 104 — Desconto DSR).

#### 3.2.2.5 Registro de Atestados Médicos
* **3.2.2.5.1 —** O sistema deve permitir o registro de atestados médicos que justificam ausências. O atestado deve impedir que os dias cobertos gerem desconto de falta e não deve afetar o DSR da semana correspondente.
* **3.2.2.5.2 —** Cada atestado deve conter: data de início, data de fim, tipo (médico / odontológico / outro) e CID opcional (texto livre, máx. 10 caracteres).
* **3.2.2.5.3 —** Os primeiros 15 dias de afastamento por doença são responsabilidade do empregador (CLT Art. 476). O sistema deve:
	- Neutralizar automaticamente o desconto de falta para todos os dias cobertos por atestado, até o limite de 15 dias corridos consecutivos.
	- Não afetar o DSR das semanas cobertas pelo atestado dentro desse período.
	- Exibir alerta ao operador quando o atestado registrado ultrapassar 15 dias corridos consecutivos.
- **3.2.2.5.4 —** O upload de arquivo digitalizado do atestado (PDF, JPG ou PNG, máx. 5 MB) é suportado nesta versão como campo opcional.

#### 3.2.2.6 Registro de Horas Extras
* **3.2.2.6.1 —** O sistema deve permitir o lançamento de horas extras por funcionário, com data de ocorrência, quantidade de horas (decimal, ex.: 1,5) e tipo:
	- **50%** — dias úteis (segunda a sábado).
	- **100%** — domingos, feriados ou quando determinado por acordo coletivo.
- **3.2.2.6.2 —** O sistema deve alertar, sem bloquear, quando o total mensal de horas extras de um funcionário ultrapassar o limite legal (2 horas/dia × dias úteis do mês, conforme CLT Art. 59).
- **3.2.2.6.3 —** O valor bruto de cada hora extra deve ser calculado conforme RN-06 e exibido em tempo real durante o lançamento.

### 3.2.3 Processamento de Proventos
Este módulo descreve o cálculo de todos os valores de natureza creditícia que compõem o salário bruto do funcionário. O salário bruto é a base de cálculo para os encargos (FGTS) e pode ser a base para descontos (INSS).

#### 3.2.3.1 Salário Base e Proporcionalidade
* **3.2.3.1.1 —** O sistema deve calcular o salário base mensal de cada funcionário, aplicando proporcionalidade de dias trabalhados quando aplicável.
- Para funcionários que trabalharam o mês completo: salário base = salário base contratual.
- Para funcionários admitidos após o primeiro dia útil ou desligados antes do último: aplicar salário proporcional conforme RN-07.

#### 3.2.3.2 Horas Extras — Processamento
* **3.2.3.2.1 —** O sistema deve consolidar todos os lançamentos de horas extras do mês de competência e calcular o total de proventos por tipo de adicional (RN-06).
* **3.2.3.2.2 —** O valor total de horas extras deve ser somado ao salário base para compor o salário bruto. Horas extras integram a base de incidência de INSS e FGTS.
* **3.2.3.2.3 —** As horas extras devem ser listadas no holerite separadas por tipo (rubrica 002 para 50% e rubrica 003 para 100%).

#### 3.2.3.3 Bônus e Gratificações
* **3.2.3.3.1 —** O sistema deve processar bônus e gratificações eventuais lançados no módulo de exceções, somando-os ao salário bruto do mês de competência (rubrica 004).
* **3.2.3.3.2 —** O sistema deve sinalizar ao operador quando um mesmo bônus for lançado por 3 meses consecutivos, conforme CLT Art. 457 § 1º.

#### 3.2.3.4 Cesta Básica
* **3.2.3.4.1 —** O sistema deve processar automaticamente o benefício de Cesta Básica com base no valor configurado no cadastro da empresa (rubrica 005 — fixa mensal).
* **3.2.3.4.2 —** A Cesta Básica não integra a base de cálculo de INSS ou FGTS, por possuir natureza indenizatória.
* **3.2.3.4.3 —** Funcionários admitidos no meio do mês recebem a Cesta Básica integral.

#### 3.2.3.5 Participação nos Lucros e Resultados (PLR)
* **3.2.3.5.1 —** O sistema deve processar a PLR quando lançada como evento variável, aplicando as regras de incidência específicas.
* **3.2.3.5.2 —** A PLR não integra a remuneração para efeitos de INSS, FGTS, férias ou 13º salário, conforme Lei nº 10.101/2000, Art. 3º.
* **3.2.3.5.3 —** A PLR deve ser exibida no holerite como rubrica destacada (código 006) com valor bruto, sem retenção de imposto.

#### 3.2.3.6 Composição do Salário Bruto
* **3.2.3.6.1 —** O sistema deve calcular o Salário Bruto como a soma de todos os proventos, conforme RN-08.

### 3.2.4 Processamento de Descontos
Este módulo define os descontos que devem ser subtraídos do salário bruto para apuração do salário líquido.

#### 3.2.4.1 Desconto de INSS
* **3.2.4.1.1 —** O sistema deve calcular o desconto de INSS aplicando a tabela progressiva vigente sobre a Base de Cálculo INSS (salário bruto excluídas as rubricas não incidentes).
* **3.2.4.1.2 —** O cálculo deve ser progressivo por faixa (não alíquota única), conforme Portaria MPS vigente para 2025.
* **3.2.4.1.3 —** O valor calculado do INSS deve ser exibido no holerite com a base de cálculo utilizada e a alíquota efetiva resultante.

#### 3.2.4.3 Desconto de Faltas, Atrasos e DSR
* **3.2.4.3.1 —** O sistema deve consolidar todos os descontos de frequência (faltas, atrasos, DSR perdido) calculados no módulo 3.2.2 e incluí-los na folha.
	- Desconto total de frequência = Desconto_Faltas + Desconto_Atrasos + Desconto_DSR.
	- Cada item deve aparecer como rubrica separada no holerite com código e valor individual.

#### 3.2.4.4 Outros Descontos Variáveis
* **3.2.4.4.1 —** O sistema deve suportar o lançamento de descontos variáveis adicionais (adiantamentos, deduções autorizadas) vinculados a uma rubrica cadastrada.
* **3.2.4.4.2 —** O sistema deve impedir que o total de descontos supere o total de proventos, resultando em salário líquido negativo.

#### 3.2.4.5 Composição do Salário Líquido
**3.2.4.5.1 —** O sistema deve calcular o Salário Líquido como resultado final do processamento, conforme RN-11.

### 3.2.5 Encargos e Provisões Trabalhistas
Este módulo define os cálculos dos encargos sobre a folha (FGTS) e das provisões mensais para obrigações trabalhistas futuras. Esses valores não impactam o salário líquido do funcionário.

#### 3.2.5.1 FGTS — Fundo de Garantia do Tempo de Serviço
* **3.2.5.1.1 —** O sistema deve calcular o FGTS mensal de cada funcionário com alíquota configurada (padrão 8%) sobre a Base de Cálculo FGTS (Salário Base Proporcional + Horas Extras + Bônus com incidência).
* **3.2.5.1.2 —** O FGTS é encargo do empregador e não é deduzido do salário líquido do funcionário.
* **3.2.5.1.3 —** O valor calculado de FGTS deve ser acumulado mensalmente para geração de relatório de encargos.

#### 3.2.5.2 Provisão de 13º Salário
* **3.2.5.2.1 —** O sistema deve calcular mensalmente a provisão de 13º Salário conforme RN-13, acumulando desde o mês de admissão e reiniciando em janeiro de cada ano.

#### 3.2.5.3 Provisão de Férias e Adicional de ⅓
* **3.2.5.3.1 —** O sistema deve calcular mensalmente a provisão de férias acrescida do adicional constitucional de ⅓ (CLT Art. 129 / CF Art. 7º, XVII), acumulando desde a data de admissão e reiniciando após 12 meses.

#### 3.2.5.4 Relatório de Encargos e Provisões
* **3.2.5.4.1 —** O sistema deve gerar, ao fechar a folha, um relatório consolidado contendo: por funcionário — FGTS do mês, Provisão 13º do mês, Provisão Férias + ⅓ do mês e acumulados no ano; e totais gerais da empresa.
* **3.2.5.4.2 —** O relatório deve ser exportável em PDF.

### 3.2.6 Saídas e Auditoria
#### 3.2.6.1 Holerite Individual
* **3.2.6.1.1 —** O sistema deve gerar o holerite individual de cada funcionário para cada mês de competência processado, em conformidade com o Art. 464 da CLT. O holerite deve conter:
	- **Cabeçalho:** Razão Social e CNPJ da empresa, Nome do funcionário, CPF mascarado, Cargo, Data de Admissão e Mês/Ano de competência.
	- **Corpo:** Tabela de Proventos (código, descrição, referência/quantidade, valor) e Tabela de Descontos.
	- **Rodapé:** Total Bruto de Proventos, Total de Descontos, Salário Líquido e linha de assinatura.
- **3.2.6.1.2 —** O arquivo PDF gerado deve ter nomenclatura padronizada: `HOLERITE_[CPF-sem-formatacao]_[AAAAMM].pdf`.
- **3.2.6.1.3 —** O sistema deve permitir a reimpressão/re-exportação do holerite de qualquer mês fechado, sem alterar nenhum dado.

#### 3.2.6.2 Relatório de Resumo da Folha
* **3.2.6.2.1 —** O sistema deve gerar, após o fechamento da folha, um relatório mensal contendo: Nome, Cargo, Salário Bruto, Total de Descontos, Salário Líquido e Status, com totais gerais ao final.
* **3.2.6.2.2 —** O relatório deve ser exportável em PDF (layout formatado).

#### 3.2.6.3 Exportação para Pagamento Bancário
* **3.2.6.3.1 —** O sistema deve gerar um arquivo de remessa simplificado contendo: CPF, Nome, Banco, Agência, Conta e Valor Líquido, em formato PDF estruturado com codificação UTF-8.

#### 3.2.6.4 Histórico e Consulta de Folhas Anteriores
* **3.2.6.4.1 —** O sistema deve manter o histórico completo de todas as folhas processadas e fechadas, sem limite de período de retenção.
* **3.2.6.4.2 —** Folhas fechadas devem ser acessíveis para consulta mesmo que cadastros ou salários tenham sido alterados posteriormente, pois os dados são gravados como snapshot imutável no momento do fechamento (nome, salário base e rubricas utilizadas são gravados junto à folha, não apenas referenciados).
* **3.2.6.4.3 —** A consulta ao histórico deve permitir filtro por intervalo de competências (mês/ano inicial a mês/ano final) e por funcionário específico.

#### 3.2.6.5 Log de Auditoria
* **3.2.6.5.1 —** O sistema deve manter um log de auditoria imutável que registra todas as operações de escrita realizadas na aplicação.
* **3.2.6.5.2 —** Cada entrada do log deve conter: ID sequencial, data e hora, usuário, tipo de operação (ex.: `CADASTRO_FUNCIONARIO`, `FECHAMENTO_FOLHA`, `ALTERACAO_SALARIO`), entidade afetada (tabela + ID do registro) e resumo da mudança (valor anterior e novo valor, quando aplicável).
* **3.2.6.5.3 —** O log deve ser append-only: nenhum registro pode ser alterado ou excluído por nenhum usuário, incluindo o Administrador.
* **3.2.6.5.4 —** O sistema deve disponibilizar tela de consulta ao log de auditoria (seção 3.1.12), acessível somente ao Administrador, com filtros por período, usuário e tipo de operação.

### 3.2.7 Quadro Consolidado de Regras de Negócio (RN-01 a RN-15)

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


## 3.3 Requisitos Não Funcionais
### 3.3.1 Desempenho
Os requisitos desta categoria estabelecem limites quantitativos para tempos de resposta e capacidade de processamento, medidos na configuração de hardware mínima especificada em 3.3.1.1.

* **3.3.1.1 —** Configuração de hardware mínima de referência: processador dual-core 2,0 GHz (x64), 4 GB de RAM (2 GB disponíveis para a JVM), Windows 10 e disco SSD ou HDD de leitura sequencial ≥ 100 MB/s.
* **3.3.1.2 —** O sistema deve processar a folha de pagamento completa:
	- Até 50 funcionários ativos: no máximo **15 segundos**.
	- De 51 a 200 funcionários: no máximo **45 segundos**.
- **3.3.1.3 —** Operações de consulta e leitura devem retornar e renderizar a tela em no máximo **3 segundos**, com até 500 funcionários cadastrados.
- **3.3.1.4 —** A geração e exportação do PDF de um holerite individual deve ser concluída em no máximo **5 segundos**.
- **3.3.1.5 —** A inicialização da aplicação deve ocorrer em no máximo **8 segundos** na configuração de hardware de referência.
- **3.3.1.6 —** O sistema deve suportar o cadastro de até 500 funcionários (ativos e inativos) e o armazenamento de até **60 meses** de histórico de folhas.

|Operação|Limite Máximo|Condição de Medição|
|---|---|---|
|Processamento da folha — até 50 funcionários|15 s|Hardware de referência, carga normal|
|Processamento da folha — 51 a 200 funcionários|45 s|Hardware de referência, carga normal|
|Consultas e listagens|3 s|Base com até 500 funcionários|
|Exportação de PDF (holerite individual)|5 s|Arquivo local, disco de referência|
|Inicialização da aplicação|8 s|Após SO iniciado, hardware de referência|

### 3.3.2 Segurança
Os requisitos desta categoria protegem dados pessoais e salariais, classificados como dados sensíveis pela LGPD (Lei nº 13.709/2018).

#### 3.3.2.1 Autenticação e Controle de Acesso
* **3.3.2.1.1 —** O sistema deve exigir autenticação por login e senha em toda sessão, com controle de acesso baseado em perfil (RBAC).
* **3.3.2.1.2 —** Após 30 minutos de inatividade do usuário, a sessão deve ser encerrada automaticamente e a tela de Login reapresentada.
* **3.3.2.1.3 —** Após 3 tentativas consecutivas de login com senha incorreta para o mesmo usuário, a conta deve ser bloqueada temporariamente por **5 minutos**. O bloqueio deve ser registrado no log de segurança com timestamp e nome do usuário.

#### 3.3.2.2 Armazenamento de Credenciais
* **3.3.2.2.1 —** As senhas de todos os usuários devem ser armazenadas no banco de dados exclusivamente como hash bcrypt, com fator de custo mínimo de 10 (~100 ms de processamento por tentativa na configuração de referência).
* **3.3.2.2.2 —** Nenhuma senha deve ser armazenada, transmitida ou exibida em texto plano em qualquer circunstância.
* **3.3.2.2.3 —** O sistema não deve implementar funcionalidade de "recuperar senha" que exiba ou envie a senha atual — apenas redefinição com nova senha via Administrador.
* **3.3.2.2.4 —** Novas senhas criadas devem atender: mínimo de 8 caracteres, ao menos uma letra maiúscula, ao menos uma letra minúscula e ao menos um dígito numérico.

#### 3.3.2.3 Proteção de Dados Pessoais (LGPD)
* **3.3.2.3.1 —** O sistema deve tratar dados pessoais e salariais conforme os princípios da LGPD (Lei nº 13.709/2018).
* **3.3.2.3.2 —** Campos de CPF devem ser exibidos mascarados (`***.***.XXX-**`) em todas as telas de listagem e consulta. O CPF completo é visível somente nas telas de cadastro e edição individual.
* **3.3.2.3.3 —** Dados de salário devem ser visíveis somente para usuários autenticados com perfil autorizado.
* **3.3.2.3.4 —** O sistema deve registrar em log de auditoria toda exportação de dados (PDF), identificando o usuário, data, hora e tipo de exportação.
* **3.3.2.3.5 —** O sistema não deve transmitir dados de funcionários para qualquer servidor externo.

#### 3.3.2.4 Comunicação e Armazenamento Seguro
* **3.3.2.4.1 —** A comunicação entre a aplicação desktop e o banco de dados deve ser em rede local.
* **3.3.2.4.2 —** Strings de conexão com o banco de dados não devem ser armazenadas em texto plano em arquivos de configuração.
* **3.3.2.4.3 —** Arquivos de backup gerados pelo sistema devem ser armazenados em diretório com permissões restritas ao usuário do sistema operacional que executa a aplicação.

#### 3.3.2.5 Registro de Segurança 
* **3.3.2.5.1 —** O sistema deve manter um log de segurança separado do log de auditoria funcional, registrando: login bem-sucedido ou falho, bloqueio de conta, encerramento de sessão e redefinição de senha.
* **3.3.2.5.2 —** Cada entrada deve conter: timestamp (data e hora), tipo de evento, nome de usuário informado e identificador da estação de trabalho (endereço IP local).
* **3.3.2.5.3 —** O log de segurança deve ser somente-inserção (append-only), sem interface de exclusão disponível para nenhum perfil.

### 3.3.3 Confiabilidade
Os requisitos desta categoria garantem que o sistema produza resultados corretos, consistentes e recuperáveis.

* **3.3.3.1 —** Todos os cálculos monetários devem ser realizados internamente com tipo `java.math.BigDecimal`, escala mínima de 4 dígitos, arredondamento `RoundingMode.HALF_UP`. O uso de `double`, `float` ou `int` para valores monetários é proibido.
* **3.3.3.2 —** O resultado do processamento da folha deve ser idempotente: processar a mesma folha duas vezes com os mesmos dados deve produzir exatamente o mesmo resultado.
* **3.3.3.3 —** O sistema deve utilizar transações de banco de dados com propriedades ACID para todas as operações de escrita relacionadas ao processamento da folha. Em caso de erro, executar rollback completo dos dados desse funcionário sem afetar os demais.
* **3.3.3.4 —** Ao final de cada processamento, o sistema deve exibir relatório de resultado discriminando: funcionários processados com sucesso, funcionários com erro (com descrição do motivo) e funcionários não processados.
* **3.3.3.5 —** O sistema deve validar a integridade dos dados antes de iniciar o processamento, listando todos os problemas em tela de pré-validação e impedindo o processamento até que sejam corrigidos.
* **3.3.3.6 —** O sistema deve gerar automaticamente um **backup** do banco de dados antes de alterar o status da folha para "Fechada". O arquivo deve seguir a nomenclatura: `SFPCLT_BACKUP_[AAAAMM]_[YYYYMMDD_HHMMSS].sql`. O sistema deve alertar caso o diretório configurado não exista ou não possua permissão de escrita.
* **3.3.3.7 —** O sistema deve detectar e tratar as seguintes condições de erro de infraestrutura com mensagem clara ao usuário:
	- Perda de conexão com o banco de dados durante operação: exibir mensagem, aguardar 30 segundos e tentar reconexão automática.
	- Falta de espaço em disco durante exportação de PDF: exibir mensagem com o caminho de destino.
	- Falha de impressora durante impressão: exibir mensagem com opção de tentar novamente ou cancelar, sem travar a aplicação.
* **3.3.3.8 —** Toda operação de fechamento de folha deve ser registrada em log de auditoria com: data/hora de conclusão, usuário responsável, quantidade de funcionários processados e hash SHA-256 do arquivo de backup gerado.
* **3.3.3.9 —** Em caso de encerramento inesperado da aplicação, os dados de lançamentos em andamento devem ser preservados, com notificação ao usuário no próximo acesso.

### 3.3.4 Usabilidade
* **3.3.4.1 —** Um usuário recém-treinado deve ser capaz de executar o ciclo completo de fechamento de uma folha de 10 funcionários em até 20 minutos, sem auxílio e sem cometer erros irreversíveis.
* **3.3.4.2 —** Mensagens de erro devem ser exibidas próximas ao campo inválido, em linguagem clara, sem termos técnicos ou códigos de exceção Java.
* **3.3.4.3 —** Operações destrutivas ou irreversíveis devem sempre ser precedidas de diálogo de confirmação modal (ex.: fechamento de folha, exclusão de lançamento, redefinição de senha).
* **3.3.4.4 —** O sistema deve suportar navegação completa por teclado em todos os formulários (Tab, Enter, Esc, setas), em conformidade com WCAG 2.1 nível A.
* **3.3.4.5 —** O sistema deve manter consistência visual e comportamental em toda a aplicação: mesmos padrões de cores para status idênticos em todas as telas.
* **3.3.4.6 —** O sistema deve exibir no cabeçalho de cada tela: nome do usuário logado, perfil de acesso ativo e mês de competência corrente.

### 3.3.5 Disponibilidade
* **3.3.5.1 —** O sistema deve estar operacional durante todo o horário de expediente da empresa, com tempo de inatividade não planejado inferior a **~171 minutos/mês** (meta de 99,0% de disponibilidade na janela de 286 h/mês).
* **3.3.5.2 —** Manutenções programadas devem ser comunicadas aos usuários com antecedência de **24 horas** e agendadas preferencialmente fora do horário de expediente.
* **3.3.5.3 —** Em caso de indisponibilidade do banco de dados, o sistema deve exibir mensagem de erro clara indicando que o banco está inacessível.

### 3.3.6 Manutenibilidade
* **3.3.6.1 —** O sistema deve separar as responsabilidades em três camadas obrigatórias: Apresentação (View/Swing), Negócio (Service) e Persistência (Repository/DAO).
* **3.3.6.2 —** O sistema deve abstrair parâmetros sensíveis ao calendário governamental (tabelas INSS, salário mínimo, FGTS), viabilizando atualização anual sem recompilação.
* **3.3.6.3 —** O projeto deve incluir suíte de testes automatizados integrável via Maven ou Gradle.
* **3.3.6.4 —** O sistema deve registrar erros no log de aplicação com stack trace completo (`java.util.logging` ou equivalente).
* **3.3.6.5 —** O código deve estar organizado em pacotes desacoplados por domínio funcional (ex.: `br.com.sfpclt.funcionario`, `br.com.sfpclt.folha`, `br.com.sfpclt.auditoria`).

### 3.3.7 Portabilidade
* **3.3.7.1 —** O sistema deve executar sobre JVM 11+ nos seguintes sistemas operacionais: Windows 10/11, Ubuntu Linux 20.04/22.04 e macOS 12+ (Intel e M-series).
* **3.3.7.2 —** O sistema deve ser distribuído com empacotador nativo (`jpackage`) nos formatos `.msi`/`.exe` (Windows), `.deb`/`.rpm` (Linux) e `.dmg` (macOS), sem exigir download avulso de dependências.
* **3.3.7.3 —** Toda a interface e mensagens devem estar em português do Brasil (pt-BR).
* **3.3.7.4 —** O PDF de holerite gerado deve ser compatível com LibreOffice Calc/Writer, Microsoft Excel e Adobe Acrobat Reader.

### 3.3.8 Conformidade Legal e Regulatória
* **3.3.8.1 —** O sistema deve aplicar somente as alíquotas e tabelas aprovadas para a competência real em processamento.
* **3.3.8.2 —** O sistema deve manter inalteradas as apurações de meses já encerrados, bloqueando qualquer manipulação retroativa.
* **3.3.8.3 —** O holerite deve conter todas as informações exigidas pelo Art. 464 da CLT.
* **3.3.8.4 —** Folhas encerradas devem ser retidas por no mínimo 5 anos, em conformidade com o prazo prescricional trabalhista.
* **3.3.8.5 —** O sistema deve possibilitar a atualização de parâmetros legais em até 5 dias úteis após publicação oficial da nova legislação.
* **3.3.8.6 —** O relatório de fechamento deve indicar claramente qual tabela de parâmetros legais foi utilizada nos cálculos.

### 3.3.9 Privacidade
* **3.3.9.1 —** O sistema deve exibir o CPF do funcionário mascarado (`***.XXX.XXX-**`) em todos os documentos e telas de listagem, em conformidade com a LGPD.

## 3.4 Requisitos de Dados
### 3.4.1 Modelo de Dados
* **3.4.1.1 —** O sistema deve utilizar banco de dados relacional (MySQL ou MariaDB) para armazenamento persistente.
* **3.4.1.2 —** O esquema de dados deve compreender, no mínimo, as seguintes entidades: `Empresa`, `ParametrosLegais`, `Usuario`, `Funcionario`, `HistoricoSalarial`, `Rubrica`, `FolhaPagamento`, `LancamentoExcecao`, `Holerite`, `RelatorioMensal` e `Auditoria`.
* **3.4.1.3 —** O sistema deve armazenar o histórico completo de alterações salariais e promoções de cada funcionário.
* **3.4.1.4 —** A tabela de lançamentos de exceção deve ser vinculada à competência corrente e ao funcionário, tornando-se somente leitura após o fechamento.
* **3.4.1.5 —** A tabela de holerites deve armazenar os reflexos imutáveis mensais como snapshot, não como referências mutáveis a outras tabelas.

### 3.4.2 Integridade e Segurança de Dados
* **3.4.2.1 —** O sistema deve garantir integridade referencial dos dados, assegurando que registros históricos ou dependentes (holerites, lançamentos) não sejam perdidos ou corrompidos caso um funcionário seja inativado.
* **3.4.2.2 —** O sistema deve suportar rotina de backup diário automático para prevenção de perda de dados.
* **3.4.2.3 —** O sistema deve criptografar, no banco de dados, campos sensíveis identificáveis individualmente, como CPF e dados bancários.

# 4. Apêndices

## 4.1 Tabela de Rubricas Padrão

|Código|Descrição|Natureza|Tipo|Incide INSS|Incide FGTS|Observação|
|---|---|---|---|---|---|---|
|001|Salário Base|Provento|Fixo|Sim|Sim|Obrigatória — não editável|
|002|Hora Extra 50%|Provento|Variável|Sim|Sim|Obrigatória — não editável|
|003|Hora Extra 100%|Provento|Variável|Sim|Sim|Obrigatória — não editável|
|004|Bônus / Gratificação|Provento|Variável|Sim|Sim|Obrigatória — não editável|
|005|Cesta Básica|Provento|Fixo|Não|Não|Obrigatória — não editável|
|006|PLR|Provento|Variável|Não|Não|Conf. Lei 10.101/2000|
|007|Adiantamento Salarial|Desconto|Variável|Não|Não|—|
|008|Outros Descontos|Desconto|Variável|Não|Não|—|
|101|Desconto INSS|Desconto|Fixo|—|—|Calculado automaticamente|
|102|Desconto por Falta|Desconto|Variável|Não|Não|—|
|103|Desconto por Atraso|Desconto|Variável|Não|Não|—|
|104|Desconto DSR|Desconto|Variável|Não|Não|Vinculado às faltas|
|105|Desconto Atestado|Desconto|Variável|Não|Não|Zerado pelo atestado|
|106|Outros Descontos|Desconto|Variável|Não|Não|—|

---

## 4.2 Modelos de Fórmulas de Cálculo

### 4.2.1 Apuração de Frequência

|||
|---|---|
|📐 **F-01 — Valor-Dia [RN-02]** Valor_Dia = Salário_Base ÷ Dias_Úteis_do_Mês|📐 **F-02 — Valor-Hora** Valor_Hora = Salário_Base ÷ Horas_Mensais_Contratuais|

|||
|---|---|
|📐 **F-03 — Desconto por Falta [RN-03]** Desconto_Dia = Valor_Dia × Nº_Dias_Faltados Desconto_Hora = Valor_Hora × Nº_Horas_Faltadas|📐 **F-04 — Desconto por Atraso [RN-04]** Desconto_Atraso = Valor_Hora × (Minutos ÷ 60) Tolerância: atrasos ≤ 10 min/dia não geram desconto|

||
|---|
|📐 **F-05 — DSR Perdido [RN-05]** Desconto_DSR = Valor_Dia × Nº_Semanas_com_Falta|

||
|---|
|📐 **F-06 — Hora Extra [RN-06]** HE_50% = Valor_Hora × 1,50 × Qtd_Horas HE_100% = Valor_Hora × 2,00 × Qtd_Horas|

### 4.2.2 Composição do Salário

||
|---|
|📐 **F-07 — Salário Proporcional [RN-07]** Salário_Proporcional = (Salário_Base ÷ Dias_Úteis) × Dias_Trabalhados Exemplo: Admissão em 10/03 \| Dias úteis = 21 \| Dias trabalhados = 16 Salário_Proporcional = (4.400,00 ÷ 21) × 16 = R$ 3.352,38|

||
|---|
|📐 **F-08 — Salário Bruto [RN-08]** Salário_Bruto = Salário_Base_Prop. + Total_Horas_Extras + Total_Bônus_com_Incidência + Cesta_Básica (NÃO incide INSS/FGTS) + PLR (NÃO incide INSS/FGTS) Base_INSS_FGTS = Sal.Base_Prop. + HE + Bônus_com_Incidência|

||
|---|
|📐 **F-09 — Salário Líquido [RN-11]** Salário_Líquido = Σ Proventos − Σ Descontos Σ Proventos = Sal.Base_Prop. + HE + Bônus + Cesta + PLR + DSR Σ Descontos = INSS + Faltas + Atrasos + DSR_Perdido + Outros Restrição legal: Salário_Líquido ≥ R$ 0,00 (CLT Art. 462 [RN-10])|

### 4.2.3 Desconto de INSS — Tabela Progressiva 2025

|Faixa|De (R$)|Até (R$)|Alíquota|Observação|
|---|---|---|---|---|
|1ª|0,01|1.518,00|7,5%|—|
|2ª|1.518,01|2.793,88|9,0%|—|
|3ª|2.793,89|4.190,83|12,0%|—|
|4ª|4.190,84|8.157,41|14,0%|—|
|Teto|8.157,42|—|—|Contribui até o limite de R$ 8.157,41|

||
|---|
|✅ **Cálculo INSS progressivo — Base de cálculo R$ 5.000,00** 1ª faixa: R$ 1.518,00 × 7,5% = R$ 113,85 2ª faixa: R$ 1.275,88 × 9,0% = R$ 114,83 3ª faixa: R$ 1.396,95 × 12,0% = R$ 167,63 4ª faixa: R$ 809,17 × 14,0% = R$ 113,28 ───────────────────────────── TOTAL INSS = R$ 509,59 \| Alíquota efetiva: 10,19%|

### 4.2.4 Encargos e Provisões do Empregador

||
|---|
|📐 **F-10 — FGTS Mensal [RN-12]** FGTS_Mensal = Base_FGTS × 8% Exemplo: Base FGTS = R$ 3.500,00 FGTS_Mensal = 3.500,00 × 0,08 = R$ 280,00 Obs.: encargo do empregador; NÃO é deduzido do salário do funcionário.|

||
|---|
|📐 **F-11 — Provisão Mensal de 13º Salário [RN-13]** Provisão_13_Mês = Salário_Base ÷ 12 Provisão_13_Acumulada = Σ Provisão_13_Mês (janeiro a dezembro) Exemplo: Salário Base = R$ 3.600,00 Provisão_13_Mês = 3.600,00 ÷ 12 = R$ 300,00|

||
|---|
|📐 **F-12 — Provisão Mensal de Férias + ⅓ Constitucional [RN-14]** Provisão_Férias_Mês = (Salário_Base ÷ 12) × (4 ÷ 3) Férias_Mês = Salário_Base ÷ 12; Adicional_⅓ = Férias_Mês ÷ 3 Exemplo: Salário Base = R$ 3.600,00 Férias_Mês = R$ 300,00 + Adicional = R$ 100,00 Provisão = R$ 400,00/mês|

---

## 4.3 Glossário Expandido

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

---

## 4.4 Matriz de Rastreabilidade — Requisitos × Módulos × Telas

|Req. Funcional|Módulo|Tela GUI|Casos de Uso|Regras de Negócio|
|---|---|---|---|---|
|3.2.1.1 — Cadastro da Empresa|Cadastros|3.1.5|UC-02, UC-03|—|
|3.2.1.2 — Cadastro de Funcionários|Cadastros|3.1.4|UC-06, UC-07|RN-01|
|3.2.1.3 — Cadastro de Rubricas|Cadastros|3.1.6|UC-08|—|
|3.2.1.4 — Usuários do Sistema|Cadastros|3.1.11|UC-04, UC-05|—|
|3.2.1.5 — Parâmetros Legais|Cadastros / Config.|3.1.5|UC-17|—|
|3.2.2 — Apuração de Frequência|Exceções Mensais|3.1.7|UC-09|RN-02 a RN-06|
|3.2.3 — Proventos|Processamento|3.1.8|UC-10|RN-07, RN-08|
|3.2.4 — Descontos|Processamento|3.1.8|UC-10|RN-09, RN-10, RN-11|
|3.2.5 — Encargos / Provisões|Processamento|3.1.8 (relatório)|UC-11|RN-12, RN-13, RN-14|
|3.2.6.1 — Holerite|Saídas|3.1.9|UC-12, UC-13|RN-15|
|3.2.6.2/.3 — Relatórios|Saídas|3.1.9|UC-14|—|
|3.2.6.4 — Histórico|Auditoria/Saídas|3.1.10|UC-15|RN-15|
|3.2.6.5 — Log de Auditoria|Auditoria|3.1.12|UC-16|—|

---

## 4.5 Tabela Consolidada de Regras de Negócio (RN-01 a RN-15)

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

---

# 5. Índice
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
        - 3.1.3 Menu Principal e Navegação (Dashboard)
        - 3.1.4 Tela de Gerenciamento de Funcionários
        - 3.1.5 Tela de Cadastro da Empresa
        - 3.1.6 Tela de Gerenciamento de Rubricas 
        - 3.1.7 Tela de Lançamentos de Exceção Mensais
        - 3.1.8 Tela de Processamento e Fechamento da Folha
        - 3.1.9 Tela de Visualização e Emissão de Holerite
        - 3.1.10 Tela de Histórico de Folhas de Pagamento
        - 3.1.11 Tela de Gerenciamento de Usuários do Sistema 
        - 3.1.12 Tela de Log de Auditoria
    - 3.2 Requisitos Funcionais
        - 3.2.1 Cadastros e Configurações
            - 3.2.1.1 Cadastro da Empresa
            - 3.2.1.2 Cadastro de Funcionários
            - 3.2.1.3 Cadastro de Rubricas
            - 3.2.1.4 Cadastro de Usuários do Sistema
            - 3.2.1.5 Atualização de Parâmetros Legais
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
            - 3.2.3.3 Bônus e Gratificações
            - 3.2.3.4 Cesta Básica
            - 3.2.3.5 Participação nos Lucros e Resultados (PLR)
            - 3.2.3.6 Composição do Salário Bruto
        - 3.2.4 Processamento de Descontos
        - 3.2.5 Encargos e Provisões do Empregador
        - 3.2.6 Saídas e Auditoria
        - 3.2.7 Quadro Consolidado de Regras de Negócio (RN-01 a RN-15)
    - 3.3 Requisitos Não Funcionais
        - 3.3.1 Desempenho
        - 3.3.2 Segurança
        - 3.3.3 Confiabilidade
        - 3.3.4 Usabilidade
        - 3.3.5 Disponibilidade
        - 3.3.6 Manutenibilidade
        - 3.3.7 Portabilidade
        - 3.3.8 Conformidade Legal e Regulatória
        - 3.3.9 Privacidade
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
    - 4.4 Matriz de Rastreabilidade — Requisitos × Módulos × Telas × Casos de Uso
    - 4.5 Tabela Consolidada de Regras de Negócio (RN-01 a RN-15)
5. Índice