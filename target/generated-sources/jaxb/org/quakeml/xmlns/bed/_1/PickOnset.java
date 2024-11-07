//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.2 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.11.07 alle 01:51:38 PM CET 
//


package org.quakeml.xmlns.bed._1;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per PickOnset.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="PickOnset"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="emergent"/&gt;
 *     &lt;enumeration value="impulsive"/&gt;
 *     &lt;enumeration value="questionable"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PickOnset")
@XmlEnum
public enum PickOnset {

    @XmlEnumValue("emergent")
    EMERGENT("emergent"),
    @XmlEnumValue("impulsive")
    IMPULSIVE("impulsive"),
    @XmlEnumValue("questionable")
    QUESTIONABLE("questionable");
    private final String value;

    PickOnset(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PickOnset fromValue(String v) {
        for (PickOnset c: PickOnset.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
