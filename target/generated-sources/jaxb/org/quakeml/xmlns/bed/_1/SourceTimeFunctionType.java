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
 * <p>Classe Java per SourceTimeFunctionType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="SourceTimeFunctionType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="box car"/&gt;
 *     &lt;enumeration value="triangle"/&gt;
 *     &lt;enumeration value="trapezoid"/&gt;
 *     &lt;enumeration value="unknown"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SourceTimeFunctionType")
@XmlEnum
public enum SourceTimeFunctionType {

    @XmlEnumValue("box car")
    BOX_CAR("box car"),
    @XmlEnumValue("triangle")
    TRIANGLE("triangle"),
    @XmlEnumValue("trapezoid")
    TRAPEZOID("trapezoid"),
    @XmlEnumValue("unknown")
    UNKNOWN("unknown");
    private final String value;

    SourceTimeFunctionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SourceTimeFunctionType fromValue(String v) {
        for (SourceTimeFunctionType c: SourceTimeFunctionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
