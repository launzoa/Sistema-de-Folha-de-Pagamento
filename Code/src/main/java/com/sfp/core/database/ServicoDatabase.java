/**
 * @brief Classe responsável por gerenciar teste para o sistema conectado com o
 *        banco de dados.
 *        Possui métodos responsáveis por limpar o banco de dados e gerar dados de
 *        teste.
 */

package com.sfp.core.database;

import java.sql.Connection;
import java.sql.Statement;

public class ServicoDatabase {

        /**
         * @brief Método responsável por zerar o banco de dados. Usado para fins de
         *        teste.
         */
        public void zerarBancoDeDados() throws Exception {
                // Pega o nome de todas as tabelas do BD
                String[] tabelas = {
                                "lancamento",
                                "folha_mes",
                                "auditoria",
                                "funcionarios",
                                "endereco_empresa",
                                "empresa",
                                "usuario"
                };

                // Conecta com o BD
                try (Connection conn = ConexaoBD.getConnection();
                                Statement stmt = conn.createStatement()) {

                        // Desativa checagem de chaves estrangeiras
                        stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");

                        // Zera todas as tabelas, exceto as rubricas e impostos
                        for (String tabela : tabelas) {
                                stmt.execute("TRUNCATE TABLE " + tabela + ";");
                        }

                        // Insere o usuário admin no BD, e.g., (nome:admin, senha:admin)
                        String sqlAdmin = "INSERT INTO usuario (nome, senha, perfil, status) VALUES ('admin', 'admin', true, true);";
                        stmt.execute(sqlAdmin);

                        // Reativa chaves estrangeiras
                        stmt.execute("SET FOREIGN_KEY_CHECKS = 1;");
                }
        }

        /**
         * @brief Método responsável por gerar dados de teste para o sistema.
         *        Ele insere dados de teste nas tabelas do banco de dados para um teste
         *        de mesa.
         */
        public void gerarTesteDeMesa() throws Exception {
                zerarBancoDeDados(); // Zera o banco de dados antes de gerar dados de teste.

                // Conecta com o BD
                try (Connection conn = ConexaoBD.getConnection();
                                Statement stmt = conn.createStatement()) {

                        // Desativa checagem de chaves estrangeiras
                        stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");

                        // Insere os dados da Empresa: Unesp
                        String sqlEmpresa = "INSERT INTO empresa (cnpj, razao_social, email, resp_legal) " +
                                        "VALUES ('48.031.918/0001-24', 'Universidade Estadual Paulista - Unesp', 'unesp@unesp.br', 'Estado de São Paulo');";
                        stmt.execute(sqlEmpresa);

                        // Assim como o endereço
                        String sqlEndereco = "INSERT INTO endereco_empresa (cnpj_empresa, cep, logradouro, bairro, complemento) "
                                        +
                                        "VALUES ('48.031.918/0001-24', '19060-900', 'Rua Roberto Simonsen 305', 'Jardim das Rosas', 'Presidente Prudente SP');";
                        stmt.execute(sqlEndereco);

                        // Adiciona os dados dos Funcionários
                        String sqlFuncs = "INSERT INTO funcionarios (nome, cpf, cargo, data_admissao, salario_bruto, numero_dependentes, status) VALUES "
                                        +
                                        "('Rogério Garcia', '111.111.111-11', 'Professor Titular', '2010-02-01', 15000.00, 2, TRUE), "
                                        +
                                        "('Celso Olivette', '222.222.222-22', 'Diretor de Faculdade', '2005-08-15', 20000.00, 1, TRUE), "
                                        +
                                        "('Ronaldo Celso', '333.333.333-33', 'Analista Administrativo', '2015-05-10', 5500.00, 0, TRUE), "
                                        +
                                        "('Analice Costacurta', '444.444.444-44', 'Coordenadora de Departamento', '2020-01-20', 17500.00, 3, TRUE);";
                        stmt.execute(sqlFuncs);

                        // Adiciona Folhas de Pagamento (Abril Fechada, Maio Fechada, Junho Aberta)
                        String sqlFolhas = "INSERT INTO folha_mes (id, competencia, dias_uteis, status) VALUES " +
                                        "(1, '04/2026', 22, 'Fechada'), " +
                                        "(2, '05/2026', 21, 'Fechada'), " +
                                        "(3, '06/2026', 22, 'Aberta');";
                        stmt.execute(sqlFolhas);

                        // Adiciona Lançamentos
                        stmt.execute(
                                        "INSERT IGNORE INTO rubrica (codigo, descricao, natureza, tipo, incide_inss, incide_fgts, incide_irrf, padrao, ativo) VALUES (901, 'Vale Transporte', 'Desconto', 'Fixo', false, false, false, false, true);");
                        stmt.execute(
                                        "INSERT IGNORE INTO rubrica (codigo, descricao, natureza, tipo, incide_inss, incide_fgts, incide_irrf, padrao, ativo) VALUES (902, 'Vale Refeição', 'Desconto', 'Fixo', false, false, false, false, true);");

                        // Aril (Folha 1)
                        stmt.execute(
                                        "INSERT INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor, modalidade, base_calculo) VALUES (1, '111.111.111-11', 2, 5, 340.90, 'Quantidade', 'Salário Bruto');");
                        stmt.execute(
                                        "INSERT INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor, modalidade, base_calculo) VALUES (1, '444.444.444-44', 102, 2, 213.33, 'Quantidade', 'Salário Bruto');");

                        // Maio (Folha 2)
                        stmt.execute(
                                        "INSERT INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor, modalidade, base_calculo) VALUES (2, '222.222.222-22', 4, 1, 2500.00, 'Valor', 'Livre');");
                        stmt.execute(
                                        "INSERT INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor, modalidade, base_calculo) VALUES (2, '333.333.333-33', 901, 1, 6.00, 'Porcentagem', 'Salário Bruto');");
                        stmt.execute(
                                        "INSERT INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor, modalidade, base_calculo) VALUES (2, '333.333.333-33', 902, 1, 150.00, 'Valor', 'Livre');");

                        // Junho (Folha 3 - Aberta)
                        stmt.execute(
                                        "INSERT INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor, modalidade, base_calculo) VALUES (3, '111.111.111-11', 4, 1, 1200.00, 'Valor', 'Livre');");
                        stmt.execute(
                                        "INSERT INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor, modalidade, base_calculo) VALUES (3, '222.222.222-22', 102, 1, 666.66, 'Valor', 'Livre');");

                        // Insere no Log de Auditoria
                        String sqlLog = "INSERT INTO auditoria (timestamp, usuario, perfil, acao, entidade, detalhes) VALUES "
                                        +
                                        "(NOW(), 'admin', 'Administrador', 'GERAR_MOCK', 'Sistema', 'Geração massiva de dados do Teste de Mesa Unesp.');";
                        stmt.execute(sqlLog);

                        // Reativa chaves estrangeiras
                        stmt.execute("SET FOREIGN_KEY_CHECKS = 1;");
                }
        }
}
