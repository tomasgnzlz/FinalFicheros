/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea7e;


import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author tomas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogoApps {
    
    @XmlElementWrapper(name="catalogo")
    
    @XmlElement(name="app")
    private List<App> listaApps;

    public List<App> getListaApps() {
        return listaApps;
    }

    public void setListaApps(List<App> listaApps) {
        this.listaApps = listaApps;
    }
    
    
    
}
