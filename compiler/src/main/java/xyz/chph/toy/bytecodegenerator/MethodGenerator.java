package xyz.chph.toy.bytecodegenerator;

import xyz.chph.toy.bytecodegenerator.statement.StatementGenerator;
import xyz.chph.toy.domain.Constructor;
import xyz.chph.toy.domain.Function;
import xyz.chph.toy.domain.node.expression.EmptyExpression;
import xyz.chph.toy.domain.node.expression.SuperCall;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.domain.node.statement.Block;
import xyz.chph.toy.domain.node.statement.ReturnStatement;
import xyz.chph.toy.domain.node.statement.Statement;
import org.objectweb.asm.ClassWriter;
import xyz.chph.toy.utils.DescriptorFactory;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodGenerator {
    public static final String MAIN_FUN_NAME = "main";
    private ClassWriter classWriter;

    public MethodGenerator(ClassWriter classWriter) {
        this.classWriter = classWriter;
    }

    public void generate(Function function) {
        String name = function.getName();
        boolean isMain = name.equals(MAIN_FUN_NAME);
        String description = DescriptorFactory.getMethodDescriptor(function);
        Block block = (Block) function.getRootStatement();
        Scope scope = block.getScope();
        int access = Opcodes.ACC_PUBLIC + (isMain ? Opcodes.ACC_STATIC : 0);
        MethodVisitor mv = classWriter.visitMethod(access, name, description, null, null);
        mv.visitCode();
        StatementGenerator statementGenerator = new StatementGenerator(mv, scope);
        block.accept(statementGenerator);
        appendReturnIfNotExists(function, block, statementGenerator);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }


    public void generate(Constructor constructor) {
        Block block = (Block) constructor.getRootStatement();
        Scope scope = block.getScope();
        int access = Opcodes.ACC_PUBLIC;
        String description = DescriptorFactory.getMethodDescriptor(constructor);
        MethodVisitor mv = classWriter.visitMethod(access, "<init>", description, null, null);
        mv.visitCode();
        StatementGenerator statementScopeGenerator = new StatementGenerator(mv, scope);
        new SuperCall().accept(statementScopeGenerator);
        block.accept(statementScopeGenerator);
        appendReturnIfNotExists(constructor, block, statementScopeGenerator);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }


    private void appendReturnIfNotExists(Function function, Block block, StatementGenerator statementScopeGenrator) {
        boolean isLastStatementReturn = false;
        if(!block.getStatements().isEmpty()) {
            Statement lastStatement = block.getStatements().get(block.getStatements().size() - 1);
            isLastStatementReturn = lastStatement instanceof ReturnStatement;
        }
        if(!isLastStatementReturn) {
            EmptyExpression emptyExpression = new EmptyExpression(function.getReturnType());
            ReturnStatement returnStatement = new ReturnStatement(emptyExpression);
            returnStatement.accept(statementScopeGenrator);
        }
    }
}
