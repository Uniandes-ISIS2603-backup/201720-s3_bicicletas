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
import co.edu.uniandes.bicicletas.entities.DireccionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.persistence.EstacionPersistence;
import java.util.ArrayList;
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
    private DireccionLogic direccionLogic;
    
    @Inject
    private BicicletaLogic bicicletaLogic;
    
    @Inject
    private ReservaLogic reservaLogic;
    
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
    
    public List<EstacionEntity> getEstaciones(){
        return persistence.findAll();
    }
    
    public EstacionEntity crearEstacion(EstacionEntity entidad){
        if(entidad.getAccesorios()==null){
            List <AccesorioEntity> lista = new ArrayList<>();
            entidad.setAccesorios(lista);
        }
        else if(entidad.getCalificacion()==null){
            ArrayList <CalificacionEntity> lista = new ArrayList<>();
            entidad.setCalificacion(lista);
        }
        else if(entidad.getDirecciones()==null){
            List <DireccionEntity> lista = new ArrayList<>();
            entidad.setDirecciones(lista);
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
    
    //-----------------------------------------------------------------------------------------------------------------
    
    /**
     * Obtiene una colección de instancias de DireccionEntity asociadas a una
     * instancia de Estacion
     *
     * @param estacionId Identificador de la instancia de Estacion
     * @return Colección de instancias de DireccionEntity asociadas a la instancia de
     * Estacion
     * @generated
     */
    public List<DireccionEntity> listDirecciones(Long estacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", estacionId);
        return getEstacion(estacionId).getDirecciones();
    }

    /**
     * Obtiene una instancia de DireccionEntity asociada a una instancia de Estacion
     *
     * @param estacionId Identificador de la instancia de Estacion
     * @param direccionesId Identificador de la instancia de Direccion
     * @return
     * @generated
     */
    public DireccionEntity getDireccion(Long estacionId, Long direccionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un libro con id = {0}", direccionesId);
        List<DireccionEntity> list = getEstacion(estacionId).getDirecciones();
        DireccionEntity direccionesEntity = new DireccionEntity();
        direccionesEntity.setId(direccionesId);
        int index = list.indexOf(direccionesEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Direccion existente a un Estacion
     *
     * @param estacionId Identificador de la instancia de Estacion
     * @param direccionesId Identificador de la instancia de Direccion
     * @return Instancia de DireccionEntity que fue asociada a Estacion
     * @generated
     */
    public DireccionEntity addDireccion(Long estacionId, Long direccionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregar un libro al estacion con id = {0}", estacionId);
        direccionLogic.addEstacion(direccionesId, estacionId);
        return direccionLogic.getDireccion(direccionesId);
    }

    /**
     * Remplaza las instancias de Direccion asociadas a una instancia de Estacion
     *
     * @param estacionId Identificador de la instancia de Estacion
     * @param list Colección de instancias de DireccionEntity a asociar a instancia
     * de Estacion
     * @return Nueva colección de DireccionEntity asociada a la instancia de Estacion
     * @generated
     */
    public List<DireccionEntity> replaceDirecciones(Long estacionId, List<DireccionEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al estacion con id = {0}", estacionId);
        EstacionEntity estacionEntity = getEstacion(estacionId);
        List<DireccionEntity> direccionList = direccionLogic.getDirecciones();
        for (DireccionEntity direccion : direccionList) {
            if (list.contains(direccion)) {
                if (!direccion.getEstaciones().contains(estacionEntity)) {
                    direccionLogic.addEstacion(direccion.getId(), estacionId);
                }
            } else {
                direccionLogic.removeEstacion(direccion.getId(), estacionId);
            }
        }
        estacionEntity.setDirecciones(list);
        return estacionEntity.getDirecciones();
    }

    /**
     * Desasocia un Direccion existente de un Estacion existente
     *
     * @param estacionId Identificador de la instancia de Estacion
     * @param direccionesId Identificador de la instancia de Direccion
     * @generated
     */
    public void removeDireccion(Long estacionId, Long direccionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del estacion con id = {0}", estacionId);
        direccionLogic.removeEstacion(direccionesId, estacionId);
    }
    
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

    
    public ReservaEntity addReserva(Long estacionId, Long reservasId) {
        reservaLogic.addEstacion(reservasId, estacionId);
        return reservaLogic.getReserva(reservasId);
    }
    
    public List<ReservaEntity> replaceReservas(Long estacionId, List<ReservaEntity> list) {
        EstacionEntity estacionEntity = getEstacion(estacionId);
        List<ReservaEntity> rList = reservaLogic.getReservas();
        for (ReservaEntity reserva : rList) {
            if (list.contains(reserva)) {
                if (!reserva.getEstaciones().contains(estacionEntity)) {
                    reservaLogic.addEstacion(reserva.getId(), estacionId);
                }
            } else {
                reservaLogic.removeEstacion(reserva.getId(), estacionId);
            }
        }
        estacionEntity.setReservas(list);
        return estacionEntity.getReservas();
    }
    public void removeReserva(Long estacionId, Long reservaId) {
        reservaLogic.removeEstacion(reservaId, estacionId);
    }
    public BicicletaEntity getBiciEstacion(Long idEstacion,Long idBici){
        EstacionEntity estacion = persistence.find(idEstacion);
        if(estacion ==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        BicicletaEntity bici = bicicletaLogic.getBIcicleta(idBici);
        boolean esta = false;
        for(BicicletaEntity temp : estacion.getBicis()){
            if(bici.equals(temp)){
                esta = true;
            }
        }
        if(esta=false){
            throw new WebApplicationException("No hay una estación asociada a la bici", 402);
        }
        return bici;
    }
    public List<BicicletaEntity> getBicisEstacion(Long idEstacion){
        EstacionEntity estacion = persistence.find(idEstacion);
        if(estacion ==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        return estacion.getBicis();
    }
    public void upDateBici(EstacionEntity estacion,BicicletaEntity bici){
        EstacionEntity aBorrar = bici.getEstacion();
        aBorrar.getBicis().remove(bici);
        bicicletaLogic.actualizarBicicleta(bici);
        actualizarEstacion(estacion);
    }
}
