package xyz.chph.toy.parsing.visitor;

import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.ClassDeclaration;
import xyz.chph.toy.domain.CompilationUnit;

public class CompilationUnitVisitor extends ToyBaseVisitor<CompilationUnit> {
    @Override
    public CompilationUnit visitCompilationUnit(ToyParser.CompilationUnitContext ctx) {
        ClassVisitor classVisitor = new ClassVisitor();
        ToyParser.ClassDeclarationContext classDeclarationContext = ctx.classDeclaration();
        ClassDeclaration classDeclaration = classDeclarationContext.accept(classVisitor);
        return new CompilationUnit(classDeclaration);
    }
}
