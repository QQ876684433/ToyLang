package xyz.chph.toy.parsing.visitor;

import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.ClassDeclaration;
import xyz.chph.toy.domain.CompilationUnit;
import xyz.chph.toy.domain.ImportDeclaration;
import xyz.chph.toy.domain.scope.Namespace;

import java.util.List;
import java.util.stream.Collectors;

public class CompilationUnitVisitor extends ToyBaseVisitor<CompilationUnit> {
    private final String module;

    public CompilationUnitVisitor(String module) {
        this.module = module;
    }

    @Override
    public CompilationUnit visitCompilationUnit(ToyParser.CompilationUnitContext ctx) {
        ImportVisitor importVisitor = new ImportVisitor();
        List<ToyParser.ImportDeclarationContext> importDeclarationContexts = ctx.importDeclaration();
        ToyParser.ClassDeclarationContext classDeclarationContext = ctx.classDeclaration();
        ImportDeclaration importDeclaration = importDeclarationContexts.stream()
                .map(importDeclarationContext -> importDeclarationContext.accept(importVisitor))
                .reduce(ImportDeclaration::new).orElse(new ImportDeclaration());
        ClassVisitor classVisitor = new ClassVisitor(module, new Namespace(importDeclaration.getImports()));
        ClassDeclaration classDeclaration = classDeclarationContext.accept(classVisitor);
        return new CompilationUnit(importDeclaration, classDeclaration);
    }
}
