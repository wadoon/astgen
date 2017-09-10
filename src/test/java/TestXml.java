import edu.kit.iti.formal.astgen.model.Hierarchy;
import edu.kit.iti.formal.astgen.model.Attr;
import edu.kit.iti.formal.astgen.model.Node;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class TestXml {
    @Test
    public void gen() throws JAXBException {
        Hierarchy h = new Hierarchy();
        h.generators.hierarchy.add("edu.kit.iti.formal.KotlinGenerator");
        h.generators.node.add("edu.kit.iti.formal.VisitorGenerator");

        Node n = new Node();
        n.name = "Top";
        Attr l = new Attr();
        l.name = "blubber";
        l.multi = true;
        l.frozen = (true);
        l.nonNull = (true);
        l.type = ("String");
        l.defaultValue = ("qqq");
        //n.attributes.add(l);
        h.nodes.add(n);
        Node n1 = new Node();
        n1.name = "blubb";
        n.children.add(n1);

        JAXBContext jaxbContext = JAXBContext.newInstance(Hierarchy.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(h, System.out);
    }
}
