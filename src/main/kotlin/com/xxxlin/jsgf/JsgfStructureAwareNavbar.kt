package com.xxxlin.jsgf

import com.intellij.icons.AllIcons
import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension
import com.intellij.lang.Language
import com.intellij.psi.PsiElement
import com.xxxlin.jsgf.psi.JsgfRule
import javax.swing.Icon

class JsgfStructureAwareNavbar(
    override val language: Language = JsgfLanguage.INSTANCE
) : StructureAwareNavBarModelExtension() {

    override fun getPresentableText(obj: Any): String? {
        if (obj is JsgfFile) {
            return obj.name
        }
        if (obj is JsgfRule) {
            return obj.defRuleName
        }
        return null
    }

    /**
     * 实现多级导航
     */
    override fun getParent(psiElement: PsiElement?): PsiElement? {
        if(psiElement is JsgfRule) {
            return psiElement.containingFile
        }
        return null
    }

    override fun getIcon(obj: Any?): Icon? {
        if (obj is JsgfRule) {
            return AllIcons.Nodes.Property
        }
        return null
    }
}