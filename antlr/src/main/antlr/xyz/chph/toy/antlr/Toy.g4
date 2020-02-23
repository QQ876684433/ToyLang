//header
grammar Toy;

@header {
package xyz.chph.toy.antlr;
}

//RULES
compilationUnit
    :   importDeclaration* classDeclaration
        EOF
    ;

importDeclaration
    :   'import' importList 'from' qualifiedName
    ;

importList
    :   importReference (',' importReference)*
    |   '{' importReference (',' importReference)* '}'
    ;

importReference
    :   ID ('as' ID)?
    ;

classDeclaration
    :   className '{' classBody '}'
    ;

className : qualifiedName ;
classBody :  field* function* ;
field : type name;
function : functionDeclaration block;
functionDeclaration : (type)? functionName '('? parametersList? ')'? ;
parametersList:  parameter (',' parameter)*
          |  parameter (',' parameterWithDefaultValue)*
          |  parameterWithDefaultValue (',' parameterWithDefaultValue)* ;
functionName : ID ;
parameter : type ID ;
parameterWithDefaultValue : type ID '=' defaultValue=expression ;
type : primitiveType
     | classType ;

primitiveType
    :   'boolean' ('[' ']')*
    |   'string' ('[' ']')*
    |   'char' ('[' ']')*
    |   'byte' ('[' ']')*
    |   'short' ('[' ']')*
    |   'int' ('[' ']')*
    |   'long' ('[' ']')*
    |   'float' ('[' ']')*
    |   'double' ('[' ']')*
    |   'void' ('[' ']')*
    ;
classType : qualifiedName ('[' ']')* ;

block : '{' statement* '}' ;

statement :     block
               | variableDeclaration
               | assignment
               | printStatement
               | forStatement
               | returnStatement
               | ifStatement
               | expression ;

variableDeclaration : VARIABLE name EQUALS expression ;
assignment : name EQUALS expression;
printStatement
    :   PRINT expression
    |   PRINTLN expression
    ;
returnStatement : 'return' expression #ReturnWithValue
                | 'return' #ReturnVoid ;

ifStatement: 'if'  ('(')? expression (')')? trueStatement=statement ('else' falseStatement=statement)?;

forStatement : 'for' ('(')? forConditions (')')? statement ;
forConditions : iterator=variableReference  'from' startExpr=expression range='to' endExpr=expression ;

name : ID ;
argumentList : argument? (',' a=argument)* #UnnamedArgumentsList
             | namedArgument? (',' namedArgument)* #NamedArgumentsList ;
argument : expression ;
namedArgument : name '->' expression ;

expression : variableReference #VarReference
           | owner=expression '.' functionName '(' argumentList ')' #FunctionCall
           | functionName '(' argumentList ')' #FunctionCall
           | superCall='super' '('argumentList ')' #Supercall
           | newCall='new' className '('argumentList ')' #ConstructorCall
           | literal        #ValueExpr
           |  '('expression '*' expression')' #Multiply
           | expression '*' expression  #Multiply
           | '(' expression '/' expression ')' #Divide
           | expression '/' expression #Divide
           | '(' expression '+' expression ')' #Add
           | expression '+' expression #Add
           | '(' expression '-' expression ')' #Substract
           | expression '-' expression #Substract
           | expression cmp='>' expression #ConditionalExpression
           | expression cmp='<' expression #ConditionalExpression
           | expression cmp='==' expression #ConditionalExpression
           | expression cmp='!=' expression #ConditionalExpression
           | expression cmp='>=' expression #ConditionalExpression
           | expression cmp='<=' expression #ConditionalExpression
           ;

variableReference : ID ;

literal
    :   integerLiteral
    |   FloatingPointLiteral
    |   BOOL
    |   StringLiteral
    ;

qualifiedName : ID ('.' ID)*;

integerLiteral
    :   HexLiteral
    |   OctalLiteral
    |   DecimalLiteral
    ;

//////////////////////////////////////////////////////////////////////////////////////
//                                      LEXER
//////////////////////////////////////////////////////////////////////////////////////

FloatingPointLiteral
    :   ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
    |   '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
    |   ('0'..'9')+ Exponent FloatTypeSuffix?
    |   ('0'..'9')+ FloatTypeSuffix
    |   ('0x' | '0X') (HexDigit )*
        ('.' (HexDigit)*)?
        ( 'p' | 'P' )
        ( '+' | '-' )?
        ( '0' .. '9' )+
        FloatTypeSuffix?
    ;

fragment
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

HexLiteral : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;

DecimalLiteral : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

OctalLiteral : '0' ('0'..'7')+ IntegerTypeSuffix? ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
IntegerTypeSuffix : ('l'|'L') ;

StringLiteral
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UnicodeEscape
    |   OctalEscape
    ;

fragment
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

VARIABLE : 'var' ;
PRINT : 'print' ;
PRINTLN : 'println';
EQUALS : '=' ;
BOOL : 'true' | 'false' ;
STRING : '"'~('\r' | '\n' | '"')*'"' ;
ID : [a-zA-Z0-9]+ ;
COMMENT
    :   '/*' .*? '*/'    -> channel(HIDDEN) // match anything between /* and */
    ;
WS  :   [ \r\t\u000C\n]+ -> channel(HIDDEN)
    ;

LINE_COMMENT
    : '//' ~[\r\n]* '\r'? '\n' -> channel(HIDDEN)
    ;