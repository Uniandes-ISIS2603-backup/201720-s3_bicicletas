/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.persistence.AccesorioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ka.babativa
 */
@Stateless
public class AccesorioLogic {
    @Inject
    private AccesorioPersistence persistence;
    
    @Inject
    private EstacionLogic estacionLogic;
    
    @Inject
    private ReservaLogic reservaLogic;
    
    public AccesorioEntity getAccesorio(Long id) throws WebApplicationException{
        AccesorioEntity accesorio = persistence.find(id);
        if(accesorio == null){
            throw new WebApplicationException("No hay un accesorio con dicho ID", 402);
        }
        return accesorio;
    }
    
    public void deleteAccesorio(Long id) throws WebApplicationException{
        AccesorioEntity accesorio = persistence.find(id);
         if(accesorio == null){
             throw new WebApplicationException("No hay un accesorio con dicho ID", 402);
         }
         persistence.delete(id);
    }
    
    public List<AccesorioEntity> getAccesorios(){
        return persistence.findAll();
    }
    
    public AccesorioEntity crearAccesorio(AccesorioEntity entidadA)throws BusinessLogicException{
        
        //Falta que se cree la relación entre Eeserva y Estacion
        EstacionEntity estacion = estacionLogic.getEstacion(entidadA.getEstacion().getId());
        if(estacion == null){
            throw new BusinessLogicException("No se encontró la estación que se desea agregar al sistema");
        }
        
        AccesorioEntity accesorioEntity = persistence.create(entidadA);
        
        List<AccesorioEntity> listaAccesorios = estacion.getAccesorios();
        
        if(listaAccesorios == null){
            listaAccesorios = new ArrayList<>();
            listaAccesorios.add(accesorioEntity);
            estacion.setAccesorios(listaAccesorios);
        }
        
        listaAccesorios.add(accesorioEntity);
        
        return accesorioEntity;
    }
    
    public AccesorioEntity actualizarAccesorio(AccesorioEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una accesorio con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    
    public List<AccesorioEntity> getAccesoriosEstacion(Long idEstacion) throws BusinessLogicException
    {
        EstacionEntity estacion = estacionLogic.getEstacion(idEstacion);
        if (estacion.getAccesorios() == null) {
            throw new BusinessLogicException("La estación que consultó aún no tiene accesorios");
        }
        return estacion.getAccesorios();
    }
    
    public AccesorioEntity getAccesorioEstacion(Long idEstacion, Long idAccesorio) throws BusinessLogicException
    {
        List<AccesorioEntity> lista = estacionLogic.getEstacion(idEstacion).getAccesorios();
        if (lista == null) {
            throw new BusinessLogicException("La estación que consultó aún no tiene accesorios");
        }
        AccesorioEntity aEntidad = new AccesorioEntity();
        aEntidad.setId(idAccesorio);
        int index = lista.indexOf(aEntidad);
        if (index >= 0) {
            return lista.get(index);
        }
        return null;
    }
}
