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
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import co.edu.uniandes.bicicletas.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ds.chacon
 */
@Stateless
public class ReservaLogic 
{
    @Inject
    private ReservaPersistence persistence;
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    @Inject 
    private BicicletaLogic biciLogic;
    
    public ReservaEntity getReserva(Long id)
    {
        //Toca agregarle más cosas, solo lo hice provisional
         ReservaEntity reserva = persistence.find(id);
         
         return reserva;
    }
    
    public List<ReservaEntity> getReservas(){
        return persistence.findAll();
    }
    
     public void deleteReserva(Long id) throws WebApplicationException
    {
         ReservaEntity Reserva = persistence.find(id);
         if(Reserva == null){
             throw new WebApplicationException("No hay una estación con dicho ID", 402);
         }
         persistence.delete(id);
    }
    public ReservaEntity crearReserva(Long idUsuario){
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        
        List<ReservaEntity> reservas = usuario.getReservas();
        List<ReservaEntity> reservaNuevo = new ArrayList<>();
        ReservaEntity reserva;
        boolean crea = false;
        if(reservas == null){
            reservas = new ArrayList<>();
            crea = true;
        }
        reserva = new ReservaEntity();
        reserva.setUsuarioReserva(usuario);
        persistence.create(reserva);
        if(crea)
            usuario.setReservas(reservas);
       
        return reserva;
    }
    
    public ReservaEntity actualizarReserva(ReservaEntity entidad) throws WebApplicationException{
        if(persistence.find(entidad.getId())==null){
            throw new WebApplicationException("No hay una estación con dicho id", 402);
        }
        return persistence.update(entidad);
    }
    
    public List<EstacionEntity> listEstaciones(Long reservaId){
        return getReserva(reservaId).getEstaciones();
    }
    
    public EstacionEntity getEstacion(Long reservaId, Long estacionId){
        List<EstacionEntity> lista = getReserva(reservaId).getEstaciones();
        EstacionEntity estacion = new EstacionEntity();
        estacion.setId(estacionId);
        int index = lista.indexOf(estacion);
        if(index >= 0){
            return lista.get(index);
        }
        return null;
    }
    
    public EstacionEntity addEstacion(Long reservaId, Long estacionesId){
        ReservaEntity reserva = getReserva(reservaId);
        EstacionEntity estacionEntity = new EstacionEntity();
        estacionEntity.setId(estacionesId);
        if(reserva.getEstaciones().size()<2){
            reserva.getEstaciones().add(estacionEntity);
            return getEstacion(reservaId, estacionesId);
        }
        else
            return null;
    }
    
    public List<EstacionEntity> replaceEstacion(Long reservaId, List<ReservaEntity> list) {
        ReservaEntity rEntity = getReserva(reservaId);
        EstacionEntity salida = rEntity.getEstacionSalida();
         EstacionEntity llegada = rEntity.getEstacionLlegada();
        rEntity.setEstaciones(salida, llegada);
        return rEntity.getEstaciones();
    }
    
    public void removeEstacion(Long reservaId, Long estacionId){
        ReservaEntity entity = getReserva(reservaId);
        EstacionEntity eEntity = new EstacionEntity();
        eEntity.setId(estacionId);
        entity.getEstaciones().remove(eEntity);
    }
    public ReservaEntity asignarBicicleta(Long idReserva, Long  idBici) throws BusinessLogicException{
        ReservaEntity reserva = getReserva(idReserva);
        BicicletaEntity bici = biciLogic.getBIcicleta(idBici);
        if(bici.darEstado()!=BicicletaEntity.DISPONIBLE){
            throw new BusinessLogicException("No esta disponible la bici");
        }
        boolean a = false;
        for (BicicletaEntity bicicletaR : reserva.getBicis()) {
            if(bicicletaR.equals(bici)){
                a=true;
            }
        }
        if(a){
            throw new BusinessLogicException("No esta disponible la bici");
        }
        reserva.getBicis().add(bici);
        bici.setEstado(BicicletaEntity.RESERVADA);
        return reserva;
    }
    public BicicletaEntity getBici(Long idReserva, Long  idBici) throws BusinessLogicException{
        ReservaEntity reserva = getReserva(idReserva);
        BicicletaEntity bici = biciLogic.getBIcicleta(idBici);
        if(bici.darEstado()!=BicicletaEntity.DISPONIBLE){
            throw new BusinessLogicException("No esta disponible la bici");
        }
        boolean a = false;
        for (BicicletaEntity bicicletaR : reserva.getBicis()) {
            if(bicicletaR.equals(bici)){
                a=true;
            }
        }
        if(!a){
            throw new BusinessLogicException("No esta disponible la bici");
        }
        return bici;
    }
    public List<BicicletaEntity> getBicis(Long idReserva)throws BusinessLogicException{
        ReservaEntity reserva = getReserva(idReserva);
        List<BicicletaEntity> bicis = reserva.getBicis();
        return bicis;
    }
}
