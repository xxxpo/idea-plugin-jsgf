package com.xxxlin.jsgf

import com.intellij.icons.AllIcons
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.xxxlin.jsgf.psi.JsgfRule
import com.xxxlin.jsgf.psi.JsgfTypes
import javax.swing.Icon


object JsgfPsiImplUtil {

    @JvmStatic
    fun getKey(element: JsgfRule): String? {
        val keyNode = element.node.findChildByType(JsgfTypes.DEF_RULE_NAME) ?: return null
        return keyNode.text
    }

    @JvmStatic
    fun getValue(element: JsgfRule): String? {
        val valueNode = element.node.findChildByType(JsgfTypes.RULE_EXPANSION) ?: return null
        return valueNode.text.trim()
    }

    @JvmStatic
    fun getName(element: JsgfRule): String {
        return "${getKey(element)}"
    }

    @JvmStatic
    fun setName(element: JsgfRule, newName: String): PsiElement {
        val node = element.node.findChildByType(JsgfTypes.DEF_RULE_NAME)
        if (node != null) {
            val property: JsgfRule = JsgfElementFactory.createProperty(element.project, newName)
            val newKeyNode = property.firstChild.node
            element.node.replaceChild(node, newKeyNode)
        }
        return element
    }

    @JvmStatic
    fun getNameIdentifier(element: JsgfRule): PsiElement? {
        val node = element.node.findChildByType(JsgfTypes.DEF_RULE_NAME)
        return node?.psi
    }

    /**
     * 结构视图
     */
    @JvmStatic
    fun getPresentation(element: JsgfRule): ItemPresentation {
        return object : ItemPresentation {
            override fun getPresentableText(): String {
                return "${element.key}"
            }

            override fun getLocationString(): String? {
                return null
            }

            override fun getIcon(unused: Boolean): Icon {
                return AllIcons.Nodes.Property
            }
        }
    }

}