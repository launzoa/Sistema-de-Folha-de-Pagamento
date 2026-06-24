USE sfp;
-- Liberando o espaço (Movendo antigas para o final)
UPDATE rubrica SET codigo = 901 WHERE codigo = 101;
UPDATE rubrica SET codigo = 902 WHERE codigo = 102;
UPDATE rubrica SET codigo = 903 WHERE codigo = 103;

-- Ajustando 1 a 5
UPDATE rubrica SET descricao = 'Salário Padrão' WHERE codigo = 1;
UPDATE rubrica SET descricao = 'Atraso por hora', natureza = 'Desconto', tipo = 'Variável', incide_inss = false, incide_fgts = false, incide_irrf = false WHERE codigo = 4;
UPDATE rubrica SET descricao = 'Falta por dia', natureza = 'Desconto', tipo = 'Variável', incide_inss = false, incide_fgts = false, incide_irrf = false WHERE codigo = 5;

-- Inserindo 100 a 102
INSERT IGNORE INTO rubrica VALUES (100,'Desconto INSS','Desconto','Fixo',FALSE,FALSE,FALSE,TRUE,TRUE);
INSERT IGNORE INTO rubrica VALUES (101,'Desconto IRRF','Desconto','Fixo',FALSE,FALSE,FALSE,TRUE,TRUE);
INSERT IGNORE INTO rubrica VALUES (102,'Recolhimento FGTS','Desconto','Fixo',FALSE,FALSE,FALSE,TRUE,TRUE);
