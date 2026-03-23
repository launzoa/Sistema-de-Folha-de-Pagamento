# Introdução

### 1.1 Propósito do Documento de Requisitos
O sistema automatiza o cálculo e a emissão de uma folha de pagamento de funcionários. A folha de pagamento é baseada no contrato CLT, onde temos condições e requisitos mais claros e determinados para projetar. Outros regimes, como não-CLT (PJ, estagiários) estão fora do escopo do sistema. 

### 1.2 Escopo do Produto
Para a primeira fase do projeto, o sistema contará com: 
* **Gestão de Cadastros:** Permitirá a inclusão, leitura e atualização de dados de funcionários, através de uma interface em comum, mantendo obrigatoriamente um histórico versionado de alterações salariais. A gestão também conta com a entrada e gestão de planilhas.
* **Histórico das Folhas de Pagamento:** Interface para visualizar as antigas folhas de pagamento referente aos meses anteriores.
 * **Lançamentos de exceção (mensais):** Interfaces para o lançamento de eventos (variáveis) que impactam o mês corrente, ex: horas extras, bônus, faltas, atrasos, atestados diversos. A entrada de exceção deverá ser feita de forma mensal. Lançamentos de exceções não alteram meses já fechados.
* **Processamento e fechamento**: Após o processamento, executará o cálculo matemático de transformação, considerando as exceções, proventos e a subtração de descontos (caso existam) registrados para o mês corrente.
* **Geração de Artefatos**: Compilará os dados processados para a emissão visual e exportável do demonstrativo de pagamento individual (Holerite\Contra-Cheque). Assim como a permissão de consultas referentes a meses anteriores já fechados. As consultas abrangem um período mensal fixo. 
* **Gestão de Rubricas Fixas e Variáveis:** Calculará o valor da diária de trabalho ("Valor dia") com base no salário contratual e processará a concessão de benefícios fixos (ex: Cesta Básica) e variáveis (ex: Participação nos Lucros e Resultados - PRL).
* **Provisões e Encargos:** Engloba o cálculo automatizado de provisões trabalhistas essenciais exibidas no fechamento mensal, especificamente a proporcionalidade ou valor integral de **13º Salário**, **férias + ⅓** e o recolhimento do **FGTS (com a multa de 40% embutida ou provisionada).

### 1.3 Definições, acrônimos e abreviações
Para garantir a compreensão uniforme deste documento por leitores de diferentes áreas (técnicas e de negócios), os seguintes acrônimos e termos legislativos são definidos:
* **CLT:** Consolidação das Leis do Trabalho. Legislação brasileira que 
  regulamenta as relações de trabalho e dita as regras de remuneração 
  aplicáveis a este sistema.
* **FGTS:** Fundo de Garantia do Tempo de Serviço.
* **PLR:** Participação nos Lucros e Resultados. Também referenciada como 
  PRL em alguns contextos. Não integra a base de cálculo de INSS, FGTS 
  ou férias (Lei nº 10.101/2000).
* **13º Salário:** Gratificação natalina compulsória prevista 
  na Lei nº 4.090/1962.
* **Rubrica:** Código ou categoria contábil que identifica a natureza de um valor na folha de pagamento (ex: Rubrica de Salário Base, Rubrica de Hora Extra).
* **Holerite / Contra-cheque:** O demonstrativo impresso ou digital que detalha todas as rubricas de proventos e descontos que compuseram o salário líquido do colaborador em uma competência.
* **Provento:** Qualquer valor financeiro de natureza creditícia que é somado ao salário do colaborador (ex: bônus, comissões).
- **Desconto:** Qualquer valor financeiro de natureza debitaria que é subtraído do salário do colaborador (ex: faltas, atrasos).

## 1.5 Estruturação do Documento de Requisitos 
Este documento segue o padrão IEEE 830 (Software Requirements Specification) adaptado ao contexto do projeto SFP-CLT. Está organizado em quatro seções principais e um índice, conforme descrito abaixo:

**Seção 1 — Introdução:** Apresenta o propósito do documento, o escopo do produto, o glossário de termos e acrônimos, as referências utilizadas e esta descrição estrutural.

**Seção 2 — Descrição Geral:** Descreve o sistema em alto nível: sua perspectiva de produto, principais funcionalidades, perfis de usuário, restrições gerais e premissas que condicionam o desenvolvimento.

**Seção 3 — Requisitos Específicos:** Núcleo do documento. Subdivide-se em:
- **3.1 Requisitos de Interface com o Usuário (GUI):** Define os padrões visuais e comportamentais de todas as telas do sistema, incluindo login, navegação, cadastros, lançamentos, processamento, holerite e histórico.
- **3.2 Requisitos Funcionais:** Especifica o comportamento funcional do sistema agrupado por módulo: cadastros, apuração de frequência, processamento de proventos e descontos, encargos e provisões, saídas e auditoria. Cada requisito é rastreável a uma regra de negócio (RN-XX).
- **3.3 Requisitos Não Funcionais:** Estabelece restrições de qualidade mensuráveis nas dimensões de desempenho, segurança, confiabilidade, usabilidade, disponibilidade, manutenibilidade, portabilidade e conformidade legal.
- **3.4 Requisitos de Dados:** Define o modelo relacional de dados, as regras de integridade referencial e as políticas de segurança e retenção de dados.

**Seção 4 — Apêndices:** Material de referência complementar que suporta a Seção 3, incluindo a tabela completa de rubricas padrão (4.1), os modelos de fórmulas de cálculo com exemplos numéricos (4.2), o glossário expandido (4.3), a matriz de rastreabilidade entre requisitos, módulos e telas (4.4) e a tabela consolidada de regras de negócio RN-01 a RN-15 (4.5).

**Seção 5 — Índice:** Lista estruturada de todas as seções e subseções do documento com suas respectivas numerações, para navegação rápida.

**Convenções adotadas neste documento:**
- Requisitos funcionais são identificados no formato **X.Y.Z.N**, onde X.Y.Z indica a seção e N é o número sequencial do requisito dentro dela.
- Regras de negócio são referenciadas pelo código **[RN-XX]** e detalhadas nos Apêndices (seção 4.5).
- Fórmulas de cálculo são identificadas pelo código **[F-XX]** e detalhadas na seção 4.2.
- Itens marcados com `*****` ou `VER` indicam pontos ainda em aberto, pendentes de decisão pela equipe.


