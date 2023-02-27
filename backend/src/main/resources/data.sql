INSERT INTO TB_AUTOR (ID, NOME, PSEUDONIMO, DT_NASCIMENTO, SEXO, EMAIL, HASH_SENHA, CONTA_VALIDADA, AUTOR_BLOQUEADO)
    VALUES (-3, 'Johny', 'Jhn', '2010-05-05', 'MASCULINO', 'jhn@bol.com', '#asdf', 1, 0);
INSERT INTO TB_AUTOR (ID, NOME, PSEUDONIMO, DT_NASCIMENTO, SEXO, EMAIL, HASH_SENHA, CONTA_VALIDADA, AUTOR_BLOQUEADO)
    VALUES (-2, 'Mary', 'Gatinha_autora', '2085-01-25', 'FEMININO', 'gtinha_autora@hotmail.com', 'gatinha123', 1, 0);
INSERT INTO TB_AUTOR (ID, NOME, PSEUDONIMO, DT_NASCIMENTO, SEXO, EMAIL, HASH_SENHA, CONTA_VALIDADA, AUTOR_BLOQUEADO)
    VALUES (-1, 'Fábio Pereira', 'Fábio pereira', '2081-11-12', 'NAO_BINARIO', 'fabio_pereira@gmail.com', '1@esf#15Ab/=2', 1, 1);
    
INSERT INTO TB_TEXTO (ID, TITULO, CORPO, DT_CRIACAO, AUTOR_ID, STATUS) VALUES (-4, 'Paçoca', 'Paçoca é doce', '2020-01-01', -3, 'APROVADO');
INSERT INTO TB_TEXTO (ID, TITULO, CORPO, DT_CRIACAO, AUTOR_ID, STATUS) VALUES (-3, 'Ônibus', 'No ônibus, indo ao trabalho, Petra suspirou quando viu o engarrafamento à sua frente. Uma hora se passou até que chegassem ao gargalo na rua que causara tanto transtorno. Surpresos, os passageiros começaram a se acumular na janela da condução e Petra, movida pela curiosidade, foi olhar também. Um Porche com o porta-malas destroçado jazia na rua, bloqueando duas das três pistas. Petra imediatamente pensou: "esse carro é realmente de parar o trânsito".', '2023-02-27', -3, 'APROVADO');
INSERT INTO TB_TEXTO (ID, TITULO, CORPO, DT_CRIACAO, AUTOR_ID, STATUS) VALUES (-2, 'Farinha', 'Paçoca é quase neutra', '2022-10-03', -2, 'AGUARDANDO_APROVACAO');
INSERT INTO TB_TEXTO (ID, TITULO, CORPO, DT_CRIACAO, AUTOR_ID, STATUS) VALUES (-1, 'Strogonoff', 'Comida muito ruim', '2010-08-12', -1, 'APROVADO');

INSERT INTO TB_GENERO (ID, NOME) VALUES (1, 'Ação');
INSERT INTO TB_GENERO (ID, NOME) VALUES (2, 'Aventura');
INSERT INTO TB_GENERO (ID, NOME) VALUES (3, 'Biografia');
INSERT INTO TB_GENERO (ID, NOME) VALUES (4, 'Cotidiano');
INSERT INTO TB_GENERO (ID, NOME) VALUES (5, 'Distopia');
INSERT INTO TB_GENERO (ID, NOME) VALUES (6, 'Ensaios');
INSERT INTO TB_GENERO (ID, NOME) VALUES (7, 'Fanfic');
INSERT INTO TB_GENERO (ID, NOME) VALUES (8, 'Fantasia');
INSERT INTO TB_GENERO (ID, NOME) VALUES (9, 'Ficção científica');
INSERT INTO TB_GENERO (ID, NOME) VALUES (10, 'História');
INSERT INTO TB_GENERO (ID, NOME) VALUES (11, 'Horror');
INSERT INTO TB_GENERO (ID, NOME) VALUES (12, 'Humor');
INSERT INTO TB_GENERO (ID, NOME) VALUES (13, 'Infantil');
INSERT INTO TB_GENERO (ID, NOME) VALUES (14, 'LGBTQ+');
INSERT INTO TB_GENERO (ID, NOME) VALUES (15, 'Policial');
INSERT INTO TB_GENERO (ID, NOME) VALUES (16, 'Realismo');
INSERT INTO TB_GENERO (ID, NOME) VALUES (17, 'Religião');
INSERT INTO TB_GENERO (ID, NOME) VALUES (18, 'Romance');
INSERT INTO TB_GENERO (ID, NOME) VALUES (19, 'Suspense');

INSERT INTO TB_TEXTO_GENERO (TEXTO_ID, GENERO_ID) VALUES (-3, 12);
INSERT INTO TB_TEXTO_GENERO (TEXTO_ID, GENERO_ID) VALUES (-2, 2);
INSERT INTO TB_TEXTO_GENERO (TEXTO_ID, GENERO_ID) VALUES (-1, 3);
INSERT INTO TB_TEXTO_GENERO (TEXTO_ID, GENERO_ID) VALUES (-3, 4);
INSERT INTO TB_TEXTO_GENERO (TEXTO_ID, GENERO_ID) VALUES (-2, 3);
INSERT INTO TB_TEXTO_GENERO (TEXTO_ID, GENERO_ID) VALUES (-4, 3);
INSERT INTO TB_TEXTO_GENERO (TEXTO_ID, GENERO_ID) VALUES (-1, 1);