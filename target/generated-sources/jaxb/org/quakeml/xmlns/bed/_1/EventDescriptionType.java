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
 * <p>Classe Java per EventDescriptionType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="EventDescriptionType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="felt report"/&gt;
 *     &lt;enumeration value="Flinn-Engdahl region"/&gt;
 *     &lt;enumeration value="local time"/&gt;
 *     &lt;enumeration value="tectonic summary"/&gt;
 *     &lt;enumeration value="nearest cities"/&gt;
 *     &lt;enumeration value="earthquake name"/&gt;
 *     &lt;enumeration value="region name"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EventDescriptionType")
@XmlEnum
public enum EventDescriptionType {

    @XmlEnumValue("felt report")
    FELT_REPORT("felt report"),
    @XmlEnumValue("Flinn-Engdahl region")
    FLINN_ENGDAHL_REGION("Flinn-Engdahl region"),
    @XmlEnumValue("local time")
    LOCAL_TIME("local time"),
    @XmlEnumValue("tectonic summary")
    TECTONIC_SUMMARY("tectonic summary"),
    @XmlEnumValue("nearest cities")
    NEAREST_CITIES("nearest cities"),
    @XmlEnumValue("earthquake name")
    EARTHQUAKE_NAME("earthquake name"),
    @XmlEnumValue("region name")
    REGION_NAME("region name");
    private final String value;

    EventDescriptionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventDescriptionType fromValue(String v) {
        for (EventDescriptionType c: EventDescriptionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
