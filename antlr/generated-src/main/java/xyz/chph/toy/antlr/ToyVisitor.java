// Generated from xyz/chph/toy/antlr/Toy.g4 by ANTLR 4.3

package xyz.chph.toy.antlr;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ToyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ToyVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code UnnamedArgumentsList}
	 * labeled alternative in {@link ToyParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnnamedArgumentsList(@NotNull ToyParser.UnnamedArgumentsListContext ctx);

	/**
	 * Visit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(@NotNull ToyParser.AddContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ReturnVoid}
	 * labeled alternative in {@link ToyParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnVoid(@NotNull ToyParser.ReturnVoidContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(@NotNull ToyParser.ArgumentContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#variableReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableReference(@NotNull ToyParser.VariableReferenceContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#forConditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForConditions(@NotNull ToyParser.ForConditionsContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#className}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassName(@NotNull ToyParser.ClassNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull ToyParser.TypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(@NotNull ToyParser.ClassDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by the {@code Divide}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivide(@NotNull ToyParser.DivideContext ctx);

	/**
	 * Visit a parse tree produced by the {@code VarReference}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarReference(@NotNull ToyParser.VarReferenceContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ReturnWithValue}
	 * labeled alternative in {@link ToyParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnWithValue(@NotNull ToyParser.ReturnWithValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(@NotNull ToyParser.FunctionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(@NotNull ToyParser.ParameterContext ctx);

	/**
	 * Visit a parse tree produced by the {@code Multiply}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiply(@NotNull ToyParser.MultiplyContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull ToyParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code Supercall}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupercall(@NotNull ToyParser.SupercallContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(@NotNull ToyParser.ClassBodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull ToyParser.BlockContext ctx);

	/**
	 * Visit a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(@NotNull ToyParser.FunctionCallContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ConstructorCall}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorCall(@NotNull ToyParser.ConstructorCallContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(@NotNull ToyParser.PrimitiveTypeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code NamedArgumentsList}
	 * labeled alternative in {@link ToyParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedArgumentsList(@NotNull ToyParser.NamedArgumentsListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#namedArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedArgument(@NotNull ToyParser.NamedArgumentContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull ToyParser.ValueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ValueExpr}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueExpr(@NotNull ToyParser.ValueExprContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(@NotNull ToyParser.FunctionNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull ToyParser.AssignmentContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(@NotNull ToyParser.QualifiedNameContext ctx);

	/**
	 * Visit a parse tree produced by the {@code ConditionalExpression}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(@NotNull ToyParser.ConditionalExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(@NotNull ToyParser.ForStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(@NotNull ToyParser.IfStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(@NotNull ToyParser.VariableDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by the {@code Substract}
	 * labeled alternative in {@link ToyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubstract(@NotNull ToyParser.SubstractContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(@NotNull ToyParser.PrintStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(@NotNull ToyParser.CompilationUnitContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(@NotNull ToyParser.FieldContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#parameterWithDefaultValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterWithDefaultValue(@NotNull ToyParser.ParameterWithDefaultValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(@NotNull ToyParser.NameContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#parametersList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametersList(@NotNull ToyParser.ParametersListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(@NotNull ToyParser.FunctionDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link ToyParser#classType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassType(@NotNull ToyParser.ClassTypeContext ctx);
}