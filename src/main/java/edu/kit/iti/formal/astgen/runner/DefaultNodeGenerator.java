package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.model.Node;
import net.sourceforge.jenesis4java.Comment;
import net.sourceforge.jenesis4java.CompilationUnit;
import net.sourceforge.jenesis4java.PackageClass;
import net.sourceforge.jenesis4java.VirtualMachine;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class DefaultNodeGenerator extends AbstractNodeGenerator {
    @Override
    public CompilationUnit modify(VirtualMachine vm, Node node, CompilationUnit unit) {
        PackageClass c = (PackageClass) unit.getTopLevelType();
        if (node.parent != null) c.setExtends(node.parent.name);
        if (node.final_ != null) c.isFinal(node.final_);
        if (node.abstrakt != null) c.isAbstract(node.abstrakt);

        unit.getTopLevelType().addAnnotation("javax.annotation.Generated",
                '"' + DefaultNodeGenerator.class.getCanonicalName() + '"'
        );

        if (node.comment != null)
            unit.getTopLevelType().setComment(
                    Comment.DOCUMENTATION,
                    node.comment
            );
        return unit;
    }
}
