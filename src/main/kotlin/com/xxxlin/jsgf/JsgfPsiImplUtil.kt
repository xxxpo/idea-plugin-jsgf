package com.xxxlin.jsgf

import com.intellij.icons.AllIcons
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.xxxlin.jsgf.psi.JsgfReferenceRuleName
import com.xxxlin.jsgf.psi.JsgfRule
import com.xxxlin.jsgf.psi.JsgfTypes
import javax.swing.Icon

object JsgfPsiImplUtil {

    @JvmStatic
    fun getDefRuleName(element: JsgfRule): String? {
        val keyNode = element.node
            .findChildByType(JsgfTypes.DEF_RULE_NAME_WRAPPER)
            ?.findChildByType(JsgfTypes.DEF_RULE_NAME) ?: return null
        return keyNode.text
    }

    @JvmStatic
    fun getValue(element: JsgfRule): String? {
        val valueNode = element.node.findChildByType(JsgfTypes.RULE_EXPANSION) ?: return null
        return valueNode.text.trim()
    }

    @JvmStatic
    fun getName(element: JsgfRule): String {
        val name = getDefRuleName(element) ?: ""
        return name
    }

    @JvmStatic
    fun setName(element: JsgfRule, newName: String): PsiElement {
        val node = element.node.findChildByType(JsgfTypes.DEF_RULE_NAME_WRAPPER)
        if (node != null) {
            val referenceRuleName = JsgfElementFactory.createProperty(element.project, newName)
            element.node.replaceChild(node, referenceRuleName.node)
        }
        return element
    }

    @JvmStatic
    fun getNameIdentifier(element: JsgfRule): PsiElement? {
        val node = element.node
            .findChildByType(JsgfTypes.DEF_RULE_NAME_WRAPPER)
            ?.findChildByType(JsgfTypes.DEF_RULE_NAME)
        return node?.psi
    }

    /**
     * 结构视图
     */
    @JvmStatic
    fun getPresentation(element: JsgfRule): ItemPresentation {
        return object : ItemPresentation {
            override fun getPresentableText(): String {
                return element.defRuleName ?: ""
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

}