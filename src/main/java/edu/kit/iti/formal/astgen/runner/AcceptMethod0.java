package edu.kit.iti.formal.astgen.runner;

import edu.kit.iti.formal.astgen.model.Node;
import net.sourceforge.jenesis4java.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author Alexander Weigl
 * @version 1 (10.09.17)
 */
public class AcceptMethod0 extends AbstractNodeGenerator{
    public static void generateAccept(VirtualMachine vm, PackageClass c, int args) {
        String gtypes = getGenericArguments(args, "R");


        String name = args == 0 ? "accept" : "accept" + args;
        ClassMethod accept = c.newMethod(vm.newType(gtypes + " R"), name);
        accept.setAccess(Access.PUBLIC);
        accept.addParameter(vm.newType("Visitor" + args + "<R>"), "visitor");

        Invoke inv = vm.newInvoke("visitor", "visit");
        inv.addVariableArg("this");
        for (int i = 0; i < args; i++) {
            String argName = "arg" + i;
            accept.addParameter(vm.newType("T" + i), argName);
            inv.addVariableArg(argName);
        }
        accept.newReturn().setExpression(inv);
    }

    public static String getGenericArguments(int args, String... fix) {
        if (args == 0 && fix.length == 0) {
            throw new IllegalArgumentException();
        }

        String prefix = Arrays.stream(fix).reduce((a, b) -> a + ", " + b)
                .orElse("");

        final Optional<String> opt = IntStream.range(0, args)
                .mapToObj(i -> "T" + i)
                .reduce((a, b) -> a + ", " + b);

        prefix += !prefix.isEmpty() && opt.isPresent() ? "," : "";
        final String suffix = opt.orElse("");
        return "<" + prefix + suffix + ">";
    }

    @Override
    public CompilationUnit modify(VirtualMachine vm, Node node, CompilationUnit unit) {
        PackageClass c = (PackageClass) unit.getTopLevelType();
        generateAccept(vm, c, 0);
        return unit;
    }
}
