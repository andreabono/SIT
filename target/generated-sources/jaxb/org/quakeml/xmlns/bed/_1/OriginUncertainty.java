//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.2 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.09.05 alle 11:56:25 AM CEST 
//


package org.quakeml.xmlns.bed._1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Classe Java per OriginUncertainty complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="OriginUncertainty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element name="horizontalUncertainty" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="minHorizontalUncertainty" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="maxHorizontalUncertainty" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="azimuthMaxHorizontalUncertainty" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="confidenceEllipsoid" type="{http://quakeml.org/xmlns/bed/1.2}ConfidenceEllipsoid" minOccurs="0"/&gt;
 *           &lt;element name="preferredDescription" type="{http://quakeml.org/xmlns/bed/1.2}OriginUncertaintyDescription" minOccurs="0"/&gt;
 *           &lt;element name="confidenceLevel" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;/choice&gt;
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OriginUncertainty", propOrder = {
    "horizontalUncertaintyOrMinHorizontalUncertaintyOrMaxHorizontalUncertainty",
    "any"
})
public class OriginUncertainty {

    @XmlElementRefs({
        @XmlElementRef(name = "horizontalUncertainty", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "minHorizontalUncertainty", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "maxHorizontalUncertainty", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "azimuthMaxHorizontalUncertainty", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "confidenceEllipsoid", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "preferredDescription", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "confidenceLevel", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> horizontalUncertaintyOrMinHorizontalUncertaintyOrMaxHorizontalUncertainty;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the horizontalUncertaintyOrMinHorizontalUncertaintyOrMaxHorizontalUncertainty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the horizontalUncertaintyOrMinHorizontalUncertaintyOrMaxHorizontalUncertainty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHorizontalUncertaintyOrMinHorizontalUncertaintyOrMaxHorizontalUncertainty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link ConfidenceEllipsoid }{@code >}
     * {@link JAXBElement }{@code <}{@link OriginUncertaintyDescription }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getHorizontalUncertaintyOrMinHorizontalUncertaintyOrMaxHorizontalUncertainty() {
        if (horizontalUncertaintyOrMinHorizontalUncertaintyOrMaxHorizontalUncertainty == null) {
            horizontalUncertaintyOrMinHorizontalUncertaintyOrMaxHorizontalUncertainty = new ArrayList<JAXBElement<?>>();
        }
        return this.horizontalUncertaintyOrMinHorizontalUncertaintyOrMaxHorizontalUncertainty;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link Element }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
