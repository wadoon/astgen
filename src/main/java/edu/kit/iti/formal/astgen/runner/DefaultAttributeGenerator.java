package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.model.Attr;
import edu.kit.iti.formal.astgen.model.Node;
import net.sourceforge.jenesis4java.*;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class DefaultAttributeGenerator extends AbstractNodeGenerator {
    @Override
    public CompilationUnit modify(VirtualMachine vm, Node node, CompilationUnit unit) {
        PackageClass c = (PackageClass) unit.getTopLevelType();

        for (Attr attr : node.attributes) {
            ClassType type = null;

            if (attr.multi != null && attr.multi) {
                if (attr.unique != null && attr.unique)
                    type = vm.newType("Set<" + attr.type + ">");
                else
                    type = vm.newType("List<" + attr.type + ">");
            } else {
                type = vm.newType(attr.type);
            }

            ClassField field = c.newField(type, attr.name);
            field.setAccess(Access.PROTECTED);
            if (attr.frozen != null && attr.frozen) {
                field.isFinal(true);
            }

            if (attr.nonNull != null && attr.nonNull) {
                field.addAnnotation("Nonnull");
            }
        }

        c.addAnnotation("Data");
        c.addAnnotation("NoArgsConstructor");
        c.addAnnotation("AllArgsConstructor");
        c.addAnnotation("RequiredArgsConstructor");

        return unit;
    }
}
