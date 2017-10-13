package edu.kit.iti.formal.astgen.gen;

import edu.kit.iti.formal.astgen.model.Generators;
import lombok.Getter;
import lombok.Setter;
import net.sourceforge.jenesis4java.CompilationUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class HierarchyRunner extends AbstractRunner {
    @Getter
    @Setter
    private List<HierarchyGenerator> generators = new ArrayList<>();

    public HierarchyRunner(List<Generators.Modifier> gen) {
        gen.forEach(s -> {
                    try {
                        generators.add(Instantiator.getInstance(s));
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Override
    public void process() {
        generators.stream()
                .map(hg -> hg.modify(sourceDirectory, getHierarchy()))
                .forEach(CompilationUnit::encode);
    }
}
