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
@XmlRootElement(name = "hierarchy")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)      // UPDATE: Need this
@ToString
public class Hierarchy {
    @XmlElement
    public String packageName;

    @XmlElement
    public Generators generators = new Generators();

    @XmlElement(name = "node", type = Node.class)
    @XmlElementWrapper(name = "nodes", nillable = false)
    public List<Node> nodes = new ArrayList<>();

    public Stream<Node> getFlatList() {
        return nodes.stream().flatMap(Node::getFlatList);
    }


    public boolean isNode(String type) {
        return getFlatList().anyMatch(n -> n.name.equals(type));
    }
}