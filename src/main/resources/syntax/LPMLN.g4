/**
* LPMLN 词法语法定义
* 王彬
* 2016-08-30
**/
grammar LPMLN;

/**
* 词法定义
**/

//缺省否定关键字
NAF_NOT : 'not';
//字符串
STRING : '"' ('\\"'|~('"'))* '"';
//规则终结符
FULLSTOP : '.';
//正整数
POSITIVE_INT : [1-9][0-9]*;
//小数(点表示法)
DECIMAL : MINUS? (POSITIVE_INT* | ZERO ) FULLSTOP ZERO* [1-9]*;

//0
ZERO : '0';
//常量
CONSTANT : [a-z][a-zA-Z0-9_]*;
//变量
VAR : [A-Z][a-zA-Z0-9_]*;

//加号
PLUS : '+';
//减号、经典否定关键字
MINUS : '-';
//乘号、 星号
STAR : '*';
//除号、斜线
SLASH : '/';

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
//范围运算
RANGE : '..';

//逗号
COMMA : ',';
//认知析取符
DISJUNCTION : '|';
//条件限制符
CONDITION : ':';
//推导符号
ASSIGN : ':-';
//弱约束推导符号
WEAK_ASSIGN : ':~';

//关系运算符
LESS_THAN : '<';
LEQ : '<=';
GREATER_THAN : '>';
GEQ : '>=';
EQUAL : '=';
DOUBLE_EQUAL : '==';
NEQ : '!=';

AGGREGATE_OP : '#count' | '#sum' | '#max' | '#min';
//
META_OP : '#show';
//单行注释
LINE_COMMENT : ('%' ~('\r' | '\n')* '\r'? '\n') -> skip;
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
arithmetic_op : PLUS | MINUS | STAR | SLASH;
//关系运算符
relation_op : LESS_THAN | LEQ | GREATER_THAN | GEQ | EQUAL | DOUBLE_EQUAL | NEQ;

//简单四则运算算术表达式，不加括号
simple_arithmetic_expr :
    integer |
    (MINUS)? VAR |
    (integer | (MINUS)? VAR) (arithmetic_op (natural_number | VAR))* ;

//四则运算算术表达式，加括号
simple_arithmetic_expr2 :
     simple_arithmetic_expr |
    (LPAREN simple_arithmetic_expr2  RPAREN) |
     simple_arithmetic_expr2 arithmetic_op simple_arithmetic_expr2;

//四则运算表达式
arithmethic_expr:
    simple_arithmetic_expr2 |
    MINUS LPAREN simple_arithmetic_expr2 RPAREN;


//函数
function : CONSTANT LPAREN term (COMMA term)* RPAREN;

//项
term : VAR | CONSTANT | arithmethic_expr | function | STRING;

//原子
atom :
    CONSTANT |
    CONSTANT LPAREN term (COMMA term)* RPAREN;

//范围整数枚举原子
range_atom : CONSTANT LPAREN integer RANGE integer RPAREN;


//文字
literal : atom | MINUS atom;

//缺省文字
default_literal : NAF_NOT literal;

//扩展文字，包含查询原子
extended_literal : literal | default_literal;

//聚合运算
aggregate_atom: AGGREGATE_OP LCBRACK (literal | VAR) CONDITION literal  RCBRACK ;
//聚合运算表达式
aggregate_expr: VAR relation_op aggregate_atom ;

//关系运算表达式
relation_expr :
    (MINUS)? VAR relation_op (MINUS)?  VAR |
    VAR relation_op STRING |
    ((MINUS)? VAR  | arithmethic_expr) relation_op ((MINUS)? VAR | arithmethic_expr);

//规则头部
head : literal (DISJUNCTION literal)*;

//规则体部
body : (extended_literal | relation_expr | aggregate_expr) (COMMA (extended_literal | relation_expr | aggregate_expr))*;

//事实
fact : (head | range_atom) FULLSTOP;

//约束
constraint : ASSIGN body FULLSTOP;

//基本规则
full_rule : head ASSIGN body FULLSTOP;

//ASP 规则 （hard rule）
hard_rule :  fact | constraint | full_rule;

//弱规则 (soft rule)
soft_rule : (DECIMAL | integer ) CONDITION hard_rule;

//
meta_rule : META_OP (MINUS)? CONSTANT SLASH natural_number FULLSTOP;

//ASP4QA程序
lpmln_rule : (hard_rule | soft_rule | meta_rule)*;