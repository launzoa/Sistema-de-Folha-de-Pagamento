USE sfp;

-- 1. Inserir Funcionários Fictícios
INSERT IGNORE INTO funcionarios (nome, cpf, cargo, data_admissao, salario_bruto, numero_dependentes, status)
VALUES 
('Rogério Garcia', '111.111.111-11', 'Analista de Sistemas', '2025-01-10', 5000.00, 0, TRUE),
('Ronaldo Celso', '222.222.222-22', 'Gerente de TI', '2024-03-15', 9000.00, 2, TRUE),
('Danilo Pereira', '333.333.333-33', 'Suporte Técnico', '2026-02-01', 2500.00, 1, TRUE);

-- 2. Inserir Folhas de Meses Passados (Fechadas)
INSERT IGNORE INTO folha_mes (id, competencia, dias_uteis, status)
VALUES
(1001, '04/2026', 22, 'Fechada'),
(1002, '05/2026', 21, 'Fechada');

-- 3. Inserir Lançamentos nas Folhas Passadas para gerar histórico de Holerites
-- Folha de Abril/2026 (id = 1001)
-- Rogério Garcia (111.111.111-11) -> 10 horas extras 50%
INSERT IGNORE INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor)
VALUES (1001, '111.111.111-11', 2, 10, 340.90);

-- Ronaldo Celso (222.222.222-22) -> Sem ocorrências em abril

-- Danilo Pereira (333.333.333-33) -> 1 falta + DSR
INSERT IGNORE INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor)
VALUES (1001, '333.333.333-33', 102, 2, 166.66);


-- Folha de Maio/2026 (id = 1002)
-- Ronaldo Celso (222.222.222-22) -> Bônus Variável (Cod 4)
INSERT IGNORE INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor)
VALUES (1002, '222.222.222-22', 4, 1, 1000.00);

-- Danilo Pereira (333.333.333-33) -> VT e PAT (Códigos 901 e 902 criados no controller)
INSERT IGNORE INTO rubrica VALUES (901, 'Vale Transporte (VT)', 'Desconto', 'Fixo', false, false, false, true, true);
INSERT IGNORE INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, valor)
VALUES (1002, '333.333.333-33', 901, 6, 150.00);
