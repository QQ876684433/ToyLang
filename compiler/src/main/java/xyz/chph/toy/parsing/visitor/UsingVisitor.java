package xyz.chph.toy.parsing.visitor;

import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.UsingDeclaration;

/**
 * Created by steve_chph, 2020/02/04 10:37
 */
public class UsingVisitor extends ToyBaseVisitor<UsingDeclaration> {
    @Override
    public UsingDeclaration visitUsingDeclaration(ToyParser.UsingDeclarationContext ctx) {
        if (ctx.getText().endsWith("*")) {
            return new UsingDeclaration(UsingDeclaration.SUBMODULE, ctx.qualifiedName().getText());
        }
        return new UsingDeclaration(UsingDeclaration.QUALIFIED_NAME, ctx.qualifiedName().getText());
    }
}
