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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="process" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="chart" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="series" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="measurement" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *                                               &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}double" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="datasetnumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="seriesnumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="startdate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="enddate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="host" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="os" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="instrumentationstate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="uptime" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="health" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "process"
})
@XmlRootElement(name = "processdashlet")
public class Processdashlet {

    protected SourceType source;
    protected SourceType comparesource;
    protected List<Processdashlet.Process> process;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "description")
    protected String description;

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
     * Gets the value of the process property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the process property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Processdashlet.Process }
     * 
     * 
     */
    public List<Processdashlet.Process> getProcess() {
        if (process == null) {
            process = new ArrayList<Processdashlet.Process>();
        }
        return this.process;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="chart" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="series" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="measurement" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
     *                                     &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}double" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="datasetnumber" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="seriesnumber" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="startdate" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="enddate" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="host" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="os" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="instrumentationstate" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="uptime" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="health" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "chart"
    })
    public static class Process {

        protected List<Processdashlet.Process.Chart> chart;
        @XmlAttribute(name = "name")
        protected String name;
        @XmlAttribute(name = "type")
        protected String type;
        @XmlAttribute(name = "host")
        protected String host;
        @XmlAttribute(name = "state")
        protected String state;
        @XmlAttribute(name = "os")
        protected String os;
        @XmlAttribute(name = "instrumentationstate")
        protected String instrumentationstate;
        @XmlAttribute(name = "uptime")
        protected String uptime;
        @XmlAttribute(name = "health")
        protected String health;

        /**
         * Gets the value of the chart property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the chart property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChart().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Processdashlet.Process.Chart }
         * 
         * 
         */
        public List<Processdashlet.Process.Chart> getChart() {
            if (chart == null) {
                chart = new ArrayList<Processdashlet.Process.Chart>();
            }
            return this.chart;
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
         * Gets the value of the host property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHost() {
            return host;
        }

        /**
         * Sets the value of the host property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHost(String value) {
            this.host = value;
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
         * Gets the value of the os property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOs() {
            return os;
        }

        /**
         * Sets the value of the os property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOs(String value) {
            this.os = value;
        }

        /**
         * Gets the value of the instrumentationstate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInstrumentationstate() {
            return instrumentationstate;
        }

        /**
         * Sets the value of the instrumentationstate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInstrumentationstate(String value) {
            this.instrumentationstate = value;
        }

        /**
         * Gets the value of the uptime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUptime() {
            return uptime;
        }

        /**
         * Sets the value of the uptime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUptime(String value) {
            this.uptime = value;
        }

        /**
         * Gets the value of the health property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHealth() {
            return health;
        }

        /**
         * Sets the value of the health property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHealth(String value) {
            this.health = value;
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
         *         &lt;element name="series" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="measurement" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *                           &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="datasetnumber" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="seriesnumber" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="startdate" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="enddate" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "series"
        })
        public static class Chart {

            protected List<Processdashlet.Process.Chart.Series> series;
            @XmlAttribute(name = "name")
            protected String name;
            @XmlAttribute(name = "startdate")
            protected String startdate;
            @XmlAttribute(name = "enddate")
            protected String enddate;

            /**
             * Gets the value of the series property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the series property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getSeries().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Processdashlet.Process.Chart.Series }
             * 
             * 
             */
            public List<Processdashlet.Process.Chart.Series> getSeries() {
                if (series == null) {
                    series = new ArrayList<Processdashlet.Process.Chart.Series>();
                }
                return this.series;
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
             * Gets the value of the startdate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStartdate() {
                return startdate;
            }

            /**
             * Sets the value of the startdate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStartdate(String value) {
                this.startdate = value;
            }

            /**
             * Gets the value of the enddate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEnddate() {
                return enddate;
            }

            /**
             * Sets the value of the enddate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEnddate(String value) {
                this.enddate = value;
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
             *                 &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
             *                 &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}double" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="datasetnumber" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="seriesnumber" type="{http://www.w3.org/2001/XMLSchema}string" />
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
            public static class Series {

                protected List<Processdashlet.Process.Chart.Series.Measurement> measurement;
                @XmlAttribute(name = "name")
                protected String name;
                @XmlAttribute(name = "datasetnumber")
                protected String datasetnumber;
                @XmlAttribute(name = "seriesnumber")
                protected String seriesnumber;

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
                 * {@link Processdashlet.Process.Chart.Series.Measurement }
                 * 
                 * 
                 */
                public List<Processdashlet.Process.Chart.Series.Measurement> getMeasurement() {
                    if (measurement == null) {
                        measurement = new ArrayList<Processdashlet.Process.Chart.Series.Measurement>();
                    }
                    return this.measurement;
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
                 * Gets the value of the datasetnumber property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDatasetnumber() {
                    return datasetnumber;
                }

                /**
                 * Sets the value of the datasetnumber property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDatasetnumber(String value) {
                    this.datasetnumber = value;
                }

                /**
                 * Gets the value of the seriesnumber property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSeriesnumber() {
                    return seriesnumber;
                }

                /**
                 * Sets the value of the seriesnumber property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSeriesnumber(String value) {
                    this.seriesnumber = value;
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
                 *       &lt;attribute name="timestamp" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
                 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}double" />
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
                    @XmlSchemaType(name = "dateTime")
                    protected XMLGregorianCalendar timestamp;
                    @XmlAttribute(name = "value")
                    protected Double value;

                    /**
                     * Gets the value of the timestamp property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public XMLGregorianCalendar getTimestamp() {
                        return timestamp;
                    }

                    /**
                     * Sets the value of the timestamp property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public void setTimestamp(XMLGregorianCalendar value) {
                        this.timestamp = value;
                    }

                    /**
                     * Gets the value of the value property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getValue() {
                        return value;
                    }

                    /**
                     * Sets the value of the value property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setValue(Double value) {
                        this.value = value;
                    }

                }

            }

        }

    }

}
