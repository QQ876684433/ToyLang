package xyz.chph.toy.domain;

public class CompilationUnit {
    private ClassDeclaration classDeclaration;

    public CompilationUnit(ClassDeclaration classDeclaration) {
        this.classDeclaration = classDeclaration;
    }


    public ClassDeclaration getClassDeclaration() {
        return classDeclaration;
    }


    public String getClassName() {
        return classDeclaration.getName();
    }
}
