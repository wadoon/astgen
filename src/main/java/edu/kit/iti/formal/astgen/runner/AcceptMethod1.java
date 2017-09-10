package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.gen.NodeGenerator;
import edu.kit.iti.formal.astgen.model.Node;
import net.sourceforge.jenesis4java.CompilationUnit;
import net.sourceforge.jenesis4java.PackageClass;
import net.sourceforge.jenesis4java.VirtualMachine;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class AcceptMethod1 extends AbstractNodeGenerator {
    @Override
    public CompilationUnit modify(VirtualMachine vm, Node node, CompilationUnit unit) {
        AcceptMethod0.generateAccept(vm, (PackageClass) unit.getTopLevelType(), 1);
        return unit;
    }
}
