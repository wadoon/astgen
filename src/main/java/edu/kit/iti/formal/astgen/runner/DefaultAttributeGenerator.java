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
            ClassType type = vm.newType(attr.type);

            if (isTrue(attr.multi)) {
                if (isTrue(attr.unique))
                    type = vm.newType("Set<" + attr.type + ">");
                else
                    type = vm.newType("List<" + attr.type + ">");
            }

            if (isTrue(attr.resolvable)) {
                type = vm.newType("IdentifiableObject<" + type + ">");
                if (isTrue(attr.multi)) {
                    if (isTrue(attr.unique))
                        type = vm.newType("IdentifiableSet<" + attr.type + ">");
                    else
                        type = vm.newType("IdentifiableList<" + attr.type + ">");
                }

                createGetterAndSetterId(attr);
                attr.frozen = true;
            }

            ClassField field = c.newField(type, attr.name);
            field.setAccess(Access.PROTECTED);
            if (isTrue(attr.frozen)) {
                field.isFinal(true);
            }

            if (attr.nonNull != null && attr.nonNull) {
                field.addAnnotation("Nonnull");
            }

            if (attr.comment != null) {
                field.setComment(Comment.DOCUMENTATION, attr.comment);
            }
        }

        c.addAnnotation("Data");
        c.addAnnotation("NoArgsConstructor");
        c.addAnnotation("AllArgsConstructor");
        c.addAnnotation("RequiredArgsConstructor");

        return unit;
    }

    private void createGetterAndSetterId(Attr attr) {

    }

    private boolean isTrue(Boolean b) {
        return b != null && b;
    }
}
