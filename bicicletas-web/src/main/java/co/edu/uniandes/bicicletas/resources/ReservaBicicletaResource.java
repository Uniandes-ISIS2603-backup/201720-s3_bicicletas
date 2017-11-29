/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.resources;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.BicicletaDetailDTO;
import co.edu.uniandes.bicicletas.dtos.BicicletaDTO;
import co.edu.uniandes.bicicletas.dtos.ReservaDTO;
import co.edu.uniandes.bicicletas.ejb.ReservaLogic;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author js.torres1
 */
@Produces("application/json")
@Consumes("application/json")
@Provider
public class ReservaBicicletaResource {
    /**
     * Este atributo es para Moldear la logica de reserva.
     */
    @Inject
    ReservaLogic reservaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    private static final Logger LOGGER = Logger.getLogger(EstacionBicicletaResource.class.getName());
    /**
     * GET Este metodo obtiene una de todas las bicicletas de una Reserva.
     * @param idReserva el id de la reserva
     * @param idBici el id de la Bicicleta
     * @return La bicicleta que esta siendo buscada.
     * @throws BusinessLogicException Las excepciones de negocio etc.
     */
    @GET
    @Path("{idBicicleta: \\d+}")
    public BicicletaDetailDTO getBici(@PathParam("idReserva") Long idReserva,@PathParam("idBicicleta") Long idBici) throws BusinessLogicException{
        BicicletaEntity entity = reservaLogic.getBici(idReserva, idBici);
        return new BicicletaDetailDTO(entity);
    }
    /**
     * GET Todas las bicicletas que estan ligadas a una reserva.
     * @param idReserva el id de la reserva.
     * @return Lista de las bicicletas que estan en una reserva.
     * @throws BusinessLogicException 
     */
    @GET
    public List<BicicletaDetailDTO> getReservaBicicis(@PathParam("idReserva") Long idReserva)throws BusinessLogicException {
        List<BicicletaEntity> listEntity = reservaLogic.getBicis(idReserva);
        return listEntity2DetailDTO(listEntity);
    }
    /**
     * PUT Este metodo asoscia una bicicleta a una reserva
     * @param idReserva id de la reserva.
     * @param bicicleta bicicleta que se quiere asociar.
     * @return Reserva con la bicicleta asociada.
     * @throws BusinessLogicException 
     */
    @PUT
    public ReservaDTO updateBiciAso(@PathParam("idReserva") Long idReserva,BicicletaDTO bicicleta)throws BusinessLogicException{
      
      return new ReservaDTO(reservaLogic.asignarBicicleta(idReserva, bicicleta.toEntity()));
    }
    /**
     * 
     * @param idReserva
     * @param bicicleta
     * @return ReservaDTO de la reserva sin la bicicleta desasociada.
     * @throws BusinessLogicException 
     */
    @Path("desAsociar")
    @PUT
    public ReservaDTO desAsociarBici(@PathParam("idReserva") Long idReserva,BicicletaDTO bicicleta)throws BusinessLogicException{
      return new ReservaDTO(reservaLogic.desAsociarBicicleta(bicicleta.toEntity()));
    }
    /**
     * Este metodo entrega todas las bicicletas de una reserva en una estación.
     * @param idReserva id de la Reserva
     * @return Reserva DTO de la reservaFinalizada
     * @throws BusinessLogicException 
     */
    @Path("entregar")
    @PUT
    public ReservaDTO entregarBicicleta(@PathParam("idReserva") Long idReserva)throws BusinessLogicException{
      return new ReservaDTO(reservaLogic.entregarBicicletas(idReserva));
    }
    /**
     * Este meotdo convierte un entityList en un DTOList
     * @param entityList Entity list que se quiere convertir en DTOList.
     * @return DTOList.
     */
    private List<BicicletaDetailDTO> listEntity2DetailDTO(List<BicicletaEntity> entityList) {
        List<BicicletaDetailDTO> list = new ArrayList<>();
        for (BicicletaEntity entity : entityList) {
            list.add(new BicicletaDetailDTO(entity));
        }
        return list;
    }
}
