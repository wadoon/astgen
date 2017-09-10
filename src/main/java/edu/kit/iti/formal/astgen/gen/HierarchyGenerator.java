package edu.kit.iti.formal.astgen.gen;

import edu.kit.iti.formal.astgen.model.Hierarchy;
import net.sourceforge.jenesis4java.CompilationUnit;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public interface HierarchyGenerator {
    CompilationUnit modify(String sourceFolder, Hierarchy hierarchy);
}
