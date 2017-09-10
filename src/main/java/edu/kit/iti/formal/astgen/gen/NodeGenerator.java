package edu.kit.iti.formal.astgen.gen;

import edu.kit.iti.formal.astgen.model.Hierarchy;
import edu.kit.iti.formal.astgen.model.Node;
import net.sourceforge.jenesis4java.CompilationUnit;
import net.sourceforge.jenesis4java.VirtualMachine;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public interface NodeGenerator {
    void setHierarchy(Hierarchy h);

    CompilationUnit modify(VirtualMachine vm, Node node, CompilationUnit unit);
}
