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
 *         &lt;element name="purepaths" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="purepath" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element ref="{}browsernode" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="tagid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="start" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="duration" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="timechart" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="javascript" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                           &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="activitykind" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="apis" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="statistics" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "purepaths"
})
@XmlRootElement(name = "javascripterrordashlet")
public class Javascripterrordashlet {

    protected SourceType source;
    protected SourceType comparesource;
    protected Javascripterrordashlet.Purepaths purepaths;
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
     * Gets the value of the purepaths property.
     * 
     * @return
     *     possible object is
     *     {@link Javascripterrordashlet.Purepaths }
     *     
     */
    public Javascripterrordashlet.Purepaths getPurepaths() {
        return purepaths;
    }

    /**
     * Sets the value of the purepaths property.
     * 
     * @param value
     *     allowed object is
     *     {@link Javascripterrordashlet.Purepaths }
     *     
     */
    public void setPurepaths(Javascripterrordashlet.Purepaths value) {
        this.purepaths = value;
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
     *         &lt;element name="purepath" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{}browsernode" maxOccurs="unbounded" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="tagid" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="start" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="duration" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="timechart" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="javascript" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                 &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="activitykind" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="apis" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="statistics" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        "purepath"
    })
    public static class Purepaths {

        protected List<Javascripterrordashlet.Purepaths.Purepath> purepath;

        /**
         * Gets the value of the purepath property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the purepath property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPurepath().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Javascripterrordashlet.Purepaths.Purepath }
         * 
         * 
         */
        public List<Javascripterrordashlet.Purepaths.Purepath> getPurepath() {
            if (purepath == null) {
                purepath = new ArrayList<Javascripterrordashlet.Purepaths.Purepath>();
            }
            return this.purepath;
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
         *         &lt;element ref="{}browsernode" maxOccurs="unbounded" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="tagid" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="start" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="duration" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="timechart" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="javascript" type="{http://www.w3.org/2001/XMLSchema}double" />
         *       &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="activitykind" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="apis" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="statistics" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "browsernode"
        })
        public static class Purepath {

            protected List<Browsernode> browsernode;
            @XmlAttribute(name = "state")
            protected String state;
            @XmlAttribute(name = "type")
            protected String type;
            @XmlAttribute(name = "name")
            protected String name;
            @XmlAttribute(name = "tagid")
            protected String tagid;
            @XmlAttribute(name = "start")
            protected String start;
            @XmlAttribute(name = "duration")
            protected Double duration;
            @XmlAttribute(name = "timechart")
            protected String timechart;
            @XmlAttribute(name = "javascript")
            protected Double javascript;
            @XmlAttribute(name = "size")
            protected BigInteger size;
            @XmlAttribute(name = "activitykind")
            protected String activitykind;
            @XmlAttribute(name = "apis")
            protected String apis;
            @XmlAttribute(name = "statistics")
            protected String statistics;

            /**
             * Gets the value of the browsernode property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the browsernode property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getBrowsernode().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Browsernode }
             * 
             * 
             */
            public List<Browsernode> getBrowsernode() {
                if (browsernode == null) {
                    browsernode = new ArrayList<Browsernode>();
                }
                return this.browsernode;
            }

            /**
             * Gets the value of the state property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getState() {
                return state;
            }

            /**
             * Sets the value of the state property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setState(String value) {
                this.state = value;
            }

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
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
             * Gets the value of the tagid property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTagid() {
                return tagid;
            }

            /**
             * Sets the value of the tagid property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTagid(String value) {
                this.tagid = value;
            }

            /**
             * Gets the value of the start property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStart() {
                return start;
            }

            /**
             * Sets the value of the start property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStart(String value) {
                this.start = value;
            }

            /**
             * Gets the value of the duration property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getDuration() {
                return duration;
            }

            /**
             * Sets the value of the duration property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setDuration(Double value) {
                this.duration = value;
            }

            /**
             * Gets the value of the timechart property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTimechart() {
                return timechart;
            }

            /**
             * Sets the value of the timechart property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTimechart(String value) {
                this.timechart = value;
            }

            /**
             * Gets the value of the javascript property.
             * 
             * @return
             *     possible object is
             *     {@link Double }
             *     
             */
            public Double getJavascript() {
                return javascript;
            }

            /**
             * Sets the value of the javascript property.
             * 
             * @param value
             *     allowed object is
             *     {@link Double }
             *     
             */
            public void setJavascript(Double value) {
                this.javascript = value;
            }

            /**
             * Gets the value of the size property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getSize() {
                return size;
            }

            /**
             * Sets the value of the size property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setSize(BigInteger value) {
                this.size = value;
            }

            /**
             * Gets the value of the activitykind property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getActivitykind() {
                return activitykind;
            }

            /**
             * Sets the value of the activitykind property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setActivitykind(String value) {
                this.activitykind = value;
            }

            /**
             * Gets the value of the apis property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getApis() {
                return apis;
            }

            /**
             * Sets the value of the apis property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setApis(String value) {
                this.apis = value;
            }

            /**
             * Gets the value of the statistics property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStatistics() {
                return statistics;
            }

            /**
             * Sets the value of the statistics property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStatistics(String value) {
                this.statistics = value;
            }

        }

    }

}
