/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.ejb;

import co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException;
import co.edu.uniandes.bicicletas.entities.PuntoEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import co.edu.uniandes.bicicletas.persistence.PuntoPersistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Gabriel Pinto
 */
@Stateless
public class PuntoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(PuntoLogic.class.getName());
    
    @Inject
    private PuntoPersistence puntPersistence;
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    public List<PuntoEntity> createPuntos(Long idUsuario)
    {
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date); 
      
        List<PuntoEntity> puntos = usuario.getPuntos();
        List<PuntoEntity> puntosNuevos = new ArrayList<PuntoEntity>(); ;
        PuntoEntity punt;
        boolean crea = false;
        if(puntos == null)
        {
            puntos = new ArrayList<PuntoEntity>();
            crea = true;
        }
        
        for(int i = 0; i < 10; i++)
        {
            punt = new PuntoEntity();
            punt.setFechaPunto(date);
            puntPersistence.create(punt);
            
            puntos.add(0, punt);
            puntosNuevos.add(punt);    
        }
        
        if(crea)
        {
            usuario.setPuntos(puntos);   
        }
        
        return puntosNuevos;
    }
    
    public List<PuntoEntity> getPuntos(Long idUsuario)
    {
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        return usuario.getPuntos();
    }
    
    public void deletePuntos(Long idUsuario) throws BusinessLogicException
    {
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        List<PuntoEntity> puntos = usuario.getPuntos();
        if(puntos == null || puntos.isEmpty() || puntos.size() < 10)
        {
            throw new BusinessLogicException("El usuario no tiene al menos 10 puntos para pagar la reserva");
        }
        else
        {
            int size = puntos.size();
            Long punt = null;
            for (int i = size-1; i >= size - 10; i--) 
            {
                punt = puntos.get(i).getId();
                puntos.remove(i);
                puntPersistence.delete(punt);
            }
        }
    }
}
