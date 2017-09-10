package edu.kit.iti.formal.astgen.gen;

import edu.kit.iti.formal.astgen.model.Hierarchy;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public abstract class AbstractRunner implements Runnable {
    @Getter
    @Setter
    protected Hierarchy hierarchy;

    @Setter
    @Getter
    protected String sourceDirectory;

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void process() throws Exception;
}
