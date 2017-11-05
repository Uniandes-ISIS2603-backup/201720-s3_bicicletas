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
import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import co.edu.uniandes.bicicletas.persistence.AccesorioPersistence;
import co.edu.uniandes.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.bicicletas.persistence.EstacionPersistence;
import co.edu.uniandes.bicicletas.persistence.ReservaPersistence;
import co.edu.uniandes.bicicletas.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
    private UsuarioPersistence persistenceUsuario;
     
    @Inject
    private EstacionPersistence persistenceEstacion;
    
    @Inject 
    private BicicletaPersistence biciLogic;
    
    @Inject
    private AccesorioPersistence persistenceAccesorio;
    
    
    public ReservaEntity getReserva(Long id)
    {
        //Toca agregarle más cosas, solo lo hice provisional
         ReservaEntity reserva = persistence.find(id);
         
         return reserva;
    }
    
    public List<ReservaEntity> getReservas(  ){
        return persistence.findAll();
    }
    
//    public List<ReservaEntity> getReservasUsuario( Long id ) throws BusinessLogicException{
//        UsuarioEntity usuario = persistenceUsuario.find(id);
//        List<ReservaEntity> reservas = usuario.getReservas();
//        if(reservas.isEmpty()){
//            throw new BusinessLogicException("El usuario no tiene reservas");
//        }
//        return usuario.getReservas();
//    }
    
//    public List<ReservaEntity> getReservasUsuarioId( Long idUsuario , Long id ) throws BusinessLogicException{
//        UsuarioEntity usuario = persistenceUsuario.find(idUsuario);
//        List<ReservaEntity> reservas = usuario.getReservas();
//        
//        if(reservas.isEmpty()){
//            throw new BusinessLogicException("El usuario no tiene reservas");
//        }
//        
//        Iterator<ReservaEntity> iter = reservas.iterator();
//        ReservaEntity lreserva =null;
//        boolean encontro = false;
//         if(iter.hasNext()&& !encontro){
//             lreserva = iter.next();
//             if(lreserva.getId() == id){
//                 encontro=true;
//             } 
//         }
//        return usuario.getReservas();
//    }
    
    public ReservaEntity getReservaUsuario(Long idReserva  , Long idUsuario) throws BusinessLogicException{
        UsuarioEntity usuario = persistenceUsuario.find(idUsuario);
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una reserva del usuario con id = {0}", idUsuario);
        List<ReservaEntity> reservasUsuario = usuario.getReservas();
        if(reservasUsuario==null || reservasUsuario.isEmpty()){
            throw new BusinessLogicException(" EL usuario no tiene reservas ");
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
         Reserva.setEstado(2);
         persistence.update(Reserva);
    }
    public ReservaEntity crearReserva(Long idUsuario, ReservaEntity entity ) throws BusinessLogicException{
        
        UsuarioEntity usuario = persistenceUsuario.find(idUsuario);
        entity.setUsuarioReserva(usuario);
        List<ReservaEntity> reservasUsuario = usuario.getReservas();
        List<ReservaEntity> reservasEstacion;
     
        EstacionEntity estacionSalida;
        
        if(entity.getEstado()<0||entity.getEstado()>4){
            throw new BusinessLogicException("El estado indicado no existe");
        }
        
        if(entity.getEstacionSalida() != null){
           
            estacionSalida = persistenceEstacion.find(entity.getEstacionSalida().getId());
            
            if(estacionSalida == null)
            {
                throw new BusinessLogicException("La estacion de salida asignada no existe");
            }
            reservasEstacion = estacionSalida.getReservas();
        }
        else
        {
            throw new BusinessLogicException("Debe proporciar la estacion de salida");
        }
        
        ReservaEntity reservaNueva;

        entity.setEstacionSalida(estacionSalida);
        entity.setUsuarioReserva(usuario);
        entity.setEstado(1);
                
       
        Iterator<ReservaEntity> iter = reservasUsuario.iterator();
        while(iter.hasNext()){
            ReservaEntity local= iter.next();
            if(local.getFechaInicio().compareTo( entity.getFechaInicio() )== 0 && 
               local.getEstacionSalida().getId().equals(entity.getEstacionSalida().getId()))
            {
                 throw new BusinessLogicException("No es posible crear una reserva a la misma hora en la misma estacion ");
            }
            if(local.getFechaInicio().compareTo( entity.getFechaInicio())== 0){
                 throw new BusinessLogicException("No es posible crear una reserva a la misma hora en diferente estacion");
            }
        }
        
        if(entity.getFechaInicio().compareTo(entity.getFechaEntrega())==0){
            throw new BusinessLogicException("No es posible crear una reserva con la misma hora de salida y llegada ");
        }
        if(entity.getFechaInicio().before(entity.getFechaReserva())){
             throw new BusinessLogicException("No es posible crear una reserva antes de la fecha actual ");
        }
        if(entity.getFechaEntrega().before(entity.getFechaInicio())){
             throw new BusinessLogicException("No es posible crear una reserva con la entrega antes del inicio  ");
        }
        
        
        reservaNueva=persistence.create(entity);
        
        reservasUsuario.add(reservaNueva);
        
        reservasEstacion.add(reservaNueva);
        
        return reservaNueva;
    }
    
    public ReservaEntity actualizarReserva(ReservaEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una reserva con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    
    public ReservaEntity asignarEstacionLlegada(Long idReserva, EstacionEntity estacion) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         EstacionEntity entity = persistenceEstacion.find(estacion.getId());
         if(entity==null){
             throw new BusinessLogicException("La estacion no existe");
         }
         reserva.setEstacionSalida(entity);
         persistence.update(reserva);
         persistenceEstacion.update(entity);
         return reserva;
     }
    
    
    public List<ReservaEntity> darReservasPorFecha(List<ReservaEntity> preservas , Date inicio , Date fin ){
        List<ReservaEntity> reservasUsuario = preservas;
        List<ReservaEntity> filtro = new ArrayList<>(); 
        Iterator<ReservaEntity> iter = reservasUsuario.iterator();
        if(reservasUsuario.isEmpty()){
            return null;
        }
        while(iter.hasNext()){
            ReservaEntity local =iter.next();
            if(local.getFechaReserva().after(inicio)&& local.getFechaReserva().before(fin)){
                filtro.add(local);
            }
        }
        return filtro;
    }
    
     public ReservaEntity asignarBicicleta(Long idReserva, BicicletaEntity bici) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         BicicletaEntity entity = biciLogic.find(bici.getId());
         if(entity.darEstado() == BicicletaEntity.RESERVADA){
             throw new BusinessLogicException("No esta disponible la bici");
         }
         
         boolean a = reserva.getBicicletas().contains(bici);
         
         if(a){
             throw new BusinessLogicException("No esta disponible la bici");
         }
         entity.setReserva(reserva);
         entity.setEstado(BicicletaEntity.RESERVADA);

         biciLogic.update(entity);
        reserva.getBicicletas().add(entity);
        persistence.update(reserva);
         return reserva;
     }
     public BicicletaEntity getBici(Long idReserva, Long  idBici) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         BicicletaEntity bici = biciLogic.find(idBici);
         if(bici.darEstado()!=BicicletaEntity.DISPONIBLE){
             throw new BusinessLogicException("No esta disponible la bici");
         }
         boolean a = false;
         for (BicicletaEntity bicicletaR : reserva.getBicicletas()) {
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
         List<BicicletaEntity> bicis = reserva.getBicicletas();
         return bicis;
     }
    
     
     public AccesorioEntity getAccesorio(Long idReserva, Long  idAccesorio) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         AccesorioEntity accesorio = persistenceAccesorio.find(idReserva);
         if(accesorio.getReservado()!=1){
             throw new BusinessLogicException("No esta disponible el accesorio");
         }
         boolean reservado = false;
         for (AccesorioEntity accesorioR : reserva.getAccesorios()) {
             if(accesorioR.equals(accesorio)){
                 reservado=true;
             }
         }
         if(reservado){
             throw new BusinessLogicException("No esta disponible el accesorio");
         }
         return accesorio;
     }
     
     public List<AccesorioEntity> getAccesorios(Long idReserva)throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         List<AccesorioEntity> accesorios = reserva.getAccesorios();
         return accesorios;
     }
     
     public ReservaEntity asignarAccesorio(Long idReserva, AccesorioEntity accesorio) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         AccesorioEntity accesorioEntity = persistenceAccesorio.find(accesorio.getId());
         if(accesorioEntity.getReservado()==1){
             throw new BusinessLogicException("No esta disponible el accesorio");
         }
         boolean a = false;
         for (AccesorioEntity accesorioR : reserva.getAccesorios()) {
             if(accesorioEntity.equals(accesorioR)){
                 a=true;
          }
        }
         if(a){
             throw new BusinessLogicException("No esta disponible el accesorio");
         }
         reserva.getAccesorios().add(accesorioEntity);
         accesorioEntity.setReservado(AccesorioEntity.EN_RESERVA);
         persistence.update(reserva);
         persistenceAccesorio.update(accesorioEntity);
         return reserva;
     }
}
