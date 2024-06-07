package com.xxxlin.jsgf

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression

/**
 * java字符串注解
 */
class JsgfRuleAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element !is PsiLiteralExpression) {
            return
        }

        val value = element.value as String? ?: return
        if (!value.startsWith("<") || !value.endsWith(">")) {
            return
        }

        // <simple>
        val keyRange = TextRange(element.getTextRange().startOffset + 1, element.textRange.endOffset - 1)
        val listPsiElement = JsgfUtil.findDefRuleName(element.getProject(), value)
        if (listPsiElement.isEmpty()) {
//            holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved property")
//                .range(keyRange)
//                .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL) // ** Tutorial step 19. - Add a quick fix for the string containing possible properties
//                .withFix(SimpleCreatePropertyQuickFix(key))
//                .create()
        } else {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(keyRange)
                .textAttributes(JsgfSyntaxHighlighter.key_number[0])
                .create()
        }
    }
}