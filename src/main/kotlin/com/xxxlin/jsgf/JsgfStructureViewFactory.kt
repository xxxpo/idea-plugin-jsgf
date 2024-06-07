package com.xxxlin.jsgf

import com.intellij.ide.structureView.StructureViewBuilder
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

/**
 * 结构视图
 */
class JsgfStructureViewFactory: PsiStructureViewFactory {

    override fun getStructureViewBuilder( psiFile: PsiFile): StructureViewBuilder {
        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return JsgfStructureViewModel(editor, psiFile)
            }
        }
    }
}