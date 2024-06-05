package com.xxxlin.jsgf

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import com.xxxlin.jsgf.psi.JsgfTypes

class JsgfRefactoringSupportProvider: RefactoringSupportProvider() {

    override fun isMemberInplaceRenameAvailable(elementToRename: PsiElement, context: PsiElement?): Boolean {
        return elementToRename.node.findChildByType(JsgfTypes.DEF_RULE_NAME) != null
    }
}