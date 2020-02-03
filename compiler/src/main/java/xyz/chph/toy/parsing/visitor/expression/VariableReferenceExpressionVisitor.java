package xyz.chph.toy.parsing.visitor.expression;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.expression.FieldReference;
import xyz.chph.toy.domain.node.expression.LocalVariableReference;
import xyz.chph.toy.domain.node.expression.Reference;
import xyz.chph.toy.domain.scope.Field;
import xyz.chph.toy.domain.scope.LocalVariable;
import xyz.chph.toy.domain.scope.Scope;

public class VariableReferenceExpressionVisitor extends ToyBaseVisitor<Reference> {
    private final Scope scope;
    public VariableReferenceExpressionVisitor(Scope scope) {
        this.scope = scope;
    }
    @Override
    public Reference visitVarReference(@NotNull ToyParser.VarReferenceContext ctx) {
        String varName = ctx.getText();
        if(scope.isFieldExists(varName)) {
            Field field = scope.getField(varName);
            return new FieldReference(field);
        }
        LocalVariable variable = scope.getLocalVariable(varName);
        return new LocalVariableReference(variable);
    }
}

