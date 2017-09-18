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
    
    //Sin la relación con usuario, no se pueden agregar a este
    public List<PuntoEntity> createPuntos(Long idUsuario)
    {
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date); 
        
        //Cambiar por la lista del usuario
        //Verificar que la lista del usuario no sea null, y cambiar o agregar a estos
        List<PuntoEntity> puntos = new ArrayList<>();
        PuntoEntity punt;
        
        for(int i = 0; i < 10; i++)
        {
            punt = new PuntoEntity();
            punt.setFechaPunto(date);
            puntPersistence.create(punt);
            puntos.add(punt);   
        }
        return puntos;
    }
    
    public ArrayList<PuntoEntity> getPuntos(Long idUsuario)
    {
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        //Se retorna la lista de los puntos del usuario
        return null;
    }
    
    public void deletePuntos(Long idUsuario) throws BusinessLogicException
    {
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        //Verifica si hay al menos 10 puntos, si no, lanza excepción
        
        throw new BusinessLogicException("El usuario no tiene al menos 10 puntos para pagar la reserva"); 
        
        //busca los puntos y los borra
    }
}
