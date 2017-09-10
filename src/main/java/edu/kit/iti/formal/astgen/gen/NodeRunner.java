package edu.kit.iti.formal.astgen.gen;

import edu.kit.iti.formal.astgen.model.Node;
import edu.kit.iti.formal.astgen.runner.AbstractNodeGenerator;
import lombok.Getter;
import net.sourceforge.jenesis4java.CompilationUnit;
import net.sourceforge.jenesis4java.PackageClass;
import net.sourceforge.jenesis4java.VirtualMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class NodeRunner extends AbstractRunner {
    @Getter
    private final List<NodeGenerator> generatorsUnit = new ArrayList<>();

    public NodeRunner(List<String> genUnit) {
        generatorsUnit.add(new AbstractNodeGenerator() {
            @Override
            public CompilationUnit modify(VirtualMachine vm, Node node, CompilationUnit unit) {
                return createUnit(vm, node, unit);
            }
        });

        generatorsUnit.add(new AbstractNodeGenerator() {
            @Override
            public CompilationUnit modify(VirtualMachine vm, Node node, CompilationUnit unit) {
                return addImports(vm, node, unit);
            }
        });

        generatorsUnit.add(new AbstractNodeGenerator() {
            @Override
            public CompilationUnit modify(VirtualMachine vm, Node node, CompilationUnit unit) {
                return addClass(vm, node, unit);
            }
        });

        for (String s : genUnit) {
            try {
                generatorsUnit.add((NodeGenerator) Class.forName(s).newInstance());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    private CompilationUnit createUnit(VirtualMachine vm, Node node, CompilationUnit unit) {
        return vm.newCompilationUnit(getSourceDirectory(), getHierarchy().packageName);
    }
    private CompilationUnit addImports(VirtualMachine vm, Node node, CompilationUnit unit) {
        unit.addImport("lombok.*");
        unit.addImport("java.util.*");
        return unit;
    }

    private CompilationUnit addClass(VirtualMachine vm, Node node, CompilationUnit unit) {
        PackageClass c = unit.newPublicClass(node.name);
        if (node.parent != null) c.setExtends(node.parent.name);
        return unit;
    }

    @Override
    public void process() {
        getHierarchy().getFlatList().forEach(
                node -> {
                    final VirtualMachine vm = VirtualMachine.getVirtualMachine();
                    CompilationUnit unit = null;

                    for (NodeGenerator gen : generatorsUnit) {
                        gen.setHierarchy(hierarchy);
                        unit = gen.modify(vm, node, unit);
                    }
                    unit.encode();
                });
    }
}
