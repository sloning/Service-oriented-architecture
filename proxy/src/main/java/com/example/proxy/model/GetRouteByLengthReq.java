//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.11 at 08:57:51 PM MSK 
//


package com.example.proxy.model;

import jakarta.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lengthFilterDto" type="{https://github.com/sloning/Service-oriented-architecture}lengthFilterDto"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "lengthFilterDto"
})
@XmlRootElement(name = "getRouteByLengthReq")
public class GetRouteByLengthReq {

    @XmlElement(required = true)
    protected LengthFilterDto lengthFilterDto;

    /**
     * Gets the value of the lengthFilterDto property.
     * 
     * @return
     *     possible object is
     *     {@link LengthFilterDto }
     *     
     */
    public LengthFilterDto getLengthFilterDto() {
        return lengthFilterDto;
    }

    /**
     * Sets the value of the lengthFilterDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthFilterDto }
     *     
     */
    public void setLengthFilterDto(LengthFilterDto value) {
        this.lengthFilterDto = value;
    }

}
