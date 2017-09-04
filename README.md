# s3_bicicletas
# Tabla de contenidos
-[Introducción](#introducción)
-[API](#api-de-la-aplicación-bicicletas)
 - [Recurso Usuario](#recurso-usuario)
  - [GET /usuarios](#GET-/usuarios)
  - [GET /usuarios/{id}](#GET-/usuarios/{id})
  - [GET /usuarios/{id}/direcciones](#GET-/usuarios/{id}/direcciones)
  - [POST /usuarios](#POST-/usuarios)
  - [POST /usuarios/{id}/direcciones](#POST-/usuarios/{id}/direcciones)
  - [PUT /usuarios/{id}](#PUT-/usuarios/{id})
  - [PUT /usuarios/{id}/direcciones](#PUT-/usuarios/{id}/direcciones)
  - [DELETE /usuarios](#DELETE-/usuarios)
  - [DELETE /usuarios/{id}](#DELETE-/usuarios/{id})
  - [DELETE /usuarios/{id}/direcciones](#DELETE-/usuarios/{id}/direcciones)
  - [DELETE /usuarios/{id}/direcciones/{id}](#DELETE-/usuarios/{id}/direcciones/{id})
 - [Recurso Direccion](#recurso-direccion)
  - [GET /direcciones](#GET-/direcciones)
  - [GET /direcciones/{id}](#GET-direcciones/{id})
  - [POST /direcciones](#POST-/direcciones)
  - [PUT /direcciones/{id}](#PUT-/direcciones/{id})
  - [DELETE /direcciones](#DELETE-/direcciones)
  - [DELETE /direcciones/{id}](#DELETE-/direcciones/{id})
 - [Recurso Punto](#recurso-punto)
  - [POST /usuarios/{idUsuario}/puntos](#POST-/usuarios/{idUsuario}/puntos)
  - [GET /usuarios/{idUsuario}/puntos](#GET-/usuarios/{idUsuario}/puntos)
  - [DELETE /usuarios/{idUsuario}](#GET-/usuarios/{idUsuario}/puntos)
  - [Recurso Calificacion](#recurso-calificacion)
  - [POST /reservas/{idReserva}/calificacionEstacionLlegada](#POST-/reservas/{idReserva}/calificacionEstacionLlegada)
  - [POST /reservas/{idReserva}/calificacionEstacionOrigen](#POST-/reservas/{idReserva}/calificacionEstacionOrigen)
  - [GET /reservas/{idReserva}/calificacionEstacionLlegada](#GET-/reservas/{idReserva}/calificacionEstacionLlegada)
  - [GET /reservas/{idReserva}/calificacionEstacionOrigen](#GET-/reservas/{idReserva}/calificacionEstacionOrigen)
  - [GET /estaciones/{idEstacion}/calificaciones](#GET-/estaciones/{idEstacion}/calificaciones)
  - [GET /estaciones/{idEstacion}/calificaciones/{idCalificacion}](#GET-/estaciones/{idEstacion}/calificaciones/{idCalificacion})
 - [Recurso Estacion](#recurso-estacion)
  - [GET /estaciones](#GET-/estaciones)
  - [GET /estaciones/{id}](#GET-/estaciones/{id})
  - [POST /estaciones](#POST-/estaciones)
  - [PUT /estaciones/{id}](#PUT-/estaciones/{id})
  - [DELETE /estaciones/{id}](#DELETE-/estaciones/{id})    
 - [Recurso Accesorio](#recurso-accesorio)
  - [GET estaciones/{estacionesid}/accesorios/](#GET-estaciones/{estacionesid}/accesorios/)
  - [GET estaciones/{estacionesid}/accesorios/{id}](#GET-estaciones/{estacionesid}/accesorios/{id})
  - [POST /estaciones{estacionesid}/accesorios/](#POST-/estaciones/{estacionesid}/accesorios/)
  - [PUT /estaciones{estacionesid}/accesorios/{id}](#PUT-/estaciones/{estacionesid}/accesorios/{id})
  - [DELETE /estaciones/{id}/accesorios/{id}](#DELETE-/estaciones/{id}/accesorios/{id})
 - [Recurso SistemaDePagos](#recurso-sistemaDePagos)
  - [GET /SistemaDePagos/{id}](#GET-/SistemaDePagos/{id})
 - [Recurso Pago](#recurso-pago)
  - [GET /reservas/{id}/pago](#GET-/reservas/{id}/pago)
  - [POST /reservas/{id}/pago](#GET-/reservas/{id}/pago)
  - [PUT /reservas/{id}/pago](#GET-/reservas/{id}/pago)
  - [DELETE /reservas/{id}/pago](#GET-/reservas/{id}/pago)
 - [Recurso Bicicleta](#recurso-bicicleta)
  - [GET /bicicletas](#GET-/bicicletas)
  - [GET /reservas/{id}/bicicletas](#GET-/bicicletas/{id})
  - [GET /bicicletas/{id}](#GET-/bicicletas)
  - [POST /bicicletas](#POST-/bicicletas)
  - [PUT /bicicletas/{id}](#PUT-/bicicletas/{id})
  - [DELETE /bicicletas/{id}](#DELETE-/bicicletas/{id})
  - [DELETE/bicicletas/bicisDañadas](#DELETE-/bicicletas/bicicsDañadas)
  - [GET/reservas/bicicletas/bicisSinReserva](#GET-/reservas/bicicletas/bicisSinReserva)
 - [Recurso AccesorioBicicleta](#recurso-AccesorioBicicleta)
  - [GET /bicicletas/accesoriosBicicleta](#GET-/AccesoriosBicicleta)
  - [GET /reservas/{id}/bicicletas/accesoriosBicicleta](#GET-/AccesorioBicicleta/{id})
  - [GET /bicicletas/{id}/AccesorioBicicleta{id}](#GET-/AccesoriosBicicleta)
  - [POST /bicicletas/AccesorioBicicleta](#POST-/AccesorioBicicleta)
  - [PUT /bicicletas/{id}/AccesorioBicicleta/{id}](#PUT-bicicletas/{id}/AccesorioBicicleta/{id})
  - [DELETE /bicicletas/{id}/AccesorioBicicleta{id}](#DELETE-/bicicletas/{id}/AccesorioBicicleta)
  - [DELETE/bicicletas/bicisDañadas/AccesoriosBicicleta](#DELETE-/bicicletas/bicicsDañadas/AccesorioBicicleta)
  - [GET/reservas/bicicletas/bicisSinReserva/AccesorioBicicleta](#GET-/reservas/bicicletas/bicisSinReserva/AccesoriosBicicleta)
 -[Recurso Reserva](#recuso-reserva)
  - [GET /estaciones/{idEstacion}/Reservas](#GET-/estaciones/{idEstacion}/Reservas)
  - [GET /estaciones/{idEstacion}/Reserva/{idReserva}](#GET-/estaciones/{idEstacion}/Reserva/{idReserva})
  - [POST /estaciones/{idEstacion}/Reserva/{IdUsuario}](#POST-/estaciones/{idEstacion}/Reserva/{IdUsuario})
  - [PUT /estaciones/{idEstacion}/Reserva/{idReserva}](#PUT-/estaciones/{idEstacion}/Reserva/{idReserva})
  - [DELETE /estaciones/{idEstacion}/Reservas/](#DELETE-/estaciones/{idEstacion}/Reservas/)
  - [DELETE /estaciones/{idEstacion}/Reservas/{idReserva}](#DELETE-/estaciones/{idEstacion}/Reservas/{idReserva})
  - [Recurso Información estación ](#recuso-reserva)
  - [GET /estaciones/{idEstacion}/informacion](#GET-/estaciones/{idEstacion}/Informacion)
  - [POST /estaciones/{idEstacion}](#POST-/estaciones/{idEstacion})
  - [PUT /estaciones/{idEstacion}](#PUT-/estaciones/{idEstacion}/Reserva/{idReserva})
  - [DELETE /estaciones/{idEstacion}](#DELETE-/estaciones/{idEstacion}/Reservas)
# API Rest
## Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /prestamoBicicletas.api/api/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor en el header `X-Total-Count` y los registros se envían en el cuerpo del mensaje como un arreglo.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
[{}, {}, {}, {}, {}, {}]
```

## API de la aplicación bicicletas
### Recurso Usuario
El objeto Usuario tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    cedula: '' /*Tipo Int*/,
    idUsuario: '' /*Tipo Long*/,
    pasaporte: '' /*Tipo Int*/,
    puntos: '' /*Tipo Int*/,
    tarjetaIdentidad: '' /*Tipo Int*/,
    tipoId: '' /*Tipo Int*/
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
   {
    idUsuario: '' /*Tipo Long*/,
    pasaporte: '' /*Tipo Int*/    }
}
```



#### GET/ usuarios

Retorna una colección de objetos Usuario en representación Detail.
Cada Usuario en la colección tiene embebidos los siguientes objetos: Direccion.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-usuario)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### GET /usuarios/{id}

Retorna una colección de objetos Usuario en representación Detail.
Cada Usuario en la colección tiene los siguientes objetos: Direccion.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Usuario a consultar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Usuario en [representaciones Detail](#recurso-usuario)
404|No existe un objeto Usuario con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### GET /usuarios/{id}/direcciones

Retorna una colección de objetos Direccion en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Usuario a consultar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto usuario en [representaciones Detail](#recurso-usuario)
404|No existe un objeto Usuariocon el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### POST /usuarios

Es el encargado de crear objetos Usuario.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Usuario que será creado|Sí|[Representación Detail](#recurso-usuario)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto usuario ha sido creado|[Representación Detail](#recurso-usuario)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Usuario|Mensaje de error

#### POST /usuarios/{id}/direcciones

Es el encargado de crear objetos Direccion del usuario espefico.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Direccion que será creado|Sí|[Representación Detail](#recurso-direccion)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto direccion ha sido creado|[Representación Detail](#recurso-direccion)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Usuario|Mensaje de error

#### PUT /usuarios/{id}

Es el encargado de actualizar objetos Usuario.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Usuario a actualizar|Sí|Long
body|body|Objeto Usuario nuevo|Sí|[Representación Detail](#recurso-usuario)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Usuario actualizado|[Representación Detail](#recurso-usuario)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Usuario|Mensaje de error

#### PUT /usuarios/{id}/direcciones

Es el encargado de actualizar objetos Direcciones del usuario especifico.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Direccion a actualizar|Sí|Long
body|body|Objeto Direccion nuevo|Sí|[Representación Detail](#recurso-direccion )

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Direccion actualizado|[Representación Detail](#recurso-direccion )
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Direccion|Mensaje de error

#### DELETE /usuarios

Elimina todos los objetos Usuario.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### DELETE /usuarios/{id}

Elimina un objeto Usuario.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Usuario a eliminar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error


#### DELETE/ usuarios/{id}/direcciones

Elimina todos los objetos Direccion de un usuario especifico.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Usuario a eliminar sus direcciones|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### DELETE /usuarios/{id}/direcciones/{id}

Elimina un objeto Direccion de un usuario especifico.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Usuario|Sí|Long
id|Path|ID del objeto Direccion a eliminar|Sí|Int

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

[Volver arriba](#tabla-de-contenidos)


### Recurso Direccion
El objeto Direccion tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    codigoPostal: '' /*Tipo Int*/,
    descripcion: '' /*Tipo String*/,
    idDireccion: '' /*Tipo Int*/,
    nombre: '' /*Tipo String*/,
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
  {
    idDireccion: '' /*Tipo Int*/,
    descripcion: '' /*Tipo string*/    }
}
```



#### GET/ direcciones

Retorna una colección de objetos Direccion en representación Detail.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-direccion)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### GET /direcciones/{id}

Retorna una colección de objetos Direccion en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Direccion a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Direccion en [representaciones Detail](#recurso-direccion)
404|No existe un objeto Direccion con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### POST /direcciones

Es el encargado de crear objetos Direccion.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Direccion que será creado|Sí|[Representación Detail](#recurso-direccion)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Direccion ha sido creado|[Representación Detail](#recurso-direccion)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Direccion|Mensaje de error

#### PUT /direcciones/{id}

Es el encargado de actualizar objetos Direccion.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Direccion a actualizar|Sí|Integer
body|body|Objeto Direccionnuevo|Sí|[Representación Detail](#recurso-direccion)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Direccion actualizado|[Representación Detail](#recurso-direccion)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Direccion|Mensaje de error

#### DELETE /direcciones

Elimina todos los objetos Direccion.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### DELETE /direcciones/{id}

Elimina un objeto Direccion.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Direccion a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

[Volver arriba](#tabla-de-contenidos)

### Recurso Punto
El objeto Punto tiene 2 representaciones JSON:

#### Representación Minimun
```javascript
{
    fechaPunto: " /*Tipo Date*/,
    idPunto: '' /*Tipo Long*/,
}
```
#### Representación Detail
```javascript
{
    // Todo lo de la representación Minimum más los objetos Minimum con relación simple.
    estacion: {
    direccion: '' /*Tipo String*/,
    idEstacion: '' /*Tipo Long*/
    nombreEstacion: " /Tipo String }
}
```

#### POST /usuarios/{idUsuario}/puntos

Asocia un objeto Punto a un objeto Usuario.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idUsuario|Path|ID del objeto Usuario al cual se le asociará el objeto Punto|Sí|Integer
body|body|Objeto Punto que será creado|Sí|[Representación Detail](#recurso-punto)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|Objeto Punto ha sido creado y asociado|[Representación Detail de Punto](#recurso-punto)
500|No se pudo crear o asociar el objeto Punto|Mensaje de error


#### GET  /usuarios/{idUsuario}/puntos

Retorna una colección de objetos Punto asociados a un objeto Usuario en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idUsuario|Path|ID del objeto Usuario a consultar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Punto en [representación Detail](#punto-category)
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error consultando puntos |Mensaje de error


#### DELETE usuarios/{idUsuario}/puntos

Remueve todos los objetos Punto de la colección en un objeto Usuario.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idUsuiaro|Path|ID del objeto Usuario asociado al objeto Punto|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

[Volver arriba](#tabla-de-contenidos)

### Recurso Calificacion
El objeto Calificacion tiene 2 representaciones JSON:

#### Representación Minimun
```javascript
{
    description: '' /*Tipo String*/,
    fechaCali: " /*Tipo Date*/,
    idCalificacion: '' /*Tipo Long*/,
    idUsuario: '' /*Tipo Long*/,
    nota: '' /*Tipo Integer*/,
}
```
#### Representación Detail
```javascript
{
    // Todo lo de la representación Minimum más los objetos Minimum con relación simple.
    estacion: {
    direccion: '' /*Tipo String*/,
    idEstacion: '' /*Tipo Long*/
    nombreEstacion: " /Tipo String }
}
```


#### POST /reservas/{idReserva}/calificacionEstacionLlegada

Asocia un objeto Calificación ( de la estación a la cual llega el usuario) a un objeto Reserva.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idReserva|Path|ID del objeto Reservar al cual se le asociará el objeto Calificacion|Sí|Integer
body|body|Objeto Calificacion que será creado|Sí|[Representación Detail](#recurso-califacion)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|Objeto Califiacion ha sido creado y asociado|[Representación Detail de Calificacion](#recurso-clasificacion)
500|No se pudo crear o asociar el objeto Calificacion|Mensaje de error


#### POST /reservas/{idReserva}/calificacionEstacionOrigen

Asocia un objeto Calificación (de la estación de la cual sale el usuario) a un objeto Reserva.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idReserva|Path|ID del objeto Reservar al cual se le asociará el objeto Calificacion|Sí|Long
body|body|Objeto Calificacion que será creado|Sí|[Representación Detail](#recurso-califacion)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|Objeto Califiacion ha sido creado y asociado|[Representación Detail de Calificacion](#recurso-clasificacion)
500|No se pudo crear o asociar el objeto Calificacion|Mensaje de error


#### GET  /reservas/{idReserva}/calificacionEstacionLlegada

Retorna un objeto Califiacion asociado a un objeto Reserva en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idReserva|Path|ID del objeto Reserva a consultar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Calificacion en [representación Detail](#recurso-calificacion)
404|No existe un objeto Calificacion con el ID solicitado asociado al objeto Reserva indicado |Mensaje de error
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error


#### GET  /reservas/{idReserva}/calificacionEstacionOrigen

Retorna un objeto Califiacion asociado a un objeto Reserva en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idReserva|Path|ID del objeto Reserva a consultar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Calificacion en [representación Detail](#recurso-calificacion)
404|No existe un objeto Calificacion con el ID solicitado asociado al objeto Reserva indicado |Mensaje de error
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error


#### GET /estaciones/{idEstacion}/calificaciones/

Retorna una colección de objetos Calificacion asociados a un objeto Estacion en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idEstacion|Path|ID del objeto Estacion a consultar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Calificacion en [representación Detail](#recurso-calificacion)
500|Error consultando calificaciones |Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error


#### GET /estaciones/{idEstacion}/calificaciones/{idCalificacion}

Retorna un objeto Calificacion asociados a un objeto Estacion en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idEstacion|Path|ID del objeto Estacion a consultar|Sí|Long
idCalificacion|Path|ID del objeto Calificacion consultar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Calificacion en [representación Detail](#recurso-calificacion)
404|No existe un objeto Calificacion con el ID solicitado asociado al objeto Estacion indicado |Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

[Volver arriba](#tabla-de-contenidos)

### Recurso Estación
El objeto Estación tiene 1 representacion JSON:

#### Representación Detail
```javascript
{
    id: '' /*Tipo Long*/,
    nombre: '' /*Tipo String*/,
    direccion: '' /*Tipo String*/
}
```
El objeto Accesorio tiene 1 representacion JSON:

#### Representación Detail
```javascript
{
    id: '' /*Tipo Long*/,
    tipo: '' /*Tipo Integer*/,
}
```


#### GET /estaciones

Retorna una colección de objetos Estación en representación Detail.
#### Parámetros
#### N/A
#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de representaciones en Detail
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error.

#### GET /estaciones/{id}
Retorna un objeto Estación en representación Detail.

#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Estación a consultar|Sí|Integer

#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Estación en representación Detail
404|No existe un objeto Estacion con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### POST /estaciones

Es el encargado de crear objetos Estacion.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Estacion que será creado|Sí|Representación Detail

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Estación ha sido creado|Representacion Detail
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Estacion|Mensaje de error


#### PUT /estaciones/{id}

Es el encargado de actualizar objetos Estación.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Estacion a actualizar|Sí|Integer
body|body|Objeto Estación nuevo|Sí|Representación Detail

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Estación actualizado|Representacion Detail
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Estación|Mensaje de error

#### DELETE /estaciones/{id}

Elimina un objeto Estacion.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Estacion a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

[Volver arriba](#tabla-de-contenidos)

### Recurso Accesorio
El objeto Accesorio tiene 1 representacion JSON:

#### Representación Detail
```javascript
{
    id: '' /*Tipo Long*/,
    tipo: '' /*Tipo Integer*/,
}
```

#### GET /estaciones/{idestacion}/accesorios
Retorna una colección de objetos Accesorio en representación Detail.

#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idestacion|Path|ID del objeto Estación a consultar|Sí|Integer

#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Accesorio en representación Detail
404|No existe un objeto Estación con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### GET /estaciones/{idestacion}/accesorios/{id}
Retorna un objeto Accesorio en representación Detail.

#### Parámetros
Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idestacion|Path|ID del objeto Estación a consultar|Sí|Integer
id|Path|ID del objeto Accesorio a consulta|Sí|Integer

#### Respuesta
Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Bicicleta en representación Detail
404|No existe un objeto Estación con el ID solicitado|Mensaje de error
404|No existe un objeto Accesorio con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### POST /estaciones/{idestacion}/accesorios/

Es el encargado de crear objetos Accesorio.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idestacion|Path|ID del objeto Estación a consultar|Sí|Integer
body|body|Objeto Accesorio que será creado|Sí|Representación Detail

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Accesorio ha sido creado|Representacion Detail
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Accesorio|Mensaje de error

#### PUT /estaciones/{estacionesid}/accesorios/{id}

Es el encargado de actualizar objetos Accesorio.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Accesorio a actualizar|Sí|Integer
estacionesid|Path|ID del objeto Estación a consultar|Sí|Integer
body|body|Objeto Bicicleta nuevo|Sí|Representación Detail

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Accesorio actualizado|Representacion Detail
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Accesorio|Mensaje de error

#### DELETE /estaciones/{estacionesid}/accesorios/

Elimina todos los obejtos Accesorio.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
estacionesid|Path|ID del objeto Estacion a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objetos eliminados|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### DELETE /estaciones/{estacionesid}/accesorios/{id}

Elimina un objeto Accesorio.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Accesorio a eliminar|Sí|Integer
estacionesid|Path|ID del objeto Estacion a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

[Volver arriba](#tabla-de-contenidos)

### Recurso SistemaDePagos
El recurso SistemaDePagos no tiene representación en JSON porque es una clase que permite la comunicación con el sistema de pagos, por lo que no tiene ningún atributo. 

#### GET /SistemaDePagos/ {id}
Retorna el estado de una transacción. El estado está modelado con constantes. '

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID de la transacción que se quiere consultar su estado |Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Estado de la transacción como un Integer
404|No existe una transacción con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

[Volver arriba](#tabla-de-contenidos)

### RECURSO PAGO
El objeto Pago tiene 2 representaciones JSON:

#### Representación minimun
```javascript
{
    id: '' /*Tipo Long*/,
    monto: '' /*Tipo Double*/,
    fecha: '' /*Tipo Date*/
    estado: '' /*Tipo Integer*/
    idTransaccion: '' /*Tipo Long*/
    idUsuario: '' /*Tipo Long*/
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    reserva: {
    idReserva: '' /*Tipo long*/
    idUsuario: '' /*Tipo Long*/,
    DiaReserva: '' /*Tipo Date*/,
    InicioReseva: '' /*Tipo Date*/,
    FinReseva: '' /*Tipo Date*/,
    Pago: '' /*Tipo Double*/,
    Calificacion: '' /*Tipo Integer*/,
    IdEstacionFin: '' /*Tipo Long*/,
    IdEstacionInicio: '' /*Tipo Long*/ }
}
```

#### GET /reservas/ {id} /pago
Retorna un objeto Pago asociado a una reserva en su representación Detail

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Reserva cuyo pago se va a consultar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Pago en representaciones Detail
404|No existe un objeto Reserva con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### POST /reservas/ {id} /pago
Es el encargado de crear un objeto Pago para una reserva en particular. 

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Reserva al que se le va agregar el pago|Sí|Long
body|body|Objeto Pago que será creado|Sí| Representación Detail

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Pago ha sido creado| Representación Detail
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Pago|Mensaje de error

#### PUT /reservas/ {id} /pago
Es el encargado de actualizar un objeto pago para una reserva en particular. 

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Reserva al que se le va a actualizar el pago|Sí|Long
body|body|Objeto Pago que será actualizado|Sí| Representación Detail

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Pago actualizado| Representación Detail
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Pago|Mensaje de error

#### DELETE /reservas/ {id} /pago
Elimina un objeto Pago asociado a la reserva que es pasa por id

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Reserva al que se quiere eliminar su Pago|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

[Volver arriba](#tabla-de-contenidos)

## API de la aplicación bicicleta_absviews
### Recurso Bicicleta
El objeto Bicicleta tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    marca: '' /*Tipo String*/,
    modelo: '' /*Tipo String*/,
    estado:''/*Tipo int*/,
}
```
#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    editorial: {
    idAccesorio: '' /*Tipo Long*/,
    nameAccesorio: '' /*Tipo String*/    }
}
```
#### GET /bicicletas

Retorna una colección de objetos Bicicleta en representación Detail.
Cada Bicicleta en la colección tiene embebidos los siguientes objetos: AccesoriosBicicleta.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-Bicicleta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error


#### GET /reservas/{id}/bicicletas
 Retorna los elementos Bicicleta de una reserva especificada en representación Detail.
Cada Bicicleta en la colección tiene embebidos los siguientes objetos: AccesoriosBicicleta.
#### Parámetros
id reserva
#### N/A

#### Respuesta

Marca|Modelo|Estado
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-Bicicleta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### GET /bicicletas/{id}

Retorna una colección de objetos Bicicleta en representación Detail.
Cada Bicicleta en la colección tiene los siguientes objetos: AccesorioBicicleta.

#### Parámetros

Marca|Modelo|Estado
:--|:--|:--|:--|:--
id|Path|ID del objeto Bicicleta consultar|Sí|Long

#### Respuesta

Marca|Modelo|Estado
:--|:--|:--
200|OK|Objeto Bicicleta en [representaciones Detail](#recurso-Bicicleta)
404|No existe un objeto Bicicleta con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### POST /bicicletas

Es el encargado de crear objetos Bicicleta.

#### Parámetros

Marca|Modelo|Estado
:--|:--|:--|:--|:--
body|body|Objeto Book que será creado|Sí|[Representación Detail](#recurso-Bicicleta)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Book ha sido creado|[Representación Detail](#recurso-Bicicleta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Bicicleta|Mensaje de error
#### PUT/bicicletas/{id}

Retorna una colección de objetos Bicicleta en representación Detail.
Cada Bicicleta en la colección tiene los siguientes objetos: AccesorioBicicleta.

#### Parámetros

Marca|Modelo|Estado
:--|:--|:--|:--|:--
id|Path|ID del objeto Bicicleta consultar|Sí|Long

#### Respuesta

Marca|Modelo|Estado
:--|:--|:--
200|OK|Objeto Bicicleta en [representaciones Detail](#recurso-Bicicleta)
404|No existe un objeto Bicicleta con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### DELETE bicicletas/{bicicletasid}

Remueve un objeto Author de la colección en un objeto Bicicleta.

#### Parámetros

Marca|Modelo|Estado
:--|:--|:--|:--|:--
id|Path|ID del objeto Bicicleta consultar|Sí|Long

#### Respuesta

Marca|Modelo|Estado
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### GET/bicicletas/bicisSinReserva
Remueve todas las bicicletas que posean el estado 2
#### Parametros
Marca|Modelo|Estado
:--|:--|:--|:--|:--
id|Path|ID del objeto Bicicleta consultar|Sí|Estado=2
#### Respuesta

Marca|Modelo|Estado
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
#### DELETE/bicicletas/bicisDañadas
Remueve todas las bicicletas que posean el estado 3
#### Parametros
Marca|Modelo|Estado
:--|:--|:--|:--|:--
id|Path|ID del objeto Bicicleta consultar|Sí|Estado=2
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-Bicicleta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

[Volver arriba](#tabla-de-contenidos)

### Recurso Accesorio Bicicleta
El objeto AccesorioBicicleta tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    descripcion: '' /*Tipo String*/,
    nombre: '' /*Tipo String*/,
}
```
#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    editorial: {
    idAccesorio: '' /*Tipo Long*/,
    nameAccesorio: '' /*Tipo String*/    }
}
```
#### GET /bicicletas/AccesorioBicicleta

Retorna una colección de objetos AccesorioBicicleta en representación Detail.
Cada AccesorioBicicleta en la colección tiene embebidos los siguientes objetos: .

#### Parámetros

#### N/A

#### Respuesta

Nombre|Descripción
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-AccesorioBicicleta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error


#### GET /reservas/{id}/bicicletas/accesorioBicicleta
 Retorna los elementos Bicicleta de una reserva especificada en representación Detail.
Cada AccesorioBicicleta en la colección tiene embebidos los siguientes objetos: 
#### Parámetros
id reserva
#### N/A

#### Respuesta

Nombre|Descripción 
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-AccesorioBicicleta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### GET /bicicletas/{id}/AccesoriosBicicleta/{id}

Retorna una colección de objetos AccesorioBicicleta en representación Detail.
Cada Bicicleta en la colección tiene los siguientes objetos: AccesorioBicicleta.

#### Parámetros

Nombre|Descripción
:--|:--|:--|:--|:--
id|Path|ID del objeto Bicicleta consultar|Sí|Long
id|Path|ID del objeto Accesorio bicicleta consultar|Sí|Long

#### Respuesta

Nombre|Descripción
:--|:--|:--
200|OK|Objeto AccesorioBicicleta en [representaciones Detail](#recurso-AccesorioBicicleta)
404|No existe un objeto Bicicleta con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### POST /bicicletas/AccesorioBicicleta

Es el encargado de crear objetos AccesorioBicicleta.

#### Parámetros

Nombre|Descripción
:--|:--|:--|:--|:--
body|body|Objeto AccesorioBicicleta que será creado|Sí|[Representación Detail](#recurso-AccesorioBicicleta)

#### Respuesta

Nombre|Descripción
:--|:--|:--
201|El objeto AccesorioBicicleta ha sido creado|[Representación Detail](#recurso-Bicicleta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Bicicleta|Mensaje de error

#### PUT/bicicletas/{id}/AccesorioBicicleta/{id}

Modifica un AccesorioBicicleta con un id especifico

#### Parámetros

Marca|Modelo|Estado
:--|:--|:--|:--|:--
id|Path|ID del objeto Bicicleta consultar|Sí|Long
id|Path|ID del objeto AccesorioBicicleta consultar|Sí|Long

#### Respuesta

Nombre|Descripción
:--|:--|:--
200|OK|Objeto Bicicleta en [representaciones Detail](#recurso-Bicicleta)
404|No existe un objeto Bicicleta con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### DELETE bicicletas/{id}/accesorioBicicleta/{id}

Remueve un objeto Author de la colección en un objeto AccesorioBicicleta.

#### Parámetros

Nombre|Descripción
:--|:--|:--|:--|:--
id|Path|ID del objeto Bicicleta consultar|Sí|Long
id|Path|ID del objeto AccesorioBicicleta consultar|Sí|Long

#### Respuesta

Marca|Modelo|Estado
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### GET/bicicletas/bicisSinReserva/AccesoriosBicicleta
Remueve todas los AccesoriosBicicleta que posean el estado 2
#### Parametros
Nombre|Descripcion
:--|:--|:--|:--|:--
id|Path|ID del objeto Bicicleta consultar|Sí|Estado=2
#### Respuesta

Marca|Modelo|Estado
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### DELETE/bicicletas/bicisDañadas/AccesoriosBicicleta
Remueve todas las bicicletas que posean el estado 3
#### Parametros
Nombre|Descripción
:--|:--|:--|:--|:--
id|Path|ID del objeto Bicicleta consultar|Sí|Estado=3
#### Respuesta

Nombre|Descripcion
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-AccesorioBicicleta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

[Volver arriba](#tabla-de-contenidos)



[Volver arriba](#tabla-de-contenidos)



[Volver arriba](#tabla-de-contenidos)
