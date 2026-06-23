| **Caso de Uso** | UC-01 — Autenticar no sistema |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia o sistema.||
||2. Exibe a tela de login com os campos "Usuário" e "Senha".|
|3. Preenche o campo "Usuário" e o campo "Senha".||
||4. Recebe as credenciais informadas e realiza a consulta dos dados cadastrados no sistema.|
||5. Compara as credenciais com as informações armazenadas e verifica se o usuário está ativo.|
||6. Caso as credenciais estejam corretas, registra o login no log de auditoria.|
||7. Exibe a tela principal do sistema, apresentando o menu lateral e identificando o usuário e o perfil ativo do operador.|
|||

|

| **Caso de Uso** | UC-02 — Cadastro da Empresa |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia acessando a tela de Empresas pelo menu principal.||
||2. Exibe os dados da empresa cadastrada ou indica que ainda não há empresa registrada no sistema.|
|3. Indica que deseja cadastrar uma nova empresa.||
||4. Exibe o formulário de empresa com os campos necessários para preenchimento.|
|5. O administrador preenche os dados da empresa, como CNPJ, razão social, e-mail, responsável legal, dia de fechamento do ponto e, quando necessário, endereço vinculado à empresa.||
|6. Confirma que deseja salvar o cadastro da empresa.||
||7. Valida se os campos obrigatórios foram preenchidos corretamente.|
||8. Persiste os dados da empresa no sistema, incluindo o endereço vinculado quando informado.|
||9. Ao retornar para a tela de Empresas, exibe os dados cadastrados e atualiza as informações apresentadas.|
|||

|

| **Caso de Uso** | UC-03 — Atualizar dados da Empresa |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia acessando a tela de Empresas pelo menu principal.||
||2. Exibe os dados atuais da empresa cadastrada no sistema.|
|3. Seleciona a opção de edição da empresa cadastrada.||
||4. Exibe o formulário de edição com os dados atuais da empresa, mantendo o CNPJ como campo não editável.|
|5. Altera os campos desejados, como razão social, e-mail, responsável legal, dia de fechamento do ponto ou endereço vinculado à empresa.||
|6. Confirma que deseja salvar as alterações realizadas.||
||7. Valida se os campos obrigatórios foram preenchidos corretamente.|
||8. Persiste as alterações da empresa no sistema, incluindo os dados de endereço quando alterados.|
||9. Ao retornar para a tela de Empresas, atualiza os dados exibidos com as novas informações.|
|||

|

| **Caso de Uso** | UC-04 — Cadastro de Usuários do Sistema |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia acessando a tela de Usuários pelo menu principal.||
||2. Exibe a listagem de usuários cadastrados no sistema, apresentando nome, perfil e status.|
|3. Informa que deseja realizar o cadastro de um novo usuário.||
||4. Exibe o formulário de cadastro com campos para nome de usuário, senha e perfil.|
|5. Preenche os dados do novo usuário e seleciona o perfil desejado, como Administrador ou Operador/RH.||
|6. Confirma o cadastro do usuário.||
||7. Valida se os campos obrigatórios foram preenchidos e se as informações estão adequadas para cadastro.|
||8. Persiste o novo usuário no sistema.|
||9. Ao retornar para a tela de Usuários, atualiza a listagem de usuários cadastrados.|
|||

|

| **Caso de Uso** | UC-05 — Atualizar Usuários no Sistema |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Começa acessando a tela de Usuários pelo menu principal.||
||2. Exibe a listagem de usuários cadastrados no sistema, apresentando nome, perfil e status.|
|3. Seleciona o usuário que deseja alterar.||
||4. Exibe o formulário de edição com os dados atuais do usuário, mantendo o perfil como campo não editável.|
|5. Realiza as alterações permitidas, como nome de usuário, senha ou status da conta.||
|6. Confirma que deseja salvar as alterações.||
||7. Valida se os campos obrigatórios foram preenchidos.|
||8. Persiste as mudanças no sistema.|
||9. Ao retornar para a tela de Usuários, atualiza a listagem apresentada.|
|||

|

| **Caso de Uso** | UC-06 — Cadastro de Funcionários |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia acessando a tela de Funcionários pelo menu principal.||
||2. Exibe a listagem de funcionários cadastrados no sistema.|
|3. Indica que deseja realizar o cadastro de um novo funcionário.||
||4. Exibe a janela de cadastro de funcionário.|
|5. O usuário preenche os campos necessários, como nome, CPF, cargo, salário bruto e data de admissão.||
|6. Confirma que deseja salvar o novo funcionário.||
||7. Valida se todos os campos obrigatórios foram preenchidos e se o salário informado possui formato válido.|
||8. Persiste o funcionário com status inicial ativo.|
||9. Exibe mensagem de sucesso no próprio formulário, indicando que o funcionário foi cadastrado.|
|||

|

| **Caso de Uso** | UC-07 — Atualizar dados de Funcionários |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia acessando a tela de Funcionários pelo menu principal.||
||2. Exibe a listagem de funcionários cadastrados no sistema.|
|3. Seleciona o funcionário desejado para edição.||
||4. Exibe a janela de edição com os dados atuais do funcionário, mantendo o CPF como identificador não editável.|
|5. Altera os dados permitidos, como nome, cargo, salário bruto, data de admissão ou status do funcionário.||
|6. Confirma que deseja salvar as alterações.||
||7. Valida se os campos obrigatórios foram preenchidos e se o salário informado possui formato válido.|
||8. Persiste as alterações do funcionário.|
||9. Exibe mensagem de sucesso no próprio formulário, indicando que o funcionário foi atualizado.|
|||

|

| **Caso de Uso** | UC-08 — Cadastro de Rubricas |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia acessando a tela de Rubricas pelo menu principal.||
||2. Exibe a lista de rubricas cadastradas, contendo código, descrição, natureza, tipo, incidências, indicação de rubrica padrão e status.|
|3. Indica que deseja cadastrar uma nova rubrica.||
||4. Exibe o formulário de cadastro de rubrica.|
|5. O administrador informa os dados da rubrica, como código, descrição, natureza, tipo e incidências em INSS, FGTS e IRRF.||
|6. Confirma que deseja salvar a rubrica.||
||7. Valida se os campos obrigatórios foram preenchidos e se o código informado é válido para rubrica personalizada.|
||8. Persiste a nova rubrica no sistema.|
||9. Ao retornar para a tela de Rubricas, atualiza a listagem de rubricas cadastradas.|
|||

|

| **Caso de Uso** | UC-09 — Atualizar Rubricas |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia acessando a tela de Rubricas pelo menu principal.||
||2. Exibe a lista de rubricas cadastradas, contendo código, descrição, natureza, tipo, incidências, indicação de rubrica padrão e status.|
|3. Seleciona a rubrica personalizada que deseja editar.||
||4. Verifica se a rubrica selecionada não pertence ao conjunto de rubricas padrão e se está ativa para edição.|
||5. Exibe o formulário de edição com os dados atuais da rubrica.|
|6. Altera os dados permitidos, como descrição, natureza, tipo, incidências ou status.||
|7. Confirma que deseja salvar as alterações da rubrica.||
||8. Valida se os campos obrigatórios foram preenchidos.|
||9. Persiste as alterações da rubrica no sistema.|
||10. Ao retornar para a tela de Rubricas, atualiza a listagem de rubricas cadastradas.|
|||

|

| **Caso de Uso** | UC-10 — Registrar Lançamentos Mensais |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Começa acessando a tela de Lançamentos pelo menu principal.||
||2. Exibe a competência da folha aberta, o status da folha e a listagem de lançamentos cadastrados.|
|3. Indica que deseja registrar um novo lançamento mensal.||
||4. Exibe o formulário de lançamento mensal, carregando funcionários, rubricas, modalidades e bases de cálculo disponíveis.|
|5. Seleciona o funcionário, escolhe a rubrica e define a modalidade do lançamento como valor, porcentagem ou quantidade.||
|6. Informa o valor ou quantidade do lançamento e, quando necessário, a base de cálculo e a data do ocorrido.||
|7. Confirma o registro do lançamento mensal.||
||8. Valida se os campos obrigatórios foram preenchidos e se existe uma folha aberta para receber o lançamento.|
||9. Persiste o lançamento mensal no sistema.|
||10. Ao retornar para a tela de Lançamentos, atualiza a listagem de lançamentos cadastrados.|
|||

|

| **Caso de Uso** | UC-11 — Atualizar Lançamentos Mensais |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Começa acessando a tela de Lançamentos pelo menu principal.||
||2. Exibe a competência da folha aberta, o status da folha e a listagem de lançamentos cadastrados.|
|3. Seleciona o lançamento mensal que deseja editar.||
||4. Exibe o formulário de edição com os dados atuais do lançamento selecionado.|
|5. Altera os dados permitidos, como funcionário, rubrica, modalidade, valor, quantidade, base de cálculo ou data do ocorrido.||
|6. Confirma que deseja salvar as alterações do lançamento.||
||7. Valida se os campos obrigatórios foram preenchidos e se existe uma folha aberta para permitir a alteração.|
||8. Persiste as alterações do lançamento mensal no sistema.|
||9. Ao retornar para a tela de Lançamentos, atualiza a listagem de lançamentos cadastrados.|
|||

|

| **Caso de Uso** | UC-12 — Processar Folha de Pagamento |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Começa acessando a tela de Processamento e Holerites pelo menu principal.||
||2. Carrega as competências de folha cadastradas e prepara os dados disponíveis para consulta e cálculo.|
|3. Seleciona a competência desejada, quando necessário.||
||4. Carrega os dados necessários, como empresa, funcionários, rubricas, parâmetros legais e lançamentos mensais registrados.|
||5. Para cada funcionário aplicável à folha, calcula os valores do demonstrativo com base no salário bruto, nos lançamentos mensais e nos dias úteis da competência.|
||6. Aplica os proventos e descontos cadastrados por meio das rubricas vinculadas aos lançamentos mensais.|
||7. Calcula os descontos legais de INSS e IRRF conforme as faixas cadastradas.|
||8. Calcula o FGTS informativo, os totais de proventos, os totais de descontos e o salário líquido.|
||9. Exibe os resultados calculados na tela para conferência do usuário.|
|10. Analisa os resultados retornados pelo sistema.||
|||

|

| **Caso de Uso** | UC-13 — Gerar Holerites |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Começa acessando a tela de Processamento e Holerites pelo menu principal.||
||2. Carrega a folha da competência consultada e calcula os demonstrativos disponíveis.|
|3. Seleciona a competência desejada.||
||4. Exibe a listagem de holerites calculados para a competência selecionada.|
|5. Seleciona o funcionário desejado para visualizar o holerite individual.||
||6. Exibe o demonstrativo de pagamento com dados da empresa, competência, funcionário, cargo, proventos, descontos, bases legais e salário líquido.|
|7. Analisa o holerite retornado pelo sistema.||
|8. Caso deseje exportar, seleciona a opção de exportar o holerite individual, todos os holerites da competência ou o arquivo geral da folha.||
||9. Solicita o local onde o arquivo em PDF será salvo.|
|10. Escolhe a pasta de destino para salvar o arquivo.||
||11. Gera o arquivo em PDF de acordo com a opção escolhida e exibe mensagem de sucesso.|
|||

|

| **Caso de Uso** | UC-14 — Consultar Histórico de Folhas |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia acessando a tela de Processamento e Holerites pelo menu principal.||
||2. Carrega as folhas cadastradas por competência e exibe as opções disponíveis para consulta.|
|3. Seleciona uma competência anterior ou uma competência desejada para análise.||
||4. Carrega a folha correspondente à competência selecionada.|
||5. Busca os dados vinculados à folha e calcula as informações disponíveis para a competência.|
||6. Exibe as informações associadas à competência selecionada, permitindo a consulta dos dados da folha.|
|7. Analisa os dados retornados pelo sistema.||
|||

|

| **Caso de Uso** | UC-15 — Consultar Log de Auditoria |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia acessando a tela de Log de Auditoria pelo menu principal.||
||2. Exibe a tela de auditoria com os registros de ações realizadas no sistema e os filtros disponíveis por usuário e data.|
|3. Caso necessário, seleciona os filtros desejados para consulta.||
||4. Atualiza a listagem de registros conforme os filtros informados.|
||5. Exibe informações relacionadas às ações registradas, como data, usuário, perfil, ação, entidade e detalhes.|
|6. Analisa os registros apresentados.||
|||

|

| **Caso de Uso** | UC-16 — Atualização de Parâmetros Legais |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|1. Inicia quando o usuário precisa atualizar um parâmetro legal utilizado no cálculo da folha.||
|2. Acessa a tela de Rubricas pelo menu principal.||
||3. Exibe a tela de Rubricas com a lista de rubricas cadastradas e a área de parâmetros legais governamentais.|
|4. Acessa a área de parâmetros legais governamentais.||
||5. Exibe as tabelas de faixas cadastradas de INSS e IRRF.|
|6. Seleciona diretamente na tabela o valor que deseja alterar.||
|7. Altera os valores permitidos, como piso, teto e alíquota de INSS ou IRRF, além da parcela de dedução do IRRF.||
||8. Persiste a alteração realizada na tabela correspondente.|
||9. Atualiza os dados exibidos para que os novos parâmetros fiquem disponíveis ao processamento da folha.|
|||
