// Generated from xyz/chph/toy/antlr/Toy.g4 by ANTLR 4.3

package xyz.chph.toy.antlr;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ToyParser}.
 */
public interface ToyListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code UnnamedArgumentsList}
	 * labeled alternative in {@link ToyParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterUnnamedArgumentsList(@NotNull ToyParser.UnnamedArgumentsListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnnamedArgumentsList}
	 * labeled alternative in {@link ToyParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitUnnamedArgumentsList(@NotNull ToyParser.UnnamedArgumentsListContext ctx);

	/**
	 * Enter a parse tree produced by the {@code Add}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdd(@NotNull ToyParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdd(@NotNull ToyParser.AddContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ReturnVoid}
	 * labeled alternative in {@link ToyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnVoid(@NotNull ToyParser.ReturnVoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnVoid}
	 * labeled alternative in {@link ToyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnVoid(@NotNull ToyParser.ReturnVoidContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(@NotNull ToyParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(@NotNull ToyParser.ArgumentContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#variableReference}.
	 * @param ctx the parse tree
	 */
	void enterVariableReference(@NotNull ToyParser.VariableReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#variableReference}.
	 * @param ctx the parse tree
	 */
	void exitVariableReference(@NotNull ToyParser.VariableReferenceContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#forConditions}.
	 * @param ctx the parse tree
	 */
	void enterForConditions(@NotNull ToyParser.ForConditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#forConditions}.
	 * @param ctx the parse tree
	 */
	void exitForConditions(@NotNull ToyParser.ForConditionsContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#className}.
	 * @param ctx the parse tree
	 */
	void enterClassName(@NotNull ToyParser.ClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#className}.
	 * @param ctx the parse tree
	 */
	void exitClassName(@NotNull ToyParser.ClassNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull ToyParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull ToyParser.TypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(@NotNull ToyParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(@NotNull ToyParser.ClassDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by the {@code Divide}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDivide(@NotNull ToyParser.DivideContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Divide}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDivide(@NotNull ToyParser.DivideContext ctx);

	/**
	 * Enter a parse tree produced by the {@code VarReference}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVarReference(@NotNull ToyParser.VarReferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarReference}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVarReference(@NotNull ToyParser.VarReferenceContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ReturnWithValue}
	 * labeled alternative in {@link ToyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnWithValue(@NotNull ToyParser.ReturnWithValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnWithValue}
	 * labeled alternative in {@link ToyParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnWithValue(@NotNull ToyParser.ReturnWithValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(@NotNull ToyParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(@NotNull ToyParser.FunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(@NotNull ToyParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(@NotNull ToyParser.ParameterContext ctx);

	/**
	 * Enter a parse tree produced by the {@code Multiply}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiply(@NotNull ToyParser.MultiplyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Multiply}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiply(@NotNull ToyParser.MultiplyContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull ToyParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull ToyParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by the {@code Supercall}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSupercall(@NotNull ToyParser.SupercallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Supercall}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSupercall(@NotNull ToyParser.SupercallContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(@NotNull ToyParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(@NotNull ToyParser.ClassBodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull ToyParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull ToyParser.BlockContext ctx);

	/**
	 * Enter a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(@NotNull ToyParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(@NotNull ToyParser.FunctionCallContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ConstructorCall}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstructorCall(@NotNull ToyParser.ConstructorCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstructorCall}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstructorCall(@NotNull ToyParser.ConstructorCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(@NotNull ToyParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(@NotNull ToyParser.PrimitiveTypeContext ctx);

	/**
	 * Enter a parse tree produced by the {@code NamedArgumentsList}
	 * labeled alternative in {@link ToyParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterNamedArgumentsList(@NotNull ToyParser.NamedArgumentsListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NamedArgumentsList}
	 * labeled alternative in {@link ToyParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitNamedArgumentsList(@NotNull ToyParser.NamedArgumentsListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#namedArgument}.
	 * @param ctx the parse tree
	 */
	void enterNamedArgument(@NotNull ToyParser.NamedArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#namedArgument}.
	 * @param ctx the parse tree
	 */
	void exitNamedArgument(@NotNull ToyParser.NamedArgumentContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull ToyParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull ToyParser.ValueContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ValueExpr}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterValueExpr(@NotNull ToyParser.ValueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValueExpr}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitValueExpr(@NotNull ToyParser.ValueExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(@NotNull ToyParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(@NotNull ToyParser.FunctionNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(@NotNull ToyParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(@NotNull ToyParser.AssignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(@NotNull ToyParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(@NotNull ToyParser.QualifiedNameContext ctx);

	/**
	 * Enter a parse tree produced by the {@code ConditionalExpression}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(@NotNull ToyParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionalExpression}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(@NotNull ToyParser.ConditionalExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(@NotNull ToyParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(@NotNull ToyParser.ForStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(@NotNull ToyParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(@NotNull ToyParser.IfStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(@NotNull ToyParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(@NotNull ToyParser.VariableDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by the {@code Substract}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubstract(@NotNull ToyParser.SubstractContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Substract}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubstract(@NotNull ToyParser.SubstractContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(@NotNull ToyParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(@NotNull ToyParser.PrintStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(@NotNull ToyParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(@NotNull ToyParser.CompilationUnitContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(@NotNull ToyParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(@NotNull ToyParser.FieldContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#parameterWithDefaultValue}.
	 * @param ctx the parse tree
	 */
	void enterParameterWithDefaultValue(@NotNull ToyParser.ParameterWithDefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#parameterWithDefaultValue}.
	 * @param ctx the parse tree
	 */
	void exitParameterWithDefaultValue(@NotNull ToyParser.ParameterWithDefaultValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(@NotNull ToyParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(@NotNull ToyParser.NameContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#parametersList}.
	 * @param ctx the parse tree
	 */
	void enterParametersList(@NotNull ToyParser.ParametersListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#parametersList}.
	 * @param ctx the parse tree
	 */
	void exitParametersList(@NotNull ToyParser.ParametersListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(@NotNull ToyParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(@NotNull ToyParser.FunctionDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link ToyParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassType(@NotNull ToyParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToyParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassType(@NotNull ToyParser.ClassTypeContext ctx);
}