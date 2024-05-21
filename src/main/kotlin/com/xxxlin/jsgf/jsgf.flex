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


DOC_VERSION=(v|V)[0-9\.]*
DOC_ENCODE=[a-z_A-Z\-0-9]*
JSGF_LANGUAGE=[a-zA-Z0-9_\.\-]*
GRAMMAR_NAME=[a-z_A-Z\-0-9\.]*

VARIABLE_NAME="<"[a-z_A-Z\-0-9]*">"
VARIABLE_VALUE=[^;\ ]([^;]|\n)*
SLOP_STRING_LITERAL=[^\ \|\;\(\)\[\]\"\<\>\n] [^\|\;\(\)\[\]\"\<\>\n]* [^\ \|\;\(\)\[\]\"\<\>\n]?
STRING=\"[^\r\n\"]*\"
LABEL_STRING=\"[^\r\n\"\ ]*\"



//characters=('([^'\\]|\\.)*'|\"([^\"\\]|\\\"|\\\'|\\)*\")"
characters=[^\r\n]*



CRLF=\R
//comment=("#"|"//")[^\r\n]*
FIRST_VALUE_CHARACTER=[^ \n\f\\] | "\\"{CRLF} | "\\".
VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
SEPARATOR=[=]
KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "
WHITE_SPACE=\s+

exp="(".+")"

LINE_COMMENT=("//").*
BLOCK_COMMENT="/*"([^]|\n)*"*/"
op_eq="="
// 槽位文本
slot_text=[^\r\n\|\"\(\)\<\>\[\]\;]*



%state state_jsgf
%state state_jsgf_encode
%state state_grammar
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
<YYINITIAL> "#JSGF" { yybegin(state_jsgf); return JsgfTypes.KEYWORD; }
<state_jsgf> " " { return TokenType.WHITE_SPACE; }
<state_jsgf> {DOC_VERSION} { return JsgfTypes.DOC_VERSION; }
<state_jsgf> {DOC_ENCODE} { yybegin(state_jsgf_encode); return JsgfTypes.DOC_ENCODE; }
<state_jsgf_encode> {JSGF_LANGUAGE} { return JsgfTypes.JSGF_LANGUAGE; }
<state_jsgf_encode> ";" { yybegin(YYINITIAL); return JsgfTypes.SEMICOLON;}

// grammar sampleTag;
<YYINITIAL> "grammar" { yybegin(state_grammar); return JsgfTypes.KEYWORD; }
<state_grammar> {GRAMMAR_NAME} { return JsgfTypes.GRAMMAR_NAME; }
<state_grammar> ";" { yybegin(YYINITIAL); return JsgfTypes.SEMICOLON;}


// 空行
{CRLF}({CRLF})+ { return TokenType.WHITE_SPACE; }
{WHITE_SPACE} { return TokenType.WHITE_SPACE; }

// 行注释
{LINE_COMMENT} { return JsgfTypes.LINE_COMMENT; }
// 多行注释
{BLOCK_COMMENT} { return JsgfTypes.BLOCK_COMMENT; }


// 表达式
<YYINITIAL> "public" { yybegin(state_exp); return JsgfTypes.KEYWORD; }
<YYINITIAL, state_exp> {VARIABLE_NAME} { yybegin(state_exp); return JsgfTypes.VARIABLE_NAME; }
<state_exp> "=" { yybegin(state_exp_value); return JsgfTypes.OP_EQ; }
<state_exp_value> "|" { return JsgfTypes.OP_OR; }
<state_exp_value> "(" { return JsgfTypes.LEFT_PAREN; }
<state_exp_value> ")" { return JsgfTypes.RIGHT_PAREN; }
<state_exp_value> "[" { return JsgfTypes.LEFT_BRACKET; }
<state_exp_value> "]" { return JsgfTypes.RIGHT_BRACKET; }
<state_exp_value> {SLOP_STRING_LITERAL} { return JsgfTypes.SLOP_STRING_LITERAL; }
<state_exp_value> {LABEL_STRING} { return JsgfTypes.LABEL_STRING; }
<state_exp_value> {VARIABLE_NAME} { return JsgfTypes.VARIABLE_NAME; }
<state_exp_value> ";" { yybegin(YYINITIAL); return JsgfTypes.SEMICOLON; }


// 字符串
{STRING} { return JsgfTypes.STRING; }
"=" { return JsgfTypes.OP_EQ; }
[^] { return TokenType.BAD_CHARACTER; }