//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.2 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.11.07 alle 01:51:38 PM CET 
//


package org.quakeml.xmlns.bed._1;

import java.io.Serializable;
import java.math.BigInteger;
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
 * <p>Classe Java per OriginQuality complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="OriginQuality"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element name="associatedPhaseCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *           &lt;element name="usedPhaseCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *           &lt;element name="associatedStationCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *           &lt;element name="usedStationCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *           &lt;element name="depthPhaseCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *           &lt;element name="standardError" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="azimuthalGap" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="secondaryAzimuthalGap" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="groundTruthLevel" minOccurs="0"&gt;
 *             &lt;simpleType&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                 &lt;maxLength value="32"/&gt;
 *               &lt;/restriction&gt;
 *             &lt;/simpleType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="maximumDistance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="minimumDistance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *           &lt;element name="medianDistance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
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
@XmlType(name = "OriginQuality", propOrder = {
    "associatedPhaseCountOrUsedPhaseCountOrAssociatedStationCount",
    "any"
})
public class OriginQuality {

    @XmlElementRefs({
        @XmlElementRef(name = "associatedPhaseCount", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "usedPhaseCount", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "associatedStationCount", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "usedStationCount", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "depthPhaseCount", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "standardError", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "azimuthalGap", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "secondaryAzimuthalGap", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "groundTruthLevel", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "maximumDistance", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "minimumDistance", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "medianDistance", namespace = "http://quakeml.org/xmlns/bed/1.2", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<? extends Serializable>> associatedPhaseCountOrUsedPhaseCountOrAssociatedStationCount;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the associatedPhaseCountOrUsedPhaseCountOrAssociatedStationCount property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the associatedPhaseCountOrUsedPhaseCountOrAssociatedStationCount property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociatedPhaseCountOrUsedPhaseCountOrAssociatedStationCount().add(newItem);
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
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends Serializable>> getAssociatedPhaseCountOrUsedPhaseCountOrAssociatedStationCount() {
        if (associatedPhaseCountOrUsedPhaseCountOrAssociatedStationCount == null) {
            associatedPhaseCountOrUsedPhaseCountOrAssociatedStationCount = new ArrayList<JAXBElement<? extends Serializable>>();
        }
        return this.associatedPhaseCountOrUsedPhaseCountOrAssociatedStationCount;
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
