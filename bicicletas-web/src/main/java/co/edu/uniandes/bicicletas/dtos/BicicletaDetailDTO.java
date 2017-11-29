package co.edu.uniandes.bicicletas.dtos;


import co.edu.uniandes.bicicletas.entities.AccesorioBicicletaEntity;
import co.edu.uniandes.bicicletas.entities.BicicletaEntity;
import java.util.ArrayList;
import java.util.List;

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

/**
 *
 * @author ISIS2603
 */
public class BicicletaDetailDTO extends BicicletaDTO {

    /**
     * Constructor por defecto
     */
    public List<AccesorioBicicletaDTO> accesorios=new ArrayList<>();
    
    
    
    
    
    public BicicletaDetailDTO() {
        //No necesita cuerpo
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public BicicletaDetailDTO(BicicletaEntity entity) {
        super(entity);
        if(entity!=null){
            accesorios = listEntity2DetailDTO(entity.getAccesorioBicicletas());
        }
        
        
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    public BicicletaEntity toEntity() {
        BicicletaEntity bicicleta = super.toEntity();
        bicicleta.setAccesoriosBicicleta(listDTO2Entity(accesorios));
        return bicicleta;
    }
    private List<AccesorioBicicletaDTO> listEntity2DetailDTO(List<AccesorioBicicletaEntity> entityList) {
        List<AccesorioBicicletaDTO> list = new ArrayList<>();
        for (AccesorioBicicletaEntity entity : entityList) {
            list.add(new AccesorioBicicletaDTO(entity));
        }
        return list;
    }
    private List<AccesorioBicicletaEntity> listDTO2Entity(List<AccesorioBicicletaDTO> entityList) {
        List<AccesorioBicicletaEntity> list = new ArrayList<>();
        for (AccesorioBicicletaDTO x : entityList) {
            list.add(x.toEntity());
        }
        return list;
    }
    private List<AccesorioBicicletaDTO> getAcc(){
        return accesorios;
    }
    private void setAcc(List<AccesorioBicicletaDTO> temp){
        accesorios=temp;
    }

}
