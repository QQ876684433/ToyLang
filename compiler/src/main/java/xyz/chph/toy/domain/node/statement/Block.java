package xyz.chph.toy.domain.node.statement;

import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.scope.Scope;

import java.util.Collections;
import java.util.List;

public class Block implements Statement {
    private List<Statement> statements;
    private Scope scope;

    public Block( Scope scope, List<Statement> statements) {
        this.statements = statements;
        this.scope = scope;
    }

    public static Block empty(Scope scope) {
        return new Block(scope, Collections.emptyList());
    }

    @Override
    public void accept(StatementGenerator generator) {
        generator.generate(this);
    }

    public Scope getScope() {
        return scope;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
