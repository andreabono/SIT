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
 * <p>Classe Java per EventTypeCertainty.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="EventTypeCertainty"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="known"/&gt;
 *     &lt;enumeration value="suspected"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EventTypeCertainty")
@XmlEnum
public enum EventTypeCertainty {

    @XmlEnumValue("known")
    KNOWN("known"),
    @XmlEnumValue("suspected")
    SUSPECTED("suspected");
    private final String value;

    EventTypeCertainty(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventTypeCertainty fromValue(String v) {
        for (EventTypeCertainty c: EventTypeCertainty.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
