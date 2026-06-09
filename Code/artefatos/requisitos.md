
# Requisitos Funcionais

### Funcionalidade

**RFF-01 — Processar folha de pagamento**
* RFF-01.01 — O sistema deve calcular o salário proporcional baseado nos dias úteis e dias trabalhados.
* RFF-01.02 — O sistema deve calcular os descontos ativos do funcionário no mês de referência.
* RFF-01.03 — O sistema deve calcular o salário líquido do funcionário no mês de referência

**RFF-02 — Calcular INSS progressivo**
* RFF-02.01 — O sistema deve identificar a faixa do INSS correspondente ao salário bruto. 
* RFF-02.02 — O sistema deve calcular o desconto com base no salário bruto e a taxas da faixa do INSS: alíquota e parcela a deduzir
* RFF-02.03 — O sistema deve retornar o teto máximo como desconto para salário bruto acima da faixa máxima do INSS. 


**RFF-03 — Calcular horas extras**
* RFF-03.01 — O sistema deve identificar corretamente cada tipo de hora extra: 50% ou 100%.
* RFF-03.02 — O sistema deve identificar a quantidade de horas extras para cada tipo de hora extra. 
* RFF-03.03 — O sistema deve calcular o valor unitário de cada tipo de hora extra.
* RFF-03.04 — O sistema deve calcular o valor total para cada tipo de hora extra.

**RFF-04 — Calcular salario proporcional**
* RFF-04.01 — O sistema deve receber corretamente a quantidade de dias trabalhados e quantidade de dias úteis no mês.
* RFF-04.02 — O sistema deve calcular o valor da diária com base no salário bruto e nos dias úteis do mês. 
* RFF-04.03 — O sistema deve calcular o valor proporcional do salário com base no valor da diária e os dias trabalhados.

### Usabilidade

**RFU-01 — Tela de folha de pagamento**
RFU-01.01 — O sistema deve apresentar uma interface gráfica para o processamento da folha de pagamento.

### Confiabilidade (Reliability)

**RFR-01 — Cálculos**
* RFR-01.01 — O sistema não deve apresentar erros de cálculos.
* RFR-01.02 — O sistema deve apresentar dízimas em precisão de 2 casas decimais, utilizando o método de arredondamento metade para cima, do padrão bancário.


### Suportabilidade

**RFS-01 — Linguagem**
* RFS-01.01 — O sistema deve rodar em linguagem Java, versão 11.

**RFS-02 — Framework**
* RFS-02.01 — O sistema utilizará o framework JavaFX para renderização da interface.
* RFS-02.02 — O sistema utilizará padrão arquitetural Model View Controller (MVC).
