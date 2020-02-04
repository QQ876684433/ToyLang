package xyz.chph.toy.domain;

public class CompilationUnit {
    private ModuleDeclaration moduleDeclaration;
    private ClassDeclaration classDeclaration;

    public CompilationUnit(ModuleDeclaration moduleDeclaration, ClassDeclaration classDeclaration) {
        this.moduleDeclaration = moduleDeclaration;
        this.classDeclaration = classDeclaration;
    }

    public ModuleDeclaration getModuleDeclaration() {
        return moduleDeclaration;
    }


    public ClassDeclaration getClassDeclaration() {
        return classDeclaration;
    }


    public String getModuleName() {
        return moduleDeclaration.getQualifiedName();
    }

    public String getClassName() {
        return classDeclaration.getName();
    }
}
