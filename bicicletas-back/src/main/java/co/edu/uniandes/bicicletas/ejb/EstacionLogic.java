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
import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.persistence.AccesorioPersistence;
import co.edu.uniandes.bicicletas.persistence.BicicletaPersistence;
import co.edu.uniandes.bicicletas.persistence.EstacionPersistence;
import co.edu.uniandes.bicicletas.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author 
 */
@Stateless
public class EstacionLogic 
{
    private static final Logger LOGGER = Logger.getLogger(BicicletaLogic.class.getName());
    
    @Inject
    private EstacionPersistence persistence;
    
    @Inject
    private BicicletaPersistence bicicletaLogic;
    
    @Inject
    private AccesorioPersistence persistenceAccesorio;
    
    @Inject
    private ReservaPersistence persistenceReserva;
    
    public EstacionEntity getEstacion(Long id) throws WebApplicationException
    {
        //Toca agregarle más cosas, solo lo hice provisional
         EstacionEntity estacion = persistence.find(id);
         if(estacion == null){
             throw new WebApplicationException("No hay una estación con dicho ID", 402);
         }
         return estacion;
    }
    
    public void deleteEstacion(Long id) throws WebApplicationException
    {
         EstacionEntity estacion = persistence.find(id);
         if(estacion == null){
             throw new WebApplicationException("No hay una estación con dicho ID", 402);
         }
         persistence.delete(id);
    }
    
    public EstacionEntity asignarAccesorio(Long idEstacion, AccesorioEntity accesorio) throws BusinessLogicException{
         EstacionEntity estacion = getEstacion(idEstacion);
         AccesorioEntity accesorioEntity = persistenceAccesorio.find(accesorio.getId());
         if(accesorioEntity.getReservado()==1){
             throw new BusinessLogicException("No esta disponible el accesorio");
         }
         boolean a = false;
         for (AccesorioEntity accesorioR : estacion.getAccesorios()) {
             if(accesorioEntity.equals(accesorioR)){
                 a=true;
          }
        }
         if(a){
             throw new BusinessLogicException("No esta disponible el accesorio");
         }
         estacion.getAccesorios().add(accesorioEntity);
         accesorioEntity.setReservado(AccesorioEntity.EN_RESERVA);
         accesorioEntity.setEstacion(estacion);
         persistence.update(estacion);
         persistenceAccesorio.update(accesorioEntity);
         return estacion;
     }
    
    public List<AccesorioEntity> getAccesorios1(Long idEstacion)throws BusinessLogicException{
         EstacionEntity estacion = getEstacion(idEstacion);
         List<AccesorioEntity> accesorios = estacion.getAccesorios();
         return accesorios;
     }
    
    public List<EstacionEntity> getEstaciones(){
        return persistence.findAll();
    }
    
    public EstacionEntity crearEstacion(EstacionEntity entidad){
        if(entidad.getAccesorios()==null){
            List <AccesorioEntity> lista = new ArrayList<>();
            entidad.setAccesorios(lista);
        }
        else if(entidad.getCalificaciones()==null){
            ArrayList <CalificacionEntity> lista = new ArrayList<>();
            entidad.setCalificaciones(lista);
        }
        else if(entidad.getReservas()==null){
            List <ReservaEntity> lista = new ArrayList<>();
            entidad.setReservas(lista);
        }
        persistence.create(entidad);
        return entidad;
    }
    
    public EstacionEntity actualizarEstacion(EstacionEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        return persistence.update(entidad);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------RESERVAS-----------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    
    
    public List<ReservaEntity> listReservas(Long estacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", estacionId);
        return getEstacion(estacionId).getReservas();
    }
    
    public ReservaEntity getReserva(Long estacionId, Long reservaId) {
        List<ReservaEntity> list = getEstacion(estacionId).getReservas();
        ReservaEntity rEntity = new ReservaEntity();
        rEntity.setId(reservaId);
        int index = list.indexOf(rEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    
    
    
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------BICICLETAS------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    
    
    public BicicletaEntity getBiciEstacion(Long idEstacion,Long idBici){
        EstacionEntity estacion = persistence.find(idEstacion);
        if(estacion ==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        BicicletaEntity bici = bicicletaLogic.find(idBici);
        boolean esta = false;
        for(BicicletaEntity temp : estacion.getBicicletas()){
            if(bici.equals(temp)){
                esta = true;
            }
        }
        if(esta==false){
            throw new WebApplicationException("No hay una estación asociada a la bici", 402);
        }
        return bici;
    }
    public List<BicicletaEntity> getBicisEstacion(Long idEstacion){
        EstacionEntity estacion = persistence.find(idEstacion);
        if(estacion ==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        return estacion.getBicicletas();
    }
    public EstacionEntity upDateBici(Long idEstacion,BicicletaEntity bicicleta) throws BusinessLogicException{
        BicicletaEntity bici = bicicletaLogic.find(bicicleta.getId());
        EstacionEntity aBorrar = bici.getEstacion();
        EstacionEntity aActualizar = persistence.find(idEstacion);
        ReservaEntity reserva = bici.getReserva();
        List<BicicletaEntity> bicicletas = reserva.getBicicletas();
        if(reserva == null){
            throw new BusinessLogicException("No se puede entregar una bicicleta sin reserva");
        }
        aBorrar.getBicicletas().remove(bici);
        bici.setEstacion(aActualizar);
        aActualizar.getBicicletas().add(bici);
        reserva.setEstacionLlegada(idEstacion);
        bicicletas.remove(bici);
        if(bicicletas.isEmpty()){
            reserva.setEstado(ReservaEntity.FINALIZADA);
            reserva.setFechaEntrega(new Date(System.currentTimeMillis()));
        }
        persistenceReserva.update(reserva);
        bicicletaLogic.update(bici);
        persistence.update(aActualizar);
        return aActualizar;
    }
    public EstacionEntity añadirBici(Long idEstacion,BicicletaEntity bicicleta){
        BicicletaEntity bici = bicicletaLogic.find(bicicleta.getId());
        EstacionEntity aBorrar = bici.getEstacion();
        EstacionEntity aActualizar = persistence.find(idEstacion);
       if(aBorrar!=null){
           throw new WebApplicationException("La bicicleta Ya tiene una estacion", 402);
       }
       if(aActualizar ==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        bici.setEstacion(aActualizar);
        if(aActualizar.getBicicletas().contains(bici)){
            throw new WebApplicationException("No hay una bici con dicho id", 402);
        }
        aActualizar.getBicicletas().add(bici);
        bicicletaLogic.update(bici);
        persistence.update(aActualizar);
        return aActualizar;
    }
    
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------ACCESORIOS----------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    
    public List<AccesorioEntity> listAccesorios(Long estacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", estacionId);
        return getEstacion(estacionId).getAccesorios();
    }
    
    public AccesorioEntity getAccesorio(Long estacionId, Long accesorioId) {
        List<AccesorioEntity> list = getEstacion(estacionId).getAccesorios();
        AccesorioEntity accesoriosEntity = new AccesorioEntity();
        accesoriosEntity.setId(accesorioId);
        int index = list.indexOf(accesoriosEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }
    
}