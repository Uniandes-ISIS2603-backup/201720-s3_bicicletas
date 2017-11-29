/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.dtos.ReservaDetailDTO;
import co.edu.uniandes.bicicletas.dtos.TransaccionDTO;
import co.edu.uniandes.bicicletas.dtos.ConsultaDTO;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.ejb.TransaccionLogic;
import co.edu.uniandes.bicicletas.ejb.UsuarioLogic;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.TransaccionEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase de recurso reserva 
 * @author ds.chacon
 */
@Path("/reservas")
@Produces("application/json")
@Consumes("application/json")
public class ReservaResource {
    @Inject
    private ReservaLogic logica;
    @Inject
    private UsuarioLogic logicaUsuario ;
    @Inject
    private TransaccionLogic logicaTransaccion;
    
    /**
     * Constante para representar el recurso reservas
    */
    private static final String RECURSO_RESERVA = "El recurso /reservas/";
    
     /**
     * Constante para indicar que no existe
    */
    private static final String NO_EXISTE = " no existe";
    
    /**
     * Metodo que transfoma una lista de entitys en DTOS
     * @param entityList
     * @return reservaDTO 
     */
    private List<ReservaDTO> listEntity2DTO(List<ReservaEntity> entityList) {
        List<ReservaDTO> lista = new ArrayList<>();
        for(ReservaEntity entidad : entityList){
            lista.add(new ReservaDetailDTO(entidad));
    }
        return lista;
        
}
    /**
     * Metodo que hace la consulta
     * @param id
     * @param consulta
     * @return 
     */
    @PUT
    @Path("{id: \\d+}/consulta")
    public List<ReservaDTO> obtenerReservas(@PathParam("id") Long id , ConsultaDTO consulta){
        consulta.setFechaFinal(consulta.getStringFinal());
        consulta.setFechaInicio(consulta.getStringInicio());
        List<ReservaEntity> rta = logica.darReservasPorFecha(logicaUsuario.getUsuario(id).getReservas(),consulta.getFechaInicio(), consulta.getFechaFinal());
        return listEntity2DTO(rta);
    }
    
    /**
     * Dar reservas
     * @param id
     * @return 
     */
    @GET
    public List<ReservaDTO> getReservas(@PathParam("id") Long id) {
        List<ReservaEntity> reservas = logica.getReservas();
        if(reservas==null){
          throw new WebApplicationException("No hay reservas en el sistema "+ id +NO_EXISTE, 404);
        }
        return listEntity2DTO(reservas) ;
    }
    
    /**
     * Da reserva por id
     * @param id
     * @return 
     */
    @GET
    @Path("{id: \\d+}")
    public ReservaDTO getReserva(@PathParam("id") Long id) {
        ReservaEntity reservas = logica.getReserva(id);
        if(reservas==null){
          throw new WebApplicationException("La reserva con id "+ id +NO_EXISTE, 404);
        }
        return new ReservaDetailDTO(reservas);
    }
    /**
     * Crea una reserva 
     * @param id
     * @param dto
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    @Path("{id: \\d+}")
    public ReservaDTO crearReserva (@PathParam("id") Long id , ReservaDTO dto) throws BusinessLogicException {
        if(logicaUsuario.getUsuario(id)==null){
          throw new WebApplicationException("El usuario con id "+ id +NO_EXISTE, 404);
        }
        return new ReservaDTO(logica.crearReserva(id ,dto.toEntity()));
    }
    /**
     * Atualiza una Reserva
     * @param id
     * @param dto
     * @return 
     */
    @PUT
    @Path("{id: \\d+}")
    public ReservaDTO actualizarReserva (@PathParam("id") Long id, ReservaDTO dto) {
        ReservaEntity entity = dto.toEntity();
        entity.setId(id);
        return new ReservaDTO(logica.actualizarReserva(entity));
    }
    /**
     * Borrar una Reserva
     * @param id 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void borrarReserva(@PathParam("id") Long id) throws BusinessLogicException{
        if (logica.getReserva(id).getEstado()!= 1){
            throw new WebApplicationException("la reserva no se puede cancelar",404);
        }      
        logica.deleteReserva(id);
    }
    /**
     * Da la transaccion de una reserva
     * @param id
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{id: \\d+}/transaccion")
    public TransaccionDTO darTransaccion(@PathParam("id") Long id) throws BusinessLogicException{
        ReservaEntity reserva = logica.getReserva(id);
        if(reserva==null){
          throw new WebApplicationException("La reserva con id "+ id +NO_EXISTE, 404);
        }
        
        TransaccionEntity transaccion = logicaTransaccion.obtenerTransaccion(reserva);
        
        return new TransaccionDTO(transaccion);
    }
    /**
     * Redirecciona el recurso a reservaEstacion
     * @param idReserva
     * @param llegada
     * @return 
     */
    @Path("{idReserva: \\d+}/Estacion / { llegada: \\d+}")
    public Class<ReservaEstacionResource> getReservaEstacionResource(@PathParam("idReserva") Long idReserva,@PathParam("llegada") int llegada){
        ReservaEntity entity = logica.getReserva(idReserva);
        if (entity == null) {
            throw new WebApplicationException(RECURSO_RESERVA + idReserva + "/Estacion/"+ llegada +"no existe.", 404);
        }
        return ReservaEstacionResource.class;
    }
    /**
     * Redirecciona el recurso a reservaEstacion
     * @param idReserva
     * @return 
     */
    @Path("{idReserva: \\d+}/estaciones")
    public Class<ReservaEstacionResource> getReservaEstacionResource(@PathParam("idReserva") Long idReserva){
        ReservaEntity entity = logica.getReserva(idReserva);
        if (entity == null) {
            throw new WebApplicationException(RECURSO_RESERVA + idReserva +"no existe.", 404);
        }
        return ReservaEstacionResource.class;
    }
    /**
     * Redirecciona el recurso a PagoReserva
     * @param idReserva
     * @return 
     */
    @Path("{idReserva: \\d+}/Pago")
    public Class<PagoReservaResource> getResource(@PathParam("idReserva") Long idReserva){
        ReservaEntity entity = logica.getReserva(idReserva);
        if (entity == null) {
            throw new WebApplicationException(RECURSO_RESERVA + idReserva + "/Pago no existe.", 404);
        }
        return PagoReservaResource.class;
    }
    /**
     * Redirecciona el recurso a UsuarioReserva
     * @param idReserva
     * @return 
     */
    @Path("{idReserva: \\d+}/usuario")
    public Class<UsuarioReservaResource> getUsuario(@PathParam("idReserva") Long idReserva) {
        ReservaEntity entity = logica.getReserva(idReserva);
        if (entity == null) {
            throw new WebApplicationException(RECURSO_RESERVA + idReserva + "/Pago no existe.", 404);
        }
        return   UsuarioReservaResource.class;
    }
    /**
     * Redirecciona el recurso ReservaBicicleta
     * @param idReserva
     * @return 
     */
    @Path("{idReserva: \\d+}/bicicletas")
    public Class<ReservaBicicletaResource> getClassAcc(@PathParam("idReserva")Long idReserva) {
        ReservaEntity entity = logica.getReserva(idReserva);
            if (entity == null) {
            throw new WebApplicationException("El recurso /bici/" + idReserva + "/reservas/ no existe.", 404);
            }
       return ReservaBicicletaResource.class;
    }
    /**
     * Redirecciona el recurso ReservaAccsesorio
     * @param idReserva
     * @return 
     */
    @Path("{idReserva: \\d+}/accesorios")
    public Class<ReservaAccesorioResource> getAccesorio(@PathParam("idReserva") Long idReserva) {
        ReservaEntity entity = logica.getReserva(idReserva);
        if (entity == null) {
            throw new WebApplicationException(RECURSO_RESERVA + idReserva + "/accesorios no existe.", 404);
        }
        return ReservaAccesorioResource.class;
    }
    
    @GET
    @Path("{id: \\d+}/iniciar")
    public ReservaDTO iniciarReserva(@PathParam("id") Long id) throws BusinessLogicException{
        ReservaEntity reserva = logica.getReserva(id);
        if(reserva==null){
          throw new WebApplicationException("La reserva con id "+ id +NO_EXISTE, 404);
        }
        Date inicio = reserva.getFechaInicio();
	Date actual = new Date();
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(inicio);// Configuramos la fecha que se recibe
	calendar.add(Calendar.MINUTE,30);
	Date despues = calendar.getTime();
	calendar.add(Calendar.MINUTE,-60);
	Date antes = calendar.getTime();
        ReservaEntity nueva;
        if(actual.after(antes) && actual.before(despues)){
		nueva =logica.iniciarReserva(reserva);  
                return new ReservaDTO(nueva);
        }else if (antes.after(actual) ){
            System.out.println("todavia es muy temprano");
            //Es temprano
            throw new WebApplicationException("Todavia es muy temparno para iniciar la reserva ", 404);
        }else{
            //tarde
            throw new WebApplicationException("Ya es muy tarde para iniciar la reserva", 404);
	}
        
    }
    
    
}
