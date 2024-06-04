package com.xxxlin.jsgf

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.xxxlin.jsgf.psi.JsgfTypes

/**
 * 语法高亮
 */
object JsgfSyntaxHighlighter : SyntaxHighlighterBase() {

    val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    val key_line_comment =
        arrayOf(createTextAttributesKey("line_comment", DefaultLanguageHighlighterColors.LINE_COMMENT))
    val key_block_comment =
        arrayOf(createTextAttributesKey("block_comment", DefaultLanguageHighlighterColors.BLOCK_COMMENT))
    val key_string = arrayOf(createTextAttributesKey("string", DefaultLanguageHighlighterColors.STRING))
    val key_keyword = arrayOf(createTextAttributesKey("keyword", DefaultLanguageHighlighterColors.KEYWORD))
    val key_label = arrayOf(createTextAttributesKey("label", DefaultLanguageHighlighterColors.LABEL))
    val key_constant = arrayOf(createTextAttributesKey("constant", DefaultLanguageHighlighterColors.CONSTANT))
    val key_text = arrayOf(createTextAttributesKey("text", HighlighterColors.TEXT))

    // 圆括号
    val key_parentheses = arrayOf(createTextAttributesKey("parentheses", DefaultLanguageHighlighterColors.PARENTHESES))
    val key_local_variable =
        arrayOf(createTextAttributesKey("local_variable", DefaultLanguageHighlighterColors.LOCAL_VARIABLE))
    val key_global_variable =
        arrayOf(createTextAttributesKey("global_variable", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE))
    val key_function_declaration =
        arrayOf(createTextAttributesKey("function_declaration", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION))
    val key_number = arrayOf(createTextAttributesKey("number", DefaultLanguageHighlighterColors.NUMBER))
    val key_parameter = arrayOf(createTextAttributesKey("parameter", DefaultLanguageHighlighterColors.PARAMETER))

    override fun getHighlightingLexer(): Lexer {
        return JsgfLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (tokenType == JsgfTypes.KEYWORD) {
            return key_keyword
        }
        if (tokenType == JsgfTypes.RULE_NAME || tokenType == JsgfTypes.DEF_RULE_NAME) {
            return key_function_declaration
        }
        if (tokenType == JsgfTypes.OP_EQ) {
            return key_text
        }
        if (tokenType == JsgfTypes.OP_OR) {
            return key_text
        }
        // 圆括号
//        if(tokenType == JsgfTypes.LEFT_PAREN
//            || tokenType == JsgfTypes.RIGHT_PAREN) {
//            return key_parentheses
//        }
        if (tokenType == JsgfTypes.LINE_COMMENT) {
            return key_line_comment
        }
        if (tokenType == JsgfTypes.BLOCK_COMMENT) {
            return key_block_comment
        }
        if (tokenType == JsgfTypes.STRING
            || tokenType == JsgfTypes.LABEL_STRING
        ) {
            return key_string
        }
        if (tokenType == JsgfTypes.SLOP_STRING_LITERAL) {
            return key_number
        }
        if (tokenType == JsgfTypes.SEMICOLON) {
            return key_text
        }
        if (tokenType == JsgfTypes.WEIGHTS) {
            return key_constant
        }
        return EMPTY_KEYS
    }
}