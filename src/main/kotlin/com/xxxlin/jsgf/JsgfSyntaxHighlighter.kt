package com.xxxlin.jsgf

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.CodeInsightColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.editor.colors.impl.DefaultColorsScheme
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.xxxlin.jsgf.psi.JsgfTypes

/**
 * 语法高亮
 */
object JsgfSyntaxHighlighter : SyntaxHighlighterBase() {

    val EMPTY_KEYS = arrayOf<TextAttributesKey>()

    val key_line_comment = arrayOf(
        createTextAttributesKey("line_comment", DefaultLanguageHighlighterColors.LINE_COMMENT)
    )

    val key_block_comment = arrayOf(
        createTextAttributesKey("block_comment", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
    )

    val key_string = arrayOf(
        createTextAttributesKey("string", DefaultLanguageHighlighterColors.STRING)
    )

    val key_keyword = arrayOf(
        createTextAttributesKey("keyword", DefaultLanguageHighlighterColors.KEYWORD)
    )

    val key_label = arrayOf(
        createTextAttributesKey("label", DefaultLanguageHighlighterColors.LABEL)
    )

    val key_constant = arrayOf(
        createTextAttributesKey("constant", DefaultLanguageHighlighterColors.CONSTANT)
    )

    val key_text = arrayOf(
        createTextAttributesKey("text", HighlighterColors.TEXT)
    )

    /**
     * 圆括号
     */
    val key_parentheses = arrayOf(
        createTextAttributesKey("parentheses", DefaultLanguageHighlighterColors.PARENTHESES)
    )

    val key_local_variable = arrayOf(
        createTextAttributesKey("local_variable", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
    )

    val key_global_variable = arrayOf(
        createTextAttributesKey("global_variable", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE)
    )

    val key_rule_name = arrayOf(
        createTextAttributesKey("rule_name", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
    )

    /**
     * 使用未定义的规则
     *
     * [CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES] 红色
     * [CodeInsightColors.ERRORS_ATTRIBUTES] 红色下划线
     * [CodeInsightColors.WARNINGS_ATTRIBUTES] 背景黄色
     * [CodeInsightColors.DUPLICATE_FROM_SERVER]背景黄色
     * [CodeInsightColors.GENERIC_SERVER_ERROR_OR_WARNING] 下划线
     * [CodeInsightColors.RUNTIME_ERROR] 下划连点
     * [CodeInsightColors.NOT_USED_ELEMENT_ATTRIBUTES] 未引用，颜色弱化
     * [CodeInsightColors.DEPRECATED_ATTRIBUTES] 册除线
     * [CodeInsightColors.MARKED_FOR_REMOVAL_ATTRIBUTES] 红色册除线
     * [CodeInsightColors.MATCHED_BRACE_ATTRIBUTES] 加深颜色加深色背景
     * [CodeInsightColors.UNMATCHED_BRACE_ATTRIBUTES] 深红色
     * [CodeInsightColors.BLINKING_HIGHLIGHTS_ATTRIBUTES] 边框
     * [CodeInsightColors.HYPERLINK_ATTRIBUTES] 深蓝色+下划线
     * [CodeInsightColors.FOLLOWED_HYPERLINK_ATTRIBUTES] 深蓝色+下划线
     * [CodeInsightColors.INACTIVE_HYPERLINK_ATTRIBUTES] 浅蓝色下划线
     * [CodeInsightColors.TODO_DEFAULT_ATTRIBUTES] 屎绿色
     * [CodeInsightColors.LINE_FULL_COVERAGE] 灰绿色
     * [CodeInsightColors.LINE_NONE_COVERAGE] 浅红色
     * [CodeInsightColors.WEAK_WARNING_ATTRIBUTES] 弱警告
     */
    val key_rule_name_err = arrayOf(
        createTextAttributesKey("rule_name_err", CodeInsightColors.WEAK_WARNING_ATTRIBUTES)
    )

    /**
     * 规则未引用
     */
    val key_rule_name_unused = arrayOf(
        createTextAttributesKey("rule_name_unused", CodeInsightColors.NOT_USED_ELEMENT_ATTRIBUTES)
    )

    /**
     * 规则引用
     */
    val key_rule_name_reference = arrayOf(
        createTextAttributesKey("rule_name_reference", CodeInsightColors.GENERIC_SERVER_ERROR_OR_WARNING)
    )

    val key_number = arrayOf(
        createTextAttributesKey("number", DefaultLanguageHighlighterColors.NUMBER)
    )

    val key_parameter = arrayOf(
        createTextAttributesKey("parameter", DefaultLanguageHighlighterColors.PARAMETER)
    )

    override fun getHighlightingLexer(): Lexer {
        return JsgfLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (tokenType == JsgfTypes.MODIFIER) {
            return key_keyword
        }
        if (tokenType == JsgfTypes.RULE_NAME || tokenType == JsgfTypes.DEF_RULE_NAME) {
            return key_rule_name
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