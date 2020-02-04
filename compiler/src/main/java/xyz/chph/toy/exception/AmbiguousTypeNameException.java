package xyz.chph.toy.exception;

public class AmbiguousTypeNameException extends CompilationException {
    public AmbiguousTypeNameException(String typeName) {
        System.out.println("oh shit you fucked here: \""+typeName+"\" is ambiguous in namespace you are using!");
    }
}
