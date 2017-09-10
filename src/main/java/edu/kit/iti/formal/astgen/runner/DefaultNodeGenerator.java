package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.model.Node;
import net.sourceforge.jenesis4java.CompilationUnit;
import net.sourceforge.jenesis4java.VirtualMachine;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class DefaultNodeGenerator extends AbstractNodeGenerator {
    @Override
    public CompilationUnit modify(VirtualMachine vm, Node node, CompilationUnit unit) {

        return unit;
    }
}
