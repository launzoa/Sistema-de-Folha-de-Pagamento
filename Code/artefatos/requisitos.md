# Especificação de Requisitos de Negócio
**Sistema de Folha de Pagamento (SFP)**
**Versão:** 1.0.0

---

## 1. Introdução

### 1.1 Propósito do Documento
Este documento de requisitos constitui a especificação completa e definitiva dos requisitos de negócios para o Sistema de Folha de Pagamento (SFP). Ele serve como o contrato universal entre os idealizadores do produto, os gestores operacionais (Recursos Humanos e Contabilidade) e as equipes de construção técnica (desenvolvedores). 
Este documento descreve estritamente *o que* o sistema fará pelas pessoas, *quais* problemas ele resolve e *como* as regras do mundo real (Leis Trabalhistas, políticas corporativas) impõem limites e condições à operação da plataforma.

### 1.2 Escopo do Produto
O Sistema de Folha de Pagamento atua como o maestro das obrigações financeiras e trabalhistas de corporações regidas pela Consolidação das Leis do Trabalho (CLT). O SFP substitui o cálculo humano falho por uma automação rígida e transparente.
**Dentro do Escopo da Plataforma:**
*   A gestão vitalícia e histórica dos dados contratuais e demográficos dos trabalhadores.
*   A definição e a parametrização global de taxas governamentais, regras de descontos corporativos e pacotes de benefícios personalizados (Rubricas).
*   A operação fluida e mensal de apontamento de frequência (controle de faltas injustificadas, atrasos proporcionais, e abonos via atestados médicos) e horas extras.
*   A execução financeira matemática da conversão de salário bruto para líquido, deduzindo progressivamente as alíquotas oficiais de INSS e bloqueando operações financeiras proibitivas (como gerar dívida/salário negativo ao trabalhador).
*   A proteção patrimonial da empresa através de auditoria imutável (diário de bordo de segurança) e fechamentos de folhas que congelam o passado de forma irrefutável para defesa jurídica.
*   A geração dos documentos oficiais de comprovação, destacando-se o contracheque legal (Holerite) formatado e projetado para validade documental digital, bem como o cálculo das provisões a longo prazo de férias e décimos terceiros salários.

**Fora do Escopo:**
*   Pagamento e emissão de notas para profissionais autônomos (Pessoa Jurídica), Freelancers, Estagiários e Menores Aprendizes.
*   Sistemas de medição física de entrada/saída (hardware de relógio de ponto eletrônico físico por biometria).
*   Repasse direto de valores interbancários automatizados (integração eletrônica automática de contas correntes); a ferramenta fornece os valores a serem depositados, mas não movimenta as contas dos bancos ativamente.

### 1.3 Glossário e Definições de Negócio
Para garantir que Diretores, Advogados e Operadores de RH interpretem os requisitos da mesma forma, as seguintes definições são leis hermenêuticas para este documento:
*   **Abono Médico:** A neutralização contábil de uma falta ou punição de frequência devido à entrega e registro de atestado médico com validade legal.
*   **Auditoria / Diário de Bordo (Log):** O recurso interno de governança corporativa que grava quem, quando e qual dado exato foi alterado por um humano na plataforma, usado exclusivamente para averiguação de fraudes ou erros.
*   **Base de Cálculo:** O somatório parcial de dinheiro sobre o qual o governo ou a empresa aplica uma porcentagem de imposto. (Ex: O INSS não recai sobre tudo que o funcionário ganha, mas apenas sobre as verbas listadas como "Base de Cálculo do INSS").
*   **Competência (Mês de Competência):** O mês e o ano aos quais um determinado trabalho, atraso ou imposto se referem. Por exemplo, horas trabalhadas em outubro geram a folha de competência "10/2026".
*   **CLT:** Consolidação das Leis do Trabalho. O conjunto legal de regras brasileiras para as relações de emprego formais.
*   **DSR (Descanso Semanal Remunerado):** Direito legal a um dia de folga paga na semana (geralmente domingo). A perda desse direito funciona como penalização financeira gravíssima imposta a quem falta sem justificativa durante a semana.
*   **Folha Aberta vs Folha Fechada:** 
    *   *Aberta:* Estado temporário onde rascunhos, testes e modificações de faltas, prêmios ou demissões podem ser reajustados à vontade pelo operador para simulação.
    *   *Fechada:* Estado trancado, assinado e definitivo. O sistema proíbe universalmente que dados daquele mês sejam retocados, apagados ou disfarçados posteriormente. É a base da confiança contábil.
*   **Holerite:** O comprovante, contracheque ou recibo de pagamento salarial que lista nominalmente cada ganho e cada perda que levaram ao valor transferido para a conta bancária do indivíduo.
*   **Provento e Desconto:** 
    *   *Provento:* Todo e qualquer centavo ou valor adicionado ao bolo financeiro a favor do funcionário (dinheiro que entra).
    *   *Desconto:* Qualquer centavo retido do bolo, seja pela empresa (por multas ou benefícios com coparticipação) ou imposto pelo governo (dinheiro que sai).
*   **Rubrica:** O nome contábil para o "tipo de movimentação". Ao invés de o sistema registrar apenas "+R$100,00", ele usa uma "Rubrica", que é uma etiqueta padronizada informando "Aquele valor de R$100 refere-se à Rubrica de Adicional Noturno".

### 1.4 Premissas e Limitações Estratégicas
*   **Conformidade Territorial:** Todas as operações matemáticas e de arredondamento seguem estritamente a unidade monetária corrente no Brasil (Reais - BRL), usando precisão rigorosa de duas casas decimais com arredondamento contábil justo.
*   **Isolamento Tecnológico:** A ferramenta funcionará sem precisar conectar aos computadores ou redes diretas do Ministério do Trabalho; toda a inteligência está contida e embutida na regra da aplicação instalada, garantindo uso mesmo em redes internas isoladas das companhias (air-gapped environments).

---

## 2. Descrição Geral da Solução Corporativa

Esta seção destrincha a espinha dorsal do projeto. Ela contextualiza o leitor sobre o formato da solução, o catálogo das funções essenciais que serão entregues, a taxonomia dos operadores que conduzirão o sistema diariamente, bem como as restrições inquebráveis e as premissas operacionais sob as quais o Sistema de Folha de Pagamento (SFP) operará no mundo real corporativo. Toda a descrição foca inteiramente no impacto administrativo, na redução do custo operacional, na eliminação do risco jurídico, mantendo o processo contábil aderente e imune a interpretações dúbias.

### 2.1 Perspectiva e Enquadramento do Produto
O Sistema de Folha de Pagamento (SFP) nasce como uma plataforma isolada, independente e autossuficiente (standalone), voltada exclusivamente para as demandas internas dos Departamentos de Recursos Humanos (RH) e das controladorias contábeis empresariais.

*   **A Arquitetura Física Organizacional:** A aplicação foi projetada para atuar em computadores corporativos isolados. Isso significa que o software reside e "vive" diretamente dentro da máquina de mesa ou laptop do Analista de RH, não exigindo acesso à internet global para funcionar. Ele conecta-se unicamente ao arquivo central da empresa (o banco de dados corporativo), que pode estar hospedado localmente no próprio prédio da corporação, isolado da vulnerabilidade da grande rede. Isso blinda o processo contábil contra ataques externos massivos.
*   **Posicionamento no Ecossistema da Empresa:** O SFP não é um aplicativo voltado ao funcionário comum; não existe "portal do colaborador" para checar holerites pelo celular. O produto é uma "Mesa de Operações" de alta segurança, de uso estritamente gerencial e restrito aos profissionais que calculam e liberam o dinheiro da companhia. Ele substitui a desordem das planilhas amadoras por um único ponto de verdade visual e centralizado, focado na ergonomia do operador que passará horas processando cálculos extensos sob pressão no fim de cada mês civil.

### 2.2 Funcionalidades do Produto (O Catálogo de Entregas)
A ferramenta consolida um catálogo completo e orquestrado de funções automatizadas que eliminam a digitação dupla e a dependência de calculadoras paralelas. O sistema entregará, fundamentalmente:

1.  **O Cálculo Automático (O Motor de Retenções):** O processamento cirúrgico e matemático da conversão do Salário Bruto em Salário Líquido. O sistema processará, de forma milimétrica, o desconto em cascata de tributos (como o INSS com suas faixas governamentais progressivas) sem a necessidade de um operador buscar tabelas na internet.
2.  **A Governança Total de Exceções Mensais:** A centralização do registro diário ou mensal do imprevisível. O sistema capturará e converterá em dinheiro ocorrências como as Horas Extras cumpridas em madrugadas, a perda punitiva originária de Faltas injustificadas, os minutos perdidos nos Atrasos matinais e a neutralização absoluta dessas falhas garantida pela apresentação física de Atestados Médicos validados.
3.  **Provisões Trabalhistas e O Passivo Oculto:** O cálculo contínuo dos deveres fiscais vindouros e direitos dos empregados. A cada fechamento mensal regular, a plataforma estipulará matematicamente as reservas intocáveis patronais necessárias (ex: as taxas de provisão fracionada mensais obrigatórias de Férias acrescidas de seu 1/3 constitucional) para o planejamento orçamentário do CEO.
4.  **A Fábrica Documental e Holerites Oficiais:** A emissão de documentos jurídicos assinados de forma eletrônica ou para impressão. Isso abrange a compilação final que emite individualmente o Holerite formal, destrinchando transparentemente todas as contas do processamento como um arquivo PDF inviolável padrão, para proteção judicial em potenciais litígios futuros.
5.  **A Auditoria Inviolável:** Uma cúpula de gravação em segundo plano. O log (livro de ocorrências) do SFP absorverá todas as atitudes passíveis de risco legal ou desvios efetuadas na plataforma de forma silenciosa e permanente. Exemplo prático: Quando um Analista aplicar um desconto de 500 reais na conta de um par de equipe; a plataforma carimbará o rastro sem possibilidade de negação plausível.

### 2.3 Taxonomia e Características Comportamentais dos Usuários
Para o planejamento estrutural do uso diário, os usuários do SFP foram mapeados em dois arquétipos distintos com funções rigorosamente independentes na organização. O acesso ao sistema obedece fielmente à identidade profissional definida para cada um.

1.  **Analista de RH / Auxiliar de Departamento Pessoal:**
    *   **Identidade e Rotina:** O usuário de "Chão de Fábrica" da plataforma. Profissional com conhecimento empírico avançado e teórico prático sobre a Consolidação das Leis do Trabalho (CLT), acordos de sindicato (CCT) e fechamento de horas.
    *   **A Dor que o SFP resolve para ele:** Busca desesperadamente agilidade e alívio do estresse cognitivo de checar centenas de minutos de atrasos em réguas calculadoras paralelas. Quer a confiança de que o sistema aplicará o desconto certo se ele disser apenas "Este operador faltou no dia X".
    *   **Comportamento na Plataforma:** Focado estritamente na digitação célere de ocorrências e navegação através de listas extensas de funcionários nas horas limite antes da liberação do pagamento para a área de Tesouraria bancária.
2.  **O Administrador do Sistema (Gestor Master / Sócio / Diretor Controller):**
    *   **Identidade e Rotina:** O usuário executivo e guardião corporativo. Responsável legal ou representante direto contábil da corporação que detém a responsabilidade de manter os padrões normativos globais e fiscalizar desvios.
    *   **A Dor que o SFP resolve para ele:** O medo de fraudes internas e pagamentos indevidos não autorizados; a agonia da obsolescência na lei trabalhista oficial.
    *   **Comportamento na Plataforma:** Focado estritamente em ajustes finos. Ele passará seu tempo atualizando as planilhas oficiais de regras globais (exemplo: criar um pacote de desconto temporário de "Perda de Uniforme" ou mudar o padrão tributário global). Além disso, utilizará ativamente a tela de "Governança/Auditoria" para interrogar relatórios e validar se as equipes de base agiram idoneamente.

### 2.4 Restrições Globais e Mandatórias de Negócio
As regras de contorno que impedem que o software seja moldado de forma não compatível com o ambiente jurídico corporativo. Essas regras são limites duros que ditaram o planejamento lógico da solução:

*   **A Cerca da Legislação Vigente:** O sistema deve curvar-se estritamente à modelagem tributária nacional prevista na Lei maior aplicável (Consolidação das Leis do Trabalho) atualizada pelas Portarias vigentes dos anos atuantes de (2025/2026). Decisões de cortes trabalhistas regionais ou sindicatos menores que firam as raízes federais das alíquotas obrigatórias federais não devem corromper a matriz lógica da base de cálculo das plataformas sem uma intervenção ativa e manual humana em regras "livres".
*   **O Cofre da Lei Geral de Proteção de Dados (LGPD - Lei nº 13.709/2018):** Como o software lidará com os dois bens mais pessoais de uma figura cívica — sua identidade de documentação privada (RG/CPF) e o valor íntimo e privado que aufere na sociedade civil (seu salário bancário privado) —, o sistema é categorizado como manipulador de "Dados Sensíveis". Sob essa restrição fortíssima, a restrição por barreiras de bloqueio (Login e Senha individual inquebrável por terceiros e senhas escondidas por "hash" na base local) é obrigação vital do SFP, impedindo acessos anônimos globais irrestritos e garantindo auditorias rastreáveis judiciais perfeitas.
*   **O Dogma da Imutabilidade Contábil (Conformidade Pós-Fechamento):** Como um livro-razão chancelado num cartório público oficial contábil, após o ato gerencial que decreta que um mês temporal está "Fechado", a plataforma rejeita mecanicamente, sob qualquer circunstância alegada pelo operador por mais nobre que seja o pretexto, a reedição, alteração ou exclusão arbitrária de qualquer dado, prêmio, ou cálculo presente daquele ciclo temporal extinto do passado corporativo. A integridade de um mês repassado financeiramente a um banco real deve ser inquestionável do momento de trancamento histórico pra frente da empresa.
*   **Independência Arquitetônica Sustentável (Tecnologia Não Refém):** Para garantir o funcionamento em redes estatais ou corporativas lentas em áreas industriais sem banda-larga contínua externa veloz; a aplicação não operará dentro de um "site de internet frágil", tampouco baseará seus esforços dependendo de uma página web falha no Google Chrome. Ele será erguido com a fundação local corporativa estável em linguagem Java (exigindo o mínimo de uma base madura e atestada empresarial em versão padrão corporativo mínima superior homologada) rodando 100% dos seus mecanismos autossuficientes sem mendigar recursos remotos à uma rede de internet exterior.

### 2.5 Suposições de Cenário e Dependências Corporativas Prévias
Premissas adotadas que precisam existir no mundo físico para que as promessas da plataforma tomem a forma desejada pelos planejadores.
*   **Da Estabilidade da Rede Física Local do Prédio Organizacional:** Supõe-se, como regra irrevogável do cenário estipulado no chão de fábrica da companhia implementadora do SFP; que a fiação e o roteamento interno comunicante entre os computadores dos vários eventuais Analistas logados interligando-os ao Servidor Central (o local que arquiva as pastas bancárias localizadas internamente pelo departamento da Tecnologia Informacional local de controle empresarial gerencial da corporação) tenham fluxo estável sem apagões intermitentes contínuos nas rotas que venham a esvaziar salários pela metade num banco corporativo num fechamento da tarde sem envio contínuo contínuo sem quedas brutais da conectividade matriz corporativa de energia da área matriz predial da operação local.
*   **A Exigência de Poder Computacional Humano Mínimo Local Laboral:** Espera-se irrevogavelmente e de antemão por premissas gerenciais base financeiras que qualquer um dos Analistas logados à interface opere através de uma estação e guichês físicos minimamente potentes em hardwares locais da atual década exigente atual operante mundial computacional global comercial básica global para não experimentar engasgos ou sofrimentos nas trocas de milhões de centavos e números contábeis calculados em milissegundos globais pelas pastas temporais.
*   **A Engrenagem da Disciplina Funcional Humana Contábil Mensal Prévia:** Como dependência final para o milagre corporativo financeiro do botão de cálculo e encerramento automatizado matemático mensal operar do modo devido sem catástrofes; baseia-se pesadamente na premissa e suposição corporativa basilar operacional que o RH não deixará meses brancos de frequência não justificada antes do trancamento das folhas finais, pois o sistema robô é inerte, ele exige, aguarda, e é categoricamente dependente do fator primário causal humano alimentando os apontamentos temporais da frequência presencial, imputações e exclusões corporativas de horários justificados faltosos extraordinários prévios e justificados na linha contábil temporal temporal (Exceções locais mensais dinâmicas de faltas médicas ou prêmios locais) ANTES e unicamente PRÉVIOS ao processo e comando de bloqueio do fechamento de contas bancárias globais salariais gerais da folha final na data agendada final global interna do departamento comercial estipulado pela base de tesouraria do RH. Sem humanos anotando o trabalho, a máquina financeira do SFP falha e para.

---

## 3. Requisitos Específicos do Sistema (FURPS+)

Esta seção detalha o comportamento estrito e exaustivo da plataforma, mapeando cada obrigação mínima que o sistema precisa cumprir perante o cenário empresarial. A categorização segue a modelagem padrão do framework FURPS+ de Craig Larman, dissecando as interações em Funcionalidade, Usabilidade, Confiabilidade, Desempenho, Suportabilidade e Restrições Físicas.

### 3.1 Requisitos de Funcionalidade (Functionality)
As capacidades primordiais da plataforma. Abordam os fluxos de trabalho centrais gerenciais e operacionais para a execução correta da legislação trabalhista e controle interno do caixa.

#### 3.1.1 Módulo de Segurança de Acesso, Autenticação e Perfis (Governança)
* **3.1.1.1** — O sistema deve exigir, imperativamente, a apresentação de credenciais exclusivas válidas (Usuário e Senha) na tela de inicialização, blindando o acesso e a visualização de qualquer dashboard, dado salarial ou informação corporativa antes da verificação positiva.
* **3.1.1.2** — O sistema deve realizar o mascaramento inquebrável das senhas inseridas no campo de login, convertendo os caracteres em asteriscos visuais. O sistema também impedirá operações de teclado voltadas à cópia (Ctrl+C) sobre este campo, mitigando o risco de captura de senhas por curiosos.
* **3.1.1.3** — O sistema deve possuir uma régua de bloqueio condicional: caso um usuário de RH falhe seguidamente a inserção de sua senha por mais de três tentativas no mesmo dia, o acesso dessa conta será suspenso sumariamente (congelado) até que um Administrador Master providencie o destrancamento, para combater ataques de adivinhação.
* **3.1.1.4** — O sistema deve monitorar ininterruptamente a atividade do usuário logado. Após cronometrar a ausência total de interações via mouse e teclado pelo espaço exato de 30 minutos contínuos, o sistema expulsará a conta logada de maneira automática, abortando o fluxo e redirecionando a visualização de volta para a tela cega de "Login", justificando com uma notificação na tela ("Sessão expirada por inatividade").
* **3.1.1.5** — O sistema deve assegurar a segregação rigorosa de poderes: Contas de perfil "Analista de RH" terão seus menus visuais de configuração estrutural da empresa (Matriz, Log de Auditoria Global, Deleção de Contas de Acesso) não apenas desabilitados, mas integralmente ocultos de seu campo de visão de navegação da interface.
* **3.1.1.6** — O sistema deve oferecer um mecanismo claro e explícito de "Desconexão" (Logoff/Sair), posicionado no topo da estrutura de navegação, permitindo que a finalização humana da rotina diária seja correspondida pelo fechamento voluntário do terminal.

#### 3.1.2 Módulo de Configuração e Políticas Matrizes (A Empresa Padrão)
* **3.1.2.1** — O sistema deve limitar e obrigar a execução sob o escopo único de um único cadastro patronal ("Empresa Matriz"). O SFP rejeitará processamentos de holerites para matrizes misturadas sob bases de dados únicas.
* **3.1.2.2** — O sistema deve submeter o cadastro da Razão Social, Inscrição Estadual (se pertinente à operação de mercado) e o Cadastro Nacional da Pessoa Jurídica (CNPJ) como campos inegociáveis. 
* **3.1.2.3** — O sistema deve integrar um validador algorítmico governamental (Dígito Verificador) sobre o CNPJ matriz digitado. CNPJs inválidos, matematicamente incoerentes, com digitações nulas (ex: 00.000.000/0000-00) ou repetitivas inviabilizarão a inicialização vital da ferramenta.
* **3.1.2.4** — O sistema deve gerir e reter centralmente a parametrização nacional de tributos em uma tela restrita (exclusiva a Administradores), definindo a Alíquota Fixa e invariável do Fundo de Garantia (FGTS) como indexador global da empresa para que o motor financeiro atue. Modificar esse índice alterará o fluxo contábil da empresa por completo.
* **3.1.2.5** — O sistema deve armazenar e obrigar a parametrização da jornada constitucional de base horária trabalhista do empregador (Carga Horária Mensal Padrão). Tal número (tradicionalmente 220 horas para celetistas) será o denominador mestre inquebrável para cálculos fracionários de descontos ou prêmios monetários.
* **3.1.2.6** — O sistema deve permitir a parametrização de valores monetários absolutos universais padronizados da corporação, tais como o teto de "Benefício Fixo de Vale-Refeição/Cesta Básica", garantindo que a inserção de novos colaboradores já seja abastecida com essas condições de base preestabelecidas.

#### 3.1.3 Módulo de Capital Humano e Cadastro (A Jornada do Funcionário)
* **3.1.3.1** — O sistema deve apresentar, em um dashboard visual central, o mapeamento gerencial sumariado da ocupação física da firma: Quantos indivíduos estão com o status vigente (Ativos) e o levantamento paralelo do "passivo e evasão" (Quantos já passaram e se encontram como Inativos).
* **3.1.3.2** — O sistema deve condicionar toda Admissão à existência formal e válida de um Cadastro de Pessoas Físicas (CPF). Este número é a chave inquestionável da plataforma; não existem admissões com CPFs provisórios.
* **3.1.3.3** — O sistema deve varrer o arquivo histórico durante uma nova admissão. Se o CPF preenchido coincidir rigorosamente com um CPF já constante no passado corporativo daquela firma (seja de um trabalhador de hoje ou um desligado em 2014), o sistema abortará a inclusão e alertará sobre a tentativa de "Clonagem Funcional/CPF Duplicado".
* **3.1.3.4** — O sistema deve condicionar a admissão à obediência financeira da nação: É terminantemente bloqueada a consolidação de um "Salário Base Contratual" cujo valor em Reais (R$) se prove matematicamente inferior ao "Piso do Salário Mínimo Vigente" do ano.
* **3.1.3.5** — O sistema deve exigir, durante a integração cadastral, o arquivamento estrito das rotas interbancárias do cidadão (Código Febraban do Banco de Destino, Agência sem máscaras e o número nominal da Conta Corrente/Poupança do portador para trâmites diretos).
* **3.1.3.6** — O sistema deve assegurar a temporalidade linear inquebrável da contratação. Datas de nascimento não podem ser fixadas no futuro; bem como a Data Formal da Admissão em Carteira também não pode ser registrada com lacunas anacrônicas distantes (ex: admitido em 1900 ou projetado fisicamente para trabalhar antes mesmo do preenchimento e existência temporal da fundação da referida empresa no Brasil).
* **3.1.3.7** — O sistema deve rastrear alterações em massa. Diante da concessão de uma benesse (Aumento Salarial ou Promoção de Função), a ferramenta preservará para sempre em seus alicerces contábeis a "Linha do Tempo Profissional". Tal linha salvará exatamente a Data e Hora em que a caneta assinou a promoção, o usuário do SFP que concedeu a melhoria, o vencimento anterior revogado e o patamar novo adquirido, possibilitando análises judiciais.
* **3.1.3.8** — O sistema deve eliminar o conceito computacional de "exclusão de pessoas" para dados da vida real atrelados a valores e histórico corporativo. O fim de um ciclo de trabalho humano não acarretará na deleção, mas acionará uma mudança flag de estado civil ("Inativado/Demitido"). Um perfil inativo paralisa a matemática folhar daquele dia em diante, mas todo seu legado imutável continuará operando nas prestações de anos passados e no repositório final de PDF's emitidos da empresa.

#### 3.1.4 Módulo Contábil Customizado (Regras de Rubricas e Incidências)
* **3.1.4.1** — O sistema deve trazer as regras e "Rubricas Matrizes do Brasil" blindadas de fábrica na classe inicial numérica (Geralmente IDs 001 até 005), representando as obrigações constitucionais como "Salário Padrão, Faltas sem Justificativa, Atrasos Laborais, Horas Adicionais Noturnas e Horas Extras 50%". O Administrador-Chefe ou Dono estarão proibidos mecanicamente de excluí-las ou de removerem delas a cobrança tributária estipulada pela base de leis estatais do Ministério da Economia.
* **3.1.4.2** — O sistema deve garantir a livre arquitetura do balanço contábil permitindo a construção criativa de novas categorias pelo gestor (Ex: Rubrica 884 - Reembolso de Creche). Todo benefício ou taxa customizada exigirá a imputação forçada de uma Natureza Dupla: Ela injeta Dinheiro Positivo ao cálculo de holerites líquidos (Vencimento/Provento) ou subtrai capital da mão do obreiro a favor do caixa corporativo (Desconto).
* **3.1.4.3** — O sistema deve fornecer para as Rubricas personalizadas um quadro de incidência flexível manipulado apenas por contas administrativas. Esse quadro exige decisões exatas de tributação: Esta verba integra o montante onde o governo debita o INSS mensalmente? O governo tira FGTS patronal de 8% disso? Essa verba engorda o cesto base do imposto de Renda futuro (IRRF)? O sistema recalculará toda a matemática universal do SFP perante qualquer preenchimento alterado nesse quadro em específico e não exitará em agir conforme determinado pelo Administrador daquele momento.

#### 3.1.5 Módulo de Apuração do Tempo Físico (Lançamento de Frequência Mensal Excepcional)
* **3.1.5.1** — O sistema deve possuir uma tela central orientada a lotes de dados operacionais (Lançamentos de Exceções) para abrigar a digitação mensal do imprevisível antes de se ordenar a ordem final e global da apuração de folha.
* **3.1.5.2** — O sistema deve processar atrasos diários de maneira milimétrica de frações contínuas, captando o tempo perdido em "Minutos de Atraso". A matemática converterá a Base Bruta de 220 horas para seu equivalente centesimal diminuto, traduzindo o desvio num valor passivo exato que formará a "Rubrica de Desconto do Dia Perdido".
* **3.1.5.3** — O sistema deve processar uma "Tolerância Padrão Legal de Circulação" para evitar atritos com pequenas perdas de ônibus, isentando minutos curtos triviais não repetitivos de se transformarem em cortes operacionais financeiros pesados conforme a lei autoriza sem exageros ditatoriais contábeis (ex: isenção condicional restrita para percalços até dez minutos flutuantes habituais por trajeto). 
* **3.1.5.4** — O sistema deve executar a "Penalização do Repouso Perdido": Ao identificar a inclusão pelo Analista de 1 Falta não escudada e não provida de fundamentação nas planilhas do colaborador; o sistema estenderá a punição legal exigida por lei tirando compulsoriamente do colaborador os valores contínuos atrelados ao seu DSR daquela respectiva semana, ceifando mais recursos financeiros em uma tacada de conformidade laboral que exime cálculos braçais de papel para quem controla as presenças num caderno pelo RH.
* **3.1.5.5** — O sistema deve implementar o Escudo Documental Sanitário ("Atestados Médicos"): Quando datas validadas amparadas pela Lei 605 de medicina curativa preencherem as tabelas de um colaborador; as faltas assinaladas ali serão neutralizadas. Nem faltas injustificadas, tampouco a punição fatal do Repouso Remunerado serão ativados. O escudo de saúde assegura pagamento normal líquido pelo prazo em que for digitado, contanto que se comprove formalmente através de anotações físicas arquivadas separadamente.
* **3.1.5.6** — O sistema deve computar trabalhos de esforço extra laborados. O operador reportará montantes excedentes fracionados ("12 horas e meia") classificadas sobre o prêmio padrão constitucional de sobreaviso ou exaustão: os dias comuns alavancam com aumento base de 50%, e domingos/feriados civis injetam ao operário prêmios avulsos diretos do montante base no montante de 100% integrais.
* **3.1.5.7** — O sistema deve processar a teoria jurídica da "Aglomeração Habitual Passiva". Se um funcionário desfrutar do apontamento no sistema sobre qualquer pacote de valores de prêmios pontuais, como um "Bônus Contínuo" por três calendários civis trancados consecutivos interligados, um alarme silencioso corporativo de notificação saltará ao Administrador Controller do sistema alertando o risco legal latente das incorporações compulsórias perante eventuais causas abertas de sindicatos e varas processuais de defesas na justiça do trabalho ("Perigo Contábil: Salário Velado Repetitivo Habitual Constituído").

#### 3.1.6 Módulo Núcleo Matemático de Tributação Progressiva e Apuração Automática
* **3.1.6.1** — O sistema deve integrar o Algoritmo do Recém-Chegado ("Cálculo Pro-Rata"): Ao deparar-se com admissões ativas firmadas depois da virada do primeiro dia do mês de referência do pagamento; o sistema amputa temporariamente no cálculo de simulação a verba fixa salarial contratual plena da base e recria uma base diminuta diária proporcional, blindando que faltas apliquem confiscos errôneos em funcionários com menos de cinco dias de registro empregatício inicial dentro de empresas abertas naquele mesmo encerramento.
* **3.1.6.2** — O sistema deve agregar o Bolo de Lucros (Base Inicial): Unirá e acumulará em base universal tudo estipulado fixamente à pessoa mais as verbas soltas digitadas e acumuladas em eventos no mês. Esse saldo consolidado bruto atuará como isca tributária global pro cálculo central final.
* **3.1.6.3** — O sistema deve efetuar e aplicar a Drenagem Progressiva da Faixa Federal Previdenciária (A Múltipla Escadaria do INSS). O SFP está barrado de bater cálculos retos errôneos antigos de alíquota estática simplista no topo salarial. Ele vai ratear o salário, cortar nos limites fatiados correspondentes progressivos, subtrair as percentagens escalares federais (como fatiar as taxas de 7.5% na sobra e avançar à escala sequencial), unindo o imposto picotado total ao final de centavos. E o sistema avisará nas tabelas globais da prestação a chamada "Alíquota Base Efetiva Média Fiel Real".
* **3.1.6.4** — O sistema deve acionar o Freio de Dívida Social Operacional ("Teto Limite Zero"). Não existe cenário ou falha admissível legal humana em que um Analista apure penalidades absurdas sobre faltas, atrasos e descontos pesados de vales até esvaziar o saldo e que isso crie um holerite constrangedor apontando "Salário Pagar Bancário" no valor líquido assustador de base negativa "R$ -200,00". O motor para a descida de confiscos aos exatos R$ 0,00 centavos líquidos no fechamento do recibo individual, assegurando que dívidas internas devem pular pro contracheque de meses ulteriores ao fato pela gestão da RH da empresa contratante perante autorização física do inadimplente corporativo de banco.

#### 3.1.7 Módulo Cérebro: Reservas de Longo Prazo e Governança do Fechamento Irrevogável (Status Final)
* **3.1.7.1** — O sistema deve aplicar o algoritmo de Provisões Invisíveis. Uma pessoa empregada ativará um relógio silencioso interno no sistema onde cada trinta dias sem ocorrência graves finalizadas se converterá contábil e matematicamente num acréscimo reservatório virtual corporativo garantindo noções baseadas ao gestor das despesas iminentes brutais exigidas perante demissões ou períodos férias gozados futuros da base integralizada e calculados nas planilhas do fim do ano de um Terço extra + 13 Salário integral no mês decimo segundo (Natal).
* **3.1.7.2** — O sistema deve abrigar e exportar o Custo Base FGTS em relatórios consolidados finais gerenciais para o departamento financeiro emitir guias unificadas da arrecadação federal Caixa sem precisarem de planilhas extras fora da arquitetura.
* **3.1.7.3** — O sistema deve viabilizar a "Trava de Arquivamento Inviolável". Uma vez analisado e revisto, a ordem humana suprema de "Fechar Folha" será dada. Essa chave mudará o estado de um "Mês/Ano Competência" de Aberta para Fechada.
* **3.1.7.4** — O sistema deve defender a Trava de Arquivamento: Nenhuma ocorrência temporal inserida por descuido do dia trancado para trás deverá afetar um centavo do passado. Um salário reajustado em Março da empresa não terá poder de desfazer os holerites gerados nos repasses dos fechamentos já lacrados de janeiro anterior e fevereiro contábil transcorrido pela equipe do RH e pagos perante o banco comercial. É uma muralha temporal contra amadorismo temporal ou passivos ocultos das rescisões judiciais alteradas à força bruta humana por retaliação perante fiscais estatais processuais fiscais de tribunais sindicais.

#### 3.1.8 Módulo de Exposição Final da Solução (Emissão de Holerites Imutáveis)
* **3.1.8.1** — O sistema deve exportar de forma vetorizada, profissional e formatada, o Recibo Estendido Documental Trabalhista Final assinado por via das telas: O "Holerite".
* **3.1.8.2** — O sistema deve desenhar o Holerite com o cabeçalho base informando o nome completo do contratante da praça (A Empresa com seu CNPJ fiscal contábil), lado a lado da identidade física demográfica admissional do favorecido em si (CPF exato do sujeito e do encargo).
* **3.1.8.3** — O sistema deve diagramar o Holerite organizando o miolo descritivo principal dividindo os Proventos injetados e listando as alíquotas com as bases onde se incidiram as leis previdenciárias de confisco estatais federais baseadas ou municipais dos anos transcorridos perante o Brasil.

#### 3.1.9 Módulo Silencioso de Cúspide: Auditoria Absoluta Corporativa e o Histórico Universal (Log Criptográfico)
* **3.1.9.1** — O sistema deve abrigar uma câmara fechada ("Caixa Preta do Rh" ou "Auditoria Operacional"). Não importará a bondade ou malícia de uma atitude em tela; a manipulação financeira na base (alteração salarial e aprovação do trancamento de meses) gerarão marcas cronológicas.
* **3.1.9.2** — O sistema deve registrar o nome carimbado perante a rede exato do "Usuário Analista" ativo logado sentado àquela estação no milissegundo do crime da base fiscal e evidenciará o montante anterior ("Ele alterou o salário do senhor X de R$ 1.500 que era base antes para a soma nova que ficou apontada agora sob a base nova R$ 3.500 no botão de salvamento").
* **3.1.9.3** — O sistema deve enrijecer o acesso da Caixa Preta; a Auditoria não exibe botões de "Deleção", "Passar Borracha" ou "Lixeira Temporal". É uma matriz baseada fundamentalmente e tecnicamente no fluxo "Append-Only" contínuo vitalício (Apenas escuta contínua de fatos inseridos nas bases no fim da fita magnética).
* **3.1.9.4** — O sistema deve abrigar o Túnel do Viajante Contábil. Gestores podem regressar à competência trancada passada de exatos cinco anos (ex: Out/2021) e re-extrair a massa de PDFs de holerites base de funcionários inativos há lustros completos demitidos daquelas competências estáticas idênticas preservadas imunes perante mudanças legais contemporâneas vigentes ocorridas anos à frente daquela janela em si, favorecendo advocacia contábil extrema das varas.

### 3.2 Requisitos de Usabilidade (Usability)
Fatores atrelados à ergonomia visual profunda e à mitigação de catástrofes manuais provocadas pela fadiga ocupacional nas rotinas administrativas massivas da equipe.

* **3.2.1.1** — O sistema deve automatizar a inteligência da digitação das rotinas densas contábeis; a inserção crua repetitiva numeral deve auto converter o alinhamento com pontos nos CPFs contínuos, trançar números no documento corporativo estendido matriz federal contínuo em CNPJs sem esforço braçal auxiliar ("000" para "000.") e defender visualmente a quebra do centavo fracionado final sempre através da base indicadora de moeda brasileira contínua global formatada por uma vírgula exata sem uso falho crônico do ponto base decimal alienígena estrangeiro comum em interfaces obsoletas rústicas sem padronizações no Brasil corporativo comercial geral.
* **3.2.1.2** — O sistema deve comunicar através do viés das "Sinalizações Semafóricas de Restrição Legal"; campos cadastrais essenciais pra admissão ou geração financeira contínua de guias obrigatórias possuirão ícones ou indicações avermelhadas persistentes no visor, engessando qualquer salvamento forçado apressado e acusando exatamente quais laudas restam vazias no preenchimento falho dos novatos apressados logados sem supervisão direta no departamento naquelas longas exaustões dos turnos laborais civis perante fim do mês temporal na RH.
* **3.2.1.3** — O sistema deve mitigar a cultura e erro base da colisão catastrófica acidental perante cliques compulsivos ("Double-click habits"). Uma barreira imperativa e visual intrusiva central ("Deseja definitivamente irreversivelmente aplicar essa penalidade financeira no alvo ou congelar toda uma massa corporativa do mês para sempre e vitalício? - CONFIRMAR/CANCELAR") deve surgir saltando do eixo, impedindo que o nervosismo custe falhas irreparáveis irreparáveis contábeis a empresa implementadora base ali do fato na execução do painel rotineiro apressado.
* **3.2.1.4** — O sistema deve abrigar, permanentemente afixado no cume central orientador ininterrupto sem ser tampado das interfaces perante os menus e abas e formulários exaustivos do fluxo do uso diário estafante de ocorrências; um farol ou placa luminosa global resumindo sem dúvidas o destino ou a fenda temporal que a pessoa atual operadora se afunda na rotina de lançamento hoje; exclamando abertamente o Status Matriz Global Geral "COMPETÊNCIA VIGENTE AGORA 12/2026 - MÊS ABERTO AO EDITAL DE MODIFICAÇÕES FINAIS APROVADAS" baseando-se a tranquilidade.
* **3.2.1.5** — O sistema deve tratar todos os comunicados gerados através da falha computacional e lógicas sob linguajar leigo e compassivo, estirpando da face corporativa códigos arcanos ilegíveis ("Exceção fatal nula nas entidades 04 da JVM do Hibernate"); traduzindo o desvio em conselhos: "As informações bancárias digitadas encontram-se fora de um parâmetro aceito comercialmente. Refaça a checagem no formulário."  

### 3.3 Requisitos de Confiabilidade (Reliability)
Mecanismos enraizados no algoritmo para barrar fraudes corporativas internas baseadas no apodrecimento moral das regras de negócio do dinheiro real sob pressões e conchavos e o combate base à obsolescência dos fatos gravados passados inquestionáveis por justiça estatal.

* **3.3.1.1** — O sistema deve consolidar o Pacto do Passado Cristalizado inabalável nas execuções globais financeiras de prestação de verbas civis; Nenhuma medida ou sentença de recomposição de verbas que adicione aumentos num contrato feito ativamente na janela corrente operada da "Folha de hoje mês tal" refletirá, espalhará ou arruinará de modo fantasmagórico o saldo retroativo depositado consolidado fiscal legal lacrado guardado selado das inúmeras folhas trancadas guardadas com códigos bases das vidas extintas guardadas passadas temporais inquestionáveis.
* **3.3.1.2** — O sistema deve amparar a indestrutibilidade base legal nas atas das edições. Como não há lixeira, exclusões limpas perfeitas intocáveis contínuas em atitudes nefastas nas alterações financeiras efetuadas; o sistema assume um posicionamento jurídico vital contábil impositivo para os gestores finais: Não existindo como varrer lixo financeiro por debaixo de tapetes logísticos de TI, os donos ganham provas cabais documentais imaculadas plenas em juízos judiciais da comarca fiscal do Brasil contra o funcionário da base financeira estipulada em roubos de dinheiro ou reajustes combinados maliciosos contínuos.

### 3.4 Requisitos de Desempenho (Performance)
Expectativas relativas à capacidade tangível perante carga exaustiva pesada massiva bruta laboratorial base de tempo contínuo contábil nos fechamentos de prazos estrangulados apertados da equipe.

* **3.4.1.1** — O sistema deve gerenciar a varredura base dos cadastros operantes dos talentos corporativos contratados através de técnicas da leitura interna paginada imediata rápida e listagens tabulares precisas sem exaurir recursos primários computadores básicos base estipulados e previstos de uso pela firma sem causar paralisia total ("travamentos gerais das janelas de Java") das telas locais do trabalhador perante bases com 1.500 trabalhadores ativos ao longo de anos extensos corporativos baseados globais ali arquivados.
* **3.4.1.2** — O sistema deve reter na fundação da arquitetura isolada toda a engrenagem matemática complexa abstrata bruta contábil necessária para triturar valores e devolver cálculos exatos líquidos federais para holerites independentemente estritamente sem se curvar para consultar internet contínua mundial de fibra-ótica paralela; o cérebro processador está residente ali local, garantindo execução ininterrupta de velocidade mesmo que galpões base percam acesso e torres de sinais telefônicas globais contínuas parem nas bases matrizes isoladas distantes em fábricas industriais regionais pesadas de montadoras afastadas sem redes.

### 3.5 Requisitos de Suportabilidade (Supportability)
Mecanismos de exportação documental, escalabilidade técnica e manutenções passivas futuras para continuidade.

* **3.5.1.1** — O sistema deve injetar os Holerites físicos no mundo real usando apenas e tão somente o invólucro do molde inviolável fixo padronizado por excelência universal contábil: O formatação PDF (Portable Document Format - Variantes fixadas estendidas imutáveis PDF/A de preservações). Esse pilar garante a aderência dos boletos nas telas de tablets variados do colaborador e a exatidão inalterada base de fonte e alinhamentos milimétricos dos dados de vencimento base sobre os cartuchos matrizes corporativos dos escritórios.
* **3.5.1.2** — O sistema deve eliminar o desgaste contínuo cognitivo doloroso em massa dos funcionários que necessitam empacotar os recibos gerados para envios isolados nomeando em fúria milhares de recibos exatos por mês; aplicando a tecnologia estrita base para que a gravação já nasça salva nomeada categoricamente na matriz contínua da empresa (Combinando Nomenclaturas como "CPF + MES_E_ANO + RECIBO.pdf" unificado nas raízes estendidas do arquivamento do sistema de arquivos operacionais global limpo e lógico corporativo).

### 3.6 Restrições Físicas e de Design (+)
Fundamentos técnicos imperativos escolhidos por segurança corporativa nacional que não devem transmutar em ideias tecnológicas novas não auditadas.

* **3.6.1.1** — A plataforma deve limitar de forma draconiana o funcionamento de todo seu arcabouço tecnológico como aplicação pesada local robusta autossuficiente (Desktop Application via JVM) em linguagem base sólida empresarial estipulada restrita (Java); coibindo qualquer transição técnica da entrega matriz pra interfaces abertas de navegadores vulgares civis flutuantes onde abas possam misturar ou extrair verbas sem controle físico local do prédio ou trancamento local nativo seguro no PC raiz financeiro de tesouraria onde reside a vida de dezenas e centenas e milhares de cidadãos trabalhadores com seu CPF lá arquivado sem medo contínuo diário.
* **3.6.1.2** — O sistema deve amarrar em seu coração fixo matemático intransponível algorítmico exato restrito as réguas baseadas contínuas do ano de fundação trabalhista estipulada pelo governo pátria da constituição da federação local estrita fixada da data de base consolidada: A CLT nos anos operacionais vigentes fixos estendidos de (2025/2026), bloqueando invenções base matemáticas fantasiosas e taxas e impostos que o Sindicato aleatório exótico local afirme estar acima e além da régua de cálculo fixo mestre progressiva nacional estipulada pra evitar e afastar litígios processuais perigosos de advogados trabalhistas base de causas nas varas municipais cíveis abertas das capitais pra cima das companhias que confiaram a nós a base operacional diária contábil limpa das famílias perante os tribunais judiciais absolutos regionais fiscais em si de cada respectivo polo contábil exato federal de atuações globais do nosso Brasil real corporativo contínuo exaustivo.
* **3.6.1.3** — O sistema deve restringir-se em termos absolutos à unidade cambial nacional base da federação oficial. A contabilidade base do cálculo e de pagamentos baseia-se unicamente restrita e intransponível nos valores monetários flutuantes fracionários convertidos padronizados em duas pontas exatas decimais arredondadas da moeda e espécie denominada Real em vigência contínua territorial absoluta das praças brasileiras de compensação bancária contábil matriz nacional central e única (BRL - R$), repudiando taxas conversíveis e moedas digitais alienígenas fictícias abstratas estrangeiras na emissão final legal trabalhista pura baseada local ali oficial exata plena nas bases laborais.

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

| Código | Descrição Oficial | Natureza | Tipo Contábil | Incide INSS | Incide FGTS | Incide IRRF |
|--------|-----------------------|-----------|-----------|-------------|-------------|-------------|
| 1 | Salário Base | Provento | Fixo | SIM | SIM | SIM |
| 2 | Hora Extra 50% | Provento | Variável | SIM | SIM | SIM |
| 3 | Hora Extra 100% | Provento | Variável | SIM | SIM | SIM |
| 4 | Bônus / Gratificação | Provento | Variável | SIM | SIM | NÃO |
| 5 | Cesta Básica | Provento | Fixo | NÃO | NÃO | NÃO |
| 6 | PLR (Part. Lucros) | Provento | Variável | NÃO | NÃO | NÃO |
| 7 | Adiantamento Salarial | Desconto | Variável | NÃO | NÃO | NÃO |
| 8 | Outros Proventos | Provento | Variável | NÃO | NÃO | NÃO |
| 101 | Desconto INSS | Desconto | Fixo | NÃO | NÃO | NÃO |
| 102 | Desconto por Falta | Desconto | Variável | NÃO | NÃO | NÃO |
| 103 | Desconto por Atraso | Desconto | Variável | NÃO | NÃO | NÃO |
| 104 | Desconto DSR | Desconto | Variável | NÃO | NÃO | NÃO |
| 105 | Desconto Atestado | Desconto | Variável | NÃO | NÃO | NÃO |
| 106 | Outros Descontos | Desconto | Variável | NÃO | NÃO | NÃO |

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
