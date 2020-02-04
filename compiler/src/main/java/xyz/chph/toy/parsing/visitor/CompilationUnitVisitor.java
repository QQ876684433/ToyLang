package xyz.chph.toy.parsing.visitor;

import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.*;
import xyz.chph.toy.domain.scope.Namespace;
import xyz.chph.toy.exception.UsingModuleConflictException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompilationUnitVisitor extends ToyBaseVisitor<CompilationUnit> {
    @Override
    public CompilationUnit visitCompilationUnit(ToyParser.CompilationUnitContext ctx) {
        ToyParser.ModuleDeclarationContext moduleDeclarationContext = ctx.moduleDeclaration();
        List<ToyParser.UsingDeclarationContext> usingDeclarationContexts = ctx.usingDeclaration();
        ToyParser.ClassDeclarationContext classDeclarationContext = ctx.classDeclaration();

        ModuleDeclaration moduleDeclaration;
        if (moduleDeclarationContext == null) {
            moduleDeclaration = new ModuleDeclaration("");
        } else {
            moduleDeclaration = moduleDeclarationContext.accept(new ModuleVisitor());
        }

        UsingVisitor usingVisitor = new UsingVisitor();
        Set<String> subModules = new HashSet<>();
        Set<String> qualifiedNames = new HashSet<>();
        for (ToyParser.UsingDeclarationContext usingDeclarationContext : usingDeclarationContexts) {
            UsingDeclaration usingDeclaration = usingDeclarationContext.accept(usingVisitor);
            // check if a module using more than once
            if (subModules.contains(usingDeclaration.getName())
                    || qualifiedNames.contains(usingDeclaration.getName())) {
                throw new UsingModuleConflictException(usingDeclaration.getName());
            }
            if (usingDeclaration.getType() == UsingDeclaration.SUBMODULE) {
                subModules.add(usingDeclaration.getName());
            } else {
                qualifiedNames.add(usingDeclaration.getName());
            }
        }
        // add java.lang.* and java.util.* to namespace if not exist
        subModules.add("java.lang");
        subModules.add("java.util");
        Namespace namespace = new Namespace(subModules, qualifiedNames);

        // pass module name to class declaration
        MetaData metaData = new MetaData(moduleDeclaration.getQualifiedName());
        ClassVisitor classVisitor = new ClassVisitor(metaData, namespace);
        ClassDeclaration classDeclaration = classDeclarationContext.accept(classVisitor);
        return new CompilationUnit(moduleDeclaration, classDeclaration);
    }
}
