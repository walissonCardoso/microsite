INSERT INTO TB_AUTOR (ID, NOME, PSEUDONIMO, DT_NASCIMENTO, SEXO, EMAIL, HASH_SENHA, CONTA_VALIDADA, AUTOR_BLOQUEADO)
    VALUES (-3, 'Johny', 'Jhn', '2010-05-05', 'MASCULINO', 'jhn@bol.com', '#asdf', 1, 0);
INSERT INTO TB_AUTOR (ID, NOME, PSEUDONIMO, DT_NASCIMENTO, SEXO, EMAIL, HASH_SENHA, CONTA_VALIDADA, AUTOR_BLOQUEADO)
    VALUES (-2, 'Mary', 'Gatinha_autora', '2085-01-25', 'FEMININO', 'gtinha_autora@hotmail.com', 'gatinha123', 1, 0);
INSERT INTO TB_AUTOR (ID, NOME, PSEUDONIMO, DT_NASCIMENTO, SEXO, EMAIL, HASH_SENHA, CONTA_VALIDADA, AUTOR_BLOQUEADO)
    VALUES (-1, 'Fábio Pereira', 'Fábio pereira', '2081-11-12', 'NAO_BINARIO', 'fabio_pereira@gmail.com', '1@esf#15Ab/=2', 1, 1);
    
INSERT INTO TB_TEXTO (ID, TITULO, CORPO, DT_CRIACAO, AUTOR_ID, STATUS) VALUES (-3, 'Paçoca', 'Paçoca é doce', '2020-01-01', -3, 'APROVADO');
INSERT INTO TB_TEXTO (ID, TITULO, CORPO, DT_CRIACAO, AUTOR_ID, STATUS) VALUES (-2, 'Farinha', 'Paçoca é quase neutra', '2022-10-03', -2, 'AGUARDANDO_APROVACAO');
INSERT INTO TB_TEXTO (ID, TITULO, CORPO, DT_CRIACAO, AUTOR_ID, STATUS) VALUES (-1, 'Strogonoff', 'Comida muito ruim', '2010-08-12', -1, 'REPROVADO');

INSERT INTO TB_GENERO (ID, NOME) VALUES (-3, 'Ação');
INSERT INTO TB_GENERO (ID, NOME) VALUES (-2, 'Aventura');
INSERT INTO TB_GENERO (ID, NOME) VALUES (-1, 'Terror');

INSERT INTO TB_TEXTO_GENERO (TEXTO_ID, GENERO_ID) VALUES (-3, -3);
INSERT INTO TB_TEXTO_GENERO (TEXTO_ID, GENERO_ID) VALUES (-2, -2);
INSERT INTO TB_TEXTO_GENERO (TEXTO_ID, GENERO_ID) VALUES (-1, -1);