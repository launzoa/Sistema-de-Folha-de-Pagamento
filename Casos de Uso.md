

| **ID**    | **Caso de Uso**                     | **Atores**        | **Tipo**   | **Descrição**                                                                                                              |
| --------- | ----------------------------------- | ----------------- | ---------- | -------------------------------------------------------------------------------------------------------------------------- |
| **UC-01** | Autenticar no Sistema (Login)       | Administrador, RH | Primário   | Realiza o acesso seguro ao sistema com usuário e senha, validando o perfil de acesso do operador.                          |
| **UC-02** | Manter Cadastro da Empresa          | Administrador     | Primário   | Cadastra ou atualiza os dados corporativos (CNPJ, Razão Social) e parâmetros fixos (ex: valor da cesta básica).            |
| **UC-03** | Gerenciar Usuários do Sistema       | Administrador     | Primário   | Cadastra novos usuários (RH ou Admin), inativa contas e realiza a redefinição manual de senhas esquecidas.                 |
| **UC-04** | Gerenciar Funcionários              | Administrador, RH | Primário   | Cadastra, edita dados salariais/contratuais, visualiza o histórico de salários e inativa funcionários.                     |
| **UC-05** | Gerenciar Rubricas                  | Administrador     | Primário   | Cadastra e personaliza rubricas de proventos e descontos específicos da empresa, definindo suas incidências.               |
| **UC-06** | Lançar Exceções Mensais             | Administrador, RH | Primário   | Registra todas as variáveis do mês ativo: faltas, atrasos, atestados, horas extras, bônus, PLR e descontos diversos.       |
| **UC-07** | Processar Folha de Pagamento        | Administrador, RH | Primário   | Executa a rotina de cálculos transformando o salário base e exceções no salário líquido, apurando os impostos (INSS).      |
| **UC-08** | Fechar Folha de Pagamento           | Administrador, RH | Primário   | Encerra a folha do mês de competência, tornando os dados imutáveis, gerando provisões (13º, Férias) e encargos (FGTS).     |
| **UC-09** | Emitir e Exportar Holerites         | Administrador, RH | Primário   | Consulta e exporta o demonstrativo individual de pagamento (holerite) em formato PDF.                                      |
| **UC-10** | Gerar Relatórios e Remessa Bancária | Administrador, RH | Secundário | Gera relatórios mensais consolidados da folha e o arquivo de remessa simplificado para pagamentos via banco.               |
| **UC-11** | Consultar Histórico de Folhas       | Administrador, RH | Secundário | Permite buscar e visualizar os dados consolidados e individuais de folhas de meses já encerrados.                          |
| **UC-12** | Consultar Log de Auditoria          | Administrador     | Secundário | Acessa o histórico imutável de ações do sistema para auditar alterações sensíveis feitas por qualquer usuário.             |
| **UC-13** | Atualizar Parâmetros Legais         | Administrador     | Primário   | Atualiza as alíquotas e tabelas legais (faixas de INSS, piso salarial, etc.) aplicáveis aos cálculos matemáticos da folha. |
