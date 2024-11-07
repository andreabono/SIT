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
 * <p>Classe Java per MomentTensor complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="MomentTensor"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element name="dataUsed" type="{http://quakeml.org/xmlns/bed/1.2}DataUsed"/&gt;
 *           &lt;element name="comment" type="{http://quakeml.org/xmlns/bed/1.2}Comment"/&gt;
 *           &lt;element name="derivedOriginID" type="{http://quakeml.org/xmlns/bed/1.2}ResourceReference"/&gt;
 *           &lt;element name="momentMagnitudeID" type="{http://quakeml.org/xmlns/bed/1.2}ResourceReference" minOccurs="0"/&gt;
 *           &lt;element name="scalarMoment" type="{http://quakeml.org/xmlns/bed/1.2}RealQuantity" minOccurs="0"/&gt;
 *           &lt;element name="tensor" type="{http://quakeml.org/xmlns/bed/1.2}Tensor" minOccurs="0"/&gt;
 *           &lt;element name="variance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="varianceReduction" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="doubleCouple" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="clvd" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="iso" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="greensFunctionID" type="{http://quakeml.org/xmlns/bed/1.2}ResourceReference" minOccurs="0"/&gt;
 *           &lt;element name="filterID" type="{http://quakeml.org/xmlns/bed/1.2}ResourceReference" minOccurs="0"/&gt;
 *           &lt;element name="sourceTimeFunction" type="{http://quakeml.org/xmlns/bed/1.2}SourceTimeFunction" minOccurs="0"/&gt;
 *           &lt;element name="methodID" type="{http://quakeml.org/xmlns/bed/1.2}ResourceReference" minOccurs="0"/&gt;
 *           &lt;element name="category" type="{http://quakeml.org/xmlns/bed/1.2}MomentTensorCategory" minOccurs="0"/&gt;
 *           &lt;element name="inversionType" type="{http://quakeml.org/xmlns/bed/1.2}MTInversionType" minOccurs="0"/&gt;
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
@XmlType(name = "MomentTensor", propOrder = {
    "dataUsedOrCommentOrDerivedOriginID",
    "any"
})
public class MomentTensor {

    @XmlElementRefs({
        @XmlElementRef(name = "dataUsed", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "comment", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "derivedOriginID", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "momentMagnitudeID", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "scalarMoment", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "tensor", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "variance", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "varianceReduction", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "doubleCouple", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "clvd", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "iso", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "greensFunctionID", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "filterID", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "sourceTimeFunction", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "methodID", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "category", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "inversionType", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "creationInfo", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> dataUsedOrCommentOrDerivedOriginID;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "publicID", required = true)
    protected String publicID;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the dataUsedOrCommentOrDerivedOriginID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the dataUsedOrCommentOrDerivedOriginID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataUsedOrCommentOrDerivedOriginID().add(newItem);
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
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * {@link JAXBElement }{@code <}{@link DataUsed }{@code >}
     * {@link JAXBElement }{@code <}{@link MTInversionType }{@code >}
     * {@link JAXBElement }{@code <}{@link MomentTensorCategory }{@code >}
     * {@link JAXBElement }{@code <}{@link RealQuantity }{@code >}
     * {@link JAXBElement }{@code <}{@link SourceTimeFunction }{@code >}
     * {@link JAXBElement }{@code <}{@link Tensor }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getDataUsedOrCommentOrDerivedOriginID() {
        if (dataUsedOrCommentOrDerivedOriginID == null) {
            dataUsedOrCommentOrDerivedOriginID = new ArrayList<JAXBElement<?>>();
        }
        return this.dataUsedOrCommentOrDerivedOriginID;
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
