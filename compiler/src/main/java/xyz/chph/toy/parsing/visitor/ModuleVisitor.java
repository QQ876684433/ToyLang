package xyz.chph.toy.parsing.visitor;

import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.ModuleDeclaration;

/**
 * Created by steve_chph, 2020/02/03 22:24
 */
public class ModuleVisitor extends ToyBaseVisitor<ModuleDeclaration> {
    @Override
    public ModuleDeclaration visitModuleDeclaration(ToyParser.ModuleDeclarationContext ctx) {
        if (ctx.qualifiedName() != null) {
            String qualifiedName = ctx.qualifiedName().getText();
            return new ModuleDeclaration(qualifiedName);
        }
        return null;
    }
}
