//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.08 at 09:40:50 PM CEST 
//


package org.freedesktop.appstream.appdata;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
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
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{}id"/>
 *         &lt;element ref="{}translation"/>
 *         &lt;element ref="{}name"/>
 *         &lt;element ref="{}summary"/>
 *         &lt;element ref="{}description"/>
 *         &lt;element ref="{}icon"/>
 *         &lt;element ref="{}categories"/>
 *         &lt;element ref="{}keywords"/>
 *         &lt;element ref="{}kudos"/>
 *         &lt;element ref="{}mimetypes"/>
 *         &lt;element ref="{}project_license"/>
 *         &lt;element ref="{}url"/>
 *         &lt;element ref="{}project_group"/>
 *         &lt;element ref="{}compulsory_for_desktop"/>
 *         &lt;element ref="{}screenshots"/>
 *         &lt;element ref="{}releases"/>
 *         &lt;element ref="{}languages"/>
 *         &lt;element ref="{}bundle"/>
 *         &lt;element ref="{}developer_name"/>
 *         &lt;element ref="{}content_rating"/>
 *       &lt;/choice>
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idOrTranslationOrName"
})
@XmlRootElement(name = "component")
public class Component {

    @XmlElementRefs({
        @XmlElementRef(name = "id", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "summary", type = Summary.class, required = false),
        @XmlElementRef(name = "description", type = Description.class, required = false),
        @XmlElementRef(name = "project_group", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "bundle", type = Bundle.class, required = false),
        @XmlElementRef(name = "project_license", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "content_rating", type = ContentRating.class, required = false),
        @XmlElementRef(name = "translation", type = Translation.class, required = false),
        @XmlElementRef(name = "name", type = Name.class, required = false),
        @XmlElementRef(name = "keywords", type = Keywords.class, required = false),
        @XmlElementRef(name = "kudos", type = Kudos.class, required = false),
        @XmlElementRef(name = "url", type = Url.class, required = false),
        @XmlElementRef(name = "developer_name", type = DeveloperName.class, required = false),
        @XmlElementRef(name = "screenshots", type = Screenshots.class, required = false),
        @XmlElementRef(name = "languages", type = Languages.class, required = false),
        @XmlElementRef(name = "icon", type = Icon.class, required = false),
        @XmlElementRef(name = "mimetypes", type = Mimetypes.class, required = false),
        @XmlElementRef(name = "categories", type = Categories.class, required = false),
        @XmlElementRef(name = "compulsory_for_desktop", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "releases", type = Releases.class, required = false)
    })
    protected List<Object> idOrTranslationOrName;
    @XmlAttribute(name = "type")
    protected String type;

    /**
     * Gets the value of the idOrTranslationOrName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idOrTranslationOrName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdOrTranslationOrName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link Summary }
     * {@link Description }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link Bundle }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link ContentRating }
     * {@link Translation }
     * {@link Name }
     * {@link Keywords }
     * {@link Kudos }
     * {@link Url }
     * {@link DeveloperName }
     * {@link Screenshots }
     * {@link Languages }
     * {@link Icon }
     * {@link Mimetypes }
     * {@link Categories }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link Releases }
     * 
     * 
     */
    public List<Object> getIdOrTranslationOrName() {
        if (idOrTranslationOrName == null) {
            idOrTranslationOrName = new ArrayList<Object>();
        }
        return this.idOrTranslationOrName;
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

}
