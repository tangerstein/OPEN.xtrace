//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.13 at 11:58:26 AM CET 
//


package org.spec.research.open.xtrace.adapters.dynatrace.model.generated;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="source" type="{}sourceType" minOccurs="0"/>
 *         &lt;element name="comparesource" type="{}sourceType" minOccurs="0"/>
 *         &lt;element name="measures" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="measure" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="measurement" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                                     &lt;attribute name="avg" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="min" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="max" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="sum" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                     &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="measure" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="agenthost" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="agentgroup" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="color" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="aggregation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="avg" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="unit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="min" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="max" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="sum" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="lastvalue" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="count" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="systemprofile" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="thresholds" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="rate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="scale" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="drawingorder" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="parent" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="application" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="splitting" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="showabsolutevalues" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "source",
    "comparesource",
    "measures"
})
@XmlRootElement(name = "dynamicmeasurematrixdashlet")
public class Dynamicmeasurematrixdashlet {

    protected SourceType source;
    protected SourceType comparesource;
    protected Dynamicmeasurematrixdashlet.Measures measures;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "description")
    protected String description;
    @XmlAttribute(name = "showabsolutevalues", required = true)
    protected String showabsolutevalues;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link SourceType }
     *     
     */
    public SourceType getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceType }
     *     
     */
    public void setSource(SourceType value) {
        this.source = value;
    }

    /**
     * Gets the value of the comparesource property.
     * 
     * @return
     *     possible object is
     *     {@link SourceType }
     *     
     */
    public SourceType getComparesource() {
        return comparesource;
    }

    /**
     * Sets the value of the comparesource property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceType }
     *     
     */
    public void setComparesource(SourceType value) {
        this.comparesource = value;
    }

    /**
     * Gets the value of the measures property.
     * 
     * @return
     *     possible object is
     *     {@link Dynamicmeasurematrixdashlet.Measures }
     *     
     */
    public Dynamicmeasurematrixdashlet.Measures getMeasures() {
        return measures;
    }

    /**
     * Sets the value of the measures property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dynamicmeasurematrixdashlet.Measures }
     *     
     */
    public void setMeasures(Dynamicmeasurematrixdashlet.Measures value) {
        this.measures = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the showabsolutevalues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShowabsolutevalues() {
        return showabsolutevalues;
    }

    /**
     * Sets the value of the showabsolutevalues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShowabsolutevalues(String value) {
        this.showabsolutevalues = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="measure" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="measurement" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                           &lt;attribute name="avg" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                           &lt;attribute name="min" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                           &lt;attribute name="max" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                           &lt;attribute name="sum" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                           &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="measure" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="agenthost" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="agentgroup" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="color" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="aggregation" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="avg" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="unit" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="min" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="max" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="sum" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="lastvalue" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="count" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="systemprofile" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="thresholds" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="rate" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="scale" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="drawingorder" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="parent" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="application" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="splitting" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "measure"
    })
    public static class Measures {

        protected List<Dynamicmeasurematrixdashlet.Measures.Measure> measure;

        /**
         * Gets the value of the measure property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the measure property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMeasure().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Dynamicmeasurematrixdashlet.Measures.Measure }
         * 
         * 
         */
        public List<Dynamicmeasurematrixdashlet.Measures.Measure> getMeasure() {
            if (measure == null) {
                measure = new ArrayList<Dynamicmeasurematrixdashlet.Measures.Measure>();
            }
            return this.measure;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="measurement" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *                 &lt;attribute name="avg" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                 &lt;attribute name="min" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                 &lt;attribute name="max" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                 &lt;attribute name="sum" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                 &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="measure" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="agenthost" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="agentgroup" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="color" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="aggregation" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="avg" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="unit" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="min" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="max" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="sum" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="lastvalue" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="count" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="systemprofile" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="thresholds" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="rate" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="scale" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="drawingorder" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="parent" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="application" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="splitting" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "measurement"
        })
        public static class Measure {

            protected List<Dynamicmeasurematrixdashlet.Measures.Measure.Measurement> measurement;
            @XmlAttribute(name = "measure")
            protected String measure;
            @XmlAttribute(name = "agenthost")
            protected String agenthost;
            @XmlAttribute(name = "agentgroup")
            protected String agentgroup;
            @XmlAttribute(name = "color")
            protected String color;
            @XmlAttribute(name = "aggregation")
            protected String aggregation;
            @XmlAttribute(name = "avg")
            protected Double avg;
            @XmlAttribute(name = "unit")
            protected String unit;
            @XmlAttribute(name = "min")
            protected Double min;
            @XmlAttribute(name = "max")
            protected Double max;
            @XmlAttribute(name = "sum")
            protected Double sum;
            @XmlAttribute(name = "lastvalue")
            protected Double lastvalue;
            @XmlAttribute(name = "count")
            protected BigInteger count;
            @XmlAttribute(name = "systemprofile")
            protected String systemprofile;
            @XmlAttribute(name = "thresholds")
            protected String thresholds;
            @XmlAttribute(name = "rate")
            protected String rate;
            @XmlAttribute(name = "scale")
            protected String scale;
            @XmlAttribute(name = "drawingorder")
            protected BigInteger drawingorder;
            @XmlAttribute(name = "parent")
            protected String parent;
            @XmlAttribute(name = "application")
            protected String application;
            @XmlAttribute(name = "splitting")
            protected String splitting;

            /**
             * Gets the value of the measurement property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the measurement property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getMeasurement().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Dynamicmeasurematrixdashlet.Measures.Measure.Measurement }
             * 
             * 
             */
            public List<Dynamicmeasurematrixdashlet.Measures.Measure.Measurement> getMeasurement() {
                if (measurement == null) {
                    measurement = new ArrayList<Dynamicmeasurematrixdashlet.Measures.Measure.Measurement>();
                }
                return this.measurement;
            }

            /**
             * Gets the value of the measure property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMeasure() {
                return measure;
            }

            /**
             * Sets the value of the measure property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMeasure(String value) {
                this.measure = value;
            }

            /**
             * Gets the value of the agenthost property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAgenthost() {
                return agenthost;
            }

            /**
             * Sets the value of the agenthost property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAgenthost(String value) {
                this.agenthost = value;
            }

            /**
             * Gets the value of the agentgroup property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAgentgroup() {
                return agentgroup;
            }

            /**
             * Sets the value of the agentgroup property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAgentgroup(String value) {
                this.agentgroup = value;
            }

            /**
             * Gets the value of the color property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getColor() {
                return color;
            }

            /**
             * Sets the value of the color property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setColor(String value) {
                this.color = value;
            }

            /**
             * Gets the value of the aggregation property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAggregation() {
                return aggregation;
            }

            /**
             * Sets the value of the aggregation property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAggregation(String value) {
                this.aggregation = value;
            }

            /**
             * Gets the value of the avg property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getAvg() {
                return avg;
            }

            /**
             * Sets the value of the avg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setAvg(Double value) {
                this.avg = value;
            }

            /**
             * Gets the value of the unit property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUnit() {
                return unit;
            }

            /**
             * Sets the value of the unit property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUnit(String value) {
                this.unit = value;
            }

            /**
             * Gets the value of the min property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getMin() {
                return min;
            }

            /**
             * Sets the value of the min property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setMin(Double value) {
                this.min = value;
            }

            /**
             * Gets the value of the max property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getMax() {
                return max;
            }

            /**
             * Sets the value of the max property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setMax(Double value) {
                this.max = value;
            }

            /**
             * Gets the value of the sum property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getSum() {
                return sum;
            }

            /**
             * Sets the value of the sum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setSum(Double value) {
                this.sum = value;
            }

            /**
             * Gets the value of the lastvalue property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getLastvalue() {
                return lastvalue;
            }

            /**
             * Sets the value of the lastvalue property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setLastvalue(Double value) {
                this.lastvalue = value;
            }

            /**
             * Gets the value of the count property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getCount() {
                return count;
            }

            /**
             * Sets the value of the count property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setCount(BigInteger value) {
                this.count = value;
            }

            /**
             * Gets the value of the systemprofile property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSystemprofile() {
                return systemprofile;
            }

            /**
             * Sets the value of the systemprofile property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSystemprofile(String value) {
                this.systemprofile = value;
            }

            /**
             * Gets the value of the thresholds property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getThresholds() {
                return thresholds;
            }

            /**
             * Sets the value of the thresholds property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setThresholds(String value) {
                this.thresholds = value;
            }

            /**
             * Gets the value of the rate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRate() {
                return rate;
            }

            /**
             * Sets the value of the rate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRate(String value) {
                this.rate = value;
            }

            /**
             * Gets the value of the scale property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getScale() {
                return scale;
            }

            /**
             * Sets the value of the scale property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setScale(String value) {
                this.scale = value;
            }

            /**
             * Gets the value of the drawingorder property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getDrawingorder() {
                return drawingorder;
            }

            /**
             * Sets the value of the drawingorder property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setDrawingorder(BigInteger value) {
                this.drawingorder = value;
            }

            /**
             * Gets the value of the parent property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getParent() {
                return parent;
            }

            /**
             * Sets the value of the parent property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setParent(String value) {
                this.parent = value;
            }

            /**
             * Gets the value of the application property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getApplication() {
                return application;
            }

            /**
             * Sets the value of the application property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setApplication(String value) {
                this.application = value;
            }

            /**
             * Gets the value of the splitting property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSplitting() {
                return splitting;
            }

            /**
             * Sets the value of the splitting property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSplitting(String value) {
                this.splitting = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
             *       &lt;attribute name="avg" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
             *       &lt;attribute name="min" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
             *       &lt;attribute name="max" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
             *       &lt;attribute name="sum" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
             *       &lt;attribute name="count" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Measurement {

                @XmlAttribute(name = "timestamp", required = true)
                protected BigInteger timestamp;
                @XmlAttribute(name = "avg", required = true)
                protected double avg;
                @XmlAttribute(name = "min", required = true)
                protected double min;
                @XmlAttribute(name = "max", required = true)
                protected double max;
                @XmlAttribute(name = "sum", required = true)
                protected double sum;
                @XmlAttribute(name = "count", required = true)
                protected BigInteger count;

                /**
                 * Gets the value of the timestamp property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getTimestamp() {
                    return timestamp;
                }

                /**
                 * Sets the value of the timestamp property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setTimestamp(BigInteger value) {
                    this.timestamp = value;
                }

                /**
                 * Gets the value of the avg property.
                 * 
                 */
                public double getAvg() {
                    return avg;
                }

                /**
                 * Sets the value of the avg property.
                 * 
                 */
                public void setAvg(double value) {
                    this.avg = value;
                }

                /**
                 * Gets the value of the min property.
                 * 
                 */
                public double getMin() {
                    return min;
                }

                /**
                 * Sets the value of the min property.
                 * 
                 */
                public void setMin(double value) {
                    this.min = value;
                }

                /**
                 * Gets the value of the max property.
                 * 
                 */
                public double getMax() {
                    return max;
                }

                /**
                 * Sets the value of the max property.
                 * 
                 */
                public void setMax(double value) {
                    this.max = value;
                }

                /**
                 * Gets the value of the sum property.
                 * 
                 */
                public double getSum() {
                    return sum;
                }

                /**
                 * Sets the value of the sum property.
                 * 
                 */
                public void setSum(double value) {
                    this.sum = value;
                }

                /**
                 * Gets the value of the count property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getCount() {
                    return count;
                }

                /**
                 * Sets the value of the count property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setCount(BigInteger value) {
                    this.count = value;
                }

            }

        }

    }

}
