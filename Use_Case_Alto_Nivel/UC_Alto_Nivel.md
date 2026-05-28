| ID    | Caso de uso                      | Atores             | Tipo       | Descrição                                                                                 |
| ----- | -------------------------------- | ------------------ | ---------- | ----------------------------------------------------------------------------------------- |
| UC-01 | Autenticar no sistema            | Administrador, RH  | Primário   | Realiza acesso seguro com usuário e senha, validando o perfil do operador.                |
| UC-02 | Cadastro da Empresa              | Administrador      | Primário   | Cadastra os dados corporativos da empresa (CNPJ, Razão Social) e parâmetros fixos.        |
| UC-03 | Atualizar dados da Empresa       | Administrador      | Primário   | Atualiza as informações cadastrais da empresa quando necessário.                          |
| UC-04 | Cadastro de Usuários do Sistema  | Administrador      | Primário   | Cadastra novos usuários (RH ou Admin) e define seus perfis de acesso.                     |
| UC-05 | Atualizar Usuários no Sistema    | Administrador      | Primário   | Edita dados de usuários existentes, inativa contas e redefine senhas.                     |
| UC-06 | Cadastro de Funcionários         | RH / Administrador | Primário   | Cadastra dados pessoais, contratuais e informações dos funcionários.                      |
| UC-07 | Atualizar dados de Funcionários  | RH / Administrador | Primário   | Edita dados salariais/contratuais, visualiza histórico e inativa funcionários.            |
| UC-08 | Cadastro de Rubricas             | Administrador      | Primário   | Cadastra e personaliza rubricas de proventos e descontos com suas incidências.            |
| UC-09 | Lançar Exceções Mensais          | RH / Administrador | Primário   | Registra variáveis do mês: faltas, atrasos, atestados, horas extras, bônus e descontos.   |
| UC-10 | Processar Folha de Pagamento     | RH / Administrador | Primário   | Executa o cálculo da folha, apurando salário líquido, impostos (INSS) e descontos.        |
| UC-11 | Fechar Folha de Pagamento        | RH / Administrador | Primário   | Encerra a folha do mês de competência, tornando os dados imutáveis e gerando provisões.   |
| UC-12 | Emitir Holerites                 | RH / Administrador | Primário   | Consulta e exibe o demonstrativo individual de pagamento do funcionário.                  |
| UC-13 | Exportar Holerites               | RH / Administrador | Secundário | Gera e exporta o holerite individual em formato PDF.                                      |
| UC-14 | Emitir Relatórios Mensais        | RH / Administrador | Secundário | Gera relatórios consolidados da folha e arquivo de remessa bancária.                      |
| UC-15 | Consultar Histórico de Folhas    | RH / Administrador | Secundário | Busca e visualiza dados consolidados e individuais de folhas de meses encerrados.         |
| UC-16 | Consultar Log de Auditoria       | Administrador      | Secundário | Acessa o histórico imutável de ações do sistema para auditoria de alterações sensíveis.   |
| UC-17 | Atualização de Parâmetros Legais | Administrador      | Primário   | Atualiza alíquotas e tabelas legais (INSS, piso salarial, FGTS) sem recompilar o sistema. |
