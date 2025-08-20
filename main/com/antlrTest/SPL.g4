grammar SPL;

query: searchCmd? commandExpr*;

searchCmd: 'search' | 'SEARCH';

commandExpr: termExpr (pipeOp? termExpr)*;

termExpr: WORD+ (EQ WORD+)* | STRING;

pipeOp: '|' ;

WORD: [a-zA-Z0-9_]+ ;
STRING: '"' .*? '"' ;
EQ: '=' ;
WS: [ \t\r\n]+ -> skip;