create table tb_autor (
	id bigint not null auto_increment,
	pseudonimo varchar(255) not null,
	nome varchar(255) not null,
	email varchar(255) not null,
	hash_senha varchar(255) not null,
	autor_bloqueado boolean not null,
	conta_validada boolean not null,
	dt_nascimento date,
	sexo varchar(255),
	primary key (id),
	unique(email),
	unique(pseudonimo)
);

create table tb_genero (
	id bigint not null auto_increment,
	nome varchar(255) not null,
	primary key (id)
);

create table tb_texto (
	id bigint not null auto_increment,
	autor_id bigint not null,
	titulo varchar(60) not null,
	corpo varchar(600) not null,
	status varchar(255) not null,
	dt_criacao timestamp(6) not null,
	primary key (id),
	foreign key (autor_id) references tb_autor(id)
);

create table tb_texto_genero (
	id bigint not null auto_increment,
	genero_id bigint not null,
	texto_id bigint not null,
	primary key (id),
	foreign key (texto_id) references tb_texto(id),
	foreign key (genero_id) references tb_genero(id)
);

INSERT INTO tb_genero (ID, NOME) VALUES (1, 'Ação');
INSERT INTO tb_genero (ID, NOME) VALUES (2, 'Aventura');
INSERT INTO tb_genero (ID, NOME) VALUES (3, 'Biografia');
INSERT INTO tb_genero (ID, NOME) VALUES (4, 'Cotidiano');
INSERT INTO tb_genero (ID, NOME) VALUES (5, 'Distopia');
INSERT INTO tb_genero (ID, NOME) VALUES (6, 'Ensaios');
INSERT INTO tb_genero (ID, NOME) VALUES (7, 'Fanfic');
INSERT INTO tb_genero (ID, NOME) VALUES (8, 'Fantasia');
INSERT INTO tb_genero (ID, NOME) VALUES (9, 'Ficção científica');
INSERT INTO tb_genero (ID, NOME) VALUES (10, 'História');
INSERT INTO tb_genero (ID, NOME) VALUES (11, 'Horror');
INSERT INTO tb_genero (ID, NOME) VALUES (12, 'Humor');
INSERT INTO tb_genero (ID, NOME) VALUES (13, 'Infantil');
INSERT INTO tb_genero (ID, NOME) VALUES (14, 'LGBTQ+');
INSERT INTO tb_genero (ID, NOME) VALUES (15, 'Policial');
INSERT INTO tb_genero (ID, NOME) VALUES (16, 'Realismo');
INSERT INTO tb_genero (ID, NOME) VALUES (17, 'Religião');
INSERT INTO tb_genero (ID, NOME) VALUES (18, 'Romance');
INSERT INTO tb_genero (ID, NOME) VALUES (19, 'Suspense');
