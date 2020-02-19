package xyz.chph.toy.parsing.visitor;

import org.antlr.v4.runtime.tree.TerminalNode;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.ImportDeclaration;

import java.util.List;

/**
 * Created by steve_chph, 21:55
 */
public class ImportVisitor extends ToyBaseVisitor<ImportDeclaration> {
    @Override
    public ImportDeclaration visitImportDeclaration(ToyParser.ImportDeclarationContext ctx) {
        ImportDeclaration importDeclaration = new ImportDeclaration();
        String qualifiedName = ctx.qualifiedName().getText();
        List<ToyParser.ImportReferenceContext> importReferenceContexts = ctx.importList().importReference();
        importReferenceContexts.forEach(importReferenceContext -> {
            List<TerminalNode> IDs = importReferenceContext.ID();
            String className = IDs.get(0).getText();
            String alias = className;
            if (IDs.size() > 1) alias = IDs.get(1).getText();
            importDeclaration.add(alias, qualifiedName + "." + className);
        });
        return importDeclaration;
    }
}
