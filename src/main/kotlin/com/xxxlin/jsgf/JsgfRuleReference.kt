package com.xxxlin.jsgf

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

/**
 * 提供或查找JSGF规则引用
 */
class JsgfRuleReference(
    private val element: PsiElement,
    textRange: TextRange,
    private val onlyThisFile: Boolean
) : PsiReferenceBase<PsiElement>(
    element, textRange
), PsiPolyVariantReference {

    private val ruleName = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val project = myElement.project
        val ruleList = if (onlyThisFile) {
            JsgfUtil.findRules(element.containingFile, ruleName)
        } else {
            JsgfUtil.findRules(project, ruleName)
        }
        return ruleList.map {
            PsiElementResolveResult(it)
        }.toTypedArray()
    }

    override fun resolve(): PsiElement? {
        return multiResolve(false).firstOrNull()?.element
    }

    override fun getVariants(): Array<out Any> {
        println("getVariants")
        val project = myElement.project
        val ruleList = JsgfUtil.findRules(project)
        val variants: MutableList<LookupElement> = ArrayList()
        for (rule in ruleList) {
            if (rule.defRuleName?.isNotEmpty() == true) {
                variants.add(
                    LookupElementBuilder.create(rule).withIcon(JsgfIcons.FILE).withTypeText(rule.containingFile.name)
                )
            }
        }
        return variants.toTypedArray()
    }

}