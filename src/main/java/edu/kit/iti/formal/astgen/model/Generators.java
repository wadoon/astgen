package edu.kit.iti.formal.astgen.model;

import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
@ToString
@XmlType//namespace = "http://formal.iti.kit.edu/astgen")
@XmlAccessorType(XmlAccessType.FIELD)
public class Generators {
    @XmlElement(name = "node", required = true)
    public List<Modifier> node = new ArrayList<>();

    @XmlElement(name = "hierarchy", required = true)
    public List<Modifier> hierarchy = new ArrayList<>();

    @XmlType(propOrder = {"clazz", "config"})
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Modifier {
        @XmlAttribute(name = "class")
        public String clazz;

        @XmlElement(name = "entry", nillable = false)
        public List<Entry> config = new ArrayList<>();
    }

    @XmlType(propOrder = {"key", "value"})
    public static class Entry {
        @XmlAttribute(required = true)
        public String key;

        @XmlValue
        public String value;
    }
}