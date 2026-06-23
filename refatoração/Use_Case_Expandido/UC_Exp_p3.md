| **Caso de Uso** | UC-01 — Autenticar no sistema |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Usuário ou senha inválidos**||
|1. O usuário informa usuário ou senha que não correspondem a um usuário ativo cadastrado no sistema.||
||2. Não libera o acesso ao sistema e exibe mensagem informando que o usuário ou a senha são inválidos.|
|3. O usuário pode tentar novamente.||
|**A2 — Campos de autenticação não reconhecidos como credenciais válidas**||
|1. O usuário tenta autenticar sem informar corretamente os dados necessários para consulta.||
||2. Mantém o usuário na tela de login e não realiza o acesso ao menu principal.|
|||

|

| **Caso de Uso** | UC-02 — Cadastro da Empresa |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Campo obrigatório não preenchido**||
|1. O administrador deixa de preencher um campo obrigatório do formulário de empresa.||
||2. Exibe mensagem de aviso informando qual dado precisa ser preenchido e não persiste o cadastro.|
|3. O administrador corrige as informações e tenta salvar novamente.||
|**A2 — Empresa já cadastrada no sistema**||
|1. O administrador acessa a tela de Empresas quando já existe uma empresa cadastrada.||
||2. Exibe os dados da empresa existente e direciona a manutenção desses dados para a atualização da empresa, evitando o cadastro duplicado.|
|**A3 — Cadastro cancelado pelo administrador**||
|1. O administrador inicia o cadastro, mas decide cancelar antes de salvar.||
||2. Fecha o formulário sem persistir os dados preenchidos. Ao retornar para a tela de Empresas, mantém as informações anteriores.|
|**A4 — Tentativa de cadastrar endereço sem empresa registrada**||
|1. O administrador tenta cadastrar um endereço antes de existir uma empresa cadastrada.||
||2. Informa que é necessário criar uma empresa antes de adicionar endereço vinculado.|
|||

|

| **Caso de Uso** | UC-03 — Atualizar dados da Empresa |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Nenhuma empresa cadastrada para edição**||
|1. O administrador acessa a tela de Empresas quando ainda não há empresa cadastrada no sistema.||
||2. Exibe os campos informativos vazios ou com indicação de ausência de empresa, mantendo a edição indisponível até que uma empresa seja cadastrada.|
|**A2 — Campo obrigatório não preenchido na atualização**||
|1. O administrador apaga ou deixa sem preenchimento um campo obrigatório do formulário de edição.||
||2. Exibe mensagem de aviso informando o campo necessário e não persiste a alteração.|
|3. O administrador corrige os dados e tenta salvar novamente.||
|**A3 — Cancelamento das alterações feitas**||
|1. O administrador altera informações da empresa, mas decide cancelar antes de salvar.||
||2. Fecha o formulário sem persistir os campos alterados e mantém os dados anteriores da empresa.|
|||

|

| **Caso de Uso** | UC-04 — Cadastro de Usuários do Sistema |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Campo obrigatório não preenchido**||
|1. O administrador tenta cadastrar um usuário sem informar nome, senha ou perfil.||
||2. Exibe mensagem de aviso solicitando o dado obrigatório que falta e não realiza o cadastro.|
|3. O administrador completa os dados e tenta salvar novamente.||
|**A2 — Cadastro cancelado pelo administrador**||
|1. O administrador inicia o cadastro de usuário, mas decide cancelar antes de salvar.||
||2. Fecha o formulário sem gravar o novo usuário e mantém a listagem anterior.|
|||

|

| **Caso de Uso** | UC-05 — Atualizar Usuários no Sistema |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Nenhum usuário selecionado para edição**||
|1. O administrador tenta editar sem selecionar um usuário na listagem.||
||2. Exibe mensagem solicitando que um usuário seja selecionado para edição.|
|**A2 — Campo obrigatório não preenchido na edição**||
|1. O administrador remove o nome de usuário, a senha ou outra informação necessária no formulário de edição.||
||2. Exibe mensagem de aviso e não persiste a alteração.|
|**A3 — Conta de usuário inativada**||
|1. O administrador altera o status do usuário para inativo e salva a edição.||
||2. Persiste a conta como inativa. Em autenticações futuras, esse usuário não consegue acessar o sistema.|
|**A4 — Cancelamento das alterações feitas**||
|1. O administrador altera os dados do usuário, mas decide cancelar antes de salvar.||
||2. Fecha o formulário sem persistir as alterações e mantém os dados anteriores na listagem.|
|||

|

| **Caso de Uso** | UC-06 — Cadastro de Funcionários |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Campo obrigatório não preenchido**||
|1. O usuário tenta salvar o funcionário sem preencher nome, CPF, cargo, salário bruto ou data de admissão.||
||2. Exibe mensagem no próprio formulário informando que todos os campos devem ser preenchidos e não persiste o cadastro.|
|**A2 — Salário informado com formato inválido**||
|1. O usuário informa um salário que não pode ser convertido para valor numérico.||
||2. Exibe mensagem de salário inválido no formulário e mantém os dados para correção.|
|**A3 — Erro ao persistir funcionário**||
|1. O usuário informa dados que não podem ser gravados, como CPF já existente ou outra inconsistência de persistência.||
||2. Exibe mensagem de erro no formulário e não conclui o cadastro.|
|||

|

| **Caso de Uso** | UC-07 — Atualizar dados de Funcionários |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Nenhum funcionário selecionado para edição**||
|1. O usuário tenta editar sem selecionar um funcionário na listagem.||
||2. Exibe mensagem solicitando que um funcionário seja selecionado para edição.|
|**A2 — Campo obrigatório não preenchido na atualização**||
|1. O usuário apaga um dado obrigatório do formulário de edição.||
||2. Exibe mensagem no próprio formulário informando que todos os campos devem ser preenchidos e não persiste a alteração.|
|**A3 — Salário informado com formato inválido**||
|1. O usuário informa um salário bruto em formato inválido.||
||2. Exibe mensagem de salário inválido no formulário e mantém os dados para correção.|
|**A4 — Funcionário marcado como inativo**||
|1. O usuário altera o status do funcionário para inativo e salva a edição.||
||2. Persiste a alteração de status e passa a exibir o funcionário como inativo na listagem.|
|||

|

| **Caso de Uso** | UC-08 — Cadastro de Rubricas |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Dados obrigatórios da rubrica não preenchidos**||
|1. O administrador tenta salvar a rubrica sem informar descrição, natureza, tipo ou código válido.||
||2. Exibe uma lista de erros informando quais dados precisam ser corrigidos e não persiste a rubrica.|
|**A2 — Código reservado para rubrica padrão**||
|1. O administrador informa um código reservado às rubricas padrão do sistema.||
||2. Informa que os códigos reservados não podem ser usados para nova rubrica personalizada e mantém o formulário aberto para correção.|
|**A3 — Código da rubrica inválido**||
|1. O administrador informa código vazio, não numérico ou menor/igual a zero.||
||2. Exibe mensagem informando que o código deve ser um número inteiro válido e não realiza o cadastro.|
|**A4 — Código de rubrica já existente ou erro de persistência**||
|1. O administrador informa um código que não pode ser gravado por já existir ou por inconsistência no banco de dados.||
||2. Rejeita a persistência da rubrica e mantém a operação pendente para correção.|
|**A5 — Cadastro cancelado pelo administrador**||
|1. O administrador inicia o cadastro, mas decide cancelar antes de salvar.||
||2. Fecha o formulário sem gravar a rubrica e mantém a listagem anterior.|
|||

|

| **Caso de Uso** | UC-09 — Atualizar Rubricas |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Nenhuma rubrica selecionada para edição**||
|1. O administrador tenta editar sem selecionar uma rubrica na tabela.||
||2. Exibe mensagem solicitando que uma rubrica seja selecionada.|
|**A2 — Tentativa de editar rubrica padrão**||
|1. O administrador seleciona uma rubrica padrão do sistema para edição.||
||2. Bloqueia a edição e informa que rubricas padrão não podem ser editadas.|
|**A3 — Tentativa de editar rubrica inativa**||
|1. O administrador seleciona uma rubrica inativa para edição.||
||2. Bloqueia a edição e informa que não é possível editar uma rubrica inativa.|
|**A4 — Dados obrigatórios não preenchidos na edição**||
|1. O administrador remove descrição, natureza ou tipo da rubrica personalizada.||
||2. Exibe a lista de dados inválidos e não persiste a atualização.|
|**A5 — Cancelamento das alterações feitas**||
|1. O administrador altera dados da rubrica, mas decide cancelar antes de salvar.||
||2. Fecha o formulário sem persistir as alterações e mantém a listagem anterior.|
|||

|

| **Caso de Uso** | UC-10 — Registrar Lançamentos Mensais |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Nenhuma folha aberta disponível**||
|1. O usuário tenta salvar um lançamento mensal quando o sistema não identifica uma folha aberta.||
||2. Exibe mensagem informando que não há folha aberta para receber o lançamento e não realiza a persistência.|
|**A2 — Campos obrigatórios do lançamento não preenchidos**||
|1. O usuário tenta salvar sem selecionar funcionário, rubrica, modalidade ou sem informar valor/quantidade.||
||2. Exibe mensagem informando os campos obrigatórios e mantém o formulário aberto para correção.|
|**A3 — Modalidade porcentagem sem base de cálculo**||
|1. O usuário escolhe a modalidade porcentagem e não seleciona a base de cálculo.||
||2. Exibe mensagem solicitando a base de cálculo para a porcentagem e não salva o lançamento.|
|**A4 — Valor ou quantidade em formato inválido**||
|1. O usuário informa valor ou quantidade que não pode ser convertido para número.||
||2. Exibe mensagem de valor inválido e mantém o formulário aberto para correção.|
|**A5 — Lançamento de falta com desconto de DSR**||
|1. O usuário seleciona uma rubrica relacionada a falta e marca o desconto de DSR.||
||2. Considera o acréscimo correspondente no lançamento antes de persistir os dados.|
|**A6 — Registro cancelado pelo usuário**||
|1. O usuário inicia o lançamento, mas decide cancelar antes de salvar.||
||2. Fecha o formulário sem persistir o novo lançamento.|
|||

|

| **Caso de Uso** | UC-11 — Atualizar Lançamentos Mensais |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Nenhum lançamento selecionado para edição**||
|1. O usuário tenta editar sem selecionar um lançamento mensal na tabela.||
||2. Exibe mensagem solicitando que um lançamento seja selecionado para edição.|
|**A2 — Nenhuma folha aberta disponível para alteração**||
|1. O usuário tenta salvar a edição de um lançamento quando o sistema não identifica folha aberta.||
||2. Exibe mensagem informando que não há folha aberta para permitir a alteração e não persiste os dados.|
|**A3 — Campos obrigatórios não preenchidos na edição**||
|1. O usuário remove funcionário, rubrica, modalidade ou valor/quantidade do lançamento.||
||2. Exibe mensagem informando os campos obrigatórios e não persiste a alteração.|
|**A4 — Valor ou quantidade em formato inválido**||
|1. O usuário informa valor ou quantidade inválida durante a edição.||
||2. Exibe mensagem de valor inválido e mantém o formulário aberto para correção.|
|**A5 — Cancelamento das alterações feitas**||
|1. O usuário altera os dados do lançamento, mas decide cancelar antes de salvar.||
||2. Fecha o formulário sem persistir as alterações realizadas.|
|||

|

| **Caso de Uso** | UC-12 — Processar Folha de Pagamento |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Nenhuma folha cadastrada ou aberta disponível**||
|1. O usuário acessa a tela de Processamento e Holerites sem existir folha disponível para consulta.||
||2. Informa a ausência de folha disponível, oculta a pré-visualização de holerite e não apresenta resultados calculados.|
|**A2 — Nenhum funcionário aplicável à folha aberta**||
|1. O sistema carrega a folha aberta, mas não encontra funcionários ativos aplicáveis ao cálculo.||
||2. Mantém a listagem de resultados vazia e oculta a pré-visualização até que existam dados calculáveis.|
|**A3 — Funcionário inativo em folha aberta**||
|1. A folha consultada está aberta e há funcionário inativo cadastrado.||
||2. Desconsidera o funcionário inativo no cálculo da folha aberta, mantendo apenas os funcionários aplicáveis.|
|**A4 — Descontos superiores aos proventos do funcionário**||
|1. Durante o cálculo, os descontos de um funcionário superam os proventos calculados.||
||2. Ajusta o salário líquido para zero e mantém o processamento dos demais funcionários, sem interromper todo o cálculo da folha.|
|||

|

| **Caso de Uso** | UC-13 — Gerar Holerites |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Nenhum holerite selecionado para exportação individual**||
|1. O usuário tenta exportar um holerite individual sem selecionar um funcionário na tabela.||
||2. Exibe mensagem solicitando que um funcionário seja selecionado primeiro.|
|**A2 — Nenhum holerite calculado para exportação da competência**||
|1. O usuário tenta exportar todos os holerites ou o arquivo geral quando não há dados calculados na listagem.||
||2. Exibe mensagem informando que não há holerites ou dados processados para exportar.|
|**A3 — Usuário cancela a escolha do diretório de destino**||
|1. O usuário abre a seleção de pasta para salvar o PDF, mas fecha a janela sem confirmar o destino.||
||2. Cancela a exportação, não gera arquivo e mantém os dados exibidos na tela.|
|**A4 — Empresa não configurada no sistema**||
|1. O usuário visualiza o holerite quando não há empresa cadastrada com dados válidos.||
||2. Exibe o demonstrativo com indicação de empresa não configurada, orientando a verificação dos dados no menu de Empresa.|
|**A5 — Competência sem holerites disponíveis**||
|1. O usuário seleciona uma competência que não possui demonstrativos calculáveis.||
||2. Mantém a listagem vazia e oculta a pré-visualização do holerite até que existam dados disponíveis.|
|||

|

| **Caso de Uso** | UC-14 — Consultar Histórico de Folhas |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Nenhuma competência cadastrada para consulta**||
|1. O usuário acessa a tela de Processamento e Holerites quando não existem folhas cadastradas.||
||2. Não apresenta competências para seleção, informa ausência de folha disponível e mantém a pré-visualização sem dados.|
|**A2 — Competência selecionada sem dados calculáveis**||
|1. O usuário seleciona uma competência que não possui funcionários ou lançamentos suficientes para gerar informações.||
||2. Carrega a competência selecionada, mas mantém a listagem sem resultados e oculta a pré-visualização.|
|**A3 — Consulta de competência anterior com funcionário inativo**||
|1. O usuário consulta uma competência anterior que contém funcionário atualmente inativo.||
||2. Permite a visualização das informações disponíveis da competência selecionada, sem tratar a inatividade atual como bloqueio automático para a consulta.|
|||

|

| **Caso de Uso** | UC-15 — Consultar Log de Auditoria |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Nenhum filtro selecionado**||
|1. O administrador acessa a tela de Log de Auditoria sem escolher usuário ou data.||
||2. Exibe os registros disponíveis de auditoria, ordenados de acordo com os dados retornados pelo sistema.|
|**A2 — Nenhum registro encontrado para os filtros**||
|1. O administrador seleciona usuário ou data que não correspondem a registros existentes.||
||2. Atualiza a listagem conforme os filtros informados e apresenta a tabela sem registros correspondentes.|
|**A3 — Filtros limpos pelo administrador**||
|1. O administrador limpa os filtros aplicados na consulta.||
||2. Remove os critérios de usuário e data e recarrega a listagem geral de registros.|
|||

|

| **Caso de Uso** | UC-16 — Atualização de Parâmetros Legais |
| ------------------------ | ---------- |

| **Ação do Ator** | **Resposta do Sistema** |
| ------------------------ | ------------------------------- |
|**A1 — Valor inválido informado na tabela de parâmetros**||
|1. O administrador tenta informar um valor que não pode ser interpretado como valor numérico válido.||
||2. Não conclui corretamente a edição da célula e mantém o parâmetro dependente de correção antes de ser utilizado no cálculo da folha.|
|**A2 — Teto de IRRF informado como ilimitado**||
|1. O administrador informa o teto de uma faixa de IRRF como ilimitado.||
||2. Interpreta o valor como faixa sem limite superior prático e persiste o parâmetro correspondente.|
|**A3 — Alteração persistida diretamente na tabela**||
|1. O administrador conclui a edição de uma célula de INSS ou IRRF.||
||2. Persiste a alteração na tabela correspondente e mantém os novos valores disponíveis para os próximos cálculos da folha.|
|**A4 — Falha ao atualizar parâmetro legal**||
|1. O administrador altera um parâmetro, mas ocorre falha de persistência no banco de dados.||
||2. Não garante a atualização do parâmetro e mantém a necessidade de nova correção antes do uso confiável no processamento.|
|||
