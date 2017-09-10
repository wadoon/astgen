package edu.kit.iti.formal.astgen.model;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
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
    public List<String> node = new ArrayList<>();

    @XmlElement(name = "hierarchy", required = true)
    public List<String> hierarchy = new ArrayList<>();
}
