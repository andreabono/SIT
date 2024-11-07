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
 * <p>Classe Java per MTInversionType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="MTInversionType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="general"/&gt;
 *     &lt;enumeration value="zero trace"/&gt;
 *     &lt;enumeration value="double couple"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MTInversionType")
@XmlEnum
public enum MTInversionType {

    @XmlEnumValue("general")
    GENERAL("general"),
    @XmlEnumValue("zero trace")
    ZERO_TRACE("zero trace"),
    @XmlEnumValue("double couple")
    DOUBLE_COUPLE("double couple");
    private final String value;

    MTInversionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MTInversionType fromValue(String v) {
        for (MTInversionType c: MTInversionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
