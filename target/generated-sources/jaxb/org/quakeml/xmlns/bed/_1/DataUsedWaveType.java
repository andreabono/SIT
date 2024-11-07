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
 * <p>Classe Java per DataUsedWaveType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="DataUsedWaveType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="P waves"/&gt;
 *     &lt;enumeration value="body waves"/&gt;
 *     &lt;enumeration value="surface waves"/&gt;
 *     &lt;enumeration value="mantle waves"/&gt;
 *     &lt;enumeration value="combined"/&gt;
 *     &lt;enumeration value="unknown"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DataUsedWaveType")
@XmlEnum
public enum DataUsedWaveType {

    @XmlEnumValue("P waves")
    P_WAVES("P waves"),
    @XmlEnumValue("body waves")
    BODY_WAVES("body waves"),
    @XmlEnumValue("surface waves")
    SURFACE_WAVES("surface waves"),
    @XmlEnumValue("mantle waves")
    MANTLE_WAVES("mantle waves"),
    @XmlEnumValue("combined")
    COMBINED("combined"),
    @XmlEnumValue("unknown")
    UNKNOWN("unknown");
    private final String value;

    DataUsedWaveType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DataUsedWaveType fromValue(String v) {
        for (DataUsedWaveType c: DataUsedWaveType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
