package com.xxxlin.jsgf

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext

/**
 * java字符串引用JSGF规则
 */
class JsgfRuleReferenceContributor : PsiReferenceContributor() {

    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(PsiLiteralExpression::class.java),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<out PsiReference> {
                    val exp = element as PsiLiteralExpression
                    val text = exp.text.trim()
                    if (text.startsWith("\"<") && text.endsWith(">\"")) {
                        val textRange = TextRange(1, text.length - 1)
                        return arrayOf<PsiReference>(JsgfRuleReference(element, textRange, false))
                    }
                    return PsiReference.EMPTY_ARRAY
                }
            }
        )
    }
}