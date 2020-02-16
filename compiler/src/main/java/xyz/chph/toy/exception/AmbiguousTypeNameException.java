package xyz.chph.toy.exception;

public class AmbiguousTypeNameException extends CompilationException {
    public AmbiguousTypeNameException(String typeName, String[] strings) {
        System.err.println("oh shit you fucked here: \"" + typeName + "\" is ambiguous in namespace you are using!");
        for (String str : strings) {
            System.err.println("\t" + str);
        }
    }
}
