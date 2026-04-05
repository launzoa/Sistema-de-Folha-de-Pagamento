# Casos de Uso Expandidos — SFP-CLT

---

## UC-01 — Autenticar no Sistema

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Autenticar no Sistema |
| **Atores**               | Administrador, RH (iniciador) |
| **Finalidade**           | Verificar que o usuário que está acessando o sistema possui permissão para manuseá-lo, validando suas credenciais e liberando o acesso conforme o perfil cadastrado. |
| **Visão geral**          | O usuário inicia o sistema e é direcionado à tela de login. Ele insere suas credenciais (usuário e senha). O sistema valida as informações contra os dados armazenados e, em caso de sucesso, redireciona o usuário ao menu principal com as funcionalidades disponíveis para seu perfil. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.2, 3.3.2.1, 3.3.2.2 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O usuário inicia a aplicação. | |
| | 2. Exibe a tela de login com os campos "Usuário" e "Senha", o nome do sistema ("SFP-CLT") e a versão atual do software. |
| 3. O usuário preenche o campo "Usuário" (texto, máx. 50 caracteres). | |
| 4. O usuário preenche o campo "Senha" (texto oculto com asteriscos). | |
| 5. O usuário clica em "Entrar". | |
| | 6. Valida que ambos os campos foram preenchidos. |
| | 7. Compara as credenciais com o hash bcrypt armazenado no banco de dados. |
| | 8. Registra no log de segurança: nome de usuário, data, hora e resultado do acesso (sucesso). |
| | 9. Redireciona o usuário ao menu principal, exibindo no cabeçalho: nome do sistema, nome do usuário e perfil de acesso ativo. Habilita somente os módulos permitidos para o perfil do usuário. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Credenciais inválidas** | |
| 1. O usuário informa usuário ou senha incorretos e clica em "Entrar". | |
| | 2. O sistema rejeita o acesso, exibe mensagem de erro em linguagem clara (sem expor detalhes técnicos) e registra a tentativa falha no log de segurança com timestamp e login informado. |
| 3. O usuário pode tentar novamente. | |
| **A2 — Conta bloqueada por tentativas excessivas** | |
| 1. O usuário erra a senha pela terceira vez consecutiva. | |
| | 2. O sistema bloqueia a conta temporariamente por 5 minutos, exibe mensagem explicativa e registra o bloqueio no log de segurança. |
| **A3 — Sessão expirada por inatividade** | |
| 1. O usuário permanece inativo por 30 minutos com a sessão aberta. | |
| | 2. O sistema encerra a sessão automaticamente e redireciona para a tela de login com mensagem explicativa informando o motivo do encerramento. |

---
---

## UC-02 — Cadastro da Empresa

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Cadastro da Empresa |
| **Atores**               | Administrador (iniciador) |
| **Finalidade**           | Registrar os dados corporativos da empresa (CNPJ, Razão Social, endereço e responsável legal) e os parâmetros fiscais utilizados nos cálculos da folha. |
| **Visão geral**          | O administrador acessa o módulo de cadastro de Empresa. O sistema verifica que o usuário possui perfil Administrador. O administrador preenche os dados obrigatórios e os parâmetros fiscais, revisa e confirma. O sistema valida e persiste os dados. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.5, 3.2.1.1 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O administrador acessa o menu "Cadastros" e seleciona "Empresa". | |
| | 2. Verifica que o usuário possui perfil Administrador. Exibe o formulário de cadastro da empresa com campos obrigatórios marcados com asterisco (*). |
| 3. O administrador preenche: Razão Social (máx. 150 caracteres), CNPJ (máscara 00.000.000/0000-00), endereço completo (logradouro, número, complemento, bairro, cidade, UF, CEP) e nome do Responsável Legal. | |
| 4. O administrador preenche os parâmetros fiscais: alíquota de FGTS (padrão 8%), horas mensais contratuais (padrão 220), percentual HE 50% e 100%, e valor da Cesta Básica. | |
| 5. O administrador clica em "Salvar". | |
| | 6. Valida o CNPJ pelo algoritmo de dígitos verificadores da Receita Federal. Rejeita CNPJs com todos os dígitos iguais. |
| | 7. Persiste os dados no banco. Registra a operação no log de auditoria com usuário e data/hora. Exibe mensagem de sucesso. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — CNPJ inválido** | |
| 1. O administrador informa um CNPJ que não passa na validação. | |
| | 2. O sistema exibe mensagem de erro específica no campo CNPJ e não persiste os dados. |
| **A2 — Campo obrigatório não preenchido** | |
| 1. O administrador tenta salvar com campos obrigatórios em branco. | |
| | 2. O sistema destaca visualmente os campos faltantes e exibe mensagem orientando o preenchimento. O cadastro não é efetuado. |
| **A3 — Usuário sem permissão** | |
| 1. Um usuário com perfil Analista de RH tenta acessar o módulo. | |
| | 2. O sistema não exibe o item de menu de gestão de empresa para o perfil RH. O acesso direto pela navegação é bloqueado. |

---
---

## UC-03 — Atualizar Dados da Empresa

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Atualizar Dados da Empresa |
| **Atores**               | Administrador (iniciador) |
| **Finalidade**           | Atualizar dados corporativos e parâmetros fiscais da empresa já cadastrada quando necessário, mantendo rastreabilidade das alterações. |
| **Visão geral**          | O administrador acessa o cadastro da empresa, localiza o campo a alterar, realiza a modificação, revisa e confirma. O sistema exige confirmação via diálogo modal para alterações de parâmetros fiscais e registra a operação no log de auditoria. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.5, 3.2.1.1, 3.2.6.5 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O administrador acessa o menu "Cadastros" e seleciona "Empresa". | |
| | 2. Verifica perfil Administrador. Exibe os dados atuais da empresa em formulário editável. |
| 3. O administrador altera os campos desejados (dados cadastrais ou parâmetros fiscais). | |
| 4. O administrador clica em "Salvar". | |
| | 5. Detecta se houve alteração em parâmetros fiscais. Exibe diálogo modal de confirmação com as opções "Confirmar" e "Cancelar". |
| 6. O administrador clica em "Confirmar". | |
| | 7. Valida os dados (CNPJ, campos obrigatórios). Persiste as alterações. Registra no log de auditoria: usuário, data/hora, campos alterados, valores anteriores e novos. Exibe mensagem de sucesso. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Administrador cancela no diálogo de confirmação** | |
| 1. O administrador clica em "Cancelar" no diálogo modal. | |
| | 2. O sistema descarta as alterações. Os dados permanecem como estavam. Nenhum registro é gravado no log. |
| **A2 — CNPJ alterado para valor inválido** | |
| 1. O administrador informa um CNPJ inválido no campo. | |
| | 2. O sistema exibe mensagem de erro específica e não persiste as alterações. |

---
---

## UC-04 — Cadastro de Usuários do Sistema

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Cadastro de Usuários do Sistema |
| **Atores**               | Administrador (iniciador) |
| **Finalidade**           | Registrar novos usuários (perfil RH ou Administrador) no sistema, definindo suas credenciais de acesso e nível de permissão. |
| **Visão geral**          | O administrador acessa o módulo de gestão de usuários, preenche os dados do novo usuário (nome de usuário, senha e perfil), revisa e confirma. O sistema valida as regras de senha, cria o usuário e registra a operação. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.2.1.4, 3.3.2.1, 3.3.2.2 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O administrador acessa o menu "Cadastros" e seleciona "Usuários". | |
| | 2. Verifica perfil Administrador. Exibe a lista de usuários cadastrados e o botão "Novo Usuário". |
| 3. O administrador clica em "Novo Usuário". | |
| | 4. Exibe formulário com campos: Nome de Usuário, Senha, Confirmação de Senha e Perfil (Administrador / Analista de RH). |
| 5. O administrador preenche todos os campos e seleciona o perfil desejado. | |
| 6. O administrador clica em "Salvar". | |
| | 7. Valida a senha: mínimo 8 caracteres, ao menos uma letra maiúscula, uma minúscula e um dígito numérico. Verifica se as senhas coincidem. |
| | 8. Armazena a senha como hash bcrypt (fator de custo mínimo 10). Persiste o novo usuário. Registra a criação no log de auditoria. Exibe mensagem de sucesso. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Senha não atende aos critérios** | |
| 1. O administrador informa uma senha fraca (menos de 8 caracteres ou sem letras/dígitos obrigatórios). | |
| | 2. O sistema exibe mensagem específica com os critérios de senha não atendidos. O cadastro não é efetuado. |
| **A2 — Nome de usuário já existe** | |
| 1. O administrador informa um nome de usuário já cadastrado. | |
| | 2. O sistema rejeita o cadastro e exibe mensagem informando que o usuário já existe. |
| **A3 — Tentativa de remover o último Administrador ativo** | |
| 1. O administrador tenta criar um usuário que resultaria em zero administradores (situação indireta, verificada em UC-05). | |
| | 2. Não se aplica ao cadastro: a regra de ao menos um Administrador ativo é verificada na inativação (UC-05). |

---
---

## UC-05 — Atualizar Usuários no Sistema

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Atualizar Usuários no Sistema |
| **Atores**               | Administrador (iniciador) |
| **Finalidade**           | Editar dados de usuários existentes, redefinir senhas e inativar contas, garantindo que ao menos um Administrador permaneça ativo no sistema. |
| **Visão geral**          | O administrador localiza o usuário desejado na lista, realiza a alteração pretendida (dados, senha ou inativação), confirma e o sistema persiste as mudanças com registro de auditoria. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.2.1.4, 3.3.2.1, 3.3.2.2, 3.2.6.5 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O administrador acessa "Cadastros > Usuários". | |
| | 2. Exibe a lista de usuários (ativos e inativos) com nome, perfil e status. |
| 3. O administrador seleciona o usuário desejado. | |
| | 4. Exibe o formulário de edição com os dados atuais do usuário. |
| 5. O administrador realiza a alteração desejada: nome de usuário, perfil ou nova senha. | |
| 6. O administrador clica em "Salvar". | |
| | 7. Valida os dados alterados (regras de senha, se aplicável). Persiste as mudanças. Registra no log de auditoria com usuário executor, data/hora e campos alterados. Exibe mensagem de sucesso. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Inativar conta de usuário** | |
| 1. O administrador seleciona um usuário e clica em "Inativar". | |
| | 2. Verifica se o usuário a ser inativado é o único Administrador ativo. Caso seja, bloqueia a operação e exibe mensagem: "Deve existir ao menos um Administrador ativo no sistema." |
| | 3. Se não for o último Administrador, exibe diálogo modal de confirmação. |
| 4. O administrador confirma a inativação. | |
| | 5. Inativa a conta. A conta inativa não permite login. Registra no log de auditoria. |
| **A2 — Redefinição de senha** | |
| 1. O administrador informa uma nova senha para o usuário. | |
| | 2. Valida os critérios da nova senha. Armazena como hash bcrypt. A senha anterior é descartada sem ser exibida. |

---
---

## UC-06 — Cadastro de Funcionários

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Cadastro de Funcionários |
| **Atores**               | RH / Administrador (iniciador) |
| **Finalidade**           | Registrar novos funcionários CLT no sistema com seus dados pessoais, contratuais e bancários, tornando-os elegíveis para participar do processamento da folha. |
| **Visão geral**          | O usuário acessa o módulo de funcionários e preenche os dados do novo colaborador. O sistema valida CPF, piso salarial e unicidade, e persiste o cadastro com histórico de admissão. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.4, 3.2.1.2, RN-01 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O usuário acessa "Cadastros > Funcionários" e clica em "Novo Funcionário". | |
| | 2. Exibe o formulário de cadastro com campos obrigatórios (marcados com *) e opcionais. |
| 3. O usuário preenche os campos obrigatórios: Nome Completo, CPF, Data de Nascimento, Data de Admissão, Cargo, Salário Base, Banco e Conta-corrente. | |
| 4. O usuário preenche os campos opcionais disponíveis (ex.: número de dependentes, e-mail institucional). | |
| 5. O usuário clica em "Salvar". | |
| | 6. Valida o CPF pelo algoritmo de dígitos verificadores. Rejeita CPFs com todos os dígitos iguais. Verifica unicidade do CPF (ativos e inativos). |
| | 7. Valida que o Salário Base é ≥ ao piso salarial nacional vigente (R$ 1.518,00 em 2025) [RN-01]. |
| | 8. Persiste o funcionário com status "Ativo". Registra a data de admissão. Calcula e armazena o tempo de empresa. Grava no log de auditoria. Exibe mensagem de sucesso. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — CPF já cadastrado** | |
| 1. O usuário informa um CPF já existente no sistema (ativo ou inativo). | |
| | 2. O sistema rejeita o cadastro e exibe mensagem específica informando que o CPF já está registrado. |
| **A2 — Salário abaixo do piso** | |
| 1. O usuário informa um salário base inferior ao piso nacional vigente. | |
| | 2. O sistema exibe mensagem de erro com o valor mínimo aceitável e não permite salvar. |
| **A3 — CPF com formato inválido** | |
| 1. O usuário informa um CPF que não passa na validação dos dígitos verificadores. | |
| | 2. O sistema aplica a máscara em tempo real e, ao perder o foco, exibe a mensagem de CPF inválido. |

---
---

## UC-07 — Atualizar Dados de Funcionários

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Atualizar Dados de Funcionários |
| **Atores**               | RH / Administrador (iniciador) |
| **Finalidade**           | Editar dados pessoais ou contratuais de funcionários existentes, visualizar o histórico salarial ou inativar funcionários desligados da empresa, sem permitir exclusão permanente. |
| **Visão geral**          | O usuário busca o funcionário na listagem, acessa seus dados e realiza a alteração desejada. Alterações salariais geram automaticamente uma entrada no histórico de salários. A inativação registra a data de desligamento e impede o funcionário de ser incluído em folhas futuras. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.4, 3.2.1.2, 3.2.6.5, RN-01 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O usuário acessa "Cadastros > Funcionários". | |
| | 2. Exibe a listagem de funcionários com: Nome, CPF (mascarado: ***.***. XXX-XX), Cargo, Salário Base e Status. Disponibiliza campo de busca por CPF. |
| 3. O usuário localiza o funcionário desejado e clica em "Editar". | |
| | 4. Exibe o formulário de edição com os dados atuais do funcionário. |
| 5. O usuário altera os campos desejados (ex.: cargo, salário base, dados bancários). | |
| 6. O usuário clica em "Salvar". | |
| | 7. Valida os dados alterados (piso salarial [RN-01], se o salário foi modificado). |
| | 8. Persiste as alterações. Se o salário base foi alterado, registra no histórico salarial: salário anterior, novo salário, data e usuário responsável. Grava no log de auditoria. Exibe mensagem de sucesso. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Inativar funcionário (desligamento)** | |
| 1. O usuário clica em "Inativar" no cadastro do funcionário. | |
| | 2. Exibe diálogo modal solicitando a data de desligamento (campo obrigatório). |
| 3. O usuário informa a data de desligamento e confirma. | |
| | 4. Altera o status do funcionário para "Inativo". Registra a data de desligamento. O funcionário não participará de processamentos de folhas posteriores à data de desligamento. Registra no log de auditoria. |
| **A2 — Visualizar histórico salarial** | |
| 1. O usuário clica em "Ver Histórico" no cadastro do funcionário. | |
| | 2. Exibe a lista de alterações salariais em ordem cronológica: salário anterior, novo salário, data e usuário que alterou. |
| **A3 — Salário alterado para valor abaixo do piso** | |
| 1. O usuário informa um novo salário abaixo do piso nacional vigente. | |
| | 2. O sistema exibe mensagem de erro com o valor mínimo e não persiste a alteração. |

---
---

## UC-08 — Cadastro de Rubricas

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Cadastro de Rubricas |
| **Atores**               | Administrador (iniciador) |
| **Finalidade**           | Cadastrar e personalizar rubricas de proventos e descontos com seus códigos, naturezas e flags de incidência (INSS, FGTS, IRRF), que serão utilizadas no processamento da folha. |
| **Visão geral**          | O administrador acessa o módulo de rubricas, preenche os dados da nova rubrica ou edita uma existente (dentro das permissões) e confirma. O sistema valida unicidade do código e as restrições de proteção às rubricas padrão. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.2.1.3, Apêndice 4.1 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O administrador acessa "Cadastros > Rubricas". | |
| | 2. Exibe a lista de rubricas cadastradas (padrão e personalizadas) com código, descrição, natureza, tipo e flags de incidência. |
| 3. O administrador clica em "Nova Rubrica". | |
| | 4. Exibe formulário com campos: Código (numérico, 3 dígitos, ≥ 500 para rubricas personalizadas), Natureza (Provento / Desconto), Tipo (Fixo / Variável) e flags de incidência (incide INSS, incide FGTS, incide IRRF). |
| 5. O administrador preenche os campos e clica em "Salvar". | |
| | 6. Valida unicidade do código. Verifica que o código está na faixa permitida para criação (≥ 500). Persiste a rubrica. Registra no log de auditoria. Exibe mensagem de sucesso. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Tentativa de editar rubrica padrão protegida (códigos 001 a 005)** | |
| 1. O administrador tenta alterar o código, natureza ou flags de incidência de uma rubrica de código 001 a 005. | |
| | 2. O sistema bloqueia a edição desses campos e exibe mensagem informando que a rubrica é obrigatória e protegida. |
| **A2 — Tentativa de excluir rubrica referenciada em folha já processada** | |
| 1. O administrador tenta excluir uma rubrica dos códigos 006 a 008 ou 101 a 106. | |
| | 2. O sistema verifica se a rubrica está referenciada em alguma folha processada. Caso positivo, bloqueia a exclusão e exibe mensagem explicativa. |
| **A3 — Código de rubrica duplicado** | |
| 1. O administrador informa um código já existente. | |
| | 2. O sistema rejeita o cadastro e exibe mensagem de código duplicado. |

---
---

## UC-09 — Lançar Exceções Mensais

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Lançar Exceções Mensais |
| **Atores**               | RH / Administrador (iniciador) |
| **Finalidade**           | Registrar variáveis do mês corrente para funcionários específicos — faltas, atrasos, atestados, horas extras, bônus e descontos — que impactarão o processamento da folha de pagamento. |
| **Visão geral**          | O usuário acessa o módulo de lançamentos com a folha do mês em status "Aberta", busca o funcionário desejado, seleciona o tipo de evento, informa os dados e confirma. O sistema calcula em tempo real os impactos monetários e persiste o lançamento. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.1.6, 3.2.2, RN-02, RN-03, RN-04, RN-05, RN-06 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O usuário acessa o módulo "Lançamento de Exceções". | |
| | 2. Verifica que a folha do mês corrente está com status "Aberta". Exibe o mês e ano de competência de forma destacada. |
| 3. O usuário busca e seleciona o funcionário desejado pelo campo de busca. | |
| | 4. Carrega os dados do funcionário e exibe os lançamentos já registrados para o mês corrente. |
| 5. O usuário seleciona o tipo de evento: Horas Extras, Falta Injustificada, Atraso, Atestado Médico, Bônus ou Desconto Variável. | |
| 6. O usuário preenche os dados do evento (quantidade, data, tipo, descrição, conforme o evento). | |
| | 7. Calcula e exibe em tempo real o valor monetário do impacto do lançamento (desconto ou provento). |
| 8. O usuário confirma o lançamento clicando em "Registrar". | |
| | 9. Valida os dados (limites legais, folha aberta). Persiste o lançamento vinculado ao funcionário e ao mês de competência. Exibe mensagem de sucesso. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Folha do mês já fechada** | |
| 1. O usuário tenta acessar lançamentos de um mês com folha fechada. | |
| | 2. O sistema bloqueia o acesso ao módulo de lançamentos para esse período e exibe mensagem: "A folha do mês selecionado está encerrada. Lançamentos não são permitidos." [RN-15] |
| **A2 — Horas extras excedem o limite legal** | |
| 1. O usuário lança horas extras que ultrapassam o limite de 2h/dia × dias úteis do mês [CLT Art. 59]. | |
| | 2. O sistema exibe alerta informando que o total de horas extras ultrapassa o limite legal, mas não bloqueia o lançamento. O usuário pode prosseguir com ciência do aviso. |
| **A3 — Atraso dentro da tolerância** | |
| 1. O usuário lança um atraso de até 10 minutos para um dado dia. | |
| | 2. O sistema identifica o atraso como dentro da tolerância CLT (Art. 58, §1º), não gera desconto e exibe indicador visual ao operador informando que o atraso está na faixa de tolerância. |
| **A4 — Editar ou excluir lançamento existente** | |
| 1. O usuário seleciona um lançamento já registrado no mês corrente e clica em "Editar" ou "Excluir". | |
| | 2. O sistema permite a operação enquanto a folha estiver "Aberta". Exibe diálogo de confirmação antes da exclusão. Registra a alteração no log de auditoria. |

---
---

## UC-10 — Processar Folha de Pagamento

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Processar Folha de Pagamento |
| **Atores**               | RH / Administrador (iniciador) |
| **Finalidade**           | Calcular o salário líquido de cada funcionário ativo no mês de competência, aplicando proventos, descontos de INSS e gerando provisões de FGTS, 13º salário e férias. |
| **Visão geral**          | O usuário acessa o módulo de processamento com a folha do mês aberta. O sistema verifica os pré-requisitos (cadastros e exceções lançadas), executa o cálculo automático para todos os funcionários ativos e exibe um resumo consolidado. O usuário revisa os resultados e, se corretos, confirma o processamento. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | UC-06, UC-08, UC-09, UC-11 — Requisitos: 3.1.7, 3.2.3, 3.2.4, 3.2.5, RN-02 a RN-14 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O usuário seleciona o mês de competência e acessa a tela de Processamento da Folha. | |
| | 2. Verifica que a folha do mês está com status "Aberta" e que há ao menos um funcionário ativo. Exibe o painel de processamento habilitado com a lista de funcionários ativos. |
| 3. O usuário clica em "Processar Folha". | |
| | 4. Para cada funcionário ativo, calcula o Valor-Dia e o Valor-Hora com base no salário contratual e nos dias úteis do mês [RN-02]. |
| | 5. Aplica os descontos de faltas, atrasos e DSR perdido registrados nas exceções do mês [RN-03, RN-04, RN-05]. |
| | 6. Soma as horas extras (50% ou 100%) ao salário bruto [RN-06] e adiciona os demais proventos de rubricas cadastradas (bônus, cesta básica, PLR). |
| | 7. Calcula o Salário Bruto consolidado [RN-08], separando a base de cálculo de INSS/FGTS das rubricas indenizatórias (Cesta Básica, PLR). |
| | 8. Calcula e aplica o desconto de INSS progressivo sobre a base de cálculo [RN-09]. |
| | 9. Calcula o Salário Líquido (Σ Proventos − Σ Descontos), garantindo resultado ≥ R$ 0,00 [RN-10, RN-11]. |
| | 10. Calcula as provisões do empregador: FGTS 8% [RN-12], 13º proporcional 1/12 [RN-13] e Férias + ⅓ proporcional [RN-14]. |
| | 11. Exibe resumo consolidado: total de funcionários processados, total de proventos, total de descontos, total líquido e total de encargos. |
| 12. O usuário revisa o resumo e confirma que os valores estão corretos. | |
| | 13. Registra o processamento no log de auditoria com timestamp, usuário responsável e competência processada. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Folha já está fechada para o mês selecionado** | |
| 1. O usuário seleciona um mês cuja folha já foi fechada. | |
| | 2. O sistema detecta status "Fechada" e desabilita o botão "Processar Folha". Exibe mensagem: "A folha de [mês/ano] já foi encerrada e não pode ser reprocessada." [RN-15] |
| **A2 — Nenhum funcionário ativo** | |
| 1. O usuário tenta processar a folha. | |
| | 2. O sistema constata ausência de funcionários ativos. Exibe mensagem de erro e mantém o botão desabilitado. |
| **A3 — Salário bruto resultaria em líquido negativo** | |
| 1. Durante o cálculo, a soma dos descontos supera a soma dos proventos de um funcionário. | |
| | 2. O sistema interrompe o processamento, identifica o funcionário com o problema e exibe alerta. O usuário deve corrigir os lançamentos via UC-09 antes de reprocessar. [RN-10] |
| **A4 — Usuário cancela após visualizar o resumo** | |
| 1. Após ver o resumo (passo 11), o usuário clica em "Cancelar". | |
| | 2. O sistema descarta os resultados. A folha permanece "Aberta". Nenhum dado é persistido. |

---
---

## UC-11 — Fechar Folha de Pagamento

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Fechar Folha de Pagamento |
| **Atores**               | RH / Administrador (iniciador) |
| **Finalidade**           | Encerrar definitivamente a folha do mês de competência, tornando todos os dados imutáveis e gerando o relatório de provisões trabalhistas. |
| **Visão geral**          | O usuário, após revisar o processamento da folha, aciona o fechamento. O sistema verifica que todos os funcionários foram processados sem pendências ou erros, exige confirmação e, ao concluir, altera o status da folha para "Fechada", impedindo qualquer alteração posterior. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | UC-10, UC-12 — Requisitos: 3.1.7, 3.2.5.4, 3.2.6.4, RN-15 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O usuário acessa a tela de Processamento e Fechamento da Folha com o mês já processado. | |
| | 2. Verifica que todos os funcionários ativos possuem status "Processado" (nenhum "Pendente" ou "Erro"). Habilita o botão "Fechar Folha". |
| 3. O usuário clica em "Fechar Folha". | |
| | 4. Exibe diálogo modal de confirmação: "Esta operação é irreversível. Deseja confirmar o fechamento da folha de [mês/ano]?" com opções "Confirmar" e "Cancelar". |
| 5. O usuário clica em "Confirmar". | |
| | 6. Altera o status da folha para "Fechada". Grava snapshot dos dados no banco de dados (nome, salário, rubricas utilizadas). Aplica constraint que impede UPDATE/DELETE na folha [RN-15]. |
| | 7. Gera e exibe automaticamente o relatório de provisões do mês: FGTS, 13º Salário e Férias + ⅓ por funcionário, com totais gerais. |
| | 8. Registra o fechamento no log de auditoria com usuário, data/hora e competência. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Existem funcionários com status "Pendente" ou "Erro"** | |
| 1. O usuário tenta fechar a folha com funcionários não processados corretamente. | |
| | 2. O botão "Fechar Folha" permanece desabilitado. O sistema exibe a lista dos funcionários com pendências. |
| **A2 — Usuário cancela no diálogo de confirmação** | |
| 1. O usuário clica em "Cancelar" no diálogo modal. | |
| | 2. O sistema não realiza nenhuma operação. A folha permanece com status "Aberta" e pode ser reprocessada. |

---
---

## UC-12 — Emitir Holerites

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Emitir Holerites |
| **Atores**               | RH / Administrador (iniciador) |
| **Finalidade**           | Consultar e exibir na tela o demonstrativo individual de pagamento (holerite) de um funcionário para um mês de competência com folha encerrada. |
| **Visão geral**          | O usuário acessa o módulo de holerites, seleciona o funcionário e o mês/ano de competência (somente meses fechados estão disponíveis). O sistema recupera e exibe o holerite completo conforme os dados imutáveis registrados no fechamento. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | UC-11, UC-13 — Requisitos: 3.1.8, 3.2.6.1 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O usuário acessa o módulo "Holerites". | |
| | 2. Exibe o seletor de funcionário (campo de busca) e o seletor de mês/ano de competência, listando apenas meses com folha fechada. |
| 3. O usuário seleciona o funcionário desejado. | |
| 4. O usuário seleciona o mês/ano de competência. | |
| 5. O usuário clica em "Emitir Holerite". | |
| | 6. Recupera os dados do snapshot da folha fechada (dados imutáveis). Exibe o holerite com: Cabeçalh o (Razão Social, CNPJ da empresa, Nome, CPF mascarado, Cargo, Data de Admissão, Mês/Ano); Tabela de Proventos (código, descrição, referência, valor); Tabela de Descontos (código, descrição, referência, valor); Rodapé (Total de Proventos, Total de Descontos, Salário Líquido em destaque, linha de assinatura). |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Nenhum mês fechado disponível para o funcionário** | |
| 1. O usuário seleciona um funcionário recém admitido sem folhas fechadas. | |
| | 2. O seletor de competência não exibe opções disponíveis. O sistema exibe mensagem informando que não há folhas encerradas para o funcionário selecionado. |
| **A2 — Funcionário inativo** | |
| 1. O usuário busca um funcionário inativo. | |
| | 2. O sistema permite a consulta de holerites de meses anteriores ao desligamento, exibindo normalmente os dados históricos. |

---
---

## UC-13 — Exportar Holerites

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Exportar Holerites |
| **Atores**               | RH / Administrador (iniciador) |
| **Finalidade**           | Gerar e exportar o holerite individual em formato PDF/A para armazenamento ou entrega ao funcionário. |
| **Visão geral**          | Com o holerite já exibido na tela (UC-12), o usuário aciona a exportação em PDF. O sistema gera o arquivo com nome padronizado, sem a linha de assinatura física, e apresenta o diálogo de seleção do destino de salvamento. |
| **Tipo**                 | Secundário |
| **Referências cruzadas** | UC-12 — Requisitos: 3.1.8, 3.2.6.1, 3.3.2.3 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. Com o holerite exibido na tela (UC-12 concluído), o usuário clica em "Exportar PDF". | |
| | 2. Gera o holerite em formato PDF/A com todos os dados do demonstrativo. Substitui a linha de assinatura pelo texto "Documento Digital — Dispensa Assinatura". |
| | 3. Abre o diálogo de seleção do diretório de destino para salvamento. O nome de arquivo sugerido é HOLERITE[CPF-sem-formatação][AAAAMM].pdf. |
| 4. O usuário escolhe o diretório de destino e confirma. | |
| | 5. Salva o arquivo PDF no diretório selecionado em no máximo 5 segundos. Registra no log de auditoria: usuário, data/hora e tipo de exportação (exportação de holerite). Exibe mensagem de sucesso. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Usuário cancela o diálogo de salvamento** | |
| 1. O usuário fecha o diálogo de seleção de diretório sem confirmar. | |
| | 2. A exportação é cancelada. Nenhum arquivo é gerado e nenhum registro é feito no log de auditoria. O holerite permanece exibido na tela. |
| **A2 — Falha ao salvar o arquivo (disco cheio ou permissão negada)** | |
| 1. O sistema tenta salvar o arquivo no destino informado. | |
| | 2. Detecta a falha e exibe mensagem de erro clara, informando o motivo. O usuário pode tentar novamente com outro diretório de destino. |

---
---

## UC-14 — Emitir Relatórios Mensais

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          |  Emitir Relatórios Mensais   |
| **Atores**               | RH / Administrador (iniciador) |
| **Finalidade**           | Gerar relatórios consolidados da folha de pagamento e o arquivo de remessa bancária após o fechamento do mês, para uso contábil e operacional. |
| **Visão geral**          | O usuário acessa o módulo de relatórios, seleciona o mês de competência (fechado) e o tipo de relatório desejado. O sistema gera o documento com os dados consolidados e permite exportação em PDF. |
| **Tipo**                 | Secundário |
| **Referências cruzadas** | UC-11 — Requisitos: 3.2.6.2, 3.2.6.3, 3.2.5.4 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O usuário acessa o módulo "Relatórios Mensais". | |
| | 2. Exibe seletor de mês/ano de competência (somente meses com folha fechada) e as opções de tipo: Resumo da Folha, Relatório de Encargos e Provisões, ou Remessa Bancária. |
| 3. O usuário seleciona o mês de competência e o tipo de relatório desejado. | |   
| 4. O usuário clica em "Gerar Relatório". | |
| | 5. Recupera os dados da folha fechada. Para Resumo da Folha: exibe por funcionário — Nome, Cargo, Salário Bruto, Total Descontos, Salário Líquido e Status — com totais gerais ao final. Para Encargos e Provisões: exibe FGTS, Provisão 13º e Provisão Férias + ⅓ por funcionário, com acumulados e totais gerais. Para Remessa Bancária: exibe tabela com CPF, Nome, Banco, Agência, Conta e Valor Líquido. |
| 5. O usuário clica em "Exportar PDF". | |
| | 6. Gera o arquivo PDF com o relatório formatado. Abre diálogo de seleção de destino. Registra a exportação no log de auditoria. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Nenhum mês fechado disponível** | |
| 1. O usuário acessa o módulo sem nenhuma folha fechada no sistema. | |
| | 2. O seletor de competência não apresenta opções. O sistema exibe mensagem informando que não há folhas encerradas para geração de relatórios. |

---
---

## UC-15 — Consultar Histórico de Folhas

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Consultar Histórico de Folhas |
| **Atores**               | RH / Administrador (iniciador) |
| **Finalidade**           | Buscar e visualizar dados consolidados e individuais de folhas de meses já encerrados, sem limite de período de consulta. |
| **Visão geral**          | O usuário acessa a tela de histórico, aplica filtros de período ou funcionário e visualiza a listagem de folhas. Pode selecionar uma folha específica para ver o resumo individual de cada funcionário naquela competência. |
| **Tipo**                 | Secundário |
| **Referências cruzadas** | UC-11 — Requisitos: 3.1.9, 3.2.6.4, RN-15 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O usuário acessa o módulo "Histórico de Folhas". | |
| | 2. Exibe a listagem de todas as folhas processadas em ordem cronológica decrescente, com: Mês/Ano de Competência, Status, Data de Fechamento, Quantidade de Funcionários Processados e Total Líquido da Folha. |
| 3. O usuário aplica filtros de período (De: Mês/Ano — Até: Mês/Ano) ou por funcionário específico (opcional). | |
| | 4. Filtra e exibe a listagem conforme os critérios informados em no máximo 3 segundos. |
| 5. O usuário seleciona uma folha fechada para visualização detalhada. | |
| | 6. Exibe o resumo individual de cada funcionário na competência selecionada: Nome, Cargo, Salário Bruto, Total de Descontos, Salário Líquido. Exibe apenas o botão "Visualizar" — sem botões de edição ou processamento. |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Nenhum resultado encontrado para os filtros** | |
| 1. O usuário aplica filtros que não correspondem a nenhuma folha cadastrada. | |
| | 2. O sistema exibe mensagem informando que não foram encontrados registros para os critérios selecionados e mantém os campos de filtro disponíveis para nova consulta. |

---
---

## UC-16 — Consultar Log de Auditoria

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Consultar Log de Auditoria |
| **Atores**               | Administrador (iniciador) |
| **Finalidade**           | Acessar o histórico imutável de ações realizadas no sistema para fins de auditoria, rastreabilidade e conformidade legal, sem possibilidade de alteração ou exclusão dos registros. |
| **Visão geral**          | O administrador acessa a tela de log de auditoria, aplica filtros por período, usuário ou tipo de operação e visualiza as entradas registradas. O log é somente leitura para todos os usuários, incluindo o Administrador. |
| **Tipo**                 | Secundário |
| **Referências cruzadas** | Requisitos: 3.2.6.5, 3.3.2.5, LGPD Art. 46 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O administrador acessa o módulo "Log de Auditoria". | |
| | 2. Verifica que o usuário possui perfil Administrador. Exibe a tela de consulta ao log com filtros disponíveis: período (data início / data fim), usuário e tipo de operação. |
| 3. O administrador seleciona os filtros desejados e clica em "Consultar". | |
| | 4. Recupera e exibe as entradas do log que correspondem aos filtros, contendo: ID sequencial, data e hora, usuário, operação realizada, entidade afetada e resumo da mudança (valor anterior e novo valor, quando aplicável). |
| 5. O administrador analisa as entradas exibidas. | |
| | 6. Não exibe botões de edição ou exclusão em nenhum registro. O log é estritamente somente leitura (append-only). |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Tentativa de acesso por usuário sem perfil Administrador** | |
| 1. Um usuário com perfil Analista de RH tenta acessar o log de auditoria. | |
| | 2. O sistema não exibe o item de menu do log para o perfil RH. O acesso direto é bloqueado. |
| **A2 — Nenhum resultado para os filtros** | |
| 1. O administrador aplica filtros sem resultados correspondentes. | |
| | 2. O sistema exibe mensagem informando que não foram encontrados registros para os critérios selecionados. |

---
---

## UC-17 — Atualização de Parâmetros Legais

### Parte 1 — Resumo

| Campo                    | Descrição |
|--------------------------|-----------|
| **Caso de uso**          | Atualização de Parâmetros Legais |
| **Atores**               | Administrador (iniciador) |
| **Finalidade**           | Atualizar alíquotas e tabelas legais vigentes (piso salarial, tabela INSS, alíquota FGTS) sem necessidade de recompilar ou reinstalar o sistema, garantindo conformidade com a legislação CLT vigente. |
| **Visão geral**          | O administrador acessa o módulo de parâmetros legais, informa os novos valores (ex.: novo piso salarial, nova tabela INSS), confirma e o sistema passa a utilizar os valores atualizados nos processamentos subsequentes. Folhas já fechadas não são afetadas. |
| **Tipo**                 | Primário e essencial |
| **Referências cruzadas** | Requisitos: 3.2.1.1, 3.2.1.3, RN-01, RN-09, RN-12 |

---

### Parte 2 — Sequência típica de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| 1. O administrador acessa "Cadastros > Parâmetros Legais". | |
| | 2. Verifica perfil Administrador. Exibe os parâmetros legais atualmente configurados: piso salarial vigente, faixas e alíquotas da tabela INSS progressiva (com teto), alíquota de FGTS. |
| 3. O administrador atualiza os valores desejados (ex.: novo piso salarial aprovado por decreto, novos limites de faixa INSS). | |
| 4. O administrador clica em "Salvar". | |
| | 5. Exibe diálogo modal de confirmação: "A alteração dos parâmetros legais afetará todos os processamentos futuros. Deseja confirmar?" |
| 6. O administrador clica em "Confirmar". | |
| | 7. Persiste os novos parâmetros. Registra no log de auditoria: usuário, data/hora, parâmetros alterados, valores anteriores e novos. Exibe mensagem de sucesso. |
| | 8. Os novos parâmetros passam a ser utilizados a partir do próximo processamento de folha. Folhas já fechadas permanecem com os valores do momento do fechamento. [RN-15] |

---

### Parte 3 — Sequências alternativas de eventos

| Ação do ator | Resposta do sistema |
|---|---|
| **A1 — Administrador cancela no diálogo de confirmação** | |
| 1. O administrador clica em "Cancelar" no diálogo modal. | |
| | 2. O sistema descarta as alterações. Os parâmetros permanecem com os valores anteriores. Nenhum registro é gravado no log. |
| **A2 — Valor de piso salarial inferior ao atual** | |
| 1. O administrador informa um novo piso salarial menor que o valor já cadastrado. | |
| | 2. O sistema exibe alerta informando que o novo valor é inferior ao piso atual, solicitando confirmação explícita do administrador para prosseguir (a redução do piso é incomum e pode indicar erro de digitação). |
