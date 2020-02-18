package xyz.chph.toy.parsing.visitor;

import xyz.chph.toy.antlr.ToyBaseVisitor;
import xyz.chph.toy.antlr.ToyParser;
import xyz.chph.toy.domain.ClassDeclaration;
import xyz.chph.toy.domain.Constructor;
import xyz.chph.toy.domain.Function;
import xyz.chph.toy.domain.MetaData;
import xyz.chph.toy.domain.node.expression.ConstructorCall;
import xyz.chph.toy.domain.node.expression.FunctionCall;
import xyz.chph.toy.domain.node.expression.Parameter;
import xyz.chph.toy.domain.node.statement.Block;
import xyz.chph.toy.domain.scope.Field;
import xyz.chph.toy.domain.scope.FunctionSignature;
import xyz.chph.toy.domain.scope.Namespace;
import xyz.chph.toy.domain.scope.Scope;
import xyz.chph.toy.domain.type.BuiltInType;
import xyz.chph.toy.domain.type.ClassType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ClassVisitor extends ToyBaseVisitor<ClassDeclaration> {
    private final String module;
    private Scope scope;

    public ClassVisitor(String module, Namespace namespace) {
        // todo important!!!
        ClassType.setNamespace(namespace);
        if (!module.isEmpty()) this.module = module + ".";
        else this.module = module;
    }

    @Override
    public ClassDeclaration visitClassDeclaration(ToyParser.ClassDeclarationContext ctx) {
        String name = module + ctx.className().getText();
        MetaData metaData = new MetaData(name, "java.lang.Object");
        scope = new Scope(metaData);
        FieldVisitor fieldVisitor = new FieldVisitor(scope);
        FunctionSignatureVisitor functionSignatureVisitor = new FunctionSignatureVisitor(scope);
        List<ToyParser.FunctionContext> methodsCtx = ctx.classBody().function();
        List<Field> fields = ctx.classBody().field().stream()
                .map(field -> field.accept(fieldVisitor))
                .peek(scope::addField)
                .collect(toList());
        methodsCtx.stream()
                .map(method -> method.functionDeclaration().accept(functionSignatureVisitor))
                .forEach(scope::addSignature);
        boolean defaultConstructorExists = scope.isParameterLessSignatureExists(name);
        addDefaultConstructorSignatureToScope(name, defaultConstructorExists);
        List<Function> methods = methodsCtx.stream()
                .map(method -> method.accept(new FunctionVisitor(scope)))
                .collect(toList());
        if (!defaultConstructorExists) {
            methods.add(getDefaultConstructor());
        }
        boolean startMethodDefined = scope.isParameterLessSignatureExists("start");
        if (startMethodDefined) {
            methods.add(getGeneratedMainMethod());
        }

        return new ClassDeclaration(name, fields, methods);
    }

    private void addDefaultConstructorSignatureToScope(String name, boolean defaultConstructorExists) {
        if (!defaultConstructorExists) {
            FunctionSignature constructorSignature = new FunctionSignature(name, Collections.emptyList(), BuiltInType.VOID);
            scope.addSignature(constructorSignature);
        }
    }

    private Constructor getDefaultConstructor() {
        FunctionSignature signature = scope.getMethodCallSignatureWithoutParameters(scope.getClassName());
        Constructor constructor = new Constructor(signature, Block.empty(scope));
        return constructor;
    }

    private Function getGeneratedMainMethod() {
        Parameter args = new Parameter("args", BuiltInType.STRING_ARR, Optional.empty());
        FunctionSignature functionSignature = new FunctionSignature("main", Collections.singletonList(args), BuiltInType.VOID);
        ConstructorCall constructorCall = new ConstructorCall(scope.getClassName());
        FunctionSignature startFunSignature = new FunctionSignature("start", Collections.emptyList(), BuiltInType.VOID);
        FunctionCall startFunctionCall = new FunctionCall(startFunSignature, Collections.emptyList(), scope.getClassType());
        Block block = new Block(new Scope(scope), Arrays.asList(constructorCall, startFunctionCall));
        return new Function(functionSignature, block);
    }
}
