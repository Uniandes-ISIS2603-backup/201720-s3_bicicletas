/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity_;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import co.edu.uniandes.bicicletas.persistence.EstacionPersistence;
import co.edu.uniandes.bicicletas.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ds.chacon
 */
@Stateless
public class ReservaLogic 
{
    private static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getName());
    @Inject
    private ReservaPersistence persistence;
    
    @Inject
    private UsuarioLogic logicaUsuario;
     
    @Inject
    private EstacionPersistence logicaEstacion;
    
    @Inject 
    private BicicletaLogic biciLogic;
    
    
    public ReservaEntity getReserva(Long id)
    {
        //Toca agregarle más cosas, solo lo hice provisional
         ReservaEntity reserva = persistence.find(id);
         
         return reserva;
    }
    
    public List<ReservaEntity> getReservas(  ){
        return persistence.findAll();
    }
    
    public ReservaEntity getReservaUsuario(Long idReserva  , Long idUsuario) throws BusinessLogicException{
        UsuarioEntity usuario = logicaUsuario.getUsuario(idUsuario);
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una reserva del usuario con id = {0}", idUsuario);
        List<ReservaEntity> reservasUsuario = usuario.getReservas();
        if(reservasUsuario==null || reservasUsuario.isEmpty()){
            throw new BusinessLogicException("hola ");
        }
        ReservaEntity lreserva = persistence.find(idReserva);
        int index = reservasUsuario.indexOf(lreserva);
        if (index >= 0) 
        {
            return reservasUsuario.get(index);
        }
        return null;
    }
    
     public void deleteReserva(Long id) throws WebApplicationException
    {
         ReservaEntity Reserva = persistence.find(id);
         if(Reserva == null){
             throw new WebApplicationException("No hay una reserva con dicho ID", 402);
         }
         persistence.delete(id);
    }
    public ReservaEntity crearReserva(Long idUsuario, ReservaEntity entity ) throws BusinessLogicException{
        
        UsuarioEntity lusuario = logicaUsuario.getUsuario(idUsuario);
        entity.setUsuarioReserva(lusuario);
        List<ReservaEntity> reservasUsuario = lusuario.getReservas();
        
        EstacionEntity estacionOrigen ;
        if(entity.getEstacionSalida().getId()!= null  ){
            //LOGGER.info("ESTA MOSTRANDO ESTO  :::   "+entity.getEstacionSalida().getId());
            //LOGGER.info("ESTA MOSTRANDO ESTO :::   "+ logicaEstacion);
            estacionOrigen = logicaEstacion.find(entity.getEstacionSalida().getId());
            LOGGER.info("ESTA MOSTRANDO ESTA HPTA MIERDAAAAAAAA  :::   "+estacionOrigen);
            if(estacionOrigen==null){
                throw new BusinessLogicException("La estacion de origen no existe");
            }
        }else{
            throw new BusinessLogicException("Debe proporcial un id de estacion ");
        }
        ReservaEntity reservaNueva;
        if(reservasUsuario == null){
            reservasUsuario = new ArrayList<>();
        }
        reservaNueva=persistence.create(entity);
        reservaNueva.setEstacionSalida(estacionOrigen);
        reservasUsuario.add(reservaNueva);
        return reservaNueva;
    }
    
    public ReservaEntity actualizarReserva(ReservaEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una reserva con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    
    
     public ReservaEntity asignarBicicleta(Long idReserva, Long  idBici) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         BicicletaEntity bici = biciLogic.getBIcicleta(idBici);
         if(bici.darEstado()!=BicicletaEntity.DISPONIBLE){
             throw new BusinessLogicException("No esta disponible la bici");
         }
         boolean a = false;
         for (BicicletaEntity bicicletaR : reserva.getBicis()) {
             if(bicicletaR.equals(bici)){
                 a=true;
          }
        }
         if(a){
             throw new BusinessLogicException("No esta disponible la bici");
         }
         reserva.getBicis().add(bici);
         bici.setEstado(BicicletaEntity.RESERVADA);
         return reserva;
     }
     public BicicletaEntity getBici(Long idReserva, Long  idBici) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         BicicletaEntity bici = biciLogic.getBIcicleta(idBici);
         if(bici.darEstado()!=BicicletaEntity.DISPONIBLE){
             throw new BusinessLogicException("No esta disponible la bici");
         }
         boolean a = false;
         for (BicicletaEntity bicicletaR : reserva.getBicis()) {
             if(bicicletaR.equals(bici)){
                 a=true;
             }
         }
         if(!a){
             throw new BusinessLogicException("No esta disponible la bici");
         }
         return bici;
     }
     public List<BicicletaEntity> getBicis(Long idReserva)throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         List<BicicletaEntity> bicis = reserva.getBicis();
         return bicis;
     }
    
}
