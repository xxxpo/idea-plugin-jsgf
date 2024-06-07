package com.xxxlin.jsgf

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import com.xxxlin.jsgf.psi.JsgfReferenceRuleName
import com.xxxlin.jsgf.psi.JsgfRule

class JsgfRefactoringSupportProvider : RefactoringSupportProvider() {

    override fun isMemberInplaceRenameAvailable(elementToRename: PsiElement, context: PsiElement?): Boolean {
        val ret = elementToRename is JsgfRule
                || elementToRename is JsgfReferenceRuleName
        return ret
    }
}