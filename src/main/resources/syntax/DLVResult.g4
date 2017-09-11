/**
* LPMLN 词法语法定义
* 王彬
* 2016-08-30
**/
grammar DLVResult;

/**
* 词法定义
**/

//字符串
STRING : '"' ('\\"'|~('"'))* '"';
//规则终结符
FULLSTOP : '.';
//正整数
POSITIVE_INT : [1-9][0-9]*;

//0
ZERO : '0';
//常量
CONSTANT : [a-z][a-zA-Z0-9_]*;

WEIGHT_FLAG : 'Cost';
WEIGHT : 'Weight';
LEVEL : 'Level';

//左圆括号
LPAREN : '(';
//右圆括号
RPAREN : ')';
//左方括号
LSBRACK : '[';
//右方括号
RSBRACK : ']';
//左花括号
LCBRACK : '{';
//右花括号
RCBRACK : '}';

LESS_THAN : '<';

MINUS : '-';

GREATER_THAN : '>';

//逗号
COMMA : ',';
//条件限制符
CONDITION : ':';

//空白字符或换行符
WS : ( ' ' | '\t' | '\n' | '\r')+ -> skip  ;
/**
* 语法规则定义
**/
//负整数
negative_int :   MINUS POSITIVE_INT ;
//整数
integer : POSITIVE_INT | negative_int | ZERO;
//自然数
natural_number : POSITIVE_INT | ZERO;
//四则运算符

//函数
function : CONSTANT LPAREN term (COMMA term)* RPAREN;

//项
term : CONSTANT | function | STRING | integer;

//原子
atom :
    CONSTANT |
    CONSTANT LPAREN term (COMMA term)* RPAREN;

//文字
literal : atom | MINUS atom;

answer_set : LCBRACK (literal (COMMA literal)*)? RCBRACK;

weight_level_flag : LSBRACK  WEIGHT CONDITION LEVEL  RSBRACK;

weight_level : LSBRACK natural_number  CONDITION natural_number RSBRACK;

weight : WEIGHT_FLAG LPAREN weight_level_flag RPAREN CONDITION
         LESS_THAN weight_level (COMMA weight_level)* GREATER_THAN ;

weighted_answer_set : answer_set weight;

possible_worlds : weighted_answer_set*;

