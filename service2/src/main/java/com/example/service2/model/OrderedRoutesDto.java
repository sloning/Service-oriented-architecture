//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.11 at 08:57:51 PM MSK 
//


package com.example.service2.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for orderedRoutesDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orderedRoutesDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idFrom" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="idTo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="orderBy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderedRoutesDto", propOrder = {
    "idFrom",
    "idTo",
    "orderBy"
})
public class OrderedRoutesDto {

    protected int idFrom;
    protected int idTo;
    @XmlElement(required = true)
    protected String orderBy;

    /**
     * Gets the value of the idFrom property.
     * 
     */
    public int getIdFrom() {
        return idFrom;
    }

    /**
     * Sets the value of the idFrom property.
     * 
     */
    public void setIdFrom(int value) {
        this.idFrom = value;
    }

    /**
     * Gets the value of the idTo property.
     * 
     */
    public int getIdTo() {
        return idTo;
    }

    /**
     * Sets the value of the idTo property.
     * 
     */
    public void setIdTo(int value) {
        this.idTo = value;
    }

    /**
     * Gets the value of the orderBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * Sets the value of the orderBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderBy(String value) {
        this.orderBy = value;
    }

}
