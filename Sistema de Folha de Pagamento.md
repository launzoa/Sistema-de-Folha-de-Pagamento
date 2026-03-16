# Introdução

O sistema automatiza o cálculo e a emissão de uma folha de pagamento de funcionários. A folha de pagamento é baseada no contrato CLT, onde temos condições e requisitos mais claros e determinados para projetar. Outros regimes, como não-CLT (PJ, estagiários) estão fora do escopo do sistema. 
Para a primeira fase do projeto, o sistema contará com: 
* **Gestão funcional e salarial:** O cadastro de colaboradores com histórico temporal de salários.
 * **Lançamentos de exceção (mensais):** Entradas de eventos (variáveis) que impactam o mês corrente, e.g., horas extras, bônus, faltas e atrasos.
* **Processamento e fechamento**: A transformação do salário bruto em líquido, considerando as exceções (caso existam), e.g., salário base + proventos - descontos legais.
* **Geração de saídas**: A emissão do demonstrativo de pagamento individual, e.g., Holerite (contra-cheque).

**O que não será feito agora**, podendo ser feito conforme os primeiros requisitos terem sido concluídos: Fisco-trabalhista (FGTS, INSS e IRRF), benefícios variáveis (e.g., coparticipação em plano de saúde) e opções de relatório para cada setor da "empresa". 

# Documento de requisitos
### 1. Introdução

##### 1.1 Propósito do documento de requisitos
O propósito é utilizar o sistema como uma forma de otimizar estes processos.

##### 1.2 Escopo do produto
A princípio, para fazer a folha de pagamento (simples), é preciso seguir alguns passos para gerar a folha, sendo eles:
1. Calcular o salário bruto do funcionário, incluindo o salário base, as horas extras, as adicionais e os prêmios;
2. Deduzir do salário bruto os descontos obrigatórios, como o INSS, o IRRF e o FGTS;
3. Calcular o salário líquido, que é o valor que o funcionário irá receber;


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

##### 1.3 Definições, Acrônimos e Abreviações
Para garantir a compreensão uniforme deste documento por leitores de diferentes áreas (técnicas e de negócios), os seguintes termos são definidos:

- **CLT:** Consolidação das Leis do Trabalho da Legislação brasileira que regulamenta as relações de trabalho e dita as regras de remuneração aplicáveis a este sistema.
- **FGTS:** Fundo de Garantia do Tempo de Serviço.
- **PRL:** Participação nos Lucros e Resultados (frequentemente tratada como PLR na legislação).
- **13º Salário:** Gratificação natalina compulsória.
- **Rubrica:** Código ou categoria contábil que identifica a natureza de um valor na folha de pagamento (ex: Rubrica de Salário Base, Rubrica de Hora Extra).
- **Provento:** Qualquer valor financeiro de natureza creditícia que é somado ao salário do colaborador (ex: bônus, comissões).
- **Desconto:** Qualquer valor financeiro de natureza debitaria que é subtraído do salário do colaborador (ex: faltas, atrasos).
- **Holerite / Contra-cheque:** O demonstrativo impresso ou digital que detalha todas as rubricas de proventos e descontos que compuseram o salário líquido do colaborador em uma competência.
* **Salário bruto:** valor total que o funcionário recebe, antes dos descontos obrigatórios. É calculado multiplicando o salário base pela quantidade de horas trabalhadas no mês.
- **Descontos obrigatórios:**  valores que são descontados do salário bruto, de acordo com a legislação vigente. São eles: INSS, IRRF e FGTS.
- **Salário líquido:** valor que o funcionário recebe, após os descontos obrigatórios. É calculado subtraindo os descontos obrigatórios do salário bruto.

##### 1.4 Referências


##### 1.5 Visão geral do restante do documento


### 2. Descrição Geral

##### 2.1 Perspectiva do Produto
Não existe um modelo único e obrigatório para a folha de pagamento. Cada empresa cria e adota critérios específicos de acordo com suas especificidades e necessidades. No entanto, existem informações que são obrigatórias de estarem presentes no documento, são elas:
- Nome completo do colaborador, seja ele empregado, prestador de serviço, autônomo etc.;
- Função, cargo ou serviços prestados;
- Frequência, incluindo faltas, atrasos e afastamentos;
- Descontos dos encargos sociais;
- Valor líquido a ser recebido pelo funcionário;
- Forma de pagamento e data em que o valor estará disponível.

Os benefícios legais, aqueles valores pagos aos colaboradores referentes a vale-transporte, vale-alimentação, contribuição sindical etc., também são deduzidos do salário e, por isso, devem aparecer discriminados na folha de pagamento. No mais, são descontados na folha de pagamento os seguintes itens:
**1. INSS:** 
- FGTS (não é descontado);
- Imposto de Renda Pessoa Física (IRRF);
- Vale-refeição;
- Vale-transporte;
- Contribuição sindical;
- Contribuição mensal;
- Contribuição sindical anual;
- Faltas e atrasos.


##### 2.2 Funcionalidades do Produto
Em resumo, o cálculo da folha de pagamento, é dado por:
- Salário bruto = $\text{salário base} \times \text{horas trabalhadas}$
- Descontos obrigatórios = $\text{INSS} + \text{IRRF} + \text{FGTS}$
- Salário líquido = $\text{salário bruto }– \text{ descontos obrigatórios}$


##### 2.3 Características do Usuário

### Janelas
* 1 janela para cadastro de dados críticos (empresa, adm/rh)
* 1 janela para cadastro para dos funcionarios (dados pessoas, salário, etc). O foco são em dados estáticos.
* 1 janela para cadastro de proventos e descontos (horas extras)
* 1 janela para o cadastro de horas (dias/horas trabalhados)
* 1 janela para a saída (emissão da folha de pagamento)
* 1 janela para histórico de folhas de pagamento
### 3 Requisitos
##### 3.1 Requisitos Funcionais (RF)
- **RF01 - Gestão de Cadastros Críticos:** O Requisito Funcional 01 (RF01) estabelece a base estrutural e os parâmetros globais de funcionamento do sistema de folha de pagamento.
	- O sistema deve prover uma interface dedicada e restrita para o cadastro, leitura e armazenamento dos dados estruturais do empregador. Isso engloba informações como o CNPJ e as alíquotas tributárias vigentes (fundamentais para o processamento matemático subsequente). A premissa técnica destes dados é a sua estabilidade: são informações organizacionais de caráter estático que sofrem modificações com baixíssima frequência, distinguindo-se claramente dos eventos transacionais de fechamento mensal.
	- O requisito impõe uma regra de negócio baseada na separação de privilégios. O sistema deve distinguir obrigatoriamente o usuário Administrador (ADM) do operador comum (o funcionário do setor que utiliza o sistema diariamente para apurar frequências e gerar folhas),. A permissão para inserir, editar ou excluir os dados críticos da empresa é uma prerrogativa exclusiva e isolada do perfil ADM. O operador diário terá, no máximo, permissões de leitura destas informações, prevenindo adulterações acidentais ou intencionais nos alicerces contábeis do sistema.
	- A persistência e a configuração correta destes cadastros básicos atuam como uma pré-condição lógica obrigatória para a operação do software. Sem que os dados da empresa estejam devidamente registrados e blindados pelo Administrador, os módulos subsequentes do sistema (como a apuração de horas, o processamento de exceções e a emissão do holerite) não podem ser instanciados ou executados.

- **RF02 - Gestão de Cadastros não-Críticos:** O Requisito Funcional 02 (RF02) descreve a função do sistema responsável pelo armazenamento e gerenciamento das informações dos empregados. 
	-  **Faz:** O sistema deve prover interfaces de usuário e estruturas de dados para permitir a inclusão, leitura e atualização do cadastro dos funcionários. Os dados obrigatórios contemplam informações pessoais, registro de dependentes, data de admissão e a alocação de cargo.
	- **Não faz:** O sistema não pode realizar atualizações destrutivas no campo de remuneração (ou seja, apagar o salário antigo ao registrar um novo). É obrigatório manter um registro temporal e versionado de todas as alterações salariais. Do ponto de vista técnico, cada salário deve estar atrelado a um período de vigência, garantindo que o sistema possa consultar o valor exato do salário base em meses anteriores para auditorias ou cálculos de recálculo retroativo.

- **RF03 - Lançamento de Exceções Mensais:** O Requisito Funcional 03 (RF03) estabelece o mecanismo pelo qual o sistema recebe e gerencia as ocorrências temporárias que alteram a remuneração de um colaborador. No contexto da especificação de sistemas, este requisito trata do fluxo de dados dinâmicos (transacionais), em oposição aos dados estáticos e permanentes definidos no cadastro do funcionário.
	- O sistema deve fornecer uma interface específica para que o operador registre eventos pontuais que impactam exclusivamente o mês corrente (a competência atual). Esta rotina abrange a entrada de ocorrências que não são fixas, compreendendo tanto os acréscimos na remuneração (como horas extras realizadas e bonificações concedidas) quanto as deduções (como faltas injustificadas e atrasos).
	- A entrada dessas exceções não é realizada de forma genérica. O sistema obriga que todo evento variável seja categorizado através do uso de "Rubricas" (ou Verbas). Uma rubrica atua como um código contábil que identifica de maneira inequívoca a natureza daquele valor na folha de pagamento. O uso de rubricas é o que sinaliza para o motor de cálculo se o lançamento inserido é um "Provento" (um valor de natureza creditícia que será somado ao salário) ou um "Desconto" (um valor que será subtraído).
	- A mecânica de exceções atua sob uma delimitação estrita de tempo. Os eventos inseridos neste módulo afetam apenas o cálculo matemático que transforma o salário bruto em salário líquido no respectivo mês de processamento. Isso significa que o lançamento de uma hora extra ou de uma falta não altera o contrato base do funcionário, garantindo que o holerite daquela competência detalhe perfeitamente a realidade daquele mês isolado.

- **RF04 - Processamento Lógico de Folha:** O Requisito Funcional 04 (RF04) corresponde à declaração do serviço algorítmico e matemático central oferecido pelo sistema de software. Ele especifica a rotina de fechamento de folha que transforma o salário bruto em salário líquido do colaborador. 
- Primeiro, é realizado o cálculo do salário bruto, derivado da multiplicação do salário base (ou "Valor dia") pelo quantitativo de horas ou dias efetivamente trabalhados; Após, é feita a consolidação de proventos, somando-se à base os eventos variáveis de natureza creditícia (como horas extras, adicionais e comissões); Por fim, é realizado o processamento de deduções, que subtrai do montante os descontos obrigatórios estipulados por lei (INSS, IRRF) e outras deduções aplicáveis (como faltas e atrasos). O produto desta equação é o salário líquido.


 **RF05 - Geração de Artefatos:** O Requisito Funcional 05 (RF05) descreve o serviço final do ciclo de processamento da folha, responsável pela materialização dos cálculos matemáticos em um documento oficial e inteligível. 
 * Após a execução do motor de cálculo, o sistema deve agregar todas as variáveis processadas para gerar o demonstrativo de pagamento individual (o Holerite ou Contra-cheque). Este documento deve discriminar de forma transparente todas as rubricas aplicadas na competência. A compilação deve detalhar obrigatoriamente o salário bruto (base e horas trabalhadas), a lista de proventos (como horas extras e prêmios), os descontos (como encargos sociais, faltas e benefícios) e, por fim, apresentar com exatidão o salário líquido a ser recebido pelo funcionário.
* O sistema deve prover uma janela de interface dedicada estritamente à emissão e conferência da folha de pagamento. Essa funcionalidade garante que o operador (gestor ou RH) possa visualizar o demonstrativo individual compilado diretamente na tela do software de forma clara e estruturada.
* Para que o artefato cumpra a sua função legal e probatória nas relações trabalhistas, a informação não pode ficar restrita ao banco de dados ou à tela do sistema. O requisito impõe que o documento gerado seja exportável (podendo ser salvo em formatos digitais padrão para envio eletrônico) e que possua uma versão formatada para impressão física. Isso garante a entrega do comprovante detalhado ao colaborador, atestando o cumprimento das obrigações da empresa.

**RF06 - Histórico de Competências:** O sistema deve fornecer uma interface para visualização e recuperação de folhas de pagamento fechadas de meses anteriores,.

##### 3.2. Requisitos Não Funcionais (RNF)
Consistem em restrições sobre os serviços oferecidos pelo sistema, incluindo confiabilidade, eficiência e segurança,.

- **RNF01 - Persistência e Integridade Transacional:** O fechamento da folha deve ser atômico. Falhas no software ou hardware durante o processamento não podem corromper os registros financeiros (exigindo mecanismos de consistência de dados),.
- **RNF02 - Controle de Acesso e Isolamento:** O acesso às interfaces de parametrização e cálculo deve ser restrito a usuários do grupo Administrativo/RH, exigindo autenticação,.

### 3.3. Requisitos de Interface (RI)

- **RI01 - Módulos de Interface:** A interface de usuário deve ser segregada em janelas específicas: (1) Dados críticos da empresa, (2) Cadastro estático de funcionários, (3) Cadastro de proventos e descontos, (4) Cadastro de horas/frequência, (5) Emissão da folha e (6) Histórico,.

**1. Cadastros Básicos**
- Cadastro de Empresa (Dados do empregador, CNPJ, alíquotas tributárias) e do ADM/RH.
- Cadastro de Verbas/Rubricas (Proventos e Descontos)
- Cadastro de Funcionários (Dados pessoais, dependentes, admissão, cargos e salários)

**2. Apuração de Frequência**
- Registro de Dias Trabalhados
- Apuração de Horas Extras
- Apuração de Faltas e Atrasos
- Cálculo de Descanso Semanal Remunerado (DSR) 

**3. Processamento de Proventos (Ganhos)**
- Cálculo do Salário Base ou Proporcional
- Cálculo de Adicionais (Insalubridade, Periculosidade, Noturno)
- Cálculo de Comissões ou Premiações
- Pagamento de Adiantamento Quinzenal (Vale) 

**4. Processamento de Descontos**
- Cálculo de INSS (Previdência Social)
- Cálculo de IRRF (Imposto de Renda Retido na Fonte)
* Desconto de Benefícios (Vale-Transporte, Vale-Refeição, Plano de Saúde)  
- Descontos Diversos (Adiantamentos, Pensão Alimentícia)

**5. Encargos e Obrigações Acessórias**
- Cálculo de FGTS (Fundo de Garantia)
- Cálculo de INSS Patronal
- Integração/Geração de eventos para o eSocial

**6. Saídas e Relatórios**
- Cálculo do Salário Líquido
- Geração do Holerite (Recibo de Pagamento)
- Geração de Arquivo de Remessa Bancária (Líquidos para pagamento)
- Relatório de Resumo da Folha

# Referências:
Sólides:
* https://solides.com.br/blog/sistema-de-folha-de-pagamento/, introdução sobre sistemas de folhas de pagamentos.
* https://solides.com.br/blog/calculo-de-folha-de-pagamento/, detalhes sobre o cálculo da folha de pagamento.