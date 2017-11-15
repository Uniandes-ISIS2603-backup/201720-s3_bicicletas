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
package co.edu.uniandes.bicicletas.entities;

import co.edu.uniandes.bicicletas.entities.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ISIS2603,js.torres1
 */
@Entity
public class BicicletaEntity extends BaseEntity implements Serializable {
   //Las contantes que indican el estado de la bicicleta
    public static final int DANADO=3;
    public static final int DISPONIBLE=2;
    public static final int PRESTADA=1;
    public static final int RESERVADA=0;
    //este atributo da el estado de la bicicleta
    private int estado;
    //este atributo da la marca de la bicicleta
    private String marca;
    //este atributo da modelo de la bicicleta
    private String modelo;
    /**
     * Este atributo moldea la relacion que tiene con los accesoriosBicicleta.
     */
    @PodamExclude
    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<AccesorioBicicletaEntity> accesorios = new ArrayList<>();
    /**
     * Este atributo moldea la relacion que tiene con las estaciones.
     */
    @PodamExclude
    @ManyToOne
    @XmlInverseReference(mappedBy="bicicletas")
    private EstacionEntity estacion;
    /**
     * Este atributo moldea las relaciones que tiene con reserva.
     */
    @PodamExclude
    @ManyToOne
    @XmlInverseReference(mappedBy="bicicletas")
    private ReservaEntity reserva;
    /**
     * Este metodo permite cambiar el estado del Entity bicicleta en su marca.
     * @param pMarca 
     */
    public void setMarca(String pMarca){
        marca=pMarca;
    }
    /**
     * Este metodo modela del Entity bicicleta en su estado si esta disponible,
     * en uso, dañada y reservada.
     * @param rEstado 
     */
    public void setEstado(int rEstado){
        estado=rEstado;
    }
    /**
     * Este modelo moldea el estado del Entity bicicleta en su modelo.
     * @param pModelo 
     */
    public void setModelo(String pModelo){
        modelo=pModelo;
    }
    /**
     * Retorna el estado de la bicicleta si es dañada, en uso, reservada o disponible
     * @return int estado
     */
    public int darEstado(){
        return estado;
    }
    /**
     * Este metodo retorna el estado modelo del Entity bicicleta.
     * @return String modelo
     */
    public String darModelo(){
        return modelo;
    }
    /**
     * Este metodo retorna la marca del Entity bicicleta
     * @return String marca
     */
    public String darMarca(){
        return marca;
    }
    /**
     * Obtiene los accesorios Bicicleta.
     * @return lista de accesoriosBicicleta.
     */
    public List<AccesorioBicicletaEntity> getAccesorioBicicletas(){
        return accesorios;
    }
    /**
     * Set de los accesoriosBicicleta.
     * @param temp lista nueva de accesoriosBicicleta.
     */
    public void setAccesoriosBicicleta(List<AccesorioBicicletaEntity> temp){
        accesorios=temp;
    }
    /**
     * Set de la estacion de la bicicleta.
     * @param temp estacion de la bicicleta.
     */
    public void setEstacion(EstacionEntity temp){
        estacion = temp;
    }
    /**
     * Obtiene la estacion de la bicicleta.
     * @return la estacion de la bicicleta.
     */
    public EstacionEntity getEstacion(){
        return estacion;
    }
    /**
     * Set de la reserva de la bicicleta.
     * @param temp reserva de la bicicelta.
     */
    public void setReserva(ReservaEntity temp){
        reserva = temp;
    }
    /**
     * Obtiene la reserva de la bicicleta.
     * @return reserva de la bicicleta.
     */
    public ReservaEntity getReserva(){
        return reserva;
    }
}
