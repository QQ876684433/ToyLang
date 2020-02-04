package xyz.chph.toy.parsing.visitor;

import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.ClassDeclaration;
import xyz.chph.toy.domain.CompilationUnit;
import xyz.chph.toy.domain.MetaData;
import xyz.chph.toy.domain.ModuleDeclaration;

public class CompilationUnitVisitor extends ToyBaseVisitor<CompilationUnit> {
    @Override
    public CompilationUnit visitCompilationUnit(ToyParser.CompilationUnitContext ctx) {
        ToyParser.ModuleDeclarationContext moduleDeclarationContext = ctx.moduleDeclaration();
        ToyParser.ClassDeclarationContext classDeclarationContext = ctx.classDeclaration();
        if (moduleDeclarationContext == null)
            return new CompilationUnit(
                    new ModuleDeclaration(""),
                    classDeclarationContext.accept(
                            new ClassVisitor(new MetaData("")))
            );

        ModuleVisitor moduleVisitor = new ModuleVisitor();
        ModuleDeclaration moduleDeclaration = moduleDeclarationContext.accept(moduleVisitor);
        // pass module name to class declaration
        MetaData metaData = new MetaData(moduleDeclaration.getQualifiedName());
        ClassVisitor classVisitor = new ClassVisitor(metaData);
        ClassDeclaration classDeclaration = classDeclarationContext.accept(classVisitor);
        return new CompilationUnit(moduleDeclaration, classDeclaration);
    }
}
