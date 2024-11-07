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
 * <p>Classe Java per AmplitudeCategory.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="AmplitudeCategory"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="point"/&gt;
 *     &lt;enumeration value="mean"/&gt;
 *     &lt;enumeration value="duration"/&gt;
 *     &lt;enumeration value="period"/&gt;
 *     &lt;enumeration value="integral"/&gt;
 *     &lt;enumeration value="other"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AmplitudeCategory")
@XmlEnum
public enum AmplitudeCategory {

    @XmlEnumValue("point")
    POINT("point"),
    @XmlEnumValue("mean")
    MEAN("mean"),
    @XmlEnumValue("duration")
    DURATION("duration"),
    @XmlEnumValue("period")
    PERIOD("period"),
    @XmlEnumValue("integral")
    INTEGRAL("integral"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    AmplitudeCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AmplitudeCategory fromValue(String v) {
        for (AmplitudeCategory c: AmplitudeCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
