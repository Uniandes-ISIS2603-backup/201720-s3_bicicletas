DELETE FROM APP.BICICLETAENTITY_ACCESORIOBICICLETAENTITY;
DELETE FROM APP.DIRECCIONENTITY_USUARIOENTITY;
DELETE FROM APP.ACCESORIOBICICLETAENTITY;
DELETE FROM APP.ACCESORIOENTITY;
DELETE FROM APP.BICICLETAENTITY;
DELETE FROM APP.CALIFICACIONENTITY;
DELETE FROM APP.DIRECCIONENTITY;
DELETE FROM APP.PAGOENTITY;
DELETE FROM APP.RESERVAENTITY;
DELETE FROM APP.ESTACIONENTITY;
DELETE FROM APP.PUNTOENTITY;
DELETE FROM APP.USUARIOENTITY;

/*Estaciones*/
insert into App.ESTACIONENTITY(ID, NAME, CIUDAD, DIRECCION) values (100, 'Portal el Dorado', 'Bogotá', 'Calle 40 #87-13' );
insert into App.ESTACIONENTITY(ID, NAME, CIUDAD, DIRECCION) values (101, 'Modelia', 'Bogotá', 'Carrera 74 #23-12' );
insert into App.ESTACIONENTITY(ID, NAME, CIUDAD, DIRECCION) values (102, 'CAN','Bogotá', 'Calle 35 #12-13' );
insert into App.ESTACIONENTITY(ID, NAME, CIUDAD, DIRECCION) values (103, 'Gobernación', 'Bogotá', 'Avenida 96 con calle 31' );
insert into App.ESTACIONENTITY(ID, NAME, CIUDAD, DIRECCION) values (104, 'Corferias', 'Bogotá', 'Carrera 89 #24-87' );
insert into App.ESTACIONENTITY(ID, NAME, CIUDAD, DIRECCION) values (105, 'Universidades', 'Bogotá', 'Avenida 24 #33-21');

/*Usuarios */
insert into App.USUARIOENTITY(DOCUMENTOUSUARIO, CONTRASENIAPSE, FECHANACIMIENTO, NOMBRE, NUMEROCSV, TARJETACREDITO, TIPOID, PASSWORD) values (4651451689,'bisonte','01/15/1990','Carlos Alba',413,12348890266785,1,'clave');
insert into App.USUARIOENTITY(DOCUMENTOUSUARIO, CONTRASENIAPSE, FECHANACIMIENTO, NOMBRE, NUMEROCSV, TARJETACREDITO, TIPOID, PASSWORD) values (4304710593,'negocio','02/16/1991','Diego Chacon',631,12342211909794,3,'clave');
insert into App.USUARIOENTITY(DOCUMENTOUSUARIO, CONTRASENIAPSE, FECHANACIMIENTO, NOMBRE, NUMEROCSV, TARJETACREDITO, TIPOID, PASSWORD) values (3488291091,'adentro','03/17/1992','Gabriel Pinto',844,12345760403010,1,'clave');
insert into App.USUARIOENTITY(DOCUMENTOUSUARIO, CONTRASENIAPSE, FECHANACIMIENTO, NOMBRE, NUMEROCSV, TARJETACREDITO, TIPOID, PASSWORD) values (5851578045,'puerto','04/18/1993','Kevin Babativa',204,43214480815558,3,'clave');
insert into App.USUARIOENTITY(DOCUMENTOUSUARIO, CONTRASENIAPSE, FECHANACIMIENTO, NOMBRE, NUMEROCSV, TARJETACREDITO, TIPOID, PASSWORD) values (6705190162,'etapas','05/19/1994','Sebastian Torres',921,43215010079699,1,'clave');
insert into App.USUARIOENTITY(DOCUMENTOUSUARIO, CONTRASENIAPSE, FECHANACIMIENTO, NOMBRE, NUMEROCSV, TARJETACREDITO, TIPOID, PASSWORD) values (7223897617,'aletear','06/30/1995','Juan Trujillo',143,43218928723470,3,'clave');
insert into App.USUARIOENTITY(DOCUMENTOUSUARIO, CONTRASENIAPSE, FECHANACIMIENTO, NOMBRE, NUMEROCSV, TARJETACREDITO, TIPOID, PASSWORD) values (1,'clave','06/30/1995','Administrador',111,43218928723470,3,'clave');


/*Bicicletas*/
/*100*/
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (100, 2, 'Merida', 'Carretera', 100);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (101, 2, 'Merida', 'Carretera', 100);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (102, 2, 'Giant', 'Montaña', 100);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (103, 2, 'Giant', 'Montaña', 100);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (104, 2, 'Felt', 'Triatlon', 100);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (105, 2, 'Felt', 'Triatlon', 100);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (106, 2, 'Focus', 'Urbanas', 100);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (107, 2, 'Focus', 'Urbanas', 100);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (108, 2, 'Orbea', 'Doble', 100);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (109, 2, 'Orbea', 'Doble', 100);

/*101*/
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (110, 2, 'Merida', 'Carretera', 101);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (111, 2, 'Merida', 'Carretera', 101);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (112, 2, 'Giant', 'Montaña', 101);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (113, 2, 'Giant', 'Montaña', 101);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (114, 2, 'Felt', 'Triatlon', 101);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (115, 2, 'Felt', 'Triatlon', 101);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (116, 2, 'Focus', 'Urbanas', 101);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (117, 2, 'Focus', 'Urbanas', 101);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (118, 2, 'Orbea', 'Doble', 101);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (119, 2, 'Orbea', 'Doble', 101);

/*102*/
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (120, 2, 'Merida', 'Carretera', 102);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (121, 2, 'Merida', 'Carretera', 102);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (122, 2, 'Giant', 'Montaña', 102);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (123, 2, 'Giant', 'Montaña', 102);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (124, 2, 'Felt', 'Triatlon', 102);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (125, 2, 'Felt', 'Triatlon', 102);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (126, 2, 'Focus', 'Urbanas', 102);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (127, 2, 'Focus', 'Urbanas', 102);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (128, 2, 'Orbea', 'Doble', 102);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (129, 2, 'Orbea', 'Doble', 102);

/*103*/
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (130, 2, 'Merida', 'Carretera', 103);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (131, 2, 'Merida', 'Carretera', 103);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (132, 2, 'Giant', 'Montaña', 103);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (133, 2, 'Giant', 'Montaña', 103);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (134, 2, 'Felt', 'Triatlon', 103);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (135, 2, 'Felt', 'Triatlon', 103);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (136, 2, 'Focus', 'Urbanas', 103);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (137, 2, 'Focus', 'Urbanas', 103);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (138, 2, 'Orbea', 'Doble', 103);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (139, 2, 'Orbea', 'Doble', 103);

/*104*/
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (140, 2, 'Merida', 'Carretera', 104);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (141, 2, 'Merida', 'Carretera', 104);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (142, 2, 'Giant', 'Montaña', 104);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (143, 2, 'Giant', 'Montaña', 104);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (144, 2, 'Felt', 'Triatlon', 104);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (145, 2, 'Felt', 'Triatlon', 104);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (146, 2, 'Focus', 'Urbanas', 104);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (147, 2, 'Focus', 'Urbanas', 104);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (148, 2, 'Orbea', 'Doble', 104);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (149, 2, 'Orbea', 'Doble', 104);

/*105*/
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (150, 2, 'Merida', 'Carretera', 105);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (151, 2, 'Merida', 'Carretera', 105);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (152, 2, 'Giant', 'Montaña', 105);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (153, 2, 'Giant', 'Montaña', 105);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (154, 2, 'Felt', 'Triatlon', 105);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (155, 2, 'Felt', 'Triatlon', 105);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (156, 2, 'Focus', 'Urbanas', 105);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (157, 2, 'Focus', 'Urbanas', 105);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (158, 2, 'Orbea', 'Doble', 105);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (159, 2, 'Orbea', 'Doble', 105);

/*Accesorios*/
/*100*/
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (100, 0, 2, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (101, 0, 2, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (102, 0, 2, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (103, 0, 2, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (104, 0, 2, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (105, 0, 2, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (106, 0, 2, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (107, 0, 2, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (108, 0, 2, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (109, 0, 2, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (110, 0, 1, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (111, 0, 1, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (112, 0, 1, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (113, 0, 1, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (114, 0, 1, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (115, 0, 1, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (116, 0, 1, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (117, 0, 1, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (118, 0, 1, 100);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (119, 0, 1, 100);

/*101*/
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (120, 0, 2, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (121, 0, 2, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (122, 0, 2, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (123, 0, 2, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (124, 0, 2, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (125, 0, 2, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (126, 0, 2, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (127, 0, 2, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (128, 0, 2, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (129, 0, 2, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (130, 0, 1, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (131, 0, 1, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (132, 0, 1, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (133, 0, 1, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (134, 0, 1, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (135, 0, 1, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (136, 0, 1, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (137, 0, 1, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (138, 0, 1, 101);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (139, 0, 1, 101);

/*102*/
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (140, 0, 2, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (141, 0, 2, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (142, 0, 2, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (143, 0, 2, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (144, 0, 2, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (145, 0, 2, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (146, 0, 2, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (147, 0, 2, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (148, 0, 2, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (149, 0, 2, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (150, 0, 1, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (151, 0, 1, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (152, 0, 1, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (153, 0, 1, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (154, 0, 1, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (155, 0, 1, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (156, 0, 1, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (157, 0, 1, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (158, 0, 1, 102);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (159, 0, 1, 102);

/*103*/
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (160, 0, 2, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (161, 0, 2, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (162, 0, 2, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (163, 0, 2, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (164, 0, 2, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (165, 0, 2, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (166, 0, 2, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (167, 0, 2, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (168, 0, 2, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (169, 0, 2, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (170, 0, 1, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (171, 0, 1, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (172, 0, 1, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (173, 0, 1, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (174, 0, 1, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (175, 0, 1, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (176, 0, 1, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (177, 0, 1, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (178, 0, 1, 103);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (179, 0, 1, 103);

/*104*/
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (180, 0, 2, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (181, 0, 2, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (182, 0, 2, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (183, 0, 2, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (184, 0, 2, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (185, 0, 2, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (186, 0, 2, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (187, 0, 2, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (188, 0, 2, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (189, 0, 2, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (190, 0, 1, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (191, 0, 1, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (192, 0, 1, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (193, 0, 1, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (194, 0, 1, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (195, 0, 1, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (196, 0, 1, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (197, 0, 1, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (198, 0, 1, 104);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (199, 0, 1, 104);

/*105*/
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (200, 0, 2, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (201, 0, 2, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (202, 0, 2, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (203, 0, 2, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (204, 0, 2, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (205, 0, 2, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (206, 0, 2, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (207, 0, 2, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (208, 0, 2, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (209, 0, 2, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (210, 0, 1, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (211, 0, 1, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (212, 0, 1, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (213, 0, 1, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (214, 0, 1, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (215, 0, 1, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (216, 0, 1, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (217, 0, 1, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (218, 0, 1, 105);
insert into App.ACCESORIOENTITY(ID, RESERVADO, TIPO, ESTACION_ID) values (219, 0, 1, 105); 


/*Puntos*/
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (100, '2017-01-04 20:59:47.172','2018-01-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (101, '2017-02-04 20:59:47.172','2018-02-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (102, '2017-03-04 20:59:47.172','2018-03-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (103, '2017-04-04 20:59:47.172','2018-04-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (104, '2017-05-04 20:59:47.172','2018-05-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (105, '2017-06-04 20:59:47.172','2018-06-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (106, '2017-07-04 20:59:47.172','2018-07-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (107, '2017-08-04 20:59:47.172','2018-08-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (108, '2017-09-04 20:59:47.172','2018-09-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (109, '2017-10-04 20:59:47.172','2018-10-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (110, '2017-11-04 20:59:47.172','2018-11-04 20:59:47.172', 3488291091);  
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (111, '2017-11-05 20:59:47.172','2018-11-05 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (112, '2017-11-06 20:59:47.172','2018-11-06 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (113, '2017-11-07 20:59:47.172','2018-11-07 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (114, '2017-11-08 20:59:47.172','2018-11-08 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (115, '2017-11-09 20:59:47.172','2018-11-09 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (116, '2017-11-10 20:59:47.172','2018-11-10 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (117, '2017-11-11 20:59:47.172','2018-11-11 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (118, '2017-11-12 20:59:47.172','2018-11-12 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (119, '2017-11-13 20:59:47.172','2018-11-13 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (120, '2017-11-14 20:59:47.172','2018-11-14 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (121, '2017-11-15 20:59:47.172','2018-11-15 20:59:47.172', 3488291091);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, FECHAVENCIMIENTO, USUARIOPUNTO_DOCUMENTOUSUARIO) values (122, '2017-11-16 20:59:47.172','2018-11-16 20:59:47.172', 3488291091);


/*Reservas*/

/*Gabriel*/
insert into App.RESERVAENTITY(ID, DESCUENTO, ESTACIONSALIDA_ID, ESTACIONLLEGADA, ESTADO, FECHAINICIO, FECHAENTREGA, FECHARESERVA, FECHAFINAL, PRECIOFINAL, USUARIORESERVA_DOCUMENTOUSUARIO) values (100, 1, 105, 104, 5, '2017-11-06 11:00:00.172', '2017-11-06 20:00:00.172', '2017-11-04 11:00:47.172', '2017-11-06 20:00:00.172', 15000, 3488291091);
insert into App.RESERVAENTITY(ID, DESCUENTO, ESTACIONSALIDA_ID, ESTACIONLLEGADA, ESTADO, FECHAINICIO, FECHAENTREGA, FECHARESERVA, FECHAFINAL, PRECIOFINAL, USUARIORESERVA_DOCUMENTOUSUARIO) values (101, 1, 103, 102, 5, '2017-11-07 11:00:00.172', '2017-11-07 20:00:00.172', '2017-11-05 12:00:47.172', '2017-11-07 20:00:00.172', 20000, 4651451689);
/*Cierre Gabriel*/ 

/*Calificaciones*/
insert into App.CALIFICACIONENTITY(ID, DESCRIPCION, FECHACALI, NOTA, ESTACION_ID, RESERVA_ID) values (100, 'Excelente estación, bastante amplia', '2017-11-06 20:59:47.172', 5, 105, 100);
insert into App.CALIFICACIONENTITY(ID, DESCRIPCION, FECHACALI, NOTA, ESTACION_ID, RESERVA_ID) values (101, 'Es muy fría y se me hizo un poco insegura', '2017-11-06 21:30:47.172', 2, 104, 100);



