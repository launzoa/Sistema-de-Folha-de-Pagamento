# Introdução

O sistema automatiza o cálculo e a emissão de uma folha de pagamento de funcionários. A folha de pagamento é baseada no contrato CLT, onde temos condições e requisitos mais claros e determinados para projetar. Outros regimes, como não-CLT (PJ, estagiários) estão fora do escopo do sistema. 
Para a primeira fase do projeto, o sistema contará com: 
* **Gestão funcional e salarial:** O cadastro de colaboradores com histórico temporal de salários.
 * **Lançamentos de exceção (mensais):** Entradas de eventos (variáveis) que impactam o mês corrente, e.g., horas extras, bônus, faltas e atrasos.
* **Processamento e fechamento**: A transformação do salário bruto em líquido, considerando as exceções (caso existam), e.g., salário base + proventos - descontos legais.
* **Geração de saídas**: A emissão do demonstrativo de pagamento individual, e.g., Holerite (contra-cheque).

**O que não será feito agora**, podendo ser feito conforme os primeiros requisitos terem sido concluídos: Fisco-trabalhista (FGTS, INSS e IRRF), benefícios variáveis (e.g., coparticipação em plano de saúde) e opções de relatório para cada setor da "empresa". 

# Documento de requisitos
