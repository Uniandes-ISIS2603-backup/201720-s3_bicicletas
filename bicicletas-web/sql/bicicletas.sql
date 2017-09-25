delete from UsuarioEntity_ReservaEntity;
delete from EstacionEntity_ReservaEntity;
delete from EstacionEntity_CalificacionEntity;
delete from CalificacionEntity;
delete from UsuarioEntity_PuntoEntity;
delete from PuntoEntity;
delete from ReservaEntity;
delete from BicicletaEntity;
delete from UsuarioEntity;
delete from EstacionEntity;
delete from DireccionEntity;

/*No modificar esto - Gabriel Pinto*/
insert into App.DireccionEntity(ID, CIUDAD, CODIGOPOSTAL, DESCRIPCION) values (1, 'Bogota', 11001, 'Direccion 1');
insert into App.DireccionEntity(ID, CIUDAD, CODIGOPOSTAL, DESCRIPCION) values (2, 'Bogota', 11011, 'Direccion 2');

insert into App.USUARIOENTITY(ID, DOCUMENTOUSUARIO, FECHANACIMIENTO, NAME, TIPOID) values (1, 123456789, '09/14/1990', 'Usuario 1', 1);
insert into App.USUARIOENTITY(ID, DOCUMENTOUSUARIO, FECHANACIMIENTO, NAME, TIPOID) values (2, 223456789, '10/14/1997', 'Usuario 2', 1);

insert into App.ESTACIONENTITY(ID,NAME ) values (1, 'Estacion 1');
insert into App.ESTACIONENTITY(ID, NAME) values (2, 'Estacion 2');

insert into App.PUNTOENTITY(ID, FECHAPUNTO, USUARIOPUNTO_ID) values (1, '09/24/2017', 1);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, USUARIOPUNTO_ID) values (2, '09/24/2017', 1);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, USUARIOPUNTO_ID) values (3, '09/24/2017', 1);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, USUARIOPUNTO_ID) values (4, '09/24/2017', 1);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, USUARIOPUNTO_ID) values (5, '09/24/2017', 1);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, USUARIOPUNTO_ID) values (6, '09/24/2017', 1);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, USUARIOPUNTO_ID) values (7, '09/24/2017', 1);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, USUARIOPUNTO_ID) values (8, '09/24/2017', 1);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, USUARIOPUNTO_ID) values (9, '09/24/2017', 1);
insert into App.PUNTOENTITY(ID, FECHAPUNTO, USUARIOPUNTO_ID) values (10, '09/24/2017', 1);

insert into App.USUARIOENTITY_PUNTOENTITY(USUARIOENTITY_ID, PUNTOS_ID) values (1,1);
insert into App.USUARIOENTITY_PUNTOENTITY(USUARIOENTITY_ID, PUNTOS_ID) values (1,2);
insert into App.USUARIOENTITY_PUNTOENTITY(USUARIOENTITY_ID, PUNTOS_ID) values (1,3);
insert into App.USUARIOENTITY_PUNTOENTITY(USUARIOENTITY_ID, PUNTOS_ID) values (1,4);
insert into App.USUARIOENTITY_PUNTOENTITY(USUARIOENTITY_ID, PUNTOS_ID) values (1,5);
insert into App.USUARIOENTITY_PUNTOENTITY(USUARIOENTITY_ID, PUNTOS_ID) values (1,6);
insert into App.USUARIOENTITY_PUNTOENTITY(USUARIOENTITY_ID, PUNTOS_ID) values (1,7);
insert into App.USUARIOENTITY_PUNTOENTITY(USUARIOENTITY_ID, PUNTOS_ID) values (1,8);
insert into App.USUARIOENTITY_PUNTOENTITY(USUARIOENTITY_ID, PUNTOS_ID) values (1,9);
insert into App.USUARIOENTITY_PUNTOENTITY(USUARIOENTITY_ID, PUNTOS_ID) values (1,10);

insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO) values (1, 2, 'Marca 1', 'Modelo 1');
insert into App.BICICLETAENTITY(ID, ESTADO, MARCA, MODELO) values (2, 2, 'Marca 2', 'Modelo 2');

insert into App.RESERVAENTITY(ID, FECHAINICIO, ESTADO) values(1, '09/20/2017',1);
insert into App.RESERVAENTITY(ID, FECHAINICIO, FECHAENTREGA, PRECIOFINAL, ESTADO ) values (2, '09/22/2017', '09/24/2017', 20.000, 0);

insert into App.USUARIOENTITY_RESERVAENTITY(USUARIOENTITY_ID, RESERVAS_ID) values (1, 1);
insert into App.USUARIOENTITY_RESERVAENTITY(USUARIOENTITY_ID, RESERVAS_ID) values (2,2);

insert into App.ESTACIONENTITY_RESERVAENTITY(ESTACIONENTITY_ID, RESERVAS_ID) values (1,1);

insert into App.CALIFICACIONENTITY(ID, DESCRIPCION, FECHACALI, IDESTACION, IDUSUARIO, NOTA, RESERVA_ID) values (1, 'Excelente', '09/24/2017', 1, 1, 5, 1);

insert into App.ESTACIONENTITY_CALIFICACIONENTITY(ESTACIONENTITY_ID, CALIFICACIONES_ID) values (1,1);
/*Agregar desde acá si quieren*/
