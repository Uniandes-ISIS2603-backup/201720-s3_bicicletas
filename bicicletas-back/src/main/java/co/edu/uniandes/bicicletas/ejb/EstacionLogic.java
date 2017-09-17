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
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.persistence.EstacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author 
 */
@Stateless
public class EstacionLogic 
{
    @Inject
    private EstacionPersistence persistence;
    
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
        persistence.create(entidad);
        return entidad;
    }
    
    public EstacionEntity actualizarEstacion(EstacionEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    
}
