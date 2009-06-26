
package fr.unice.i3s.modalis.jseduite.technical.news.breaking;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteBreakingNews complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteBreakingNews">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="b" type="{http://breaking.news.technical.jSeduite.modalis.i3s.unice.fr/}breakNew" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteBreakingNews", propOrder = {
    "b"
})
public class DeleteBreakingNews {

    protected BreakNew b;

    /**
     * Gets the value of the b property.
     * 
     * @return
     *     possible object is
     *     {@link BreakNew }
     *     
     */
    public BreakNew getB() {
        return b;
    }

    /**
     * Sets the value of the b property.
     * 
     * @param value
     *     allowed object is
     *     {@link BreakNew }
     *     
     */
    public void setB(BreakNew value) {
        this.b = value;
    }

}
