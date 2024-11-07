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
 * <p>Classe Java per FocalMechanism complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FocalMechanism"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element name="waveformID" type="{http://quakeml.org/xmlns/bed/1.2}WaveformStreamID"/&gt;
 *           &lt;element name="comment" type="{http://quakeml.org/xmlns/bed/1.2}Comment"/&gt;
 *           &lt;element name="momentTensor" type="{http://quakeml.org/xmlns/bed/1.2}MomentTensor"/&gt;
 *           &lt;element name="triggeringOriginID" type="{http://quakeml.org/xmlns/bed/1.2}ResourceReference" minOccurs="0"/&gt;
 *           &lt;element name="nodalPlanes" type="{http://quakeml.org/xmlns/bed/1.2}NodalPlanes" minOccurs="0"/&gt;
 *           &lt;element name="principalAxes" type="{http://quakeml.org/xmlns/bed/1.2}PrincipalAxes" minOccurs="0"/&gt;
 *           &lt;element name="azimuthalGap" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="stationPolarityCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *           &lt;element name="misfit" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="stationDistributionRatio" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="methodID" type="{http://quakeml.org/xmlns/bed/1.2}ResourceReference" minOccurs="0"/&gt;
 *           &lt;element name="evaluationMode" type="{http://quakeml.org/xmlns/bed/1.2}EvaluationMode" minOccurs="0"/&gt;
 *           &lt;element name="evaluationStatus" type="{http://quakeml.org/xmlns/bed/1.2}EvaluationStatus" minOccurs="0"/&gt;
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
@XmlType(name = "FocalMechanism", propOrder = {
    "waveformIDOrCommentOrMomentTensor",
    "any"
})
public class FocalMechanism {

    @XmlElementRefs({
        @XmlElementRef(name = "waveformID", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "comment", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "momentTensor", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "triggeringOriginID", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "nodalPlanes", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "principalAxes", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "azimuthalGap", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "stationPolarityCount", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "misfit", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "stationDistributionRatio", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "methodID", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "evaluationMode", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "evaluationStatus", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "creationInfo", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> waveformIDOrCommentOrMomentTensor;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "publicID", required = true)
    protected String publicID;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the waveformIDOrCommentOrMomentTensor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the waveformIDOrCommentOrMomentTensor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWaveformIDOrCommentOrMomentTensor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link Double }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Comment }{@code >}
     * {@link JAXBElement }{@code <}{@link CreationInfo }{@code >}
     * {@link JAXBElement }{@code <}{@link EvaluationMode }{@code >}
     * {@link JAXBElement }{@code <}{@link EvaluationStatus }{@code >}
     * {@link JAXBElement }{@code <}{@link MomentTensor }{@code >}
     * {@link JAXBElement }{@code <}{@link NodalPlanes }{@code >}
     * {@link JAXBElement }{@code <}{@link PrincipalAxes }{@code >}
     * {@link JAXBElement }{@code <}{@link WaveformStreamID }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getWaveformIDOrCommentOrMomentTensor() {
        if (waveformIDOrCommentOrMomentTensor == null) {
            waveformIDOrCommentOrMomentTensor = new ArrayList<JAXBElement<?>>();
        }
        return this.waveformIDOrCommentOrMomentTensor;
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
