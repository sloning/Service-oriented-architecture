//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.11 at 08:57:51 PM MSK 
//


package com.example.proxy.model;

import jakarta.xml.bind.annotation.*;


/**
 * <p>Java class for length.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="length"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SHORTEST"/&gt;
 *     &lt;enumeration value="LONGEST"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "length")
@XmlEnum
public enum Length {

    SHORTEST,
    LONGEST;

    public String value() {
        return name();
    }

    public static Length fromValue(String v) {
        return valueOf(v);
    }

}
