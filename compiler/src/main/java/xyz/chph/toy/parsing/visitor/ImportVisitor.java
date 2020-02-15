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
        importReferenceContexts.stream().map(importReferenceContext -> {
            List<TerminalNode> IDs = importReferenceContext.ID();
            if (IDs.size() > 1) return IDs.get(1).getText();
            else return IDs.get(0).getText();
        }).forEach(id -> importDeclaration.add(id, qualifiedName + "." + id));
        return importDeclaration;
    }
}
