package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.gen.HierarchyGenerator;
import edu.kit.iti.formal.astgen.model.Hierarchy;
import edu.kit.iti.formal.astgen.runner.VisitorGenerator0.VisitorGeneratorN;
import net.sourceforge.jenesis4java.CompilationUnit;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class VisitorGenerator1 implements HierarchyGenerator {

    @Override
    public CompilationUnit modify(String sourceFolder, Hierarchy hierarchy) {
        VisitorGeneratorN vgn = new VisitorGeneratorN(1, sourceFolder, hierarchy);
        return vgn.build();
    }

}