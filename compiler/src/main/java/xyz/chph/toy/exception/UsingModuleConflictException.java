package xyz.chph.toy.exception;

public class UsingModuleConflictException extends CompilationException {
    public UsingModuleConflictException(String moduleName) {
        System.out.println("oh shit you fucked up here: using module \"" + moduleName + "\" more than once!");
    }
}
