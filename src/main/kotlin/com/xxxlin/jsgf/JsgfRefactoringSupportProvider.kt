package com.xxxlin.jsgf

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import com.xxxlin.jsgf.psi.JsgfDefRuleNameWrapper
import com.xxxlin.jsgf.psi.JsgfReferenceRuleName

class JsgfRefactoringSupportProvider : RefactoringSupportProvider() {

    override fun isMemberInplaceRenameAvailable(elementToRename: PsiElement, context: PsiElement?): Boolean {
        val ret = elementToRename is JsgfDefRuleNameWrapper
                || elementToRename is JsgfReferenceRuleName
        return false
    }
}