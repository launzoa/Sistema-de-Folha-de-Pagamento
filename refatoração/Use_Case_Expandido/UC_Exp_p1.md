| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-01 — Autenticar no sistema |
| **Atores** | RH / Administrador (iniciador) |
| **Finalidade** | Verificar se o usuário que está acessando o sistema possui credenciais válidas e permissão para utilizá-lo. |
| **Visão geral** | O usuário inicia o sistema e é direcionado à tela de autenticação. Ele informa usuário e senha, e o sistema valida as credenciais e o status do usuário cadastrado. Caso os dados estejam corretos e o usuário esteja ativo, o sistema libera o acesso e identifica o perfil do operador. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.2.1, 3.1.2.2, 3.1.2.3, 3.1.2.4, 3.1.2.5, 3.1.2.6, 3.1.2.7 — 3.3.2.1 (3.3.2.1.1, 3.3.2.1.2, 3.3.2.1.3) — 3.3.2.2 (3.3.2.2.1 a 3.3.2.2.4) — 3.3.2.5 |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-02 — Cadastro da Empresa |
| **Atores** | Administrador (iniciador) |
| **Finalidade** | Realizar o cadastro dos dados corporativos da empresa, como CNPJ, razão social e informações cadastrais necessárias ao funcionamento do sistema. |
| **Visão geral** | A operação é prevista para usuário com perfil de Administrador. O administrador acessa a área de cadastro da empresa, informa os dados corporativos e cadastrais solicitados, revisa as informações e confirma o cadastro. Após a confirmação, o sistema valida e persiste os dados da empresa. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.5.1 (3.1.5.1.1 a 3.1.5.1.4), 3.1.5.2, 3.1.5.3 (3.1.5.3.1 a 3.1.5.3.4), 3.1.5.4 — 3.2.1.1 (3.2.1.1.1, 3.2.1.1.2, 3.2.1.1.3) |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-03 — Atualizar dados da Empresa |
| **Atores** | Administrador (iniciador) |
| **Finalidade** | Realizar a atualização das informações cadastrais da empresa quando necessário. |
| **Visão geral** | A operação é prevista para usuário com perfil de Administrador. O administrador acessa a área de cadastro da empresa, consulta os dados já cadastrados, altera as informações necessárias, revisa os dados modificados e confirma a atualização. Após a confirmação, o sistema valida e persiste as alterações. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.5.1 (3.1.5.1.1 a 3.1.5.1.4), 3.1.5.2, 3.1.5.3 (3.1.5.3.1 a 3.1.5.3.4), 3.1.5.4 — 3.2.1.1 (3.2.1.1.1, 3.2.1.1.2, 3.2.1.1.3) |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-04 — Cadastro de Usuários do Sistema |
| **Atores** | Administrador (iniciador) |
| **Finalidade** | Realizar o cadastro de novos usuários do sistema, definindo suas credenciais e seus perfis de acesso. |
| **Visão geral** | A operação é prevista para usuário com perfil de Administrador. O administrador acessa a área de cadastro de usuários, informa os dados do novo usuário, define seu perfil de acesso como RH ou Administrador, revisa as informações e confirma o cadastro. Após a confirmação, o sistema valida e persiste o novo usuário. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.11.1, 3.1.11.2, 3.1.11.3, 3.1.11.4, 3.1.11.5 — 3.2.1.4 (3.2.1.4.1, 3.2.1.4.2, 3.2.1.4.3, 3.2.1.4.4) — 3.3.2.2 (3.3.2.2.1 a 3.3.2.2.4) |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-05 — Atualizar Usuários no Sistema |
| **Atores** | Administrador (iniciador) |
| **Finalidade** | Realizar a edição de dados de usuários existentes, permitindo alterar nome de usuário, redefinir senha e modificar o status da conta. |
| **Visão geral** | A operação é prevista para usuário com perfil de Administrador. O administrador acessa a área de cadastro de usuários, seleciona um usuário existente, altera os dados permitidos, redefine senha ou modifica o status da conta quando necessário. Em seguida, revisa as informações e confirma a atualização para que o sistema persista as alterações. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.11.1, 3.1.11.2, 3.1.11.4, 3.1.11.5 — 3.2.1.4 (3.2.1.4.1, 3.2.1.4.2, 3.2.1.4.3, 3.2.1.4.4) — 3.3.2.2 (3.3.2.2.3, 3.3.2.2.4) |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-06 — Cadastro de Funcionários |
| **Atores** | RH / Administrador (iniciador) |
| **Finalidade** | Realizar o cadastro de funcionários no sistema, registrando nome, CPF, cargo, data de admissão, salário bruto e status inicial ativo, informações necessárias ao processamento da folha. |
| **Visão geral** | A operação é prevista para usuário com perfil de RH ou Administrador. O usuário acessa a área de cadastro de funcionários, informa os dados pessoais e contratuais do funcionário, revisa os dados preenchidos e confirma o cadastro. Após a confirmação, o sistema valida e persiste o novo funcionário. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.4.1, 3.1.4.2, 3.1.4.4 (3.1.4.4.1 a 3.1.4.4.7), 3.1.4.5, 3.1.4.6, 3.1.4.7 — 3.2.1.2 (3.2.1.2.1 a 3.2.1.2.9) — RN-01 |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-07 — Atualizar dados de Funcionários |
| **Atores** | RH / Administrador (iniciador) |
| **Finalidade** | Realizar a atualização de dados permitidos de funcionários, além de permitir a inativação de funcionários. |
| **Visão geral** | A operação é prevista para usuário com perfil de RH ou Administrador. O usuário acessa a área de cadastro de funcionários, seleciona um funcionário já cadastrado e altera os dados permitidos, como nome, cargo, data de admissão, salário bruto e status, mantendo o CPF como identificador não editável. Em seguida, revisa as informações e confirma a atualização para que o sistema persista as alterações sem excluir o histórico do funcionário. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.4.1, 3.1.4.2, 3.1.4.3, 3.1.4.8, 3.1.4.9, 3.1.4.10 — 3.2.1.2 (3.2.1.2.1, 3.2.1.2.5, 3.2.1.2.6, 3.2.1.2.7, 3.2.1.2.8, 3.2.1.2.9) — RN-01 |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-08 — Cadastro de Rubricas |
| **Atores** | Administrador (iniciador) |
| **Finalidade** | Realizar o cadastro de rubricas de proventos e descontos, configurando sua natureza, tipo e incidências utilizadas no processamento da folha. |
| **Visão geral** | A operação é prevista para usuário com perfil de Administrador. O administrador acessa a área de cadastro de rubricas, informa os dados da nova rubrica, como descrição, natureza, tipo de cálculo e incidências. Em seguida, revisa as informações e confirma o cadastro para que o sistema valide e persista a nova rubrica. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.6.1, 3.1.6.2, 3.1.6.3, 3.1.6.4, 3.1.6.5 — 3.2.1.3 (3.2.1.3.1 a 3.2.1.3.5) |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-09 — Atualizar Rubricas |
| **Atores** | Administrador (iniciador) |
| **Finalidade** | Realizar a edição de rubricas personalizadas já cadastradas, ajustando descrição, natureza, tipo, incidências e status. |
| **Visão geral** | A operação é prevista para usuário com perfil de Administrador. O administrador acessa a área de cadastro de rubricas, seleciona uma rubrica personalizada já cadastrada, não pertencente ao conjunto de rubricas padrão, e altera os dados permitidos, como descrição, natureza, tipo de cálculo, incidências e status. Em seguida, revisa as informações e confirma a atualização para que o sistema persista as alterações. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.6.1, 3.1.6.2, 3.1.6.3, 3.1.6.4, 3.1.6.5 — 3.2.1.3 (3.2.1.3.1 a 3.2.1.3.5) |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-10 — Registrar Lançamentos Mensais |
| **Atores** | RH / Administrador (iniciador) |
| **Finalidade** | Registrar lançamentos mensais vinculados a funcionários e rubricas, com valor, porcentagem ou quantidade, para compor o cálculo da folha da competência. |
| **Visão geral** | A operação é prevista para usuário com perfil de RH ou Administrador. O usuário acessa a área de lançamentos mensais, e o sistema verifica se existe uma folha aberta para receber o lançamento. O usuário seleciona o funcionário, escolhe a rubrica, define a modalidade do lançamento, informa valor, porcentagem ou quantidade e, de forma opcional, a data do ocorrido. Após a confirmação o sistema valida e persiste o lançamento mensal para uso no cálculo da folha. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.7.1, 3.1.7.2, 3.1.7.3, 3.1.7.4 (3.1.7.4.1 a 3.1.7.4.5), 3.1.7.5, 3.1.7.6 — 3.2.2 (3.2.2.1 a 3.2.2.6) — RN-02, RN-03, RN-04, RN-05, RN-06 |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-11 — Atualizar Lançamentos Mensais |
| **Atores** | RH / Administrador (iniciador) |
| **Finalidade** | Editar lançamentos mensais já cadastrados na folha aberta, ajustando funcionário, rubrica, modalidade, valor, quantidade, base de cálculo e data do ocorrido. |
| **Visão geral** | A operação é prevista para usuário com perfil de RH ou Administrador. O usuário acessa a área de lançamentos mensais, e o sistema verifica se existe uma folha aberta para permitir alterações. O usuário seleciona um lançamento mensal já cadastrado, altera os dados permitidos, revisa as informações e confirma a atualização. Após a confirmação, o sistema valida e persiste as alterações do lançamento mensal. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.7.1, 3.1.7.2, 3.1.7.3, 3.1.7.4 (3.1.7.4.1 a 3.1.7.4.5), 3.1.7.5, 3.1.7.6 — 3.2.2 (3.2.2.1 a 3.2.2.6) — RN-02, RN-03, RN-04, RN-05, RN-06 |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-12 — Processar Folha de Pagamento |
| **Atores** | RH / Administrador (iniciador) |
| **Finalidade** | Executar o cálculo da folha da competência, apurando salário líquido, INSS, IRRF, FGTS informativo, proventos e descontos. |
| **Visão geral** | O usuário acessa o módulo de processamento e holerites e seleciona a competência desejada. O sistema carrega os dados necessários, como empresa, funcionários, rubricas, parâmetros legais e lançamentos mensais registrados, e executa o cálculo automático dos funcionários aplicáveis à folha. Ao final, o sistema apresenta os resultados calculados para conferência. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.8.1, 3.1.8.2, 3.1.8.3 (3.1.8.3.1 a 3.1.8.3.6), 3.1.8.4 — 3.2.3 (3.2.3.1 a 3.2.3.6) — 3.2.4 (3.2.4.1, 3.2.4.3, 3.2.4.4, 3.2.4.5) — 3.2.5 (3.2.5.1 a 3.2.5.3) — RN-07, RN-08, RN-09, RN-10, RN-11, RN-12, RN-13, RN-14 |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-13 — Gerar Holerites |
| **Atores** | RH / Administrador (iniciador) |
| **Finalidade** | Consultar demonstrativos individuais de pagamento e permitir a exportação de holerites individuais, holerites da competência e arquivo geral da folha em PDF. |
| **Visão geral** | O usuário acessa o módulo de holerites. O sistema carrega a folha da competência consultada e calcula os demonstrativos disponíveis. O usuário seleciona a competência e, quando necessário, o funcionário desejado. O sistema exibe o demonstrativo de pagamento com dados do funcionário, proventos, descontos, bases legais e salário líquido. O usuário pode exportar o holerite individual, os holerites da competência ou o arquivo geral da folha em PDF. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.9.1, 3.1.9.2 (3.1.9.2.1 a 3.1.9.2.7), 3.1.9.3, 3.1.9.4, 3.1.9.5 — 3.2.6.1 (3.2.6.1.1, 3.2.6.1.2, 3.2.6.1.3) — 3.2.6.2 (3.2.6.2.1, 3.2.6.2.2) — 3.3.1.4 — 3.3.2.3.4 — 3.3.8.3 |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-14 — Consultar Histórico de Folhas |
| **Atores** | RH / Administrador (iniciador) |
| **Finalidade** | Consultar folhas cadastradas por competência, permitindo visualizar demonstrativos individuais e informações gerais associadas à competência selecionada. |
| **Visão geral** | O usuário acessa o módulo de processamento e holerites e seleciona uma competência anterior disponível. O sistema carrega a folha correspondente, busca os lançamentos vinculados e apresenta os demonstrativos e informações da competência selecionada. |
| **Tipo** | Secundário e opcional |
| **Referências cruzadas** | Requisitos: 3.1.10.1, 3.1.10.2, 3.1.10.3, 3.1.10.4, 3.1.10.5 — 3.2.6.4 (3.2.6.4.1, 3.2.6.4.2, 3.2.6.4.3) — 3.3.8.4 |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-15 — Consultar Log de Auditoria |
| **Atores** | Administrador (iniciador) |
| **Finalidade** | Permitir que o administrador consulte o histórico registrado de ações realizadas no sistema para auditoria de alterações sensíveis. |
| **Visão geral** | A operação é prevista para usuário com perfil de Administrador. O administrador acessa o módulo de auditoria e consulta o histórico de ações registradas no sistema, podendo visualizar informações relacionadas a operações sensíveis, como login, logout, cadastros, edições e exclusões registradas. |
| **Tipo** | Secundário e opcional |
| **Referências cruzadas** | Requisitos: 3.1.12.1, 3.1.12.2, 3.1.12.3, 3.1.12.4 — 3.2.6.5 (3.2.6.5.1 a 3.2.6.5.4) — 3.3.2.5 (3.3.2.5.1 a 3.3.2.5.3) |

|

| Campo | Descrição |
| ------------------------ | ------------------------ |
| **Caso de uso** | UC-16 — Atualização de Parâmetros Legais |
| **Atores** | RH / Administrador (iniciador) |
| **Finalidade** | Permitir que o usuário atualize faixas, tetos e alíquotas de INSS e IRRF, além da parcela de dedução do IRRF, sem recompilar o sistema. |
| **Visão geral** | A operação é prevista para usuário com perfil de RH ou Administrador. O usuário acessa a área de rubricas e parâmetros legais. Na aba de parâmetros legais governamentais, consulta as faixas cadastradas de INSS e IRRF, edita os valores permitidos diretamente nas tabelas e o sistema persiste as alterações para uso no processamento da folha. |
| **Tipo** | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.2.1.5 (3.2.1.5.1, 3.2.1.5.2) — 3.3.6.2 — 3.3.8.1, 3.3.8.5 — RN-09 |
