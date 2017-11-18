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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    /**
     * Logger de información
     */
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    /**
     * Persistencia de CalificacionEntity
     */
    @Inject
    private CalificacionPersistence caliPersistence;
    
    /**
     * Lógica de ReservaEntity
     */
    @Inject 
    private ReservaLogic reservaLogic;
    
    /**
     * Persistencia de EstacionEntity
     */
    @Inject
    private EstacionPersistence estacionPersistence;
    
    /**
     * Constate que modela una estación sin calificaciones
     */
    public static final String MENSAJE = "La estación que consultó aún no tiene calificaciones";
    
    /**
     * Crea una nueva calificación en la base de datos
     * @param idEstacion
     * @param idReserva Id de la reserva a la cual se le calificará una de las 2 estaciones
     * @param caliEntity Objeto de CalificacionEntity con los datos nuevos
     * @return El objero CalificacionEntity creado
     * @throws BusinessLogicException Se lanza si no se existe la estación que se desea calificar
     */
    public CalificacionEntity createCalificacion(Long idEstacion, Long idReserva, CalificacionEntity caliEntity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de crear una calificación");
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        EstacionEntity estacion = null;
        List<CalificacionEntity> calificaciones = reserva.getCalificaciones();
        CalificacionEntity calSalida = getCalificacionPos(calificaciones, 0);
        
        boolean origen = false;
        boolean noPuede = true;
        
        if (idEstacion == 0 && calSalida == null)
        {           
            origen = true; 
            noPuede = false;
            estacion = estacionPersistence.find(reserva.getEstacionSalida().getId());
        }
        else if(idEstacion == 1)
        {
            if(calSalida == null)
            {
                throw new BusinessLogicException("Antes de calificar la estación de llegada, por favor califica la estación de salida");
            }
            else if(reserva.getEstacionLlegada() == null)
            {
                throw new BusinessLogicException("No es posible calificar la estación de llegada, porque aún no ha sido definida.");
            }
            else if(getCalificacionPos(calificaciones, 1) == null)
            {
                estacion = estacionPersistence.find(reserva.getEstacionLlegada());
                noPuede = false;
            }      
        }
            
        if(noPuede)
        {
            throw new BusinessLogicException("Ya existe una calificación para la estación indicada dentro de la reserva");
        }
                  
        caliEntity.setReserva(reserva);
                     
        caliEntity.setFechaCali(fechaActual());
        caliEntity.setEstacion(estacion);
        CalificacionEntity califiEntity = caliPersistence.create(caliEntity);
        
        if(origen)
        {
            reserva.setCalificacionEstacionSalida(califiEntity);
        }
        else
        {
            reserva.setCalificacionEstacionLlegada(califiEntity);
        }
        LOGGER.info("Termina proceso de crear una calificación");
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
        if (estacion.getCalificaciones() == null || estacion.getCalificaciones().isEmpty()) {
            throw new BusinessLogicException(MENSAJE);
        }
               
        return estacion.getCalificaciones();
    }
    
    /**
     * Devulve la fecha actual con el formato definido
     * @return La fecha actual
     */
    public Date fechaActual()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateFormat.format(date); 
        
        return date;
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
        List<CalificacionEntity> lista = estacionPersistence.find(idEstacion).getCalificaciones();
     
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
        
        List<CalificacionEntity> calificaciones = reserva.getCalificaciones();
        CalificacionEntity caliEntity;
                
        if(cali)
        {
            caliEntity = getCalificacionPos(calificaciones, 0);
        }
        else
        {
            caliEntity = getCalificacionPos(calificaciones, 1);
        }
        
        return caliEntity;
    }
    
    /**
     * Verifica, y devuelve, si ya existe la calificación de la estación de llegada o salida
     * @param calis Calificaciones de la reserva
     * @param pos 0 - Calificacion estacion de salida, 1 - Calificacion estacion de llegada
     * @return calificación de la estación de llegada o salida
     */
    public CalificacionEntity getCalificacionPos(List<CalificacionEntity> calis, int pos)
    {
        CalificacionEntity cali;
       
        try
        {
            cali = calis.get(pos);
            
        }
        catch(IndexOutOfBoundsException e)
        {
            LOGGER.log(Level.SEVERE, "Excepcion:", e);
            return null;
        }
        return cali;
    }
    
    /**
     * Actualiza los datos de una calificación 
     * @param calEntity Objeto con los nuevos datos de la calificación
     * @return Objeto CalificacionEntity con los datos actualizado
     */
    public CalificacionEntity updateCalificacion(CalificacionEntity calEntity) {
        LOGGER.info("Inicia proceso de actualizar una calificacion");  
        calEntity.setFechaCali(fechaActual());
        return caliPersistence.update(calEntity);
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