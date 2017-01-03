//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.13 at 11:58:26 AM CET 
//


package org.spec.research.open.xtrace.adapters.dynatrace.model.generated;

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
 *         &lt;element name="apis" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="api" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="exec_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="exec_min" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="exec_max" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="exec_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="cpu_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="cpu_min" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="cpu_max" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="cpu_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="sync_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="sync_min" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="sync_max" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="sync_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="wait_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="wait_min" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="wait_max" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="wait_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="suspension_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="suspension_min" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="suspension_max" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="suspension_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
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
 *       &lt;attribute name="displaysource" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "apis"
})
@XmlRootElement(name = "apibreakdowndashlet")
public class Apibreakdowndashlet {

    protected SourceType source;
    protected SourceType comparesource;
    protected Apibreakdowndashlet.Apis apis;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "description")
    protected String description;
    @XmlAttribute(name = "displaysource", required = true)
    protected String displaysource;

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
     * Gets the value of the apis property.
     * 
     * @return
     *     possible object is
     *     {@link Apibreakdowndashlet.Apis }
     *     
     */
    public Apibreakdowndashlet.Apis getApis() {
        return apis;
    }

    /**
     * Sets the value of the apis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Apibreakdowndashlet.Apis }
     *     
     */
    public void setApis(Apibreakdowndashlet.Apis value) {
        this.apis = value;
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
     * Gets the value of the displaysource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplaysource() {
        return displaysource;
    }

    /**
     * Sets the value of the displaysource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplaysource(String value) {
        this.displaysource = value;
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
     *         &lt;element name="api" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="exec_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="exec_min" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="exec_max" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="exec_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="cpu_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="cpu_min" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="cpu_max" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="cpu_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="sync_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="sync_min" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="sync_max" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="sync_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="wait_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="wait_min" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="wait_max" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="wait_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="suspension_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="suspension_min" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="suspension_max" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="suspension_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
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
        "api"
    })
    public static class Apis {

        protected List<Apibreakdowndashlet.Apis.Api> api;

        /**
         * Gets the value of the api property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the api property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getApi().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Apibreakdowndashlet.Apis.Api }
         * 
         * 
         */
        public List<Apibreakdowndashlet.Apis.Api> getApi() {
            if (api == null) {
                api = new ArrayList<Apibreakdowndashlet.Apis.Api>();
            }
            return this.api;
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
         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="exec_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="exec_min" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="exec_max" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="exec_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="cpu_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="cpu_min" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="cpu_max" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="cpu_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="sync_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="sync_min" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="sync_max" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="sync_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="wait_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="wait_min" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="wait_max" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="wait_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="suspension_avg" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="suspension_min" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="suspension_max" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="suspension_sum" type="{http://www.w3.org/2001/XMLSchema}double" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Api {

            @XmlAttribute(name = "name")
            protected String name;
            @XmlAttribute(name = "exec_avg")
            protected Double execAvg;
            @XmlAttribute(name = "exec_min")
            protected Double execMin;
            @XmlAttribute(name = "exec_max")
            protected Double execMax;
            @XmlAttribute(name = "exec_sum")
            protected Double execSum;
            @XmlAttribute(name = "cpu_avg")
            protected Double cpuAvg;
            @XmlAttribute(name = "cpu_min")
            protected Double cpuMin;
            @XmlAttribute(name = "cpu_max")
            protected Double cpuMax;
            @XmlAttribute(name = "cpu_sum")
            protected Double cpuSum;
            @XmlAttribute(name = "sync_avg")
            protected Double syncAvg;
            @XmlAttribute(name = "sync_min")
            protected Double syncMin;
            @XmlAttribute(name = "sync_max")
            protected Double syncMax;
            @XmlAttribute(name = "sync_sum")
            protected Double syncSum;
            @XmlAttribute(name = "wait_avg")
            protected Double waitAvg;
            @XmlAttribute(name = "wait_min")
            protected Double waitMin;
            @XmlAttribute(name = "wait_max")
            protected Double waitMax;
            @XmlAttribute(name = "wait_sum")
            protected Double waitSum;
            @XmlAttribute(name = "suspension_avg")
            protected Double suspensionAvg;
            @XmlAttribute(name = "suspension_min")
            protected Double suspensionMin;
            @XmlAttribute(name = "suspension_max")
            protected Double suspensionMax;
            @XmlAttribute(name = "suspension_sum")
            protected Double suspensionSum;

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
             * Gets the value of the execAvg property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getExecAvg() {
                return execAvg;
            }

            /**
             * Sets the value of the execAvg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setExecAvg(Double value) {
                this.execAvg = value;
            }

            /**
             * Gets the value of the execMin property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getExecMin() {
                return execMin;
            }

            /**
             * Sets the value of the execMin property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setExecMin(Double value) {
                this.execMin = value;
            }

            /**
             * Gets the value of the execMax property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getExecMax() {
                return execMax;
            }

            /**
             * Sets the value of the execMax property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setExecMax(Double value) {
                this.execMax = value;
            }

            /**
             * Gets the value of the execSum property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getExecSum() {
                return execSum;
            }

            /**
             * Sets the value of the execSum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setExecSum(Double value) {
                this.execSum = value;
            }

            /**
             * Gets the value of the cpuAvg property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getCpuAvg() {
                return cpuAvg;
            }

            /**
             * Sets the value of the cpuAvg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setCpuAvg(Double value) {
                this.cpuAvg = value;
            }

            /**
             * Gets the value of the cpuMin property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getCpuMin() {
                return cpuMin;
            }

            /**
             * Sets the value of the cpuMin property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setCpuMin(Double value) {
                this.cpuMin = value;
            }

            /**
             * Gets the value of the cpuMax property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getCpuMax() {
                return cpuMax;
            }

            /**
             * Sets the value of the cpuMax property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setCpuMax(Double value) {
                this.cpuMax = value;
            }

            /**
             * Gets the value of the cpuSum property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getCpuSum() {
                return cpuSum;
            }

            /**
             * Sets the value of the cpuSum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setCpuSum(Double value) {
                this.cpuSum = value;
            }

            /**
             * Gets the value of the syncAvg property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getSyncAvg() {
                return syncAvg;
            }

            /**
             * Sets the value of the syncAvg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setSyncAvg(Double value) {
                this.syncAvg = value;
            }

            /**
             * Gets the value of the syncMin property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getSyncMin() {
                return syncMin;
            }

            /**
             * Sets the value of the syncMin property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setSyncMin(Double value) {
                this.syncMin = value;
            }

            /**
             * Gets the value of the syncMax property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getSyncMax() {
                return syncMax;
            }

            /**
             * Sets the value of the syncMax property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setSyncMax(Double value) {
                this.syncMax = value;
            }

            /**
             * Gets the value of the syncSum property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getSyncSum() {
                return syncSum;
            }

            /**
             * Sets the value of the syncSum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setSyncSum(Double value) {
                this.syncSum = value;
            }

            /**
             * Gets the value of the waitAvg property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getWaitAvg() {
                return waitAvg;
            }

            /**
             * Sets the value of the waitAvg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setWaitAvg(Double value) {
                this.waitAvg = value;
            }

            /**
             * Gets the value of the waitMin property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getWaitMin() {
                return waitMin;
            }

            /**
             * Sets the value of the waitMin property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setWaitMin(Double value) {
                this.waitMin = value;
            }

            /**
             * Gets the value of the waitMax property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getWaitMax() {
                return waitMax;
            }

            /**
             * Sets the value of the waitMax property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setWaitMax(Double value) {
                this.waitMax = value;
            }

            /**
             * Gets the value of the waitSum property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getWaitSum() {
                return waitSum;
            }

            /**
             * Sets the value of the waitSum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setWaitSum(Double value) {
                this.waitSum = value;
            }

            /**
             * Gets the value of the suspensionAvg property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getSuspensionAvg() {
                return suspensionAvg;
            }

            /**
             * Sets the value of the suspensionAvg property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setSuspensionAvg(Double value) {
                this.suspensionAvg = value;
            }

            /**
             * Gets the value of the suspensionMin property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getSuspensionMin() {
                return suspensionMin;
            }

            /**
             * Sets the value of the suspensionMin property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setSuspensionMin(Double value) {
                this.suspensionMin = value;
            }

            /**
             * Gets the value of the suspensionMax property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getSuspensionMax() {
                return suspensionMax;
            }

            /**
             * Sets the value of the suspensionMax property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setSuspensionMax(Double value) {
                this.suspensionMax = value;
            }

            /**
             * Gets the value of the suspensionSum property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getSuspensionSum() {
                return suspensionSum;
            }

            /**
             * Sets the value of the suspensionSum property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setSuspensionSum(Double value) {
                this.suspensionSum = value;
            }

        }

    }

}
