package edu.kit.iti.formal.astgen;

import edu.kit.iti.formal.astgen.gen.HierarchyRunner;
import edu.kit.iti.formal.astgen.gen.NodeRunner;
import edu.kit.iti.formal.astgen.model.Attr;
import edu.kit.iti.formal.astgen.model.Hierarchy;
import edu.kit.iti.formal.astgen.model.Node;
import edu.kit.iti.formal.astgen.runner.VisitorGenerator0;
import edu.kit.iti.formal.astgen.runner.VisitorGenerator1;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class Astgen {
    @Getter
    @Setter
    private Hierarchy hierarchy;

    @Getter
    @Setter
    private String hierarchyFile;

    @Getter
    @Setter
    private String sourceFolder;

    public static void main(String[] args) throws IOException, JAXBException {
        Astgen astgen = new Astgen();

        astgen.setHierarchyFile(args[0]);
        astgen.setSourceFolder(args[1]);
        astgen.run();
    }

    public void run() throws IOException, JAXBException {
        setHierarchy(loadHierarchyFile());

        Files.createDirectories(Paths.get(sourceFolder));

        NodeRunner nodeRunner = new NodeRunner(getHierarchy().generators.node);
        nodeRunner.setHierarchy(getHierarchy());
        nodeRunner.setSourceDirectory(getSourceFolder());
        nodeRunner.run();

        HierarchyRunner hierarchyRunner = new HierarchyRunner(hierarchy.generators.hierarchy);
        hierarchyRunner.setHierarchy(hierarchy);
        hierarchyRunner.setSourceDirectory(getSourceFolder());
        hierarchyRunner.run();
    }

    private Hierarchy loadHierarchyFile() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Hierarchy.class, Node.class, Attr.class);
        Unmarshaller m = jaxbContext.createUnmarshaller();

        Hierarchy h = (Hierarchy) m.unmarshal(new File(getHierarchyFile()));
        System.out.println(h);
        h.nodes.forEach(Node::updateParent);
        return h;
    }
}
