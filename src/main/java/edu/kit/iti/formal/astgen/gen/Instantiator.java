package edu.kit.iti.formal.astgen.gen;

import edu.kit.iti.formal.astgen.model.Generators;

/**
 * @author Alexander Weigl
 * @version 1 (12.10.17)
 */
public class Instantiator {
    public static <T> T getInstance(Generators.Modifier s) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName(s.clazz);
        T t = (T) clazz.newInstance();
        for (Generators.Entry e : s.config) {
            String type = clazz.getField(e.key).getType().getSimpleName();
            Object value = e.value;
            switch (type) {
                case "int":
                case "Integer":
                    value = Integer.parseInt(e.value);
                    break;

                case "boolean":
                case "Boolean":
                    value = Boolean.parseBoolean(e.value);
                    break;


                case "double":
                case "Double":
                    value = Double.parseDouble(e.value);
                    break;
            }

            clazz.getField(e.key).set(t, value);
        }

        return t;
    }
}
