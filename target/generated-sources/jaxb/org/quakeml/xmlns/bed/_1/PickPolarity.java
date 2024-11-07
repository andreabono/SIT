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
 * <p>Classe Java per PickPolarity.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="PickPolarity"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="positive"/&gt;
 *     &lt;enumeration value="negative"/&gt;
 *     &lt;enumeration value="undecidable"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PickPolarity")
@XmlEnum
public enum PickPolarity {

    @XmlEnumValue("positive")
    POSITIVE("positive"),
    @XmlEnumValue("negative")
    NEGATIVE("negative"),
    @XmlEnumValue("undecidable")
    UNDECIDABLE("undecidable");
    private final String value;

    PickPolarity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PickPolarity fromValue(String v) {
        for (PickPolarity c: PickPolarity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
