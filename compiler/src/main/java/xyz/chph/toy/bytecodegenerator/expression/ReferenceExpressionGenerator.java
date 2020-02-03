package xyz.chph.toy.bytecodegenerator.expression;

import xyz.chph.toy.domain.node.expression.FieldReference;
import xyz.chph.toy.domain.node.expression.LocalVariableReference;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.domain.type.Type;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ReferenceExpressionGenerator {
    private final MethodVisitor methodVisitor;
    private final Scope scope;
    public ReferenceExpressionGenerator(MethodVisitor methodVisitor, Scope scope) {
        this.methodVisitor = methodVisitor;
        this.scope = scope;
    }
    public void generate(LocalVariableReference localVariableReference) {
        String varName = localVariableReference.geName();
        int index = scope.getLocalVariableIndex(varName);
        Type type = localVariableReference.getType();
        methodVisitor.visitVarInsn(type.getLoadVariableOpcode(), index);
    }
    public void generate(FieldReference fieldReference) {
        String varName = fieldReference.geName();
        Type type = fieldReference.getType();
        String ownerInternalName = fieldReference.getOwnerInternalName();
        String descriptor = type.getDescriptor();
        methodVisitor.visitVarInsn(Opcodes.ALOAD,0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, ownerInternalName,varName,descriptor);
    }
}
