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
 * <p>Classe Java per OriginDepthType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="OriginDepthType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="from location"/&gt;
 *     &lt;enumeration value="from moment tensor inversion"/&gt;
 *     &lt;enumeration value="from modeling of broad-band P waveforms"/&gt;
 *     &lt;enumeration value="constrained by depth phases"/&gt;
 *     &lt;enumeration value="constrained by direct phases"/&gt;
 *     &lt;enumeration value="constrained by depth and direct phases"/&gt;
 *     &lt;enumeration value="operator assigned"/&gt;
 *     &lt;enumeration value="other"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "OriginDepthType")
@XmlEnum
public enum OriginDepthType {

    @XmlEnumValue("from location")
    FROM_LOCATION("from location"),
    @XmlEnumValue("from moment tensor inversion")
    FROM_MOMENT_TENSOR_INVERSION("from moment tensor inversion"),
    @XmlEnumValue("from modeling of broad-band P waveforms")
    FROM_MODELING_OF_BROAD_BAND_P_WAVEFORMS("from modeling of broad-band P waveforms"),
    @XmlEnumValue("constrained by depth phases")
    CONSTRAINED_BY_DEPTH_PHASES("constrained by depth phases"),
    @XmlEnumValue("constrained by direct phases")
    CONSTRAINED_BY_DIRECT_PHASES("constrained by direct phases"),
    @XmlEnumValue("constrained by depth and direct phases")
    CONSTRAINED_BY_DEPTH_AND_DIRECT_PHASES("constrained by depth and direct phases"),
    @XmlEnumValue("operator assigned")
    OPERATOR_ASSIGNED("operator assigned"),
    @XmlEnumValue("other")
    OTHER("other");
    private final String value;

    OriginDepthType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OriginDepthType fromValue(String v) {
        for (OriginDepthType c: OriginDepthType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
