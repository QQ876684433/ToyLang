package xyz.chph.toy.domain;

public class CompilationUnit {
    private ImportDeclaration importDeclaration;
    private ClassDeclaration classDeclaration;

    public CompilationUnit(ImportDeclaration importDeclaration, ClassDeclaration classDeclaration) {
        this.importDeclaration = importDeclaration;
        this.classDeclaration = classDeclaration;
    }


    public ClassDeclaration getClassDeclaration() {
        return classDeclaration;
    }

    public ImportDeclaration getImportDeclaration() {
        return importDeclaration;
    }


    public String getClassName() {
        return classDeclaration.getName();
    }
}
