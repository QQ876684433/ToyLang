package xyz.chph.toy.domain.scope;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import xyz.chph.toy.domain.node.expression.Parameter;
import xyz.chph.toy.domain.type.BuiltInType;
import xyz.chph.toy.domain.type.Type;
import xyz.chph.toy.utils.ReflectionObjectToSignatureMapper;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClassPathScope {

    private Scope scope;

    public ClassPathScope(Scope scope) {
        this.scope = scope;
    }

    public Optional<FunctionSignature> getMethodSignature(Type owner, String methodName, List<Type> arguments) {
        System.out.println("owner: " + owner.getName());
        System.out.println("methodName: " + methodName);
        try {
            Class<?> methodOwnerClass = owner.getTypeClass();
            Class<?>[] params = arguments.stream()
                    .map(Type::getTypeClass).toArray(Class<?>[]::new);
            Method method = MethodUtils.getMatchingAccessibleMethod(methodOwnerClass, methodName, params);
            return Optional.of(ReflectionObjectToSignatureMapper.fromMethod(method));
        } catch (Exception e) {
            return getFunctionSignature(owner, methodName, arguments);
        }
    }

    // get function signature from namespace
    private Optional<FunctionSignature> getFunctionSignature(Type owner, String methodName, List<Type> arguments) {
        class Visitor extends ClassVisitor {

            public Visitor() {
                super(Opcodes.ASM4);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                System.out.println(name + " " + desc + "" + signature);
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }
        Visitor visitor = new Visitor();
        ClassReader classReader = new ClassReader();

        return Optional.of(
                new FunctionSignature(owner.getName() + "." + methodName,
                        arguments.stream()
                                .map(type -> new Parameter(type.getName(), type, Optional.empty()))
                                .collect(Collectors.toList()),
                        null
                )
        );
    }

    public Optional<FunctionSignature> getConstructorSignature(String className, List<Type> arguments) {
        try {
            Class<?> methodOwnerClass = Class.forName(className);
            Class<?>[] params = arguments.stream()
                    .map(Type::getTypeClass).toArray(Class<?>[]::new);
            Constructor<?> constructor = ConstructorUtils.getMatchingAccessibleConstructor(methodOwnerClass, params);
            return Optional.of(ReflectionObjectToSignatureMapper.fromConstructor(constructor));
        } catch (Exception e) {
            return Optional.of(
                    new FunctionSignature(className,
                            arguments.stream()
                                    .map(type -> new Parameter(type.getName(), type, Optional.empty()))
                                    .collect(Collectors.toList()),
                            BuiltInType.VOID)
            );
        }
    }
}
