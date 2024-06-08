package com.xxxlin.jsgf

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.xxxlin.jsgf.psi.JsgfTypes
import com.xxxlin.utils.LogUtil

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
        val psiList = if (onlyThisFile) {
            JsgfUtil.findDefRuleNameWrapper(element.containingFile, ruleName)
        } else {
            JsgfUtil.findDefRuleNameWrapper(project, ruleName)
        }
        return psiList.map {
            PsiElementResolveResult(it)
        }.toTypedArray()
    }

    override fun resolve(): PsiElement? {
        return multiResolve(false).firstOrNull()?.element
    }

    override fun getVariants(): Array<out Any> {
        LogUtil.log("getVariants")
        val project = myElement.project
        val psiList = JsgfUtil.findDefRuleNameWrapper(project)
        val variants: MutableList<LookupElement> = ArrayList()
//        for (rule in psiList) {
//            if (rule.defRuleName?.isNotEmpty() == true) {
//                variants.add(
//                    LookupElementBuilder.create(rule).withIcon(JsgfIcons.FILE).withTypeText(rule.containingFile.name)
//                )
//            }
//        }
        return variants.toTypedArray()
    }

    override fun handleElementRename(newElementName: String): PsiElement {
        LogUtil.log("handleElementRename newElementName=$newElementName")
        val identifier = PsiElementFactory
            .getInstance(element.project)
            .createIdentifier(newElementName)
        LogUtil.log("handleElementRename newElementName new=${identifier.text}")
        return identifier
    }

    override fun bindToElement(element: PsiElement): PsiElement {
        LogUtil.log("handleElementRename bindToElement")
        return super.bindToElement(element)
    }

}