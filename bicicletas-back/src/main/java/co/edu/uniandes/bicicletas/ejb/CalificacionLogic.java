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
import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.persistence.CalificacionPersistence;
import co.edu.uniandes.bicicletas.persistence.EstacionPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *Clase lógica de una Calificacion
 * @author gl.pinto10
 */
@Stateless
public class CalificacionLogic 
{
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    @Inject
    private CalificacionPersistence caliPersistence;
    
    @Inject 
    private ReservaLogic reservaLogic;
    
    @Inject
    private EstacionPersistence estacionPersistence;
    
    public static final String MENSAJE = "La estación que consultó aún no tiene calificaciones";
    
    /**
     * Crea una nueva calificación en la base de datos
     * @param idReserva Id de la reserva a la cual se le calificará una de las 2 estaciones
     * @param caliEntity Objeto de CalificacionEntity con los datos nuevos
     * @return El objero CalificacionEntity creado
     * @throws BusinessLogicException Se lanza si no se existe la estación que se desea calificar
     */
    public CalificacionEntity createCalificacion(Boolean origen, Long idReserva, CalificacionEntity caliEntity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de crear una calificación");
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        EstacionEntity estacion;
        
        if(origen && reserva.getCalificacionEstacionOrigen() == null && reserva.getEstacionSalida() != null)
        {
            estacion = reserva.getEstacionSalida();
        }
        else if(!origen && reserva.getCalificacionEstacionLlegada() == null && reserva.getEstacionLlegada() != null )
        {
            estacion = reserva.getEstacionLlegada();
        }
        else
        {
             throw new BusinessLogicException("No es posible calificar la estación");
        }
                      
        caliEntity.setReserva(reserva);
        caliEntity.setEstacion(estacion);
        
        CalificacionEntity califiEntity = caliPersistence.create(caliEntity);
        
        List<CalificacionEntity> listaCalis = estacion.getCalificacion();
        
        listaCalis.add(califiEntity);
        
        if(origen)
        {
            reserva.setCalificacionEstacionOrigen(califiEntity);
        }
        else
        {
            reserva.setCalificacionEstacionLlegada(califiEntity);
        }
        
        return califiEntity;
    }
    
    /**
     * Obtiene la lista de calificaciones de una estacion
     * @param idEstacion Id de la estación de la cual se desean obtener sus calificaciones
     * @return Lista de CalificacionEntity
     * @throws BusinessLogicException Se lanza si la estación no tiene calificaciones 
     */
    public List<CalificacionEntity> getCalificacionesEstacion(Long idEstacion) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de consultar todos las calificaciones de una estación");
        EstacionEntity estacion = estacionPersistence.find(idEstacion);
        if (estacion.getCalificacion() == null || estacion.getCalificacion().isEmpty()) {
            throw new BusinessLogicException(MENSAJE);
        }
               
        return estacion.getCalificacion();
    }
    
    /**
     * Obtiene una calificación de una estación
     * @param idEstacion El id de la estación de la cual se quiere ver la calificacion
     * @param idCalificacion La calificación que se desea observar
     * @return Un objeto CalificacionEntity
     * @throws BusinessLogicException Se la si la estación no tiene calificaciones
     */
    public CalificacionEntity getCalificacionEstacion(Long idEstacion, Long idCalificacion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una calificación de la estación con id = {0}", idEstacion);
        List<CalificacionEntity> lista = estacionPersistence.find(idEstacion).getCalificacion();
     
        if (lista == null || lista.isEmpty()) {
            throw new BusinessLogicException(MENSAJE);
        }
        
        CalificacionEntity caliEntity = caliPersistence.find(idCalificacion);
        int index = lista.indexOf(caliEntity);
        if (index >= 0) 
        {
            return lista.get(index);
        }
        return null;
    }
    
    /**
     * Obtiene la calificación de una estación con base a la reserva
     * @param idReserva Id de la reserva de la cual se quieren observar las calificaciones
     * @param cali false o true dependiendo de si se quiere ver la calificación de la estación de llegada u origen
     * @return Un objeto de CalificacionEntity 
     */
    public CalificacionEntity getCalificionReserva(Long idReserva, boolean cali)
    {
        LOGGER.info("Inicia proceso de consultar una calificación de una reserva");
        
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        
        CalificacionEntity caliEntity = null;
        
        if(cali)
        {
            caliEntity = reserva.getCalificacionEstacionOrigen();
        }
        else
        {
            caliEntity = reserva.getCalificacionEstacionLlegada();
        }
        
        return caliEntity;
    }
    
    /**
     * Actualiza los datos de una calificación 
     * @param idReserva El id que tiene la información de la calificación a actuaalizar
     * @param cali false o true dependiendo de si se quiere ver la calificación de la estación de llegada u origen
     * @param calEntity Objeto con los nuevos datos de la calificación
     * @return Objeto CalificacionEntity con los datos actualizado
     */
    public CalificacionEntity updateCalificacion(Long idReserva, boolean cali, CalificacionEntity calEntity) {
        LOGGER.info("Inicia proceso de actualizar una calificacion");
        
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        CalificacionEntity califi = null;
        
        if(cali && reserva.getCalificacionEstacionOrigen() != null)
        {
            calEntity.setId(reserva.getCalificacionEstacionOrigen().getId());
            califi = caliPersistence.update(calEntity);
        }
        else if(!cali && reserva.getEstacionLlegada() != null)
        {
            calEntity.setId(reserva.getCalificacionEstacionLlegada().getId());
            califi = caliPersistence.update(calEntity);
        }
                
        return califi;
    }
   
    /**
     * Obtiene una calificación con base a su id
     * @param idCali Id de la calificación que se desea consultar
     * @return La calificacón consultado
     */
    public CalificacionEntity getCalificacion(Long idCali)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una calificación con id={0}", idCali);
        CalificacionEntity calificacion = caliPersistence.find(idCali);
        if (calificacion == null) {
            LOGGER.log(Level.SEVERE, "La calificación con el id {0} no existe", idCali);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar una califiacón con id={0}", idCali);
        return calificacion;
    }
    
    /**
     * Obtiene todas las calificaciones almacenadas en el sistema
     * @return Lista de CalificacionEntity
     */
    public List<CalificacionEntity> getCalificaciones()
    {
        LOGGER.info("Inicia proceso de consultar todas las calificaciones");
        List<CalificacionEntity> calificaciones = caliPersistence.findAll();
        LOGGER.info("Termina proceso de consultar todos las califiaciones");
        return calificaciones;
    }

}
