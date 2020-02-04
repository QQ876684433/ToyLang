package xyz.chph.toy.parsing.visitor;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.scope.Field;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.domain.type.Type;
import xyz.chph.toy.utils.TypeResolver;

public class FieldVisitor extends ToyBaseVisitor<Field> {
    private final Scope scope;

    public FieldVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Field visitField(@NotNull ToyParser.FieldContext ctx) {
        Type owner = scope.getClassType();
        String qualifiedName = scope.getQualifiedNameFromTypeName(ctx.type().getText());
        Type type = TypeResolver.getFromTypeName(qualifiedName);
        String name = ctx.name().getText();
        return new Field(name, owner, type);
    }
}
