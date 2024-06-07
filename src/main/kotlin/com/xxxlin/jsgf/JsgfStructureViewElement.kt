package com.xxxlin.jsgf

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import com.xxxlin.jsgf.psi.impl.JsgfRuleImpl

class JsgfStructureViewElement(
    private val element: NavigatablePsiElement
) : StructureViewTreeElement, SortableTreeElement {

    override fun getPresentation(): ItemPresentation {
        val presentation = element.presentation
        return presentation ?: PresentationData()
    }

    override fun getChildren(): Array<TreeElement> {
        if (element is JsgfFile) {
            return JsgfUtil.findRules(element).map {
                JsgfStructureViewElement(it as JsgfRuleImpl)
            }.toTypedArray()
        }
        return arrayOf()
    }

    override fun getValue(): Any {
        return element.text
    }

    override fun getAlphaSortKey(): String {
        return element.name ?: ""
    }

    override fun navigate(requestFocus: Boolean) {
        element.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return element.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return element.canNavigateToSource()
    }
}