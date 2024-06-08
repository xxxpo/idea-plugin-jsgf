package com.xxxlin.jsgf

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression

/**
 * JAVA字符串注解
 */
class JavaAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is PsiLiteralExpression) {
            annotateString(element, holder)
        }
    }

    /**
     * 引用规则标色
     */
    private fun annotateString(element: PsiLiteralExpression, holder: AnnotationHolder) {
        val value = element.value as String? ?: return
        if (!value.startsWith("<") || !value.endsWith(">")) {
            return
        }

        val keyRange = TextRange(element.textRange.startOffset + 1, element.textRange.endOffset - 1)
        val listPsiElement = JsgfUtil.findRule(element.project, value)
        if (listPsiElement.isNotEmpty()) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION).range(keyRange)
                .textAttributes(JsgfSyntaxHighlighter.key_number[0])
                .tooltip("in ${listPsiElement[0].containingFile.name}").create()
        }
    }
}