/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tomas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogoPojo {

    @XmlElementWrapper(name="catalogo")
    
    @XmlElement(name="clasepojo")
    private List<ClasePojo> lista;

    
    public CatalogoPojo() {
        
    }

    public List<ClasePojo> getLista() {
        return lista;
    }

    public void setLista(List<ClasePojo> lista) {
        this.lista = lista;
    }

}
