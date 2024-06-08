package com.xxxlin.jsgf

import com.intellij.icons.AllIcons
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.xxxlin.jsgf.psi.JsgfDefRuleNameWrapper
import com.xxxlin.jsgf.psi.JsgfReferenceRuleName
import com.xxxlin.jsgf.psi.JsgfRule
import com.xxxlin.jsgf.psi.JsgfTypes
import com.xxxlin.utils.LogUtil
import javax.swing.Icon

object JsgfPsiImplUtil {

    @JvmStatic
    fun getDefRuleName(element: JsgfRule): String {
        return element.node
            .findChildByType(JsgfTypes.DEF_RULE_NAME_WRAPPER)
            ?.findChildByType(JsgfTypes.DEF_RULE_NAME)
            ?.text ?: return ""
    }

    @JvmStatic
    fun getValue(element: JsgfRule): String {
        // fixme 字符串无法显示
        return element.text.trim()
    }

    @JvmStatic
    fun getModifier(element: JsgfRule): PsiElement? {
        return element.node.findChildByType(JsgfTypes.MODIFIER)?.psi
    }

    /**
     * 结构视图
     */
    @JvmStatic
    fun getPresentation(element: JsgfRule): ItemPresentation {
        return object : ItemPresentation {
            override fun getPresentableText(): String {
                return element.defRuleName
            }

            override fun getLocationString(): String? {
                return null
            }

            override fun getIcon(unused: Boolean): Icon {
                return AllIcons.Nodes.Property
            }
        }
    }

    @JvmStatic
    fun getName(element: JsgfReferenceRuleName): String {
        return element.text
    }

    @JvmStatic
    fun setName(element: JsgfReferenceRuleName, newName: String): PsiElement {
        val node = element.node.findChildByType(JsgfTypes.REFERENCE_RULE_NAME)
        if (node != null) {
            val property = JsgfElementFactory.createRuleName(element.project, newName)
            val newKeyNode = property.firstChild.node
            element.node.replaceChild(node, newKeyNode)
        }
        return element
    }

    @JvmStatic
    fun getNameIdentifier(element: JsgfReferenceRuleName): PsiElement? {
        val node = element.node.findChildByType(JsgfTypes.RULE_NAME)
        return node?.psi
    }

    @JvmStatic
    fun getReference(element: JsgfReferenceRuleName): PsiReference {
        val text = element.text
        val textRange = TextRange(0, text.length)
        return JsgfRuleReference(element, textRange, true)
    }

    @JvmStatic
    fun getName(element: JsgfDefRuleNameWrapper): String {
        return element.text
    }

    @JvmStatic
    fun setName(element: JsgfDefRuleNameWrapper, newName: String): PsiElement {
        LogUtil.log("JsgfDefRuleNameWrapper setName $newName")
        val node = element.node.findChildByType(JsgfTypes.DEF_RULE_NAME)
        if (node != null) {
            val psi = JsgfElementFactory.createDefRuleName(element.project, newName)
            element.node.replaceChild(node, psi.node)
        }
        return element
    }

    @JvmStatic
    fun getNameIdentifier(element: JsgfDefRuleNameWrapper): PsiElement? {
        val node = element.node.findChildByType(JsgfTypes.DEF_RULE_NAME)
        return node?.psi
    }

}