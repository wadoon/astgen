package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.model.Node;
import net.sourceforge.jenesis4java.*;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class NameProperty extends AbstractNodeGenerator {
    @Override
    public CompilationUnit modify(VirtualMachine vm, Node node,
                                  CompilationUnit unit) {
        PackageClass c = (PackageClass) unit.getTopLevelType();
        ClassMethod m = c.newMethod(vm.newType("String"), "getName");
        m.setAccess(Access.PUBLIC);
        //m.addAnnotation("Override");
        m.newReturn().setExpression(
                vm.newInvoke(
                        vm.newInvoke("getClass"),
                        "getSimpleName"));
        return unit;
    }
}
