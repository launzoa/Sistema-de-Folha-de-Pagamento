# Documento de Requisitos
**Sistema de Folha de Pagamento (SFP)**
**Versão:** 2.0.0
**Nome: Igor Romero Nogueira e Luã do Carmo**

---

## 1. Introdução

Esta seção contextualiza o leitor sobre o propósito do documento de requisitos, quais as funcionalidades que entram no escopo do projeto e quais são desconsideradas, as premissas e limitações consideras, bem como um glossário que define alguns jargões adotados no decorrer do documento. 
### 1.1 Propósito do Documento

Este documento de requisitos constitui a especificação completa e definitiva dos requisitos de negócios para o Sistema de Folha de Pagamento (SFP). Ele serve como o contrato universal entre os idealizadores do produto, os gestores operacionais (Recursos Humanos e Contabilidade) e as equipes de construção técnica (Desenvolvedores do Sistema). Ele descreve estritamente **o que** o sistema fará pelas pessoas, **quais** problemas ele resolve e **como** as regras do mundo real (Leis Trabalhistas, políticas corporativas) impõem limites e condições à operação da plataforma.

### 1.2 Escopo do Produto

O Sistema de Folha de Pagamento atua como um facilitar e orquestrador das obrigações financeiras e trabalhistas de corporações regidas pela Consolidação das Leis do Trabalho (CLT). O SFP substitui o cálculo humano falho por uma automação rígida e transparente.

**Dentro do Escopo da Plataforma:**
*   A gestão vitalícia e histórica dos dados contratuais e demográficos dos trabalhadores a partir do começo de adoção do sistema.
*   A definição e a parametrização global de taxas governamentais, regras de descontos corporativos e pacotes de benefícios personalizados (Rubricas).
*   A operação fluida e mensal de apontamento de lançamentos (controle de faltas injustificadas, atrasos proporcionais, e abonos) e horas extras.
*   A execução financeira matemática da conversão de salário bruto para líquido, deduzindo progressivamente as alíquotas oficiais de INSS e bloqueando operações financeiras proibitivas (como gerar dívida/salário negativo ao trabalhador).
*   A proteção patrimonial da empresa através de auditoria imutável e fechamentos de folhas que congelam o passado de forma irrefutável para defesa jurídica.
*   A geração dos documentos oficiais de comprovação, destacando-se o contracheque legal (Holerite) formatado e projetado para validade documental digital.  

**Fora do Escopo:**
*   Pagamento e emissão de notas para profissionais autônomos (Pessoa Jurídica), Freelancers, Estagiários e Menores Aprendizes.
*   Sistemas de medição física de entrada/saída (hardware de relógio de ponto eletrônico físico por biometria).
*   Repasse direto de valores interbancários automatizados (integração eletrônica automática de contas correntes); a ferramenta fornece os valores a serem depositados, mas não movimenta as contas dos bancos ativamente.
*   Cálculo de eventos de longo prazo como o pagamento de fim de ano, regido pelo 13º salário, e férias acumulativas. 
*   Validação de faltas ou atrasos pela apresentação de atestados ou abonos médicos.
*   Barreiras de bloqueio (Login e Senha inquebráveis por terceiros, bem como senhas criptografadas) ditadas pela Lei Geral de Proteção de Dados (LGPD - Lei nº 13.709/2018), onde o sistema é categorizado por manipulador "Dados Sensíveis".

### 1.3 Glossário e Definições de Negócio

Para garantir que os interessados no sistema (Diretores, Advogados ou Operadores de RH) interpretem os requisitos da mesma forma, são apresentadas as seguintes definições de jargões e termos usados no decorrer deste documento:

*   **Abono Médico:** A neutralização contábil de uma falta ou punição de frequência devido à entrega e registro de atestado médico com validade legal.
*   **Auditoria (Log):** O recurso interno de governança corporativa que grava quem, quando e qual dado exato foi alterado por um usuário na plataforma, usado exclusivamente para averiguação de fraudes ou erros.
*   **Base de Cálculo:** O somatório parcial de dinheiro sobre o qual o governo ou a empresa aplica uma porcentagem de imposto. (Ex: O INSS não recai sobre tudo que o funcionário ganha, mas apenas sobre as verbas listadas como "Base de Cálculo do INSS").
*   **Competência (Mês de Competência):** O mês e o ano aos quais um determinado trabalho, atraso ou imposto se referem. Por exemplo, horas trabalhadas em outubro geram a folha de competência "10/2026".
*   **CLT:** Consolidação das Leis do Trabalho. O conjunto legal de regras brasileiras para as relações de emprego formais.
*   **DSR (Descanso Semanal Remunerado):** Direito legal a um dia de folga paga na semana (geralmente domingo). A perda desse direito funciona como penalização financeira gravíssima imposta a quem falta sem justificativa durante a semana.
*   **Folha Aberta vs Folha Fechada:** 
    *   *Aberta:* Estado temporário onde rascunhos, testes e modificações de faltas, prêmios ou demissões podem ser reajustados à vontade pelo operador para simulação.
    *   *Fechada:* Estado trancado, assinado e definitivo. O sistema proíbe universalmente que dados daquele mês sejam retocados, apagados ou disfarçados posteriormente.
*   **Holerite:** O comprovante, contracheque ou recibo de pagamento salarial que lista nominalmente cada ganho e cada perda que levaram ao valor transferido para a conta bancária do indivíduo.
*   **Provento e Desconto:** 
    *   *Provento:* Todo e qualquer centavo ou valor adicionado ao bolo financeiro a favor do funcionário (dinheiro que entra).
    *   *Desconto:* Qualquer centavo retido do bolo, seja pela empresa (por multas ou benefícios com coparticipação) ou imposto pelo governo (dinheiro que sai).
*   **Rubrica:** O nome contábil para o "tipo de movimentação". Ao invés de o sistema registrar apenas "+R$100,00", ele usa uma "Rubrica", que é uma etiqueta padronizada informando "Aquele valor de R$100 refere-se à Rubrica de Adicional Noturno".

### 1.4 Premissas e Limitações Estratégicas

*   **Conformidade Territorial:** Todas as operações matemáticas e de arredondamento seguem estritamente a unidade monetária corrente no Brasil (Reais - BRL), usando precisão rigorosa de duas casas decimais com arredondamento contábil justo.
*   **Isolamento Tecnológico:** A ferramenta funcionará sem precisar conectar aos computadores ou redes diretas do Ministério do Trabalho; toda a inteligência está contida e embutida na regra da aplicação instalada, garantindo uso mesmo em redes internas isoladas das companhias.

---

## 2. Descrição Geral

Esta seção destrincha e contextualiza o leitor sobre o formato da solução, o catálogo das funções essenciais que serão entregues, bem como as restrições e as premissas operacionais sob as quais o Sistema de Folha de Pagamento (SFP) operará no mundo real corporativo. Toda a descrição foca inteiramente no impacto administrativo, na redução do custo operacional, na eliminação do risco jurídico, mantendo o processo contábil aderente e imune a interpretações ambíguas.

### 2.1 Perspectiva e Enquadramento do Produto

O Sistema de Folha de Pagamento (SFP) nasce como uma plataforma isolada, independente e autossuficiente (standalone), voltada exclusivamente para as demandas internas dos Departamentos de Recursos Humanos (RH) e das controladorias contábeis empresariais.

*   **Arquitetura Física Organizacional:** A aplicação foi projetada para atuar em computadores corporativos isolados. Isso significa que o software reside e "vive" diretamente dentro da máquina de mesa ou laptop do Analista de RH, não exigindo acesso à internet global para funcionar. Ele conecta-se unicamente ao arquivo central da empresa (o banco de dados corporativo), que pode estar hospedado localmente no próprio prédio da corporação, isolado da vulnerabilidade da grande rede. Isso blinda o processo contábil contra ataques externos massivos providos pelo ecossistema da Internet.
*   **Posicionamento no Ecossistema da Empresa:** O SFP não é um aplicativo voltado ao funcionário comum; não existe "portal do colaborador" para checar holerites pelo celular. O produto é uma "Mesa de Operações" de alta segurança, de uso estritamente gerencial e restrito aos profissionais que calculam e liberam o dinheiro da companhia. Ele substitui a desordem das planilhas amadoras por um único ponto de verdade visual e centralizado, focado na ergonomia do operador que passará horas processando cálculos extensos sob pressão no fim de cada mês civil.

### 2.2 Funcionalidades do Produto

A ferramenta consolida um catálogo completo e orquestrado de funções automatizadas que eliminam a digitação dupla e a dependência de calculadoras paralelas. O sistema entregará, fundamentalmente:

1.  **Cálculo Automático:** O processamento confiável e matemático da conversão do Salário Bruto em Salário Líquido. O sistema processará, o desconto em cascata de tributos (como o INSS com suas faixas governamentais progressivas) sem a necessidade de um operador buscar tabelas na internet.
2.  **Governança de Exceções Mensais:** A centralização do registro diário ou mensal do imprevisível. O sistema capturará e converterá em dinheiro ocorrências como as Horas Extras cumpridas, a perda punitiva originária de Faltas injustificadas, as horas perdidas nos Atrasos.
3.  **Provisões Trabalhistas:** O cálculo contínuo dos deveres fiscais e dos direitos dos empregados contidos delimitados pelo escopo. 
4.  **Emissão Documental e Holerites Oficiais:** A emissão de documentos jurídicos de forma eletrônica ou para impressão. Isso abrange a compilação final que emite individualmente o Holerite formal, destrinchando transparentemente todas as contas do processamento como um arquivo PDF inviolável padrão, para proteção judicial em potenciais litígios futuros.
5.  **Auditoria Inviolável:** Uma auditoria de gravação em segundo plano. O log do SFP absorverá todas as atitudes passíveis de risco legal ou desvios efetuadas na plataforma de forma silenciosa e permanente (ex: quando um Analista aplicar um desconto de 500 reais na conta de um par de equipe; a plataforma manterá um registro da ação sem a possibilidade de negação plausível).

### 2.3 Características Comportamentais dos Usuários e Taxonomia

Para o planejamento estrutural do uso diário, os usuários do SFP foram mapeados em dois arquétipos distintos com funções independentes na organização. O acesso ao sistema obedece fielmente à identidade profissional definida para cada um.

**Analista de RH / Auxiliar de Departamento Pessoal:**
*   **Identidade e Rotina:** O usuário principal da plataforma. Profissional com conhecimento avançado,  teórico e prático sobre a Consolidação das Leis do Trabalho (CLT), acordos de sindicato (CCT) e fechamento de horas.
*   **A Dor que o SFP resolve para ele:** Busca agilidade e alívio do estresse cognitivo de checar cálculos manuais em calculadoras paralelas, bem como o tempo desperdiçado manualmente na elaboração visual do Holerite. Quer a confiança de que o sistema aplicará o desconto certo se ele realizar apenas o lançamento daquele evento (ex: o funcionário faltou 2 dias).
*   **Comportamento na Plataforma:** Focado estritamente na digitação de dados e na navegação através de listas de funcionários, rubricas e lançamentos.

**O Administrador do Sistema:**
*   **Identidade e Rotina:** O usuário executivo e de confiança corporativa. Responsável legal ou representante direto contábil da corporação que detém a responsabilidade de manter os padrões normativos para os dados da empresa, bem como dos RH e fiscalizar possíveis desvios pelo registro de auditoria.
*   **A Dor que o SFP resolve para ele:** O medo de fraudes internas entre os funcionários e de lançamentos indevidos não autorizados; o cadastro confiável de novos usuários no sistema; a alteração de dados informativos sobre a empresa.
*   **Comportamento na Plataforma:** Focado estritamente em necessidades finas. Ele passará a maior parte do seu tempo auditorando as mudanças que ocorrem no sistema, providas pelas interações entre os usuários e o sistema, utilizando ativamente a tela de Auditoria. Além disso, terá o controle dos cadastros de usuários no sistema e das alterações dos dados cadastrados da empresa. 

### 2.4 Restrições Globais e Mandatórias de Negócio

As regras de contorno que impedem que o software seja moldado de forma não compatível com o ambiente jurídico corporativo. Essas regras são limites que ditaram o planejamento lógico da solução:

*   **Legislação Vigente:** O sistema deve curvar-se estritamente à modelagem tributária nacional prevista na Lei maior aplicável (Consolidação das Leis do Trabalho) atualizada pelas Portarias vigentes dos anos atuantes de (2025/2026). Decisões de cortes trabalhistas regionais ou sindicatos menores que firam as raízes federais das alíquotas obrigatórias federais não devem corromper a matriz lógica da base de cálculo das plataformas sem uma intervenção ativa e manual humana em regras "livres".
*   **Imutabilidade Contábil (Conformidade Pós-Fechamento):** Após o ato gerencial e sistema que decreta que um mês temporal está "Fechado", a plataforma rejeita mecanicamente, sob qualquer circunstância alegada pelo operador (independente do pretexto) a reedição, alteração ou exclusão arbitrária de qualquer dado, prêmio, ou cálculo presente daquele ciclo temporal extinto do passado corporativo. A integridade de um mês repassado financeiramente a um banco real deve ser inquestionável do momento de trancamento histórico pra frente da empresa.
*   **Independência Arquitetônica:** Para garantir o funcionamento em redes estatais ou corporativas lentas em áreas industriais sem internet; a aplicação não operará dentro de um "site de internet", tampouco baseará seus esforços dependendo de uma página web. O sistema é "inserido" como uma fundação local corporativa estável em linguagem Java (exigindo o mínimo de uma base madura computacional) rodando os seus mecanismos de forma autossuficiente sem precisar de recursos remotos à uma rede de internet exterior.

### 2.5 Suposições de Cenário e Dependências Corporativas Prévias

Premissas adotadas que precisam existir no mundo físico para que as promessas da plataforma tomem a forma desejada pelos planejadores.

*   **Estabilidade Local do Ambiente Organizacional e Local:** Supõe-se, como regra irrevogável do cenário estipulado no ambiente local da companhia implementadora do SFP; que a fiação e o roteamento interno comunicante entre os computadores dos eventuais Analistas (usuários) logados interligando-os ao Servidor Central (o local que arquiva as pastas bancárias) tenham fluxo estável sem apagões intermitentes contínuos; assim como é esperado que qualquer tipo de quedas brutais da conectividade por meio da falta de energia sejam devidamente evitadas.
*   **Exigência de Poder Computacional Mínima:** Espera-se irrevogavelmente e de antemão por premissas gerenciais base financeiras que qualquer um dos Analistas logados à interface opere através de uma estação computacional fisicamente confiável e minimamente potente em hardwares locais da década atual exigente (2026) para não experimentar engasgos ou sofrimentos nos devidos cálculos realizados pelo processamento das folhas de pagamento calculadas automaticamente.
*   **A Engrenagem da Disciplina Funcional Humana Contábil Mensal Prévia:** Como dependência final para o sucesso corporativo financeiro dos esperados cálculos, bem como do encerramento automatizado e matemático mensal da folha, operar do modo devido e sem catástrofes; baseia-se na premissa e suposição corporativa operacional que o RH não deixará meses brancos de frequência não justificada antes do trancamento das folhas finais, pois o sistema é inerte, e é categoricamente dependente do fator primário humano alimentando os lançamentos temporais da rubricas, imputações e exclusões corporativas de rubricas antes do fechamento e bloqueio da folha de pagamento do mês; bem como as rubricas justificadas (ex: faltosos prévios ou faltas médicas) devem ser validadas de forma externa ao sistema, preferencialmente por um departamento de RH, e posteriormente considerado ou desconsiderado como um lançamento (dentro do escopo do sistema). Sem trabalho humano de alimentação e lançamento de exceções, o sistema financeiro do SFP falha e para.

---

## 3. Requisitos Específicos do Sistema 

Esta seção detalha o comportamento estrito e exaustivo da plataforma, mapeando cada obrigação mínima que o sistema precisa cumprir perante o cenário empresarial. A categorização segue a modelagem padrão do framework FURPS+ de Craig Larman, dissecando as interações em Funcionalidade, Usabilidade, Confiabilidade, Desempenho, Suportabilidade e Restrições Físicas.

### 3.1 Requisitos de Funcionalidade 

As capacidades primordiais da plataforma. Abordam os fluxos de trabalho centrais gerenciais e operacionais para a execução correta da legislação trabalhista e controle interno do caixa.

#### 3.1.1 Módulo de Segurança de Acesso, Autenticação e Perfis (Governança)

* **3.1.1.1 [E]** — O sistema deve exigir, imperativamente, a apresentação de credenciais exclusivas válidas (Usuário e Senha) na tela de inicialização, blindando o acesso e a visualização de qualquer dashboard, dado salarial ou informação corporativa antes da verificação positiva.
* **3.1.1.2 [E]** — O sistema deve realizar o mascaramento inquebrável das senhas inseridas no campo de login, convertendo os caracteres em asteriscos visuais. O sistema também impedirá operações de teclado voltadas à cópia (Ctrl+C) sobre este campo, mitigando o risco de captura de senhas.
* **3.1.1.3 [E]** — O sistema deve assegurar a segregação rigorosa de poderes: Contas de perfil "Operador" terão seus menus visuais de configuração estrutural da empresa (Dados pessoais, Log de Auditoria Global, Gerenciamento de Contas de Acesso) não apenas desabilitados, mas integralmente ocultos de seu campo de visão de navegação da interface.
* **3.1.1.4 [E]** — O sistema deve oferecer um mecanismo claro e explícito de "Desconectar" (Logoff/Sair), posicionado no topo da estrutura de navegação, permitindo que a finalização humana da rotina diária seja correspondida pelo fechamento voluntário do terminal.
* **3.1.1.5 [O]** — O sistema deve garantir a Rastreabilidade de Acesso (Auditoria de Sessão), injetando silenciosamente um registro perpétuo e inalterável no Log de Auditoria no exato milissegundo em que um usuário realiza um "Login" com sucesso e no momento em que invoca o "Logout" do sistema.
* **3.1.1.6 [O]** — O sistema deve aplicar o conceito de Desativação Lógica (Soft-Delete) para o encerramento do ciclo de vida das credenciais de acesso corporativo. Contas de usuários não podem ser deletadas do banco de dados (para preservar a integridade histórica da auditoria), devendo apenas ter o seu "Status" alterado para Inativo, o que bloqueará preventivamente qualquer nova tentativa de login na plataforma.
* **3.1.1.7 [D]** — A governança visual da aplicação deve ser controlada de maneira global, disponibilizando um alternador de Tema (Claro / Modo Escuro - Dark Mode) para mitigar a fadiga visual dos Operadores e Administradores de RH durante jornadas contínuas de análise de dados.

#### 3.1.2 Módulo de Configuração e Políticas DA Empresa Padrão

* **3.1.2.1 [O]** — O sistema deve limitar e obrigar a execução sob o escopo único de um único cadastro patronal ("Empresa"). O SFP rejeitará processamentos de holerites para outras matrizes misturadas, se não à cadastrada sob bases de dados únicas.
* **3.1.2.2 [E]** — O sistema deve submeter o cadastro da Razão Social, o Responsável Legal, o Email e o Cadastro Nacional da Pessoa Jurídica (CNPJ) como campos inegociáveis. 
* **3.1.2.3 [O]** — O sistema deve reter centralmente a parametrização nacional de tributos de forma implícita (excluída de Administradores e Operadores), definindo a Alíquota Fixa e invariável do Fundo de Garantia (FGTS) como indexador de 8% fixo global da empresa. Não é possível modificar esse índice manualmente pela usuário, já que alterará o fluxo contábil da empresa por completo.
* **3.1.2.4 [O]** — O sistema deve manter de forma fixa a parametrização da quantidade de dias para a jornada constitucional trabalhista do empregador (Quantidade de Dias Uteis de Trabalho Mensal). Tal número (tradicionalmente 22 dias) é o denominado de forma fixada e implícita para os cálculos fracionários de salário proporcional em razão do valor diário do trabalhador.
* **3.1.2.5 [O]** — O sistema deve manter de forma fixa a parametrização da jornada constitucional de base horária trabalhista do empregador (Carga Horária Mensal Padrão). Tal número (tradicionalmente 220 horas para celetistas) será o denominado de forma fixada e implícita para cálculos fracionários de descontos ou prêmios monetários.
* **3.1.2.6 [O]** — O sistema deve permitir a parametrização de valores monetários absolutos universais padronizados da corporação, tais como o teto de "Benefício Fixo de Vale-Refeição/Cesta Básica", garantindo que a inserção de novos colaboradores já seja abastecida com essas condições de base preestabelecidas.
* **3.1.2.7 [D]** — O sistema deve implementar o suporte corporativo para Múltiplas Filiais Físicas (Endereçamento Múltiplo), permitindo que a mesma entidade patronal (CNPJ Matriz) mantenha e cadastre uma lista em formato cascata contendo vários endereços legais válidos (CEP, Logradouro, Bairro, Complemento).
* **3.1.2.8 [E]** — O sistema deve forçar a padronização temporal da jornada de trabalho ao predefinir estritamente um "Dia de Fechamento do Ponto" mensal da companhia (Opções restritas: 1, 15, 20, 25 ou 30), alinhando o bloqueio da auditoria temporal de faltas e horas extras com a exatidão financeira exigida pelo motor da folha.

#### 3.1.3 Módulo de Capital Humano e Cadastro

* **3.1.3.1 [E]** — O sistema deve apresentar, em um dashboard visual central, o mapeamento gerencial sumariado da ocupação física da firma: Quantos indivíduos estão com o status vigente (Ativos).
* **3.1.3.2 [O]** — O sistema deve condicionar toda Admissão à existência formal e válida de um Cadastro de Pessoas Físicas (CPF). Este número é a chave inquestionável da plataforma; não existem admissões com CPFs provisórios.
* **3.1.3.3 [O]** — O sistema deve varrer o arquivo histórico durante uma nova admissão. Se o CPF preenchido coincidir rigorosamente com um CPF já constante no passado corporativo daquela firma (seja de um trabalhador de hoje ou um desligado em 2014), o sistema abortará a inclusão e alertará sobre a tentativa de "Clonagem Funcional/CPF Duplicado".
* **3.1.3.4 [O]** — O sistema deve condicionar a admissão à obediência financeira da nação: É terminantemente bloqueada a consolidação de um "Salário Base Contratual" cujo valor em Reais (R$) se prove matematicamente inferior ao "Piso do Salário Mínimo Vigente" (R\$ 1621,00 para o ano ano vigente de 2026) do ano.
* **3.1.3.5 [O]** — O sistema deve eliminar o conceito computacional de "exclusão de pessoas" para dados da vida real atrelados a valores e histórico corporativo. O fim de um ciclo de trabalho humano não acarretará na deleção, mas acionará uma mudança flag de estado civil ("Inativado/Demitido"). Um perfil inativo paralisa a matemática dos lançamentos a partir daquele dia em diante na folha de pagamento, mas todo seu legado imutável continuará operando nas prestações de anos passados e no repositório final de PDF's emitidos da empresa.
* **3.1.3.6 [E]** — O sistema deve exigir obrigatoriamente a identificação civil nominal completa (Nome) do indivíduo no ato do seu cadastro admissional.
* **3.1.3.7 [E]** — O sistema deve capturar e registrar o Cargo/Função ocupada pelo colaborador na arquitetura hierárquica da organização no momento da sua admissão, refletindo isso em todos os holerites futuros.
* **3.1.3.8 [E]** — O sistema deve exigir a definição cronológica exata de quando o vínculo de trabalho foi sacramentado (Data de Admissão), registrando no histórico a temporalidade inicial do colaborador.
* **3.1.3.9 [E]** — O sistema deve requerer a quantidade exata de Número de Dependentes Legais do colaborador logo na ficha de admissão, uma vez que esta métrica tem peso decisivo na fórmula federal de abatimento de impostos na fonte (IRRF).


#### 3.1.4 Módulo Contábil de Rubricas e Incidências

* **3.1.4.1 [O]** — O sistema deve trazer as regras e "Rubricas do Brasil" blindadas previamente na classe inicial numérica (Geralmente IDs 001 até 005), representando as obrigações constitucionais como "Salário Padrão,  Horas Extras 50%, Horas Extras 100%, Atraso por Hora e Falta por Dia". O usuário estará proibido mecanicamente de excluí-las, alterá-las ou de removerem delas a cobrança tributária estipulada pela base de leis estatais do Ministério da Economia. 
* **3.1.4.2 [O]** — O sistema deve trazer as regras e Parâmetros Legais Federativos inseridas previamente na classe inicial numérica (Geralmente IDs 100 até 102), representando as obrigações constitucionais da empresa com o trabalhador como "Desconto do INSS, Desconto do IRRF e FGTS". O usuário estará proibido mecanicamente de excluí-las, alterá-las ou de removerem delas a cobrança tributária estipulada pela base de leis estatais do Ministério da Economia. 
* **3.1.4.3 [E]** — O sistema deve garantir a livre arquitetura do balanço contábil permitindo a construção criativa de novas categorias pelo gestor (Ex: Rubrica 6 - Participação nos Lucros e Resultados). Todo benefício ou taxa customizada exigirá a imputação forçada de uma Natureza Dupla: Ela injeta Dinheiro Positivo ao cálculo de holerites líquidos (Vencimento/Provento) ou subtrai capital da mão do obreiro a favor do caixa corporativo (Desconto), assim como o Tipo (Fixo ou Variável), bem como informações de possível incidência da nova rubrica para com os parâmetros legais (INSS, IRRF) e o Status atual da rubrica (Ativo ou Inativo).
* **3.1.4.4 [E]** — O sistema deve fornecer para as Rubricas personalizadas um quadro de incidência flexível manipulado para o usuário. Esse quadro exige decisões exatas de tributação: Esta verba integra o montante onde o governo debita o INSS mensalmente? Essa verba engorda o cesto base do imposto de Renda futuro (IRRF)? O sistema recalculará toda a matemática universal do SFP perante qualquer preenchimento alterado nesse quadro em específico e não exitará em agir conforme determinado pelo usuário daquele momento.
* **3.1.4.5 [O]** — O sistema deve trazer as regras de Rubricas de Verbas Indenizatórias (PAT ou VAT) inseridas previamente na classe inicial numérica (Geralmente IDs de 901 e 902), representando as obrigações constitucionais como "Programa de Alimentação Trabalhista e Vale Transporte". O usuário estará proibido mecanicamente de excluí-las, alterá-las ou de removerem delas a cobrança tributária estipulada pela base de leis estatais do Ministério da Economia. 
* **3.1.4.6 [E]** — O sistema deve fornecer uma interface de edição independente para os "Parâmetros Legais" tributários do Governo (Tabelas do INSS e IRRF), de forma que o Gestor de RH tenha plena autonomia para atualizar anualmente as Alíquotas, Pisos, Tetos e Parcelas a Deduzir das faixas tributárias diretamente no banco de dados, acompanhando as diretrizes do Ministério da Economia sem precisar alterar o código-fonte ou a estrutura das rubricas bases.

#### 3.1.5 Módulo de Apuração De Lançamento de Exceções Mensais 

* **3.1.5.1 [E]** — O sistema deve possuir uma tela central orientada a lotes de dados operacionais (Lançamentos de Exceções) para abrigar a digitação mensal do imprevisível. A interface suportará um motor matemático flexível dividindo as entradas em três modalidades operacionais: Valor Fixo Absoluto, Fator de Quantidade (Horas/Dias) e Fator de Porcentagem (sobre Salário Bruto, Salário Líquido ou uma Base de Cálculo Livre definida manualmente pelo RH).
* **3.1.5.2 [O]** — O sistema deve processar exceções diárias de maneira automática, captando o tempo em "Horas" ou "Dias", calculando o valor dinamicamente em função da divisão do Salário Bruto do trabalhador pela carga horária mensal (ex: 220h) ou pelos dias úteis do mês estipulados pela Empresa.
* **3.1.5.3 [E]** — O sistema deve suportar a aplicação da "Penalização do Repouso Perdido": Ao selecionar uma rubrica de Falta para o colaborador, o sistema habilitará um controle opcional na tela (Checkbox) para que o Analista de RH decida, de forma não-compulsória, se deseja estender a punição descontando também os valores do DSR (Descanso Semanal Remunerado) daquela respectiva semana (gerando um lançamento avulso automático).
* **3.1.5.4 [O]** — O sistema deve computar trabalhos de esforço extra laborados. O operador reportará montantes excedentes fracionados ("12 horas e meia") classificadas sobre o prêmio padrão constitucional de sobreaviso ou exaustão: o multiplicador buscará automaticamente na nomenclatura da Rubrica o seu fator percentual (ex: Hora Extra 50% injeta multiplicador matemático de 1.50 na taxa base horária de forma dinâmica).

#### 3.1.6 Módulo Núcleo Matemático de Tributação Progressiva e Apuração Automática

* **3.1.6.1 [O]** — O sistema deve aplicar o "Cálculo Pro-Rata": Funcionários admitidos no decorrer da competência vigente terão seus salários base fragmentados perfeitamente. O algoritmo aplicará a régua comercial da CLT (Mês de 30 dias), multiplicando o valor fracionário diário pelos exatos dias trabalhados entre a admissão e o fechamento do ciclo.
* **3.1.6.2 [O]** — O sistema deve agregar o Bolo de Lucros (Base Inicial): Unirá e acumulará em base universal tudo estipulado fixamente à pessoa (após o Pro-Rata) mais os proventos digitados e acumulados em eventos no mês. Esse saldo consolidado bruto atuará como isca tributária global pro cálculo central final.
* **3.1.6.3 [O]** — O sistema deve efetuar e aplicar a Tributação Federal Previdenciária (INSS). O sistema processará a exatidão tributária através do algoritmo simplificado de "Parcela a Deduzir" para encontrar o valor correspondente fatiado sem necessitar percorrer o laço de escadaria regressiva das sobras, alcançando o mesmo valor legal.
* **3.1.6.4 [O]** — O sistema deve efetuar e aplicar a Retenção na Fonte do Imposto de Renda (IRRF). Utilizará as faixas do Governo e deduzirá a cota legal estipulada para o Número de Dependentes registrados na ficha admissional do colaborador, subtraindo primeiro o INSS da base tributável para não haver bitributação inconstitucional.
* **3.1.6.5 [O]** — O sistema deve acionar o Freio de Dívida Social Operacional ("Teto Limite Zero"). Caso o volume de descontos imputados ultrapasse os ganhos, o motor bloqueará a geração de saldo negativo e travará o líquido a receber em exatos R$ 0,00. As dívidas residuais não serão perdoadas; elas ficarão registradas no documento oficial (Holerite) como "Dívida a Abater Próx. Mês" para transparência contábil e auditoria financeira do RH.

#### 3.1.7 Módulo de Governança do Fechamento Contábil

* **3.1.7.1 [E]** — O sistema deve abrigar e exportar o Custo Base FGTS em relatórios consolidados finais gerenciais para o departamento financeiro emitir guias unificadas da arrecadação federal Caixa sem precisarem de planilhas extras fora da arquitetura.
* **3.1.7.2 [O]** — O sistema deve viabilizar a "Trava de Arquivamento Inviolável" de forma automatizada. Ao invés da dependência volátil humana, o próprio motor silencioso temporal transitará de forma algorítmica o status de um "Mês/Ano Competência" para "Fechada" pelo sistema após exatos cinco dias corridos de sua data limite original de apuração, lacrando o ciclo.
* **3.1.7.3 [O]** — O sistema deve defender a Trava de Arquivamento: Nenhuma ocorrência temporal inserida por descuido do dia trancado para trás deverá afetar um centavo do passado. Um salário reajustado em Março da empresa não terá poder de desfazer os holerites gerados nos repasses dos fechamentos já lacrados de janeiro anterior e fevereiro contábil transcorrido pela equipe do RH e pagos perante o banco comercial. É uma muralha temporal contra amadorismo temporal ou passivos ocultos das rescisões judiciais alteradas à força bruta humana por retaliação perante fiscais estatais processuais fiscais de tribunais sindicais.

#### 3.1.8 Módulo de Emissão de Holerites 

* **3.1.8.1 [E]** — O sistema deve exportar de forma vetorizada, profissional e formatada, o Recibo Estendido Documental Trabalhista Final assinado por via das telas: O "Holerite".
* **3.1.8.2 [E]** — O sistema deve desenhar o Holerite com o cabeçalho base informando o nome completo do contratante da praça (A Empresa com seu CNPJ fiscal contábil), e em seção dedicada logo abaixo, a identidade física demográfica admissional do favorecido em si (Nome exato do sujeito, CPF e Cargo ocupado).
* **3.1.8.3 [E]** — O sistema deve diagramar o Holerite organizando o miolo descritivo principal dividindo os Proventos injetados e listando as alíquotas com as bases onde se incidiram as leis previdenciárias estatais federais.
* **3.1.8.4 [E]** — O sistema deve incluir uma declaração sumária afixada no rodapé do documento impressso informando o "FGTS do Mês" e sua "Base de Cálculo", evidenciando os depósitos do Fundo de Garantia ao funcionário de forma indissociável.

#### 3.1.9 Módulo de Auditoria e o Histórico Universal

* **3.1.9.1 [E]** — O sistema deve abrigar uma câmara fechada ("Caixa Preta do Rh" ou "Auditoria Operacional"). Não importará a bondade ou malícia de uma atitude em tela; a manipulação financeira na base (alteração salarial e aprovação do trancamento de meses) gerarão marcas cronológicas.
* **3.1.9.2 [O]** — O sistema deve registrar o nome carimbado perante a rede exato do "Usuário Analista" ativo logado sentado àquela estação, associando a ele os metadados macroscópicos da ação executada (ex: apontar que ocorreu um evento genérico do tipo "Edição" sobre a entidade "Funcionário" vinculada a um CPF específico). A plataforma abre mão de espelhar as variações intrínsecas dos campos e salários para focar na temporalidade global da autoria.
* **3.1.9.3 [O]** — O sistema deve enrijecer o acesso da Caixa Preta; a Auditoria não exibe botões de "Deleção", "Passar Borracha" ou "Lixeira Temporal". É uma matriz baseada fundamentalmente e tecnicamente no fluxo "Append-Only" contínuo vitalício (Apenas escuta contínua de fatos inseridos nas bases no fim da fita magnética).
* **3.1.9.4 [E]** — O sistema deve abrigar o Túnel do Viajante Contábil. Gestores podem regressar à competência trancada passada de exatos cinco anos (ex: Out/2021) e re-extrair a massa de PDFs de holerites base de funcionários inativos há lustros completos demitidos daquelas competências estáticas idênticas preservadas imunes perante mudanças legais contemporâneas vigentes ocorridas anos à frente daquela janela em si, favorecendo advocacia contábil extrema das varas.

### 3.2 Requisitos de Usabilidade (Usability)

Fatores atrelados à ergonomia visual profunda e à mitigação de catástrofes manuais provocadas pela fadiga ocupacional nas rotinas administrativas massivas da equipe.

* **3.2.1.1 [E]** — O sistema deve automatizar a inteligência da digitação das rotinas densas contábeis; a inserção crua repetitiva numeral deve auto converter o alinhamento com pontos nos CPFs contínuos, trançar números no documento corporativo estendido matriz federal contínuo em CNPJs sem esforço braçal auxiliar ("000" para "000.") e defender visualmente a quebra do centavo fracionado final sempre através da base indicadora de moeda brasileira contínua global formatada por uma vírgula exata sem uso falho crônico do ponto base decimal alienígena estrangeiro comum em interfaces obsoletas rústicas sem padronizações no Brasil corporativo comercial geral.
* **3.2.1.2 [E]** — O sistema deve comunicar através do viés das "Sinalizações Semafóricas de Restrição Legal"; campos cadastrais essenciais pra admissão ou geração financeira contínua de guias obrigatórias possuirão ícones ou indicações avermelhadas persistentes no visor, engessando qualquer salvamento forçado apressado e acusando exatamente quais laudas restam vazias no preenchimento falho dos novatos apressados logados sem supervisão direta no departamento naquelas longas exaustões dos turnos laborais civis perante fim do mês temporal na RH.
* **3.2.1.3 [E]** — O sistema deve mitigar a cultura e erro base da colisão catastrófica acidental perante cliques compulsivos ("Double-click habits"). Uma barreira imperativa e visual intrusiva central ("Deseja definitivamente irreversivelmente aplicar essa penalidade financeira no alvo ou congelar toda uma massa corporativa do mês para sempre e vitalício? - CONFIRMAR/CANCELAR") deve surgir saltando do eixo, impedindo que o nervosismo custe falhas irreparáveis irreparáveis contábeis a empresa implementadora base ali do fato na execução do painel rotineiro apressado.
* **3.2.1.4 [E]** — O sistema deve abrigar, permanentemente afixado no cume central orientador ininterrupto sem ser tampado das interfaces perante os menus e abas e formulários exaustivos do fluxo do uso diário estafante de ocorrências; um farol ou placa luminosa global resumindo sem dúvidas o destino ou a fenda temporal que a pessoa atual operadora se afunda na rotina de lançamento hoje; exclamando abertamente o Status Matriz Global Geral "COMPETÊNCIA VIGENTE AGORA 12/2026 - MÊS ABERTO AO EDITAL DE MODIFICAÇÕES FINAIS APROVADAS" baseando-se a tranquilidade.
* **3.2.1.5 [E]** — O sistema deve tratar todos os comunicados gerados através da falha computacional e lógicas sob linguajar leigo e compassivo, estirpando da face corporativa códigos arcanos ilegíveis ("Exceção fatal nula nas entidades 04 da JVM do Hibernate"); traduzindo o desvio em conselhos: "As informações bancárias digitadas encontram-se fora de um parâmetro aceito comercialmente. Refaça a checagem no formulário."  

### 3.3 Requisitos de Confiabilidade (Reliability)

Mecanismos enraizados no algoritmo para barrar fraudes corporativas internas baseadas no apodrecimento moral das regras de negócio do dinheiro real sob pressões e conchavos e o combate base à obsolescência dos fatos gravados passados inquestionáveis por justiça estatal.

* **3.3.1.1 [O]** — O sistema deve consolidar o Pacto do Passado Cristalizado inabalável nas execuções globais financeiras de prestação de verbas civis; Nenhuma medida ou sentença de recomposição de verbas que adicione aumentos num contrato feito ativamente na janela corrente operada da "Folha de hoje mês tal" refletirá, espalhará ou arruinará de modo fantasmagórico o saldo retroativo depositado consolidado fiscal legal lacrado guardado selado das inúmeras folhas trancadas guardadas com códigos bases das vidas extintas guardadas passadas temporais inquestionáveis.
* **3.3.1.2 [O]** — O sistema deve amparar a indestrutibilidade base legal nas atas das edições. Como não há lixeira, exclusões limpas perfeitas intocáveis contínuas em atitudes nefastas nas alterações financeiras efetuadas; o sistema assume um posicionamento jurídico vital contábil impositivo para os gestores finais: Não existindo como varrer lixo financeiro por debaixo de tapetes logísticos de TI, os donos ganham provas cabais documentais imaculadas plenas em juízos judiciais da comarca fiscal do Brasil contra o funcionário da base financeira estipulada em roubos de dinheiro ou reajustes combinados maliciosos contínuos.

### 3.4 Requisitos de Desempenho (Performance)
Expectativas relativas à capacidade tangível perante carga exaustiva pesada massiva bruta laboratorial base de tempo contínuo contábil nos fechamentos de prazos estrangulados apertados da equipe.

* **3.4.1.1 [O]** — O sistema deve gerenciar a varredura base dos cadastros operantes dos talentos corporativos contratados através de técnicas da leitura interna paginada imediata rápida e listagens tabulares precisas sem exaurir recursos primários computadores básicos base estipulados e previstos de uso pela firma sem causar paralisia total ("travamentos gerais das janelas de Java") das telas locais do trabalhador perante bases com 1.500 trabalhadores ativos ao longo de anos extensos corporativos baseados globais ali arquivados.
* **3.4.1.2 [O]** — O sistema deve reter na fundação da arquitetura isolada toda a engrenagem matemática complexa abstrata bruta contábil necessária para triturar valores e devolver cálculos exatos líquidos federais para holerites independentemente estritamente sem se curvar para consultar internet contínua mundial de fibra-ótica paralela; o cérebro processador está residente ali local, garantindo execução ininterrupta de velocidade mesmo que galpões base percam acesso e torres de sinais telefônicas globais contínuas parem nas bases matrizes isoladas distantes em fábricas industriais regionais pesadas de montadoras afastadas sem redes.

### 3.5 Requisitos de Suportabilidade (Supportability)

Mecanismos de exportação documental, escalabilidade técnica e manutenções passivas futuras para continuidade.

* **3.5.1.1 [E]** — O sistema deve injetar os Holerites físicos no mundo real usando apenas e tão somente o molde fixo padronizado de forma universal contábil: A formatação PDF (Portable Document Format - Variantes fixadas estendidas imutáveis PDF/A de preservações). Esse pilar garante a aderência dos boletos nas telas dos mais diversos dispositivos eletrônicos (tablets, computadores e smartphones) do colaborador e a exatidão inalterada base de fonte.
* **3.5.1.2 [O]** — O sistema deve eliminar o desgaste contínuo cognitivo doloroso em massa dos funcionários que necessitam empacotar os recibos gerados para envios isolados nomeando em fúria milhares de recibos exatos por mês; aplicando a tecnologia estrita base para que a gravação já nasça salva nomeada categoricamente na matriz contínua da empresa (Combinando Nomenclaturas como "CPF + MES_E_ANO + RECIBO.pdf" unificado nas raízes estendidas do arquivamento do sistema de arquivos operacionais global limpo e lógico corporativo).

### 3.6 Restrições Físicas e de Design (+)

Fundamentos técnicos imperativos escolhidos por segurança corporativa nacional que não devem transmutar em ideias tecnológicas novas não auditadas.

* **3.6.1.1 [E]** — A plataforma deve limitar de forma sólida o funcionamento de todo seu arcabouço tecnológico como aplicação pesada local robusta autossuficiente (Desktop Application via JVM) em linguagem base sólida empresarial estipulada restrita (Java); evitando qualquer transição técnica da entrega para interfaces abertas de navegadores vulgares civis flutuantes onde abas possam misturar ou extrair verbas sem controle físico local do prédio ou trancamento local nativo seguro no PC raiz financeiro de tesouraria onde reside a vida de dezenas e centenas e milhares de cidadãos trabalhadores com seu CPF lá arquivado sem medo contínuo diário.
* **3.6.1.2 [O]** — O sistema deve amarrar em seu coração fixo matemático intransponível algorítmico exato restrito as réguas baseadas contínuas do ano de fundação trabalhista estipulada pelo governo pátria da constituição da federação local estrita fixada da data de base consolidada: A CLT nos anos operacionais vigentes fixos estendidos de (2025/2026), não inicializando com invenções de base matemáticas fantasiosas e taxas e impostos que o Sindicato local afirme estar acima e além da régua de cálculo fixo mestre progressiva nacional estipulada pra evitar e afastar possíveis perigosos processuais de advogados trabalhistas em cima das companhias que confiaram a nós a base operacional diária contábil limpa dos seus funcionários.
* **3.6.1.3 [O]** — O sistema deve restringir-se em termos absolutos à unidade cambial nacional base da federação oficial. A contabilidade base do cálculo e de pagamentos baseia-se unicamente restrita e intransponível nos valores monetários flutuantes fracionários convertidos padronizados em duas pontas exatas decimais arredondadas da moeda e espécie denominada Real em vigência contínua territorial absoluta das praças brasileiras de compensação bancária contábil matriz nacional central e única (BRL - R$), repudiando taxas conversíveis e moedas digitais fictícias abstratas estrangeiras na emissão final legal trabalhista pura.

---

## 4. Apêndices

### 4.1 Glossário de Termos e Jargões Financeiros
Abaixo constam os jargões utilizados no ambiente corporativo e nos processamentos matemáticos da plataforma:
*   **Alíquota:** Percentual (taxa) aplicado sobre uma Base de Cálculo para encontrar o valor do imposto. (Ex: Alíquota patronal de 8% para FGTS).
*   **Base de Cálculo:** Montante estrito sobre o qual se aplica um imposto. (Ex: Se o INSS incide apenas sobre Salário + Horas Extras, a Base de Cálculo do INSS ignorará o Valor do Vale Transporte).
*   **CLT:** Consolidação das Leis do Trabalho. Livro legal maior que rege o emprego formal no Brasil.
	*   **Competência:** O mês fechado a que se refere a apuração de um pagamento. (Ex: Um trabalho executado ao longo de maio pertence unicamente à competência "05/2026").
*   **DSR (Descanso Semanal Remunerado):** O direito legal inalienável a um dia de folga paga na semana (tradicionalmente o domingo civil). A severa perda da remuneração do DSR ocorre imediatamente quando o funcionário falta de forma injustificada em qualquer dia útil daquela mesma semana.
*   **FGTS:** Fundo de Garantia do Tempo de Serviço. Recolhimento contábil patronal (da empresa) sobre a remuneração, depositado em conta federal bloqueada na Caixa. O FGTS não é retirado do bolso do trabalhador.
*   **Holerite (Recibo de Vencimentos):** Contracheque oficial; documento PDF assinado que detalha matematicamente os proventos, descontos e tributos daquele ciclo.
*   **INSS:** Instituto Nacional do Seguro Social. Imposto trabalhista obrigatório e direto da parte do trabalhador para a previdência civil pública. É deduzido progressivamente com base nas tabelas federais.
*   **IRRF:** Imposto de Renda Retido na Fonte. Imposto confiscatório federal cobrado progressivamente, admitindo abatimentos baseados no número de filhos legais dependentes.
*   **Provento:** Verba ou bônus que adiciona, engorda e soma ao cálculo do salário bruto bancário. (Representa entrada monetária).
*   **Desconto:** Verba ou punição deduzida, amputada e subtraída do bolo contábil salarial bruto. (Representa perda monetária).
*   **Rubrica:** Etiqueta contábil numérica e alfabética que abriga a regra de incidência de um valor (Ex: A Rubrica Oficial nº 2 carrega sempre e apenas as lógicas financeiras de repasse da "Hora Extra 50%").

### 4.2 Documentação das Rubricas Nativas (Matriz de Cadastros)
Baseando-se no coração do repositório de rubricas padronizadas e cravadas no sistema SQL no momento zero da operação da plataforma (Catálogo Oficial Protetivo):

| Código | Descrição Oficial     | Natureza | Tipo Contábil | Incide INSS | Incide FGTS | Incide IRRF |
| ------ | --------------------- | -------- | ------------- | ----------- | ----------- | ----------- |
| 1      | Salário Base          | Provento | Fixo          | SIM         | SIM         | SIM         |
| 2      | Hora Extra 50%        | Provento | Variável      | SIM         | SIM         | SIM         |
| 3      | Hora Extra 100%       | Provento | Variável      | SIM         | SIM         | SIM         |
| 4      | Bônus / Gratificação  | Provento | Variável      | SIM         | SIM         | NÃO         |
| 5      | Cesta Básica          | Provento | Fixo          | NÃO         | NÃO         | NÃO         |
| 6      | PLR (Part. Lucros)    | Provento | Variável      | NÃO         | NÃO         | NÃO         |
| 7      | Adiantamento Salarial | Desconto | Variável      | NÃO         | NÃO         | NÃO         |
| 8      | Outros Proventos      | Provento | Variável      | NÃO         | NÃO         | NÃO         |
| 101    | Desconto INSS         | Desconto | Fixo          | NÃO         | NÃO         | NÃO         |
| 102    | Desconto por Falta    | Desconto | Variável      | NÃO         | NÃO         | NÃO         |
| 103    | Desconto por Atraso   | Desconto | Variável      | NÃO         | NÃO         | NÃO         |
| 104    | Desconto DSR          | Desconto | Variável      | NÃO         | NÃO         | NÃO         |
| 105    | Desconto Atestado     | Desconto | Variável      | NÃO         | NÃO         | NÃO         |
| 106    | Outros Descontos      | Desconto | Variável      | NÃO         | NÃO         | NÃO         |

*(Nota de Negócio: Rubricas entre 1 e 499 nascem trancadas e são blindadas contra manipulações tributárias pela gestão para que o motor não desmonte, mantendo a conformidade do Ministério do Trabalho).*

### 4.3 Fórmulas Matemáticas e Mecânica Contábil de Código

**[F-01] Engenharia do Valor da Hora de Trabalho Pura**
Para que o sistema descubra o lastro exato de uma hora do colaborador, é dividida a base absoluta pelo número de horas mensais.
`Valor Hora = (Salário Base Mensal Contratual) / (Carga Horária Padrão da Empresa)`
*Exemplo Simulado (Base de 220h mensais padrão): Salário de R$ 2.200,00 / 220 = R$ 10,00 por hora cravados.*

**[F-02] O Cálculo Composto de Horas Extras Adicionais**
`Valor Integral Extra = (Valor Hora Limpa) * (1 + (Percentual Fixo de Bônus / 100)) * (Quantidade Frações de Horas Apontadas)`
*Exemplo Simulado (Prêmio CLT de 50% extra): R$ 10,00 * 1.5 * 2 horas apontadas = R$ 30,00 englobados aos proventos.*

**[F-03] A Dedução de Atraso Fracionado Físico**
`Desconto Atraso Culpável = (Valor da Hora Limpa / 60) * Minutos de Atraso Acumulados Apontados`
*Exemplo Simulado: Trabalhador com hora valendo R$ 10,00. Atraso real de 30 minutos na roleta = R$ 5,00 subtraídos sumariamente.*

**[F-04] O Motor Escalável de INSS Progressivo Nacional**
Extirpada a alíquota fixa linear de outrora, a `CalculadoraINSS` nativa no núcleo Java fatia o valor em cascatas.
Se a Base INSS tributável apontar R$ 3.000,00 brutos:
1.  O sistema isola e apura a Faixa 1 até o teto oficial, multiplicando pela sua alíquota base diminuta.
2.  O saldo que transcende a primeira faixa deságua no funil da Faixa 2, recebendo o imposto apenas daquele segundo patamar.
3.  O trâmite continua progressivamente até exaurir todo o R$ 3.000,00 originais, unificando a soma dos fracionamentos exatos apurados.
*O valor líquido imposto resultante final gravita automaticamente na "Rubrica Mestra 101".*

**[F-05] Engenharia Confiscatória do Imposto de Renda (IRRF)**
`Base Real do IRRF = (Soma Estrita de Proventos cuja Natureza seja Incide_IRRF = TRUE) - (Desconto INSS da Fórmula F-04 já apurado integralmente) - (Número de Dependentes Legais do Colaborador * Valor Padrão Estatal Isento por Criança)`
Após forjar a Base do IRRF pura e isenta, a arquitetura cruza o valor remanescente com a "Tabela Escalar Oficial de IRRF". Localizando a faixa exata, multiplica a taxa de percentual ali existente e em seguida abate incondicionalmente a "Parcela a Deduzir" de socorro estipulada pela Receita Federal nas regras da tributação de impostos pátria.

**[F-06] Dedução Compulsória de Faltas Físicas e Rota da Perda do DSR**
`Punição Faltas Estritas = (Salário Base Fixo / 30 Dias Civis) * Número Cru de Dias Declarados como Faltas`
`Corte Retaliatório de DSR = Na condição algorítmica onde existam Faltas > 0, dispara-se o confisco aditivo severo:  Corte DSR extra = (Salário Base Fixo / 30 Dias Civis)`
*(Isso embute a norma vital do mundo corporativo: O cidadão que se ausenta sem atestar numa quinta-feira é ferido em duas vias contábeis brutais e imediatas — O dia em que não se apresentou perante o ponto e o descanso do respectivo domingo cortado em retaliação algorítmica e legal imediata).*

### 4.4 Matriz de Tabelas Operacionais Governamentais (Referência Embutida: 2024/2025)

**A Estrutura Federal Progressiva do INSS**
*(Alocadas na classe `FaixaINSS` do repositório núcleo central)*
| Faixa Temporal de Salário (Base do Imposto) | Alíquota Escalável | Parcela Estática de Dedução Oficial |
|---------------------------------|----------|-------------------|
| Patamar 1: Até o teto de R$ 1.412,00 | 7,5% (Base Zero) | Isenção Plena (R$ 0,00) |
| Patamar 2: De R$ 1.412,01 saltando até R$ 2.666,68 | 9,0% Ativos | R$ 21,18 (Alívio Federal) |
| Patamar 3: De R$ 2.666,69 saltando até R$ 4.000,03 | 12,0% Ativos | R$ 101,18 (Alívio Federal) |
| Patamar 4: De R$ 4.000,04 saltando até o teto máximo pátrio fixado em R$ 7.786,02 | 14,0% Pesados | R$ 181,18 (Alívio Federal) |

*(Nota Administrativa: O Teto final teto superior previdenciário base no Brasil estipula um bloqueio para quem ganha dezenas de milhares de reais; o imposto do SFP trancará inexoravelmente sua subida caso os cálculos de renda superem as margens de teto oficiais apontadas pelo executivo).*

**A Tabela Estrita Nacional do Imposto de Renda (IRRF)**
*(Engrenada na classe computacional `CalculadoraIRRF` em execução)*
| Base Apurada e Limpa para o IRRF Oficial | Alíquota Tributável de Confisco | Parcela Estática a Deduzir de Ajuda do IR |
|---------------------------------|----------|-------------------------|
| Isenção Primordial: Rendimentos enxutos limitados em R$ 2.259,20 | 0% Imunes | R$ 0,00 (Não Tributado) |
| Escada Base: De rendas somadas entre R$ 2.259,21 até estourar nos R$ 2.826,65 | 7,5% Restritos | R$ 169,44 Subtraídos |
| Escada Mediana: De ganhos entre R$ 2.826,66 encostando e limitados aos R$ 3.751,05 | 15,0% Ascendentes | R$ 381,44 Subtraídos |
| Escada Avassaladora: De R$ 3.751,06 em diante com parada e freio final no teto abstrato de R$ 4.664,68 | 22,5% Severos | R$ 662,77 Subtraídos |
| Confisco Supremo e Marginal: Qualquer ganho avulso financeiro excedente à marca final e teto de R$ 4.664,68 ao mês | 27,5% Mortais | R$ 896,00 Subtraídos de Ajuda |

*(Alerta de Redução por Laço Sanguíneo: Para cada menino, menina ou adulto dependente legal cravado ativamente no cadastro primário temporal do titular no sistema corporativo, o motor garantirá a imposição da ajuda social abatendo exatos e adicionais R$ 189,59 fixos diretamente do núcleo base do cálculo perante o tribunal da receita, aliviando consideravelmente as amarras do imposto para pais nutridos das companhias instaladoras do SFP).*

---

## 5. Índice (Sumário da Estrutura)

1. **Introdução**
   - 1.1 Propósito do Documento
   - 1.2 Escopo do Produto
   - 1.3 Glossário e Definições de Negócio
   - 1.4 Premissas e Limitações Estratégicas
2. **Descrição Geral da Solução Corporativa**
   - 2.1 Perspectiva e Enquadramento do Produto
   - 2.2 Funcionalidades do Produto (O Catálogo de Entregas)
   - 2.3 Taxonomia e Características Comportamentais dos Usuários
   - 2.4 Restrições Globais e Mandatórias de Negócio
   - 2.5 Suposições de Cenário e Dependências Corporativas Prévias
3. **Requisitos Específicos do Sistema (FURPS+)**
   - 3.1 Requisitos de Funcionalidade (Functionality)
     - 3.1.1 Módulo de Segurança de Acesso, Autenticação e Perfis (Governança)
     - 3.1.2 Módulo de Configuração e Políticas Matrizes (A Empresa Padrão)
     - 3.1.3 Módulo de Capital Humano e Cadastro (A Jornada do Funcionário)
     - 3.1.4 Módulo Contábil Customizado (Regras de Rubricas e Incidências)
     - 3.1.5 Módulo de Apuração do Tempo Físico (Lançamento de Frequência Mensal Excepcional)
     - 3.1.6 Módulo Núcleo Matemático de Tributação Progressiva e Apuração Automática
     - 3.1.7 Módulo Cérebro: Reservas de Longo Prazo e Governança do Fechamento Irrevogável (Status Final)
     - 3.1.8 Módulo de Exposição Final da Solução (Emissão de Holerites Imutáveis)
     - 3.1.9 Módulo Silencioso de Cúspide: Auditoria Absoluta Corporativa e o Histórico Universal (Log Criptográfico)
   - 3.2 Requisitos de Usabilidade (Usability)
   - 3.3 Requisitos de Confiabilidade (Reliability)
   - 3.4 Requisitos de Desempenho (Performance)
   - 3.5 Requisitos de Suportabilidade (Supportability)
   - 3.6 Restrições Físicas e de Design (+)
4. **Apêndices**
   - 4.1 Glossário de Termos e Jargões Financeiros
   - 4.2 Documentação das Rubricas Nativas (Matriz de Cadastros)
   - 4.3 Fórmulas Matemáticas e Mecânica Contábil de Código
   - 4.4 Matriz de Tabelas Operacionais Governamentais (Referência Embutida: 2024/2025)
5. **Índice (Sumário)**
