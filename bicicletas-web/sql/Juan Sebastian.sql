insert into App.ESTACIONENTITY(ID, NAME, CIUDAD, DIRECCION) values (200, 'Calle 127', 'Bogotá', 'Calle 127 #30' );
insert into App.RESERVAENTITY(ID, DESCUENTO, ESTACIONSALIDA_ID, ESTACIONLLEGADA, ESTADO, FECHAINICIO, FECHAENTREGA, FECHARESERVA, FECHAFINAL, PRECIOFINAL, USUARIORESERVA_DOCUMENTOUSUARIO) values (200, 1, 105, 104, 5, '2017-11-06 11:00:00.172', '2017-11-06 20:00:00.172', '2017-11-04 11:00:47.172', '2017-11-06 20:00:00.172', 15000, 3488291091);
insert into App.ACCESORIOBICICLETAENTITY(ID,DESCRIPCION,NAME,NOMBRE,BICI_ID) values(200,'negra','acc',2,200);
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO, ESTACION_ID) VALUES (200, 2, 'Merida', 'Carretera', 100);