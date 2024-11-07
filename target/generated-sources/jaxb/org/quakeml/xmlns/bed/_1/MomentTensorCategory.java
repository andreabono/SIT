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
 * <p>Classe Java per MomentTensorCategory.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="MomentTensorCategory"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="teleseismic"/&gt;
 *     &lt;enumeration value="regional"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MomentTensorCategory")
@XmlEnum
public enum MomentTensorCategory {

    @XmlEnumValue("teleseismic")
    TELESEISMIC("teleseismic"),
    @XmlEnumValue("regional")
    REGIONAL("regional");
    private final String value;

    MomentTensorCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MomentTensorCategory fromValue(String v) {
        for (MomentTensorCategory c: MomentTensorCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
