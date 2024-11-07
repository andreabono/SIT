//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.2 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.09.05 alle 11:56:25 AM CEST 
//


package org.quakeml.xmlns.quakeml._1;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.quakeml.xmlns.quakeml._1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Quakeml_QNAME = new QName("http://quakeml.org/xmlns/quakeml/1.2", "quakeml");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.quakeml.xmlns.quakeml._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Quakeml }
     * 
     */
    public Quakeml createQuakeml() {
        return new Quakeml();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Quakeml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Quakeml }{@code >}
     */
    @XmlElementDecl(namespace = "http://quakeml.org/xmlns/quakeml/1.2", name = "quakeml")
    public JAXBElement<Quakeml> createQuakeml(Quakeml value) {
        return new JAXBElement<Quakeml>(_Quakeml_QNAME, Quakeml.class, null, value);
    }

}
