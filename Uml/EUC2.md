| **Caso de Uso**         |   Atualização de Parâmetros Legais          |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Inicia quando o usuário precisa atualizar um parâmetro legal.||
|2. Acessa o sistema efetuando seu login e acessa o módulo de cadastro da empresa.||
||3. Verifica se o usuáro possui as credenciais para poder realizar alterações de parâmetros legais. Exibe os parâmentros atuais usados.|
|4. Altera os parâmetros conforme desejado.||
|5. Salva as alterações.||
||6. Exibe uma mensagem pedindo confirmação.|
|7. Confirma as mudanças feitas.||
||8. Persiste os novos parâmetros. Registra a ação no log de auditoria. Exibe mensagem de sucesso.|
||9. Os novos parâmetros passam a ser utilizados a partir do próximo processamento de folha.|
|||

|

| **Caso de Uso**         |   Consultar Log de Auditoria          |
| ------------------------| ---------- |

| **Ação do Ator**    | **Resposta do Sistema**    |
| --------------------| ---------------------------|
|1. Inicia acessando o módulo de Auditoria||
||2. Confirma se o usuário se trata de um administrador.|
||3. Exibe a tela de auditoria para consulta das ações do sistema de acordo com os filtros: período, usuário e ação realizada.|
|4. Seleciona os filtros da auditoria.||
||5. Exibe as ações correspondentes aos filtros da busca com: ID, data, usuário, ação realizada.|
|6. Analisa a busca feita.||
|||

|

| **Caso de Uso**         |  Consultar Histórico de Folhas  |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Inicia acessando o módulo de histórico||
||2. Exibe todas folhas de pagamento processadas em ordem cronológica decrescente, contendo: Mês, Status, Data de Fechamento, Quantidade de Funcionários e Total Líquido da Folha.|
|2. Seleciona o filtro do mês da folha desejada ou um funcionário específico para busca.||
||3. Verifica se o mês inserido para busca refere-se a uma folha fechada.|
||4. Exibe a folha de acordo com os filtros desejado.|
|5. Analisa a folha recebida do sistema.||
|||

|

| **Caso de Uso**         |   Emitir Relatórios Mensais |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Inicia acessando o módulo de processamento da folha||
||2. O sistema exibe a folha do mês atual com seletor para o tipo de relatório desejado, podendo ser: resumo da folha ou remessa bancária.|
|3. Seleciona qual relatório deseja emitir.||
||4. Verifca se a folha referente ao mês atual já está fechada.|
||5. Exibe os dados da folha fechada. Para Resumo da Folha: exibe por funcionário — Nome, Cargo, Salário Bruto, Total Descontos, Salário Líquido, FGTS, 13º, Férias + ⅓ e Status — com totais gerais ao final. Para Remessa Bancária: exibe tabela com CPF, Nome, Banco, Agência, Conta e Valor Líquido.|
|6. Analisa os dados exibidos pelo sistema.||
|||

|

| **Caso de Uso**         |  Exportar Holerites |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Inicia acessando o módulo de holerites e após ter concluído o UC-12.||
||2. Verifica se o mês atual já se encontra fechado.|
|3. Verfica se os dados do documento estão em conformidade.||
|4. Seleciona que deseja exportar o holerite atual. (Já gerado de acordo com o UC-12).||
||5. Gera o arquivo do Holerite com todos dados em formato PDF. Substitui a linha de assinatura por: "Documento Digital - Dispensa Assinatura".|
||6. Sugere o nome do arquivo a ser salvo seguindo o padrão: "HOLERITE[CPF-sem-formatação][AAAAMM].pdf".|
|7. Escolhe onde vai salvar o holerite.||
||8. Salva o arquivo e registra em Log de Auditoria com as informações de usuário, data e que efetuou a exportação do holerite.|
||9. Exibe mensagem de confirmação da exportação.|
|||

|

| **Caso de Uso**         | Emitir Holerites |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Começa acessando o módulo de Holerites.||
||2. Exibe uma listagem das folhas de pagamentos fechadas.|
|3. Filtra a emissão holerite escolhendo um mês e um funcionário desejado para emitir o holerite.||
||4. Verifica se o mês de busca refere-se a um mês com o status fechado.|
||5. Exibe o holerite com: Cabeçalho (Razão Social, CNPJ da empresa, Nome, CPF mascarado, Cargo, Data de Admissão, Mês/Ano); Tabela de Proventos (código, descrição, referência, valor); Tabela de Descontos (código, descrição, referência, valor); Rodapé (Total de Proventos, Total de Descontos, Salário Líquido em destaque, linha de assinatura).|
|6. Analisa o holerite retornado.||
|||

|

| **Caso de Uso**         |  Fechar Folha de Pagamento |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Começa acessando o módulo de processamento da folha de pagamento.||
||2. Verifica se o status da folha de pagamento do mês atual está com o status "aberta" e que todos os funcionários ativos possuem status "Processado" (nenhum "Pendente" ou "Erro").|
|3. Revisa o processamento da folha atual, como a concordância dos salários líquidos com as normas impostas pela CLT.||
|4. Confirma o fechamento da folha de pagamento.||
||5. Exibe uma mensagem pedindo uma segunda confirmação do fechamento da folha, visto que se trata de uma ação irreversível.|
|6. Realiza a nova confirmação do fechamento da folha de pagamento processada.||
||7. Efetua o fechamento da folha do mês atual, tornando-a imutável,  alterando o status da folha para "Fechada".|
||8. Registra o fechamento no log de auditoria com usuário, data/hora e competência.|
|||

|

| **Caso de Uso** | Processar Folha de Pagamento |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Começa acessando o módulo de processamento da folha||
||2. Verifica que a folha do mês está com status "Aberta" e que há pelo menos um funcionário ativo.|
||3.Exibe o painel de processamento habilitado com a lista de funcionários ativos.|
|4. Indica que deseja processar a folha de pagemento.||
||5. Para cada funcionário ativo, calcula o Valor-Dia e o Valor-Hora com base no salário contratual e nos dias úteis do mês.|
||6. Aplica os descontos de faltas, atrasos e DSR perdido registrados nas exceções do mês.|
||7. Soma as horas extras (50% ou 100%) ao salário bruto e adiciona os demais proventos de rubricas cadastradas (bônus, cesta básica, PLR)|
||8. Calcula o Salário Bruto consolidado, separando a base de cálculo de INSS/FGTS das rubricas indenizatórias (Cesta Básica, PLR).|
||9. Calcula e aplica o desconto de INSS progressivo sobre a base de cálculo.|
||10. Calcula o Salário Líquido.|
||11. Calcula as provisões do empregador: FGTS 8%, 13º proporcional 1/12  e Férias + ⅓ proporcional.|
||12. Exibe resumo consolidado: total de funcionários processados, total de proventos, total de descontos, total líquido e total de encargos.|
|13. Revisa o resumo da folha processada.||
||14. Registra o processamento no log de auditoria com timestamp, usuário responsável e competência processada.|
|||

|

| **Caso de Uso**         | Lançar Exceções Mensais |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Começa acessando o módulo de lançamento de exceções mensais.||
||2. Verifica que a folha está com o status "Aberto" para receber novas variáveis. Exibe o mês e ano de competência de forma destacada.|
|3. Busca o funcionário que deseja lançar a exceção.||
||4. Carrega os dados do funcionário e exibe os lançamentos já registrados para o mês corrente.|
|5. Insere os dados referentes para o lançamento, como: horas extras, faltas, atrasos, bônus ou descontos e dados do lançamento, como: quantidade, data e tipo.||
||6. Calcula e exibe em o valor monetário do lançamento (desconto ou provento).|
||7. Valida e realiza a permanência da exceção referente ao funcionário.|
||8. Registra em Log de Auditoria com os dados do usuário, data e o lançamento feito.|
|||

|

| **Caso de Uso**         | Cadastro de Rubricas |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Inicia acessando o módulo de cadastro e posteriormente o cadastro de rubricas.||
||2. Verifica se o usuário tem as credenciais para manipular as rubricas.|
||3.  Exibe a lista de rubricas cadastradas, com código, descrição, natureza, tipo e flags de incidência.|
|4. Insere ou atualiza os dados referentes a rubrica desejada, como rubrica de proventos ou descontos.||
||5. Valida unicidade do código da rubrica e verifica que o código está na faixa permitida para criação (≥ 500). |
||6.  Persiste a rubrica exibindo uma mensagem de sucesso.|
||7.  Registra no log de auditoria, contendo o usuário, data e a rubrica cadastrada ou atualizada.|
|||

|

| **Caso de Uso**         | Atualizar dados de Funcionários |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Inicia acessando o módulo de cadastro e posteriormente o cadastro de funcionários.||
||2. Exibe uma listagem de todos funcionários cadastrados e seus respectivos dados cadastrados.|
|3. Seleciona o funcionário desejado para editar.||
||4. Exibe o formulário de edição com os dados atuais do funcionário.|
|5. O usuário pode: alterar os campos desejados, como: cargo, salário base ou dados bancários ou inativar o funcionário. ||
||6. Valida a alteração realizada, se está em conformidade com o regime CLT. |
||7. Persiste as alterações exibindo uma mensagem de sucesso da ação.|
||8. Registra em Log de Auditoria usuário, data e o novo salário (caso tenha alterado o salário base do colaborador).|
|||

|

| **Caso de Uso**         | Cadastro de Funcionários |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Inicia acessando o módulo de cadastro e posteriormente o cadastro de funcionários.||
||2. Exibe uma listagem de todos os funcionários ativos e inativos da empresa, contendo: Nome, cargo, salário e as demais informações.|
|3. Indica que deseja realizar o cadastro de um novo funcionário.||
||4. Exibe o formulário de cadastro com campos obrigatórios (marcados com *) e opcionais.|
|5.O usuário preenche os campos obrigatórios: Nome Completo, CPF, Data de Nascimento, Data de Admissão, Cargo, Salário Base e pode preencher os campos opcinais: Banco e número da conta corrente.||
||6. Valida o CPF pelo algoritmo de dígitos verificadores. Rejeita CPFs com todos os dígitos iguais. Verifica unicidade do CPF (ativos e inativos).|
||7. Valida que o Salário Base é maior ou igual ao piso salarial nacional vigente.|
||8. Persiste o funcionário com status "Ativo" exibindo uma mensagem de sucesso. |
||9. Registra em Log de Auditoria o usuário, data e o funcionário cadastrado.|
|||

|

| **Caso de Uso**         | Atualizar Usuários no Sistema |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Começa acessando o módulo de cadastro e cadastro de usuário.||
||2. Verifica se o usuário que está acessando se trata de um administrador.|
||3. Exibe uma listagem de todos usuários cadastrados com o nome de usuário de cada um.|
|4. Seleciona o funcionário que deseja realizar alterações, como: alterar nome de usuário, redefinir senhas ou inativar contas existente.||
||5. Exibe o formulário para edição do funcionário.|
|6. Realiza a alteração desejada||
||7.  Valida os dados alterados (regras de senha, se aplicável) e persiste as mudanças exibindo uma mensagem de sucesso.|
||8. Registra no Log de Auditoria o usuário, data e campos alterados.|
|||


|

| **Caso de Uso**         | Cadastro de Usuários do Sistema |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Inicia acessando o módulo de cadastro e depois cadastro de usuários.||
||2. Verifica se o usuário é um administrador.|
||3. Exibe uma listagem de todos usuários cadastrados no sistema com o nome de usário e nível de acesso.|
|4. Informa que deseja realizar o cadastro de um novo usuário.||
||5. Exibe formulário com campos: Nome de Usuário, Senha, Confirmação de Senha e Perfil (Administrador / Analista de RH).|
|6. Preenche todos os campos e seleciona o perfil desejado.||
||7. Valida a senha: mínimo 8 caracteres, ao menos uma letra maiúscula, uma minúscula e um dígito numérico. Verifica se as senhas coincidem.|
||8. Persiste o novo usuário exibindo uma mensagem de sucesso.|
||9. Registra em Log de Auditoria o usuário, data e o cadastro do usuário realizado.|
|||

|

| **Caso de Uso**         | Atualizar dados da Empresa |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | ---------------------------|
|1. Inicia acessando o módulo de Cadastro e cadastro da Empresa.||
||2. Verifica se o usuário é um administrador.|
||3. Exibe os dados atuais da empresa cadastrados.|
|4. Altera os campos desejados (dados cadastrais).||
||5. Exibe mensagem de confirmação com as opções.|
|6. Confirma que deseja realizar as alterações.||
||7. Valida os dados (CNPJ, campos obrigatórios) persistindo as alterações e exibindo uma mensagem de sucesso.|
||8. Registra no Log de Auditoria o usuário, data e alterações da empresa alteradas.|
|||

|

| **Caso de Uso**         | Cadastro da Empresa |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | ---------------------------|
|1. Inicia acessando o módulo de Cadastro e Cadastro da Emrpesa.||
||2. Verifica se o usuário é um administrador.|
||3. Exibe o formulário de cadastro da empresa com campos obrigatórios marcados com asterisco (*).|
|4. O administrador preenche: Razão Social, CNPJ , endereço completo e nome do Responsável Legal.||
||5. Valida o CNPJ pelo algoritmo de dígitos verificadores da Receita Federal. Rejeita CNPJs com todos os dígitos iguais.|
||6. Persiste os dados e exibe mensagem de sucesso.|
||7. Registra em Log de Auditoria o usuário, data e o Cadastro de Empresa realizado.|
|||

|

| **Caso de Uso**         | Autenticar no Sistema |
| ------------------------ | ---------- |

| **Ação do Ator**    | **Resposta do Sistema**         |
| ------------------------ | -------------------------------   |
|1. Inicia o Sistema.||
||2. Exibe a tela de login com os campos "Usuário" e "Senha".|
|3. Preenche o campo "Usuário" e o campo "Senha".||
||4. Valida que ambos os campos foram preenchidos.|
||5. Compara as credenciais armazenadas no banco de dados.|
||6. Registra no log de segurança: usuário, data e resultado do acesso.|
||7. Redireciona o usuário ao menu principal, exibindo: nome do sistema, usuário e perfil ativo. Habilita os módulos permitidos para o perfil do usuário.|
