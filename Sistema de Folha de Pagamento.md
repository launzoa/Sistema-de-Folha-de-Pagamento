# Introdução

O sistema automatiza o cálculo e a emissão de uma folha de pagamento de funcionários. A folha de pagamento é baseada no contrato CLT, onde temos condições e requisitos mais claros e determinados para projetar. Outros regimes, como não-CLT (PJ, estagiários) estão fora do escopo do sistema. 
Para a primeira fase do projeto, o sistema contará com: 
* **Gestão funcional e salarial:** O cadastro de colaboradores com histórico temporal de salários.
 * **Lançamentos de exceção (mensais):** Entradas de eventos (variáveis) que impactam o mês corrente, e.g., horas extras, bônus, faltas e atrasos.
* **Processamento e fechamento**: A transformação do salário bruto em líquido, considerando as exceções (caso existam), e.g., salário base + proventos - descontos legais.
* **Geração de saídas**: A emissão do demonstrativo de pagamento individual, e.g., Holerite (contra-cheque).

**O que não será feito agora**, podendo ser feito conforme os primeiros requisitos terem sido concluídos: Fisco-trabalhista (FGTS, INSS e IRRF), benefícios variáveis (e.g., coparticipação em plano de saúde) e opções de relatório para cada setor da "empresa". 

# Documento de requisitos
- **Gestão de Cadastros:** Permitirá a inclusão, leitura e atualização de dados de funcionários, mantendo obrigatoriamente um histórico versionado de alterações salariais. A gestão também conta com a entrada e gestão de planilhas.
- **Histórico das Folhas de Pagamento:** Fornecerá uma interface para visualizar as antigas folhas de pagamento referente aos meses anteriores.
- **Gestão de Exceções Mensais:** Fornecerá uma interfaces para o lançamento de eventos variáveis que ocorrem em uma competência específica, como o registro de **horas extras** realizadas, bonificações concedidas, ou **descontos devidos a faltas injustificadas e atrasos (atestado)**.
- **Processamento Lógico:** Após o Executará o cálculo matemático de transformação do salário bruto em salário líquido, aplicando as equações de soma de proventos e subtração de descontos registrados para o mês corrente.
- **Geração de Artefatos:** Compilará os dados processados para a emissão visual e exportável do demonstrativo de pagamento individual (holerite).
- **Gestão de Rubricas Fixas e Variáveis:** Calculará o valor da diária de trabalho ("Valor dia") com base no salário contratual e processará a concessão de benefícios fixos (ex: Cesta Básica) e variáveis (ex: Participação nos Lucros e Resultados - PRL).
- **Motor de Provisões e Encargos:** Diferente de um sistema básico, este MVP já englobará o cálculo automatizado de provisões trabalhistas essenciais exibidas no fechamento mensal, especificamente a proporcionalidade ou valor integral de **13º Salário**, **Férias + ⅓** e o recolhimento do **FGTS (com a multa de 40% embutida ou provisionada)**.

**O que o produto NÃO fará nesta fase inicial (Restrições de Escopo):**

- Não realizará pagamentos para regimes não-CLT (PJ, estagiários).
    
- Não gerenciará a retenção na fonte de impostos que não constam no fluxo atual aprovado (como tabelas progressivas de IRRF e INSS).

### 1.3 Definições, Acrônimos e Abreviações

Para garantir a compreensão uniforme deste documento por leitores de diferentes áreas (técnicas e de negócios), os seguintes termos são definidos:

- **CLT:** Consolidação das Leis do Trabalho. Legislação brasileira que regulamenta as relações de trabalho e dita as regras de remuneração aplicáveis a este sistema.
- **FGTS:** Fundo de Garantia do Tempo de Serviço.
- **PRL:** Participação nos Lucros e Resultados (frequentemente tratada como PLR na legislação).
- **13º Salário:** Gratificação natalina compulsória.
- **Rubrica:** Código ou categoria contábil que identifica a natureza de um valor na folha de pagamento (ex: Rubrica de Salário Base, Rubrica de Hora Extra).
- **Provento:** Qualquer valor financeiro de natureza creditícia que é somado ao salário do colaborador (ex: bônus, comissões).
- **Desconto:** Qualquer valor financeiro de natureza debitaria que é subtraído do salário do colaborador (ex: faltas, atrasos).
- **Holerite / Contra-cheque:** O demonstrativo impresso ou digital que detalha todas as rubricas de proventos e descontos que compuseram o salário líquido do colaborador em uma competência.