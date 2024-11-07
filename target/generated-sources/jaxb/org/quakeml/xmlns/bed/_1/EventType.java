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
 * <p>Classe Java per EventType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <pre>
 * &lt;simpleType name="EventType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="not existing"/&gt;
 *     &lt;enumeration value="not reported"/&gt;
 *     &lt;enumeration value="earthquake"/&gt;
 *     &lt;enumeration value="anthropogenic event"/&gt;
 *     &lt;enumeration value="collapse"/&gt;
 *     &lt;enumeration value="cavity collapse"/&gt;
 *     &lt;enumeration value="mine collapse"/&gt;
 *     &lt;enumeration value="building collapse"/&gt;
 *     &lt;enumeration value="explosion"/&gt;
 *     &lt;enumeration value="accidental explosion"/&gt;
 *     &lt;enumeration value="chemical explosion"/&gt;
 *     &lt;enumeration value="controlled explosion"/&gt;
 *     &lt;enumeration value="experimental explosion"/&gt;
 *     &lt;enumeration value="industrial explosion"/&gt;
 *     &lt;enumeration value="mining explosion"/&gt;
 *     &lt;enumeration value="quarry blast"/&gt;
 *     &lt;enumeration value="road cut"/&gt;
 *     &lt;enumeration value="blasting levee"/&gt;
 *     &lt;enumeration value="nuclear explosion"/&gt;
 *     &lt;enumeration value="induced or triggered event"/&gt;
 *     &lt;enumeration value="rock burst"/&gt;
 *     &lt;enumeration value="reservoir loading"/&gt;
 *     &lt;enumeration value="fluid injection"/&gt;
 *     &lt;enumeration value="fluid extraction"/&gt;
 *     &lt;enumeration value="crash"/&gt;
 *     &lt;enumeration value="plane crash"/&gt;
 *     &lt;enumeration value="train crash"/&gt;
 *     &lt;enumeration value="boat crash"/&gt;
 *     &lt;enumeration value="other event"/&gt;
 *     &lt;enumeration value="atmospheric event"/&gt;
 *     &lt;enumeration value="sonic boom"/&gt;
 *     &lt;enumeration value="sonic blast"/&gt;
 *     &lt;enumeration value="acoustic noise"/&gt;
 *     &lt;enumeration value="thunder"/&gt;
 *     &lt;enumeration value="avalanche"/&gt;
 *     &lt;enumeration value="snow avalanche"/&gt;
 *     &lt;enumeration value="debris avalanche"/&gt;
 *     &lt;enumeration value="hydroacoustic event"/&gt;
 *     &lt;enumeration value="ice quake"/&gt;
 *     &lt;enumeration value="slide"/&gt;
 *     &lt;enumeration value="landslide"/&gt;
 *     &lt;enumeration value="rockslide"/&gt;
 *     &lt;enumeration value="meteorite"/&gt;
 *     &lt;enumeration value="volcanic eruption"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EventType")
@XmlEnum
public enum EventType {

    @XmlEnumValue("not existing")
    NOT_EXISTING("not existing"),
    @XmlEnumValue("not reported")
    NOT_REPORTED("not reported"),
    @XmlEnumValue("earthquake")
    EARTHQUAKE("earthquake"),
    @XmlEnumValue("anthropogenic event")
    ANTHROPOGENIC_EVENT("anthropogenic event"),
    @XmlEnumValue("collapse")
    COLLAPSE("collapse"),
    @XmlEnumValue("cavity collapse")
    CAVITY_COLLAPSE("cavity collapse"),
    @XmlEnumValue("mine collapse")
    MINE_COLLAPSE("mine collapse"),
    @XmlEnumValue("building collapse")
    BUILDING_COLLAPSE("building collapse"),
    @XmlEnumValue("explosion")
    EXPLOSION("explosion"),
    @XmlEnumValue("accidental explosion")
    ACCIDENTAL_EXPLOSION("accidental explosion"),
    @XmlEnumValue("chemical explosion")
    CHEMICAL_EXPLOSION("chemical explosion"),
    @XmlEnumValue("controlled explosion")
    CONTROLLED_EXPLOSION("controlled explosion"),
    @XmlEnumValue("experimental explosion")
    EXPERIMENTAL_EXPLOSION("experimental explosion"),
    @XmlEnumValue("industrial explosion")
    INDUSTRIAL_EXPLOSION("industrial explosion"),
    @XmlEnumValue("mining explosion")
    MINING_EXPLOSION("mining explosion"),
    @XmlEnumValue("quarry blast")
    QUARRY_BLAST("quarry blast"),
    @XmlEnumValue("road cut")
    ROAD_CUT("road cut"),
    @XmlEnumValue("blasting levee")
    BLASTING_LEVEE("blasting levee"),
    @XmlEnumValue("nuclear explosion")
    NUCLEAR_EXPLOSION("nuclear explosion"),
    @XmlEnumValue("induced or triggered event")
    INDUCED_OR_TRIGGERED_EVENT("induced or triggered event"),
    @XmlEnumValue("rock burst")
    ROCK_BURST("rock burst"),
    @XmlEnumValue("reservoir loading")
    RESERVOIR_LOADING("reservoir loading"),
    @XmlEnumValue("fluid injection")
    FLUID_INJECTION("fluid injection"),
    @XmlEnumValue("fluid extraction")
    FLUID_EXTRACTION("fluid extraction"),
    @XmlEnumValue("crash")
    CRASH("crash"),
    @XmlEnumValue("plane crash")
    PLANE_CRASH("plane crash"),
    @XmlEnumValue("train crash")
    TRAIN_CRASH("train crash"),
    @XmlEnumValue("boat crash")
    BOAT_CRASH("boat crash"),
    @XmlEnumValue("other event")
    OTHER_EVENT("other event"),
    @XmlEnumValue("atmospheric event")
    ATMOSPHERIC_EVENT("atmospheric event"),
    @XmlEnumValue("sonic boom")
    SONIC_BOOM("sonic boom"),
    @XmlEnumValue("sonic blast")
    SONIC_BLAST("sonic blast"),
    @XmlEnumValue("acoustic noise")
    ACOUSTIC_NOISE("acoustic noise"),
    @XmlEnumValue("thunder")
    THUNDER("thunder"),
    @XmlEnumValue("avalanche")
    AVALANCHE("avalanche"),
    @XmlEnumValue("snow avalanche")
    SNOW_AVALANCHE("snow avalanche"),
    @XmlEnumValue("debris avalanche")
    DEBRIS_AVALANCHE("debris avalanche"),
    @XmlEnumValue("hydroacoustic event")
    HYDROACOUSTIC_EVENT("hydroacoustic event"),
    @XmlEnumValue("ice quake")
    ICE_QUAKE("ice quake"),
    @XmlEnumValue("slide")
    SLIDE("slide"),
    @XmlEnumValue("landslide")
    LANDSLIDE("landslide"),
    @XmlEnumValue("rockslide")
    ROCKSLIDE("rockslide"),
    @XmlEnumValue("meteorite")
    METEORITE("meteorite"),
    @XmlEnumValue("volcanic eruption")
    VOLCANIC_ERUPTION("volcanic eruption");
    private final String value;

    EventType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventType fromValue(String v) {
        for (EventType c: EventType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
