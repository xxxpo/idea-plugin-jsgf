package com.xxxlin.jsgf

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.util.containers.toArray

class JsgfReference(
    element: PsiElement, textRange: TextRange
) : PsiReferenceBase<PsiElement>(
    element, textRange
), PsiPolyVariantReference {

    private val key: String = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val project = myElement.project
        val ruleList = JsgfUtil.findRules(project, key)
        val results: MutableList<ResolveResult> = ArrayList()
        for (rule in ruleList) {
            results.add(PsiElementResolveResult(rule))
        }
        return results.toArray(arrayOf())
    }

    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return resolveResults.firstOrNull()?.element
    }

    override fun getVariants(): Array<out Any> {
        val project = myElement.project
        val ruleList = JsgfUtil.findRules(project)
        val variants: MutableList<LookupElement> = ArrayList()
        for (rule in ruleList) {
            if (rule.getKey()?.isNotEmpty() == true) {
                variants.add(
                    LookupElementBuilder.create(rule).withIcon(JsgfIcons.FILE).withTypeText(rule.containingFile.name)
                )
            }
        }
        return variants.toTypedArray()
    }

}