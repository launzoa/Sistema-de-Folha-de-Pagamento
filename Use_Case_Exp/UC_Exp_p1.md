

| Campo                    | Descrição |
| ------------------------ | ------------------------ |
|**Caso de uso**|Autenticar no sistema|
|**Atores**|RH / Administrador (iniciador)|
|**Finalidade**|Verificar que o usuário que esta acessando o sistema tenha permissão para manuseá-lo.|
|**Visão geral**|O usuário inicia o sistema e é direcionando a autenticar sua entrada, realizar o "login". O sistema verifica se as credenciais inseridas pelo usuário estão em conformidade com as registradas no sistema e o sistema libera a entrada do usuário para manuseio do sistema.|
|**Tipo**|Primário e essencial|
|**Referências cruzadas**|Requisitos: a definir|
|||

|

| Campo| Descrição|
| ------------------------ | ------------- |
|**Caso de uso**|Cadastro da Empresa|
|**Atores**|Administrador (iniciador)|
|**Finalidade**|Realizar o cadastro de dados corporativos referentes a empresa como CNPJ e Razão Social, além de outros o parâmetros fiscais.|
|**Visão geral**|O usuário acessa a área de cadastro e depois acessa o módulo de cadastro de Empresa. O sistema verifica se o usuário é o administrador, o qual tem permissão para cadastrar a empresa. O administrador insere os dados referentes a empresa, revisa-os e, por fim, confirma o cadastro para o sistema realizar a permanência dos dados.|
|**Tipo**|Primário e essencial|
|**Referências cruzadas**|Requisitos: a definir|
|||

|


| Campo                    | Descrição                                                                                                                                                                                                                                                                                                         |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Atualizar dados da Empresa                                                                                                                                                          |
| **Atores**               | Administrador (iniciador)                                                                                                                                                                                                                                                                                    |
| **Finalidade**           |   Realizar a atualização de dados corporativos referentes a empresa como CNPJ e Razão Social, além de outros o parâmetros fiscais quando necessário.                                                                                                                        |
| **Visão geral**          | O usuário acessa a área de cadastro e depois acessa o módulo de cadastro de Empresa. O sistema verifica se o usuário é o administrador, o qual tem permissão para atualizar dados referentes a empresa. O administrador realiza a alteração dos dados referentes a empresa desejados, revisa-os e, por fim, confirma a atualização para o sistema realizar a permanência dos dados. |
| **Tipo**                 | Primário e essencial                                                                                                                                                                                                                                                                                              |
| **Referências cruzadas** |  Requisitos: a definir

|


| Campo                    | Descrição                                                                                                                                                                                                                                                                                                         |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Cadastro de Usuários do Sistema                                                                                                                                      |
| **Atores**               | Administrador (iniciador)                                                                                                                                                                                                                                                                                    |
| **Finalidade**           | Realizar o cadastro de novos usuários no sistema definindo seus perfis de acesso para o uso do sistema de acordo com seu nível de acesso.                                                                                                                         |
| **Visão geral**          | O administrador acessa a área de cadastro e depois acessa o módulo de cadastro de usuários. O sistema verifica se o usuário é o administrador, o qual tem permissão para cadastrar novos usuários, seja esses outros administradores ou profissionais do Rh. O administrador insere os dados referentes ao nome de usuário e senha, revisa-os e, por fim, confirma o cadastro para o sistema realizar a permanência desse novo usuário. |
| **Tipo**                 | Primário e essencial                                                                                                                                                                                                                                                                                              |
| **Referências cruzadas** |  Requisitos: a definir      

|

| Campo                    | Descrição                                                                                                                                                                                                                                                                                                         |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Atualizar Usuários no Sistema                                                                                                                                      |
| **Atores**               | Administrador (iniciador)                                                                                                                                                                                                                                                                                    |
| **Finalidade**           | Realizar a edição de dados referentes a usuário existentes no sistema, sendo permitido a ele alterar os perfis de acesso e inativar usuáros.                                                                                                                         |
| **Visão geral**          | O administrador acessa a área de cadastro e depois acessa o módulo de cadastro de usuários. O sistema verifica se o usuário é o administrador, o qual tem permissão para editar perfis de usuários, seja para alterar nome de usuário, redefinir senhas ou inativar contas existente. O administrador realização a alteração do que deseja, revisa-os e, por fim, confirma a atualização para o sistema realizar a permanência desse usuário. |
| **Tipo**                 | Primário e essencial                                                                                                                                                                                                                                                                                              |
| **Referências cruzadas** |  Requisitos: a definir      

|

| Campo                    | Descrição                                                                                                                                                                                                                                                                                                         |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Cadastro de Funcionários                                                                                                    |
| **Atores**               | RH / Administrador (iniciador)                                                                                                                                                                                                                                                                                    |
| **Finalidade**           | Realizar o cadastro de novos funcionários no sistema definindo seus dados de pessoais, contratuais e informações gerais.                                                                                                                         |
| **Visão geral**          | O usuário acessa a área de cadastro e depois acessa o módulo de cadastro de funcionários. O sistema verifica se o usuário é válido. O usuário insere os dados referentes ao novo funcionário, como dados pessoais, contratuais e informações gerais. Revisa-os e, por fim, confirma o cadastro para o sistema realizar a permanência desse novo funcionário. |
| **Tipo**                 | Primário e essencial                                                                                                                                                                                                                                                                                              |
| **Referências cruzadas** |  Requisitos: a definir      

|

| Campo                    | Descrição                                                                                                                                                                                                                                                                                                         |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Atualizar dados de Funcionários                                                                                                    |
| **Atores**               | RH / Administrador (iniciador)                                                                                                                                                                                                                                                                                    |
| **Finalidade**           | Realizar a atualização de funcionários no sistema como dados contratuais ou inativar funcionários desligados da empresa. ou                                                                                                                          |
| **Visão geral**          | O usuário acessa a área de cadastro e depois acessa o módulo de cadastro de funcionários. O sistema verifica se o usuário é válido. O usuário define se deseja alterar dados pessoais ou contratuais ou inativar um funcionário que não faz mais parte da empresa, mas não excluir seus dados. Revisa-os e, por fim, confirma a ação para o sistema realizar a permanência dessa ação. |
| **Tipo**                 | Primário e essencial                                                                                                                                                                                                                                                                                              |
| **Referências cruzadas** |  Requisitos: a definir    


|

| Campo                    | Descrição                                                                                                                                                                                                                                                                                                         |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Cadastro de Rubricas                                                                                                   |
| **Atores**               | Administrador (iniciador)                                                                                                                                                                                                                                                                                    |
| **Finalidade**           | Realizar o cadastro e personalização de novas rubricas referentes a proventos e descontos  com suas incidências sobre o salário bruto.                                                                               |
| **Visão geral**          | O administrador acessa a área de cadastro e depois acessa o módulo de cadastro de rubricas. O sistema verifica se o usuário é o administrador para permitir manipulação de rubricas. O administrador insere ou atualiza os dados referentes a rubrica desejada, como rubrica de proventos ou descontos. Revisa-os e, por fim, confirma o cadastro ou atualizaão para o sistema realizar a permanência. |
| **Tipo**                 | Primário e essencial                                                                                                                                                                                                                                                                                              |
| **Referências cruzadas** |  Requisitos: a definir  

|

| Campo                    | Descrição                                                                                                                                                                                                                                                                                                         |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Lançar Exceções Mensais                                                                                                    |
| **Atores**               | RH / Administrador (iniciador)                                                                                                                                                                                                                                                                                    |
| **Finalidade**           | Realizar o lançamento de variáveis referente ao mês em aberto para funcionários específicos.                                                                                                                         |
| **Visão geral**          | O usuário acessa a área de lançamento de excessções. O sistema verifica se o usuário é válido e se o status da folha está em aberto para receber novas variáveis. O usuário busca pelo funcionário que deseja realizar os lançamentos no mês em aberto. Ele insere os dados referentes para o lançamento, como horas extras, faltas, atrasos, bônus ou descontos. Revisa-os e, por fim, confirma o cadastro para o sistema realizar a permanência desse lançamento. |
| **Tipo**                 | Primário e essencial                                                                                                                                                                                                                                                                                              |
| **Referências cruzadas** |  Requisitos: a definir      

|

| Campo                    | Descrição                                                                                                                                                                                                                                                                                                         |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Processar Folha de Pagamento                                                                                                                                                          |
| **Atores**               | RH / Administrador (iniciador)                                                                                                                                                                                                                                                                                    |
| **Finalidade**           | Calcular o salário líquido de cada funcionário ativo no mês de competência, aplicando proventos, descontos de INSS e gerando provisões de FGTS, 13º salário e férias.                                                                                                                                              |
| **Visão geral**          | O usuário acessa o módulo de processamento com a folha do mês aberta. O sistema verifica os pré-requisitos (cadastros e exceções lançadas), executa o cálculo automático para todos os funcionários ativos e exibe um resumo consolidado. O usuário revisa os resultados e, se corretos, confirma o processamento. |
| **Tipo**                 | Primário e essencial                                                                                                                                                                                                                                                                                              |
| **Referências cruzadas** |  Requisitos: 3.2.3, 3.2.4, 3.2.5                                                                                                            |

|

| Campo                    | Descrição                                                                                                                                                                                                                                                                                                         |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Fechar Folha de Pagamento                                                                                                                                                          |
| **Atores**               | RH / Administrador (iniciador)                                                                                                                                                                                                                                                                                    |
| **Finalidade**           | Encerra a folha do mês em aberto de competência, assim, tornando os dados referentes a folha imutáveis.                                                                                                                                              |
| **Visão geral**          | O usuário acessa o módulo de processamento com a folha do mês aberta. O sistema verifica se a folha do mês atual está em aberta. O usuário revisa o processamento da folha atual, como a concordância dos salários líquidos com as normas impostas pela CLT  e, se corretos, confirma o fechamento da folha, com ciência que seu estado não pode ser revertível. |
| **Tipo**                 | Primário e essencial                                                                                                                                                                                                                                                                                              |
| **Referências cruzadas** |  Requisitos: a definir                                                                                                            |

|

| Campo                    | Descrição                                                                                                                                                                                                                                                                                                         |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Emitir Holerites                                                                           |
| **Atores**               | RH / Administrador (iniciador)                                                                                                                            |
| **Finalidade**           | Realiza a condulta de um funcionário desejado e exibe seu demonstrativo individual o pagamento.                                                                                                                                               |
| **Visão geral**          | O usuário acessa o módulo de holerite. O sistema verifica se a data da consulta feita aprensenta um mês com uma folha de pagamento já fechada. O usuário realiza a consulta do funcionário e emite o holerite referente a ele. O holerite contém informações pessoais e dados referentes ao salário. |
| **Tipo**                 | Primário e essencial                                                                                                                                  |
| **Referências cruzadas** |  Requisitos: a definir                                                                                                            |

|


| Campo                    | Descrição                     |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Exportar Holerites                                       |
| **Atores**               | RH / Administrador (iniciador)         |
| **Finalidade**           | Com o holerite gerado o usuário realiza a ação de exportar o documento em formato PDF.                                           |
| **Visão geral**          | O usuário acessa o módulo de holerite. O sistema deve verificar se o holerite corresponde a um mês fechado e o usuário verifica se há erros no documento. Caso esteja em conformidade, o usuário exporta o documento em formato PDF. |
| **Tipo**                 | Secundário e Opcional      |
| **Referências cruzadas** |  Requisitos: a definir      |

|

| Campo                    | Descrição                     |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Emitir Relatórios Mensais	                                       |
| **Atores**               | RH / Administrador (iniciador)         |
| **Finalidade**           | Após o fechamento da folha de pagamento o usuário pode emitir um relatório da folha referente ao mês desejado.                                           |
| **Visão geral**          | O usuário acessa o módulo de processamento da folha. O sistema deve verificar se o mês atual corresponde a um mês fechado. Caso esteja em conformidade, o usuário gera o relatório da folha de pagamento ou um arquivo de remessa bancária. |
| **Tipo**                 | Secundário e Opcional      |
| **Referências cruzadas** |  Requisitos: a definir      |

|

| Campo                    | Descrição                     |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Consultar Histórico de Folhas 	                                       |
| **Atores**               | RH / Administrador (iniciador)         |
| **Finalidade**           | O usuário pode consultar dados consolidados e individuais de folhas de meses encerrados.                                            |
| **Visão geral**          | O usuário acessa o módulo de históricos e realiza uma busca referente a dados consolidados de meses anteriores. O sistema deve verificar se o mês buscado corresponde a um mês fechado. Caso esteja em conformidade, o usuário recebe os dados desejados referentes a folhas gerais ou de um funcionário em específico. |
| **Tipo**                 | Secundário e Opcional      |
| **Referências cruzadas** |  Requisitos: a definir     |

|

| Campo                    | Descrição                     |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Consultar Log de Auditoria 	                                       |
| **Atores**               | Administrador (iniciador)         |
| **Finalidade**           | O administrador pode consultar dados do histórico de ações no sistema para auditoria de alterações.                                            |
| **Visão geral**          | O administrador acessa o módulo de auditoria. O sistema deve verificar se o usuário corresponde a um administrador. Caso esteja em conformidade, o administrador pode realizar o acesso do histórico imutável de ações do sistema para auditoria de alterções sensíveis. |
| **Tipo**                 | Secundário e Opcional      |
| **Referências cruzadas** |  Requisitos: a definir     |

|

| Campo                    | Descrição                     |
| ------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Caso de uso**          | Atualização de Parâmetros Legais 	                                       |
| **Atores**               | Administrador (iniciador)         |
| **Finalidade**           | O administrador pode atualizar alíquotas e tabelas legais sem precisar recompilar o sistema.                                            |
| **Visão geral**          | O administrador acessa o módulo de cadastro da empresa. O sistema deve verificar se o usuário corresponde a um administrador. Caso esteja em conformidade, o administrador pode realizar a alteração das alíquotas e tabelas legais, como: INSS, piso salarial e FGTS, sem a necessidade de recompilar o sistema. |
| **Tipo**                 | Primário e Opcional      |
| **Referências cruzadas** |  Requisitos: a definir     |  
