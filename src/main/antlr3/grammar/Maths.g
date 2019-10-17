grammar Maths;

options {
	output=AST;
	backtrack=false;
	language=Java;
}

@lexer::header {
	package ru.amm.maths.grammar;
}

@parser::header {
	package ru.amm.maths.grammar;
}


ADD_OP	:	'+';
MULT_OP	:	'*';

LPAREN	:	'(';
RPAREN	:	')';

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

fragment
DIGIT : ('0'..'9') ;

NUMBER: DIGIT+;

// Start rule
mathExpr	:	expression EOF!
	;


expression
	:	sumExpression
	;

sumExpression
	:	multExpr (ADD_OP^ multExpr)*
	;


multExpr
	: 	baseExpr (MULT_OP^ baseExpr)*
	;


baseExpr
	:	NUMBER
	|

		(
			LPAREN^ expression RPAREN
		)
	;

