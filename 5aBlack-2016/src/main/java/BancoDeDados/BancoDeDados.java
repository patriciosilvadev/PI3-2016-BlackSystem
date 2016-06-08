/*=============================================================================================

    NÃO ESQUECER DE COLOCAR O NOME DO BANCO COMO: "BlackSystemLavandeiria" E A SENHA E USUARIO COMO "app"

==============================================================================================*/


/*
CREATE TABLE FILIAL (
 ID_FILIAL INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_Departamento PRIMARY KEY,
 NOME_FILIAL VARCHAR(100) UNIQUE,
 CIDADE_FILIAL VARCHAR(80),
TEL_FIFLIAL VARCHAR(20),
CEP_FILIAL CHAR(17),
ESTADO_FILIAL VARCHAR(20)
);

CREATE TABLE SERVICO (
ID_SERVICO INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_Servico PRIMARY KEY,
NOME_SERV VARCHAR(50) UNIQUE,
TIPO_SERV VARCHAR(30),
 VALOR_SERV FLOAT(10),
 PRAZO_SERV INT
);

CREATE TABLE TIPO_PRODUTO (
 ID_TIPO_PROD INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_Tipo_Produto PRIMARY KEY,
 NOME_PROD VARCHAR(50),
 DESCRICAO_PROD VARCHAR(512)
);

CREATE TABLE CLIENTE (
 ID_CLIENTE INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1)CONSTRAINT PK_Cliente PRIMARY KEY,
CPF_CLIENTE VARCHAR(16) UNIQUE,
 NOME_CLIENTE VARCHAR(100),
 SOBRENOME_CLIENTE VARCHAR(150),
SEXO_CLIENTE CHAR(1),
NASC_CLIENTE VARCHAR(10),
TEL_CLIENTE VARCHAR(20),
 CEL_CLIENTE VARCHAR(21),
 EMAIL_CLIENTE VARCHAR(100),
 STATUS_CLIENTE CHAR(30),
DTCADASTRO_CLIENTE VARCHAR(10)
);

CREATE TABLE ENDERECO_CLIENTE (
 ID_ENDERECO_CLI INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_Endereco PRIMARY KEY,
 LOGRADOURO_CLI VARCHAR(150),
NUMERO_CLI INT,
 COMPLEMENTO_CLI VARCHAR(50),
 BAIRRO_CLI VARCHAR(80),
 CIDADE_CLI VARCHAR(80),
 ESTADO_CLI VARCHAR(4),
 CEP_CLI VARCHAR (17) ,
ID_CLIENTE INT NOT NULL,
FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE)
);

CREATE TABLE FUNCIONARIO (
 ID_FUNC INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_Funcionario PRIMARY KEY,
 CPF_FUNC CHAR(16) NOT NULL UNIQUE,
 NOME_FUNC VARCHAR(100),
 SOBRENOME_FUNC VARCHAR(150),
SEXO_FUNC CHAR(1),
NASC_FUNC VARCHAR(10),
TEL_FUNC VARCHAR(20),
DATA_CONTRATACAO DATE,
 CEL_FUNC VARCHAR(21),
 EMAIL_FUNC VARCHAR(100),
 STATUS_FUNC CHAR(30),
 SENHA_FUNC VARCHAR(100),
 CARGO_FUNC VARCHAR(80),
 SALARIO_FUNC FLOAT(20),
ID_FILIAL INT,
SALT CHAR(20) NOT NULL,
DTCADASTRO_FUNC VARCHAR(10),

FOREIGN KEY (ID_FILIAL) REFERENCES FILIAL(ID_FILIAL)
);

CREATE TABLE ENDERECO_FUNCIONARIO (
 ID_ENDERECO_FUNC INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_ENDERECO_FUNC PRIMARY KEY,
 LOGRADOURO_FUNC VARCHAR(150),
NUMERO_FUNC INT,
 COMPLEMENTO_FUNC VARCHAR(50),
 BAIRRO_FUNC VARCHAR(80),
 CIDADE_FUNC VARCHAR(80),
 ESTADO_FUNC CHAR(4),
 CEP_FUNC CHAR(17),
ID_FUNC INT,
FOREIGN KEY(ID_FUNC) REFERENCES FUNCIONARIO(ID_FUNC)
);

CREATE TABLE PRODUTO (
 ID_PROD INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT PK_Produto PRIMARY KEY,
 NOME_PROD VARCHAR(100) UNIQUE,
 VALIDADE_PROD DATE,
 LOTE INT,
 STATUS VARCHAR(30),
 QTD_MIN DECIMAL(10),
 QTD_MAX DECIMAL(10),
 QTD_ATUAL DECIMAL(10),
 ID_TIPO_PROD INT,
ID_FUNC INT,
FOREIGN KEY (ID_TIPO_PROD) REFERENCES TIPO_PRODUTO(ID_TIPO_PROD),
FOREIGN KEY (ID_FUNC) REFERENCES FUNCIONARIO(ID_FUNC)
);

CREATE TABLE PEDIDO ( 
ID_PEDIDO INT NOT NULL GENERATED ALWAYS AS IDENTITY
(START WITH 1, INCREMENT BY 1) CONSTRAINT PK_PEDIDO PRIMARY KEY,
 STATUS VARCHAR(20),
 DATA_ENTRADA DATE,
 DATA_SAIDA DATE,
 ID_FUNC INT,
 ID_CLIENTE INT NOT NULL,
 ID_FILIAL INT,


FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE),
FOREIGN KEY (ID_FUNC) REFERENCES FUNCIONARIO(ID_FUNC),
FOREIGN KEY (ID_FILIAL) REFERENCES FILIAL(ID_FILIAL)
);

CREATE TABLE PECA (
 ID_PECA INT NOT NULL GENERATED ALWAYS AS IDENTITY 
(START WITH 1, INCREMENT BY 1) CONSTRAINT PK_Roupa PRIMARY KEY,
 QTD_PECA INT,
 TIPO_PECA VARCHAR(30),
 COR_PECA VARCHAR(10),
 TIPO_TECIDO VARCHAR(30),
 ID_PEDIDO INT,
 ID_SERVICO INT,

FOREIGN KEY (ID_PEDIDO) REFERENCES PEDIDO(ID_PEDIDO),
FOREIGN KEY (ID_SERVICO) REFERENCES SERVICO(ID_SERVICO)
);

CREATE TABLE Alteracao (
 id_alteracao INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_Alteracao PRIMARY KEY,
 tipo_de_alteracao VARCHAR(80),
 descricao VARCHAR(600),
 data_alterada DATE,
 id_funcionario INT
);

CREATE TABLE CHAMADO (
 ID_CHAMADO INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_Chamado PRIMARY KEY,
 DESCRICAO_CHAMADO VARCHAR(512),
 STATUS CHAR(20),
 DATA_ABERTURA DATE,
 TIPO_SOLICITACAO VARCHAR(80),
 ID_FUNC INT,

FOREIGN KEY (ID_FUNC) REFERENCES FUNCIONARIO(ID_FUNC)
);

CREATE TABLE FEED_NOTICIAS (
ID_FEED INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_Feed_noticias PRIMARY KEY,
 DESCRICAO_FEED VARCHAR(512),
 DATA_POST DATE,
 TITULO_FEED VARCHAR(150),
ID_FUNC INT,
FOREIGN KEY (ID_FUNC) REFERENCES FUNCIONARIO(ID_FUNC)
);


CREATE TABLE TRATATIVA_CHAMADO (
 ID_TRATATIVA INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_Tratativa_chamado PRIMARY KEY,
 DATA_FECHAMENTO DATE,
 DESCRICAO_TRATATIVA VARCHAR(512),
 ID_CHAMADO INT,
 ID_FUNC INT,
FOREIGN KEY (ID_CHAMADO) REFERENCES CHAMADO(ID_CHAMADO),
FOREIGN KEY (ID_FUNC) REFERENCES FUNCIONARIO(ID_FUNC)
);

CREATE TABLE ENTRADA(
    ID_ENTRADA INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_ENTRADA PRIMARY KEY,
    DESCRICAO VARCHAR(100),
    DATA_ENTRADA DATE,
    QTD FLOAT,
    ID_PRODUTO INT NOT NULL,
    ID_FUNCIONARIO INT NOT NULL,
    ID_FILIAL INT NOT NULL,
    FOREIGN KEY(ID_PRODUTO) REFERENCES PRODUTO(ID_PROD),
    FOREIGN KEY(ID_FUNCIONARIO) REFERENCES FUNCIONARIO(ID_FUNC),
    FOREIGN KEY(ID_FILIAL) REFERENCES FILIAL(ID_FILIAL)
);

CREATE TABLE SAIDA(
    ID_SAIDA INT NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1) CONSTRAINT PK_SAIDA PRIMARY KEY,
    DESCRICAO VARCHAR(100),
    DATA_ENTRADA DATE,
    QTD FLOAT,
    ID_PRODUTO INT NOT NULL,
    ID_FUNCIONARIO INT NOT NULL,
    ID_FILIAL INT NOT NULL,
    FOREIGN KEY(ID_PRODUTO) REFERENCES PRODUTO(ID_PROD),
    FOREIGN KEY(ID_FUNCIONARIO) REFERENCES FUNCIONARIO(ID_FUNC),
    FOREIGN KEY(ID_FILIAL) REFERENCES FILIAL(ID_FILIAL)
);

INSERT INTO FILIAL(NOME_FILIAL,CIDADE_FILIAL,TEL_FIFLIAL,CEP_FILIAL,ESTADO_FILIAL)
    VALUES('5ABLACK MATRIZ', 'SAO-PAULO', '(99)9999-9999', '150555-557', 'SP');

INSERT INTO FILIAL(NOME_FILIAL,CIDADE_FILIAL,TEL_FIFLIAL,CEP_FILIAL,ESTADO_FILIAL)
    VALUES('5ABLACK FILIA 01', 'SANTOS', '(64)7777-9999', '125633-654', 'SP');

INSERT INTO FILIAL(NOME_FILIAL,CIDADE_FILIAL,TEL_FIFLIAL,CEP_FILIAL,ESTADO_FILIAL)
    VALUES('5ABLACK FILIA 02', 'MINAS-GERAIS', '(29)5555-9999', '58666-557', 'MG');

INSERT INTO FILIAL(NOME_FILIAL,CIDADE_FILIAL,TEL_FIFLIAL,CEP_FILIAL,ESTADO_FILIAL)
    VALUES('5ABLACK FILIA 03', 'RIO-DE-JANEIRO', '(21)4444-9999', '19995-557', 'RJ');

INSERT INTO SERVICO(NOME_SERV,TIPO_SERV,VALOR_SERV,PRAZO_SERV)
    VALUES('LAVAGEM A SECO', 'LAVAGEM', 20, 3);

INSERT INTO TIPO_PRODUTO(NOME_PROD,DESCRICAO_PROD)
    VALUES('SABAO', 'LAVAGEM E TIRA MANCHAS');

INSERT INTO FUNCIONARIO(CPF_FUNC, NOME_FUNC, SENHA_FUNC, CARGO_FUNC, SALT)
    VALUES('123123', 'ADMIM', 'eb3eae884d3412c15be49452e518e6c5a56b059', 'ADMIM', '48737d31978212a');



INSERT INTO TIPO_PRODUTO(NOME_PROD,DESCRICAO_PROD);
 VALUES('SABAO EM PO', 'Uma poderosa fórmula desenvolvida pela Brilhante que tira manchas de suas roupas brancas ou coloridas sem danificá-las. Remove manchas como café, vinho, suco de uva, chá preto e chocolate.');

INSERT INTO TIPO_PRODUTO(NOME_PROD,DESCRICAO_PROD)
 VALUES('SABAO EM PEDRA', UPPER('quím substância detergente, obtida pela mistura de sais de sódio e de potássio com ácidos graxos, e us. com água para lavagem.'));

INSERT INTO TIPO_PRODUTO(NOME_PROD,DESCRICAO_PROD)
 VALUES('ALVEJANTE SEM CLORO', UPPER('Alvejante sem Cloro Vantage possui ação do oxigênio ativo, que garante eficiência no combate a manchas e sujeiras. Por ser um alvejante sem cloro, pode ser usado com total segurança em roupas brancas, coloridas e em diversos tecidos, na pré-lavagem ou na máquina.'));

INSERT INTO TIPO_PRODUTO(NOME_PROD,DESCRICAO_PROD)
 VALUES('ALVEJANTE COMUM', UPPER('Maior poder de limpeza e alvejamento, devido aos polímeros especiais e Enzimas (Protease, Amilase Celulase);
Baixa agressividade ao tecido, aumentando o tempo de vida útil da roupa, removendo sujeiras pesadas;
Ótima relação de benéfico e custo;
Fácil fabricação e manipulação das matérias primas;
Produto ecologicamente correto (isento de fosfatos que causam a eutrofização);'));


INSERT INTO TIPO_PRODUTO(NOME_PROD,DESCRICAO_PROD)
 VALUES(ALVEJANTE EM PO', UPPER('Maior poder de limpeza e alvejamento, devido aos polímeros especiais e Enzimas (Protease, Amilase Celulase);
Baixa agressividade ao tecido, aumentando o tempo de vida útil da roupa, removendo sujeiras pesadas;
Ótima relação de benéfico e custo;
Fácil fabricação e manipulação das matérias primas;
Produto ecologicamente correto (isento de fosfatos que causam a eutrofização);'));

SELECT  DATA_FECHAMENTO, DESCRICAO_TRATATIVA, CHAMADO.STATUS, TRATATIVA_CHAMADO.ID_CHAMADO, CHAMADO.TIPO_SOLICITACAO, FUNCIONARIO.NOME_FUNC, FUNCIONARIO.EMAIL_FUNC FROM TRATATIVA_CHAMADO
INNER JOIN FUNCIONARIO ON FUNCIONARIO.ID_FUNC = TRATATIVA_CHAMADO.ID_FUNC
INNER JOIN CHAMADO ON CHAMADO.ID_CHAMADO = TRATATIVA_CHAMADO.ID_CHAMADO;


INSERT INTO FILIAL(NOME_FILIAL,CIDADE_FILIAL,TEL_FIFLIAL,CEP_FILIAL,ESTADO_FILIAL)
    VALUES('5ABLACK MATRIZ', 'SAO-PAULO', '(99)9999-9999', '150555-557', 'SP');

DELETE FROM FUNCIONARIO;

INSERT INTO FUNCIONARIO(CPF_FUNC, NOME_FUNC, SOBRENOME_FUNC, SEXO_FUNC, NASC_FUNC, TEL_FUNC,DATA_CONTRATACAO,CEL_FUNC,EMAIL_FUNC,STATUS_FUNC,SENHA_FUNC,CARGO_FUNC,SALARIO_FUNC,ID_FILIAL,SALT,DTCADASTRO_FUNC)
VALUES
('11110364202','Murilo', 'Oliveira Fernandes','M','1990-08-01', '99-9999-9999', DATE(CURRENT TIMESTAMP), '11-1111-1111', 'MURILOS.OLIVEIRA@OUTLOOK.COM','ATIVO','24689','Grente TI', 5000, 1, 'fff', DATE(CURRENT TIMESTAMP)),
('22948935148','Goncalves', 'almeida','M','1991-05-26', '77-7777-7777', DATE(CURRENT TIMESTAMP), '11-1111-1111', 'goncalves@GMAIL.COM','ATIVO','24689','atendente', 1500, 1, 'fff', DATE(CURRENT TIMESTAMP)),
('17154146400','MIQUEAS', 'B.SANTOS','M','1996-04-21', '99-9999-9999', DATE(CURRENT TIMESTAMP), '11-1111-1111', 'MIQUEAS.BSANTOS@GMAIL.COM','ATIVO','24689','Grente TI', 5000, 1, 'fff', DATE(CURRENT TIMESTAMP));

INSERT INTO ENDERECO_FUNCIONARIO(LOGRADOURO_FUNC, NUMERO_CLI, BAIRRO_FUNC, CIDADE_FUNC, ESTADO_FUNC, CEP_FUNC, ID_FUNC)
 VALUES
    ('Rua Segunda',100, 'Ananindeua-PA','SAO PAULO', 'SP', '67103-390', (SELECT ID_FUNC FROM FUNCIONARIO WHERE CPF_FUNC='11110364202')),
    ('Travessa Manoel', 50,'JARDIM DA ERVAS','Recife', 'PE', '67103-390', (SELECT ID_FUNC FROM FUNCIONARIO WHERE CPF_FUNC='22948935148')),
    ('Rua Segunda', 100,'JARDIM ICARAI','SAO PAULO', 'SP', '67103-390', (SELECT ID_FUNC FROM FUNCIONARIO WHERE CPF_FUNC='17154146400'));

INSERT INTO CLIENTE(CPF_CLIENTE, NOME_CLIENTE, SOBRENOME_CLIENTE, SEXO_CLIENTE, NASC_CLIENTE, TEL_CLIENTE, CEL_CLIENTE, EMAIL_CLIENTE, STATUS_CLIENTE, DTCADASTRO_CLIENTE)
VALUES 
('19205461547', 'Lucas', 'Melo Azevedo', 'M', '1990-08-01', '99-9999-9999', '99-89999-9999', 'LUCAS@OUTLOOK.COM', 'ATIVO', DATE(CURRENT TIMESTAMP)),
('83806510733', 'Brenda', 'Oliveira Correia', 'F', '1991-05-26', '11-8899-9999', '99-79999-9999', 'BRENDA@GMAIL.COM', 'ATIVO', DATE(CURRENT TIMESTAMP)),
('16766514388', 'TANIA', 'Correia Carvalho', 'F', '1995-04-02', '11-8899-9999', '11-91111-9999', 'TANIA@GMAIL.COM', 'ATIVO', DATE(CURRENT TIMESTAMP));

INSERT INTO ENDERECO_CLIENTE(LOGRADOURO_CLI, NUMERO_CLI, BAIRRO_CLI, CIDADE_CLI, ESTADO_CLI, CEP_CLI, ID_CLIENTE)
 VALUES
    ('Rua Segunda',100, 'JARDIM PRIMAVERA','SAO PAULO', 'SP', '67103-390', (SELECT ID_CLIENTE FROM CLIENTE WHERE CPF_CLIENTE='19205461547')),
    ('Travessa Manoel', 50,'JARDIM DA ERVAS','Recife', 'PE', '67103-390', (SELECT ID_CLIENTE FROM CLIENTE WHERE CPF_CLIENTE='83806510733')),
    ('Rua Segunda', 100,'GRAJAU','SAO PAULO', 'SP', '67103-390', (SELECT ID_CLIENTE FROM CLIENTE WHERE CPF_CLIENTE='16766514388'));


INSERT INTO PEDIDO(STATUS, DATA_ENTRADA, DATA_SAIDA, ID_FUNC, ID_CLIENTE, ID_FILIAL)
 VALUES
('ABERTO', DATE(CURRENT TIMESTAMP), DATE('2016-06-08'), (SELECT ID_FUNC FROM FUNCIONARIO WHERE CPF_FUNC='11110364202'), (SELECT ID_CLIENTE FROM CLIENTE WHERE CPF_CLIENTE='19205461547'), 1),
('ABERTO', DATE(CURRENT TIMESTAMP), DATE('2016-06-22'), (SELECT ID_FUNC FROM FUNCIONARIO WHERE CPF_FUNC='22948935148'), (SELECT ID_CLIENTE FROM CLIENTE WHERE CPF_CLIENTE='83806510733'), 1),
('ABERTO', DATE(CURRENT TIMESTAMP), DATE('2016-06-22'), (SELECT ID_FUNC FROM FUNCIONARIO WHERE CPF_FUNC='17154146400'), (SELECT ID_CLIENTE FROM CLIENTE WHERE CPF_CLIENTE='16766514388'), 1);

INSERT INTO PECA(QTD_PECA, TIPO_PECA, COR_PECA, TIPO_TECIDO, ID_PEDIDO, ID_SERVICO)
 VALUES
(1, 'CALÇA', 'PRETA', 'ALGODAO', 1 ,1,),
(1, 'CAMISA', 'ROSA', 'ALGODAO', 1, ,1,),
(1, 'SHORTS', 'AZUL', 'JEANNS', 1, ,1,),

(1, 'CALÇA', 'PRETA', 'ALGODAO', 2 ,1,),
(1, 'CAMISA', 'ROSA', 'ALGODAO', 2, ,1,),
(1, 'SHORTS', 'AZUL', 'JEANNS', 2, ,1,),

(1, 'CALÇA', 'PRETA', 'ALGODAO', 3 ,1,),
(1, 'CAMISA', 'ROSA', 'ALGODAO', 3, ,1,),
(1, 'SHORTS', 'AZUL', 'JEANNS', 3, ,1,)



*/