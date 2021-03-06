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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *Logica de la reserva
 * @author ds.chacon
 */
@Stateless
public class ReservaLogic 
{
    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getName());
    
    /**
     * Persistencia de la reserva 
     */
    @Inject
    private ReservaPersistence persistence;
    /**
     * Persistencia del ususario
     */
    @Inject
    private UsuarioPersistence persistenceUsuario;
     /**
      * Persistencia de la estacion
      */
    @Inject
    private EstacionPersistence persistenceEstacion;
    /**
     * Presistencia de bicicleta 
     */
    @Inject 
    private BicicletaPersistence biciLogic;
    /**
     * Persistencia de accesorio
     */
    @Inject
    private AccesorioPersistence persistenceAccesorio;
    
    /**
     * Constante para indicar que no esta disponible la bicicleta
    */
    private static final String NO_BICI = "No esta disponible la bicicleta";
    
    /**
     * Constante para indicar que no esta disponible el accesorio
    */
    private static final String NO_ACCESORIO = "No esta disponible el accesorio";
    
    /**
     * Da reserva por id
     * @param id
     * @return 
     */
    public ReservaEntity getReserva(Long id)
    {
        return persistence.find(id);
    }
    /**
     * Dar reservas
     * @return 
     */
    public List<ReservaEntity> getReservas(  ){
        return persistence.findAll();
    }
    /**
     * Dar usuario de la reserva
     * @param idReserva
     * @param idUsuario
     * @return
     * @throws BusinessLogicException 
     */
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
    /**
     * Borra la reserva
     * @param id
     * @throws WebApplicationException 
     */
     public void deleteReserva(Long id)
    {
         ReservaEntity localReserva = persistence.find(id);
         if(localReserva == null){
             throw new WebApplicationException("No hay una reserva con dicho ID", 402);
         }
         
         
         localReserva.setEstado(2);
         persistence.update(localReserva);
    }
     /**
      * Crear la reserva, aca se aplican la mayoria de regals de negocio
      * fecha reserva > fechainicio > fecha fin  
      * @param idUsuario
      * @param entity
      * @return
      * @throws BusinessLogicException 
      */
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
            if(local.getEstado()!=2){
                
                
                if(local.getFechaInicio().compareTo( entity.getFechaInicio() )== 0 && 
                local.getEstacionSalida().getId().equals(entity.getEstacionSalida().getId()))
                {
                     throw new BusinessLogicException("No es posible crear una reserva a la misma hora en la misma estacion ");
                }
                if(local.getFechaInicio().compareTo( entity.getFechaInicio())== 0){
                     throw new BusinessLogicException("No es posible crear una reserva a la misma hora en diferente estacion");
                }
                
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
        
        if(entity.getFechaInicio().getDay()-entity.getFechaEntrega().getDay()!= 0){
            throw new BusinessLogicException("La reserva debe finalizar el mismo dia de creacion  ");
        }
        
        
        reservaNueva=persistence.create(entity);
        
        reservasUsuario.add(reservaNueva);
        
        reservasEstacion.add(reservaNueva);
        
        return reservaNueva;
    }
    
    /**
     * Metodo que inicializa la reserva
     * @param lreserva
     * @return
     * @throws BusinessLogicException 
     */
    public ReservaEntity iniciarReserva( ReservaEntity lreserva ) throws BusinessLogicException{
        
        if(lreserva.getEstado()== 0){
            lreserva.setEstado(3);
            List<BicicletaEntity> bicicletas = lreserva.getBicicletas();
            for (BicicletaEntity bicicleta : bicicletas) {
                bicicleta.setEstado(BicicletaEntity.RESERVADA);
                biciLogic.update(bicicleta);
            }
            List<AccesorioEntity> accesorios = lreserva.getAccesorios();
            for (AccesorioEntity accesorio : accesorios) {
                accesorio.setReservado(AccesorioEntity.EN_RESERVA);
                persistenceAccesorio.update(accesorio);
            }
            persistence.update(lreserva);
        }else{
            throw new BusinessLogicException("No se puede inicializar una reserva finalizada,cancelada o en uso ");
        }
        
        return lreserva;
    }
    /**
     * 
     * @param entidad
     * @return
     * @throws WebApplicationException 
     */
    public ReservaEntity actualizarReserva(ReservaEntity entidad){
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una reserva con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    /**
     * Asigna la estacion
     * @param idReserva
     * @param estacion
     * @return
     * @throws BusinessLogicException 
     */
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
    
    /**
     * Da reservas en determinada fecha
     * @param preservas
     * @param inicio
     * @param fin
     * @return 
     */
    public List<ReservaEntity> darReservasPorFecha(List<ReservaEntity> preservas , Date inicio , Date fin ){
        List<ReservaEntity> reservasUsuario = preservas;
        List<ReservaEntity> filtro = new ArrayList<>(); 
        Iterator<ReservaEntity> iter = reservasUsuario.iterator();
        if(reservasUsuario.isEmpty()){
            return reservasUsuario ;
        }
        while(iter.hasNext()){
            ReservaEntity local =iter.next();
            if(local.getFechaReserva().after(inicio)&& local.getFechaReserva().before(fin)){
                filtro.add(local);
            }
        }
        return filtro;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------BICICLETAS------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    
    /**
     * Este metodo asigna una bicicleta en la reserva
     * @param idReserva Long id de la reserva a la que se quiere agregar la Bicicleta
     * @param bici bicicleta que se quiere añadir a la reserva
     * @return reserva a la que se le asigno la bicicleta
     * @throws BusinessLogicException Esta bicicleta no esta disponoble en esa estacion,Hay un pago no se puede añadir la bicicleta
     */
    public ReservaEntity asignarBicicleta(Long idReserva, BicicletaEntity bici) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         BicicletaEntity entity = biciLogic.find(bici.getId());
         if(entity.darEstado() == BicicletaEntity.RESERVADA){
             throw new BusinessLogicException(NO_BICI);
         }
         if(!reserva.getEstacionSalida().equals(entity.getEstacion())){
             throw new BusinessLogicException("Esta bicicleta no esta disponoble en esa estacion");
         }
         if(reserva.getEstado()!=ReservaEntity.PAGO){
             throw new BusinessLogicException(NO_BICI);
         }
         
         boolean a = reserva.getBicicletas().contains(bici);
         
         if(a){
             throw new BusinessLogicException(NO_BICI);
         }
         if(reserva.getPago()!=null){
             throw new BusinessLogicException("Hay un pago no se puede añadir la bicicleta");
         }
        
         
        entity.setReserva(reserva);
        reserva.getBicicletas().add(entity);
        
        Double nuevoCosto = reserva.calcularCostoFinal(reserva.getFechaInicio(), reserva.getFechaEntrega(), reserva.getBicicletas().size());
        reserva.setPrecioFinal(nuevoCosto);
        
        biciLogic.update(entity);
        persistence.update(reserva);
         return reserva;
     }
    /**
     * 
     * @param idReserva
     * @param idBici
     * @return
     * @throws BusinessLogicException 
     */
     public BicicletaEntity getBici(Long idReserva, Long  idBici) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         BicicletaEntity bici = biciLogic.find(idBici);
         if(bici.darEstado()!=BicicletaEntity.DISPONIBLE){
             throw new BusinessLogicException(NO_BICI);
         }
         boolean a = false;
         for (BicicletaEntity bicicletaR : reserva.getBicicletas()) {
             if(bicicletaR.equals(bici)){
                 a=true;
             }
         }
         if(!a){
             throw new BusinessLogicException(NO_BICI);
         }
         return bici;
     }
     public ReservaEntity desAsociarBicicleta(BicicletaEntity bicicleta) throws BusinessLogicException{
         BicicletaEntity bici = biciLogic.find(bicicleta.getId());
         if(bici==null){
             throw new BusinessLogicException(NO_BICI);
         }
         ReservaEntity reserva=bici.getReserva();
         if(reserva.getPago()!=null){
             throw new BusinessLogicException("hay un pago ya asociado a esta reserva");
         }
         reserva.getBicicletas().remove(bici);
         bici.setReserva(null);
         actualizarReserva(reserva);
         biciLogic.update(bici);
         return reserva;
     }
     public ReservaEntity entregarBicicletas(Long idReserva,EstacionEntity idEstacion)throws BusinessLogicException{
         ReservaEntity reserva = persistence.find(idReserva);
         EstacionEntity estacion = persistenceEstacion.find(idEstacion.getId());
         if(reserva==null){
             throw new BusinessLogicException("No se encontro dicha reserva");
         }
         if(reserva.getEstado()==ReservaEntity.FINALIZADA){
             throw new BusinessLogicException("La reserva esta finalizada");
         }
         if(estacion==null){
             throw new BusinessLogicException("No existe la estacion");
         }
         Date fechaActual = new Date(System.currentTimeMillis());
         reserva.setFechaFinal(fechaActual);
         if(fechaActual.getHours()-reserva.getFechaInicio().getHours()<1){
             throw new BusinessLogicException("No se ha cumplido minimo de uso");
         }
         List<BicicletaEntity> bicisViejas = new ArrayList<BicicletaEntity>();
         List<BicicletaEntity> bicicletas  = reserva.getBicicletas();
         BicicletaEntity entityNueva;
         for (BicicletaEntity bicicleta : bicicletas) {
             entityNueva = new BicicletaEntity();
             entityNueva.setMarca(bicicleta.darMarca());
             entityNueva.setModelo(bicicleta.darModelo());
             entityNueva.setReserva(reserva);
             bicisViejas.add(entityNueva);
             bicicleta.setReserva(null);
             bicicleta.setEstado(BicicletaEntity.DISPONIBLE);
             bicicleta.setEstacion(estacion);
             estacion.getBicicletas().add(bicicleta);
             biciLogic.update(bicicleta);
             
         }
         reserva.setEstacionLlegada(estacion.getId());
         persistenceEstacion.update(estacion);
         reserva.setBicicletas(bicisViejas);
         reserva.setEstado(ReservaEntity.FINALIZADA);
         persistence.update(reserva);
         
         return reserva;
     }
     /**
      * 
      * @param idReserva
      * @return
      * @throws BusinessLogicException 
      */
     public List<BicicletaEntity> getBicis(Long idReserva)throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         return reserva.getBicicletas();
     }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------ACCESORIOS----------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
     /**
      * 
      * @param idReserva
      * @return
      * @throws BusinessLogicException 
      */
     public AccesorioEntity getAccesorio(Long idReserva, Long idAccesorio) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         AccesorioEntity accesorio = persistenceAccesorio.find(idAccesorio);
         if(accesorio.getReservado()!=1){
             throw new BusinessLogicException(NO_ACCESORIO);
         }
         boolean reservado = false;
         for (AccesorioEntity accesorioR : reserva.getAccesorios()) {
             if(accesorioR.equals(accesorio)){
                 reservado=true;
             }
         }
         if(reservado){
             throw new BusinessLogicException(NO_ACCESORIO);
         }
         return accesorio;
     }
     /**
      * 
      * @param idReserva
      * @return
      * @throws BusinessLogicException 
      */
     public List<AccesorioEntity> getAccesorios(Long idReserva)throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         return reserva.getAccesorios();
     }
     /**
      * 
      * @param idReserva
      * @param accesorio
      * @return
      * @throws BusinessLogicException 
      */
     public ReservaEntity asignarAccesorio(Long idReserva, AccesorioEntity accesorio) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         AccesorioEntity accesorioEntity = persistenceAccesorio.find(accesorio.getId());
         if(accesorioEntity.getReservado()==1){
             throw new BusinessLogicException(NO_ACCESORIO);
         }
         if(reserva.getEstacionSalida().getId()!=accesorioEntity.getEstacion().getId()){
             throw new BusinessLogicException("La reserva y el accesorio no pertenecen a una misma estación");
         }
         if(reserva.getEstado() == ReservaEntity.PAGADA){
             throw new BusinessLogicException("Ya hay un pago asociado");
         }
         boolean a = false;
         for (AccesorioEntity accesorioR : reserva.getAccesorios()) {
             if(accesorioEntity.equals(accesorioR)){
                 a=true;
          }
        }
         if(a){
             throw new BusinessLogicException(NO_ACCESORIO);
         }
         reserva.getAccesorios().add(accesorioEntity);
         accesorioEntity.setReservado(AccesorioEntity.EN_RESERVA);
         persistence.update(reserva);
         persistenceAccesorio.update(accesorioEntity);
         return reserva;
     }
     /**
      * 
      * @param idReserva
      * @param accesorio
      * @return
      * @throws BusinessLogicException 
      */
     public ReservaEntity desasignarAccesorio(Long idReserva, AccesorioEntity accesorio) throws BusinessLogicException{
         ReservaEntity reserva = getReserva(idReserva);
         AccesorioEntity accesorioEntity = persistenceAccesorio.find(accesorio.getId());
         for(int i =0; i<reserva.getAccesorios().size(); i++){
             if(reserva.getAccesorios().get(i).getId()==accesorioEntity.getId()){
                 reserva.getAccesorios().remove(i);
             }
         }
         accesorioEntity.setReservado(AccesorioEntity.EN_ESTACION);
         persistence.update(reserva);
         persistenceAccesorio.update(accesorioEntity);
         return reserva;
     }
}
