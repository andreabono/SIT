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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Classe Java per Arrival complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Arrival"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element name="comment" type="{http://quakeml.org/xmlns/bed/1.2}Comment"/&gt;
 *           &lt;element name="pickID" type="{http://quakeml.org/xmlns/bed/1.2}ResourceReference"/&gt;
 *           &lt;element name="phase" type="{http://quakeml.org/xmlns/bed/1.2}Phase"/&gt;
 *           &lt;element name="timeCorrection" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="azimuth" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="distance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="takeoffAngle" type="{http://quakeml.org/xmlns/bed/1.2}RealQuantity" minOccurs="0"/&gt;
 *           &lt;element name="timeResidual" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="horizontalSlownessResidual" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="backazimuthResidual" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="timeWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="horizontalSlownessWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="backazimuthWeight" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="earthModelID" type="{http://quakeml.org/xmlns/bed/1.2}ResourceReference" minOccurs="0"/&gt;
 *           &lt;element name="creationInfo" type="{http://quakeml.org/xmlns/bed/1.2}CreationInfo" minOccurs="0"/&gt;
 *         &lt;/choice&gt;
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="publicID" use="required" type="{http://quakeml.org/xmlns/bed/1.2}ResourceReference" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Arrival", propOrder = {
    "commentOrPickIDOrPhase",
    "any"
})
public class Arrival {

    @XmlElementRefs({
        @XmlElementRef(name = "comment", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "pickID", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "phase", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "timeCorrection", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "azimuth", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "distance", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "takeoffAngle", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "timeResidual", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "horizontalSlownessResidual", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "backazimuthResidual", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "timeWeight", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "horizontalSlownessWeight", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "backazimuthWeight", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "earthModelID", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "creationInfo", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> commentOrPickIDOrPhase;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "publicID", required = true)
    protected String publicID;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the commentOrPickIDOrPhase property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the commentOrPickIDOrPhase property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommentOrPickIDOrPhase().add(newItem);
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
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * {@link JAXBElement }{@code <}{@link Phase }{@code >}
     * {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getCommentOrPickIDOrPhase() {
        if (commentOrPickIDOrPhase == null) {
            commentOrPickIDOrPhase = new ArrayList<JAXBElement<?>>();
        }
        return this.commentOrPickIDOrPhase;
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
     * Recupera il valore della proprietà publicID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicID() {
        return publicID;
    }

    /**
     * Imposta il valore della proprietà publicID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicID(String value) {
        this.publicID = value;
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
