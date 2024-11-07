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
 * <p>Classe Java per OriginType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="OriginType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="hypocenter"/&gt;
 *     &lt;enumeration value="centroid"/&gt;
 *     &lt;enumeration value="amplitude"/&gt;
 *     &lt;enumeration value="macroseismic"/&gt;
 *     &lt;enumeration value="rupture start"/&gt;
 *     &lt;enumeration value="rupture end"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "OriginType")
@XmlEnum
public enum OriginType {

    @XmlEnumValue("hypocenter")
    HYPOCENTER("hypocenter"),
    @XmlEnumValue("centroid")
    CENTROID("centroid"),
    @XmlEnumValue("amplitude")
    AMPLITUDE("amplitude"),
    @XmlEnumValue("macroseismic")
    MACROSEISMIC("macroseismic"),
    @XmlEnumValue("rupture start")
    RUPTURE_START("rupture start"),
    @XmlEnumValue("rupture end")
    RUPTURE_END("rupture end");
    private final String value;

    OriginType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OriginType fromValue(String v) {
        for (OriginType c: OriginType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
