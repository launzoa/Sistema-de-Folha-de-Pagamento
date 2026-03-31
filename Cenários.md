### Cenário 1: Cadastro de um Novo Funcionário

* **Premissa inicial:** Um Analista de RH ou Administrador está autenticado no sistema e possui em mãos os dados e documentos admissionais de um novo colaborador. O usuário encontra-se na tela do menu principal.   
* **Fluxo normal:** O usuário acessa o módulo de "Cadastros de funcionários" e preenche os campos obrigatórios: 
	* Nome completo;
	* CPF;
	* Data de Nascimento;
	* Data de Admissão;
	* Cargo;
	* Salário Base;
	* Banco;
	* Conta-corrente.
* O usuário insere um salário base válido e clica para salvar. O sistema confirma a operação, define o status do funcionário como "Ativo", inicializa o cálculo automático de tempo de empresa e registra o salário inicial no histórico versionado de alterações salariais.
* **O que pode dar errado:** O CPF inserido possui todos os dígitos iguais ou já pertence a outro funcionário cadastrado no sistema. O sistema bloqueia a ação, rejeita a inserção e exibe uma mensagem de erro específica.
	* O Salário Base informado é inferior ao piso estipulado (R$ 1.518,00). O sistema rejeita o cadastro e exibe uma mensagem de alerta informando o valor mínimo aceitável.
* **Outras atividades:** Outro usuário credenciado pode estar simultaneamente lançando exceções mensais ou consultando o histórico de folhas de pagamento em outra máquina. 
12. **Estado do sistema na conclusão:** O usuário continua com a sessão ativa. O novo funcionário consta na base de dados relacional, listado com status "Ativo", e está apto para participar do processamento da folha do mês de competência corrente.

### Cenário 2: Lançamento de Exceções Mensais (Horas Extras e Atestado)
* **Premissa inicial:** Um Analista de RH acessou o sistema e a folha de pagamento do mês de competência atual está com o status "Aberta". O analista precisa registrar na plataforma as variáveis (exceções) de um funcionário com base em sua folha de ponto.
* **Fluxo normal:** O usuário navega até a tela de "Lançamentos de exceções" e busca o funcionário desejado. Ele insere a quantidade de horas extras realizadas, a data da ocorrência e seleciona o adicional aplicável (50% ou 100%), com o sistema calculando e exibindo o valor monetário instantaneamente. Em seguida, o usuário lança um atestado médico, informando as datas de início e fim, e anexa o arquivo PDF digitalizado do documento. O sistema processa a informação para neutralizar o desconto de falta e resguardar o Descanso Semanal Remunerado (DSR). O usuário salva as edições.
* **O que pode dar errado:**   
    - O usuário tenta fazer lançamentos para um mês cuja folha já possui o status "Fechada". O sistema bloqueia a inserção de eventos e exibe uma mensagem explicativa de que meses fechados não são alterados.
    - O atestado médico anexado indica um afastamento superior ao limite de 15 dias corridos consecutivos. O sistema emite um aviso visual alertando sobre o excesso.
    - A quantidade de horas extras ultrapassa o limite diário definido por lei. O sistema emite um alerta de aviso não impeditivo ao usuário.       
4. **Outras atividades:** O Administrador com acesso master pode estar editando os parâmetros fiscais da empresa, gerando um registro no histórico de ações de auditoria.
5. **Estado do sistema na conclusão:** As exceções mensais ficam gravadas no sistema e atreladas ao funcionário para o mês corrente. O colaborador precisará passar pelo processamento da folha para que essas rubricas variáveis integrem a composição final de seu salário bruto e líquido.

### Cenário 3: Processamento e Fechamento da Folha
* **Premissa inicial:** Todos os lançamentos de proventos e descontos variáveis do mês já foram concluídos pela equipe. O usuário logado está na tela de "Processamento e Fechamento da Folha" e o mês corrente exibe o status "Aberta".   
* **Fluxo normal:** O usuário clica no botão "Processar Folha". O sistema realiza a verificação de anomalias e executa os cálculos matemáticos para todos os funcionários ativos (levando no máximo 45 segundos). Um resumo tabular é exibido contendo Salário Bruto, Proventos, Descontos, Salário Líquido e o Status de cada funcionário. Tudo estando correto, o usuário clica em "Fechar Folha" e confirma a operação em uma janela de diálogo. O sistema empacota um clone de backup assinado da base, torna a competência imutável e gera automaticamente o relatório de provisões (13º, Férias e FGTS).
3. **O que pode dar errado:**
    - Após o cálculo, algum funcionário acusa o status de processamento como "Pendente" ou "Erro". O sistema mantém o botão "Fechar Folha" desabilitado até que o erro na base do trabalhador seja estornado e corrigido.
    - As conexões com o banco de dados evaporam no meio do processamento. O sistema exibe um pop-up de aguardo por 30 segundos forçando o restabelecimento para não corromper o montante fechado.
    - O diretório configurado para receber o clone espelho de backup não possui permissões de escrita ou está ausente. O sistema dispara um alerta paralisando o fechamento.
4. **Outras atividades:** Nenhuma alteração cadastral ou lançamento de evento que afete a competência em fechamento pode estar ocorrendo simultaneamente.
5. **Estado do sistema na conclusão:** O status do mês é atualizado para "Fechada", bloqueando edições futuras em prol da integridade contábil. Os demonstrativos de pagamento individuais (Holerites) convertem-se em um formato final disponibilizado para emissão visual e exportação no padrão PDF/A. O arquivo mensal é eternizado no banco de dados.