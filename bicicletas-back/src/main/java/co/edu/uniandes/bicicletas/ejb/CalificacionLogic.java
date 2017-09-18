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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
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
    private EstacionLogic estacionLogic;
    
    
    public CalificacionEntity createCalificacion(Long idReserva, CalificacionEntity caliEntity) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de crear una calificación");
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        
        if(reserva == null)
        {
            throw new BusinessLogicException("No se puede agregar una calificación a una reserva que no existe en el sistema");
        }
        
        if(reserva.getCalificacionEstacionLlegada() != null && reserva.getCalificacionEstacionOrigen() != null )
        {
             throw new BusinessLogicException("Ya existe una calificación tanto para la estación de origen como para la de llegada");
        }
        caliEntity.setIdReserva(reserva.getId());
        
        EstacionEntity estacion = estacionLogic.getEstacion(caliEntity.getIdEstacion());
        if(estacion == null)
        {
            throw new BusinessLogicException("No se encontró la estación que se desea calificar");
        }
        
        CalificacionEntity califiEntity = caliPersistence.create(caliEntity);
        
        ArrayList<CalificacionEntity> listaCalis = estacion.getCalificaciones();
        
        if(listaCalis == null)
        {
            listaCalis = new ArrayList<>();
            listaCalis.add(califiEntity);
            estacion.setCalificaciones(listaCalis);
        }
        
        listaCalis.add(califiEntity);
        
        if(reserva.getCalificacionEstacionOrigen() == null)
        {
            reserva.setCalificacionEstacionOrigen(califiEntity);
        }
        
        reserva.setCalificacionEstacionLlegada(califiEntity);
       
        return califiEntity;
    }
    
    public List<CalificacionEntity> getCalificacionesEstacion(Long idEstacion) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de consultar todos las calificaciones de una estación");
        EstacionEntity estacion = estacionLogic.getEstacion(idEstacion);
        if (estacion.getCalificaciones() == null) {
            throw new BusinessLogicException("La estación que consultó aún no tiene calificaciones");
        }
        if (estacion.getCalificaciones().isEmpty()) {
            throw new BusinessLogicException("La estación que consultó aún no tiene calificaciones");
        }
        
        return estacion.getCalificaciones();
    }
    
    public CalificacionEntity getCalificacionEstacion(Long idEstacion, Long idCalificacion) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una calificación de la estación con id = {0}", idEstacion);
        List<CalificacionEntity> lista = estacionLogic.getEstacion(idEstacion).getCalificaciones();
     
        if (lista == null) {
            throw new BusinessLogicException("La estación que consultó aún no tiene calificaciones");
        }
        if (lista.isEmpty()) {
            throw new BusinessLogicException("La estación que consultó aún no tiene calificaciones");
        }
        CalificacionEntity caliEntity = new CalificacionEntity();
        caliEntity.setId(idCalificacion);
        int index = lista.indexOf(caliEntity);
        if (index >= 0) 
        {
            return lista.get(index);
        }
        return null;
    }
    
    public CalificacionEntity getCalificionReserva(Long idReserva, Integer cali) throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de consultar una calificación de una reserva");
        
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        
        CalificacionEntity caliEntity = null;
        
        if(cali == 0)
        {
            caliEntity = reserva.getCalificacionEstacionOrigen();
        }
        else if(cali == 1)
        {
            caliEntity = reserva.getCalificacionEstacionLlegada();
        }
        
        return caliEntity;
    }
    
    public CalificacionEntity updateCalificacion(Long idReserva, Integer cali, CalificacionEntity calEntity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualizar una calificacion");
        ReservaEntity reserva = reservaLogic.getReserva(idReserva);
        CalificacionEntity caliActualizada = caliPersistence.update(calEntity);
        
        if(cali == 0)
        {
            reserva.setCalificacionEstacionOrigen(caliActualizada);
        }
        else if(cali == 1)
        {
            reserva.setCalificacionEstacionLlegada(caliActualizada);
        }
        
        return caliActualizada;
    }
   
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
    
    public List<CalificacionEntity> getCalificaciones()
    {
        LOGGER.info("Inicia proceso de consultar todas las calificaciones");
        List<CalificacionEntity> calificaciones = caliPersistence.findAll();
        LOGGER.info("Termina proceso de consultar todos las califiaciones");
        return calificaciones;
    }
    
}
