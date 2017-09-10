package edu.kit.iti.formal.astgen.gen;

import lombok.Getter;
import lombok.Setter;
import net.sourceforge.jenesis4java.CompilationUnit;
import net.sourceforge.jenesis4java.impl.MCodeWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public HierarchyRunner(List<String> gen) {
        gen.forEach(s -> {
                    try {
                        generators.add(
                                (HierarchyGenerator) Class.forName(s).newInstance());
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
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
