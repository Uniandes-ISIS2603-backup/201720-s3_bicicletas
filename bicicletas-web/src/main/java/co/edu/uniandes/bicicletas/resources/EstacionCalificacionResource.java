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
package co.edu.uniandes.bicicletas.resources;
import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.dtos.CalificacionDTO;
import co.edu.uniandes.bicicletas.ejb.CalificacionLogic;
import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "calificaciones"
 * URI: estaciones/{idEstacion: \\d+}/calificaciones
 * @author Gabriel Pinto
 */
@Produces("application/json")
@Consumes("application/json")
public class EstacionCalificacionResource 
{
    /**
     * Lógica de CalificacionEntity
     */
    @Inject
    private CalificacionLogic calificacionLogic;
    
    /**
     * Retorna la lista de calificaciones de la estación
     * @param idEstacion El id de la estación
     * @return Las calificaciones de la estación 
     * @throws BusinessLogicException Se lanza si la estación no tiene calificaciones
     */
    @GET
    public List<CalificacionDTO> getCalificaciones(@PathParam("idEstacion") Long idEstacion) throws BusinessLogicException
    {
        return listEntity2DTO(calificacionLogic.getCalificacionesEstacion(idEstacion));
    }
    
    /**
     * Obtiene una calificación en especifico de una estación
     * @param idEstacion El id de la estación 
     * @param idCali El id de la calificación a consultar
     * @return La calificación consultada
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("idEstacion") Long idEstacion, @PathParam("id") Long idCali) throws BusinessLogicException
    {
        CalificacionEntity caliEntity = calificacionLogic.getCalificacionEstacion(idEstacion, idCali);
        if(caliEntity == null)
        {
            throw new WebApplicationException("El recurso /estaciones/" + idEstacion + "/calificaciones/" + idCali + " no existe", 404);
        }
        return new CalificacionDTO(caliEntity);
    }
    
    /**
     * Convierte una lista de objetos de tipo entity en una lista de objetos de tipo DTO
     * @param listaEntiCali La lista que se quiere convertir
     * @return La lista de calificaciones de tipo DTO
     */
    private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> listaEntiCali)
    {
        List<CalificacionDTO> lista = new ArrayList<>();
        for(CalificacionEntity caliEntity : listaEntiCali)
        {
            lista.add(new CalificacionDTO(caliEntity));
        }
        return lista;
    }
}
