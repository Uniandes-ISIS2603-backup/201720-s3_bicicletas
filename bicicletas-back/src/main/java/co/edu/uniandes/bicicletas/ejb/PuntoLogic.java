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
import co.edu.uniandes.bicicletas.entities.PuntoEntity;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import co.edu.uniandes.bicicletas.persistence.PuntoPersistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase lógica un Punto
 * @author gl.pinto10
 */
@Stateless
public class PuntoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(PuntoLogic.class.getName());
    
    @Inject
    private PuntoPersistence puntPersistence; // Variable para acceder a la persistencia de la aplicación.
    
    @Inject
    private UsuarioLogic usuarioLogic; //Logica del usuario
    
    /**
     * Se encarga de crear 10 puntos en la base de datos
     * @param idUsuario Id del usuario al cual se le crearán los puntos
     * @return Lista de PuntoEntity creados
     * @throws co.edu.uniandes.baco.bicicletas.exceptions.BusinessLogicException
     */
    public PuntoEntity createPunto(Long idUsuario) throws BusinessLogicException
    {
        LOGGER.info("Empieza el proceso de crear 1 punto");
        
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        List<ReservaEntity> reservas = usuario.getReservas();        
        List<PuntoEntity> puntos = usuario.getPuntos();
        
        if(reservas.isEmpty() || reservas.size() < (puntos.size() + 1)) 
        {
            throw new BusinessLogicException("No se puede agregar un punto al usuario");
        }
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date vence = cal.getTime();
        dateFormat.format(date); 
        dateFormat.format(vence);
     
        PuntoEntity punt, punto;
        
        punt = new PuntoEntity();
        punt.setFechaPunto(date);
        punt.setFechaVencimiento(vence);
        punt.setUsuarioPunto(usuario);
        punto = puntPersistence.create(punt);
        
        LOGGER.info("Terminar el proceso crear 1 punto");
        
        return punto;
    }
    
    /**
     * Obtiene la lista de puntos de un usuario
     * @param idUsuario Id del usuario de los puntos a consultar
     * @return Lista de objetos PuntoEntity
     */
    public List<PuntoEntity> getPuntos(Long idUsuario)
    {
        LOGGER.info("Inicia el proceso de consultar los puntos de un usuario");
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        verificarFechaVencimiento(usuario, date);
        LOGGER.info("Termina el proceso de consultar los puntos de un usuario");
        usuario.getPuntos();
        return usuario.getPuntos();
    }
    
    /**
     * Elimina 10 objetos PuntoEntity de la base de datos y de la lista de puntos de un usuario
     * @param idUsuario Id del usuario al cual se le borrarán los puntos
     * @throws BusinessLogicException Se lanza si el usuario no tiene al menos 10 puntos
     */
    public void deletePuntos(Long idUsuario) throws BusinessLogicException
    {
        LOGGER.info("Inicia el proceso de borrar los puntos de un usuario");
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        List<PuntoEntity> puntos = usuario.getPuntos();
        if(puntos == null || puntos.isEmpty() || puntos.size() < 10)
        {
            throw new BusinessLogicException("El usuario no tiene al menos 10 puntos para pagar la reserva");
        }
        else
        {
            PuntoEntity punt;
            for (int i = 0; i < 10; i++) 
            {
                punt = puntos.remove(0);
                puntPersistence.delete(punt.getId());
            }
        }
        LOGGER.info("Termina el proceso de borrar 10 puntos de un usuario");
    }
    
    public void verificarFechaVencimiento(UsuarioEntity usuario, Date fechaActual)
    {
        List<PuntoEntity> puntos = usuario.getPuntos();
        int cantidad = puntos.size();
        Long idPunto;
        for(int i = cantidad - 1; i >= 0; i--)
        {
            idPunto = puntos.get(i).getId();
            PuntoEntity punt = puntPersistence.find(idPunto);
            if(punt.getFechaVencimiento().before(fechaActual))
            {
                puntos.remove(punt);
                puntPersistence.delete(idPunto); 
            }
        }
    }
}
