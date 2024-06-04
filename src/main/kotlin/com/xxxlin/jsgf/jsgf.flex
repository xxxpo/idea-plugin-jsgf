// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.xxxlin.jsgf;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.xxxlin.jsgf.psi.JsgfTypes;
import com.intellij.psi.TokenType;

%%

%class JsgfLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}


VERSION=(V)[0-9][0-9\.]*[0-9]
CHAR_ENCODING=[a-z_A-Z\-0-9]*
LOCALE=[a-zA-Z0-9_\.\-]*

// 其实是java包名
GRAMMAR_NAME=[a-zA-Z_]+(\.[a-zA-Z0-9_$]+)*
IMPORT_PACKAGE_NAME=[a-zA-Z_]+(\.([a-zA-Z0-9_$]+|\*))*

RULE_NAME="<"[^\ <>]+">"
VARIABLE_VALUE=[^;\ ]([^;]|\n)*
WEIGHTS="/"" "*(0|(0\.[0-9]+)|([1-9]+("."[0-9]+)?))" "*"/"

SLOP_STRING_LITERAL=[^\ \|\;\(\)\[\]\"\<\>\n\+\*/\\{}=] [^\|\;\(\)\[\]\"\<\>\n\+\*/\\{}=]* [^\ \|\;\(\)\[\]\"\<\>\n\+\*/\\{}=]?
STRING=\"[^\r\n\"]*\"

CRLF=\R
WHITE_SPACE=\s+
LINE_COMMENT=("//").*
// ([^]* "*/" [^]*)匹配任何包含*/的序列，!([^]* "*/" [^]*)匹配除包含*/的序列之外的任何内容
BLOCK_COMMENT="/*" !([^]* "*/" [^]*) ("*/")?



%state state_jsgf
%state state_jsgf_encode
%state state_grammar
%state state_import

%state state_exp
%state state_exp_value
%state state_exp_value_start

%state WAITING_VALUE
%state state_gramar_tag_name
%state state_left
%state state_right
%state state_tag
%state state_string


%%

// #JSGF V1.0 utf-8 en;
<YYINITIAL> "#JSGF " { yybegin(state_jsgf); yypushback(1); return JsgfTypes.KEYWORD; }
<state_jsgf> " " { return TokenType.WHITE_SPACE; }
<state_jsgf> {VERSION} { return JsgfTypes.VERSION; }
<state_jsgf> {CHAR_ENCODING} { yybegin(state_jsgf_encode); return JsgfTypes.CHAR_ENCODING; }
<state_jsgf_encode> {LOCALE} { return JsgfTypes.LOCALE; }
<state_jsgf_encode> ";" { yybegin(YYINITIAL); return JsgfTypes.SEMICOLON;}

// grammar sampleTag;
<YYINITIAL> "grammar " { yybegin(state_grammar); yypushback(1); return JsgfTypes.KEYWORD; }
<state_grammar> {GRAMMAR_NAME} { return JsgfTypes.GRAMMAR_NAME; }
<state_grammar> ";" { yybegin(YYINITIAL); return JsgfTypes.SEMICOLON;}

// import <fullyQualifiedRuleName>;
// import <fullGrammarName.*>;
<YYINITIAL> "import " { yybegin(state_import); yypushback(1); return JsgfTypes.KEYWORD; }
<state_import> "<"{IMPORT_PACKAGE_NAME}">" { return JsgfTypes.IMPORT_PACKAGE_NAME;}
<state_import> ";" { yybegin(YYINITIAL); return JsgfTypes.SEMICOLON;}

// 空行
{CRLF}({CRLF})+ { return TokenType.WHITE_SPACE; }
{WHITE_SPACE} { return TokenType.WHITE_SPACE; }

// 行注释
{LINE_COMMENT} { return JsgfTypes.LINE_COMMENT; }
// 多行注释
{BLOCK_COMMENT} { return JsgfTypes.BLOCK_COMMENT; }


// 表达式
<YYINITIAL> "public" { yybegin(state_exp); return JsgfTypes.KEYWORD; }
<YYINITIAL, state_exp> {RULE_NAME} { yybegin(state_exp); return JsgfTypes.DEF_RULE_NAME; }
<state_exp> "=" { yybegin(state_exp_value); return JsgfTypes.OP_EQ; }
<state_exp_value> "|" { return JsgfTypes.OP_OR; }
<state_exp_value> "(" { return JsgfTypes.LEFT_PAREN; }
<state_exp_value> ")" { return JsgfTypes.RIGHT_PAREN; }
<state_exp_value> "[" { return JsgfTypes.LEFT_BRACKET; }
<state_exp_value> "]" { return JsgfTypes.RIGHT_BRACKET; }
<state_exp_value> "+" { return JsgfTypes.OP_ONEMORE; }
<state_exp_value> "*" { return JsgfTypes.OP_ZEROMORE; }
<state_exp_value> {SLOP_STRING_LITERAL} { return JsgfTypes.SLOP_STRING_LITERAL; }
<state_exp_value> {STRING} { return JsgfTypes.LABEL_STRING; }
<state_exp_value> {RULE_NAME} { return JsgfTypes.RULE_NAME; }
<state_exp_value> {WEIGHTS} { return JsgfTypes.WEIGHTS; }
<state_exp_value> ";" { yybegin(YYINITIAL); return JsgfTypes.SEMICOLON; }


// 字符串
{STRING} { return JsgfTypes.STRING; }
"=" { return JsgfTypes.OP_EQ; }
[^] { return TokenType.BAD_CHARACTER; }