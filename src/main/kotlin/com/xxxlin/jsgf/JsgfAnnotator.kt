package com.xxxlin.jsgf

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.xxxlin.jsgf.psi.JsgfReferenceRuleName
import com.xxxlin.jsgf.psi.JsgfRule
import com.xxxlin.jsgf.psi.JsgfTypes

/**
 * JSGF字符串注解
 */
class JsgfAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is JsgfReferenceRuleName) {
            annotateReferenceRuleName(element, holder)
        } else if (element is JsgfRule) {
            annotateDefRuleNameWrapper(element, holder)
        }
    }

    /**
     * 使用未定义规则标色
     */
    private fun annotateReferenceRuleName(element: JsgfReferenceRuleName, holder: AnnotationHolder) {
        val hasDefRuleName = JsgfUtil.hasDefRuleName(element.containingFile, element.text)
        if (!hasDefRuleName) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(JsgfSyntaxHighlighter.key_rule_name_err[0])
                .tooltip("Undefined")
                .create()
        }
    }

    /**
     * 未使用的规则标色
     */
    private fun annotateDefRuleNameWrapper(element: JsgfRule, holder: AnnotationHolder) {
        if (element.modifier?.text == "public") {
            return
        }
        val ruleNameWrapper = element.node.findChildByType(JsgfTypes.DEF_RULE_NAME_WRAPPER)?.psi ?: return
        val ruleName = element.defRuleName
        val hasRuleName = JsgfUtil.hasRuleName(element.containingFile, ruleName)
        if (!hasRuleName) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(ruleNameWrapper)
                .textAttributes(JsgfSyntaxHighlighter.key_rule_name_unused[0])
                .tooltip("Unused")
                .create()
        }
    }
}