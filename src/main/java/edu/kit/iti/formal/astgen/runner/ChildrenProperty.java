package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.model.Attr;
import edu.kit.iti.formal.astgen.model.Node;
import net.sourceforge.jenesis4java.*;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class ChildrenProperty extends AbstractNodeGenerator {

    @Override
    public CompilationUnit modify(VirtualMachine vm, Node node,
                                  CompilationUnit unit) {
        PackageClass c = (PackageClass) unit.getTopLevelType();
        ClassMethod m = c.newMethod(vm.newType("Collection<Object>"),
                "getChildren");
        m.setAccess(Access.PUBLIC);
        //m.addAnnotation("Override");
        m.newDeclarationLet(vm.newType("List<Object>"))
                .addAssign("rt", vm.newClass(vm.newType("ArrayList<>"))
                        .addArg(vm.newInt(node.attributes.size())));
        for (Attr attr : node.attributes) {
            if (hierarchy.isNode(attr.type)) {
                m.newStmt(vm.newInvoke(vm.newVar("rt"), "add").addVariableArg(attr.name));
            }
        }

        m.newReturn().setExpression(vm.newVar("rt"));
        return unit;
    }
}
