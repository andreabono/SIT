//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.2 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.09.05 alle 11:56:25 AM CEST 
//


package org.quakeml.xmlns.bed._1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per OriginUncertaintyDescription.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="OriginUncertaintyDescription"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="horizontal uncertainty"/&gt;
 *     &lt;enumeration value="uncertainty ellipse"/&gt;
 *     &lt;enumeration value="confidence ellipsoid"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "OriginUncertaintyDescription")
@XmlEnum
public enum OriginUncertaintyDescription {

    @XmlEnumValue("horizontal uncertainty")
    HORIZONTAL_UNCERTAINTY("horizontal uncertainty"),
    @XmlEnumValue("uncertainty ellipse")
    UNCERTAINTY_ELLIPSE("uncertainty ellipse"),
    @XmlEnumValue("confidence ellipsoid")
    CONFIDENCE_ELLIPSOID("confidence ellipsoid");
    private final String value;

    OriginUncertaintyDescription(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OriginUncertaintyDescription fromValue(String v) {
        for (OriginUncertaintyDescription c: OriginUncertaintyDescription.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
