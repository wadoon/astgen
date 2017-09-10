package edu.kit.iti.formal.astgen.model;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
@ToString
@XmlType(name = "attr",
        propOrder = {"name", "type", "multi", "unique",
                "frozen", "nonNull", "defaultValue"})
@XmlAccessorType(XmlAccessType.FIELD)      // UPDATE: Need this
public class Attr {
    @XmlAttribute(required = true)
    public String name;

    @XmlAttribute(required = true)
    public String type;

    @XmlAttribute(required = false)
    public Boolean multi;

    @XmlAttribute(required = false)
    public Boolean unique;

    @XmlAttribute
    public Boolean frozen = false;

    @XmlAttribute
    public Boolean nonNull = false;

    @XmlAttribute
    public String defaultValue;
}
