package xyz.chph.toy.bytecodegenerator.statement;

import xyz.chph.toy.domain.node.statement.Block;
import xyz.chph.toy.domain.node.statement.Statement;
import xyz.chph.toy.domain.scope.Scope;
import org.objectweb.asm.MethodVisitor;

import java.util.List;

public class BlockStatementGenerator {
    private final MethodVisitor methodVisitor;
    public BlockStatementGenerator(MethodVisitor methodVisitor) {
        this.methodVisitor = methodVisitor;
    }
    public void generate(Block block) {
        Scope newScope = block.getScope();
        List<Statement> statements = block.getStatements();
        StatementGenerator statementGenerator = new StatementGenerator(methodVisitor, newScope);
        statements.stream().forEach(stmt -> stmt.accept(statementGenerator));
    }
}
