import edu.kit.iti.formal.astgen.Astgen;
import org.junit.Test;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class AstgenTestExpr {
    @Test
    public void test() throws Exception {
        Astgen astgen = new Astgen();
        astgen.setHierarchyFile("src/test/resources/expr.xml");
        astgen.setSourceFolder("target/bla/");
        astgen.run();
    }
}
