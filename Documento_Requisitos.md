# Introdução

## 1.1 Propósito do Documento de Requisitos (motivações, público-alvo,...)
O sistema automatiza o cálculo e a emissão de uma folha de pagamento de funcionários. A folha de pagamento é baseada no contrato CLT, onde temos condições e requisitos mais claros e determinados para projetar. Outros regimes, como não-CLT (PJ, estagiários) e alterações contratuais do CLT estão fora do escopo do sistema. 

## 1.2 Escopo do produto (oq faz, oq não faz e desc a aplicação) 
Para a primeira fase do projeto, o sistema contará com: 
* **Histórico das Folhas de Pagamento:** Fornecerá uma interface para visualizar as antigas folhas de pagamento referente aos meses anteriores.
* **Horas Computadas:** O tempo de trabalho máximo de qualquer tipo de funcionário será de oito (8) horas diárias para cinco (5) dias na semana - segunda, terça, quarta, quinta e sexta-feira. 
* **Gestão de Cadastros:** Permitirá a inclusão, leitura e atualização de dados de funcionários, mantendo obrigatoriamente um histórico versionado de alterações salariais. A gestão também conta com a entrada e gestão de planilhas.
* **Lançamentos de exceção (mensais):** Fornecerá uma interfaces para o lançamento de eventos (variáveis) que impactam o mês corrente, e.g., horas extras, bônus, faltas, atrasos, atestados diversos. A entrada de exceção deverá ser feita de forma mensal. Lançamentos de excessões não alteram meses já fechados, implica um histórico auditável.
* **Processamento Lógico:** Após o Executará o cálculo matemático de transformação do salário bruto em salário líquido, aplicando as equações de soma de proventos e subtração de descontos registrados para o mês corrente.
* **Geração de Artefatos:** Compilará os dados processados para a emissão visual e exportável do demonstrativo de pagamento individual (holerite).
* **Gestão de Rubricas Fixas e Variáveis:** Calculará o valor da diária de trabalho ("Valor dia") com base no salário contratual e processará a concessão de benefícios fixos (ex: Cesta Básica) e variáveis (ex: Participação nos Lucros e Resultados - PRL).
* **Motor de Provisões e Encargos:** Diferente de um sistema básico, este MVP já englobará o cálculo automatizado de provisões trabalhistas essenciais exibidas no fechamento mensal, especificamente a proporcionalidade ou valor integral de **13º Salário**, **Férias + ⅓** e o recolhimento do **FGTS (com a multa de 40% embutida ou provisionada)**.
* **Geração de saídas:** A emissão do demonstrativo de pagamento individual, e.g., Holerite (contra-cheque). Permite consultas referentes a meses anteriores já fechados. As consultas abrangem um período mensal fixo. 
* **O que não será feito agora:** Por enquanto, não haverá implementação de exceção (mensais) como: fonte de impostos que não constam no fluxo atual aprovado (como tabelas progressivas de IRRF e INSS), benefícios variáveis (e.g, coparticipação em plano de saúde) e opções de relatório para cada setor da "empresa", não realizará pagamentos para regimes não-CLT (PJ, estagiários).

## 1.3 - Definições, acrônimos e abreviações
Para garantir a compreensão uniforme deste documento por leitores de diferentes áreas (técnicas e de negócios), os seguintes termos são definidos:

* **CLT:** Consolidação das Leis do Trabalho. Legislação brasileira que regulamenta as relações de trabalho e dita as regras de remuneração aplicáveis a este sistema.
* **FGTS:** Fundo de Garantia do Tempo de Serviço.
* **PRL:** Participação nos Lucros e Resultados (frequentemente tratada como PLR na legislação).
* **13º Salário:** Gratificação natalina compulsória.
* **Rubrica:** Código ou categoria contábil que identifica a natureza de um valor na folha de pagamento (ex: Rubrica de Salário Base, Rubrica de Hora Extra).
* **Provento:** Qualquer valor financeiro de natureza creditícia que é somado ao salário do colaborador (ex: bônus, comissões).
* **Desconto:** Qualquer valor financeiro de natureza debitaria que é subtraído do salário do colaborador (ex: faltas, atrasos).
* **Holerite / Contra-cheque:** O demonstrativo impresso ou digital que detalha todas as rubricas de proventos e descontos que compuseram o salário líquido do colaborador em uma competência.

## 1.4 - Referências
* As informações referentes ao regime CLT foram retiradas do documento: Consolidação das Leis do Trabalho - CLT e normas correlatas. Originária da Constituição da República Federatica do Brasil

## 1.5 - Estruturação do Documento de Requisitos (estrutura/organização)




# 2 Descrição Geral

## 2.1 Perspectiva do Produto  Relacionamento: sistema, usuário, hardware, software, comunicação.
* O sistema é uma aplicação Web Standalone voltada para o departamento de Recursos Humanos (RH) ou contabilidade.
- Interface: Navegador web (Chrome, Firefox, Edge).
- Hardware: Servidor em nuvem para processamento dos cálculos e armazenamento de banco de dados.
- Comunicação: Protocolo HTTPS para garantir a segurança dos dados sensíveis (salários e CPFs).]

## 2.2 Funcionalidades do Produto
* As principais funções que o software deve executar são:
- Cálculo Automático: Processar Salário Bruto -> Líquido.
- Gestão de Ponto/Exceções: Importação de horas extras, faltas e atrasos.
- Provisões Trabalhistas: Cálculo mensal de reserva para 13º e Férias.
- Emissão de Documentos: Geração de PDF do Holerite.
- Auditoria: Log de alterações salariais para conformidade legal.

## 2.3 Características do Usuário
* Analista de RH/Departamento Pessoal: Usuário principal. Possui conhecimento das regras da CLT, mas busca agilidade no cálculo manual.
* Administrador do Sistema: Responsável por cadastrar usuários e gerenciar permissões de acesso.

## 2.4 Restrições Gerais  Limitações de hardware, considerações sobre segurança, ...
* Legislação: O sistema deve seguir estritamente a tabela vigente da CLT (2024/2025/2026).
* Segurança: Dados salariais são protegidos pela LGPD (Lei Geral de Proteção de Dados). O acesso deve ser restrito por login e senha.
* Conformidade: O sistema não permite a alteração de folhas de meses já encerrados para garantir a integridade contábil.

## 2.5 Suposições e Dependências - Máquina específica, sistema operacional, ...
* Supõe-se que o usuário possua acesso estável à internet.
* O sistema depende da alimentação mensal das "exceções" (faltas/extras) antes do fechamento da folha.

# 3 Requisitos Específicos 
## Abrangem os requisitos funcionais, não funcionais e de interface.
## Os requisitos podem documentar interfaces externas, descrever funcionalidade e desempenho do sistema, especificar requisitos lógicos de banco de dados, restrições de projeto, propriedades emergentes do sistema e características de qualidade.

# 4 Apêndices - Para essa parte é ter feito o item 3
* Tabela de Rubricas Padrão: Lista de códigos (Ex: 001 - Salário Base, 002 - Hora Extra 50%).
* Modelos de Fórmulas: Exemplo: $Salário \ Líquido = \sum Proventos - \sum Descontos$.
# 5 Índice







