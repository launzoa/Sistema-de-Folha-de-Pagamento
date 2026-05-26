| ID    | Caso de Uso                                 | Atores             | Tipo       | Descrição                                                                             |
| ----- | ------------------------------------------- | ------------------ | ---------- | ------------------------------------------------------------------------------------- |
| UC-01 | Cadastro da Empresa                         | Administrador      | Primário   | Realiza login e cadastra ou atualiza dados da empresa com as informações necessárias. |
| UC-02 | Cadastro de Usuários do Sistema             | Administrador      | Primário   | Cadastra usuários no sistema (RH ou administrador) e define perfis.                   |
| UC-03 | Cadastro e manipulação de Funcionários      | RH / Administrador | Primário   | Cadastra e edita dados pessoais, contratuais e informações dos funcionários.          |
| UC-04 | Cadastro e manipulação de Rubricas          | RH / Administrador | Primário   | Cadastra e edita proventos, descontos e parâmetros de cálculo das rubricas.           |
| UC-05 | Lançamento de Faltas Injustificadas         | RH                 | Primário   | Cadastra faltas injustificadas para cálculo de descontos e DSR.                       |
| UC-06 | Lançamento de Atestados Médicos             | RH                 | Primário   | Cadastra afastamentos justificados que não geram desconto.                            |
| UC-07 | Lançamento de Horas Extras                  | RH                 | Primário   | Cadastra horas extras (50% e 100%) conforme regras definidas.                         |
| UC-08 | Lançamento de Bônus e Gratificações         | RH                 | Primário   | Cadastra valores adicionais variáveis aos funcionários.                               |
| UC-09 | Lançamento de Participação nos Lucros (PLR) | RH / Administrador | Primário   | Registra valores de PLR conforme regras da empresa.                                   |
| UC-10 | Lançamento de Descontos Variáveis           | RH                 | Primário   | Registra descontos diversos aplicáveis aos funcionários.                              |
| UC-11 | Solicitar Holerite dos Funcionários         | RH                 | Primário   | Permite visualizar ou emitir o holerite individual.                                   |
| UC-12 | Solicitar Relatório Mensal                  | RH / Administrador | Secundário | Gera relatórios consolidados da folha de pagamento.                                   |
| UC-13 | Exportar PDF                                | RH / Administrador | Secundário | Gera arquivo PDF de relatório ou documento.                                           |
| UC-14 | Consulta de Folhas Anteriores               | RH / Administrador | Secundário | Permite consultar folhas encerradas e visualizar dados consolidados e individuais.    |
| UC-15 | Consulta do Log de Auditoria                | Administrador      | Secundário | Permite visualizar histórico de ações no sistema.                                     |
| UC-16 | Recuperar Senha                             | RH / Administrador | Secundário | Permite redefinir senha (caso implementado - veja pf).                                |
| UC-17 | Atualização de Parâmetros Legais            | Administrador      | Primário   | Atualiza tabelas legais (INSS, FGTS, etc.) sem recompilar o sistema.                  |
| UC-18 | Processar folha de pagamento                | RH                 | Primário   | Processa a folha, calculando proventos, descontos e salário líquido.                  |
| UC-19 | Fechar folha de pagamento                   | RH                 | Primário   | Encerra a folha, tornando-a imutável e gerando provisões automaticamente.             |
**Os UC 5 a 10 se pa que pode deixar em um único como lançamento de exceções**

