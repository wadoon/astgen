package edu.kit.iti.formal.astgen.model;

import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
@ToString(exclude = "parent")
@XmlType(name = "node",
        propOrder = {"name", "abstrakt", "generatorClass", "attributes", "children", "subPackage", "comment"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Node {
    @XmlAttribute(name = "generator")
    public String generatorClass = null;

    @XmlAttribute
    public Boolean skip;

    @XmlAttribute(required = true)
    public String name;

    @XmlAttribute(name = "abstract", required = false)
    public Boolean abstrakt = false;

    @XmlTransient
    public Node parent;

    @XmlAttribute(name = "subPackage")
    public String subPackage;

    @XmlAttribute(name = "final", required = false)
    public Boolean final_ = false;

    @XmlElementWrapper(name = "children", nillable = true)
    @XmlElement(name = "node")
    public List<Node> children = new ArrayList<>();

    @XmlElement(name = "attr", nillable = true)
    public List<Attr> attributes = new ArrayList<>();

    @XmlElement(name = "comment")
    public String comment;

    public Stream<Node> getFlatList() {
        return Stream.concat(
                Stream.of(this),
                children.stream().flatMap(Node::getFlatList));
    }

    public void updateParent() {
        children.forEach(c -> c.parent = this);
        children.forEach(Node::updateParent);
    }

    public String getSubPackage() {
        if (subPackage != null) return subPackage;
        if (parent != null && parent.getSubPackage() != null) {
            return parent.getSubPackage();
        }
        return null;
    }
}
