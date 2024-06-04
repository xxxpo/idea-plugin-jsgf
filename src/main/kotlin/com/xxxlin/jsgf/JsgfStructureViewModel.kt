package com.xxxlin.jsgf

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import com.xxxlin.jsgf.psi.JsgfRule

class JsgfStructureViewModel(
    editor: Editor?, psiFile: PsiFile
) : StructureViewModelBase(
    psiFile, editor, JsgfStructureViewElement(psiFile)
), StructureViewModel.ElementInfoProvider {

    override fun getSorters(): Array<Sorter> {
        return arrayOf(Sorter.ALPHA_SORTER)
    }

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean {
        return false
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return element.value is JsgfRule
    }

    override fun getSuitableClasses(): Array<out Class<*>> {
        return arrayOf(JsgfRule::class.java)
    }

}