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
        // todo 这里除了String或string、内置类型，都是没有全限定名的类型名，因此也需要回填
        Type owner = scope.getClassType();
        Type type = TypeResolver.getFromTypeContext(ctx.type());
        String name = ctx.name().getText();
        // todo 因此，类定义中的所有域Field的type和owner均需要回填
        return new Field(name, owner, type);
    }
}
