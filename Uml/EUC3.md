
| **Caso de Uso**         | Autenticar no Sistema |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|**A1 — Credenciais Inválidas**||
|1. O usuário informa usuário ou senha incorretos.||
||2. O sistema rejeita o acesso, exibe mensagem de erro e registra a tentativa falha no log de segurança com data e login informado.|
|3. O usuário pode tentar novamente.||
|**A2 — Conta bloqueada por tentativas excessivas**||
|1. O usuário erra a senha pela terceira vez consecutiva.||
||2. O sistema bloqueia a conta de forma temporaria por 5 minutos e exibe mensagem explicativa.|
||3. Registra o bloqueio no log de segurança com data e login informado.|
|**A3 — Sessão expirada por inatividade**||
|1. O usuário permanece inativo por 30 minutos com a sessão aberta.||
||2. O sistema encerra a sessão automaticamente e redireciona para a tela de login com mensagem explicativa informando o motivo do encerramento.|
|||

|

| **Caso de Uso**         | Cadastro da Empresa |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|**A1 - Credenciais Inválidas**||
|1. Usuário com perfil diferente do administrador tenta acessar os campos destinados a emrpesa.||
||2. Desativa os campos relacionados ao cadastro da emprasa para perfis diferentes do administrador bloqueando o acesso.|
|**A2 - Campo Obrigatório não preenchido**||
|1. Não preenche todos os campo considerados obrigatórios.||
||2. Exibe uma mensagem de obrigatoriedade do campo e foca para o campo não preenchido.|
|**A3 - CNPJ inválido**||
|1. Informa um CNPJ inválido.||
||2. Informa que o CNPJ é inválido e foca o campo para correção do CNPJ.|
|||

|

| **Caso de Uso**         | Atualizar Dados da Empresa |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|**A1 - Credenciais Inválidas**||
|1. Usuário com perfil diferente do administrador tenta acessar os campos destinados a emrpesa.||
||2. Desativa os campos relacionados ao cadastro da emprasa para perfis diferentes do administrador bloqueando o acesso.|
|**A2 - CNPJ alterado inválido**||
|1. Ao alterar o CNPJ insere um CNPJ considerado inválido.||
||2. Informa que o CNPJ é inválido e foca o campo para correção do CNPJ.|
|**A3- Cancela Alterações feitas**||
|1. Insere novas informações, no entanto decide descartar as alterações feitas.||
||2. Não realiza a permanência dos campos alterados e descarta as alterações.|
|||

|

| **Caso de Uso**         | Cadastro de Usuários do Sistema |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|**A1 - Credenciais Inválidas**||
|1. Usuário com perfil diferente do administrador tenta acessar os campos destinados a emrpesa.||
||2. Desativa os campos relacionados ao cadastro da emprasa para perfis diferentes do administrador bloqueando o acesso.|
|**A2 - A senha do novo usuário não atende aos requisitos**||
|1. A senha do novo usuário que está tentando cadastrar difere das especificações do sistema.||
||2. Exibe mensagem específica com os critérios de senha não atendidos. O cadastro não é efetuado.|
|**A3 - Nome de usuário já existente**||
|1. Informa um nome de usuário já cadastrado.||
||2. Rejeita o cadastro e exibe mensagem informando que o usuário já existe|
|||

|

| **Caso de Uso**         | Atualizar Usuários no Sistema |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|**A1 - Credenciais Inválidas**||
|1. Usuário com perfil diferente do administrador tenta acessar os campos destinados a emrpesa.||
||2. Desativa os campos relacionados ao cadastro da emprasa para perfis diferentes do administrador bloqueando o acesso.|
|**A2 — Redefinição de senha**||
|1. Informa uma nova senha para o usuário.||
||2. Valida os critérios da nova senha A senha anterior é descartada sem ser exibida.|
|**A3 — Inativar conta de usuário**||
|1. Seleciona um usuário para inativar.||
||2. Verifica se o usuário a ser inativado é o único Administrador ativo. Caso seja, bloqueia a operação e exibe uma mensagem.|
||3. Se não for o último Administrador, exibe mensagem de confirmação.|
|4. Confirma a inativação.||
||5. Inativa a conta. A conta inativa não permite login. Registra no log de auditoria.|
|**A4- Cancela Alterações feitas**||
|1. Insere novas informações, no entanto decide descartar as alterações feitas.||
||2. Não realiza a permanência dos campos alterados e descarta as alterações.|
|||

|

| **Caso de Uso**         | Cadastro de Funcionários |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|**A1 - Campo Obrigatório não preenchido**||
|1. Não preenche todos os campo considerados obrigatórios.||
||2. Exibe uma mensagem de obrigatoriedade do campo e foca para o campo não preenchido|
|**A2 — CPF já cadastrado**||
|1. Informa um CPF já existente no sistema.||
||2. Rejeita o cadastro e exibe mensagem informando que o CPF já está registrado.|
|**A3 — Salário abaixo do piso**||
|1. Informa um salário base inferior ao piso nacional vigente.||
||2. Exibe mensagem de erro com o valor mínimo aceitável e não permite salvar.|
|**A4 — CPF com formato inválido**||
|1. Informa um CPF que não passa na validação dos dígitos verificadores.||
||2. Aplica a máscara em tempo real e, ao perder o foco, exibe a mensagem de CPF inválido.|
|||

|

| **Caso de Uso**         | Atualizar Dados de Funcionários |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|**A1 — Inativar funcionário**||
|1. Seleciona "Inativar" no cadastro do funcionário.||
||2. Exibe mensagem solicitando a data de desligamento.|
|3. Informa a data de desligamento e confirma.||
||4. Altera o status do funcionário para "Inativo" registrando a data de desligamento.|
||5. Registra no Log de Auditoria o usuário, data e inativação do usuário.|
|**A2 — Salário alterado para valor abaixo do piso**||
|1. Informa um novo salário abaixo do piso nacional vigente.||
||2. Exibe mensagem de erro com o valor mínimo e não persiste a alteração.|
|**A3 — CPF já cadastrado**||
|1. Informa um CPF já existente no sistema.||
||2. Rejeita o cadastro e exibe mensagem informando que o CPF já está registrado.|
|**A4 — CPF com formato inválido**||
|1. Informa um CPF que não passa na validação dos dígitos verificadores.||
||2. Aplica a máscara em tempo real e, ao perder o foco, exibe a mensagem de CPF inválido.|
|**A5 - Campo Obrigatório não preenchido**||
|1. Não preenche todos os campo considerados obrigatórios.||
||2. Exibe uma mensagem de obrigatoriedade do campo e foca para o campo não preenchido.|
|**A6- Cancela Alterações feitas**||
|1. Insere novas informações, no entanto decide descartar as alterações feitas.||
||2. Não realiza a permanência dos campos alterados e descarta as alterações.|
|||

|

| **Caso de Uso**         | Cadastro de Rubricas |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | ---------------------------|
|**A1 - Credenciais Inválidas**||
|1. Usuário sem credenciais tenta acessar os campos destinados a emrpesa.||
||2. Desativa os campos relacionados ao cadastro da emprasa para perfis diferentes do usuário bloqueando o acesso.|
|**A2 — Tentativa de editar rubrica padrão protegida (códigos 001 a 005)**||
|1. Tentativa de alterar uma rubrica de código 001 a 005.||
||2. Bloqueia a edição desses campos e exibe mensagem informando que a rubrica é obrigatória e protegida.|
|**A3 — Tentativa de excluir rubrica referenciada em folha já processada**||
|1. Tenta excluir uma rubrica dos códigos 006 a 008 ou 101 a 106, por exemplo.||
||2. Verifica se a rubrica está referenciada em alguma folha processada. Caso positivo, bloqueia a exclusão e exibe mensagem explicativa.|
|**A4 — Código de rubrica duplicado**||
|1. Informa um código já existente.||
||2. Rejeita o cadastro e exibe mensagem de código duplicado.|
|||

|

| **Caso de Uso**         | Lançar Exceções Mensais |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|**A1 — Folha do mês já fechada**||
|1. Tenta acessar lançamentos de um mês com folha fechada.||
||2. Bloqueia o acesso ao módulo de lançamentos para esse período e exibe mensagem informando.|
|**A2 — Horas extras excedem o limite legal**||
|1. Lança horas extras que ultrapassam o limite [CLT Art. 59].||
||2. Exibe alerta informando que o total de horas extras ultrapassa o limite legal, mas não bloqueia o lançamento. O usuário pode prosseguir com ciência do aviso.|
|**A3 — Atraso dentro da tolerância**||
|1. Lança um atraso de até 10 minutos para um dado dia.||
||2. Identifica o atraso como dentro da tolerância CLT (Art. 58, §1º), não gera desconto e exibe indicador visual ao operador informando que o atraso está na faixa de tolerância.|
|**A4 — Editar ou excluir lançamento existente**||
|1. Seleciona um lançamento já registrado no mês corrente e clica em "Editar" ou "Excluir".||
||2. Permite a operação enquanto a folha estiver "Aberta". Exibe diálogo de confirmação antes da exclusão.|
||3. Registra a alteração no Log de Auditoria com usuário, data e edição ou exclusão do lançamento.|
|||

|

| **Caso de Uso**         | Processar Folha de Pagamento |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | ---------------------------|
|**A1 — Folha já está fechada para o mês selecionado**||
|1. Seleciona um mês cuja folha já foi fechada.||
||2. Detecta status "Fechada" e desabilita o processamento da folha. Exibe mensagem informando que a folha já foi fechada.|
|**A2 — Nenhum funcionário ativo**||
|1. Tenta processar a folha.||
||2. Constata ausência de funcionários ativos. Exibe mensagem de erro e desabilita o processamento.|
|**A3 — Salário bruto resulta em líquido negativo**||
|1. Durante o cálculo, a soma dos descontos supera a soma dos proventos de um funcionário.||
||2. Interrompe o processamento, identifica o funcionário com o problema e exibe alerta. O usuário deve corrigir os lançamentos antes de reprocessar.|
|**A4 — Usuário cancela após visualizar o resumo**||
|1. Após ver o resumo (passo 11), o usuário cancela o processamento.||
||2. Descarta os resultados. A folha permanece "Aberta". Nenhum dado é persistido.|
|||

|

| **Caso de Uso**         | Fechar Folha de Pagamento |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | ---------------------------|
|**A1 — Folha já está fechada para o mês selecionado**||
|1. Seleciona um mês cuja folha já foi fechada.||
||2. Detecta status "Fechada". Exibe mensagem informando que a folha já foi fechada.|
|**A2 — Existem funcionários com status "Pendente" ou "Erro"**||
|1. O usuário tenta fechar a folha com funcionários não processados corretamente.||
||2. O fechamento da folha permanece desabilitado. Exibe a lista dos funcionários com pendências.|
|**A3 — Usuário cancela no diálogo de confirmação**||
|1. Cancela o fechamento da folha.||
||2. Não realiza nenhuma operação. A folha permanece com status "Aberta" e pode ser fechada.|
|||

|

| **Caso de Uso**         | Emitir Holerites |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | ---------------------------|
|**A1 — Nenhum mês fechado disponível para o funcionário**||
|1. O usuário seleciona um funcionário recém admitido sem folhas fechadas.||
||2. O seletor de competência não exibe opções disponíveis. O sistema exibe mensagem informando que não há folhas encerradas para o funcionário selecionado.|
|**A2 — Funcionário inativo**||
|1. O usuário busca um funcionário inativo.||
||2. O sistema permite a consulta de holerites de meses anteriores ao desligamento, exibindo normalmente os dados históricos.|
|**A3 - Mês de busca com folha em Aberto**||
|1. Filtra a busca do funcionário de um mês com a folha em aberto.||
||2. Desabilita a busca do holerite e exibe uma mensagem informando que período de busca é inválido.|
|||

|

| **Caso de Uso**         | Exportar Holerites |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | ---------------------------|
|**A1 — Usuário cancela o diálogo de salvamento**||
|1. Fecha o diálogo de seleção de diretório sem confirmar.||
||2. A exportação é cancelada. Nenhum arquivo é gerado e o holerite permanece exibido na tela.|
|**A2 — Falha ao salvar o arquivo**||
||1. Tenta salvar o arquivo no destino informado.|
||2. Detecta a falha e exibe mensagem de erro clara, informando o motivo. O usuário pode tentar novamente com outro diretório de destino.|
|**A3 - Mês de busca com folha em Aberto**||
|1. Filtra a busca do funcionário de um mês com a folha em aberto.||
||2. Desabilita a busca do holerite e exibe uma mensagem informando que período de busca é inválido.|
|||

|

| **Caso de Uso**         | Emitir Relatórios Mensais |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | ---------------------------|
|**A1 — Nenhum mês fechado disponível**||
|1. O usuário acessa o módulo sem nenhuma folha fechada no sistema.||
||2. O seletor de competência não apresenta opções. O sistema exibe mensagem informando que não há folhas encerradas para geração de relatórios.|
|**A2 - Mês da folha atual em Aberto**||
|1. Mês atual está com a folha em aberto.||
||2. Desabilita a emissão do relatório e exibe uma mensagem informando.|
|**A3 - Emitir Resumo da Folha**||
|1. Seleciona que deseja emitir o resumo da folha.||
||2. Exibe o resumo da folha por funcionário com os dados:  Nome, Cargo, Salário Bruto, Total Descontos, Salário Líquido, FGTS, 13º, Férias + ⅓ e Status — com totais gerais ao final.|
|**A4 - Emitir Remessa Bancária**||
|1. Selecina que deseja emitir a remessa bancária.||
||2. Exibe tabela com CPF, Nome, Banco, Agência, Conta e Valor Líquido.|
|||

|

| **Caso de Uso**         | Consultar Histórico de Folhas |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | --------------------------- |
|**A1 — Nenhum resultado encontrado para os filtros**||
|1. Aplica filtros que não correspondem a nenhuma folha cadastrada.||
||2. Exibe mensagem informando que não foram encontrados registros para os critérios selecionados e mantém os campos de filtro disponíveis para nova consulta.|
|||

|

| **Caso de Uso**         | Consultar Log de Auditoria |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | ---------------------------|
|**A1 - Credenciais Inválidas**||
|1. Usuário com perfil diferente do administrador tenta acessar os campos destinados a emrpesa.||
||2. Não exibe o item de menu do log para o perfil RH. O acesso direto é bloqueado.|
|**A2 — Nenhum resultado para os filtros**||
|1. Aplica filtros sem resultados correspondentes.||
||2. Exibe mensagem informando que não foram encontrados registros para os critérios selecionados.|
|||

|

| **Caso de Uso**         | Atualização de Parâmetros Legais |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | ---------------------------|
|**A1 - Credenciais Inválidas**||
|1. Usuário sem credenciais tenta acessar os campos destinados a emrpesa.||
||2. Não exibe o item de menu do log para o perfil RH. O acesso direto é bloqueado.|
|**A2 — Usuário cancela no diálogo de confirmação**||
|1. Cancelamento das alterações.||
||2. Descarta as alterações. Os parâmetros permanecem com os valores anteriores e nenhum registro é gravado no log.|
|**A2 — Valores dos parâmetros inválidos**||
|1. Informa um novo valor de parâmetros fora das normativas estabelecidas, como um piso salarial inferior ao já cadastrado.||
||2. O sistema exibe alerta informando que o novo valor é inválido, solicitando confirmação explícita do usuário para prosseguir (a redução do piso é incomum e pode indicar erro de digitação, por exemplo).|
