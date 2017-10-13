package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.gen.HierarchyGenerator;
import edu.kit.iti.formal.astgen.model.Hierarchy;
import edu.kit.iti.formal.astgen.model.Node;
import net.sourceforge.jenesis4java.*;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class VisitorGenerator0 implements HierarchyGenerator {

    @Override
    public CompilationUnit modify(String sourceFolder, Hierarchy hierarchy) {
        VisitorGeneratorN vgn = new VisitorGeneratorN(0, sourceFolder, hierarchy);
        return vgn.build();
    }

    public static class VisitorGeneratorN {
        private final ClassType type;
        private final int arg;
        private final Interface topLevel;
        private final Hierarchy hierarchy;
        private final CompilationUnit cu;
        VirtualMachine vm = VirtualMachine.getVirtualMachine();

        public VisitorGeneratorN(int arg, String source, Hierarchy hierarchy) {
            this.arg = arg;
            this.hierarchy = hierarchy;
            type = vm.newType(AddAcceptMethod.getGenericArguments(arg, "R") + " R");
            cu = vm.newCompilationUnit(source, hierarchy.packageName);
            topLevel = cu.newPublicInterface("Visitor" + arg + "<R>");
        }

        public CompilationUnit build() {
            hierarchy.getFlatList().forEach(this::addMethod);
            return cu;
        }

        private void addMethod(Node node) {
            AbstractMethod m = topLevel.newMethod(type, "visit");
            m.addParameter(vm.newType(node.name), "node");
            for (int i = 0; i < arg; i++) {
                m.addParameter(vm.newType("T" + i), "arg" + i);
            }
        }


    }

}
