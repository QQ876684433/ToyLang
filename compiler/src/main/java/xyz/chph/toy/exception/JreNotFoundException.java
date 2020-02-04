package xyz.chph.toy.exception;

public class JreNotFoundException extends CompilationException {
    public JreNotFoundException() {
        System.err.println("oh shit you fucked up here:" +
                " \"$JAVA_HOME/jre/lib/rt.jar\" not found on your system!");
    }
}
