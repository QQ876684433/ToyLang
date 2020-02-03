package xyz.chph.toy.parsing.visitor.statement;

import org.antlr.v4.runtime.misc.NotNull;
import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.node.statement.Block;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.domain.scope.Scope;

import java.util.List;
import java.util.stream.Collectors;

public class BlockStatementVisitor extends ToyBaseVisitor<Block> {
    private final Scope scope;
    public BlockStatementVisitor(Scope scope) {
        this.scope = scope;
    }
    @Override
    public Block visitBlock(@NotNull ToyParser.BlockContext ctx) {
        List<ToyParser.StatementContext> blockstatementsCtx = ctx.statement();
        Scope newScope = new Scope(scope);
        StatementVisitor statementVisitor = new StatementVisitor(newScope);
        List<Statement> statements = blockstatementsCtx.stream().map(smtt -> smtt.accept(statementVisitor)).collect(Collectors.toList());
        return new Block(newScope, statements);
    }
}
