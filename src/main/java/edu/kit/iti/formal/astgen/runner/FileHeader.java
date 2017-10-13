package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.model.Node;
import net.sourceforge.jenesis4java.Comment;
import net.sourceforge.jenesis4java.CompilationUnit;
import net.sourceforge.jenesis4java.VirtualMachine;

/**
 * @author Alexander Weigl
 * @version 1 (12.10.17)
 */
public class FileHeader extends AbstractNodeGenerator {
    public String comment;

    @Override
    public CompilationUnit modify(VirtualMachine vm, Node node, CompilationUnit unit) {
        unit.setComment(Comment.MULTI_LINE, comment.trim());
        return unit;
    }
}
