package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.gen.NodeGenerator;
import edu.kit.iti.formal.astgen.model.Hierarchy;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public abstract class AbstractNodeGenerator implements NodeGenerator {
    @Getter
    @Setter
    protected Hierarchy hierarchy;
}
