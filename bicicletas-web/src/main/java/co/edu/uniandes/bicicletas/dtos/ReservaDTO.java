/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bicicletas.dtos;
import co.edu.uniandes.bicicletas.entities.AccesorioEntity;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import co.edu.uniandes.bicicletas.entities.CalificacionEntity;
import co.edu.uniandes.bicicletas.entities.EstacionEntity;
import java.util.Date;
import co.edu.uniandes.bicicletas.entities.ReservaEntity;
import co.edu.uniandes.bicicletas.entities.TransaccionEntity;
import co.edu.uniandes.bicicletas.entities.UsuarioEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Dto de reserva 
 * @author ds.chacon
 */
public class ReservaDTO {
    
    
    /**
     *id de la reserva 
     */
    private Long idReserva;
    /**
     * estado de la reserva
     */
    private int estado;
    /**
     * usuario de la reserva
     */
    private UsuarioEntity usuarioReserva;
    /**
     * fecha de creacion de la reserva 
     */
    private String fechaReserva;
    /**
     *fecha inicio de la reserva 
     */
    private String fechaInicio;
    /**
     * Fecha fin de la reserva 
     */
    private String fechaFin;
    /**
     * estacion salida
     */
    private EstacionEntity estacionSalida;
    /**
     * Estacion de llegada
     */
    private Long estacionLlegada;
    /**
     * Precio Final
     */
    private double precioFinal;
    /**
     * boooleano que indica si hay Descuento 
     */
    private Boolean descuento;
    /**
     * Trnsaccion de la reserva
     */
    private TransaccionEntity transaccion;
    /**
     * Estacion de llegada
     */
    private EstacionEntity mostrarLlegada;
    
    /**
     * Calificación de la estación de salida
     */
    private CalificacionEntity calificacionSalida;
    
    /**
     * Calificación de la estación de salida
     */
    private CalificacionEntity calificacionLlegada;
    
    /**
     * Bicicletas de la reserva
     */
    private List<BicicletaEntity> bicicletas;
    
    /**
     * Accesorios de la reserva
     */
    private List<AccesorioEntity> accesorios;
    
    /**
     * constructor vacio
     */
    public ReservaDTO(){
        //No necesita cuerpo
    }
    
    /**
     * 
     * @param entidad 
     */
    public ReservaDTO(ReservaEntity entidad) {
        if(entidad!=null){
            this.idReserva = entidad.getId();
            this.usuarioReserva = entidad.getUsuarioReserva();
            this.estado = entidad.getEstado();
            this.fechaInicio = fechaOrg(entidad.getFechaInicio());
            this.estacionSalida = entidad.getEstacionSalida();
            this.fechaFin = fechaOrg(entidad.getFechaEntrega());
            this.precioFinal = entidad.getPrecioFinal();
            this.fechaReserva = entidad.getFechaReserva().toString();
            this.descuento = entidad.getDescuento();
            this.transaccion = entidad.getTransaccion();
            this.estacionLlegada = entidad.getEstacionLlegada();
            
            if(!entidad.getCalificaciones().isEmpty())
            {
                this.calificacionSalida = entidad.getCalificaciones().get(0);
            }
            if(entidad.getCalificaciones().size() == 2)
            {
                this.calificacionLlegada = entidad.getCalificaciones().get(1);
            }
            
            if(!entidad.getBicicletas().isEmpty())
            {
                this.bicicletas = entidad.getBicicletas();
            }
            
            if(!entidad.getAccesorios().isEmpty())
            {
                this.accesorios = entidad.getAccesorios();
            }
            
            
        }
    }
    
    /**
     * 
     * @return 
     */
    public Long getEstacionLlegada() {
        return estacionLlegada;
    }

    /**
     * 
     * @param estacionLlegada 
     */
    public void setEstacionLlegada(Long estacionLlegada) {
        this.estacionLlegada = estacionLlegada;
    }

    /**
     * 
     * @return 
     */
    public String isuue(){
        return fechaReserva+"000";
    }
    
    /**
     * 
     * @return 
     */
    public EstacionEntity getMostrarLlegada() {
        return mostrarLlegada;
    }

    /**
     * 
     * @param mostrarLlegada 
     */
    public void setMostrarLlegada(EstacionEntity mostrarLlegada) {
        this.mostrarLlegada = mostrarLlegada;
    }
    

    
    
    /**
     * 
     * @param fecha
     * @return 
     */
    public String fechaOrg(Date fecha){
        String rta = fecha.toString();
        return rta.substring(0,10)+" "+rta.substring(24,28)+" "+rta.substring(11,19);
    }
    /**
     * 
     * @return 
     */
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(this.getIdReserva());
        entity.setUsuarioReserva(this.getUsuarioReserva());
        entity.setEstado(this.getEstado());
        entity.setFechaInicio(this.pasceFecha(fechaInicio));
        entity.setEstacionSalida(this.getEstacionSalida());
        entity.setFechaEntrega(this.pasceFecha(fechaFin));   
        entity.setPrecioFinal(this.getPrecioFinal());
        entity.setFechaReserva(this.getFechaReserva());
        entity.setEstacionLlegada(this.estacionLlegada);
        entity.setDescuento( );
        entity.setTransaccion(transaccion);
        return entity;
    }

    /**
     * 
     * @return 
     */
     public Date getFechaReserva() {
        return new GregorianCalendar().getTime();
    }
/**
 * 
 * @param fechaReserva 
 */
    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * 
     * @return 
     */
    public boolean isDescuento() {
        return descuento;
    }

    /**
     * 
     * @param descuento 
     */
    public void setDescuento(boolean descuento) {
        this.descuento = descuento;
    }
    /**
     * @return the idReserva
     */
    public Long getIdReserva() {
        return idReserva;
    }

    /**
     * @param idReserva the idReserva to set
     */
    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * @return the usuarioReserva
     */
    public UsuarioEntity getUsuarioReserva() {
        return usuarioReserva;
    }

    /**
     * @param usuarioReserva the usuarioReserva to set
     */
    public void setUsuarioReserva(UsuarioEntity usuarioReserva) {
        this.usuarioReserva = usuarioReserva;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaInicio
     */
    public String getFechaInicio() {
       return this.fechaInicio;
    }
    
    private String darMes( String pmes){
        
        switch(pmes){
            case "Jan":
                return "01";
            case "Feb":
                return "02";
            case "Mar":
                return "03";
            case "Apr":
                return "04";
            case "May":
                return "05";
            case"Jun":
               return "06";
            case "Jul":
                return "07";
            case "Aug":
                return "08";
            case "Sep":
                return "09";
            case "Oct":
                return "10";
            case "Nov":
                return "11";
            case"Dec":
                return "12";
            default:
                return"00";
        }
    }
    
    /**
     * 
     * @param pfecha
     * @return 
     */
    public Date pasceFecha ( String pfecha  ){
        
        String mes = darMes(pfecha.substring(4,7));
        String dia = pfecha.substring(7,10);
        String anio = pfecha.substring(10,15);
        String hora = pfecha.substring(15,24);
        
        String newFecha = dia+"/"+mes+"/"+anio+" "+hora;
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return formato.parse(newFecha);
        } catch (ParseException ex) {
           return new Date(2001, 01,01,01,00,00);
        }
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the estacionSalida
     */
    public EstacionEntity getEstacionSalida() {
        return estacionSalida;
    }

    /**
     * @param estacionSalida the estacionSalida to set
     */
    public void setEstacionSalida(EstacionEntity estacionSalida) {
        this.estacionSalida = estacionSalida;
    }

    /**
     * @return the fechaEntrega
     */
    public String getFechaEntrega() {
        return this.fechaFin;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(String fechaEntrega) {
        this.fechaFin = fechaEntrega;
    }
    /**
     * @return the precioFinal
     */
    public double getPrecioFinal() {
        return precioFinal;
    }

    /**
     * @param precioFinal the precioFinal to set
     */
    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }
    
    /**
     * 
     * @return 
     */

    public TransaccionEntity getTransaccion() {
        return transaccion;
    }
    /**
    * 
    * @param transaccion 
    */
    public void setTransaccion(TransaccionEntity transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * @return the calificacionSalida
     */
    public CalificacionEntity getCalificacionSalida() {
        return calificacionSalida;
    }

    /**
     * @param calificacionSalida the calificacionSalida to set
     */
    public void setCalificacionSalida(CalificacionEntity calificacionSalida) {
        this.calificacionSalida = calificacionSalida;
    }

    /**
     * @return the calificacionLlegada
     */
    public CalificacionEntity getCalificacionLlegada() {
        return calificacionLlegada;
    }

    /**
     * @param calificacionLlegada the calificacionLlegada to set
     */
    public void setCalificacionLlegada(CalificacionEntity calificacionLlegada) {
        this.calificacionLlegada = calificacionLlegada;
    }

    /**
     * @return the bicicletas
     */
    public List<BicicletaEntity> getBicicletas() {
        return bicicletas;
    }

    /**
     * @param bicicletas the bicicletas to set
     */
    public void setBicicletas(List<BicicletaEntity> bicicletas) {
        this.bicicletas = bicicletas;
    }

    /**
     * @return the accesorios
     */
    public List<AccesorioEntity> getAccesorios() {
        return accesorios;
    }

    /**
     * @param accesorios the accesorios to set
     */
    public void setAccesorios(List<AccesorioEntity> accesorios) {
        this.accesorios = accesorios;
    }
    
    
    
    

}