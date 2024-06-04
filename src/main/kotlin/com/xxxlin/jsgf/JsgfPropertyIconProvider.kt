package com.xxxlin.jsgf

import com.intellij.ide.IconProvider
import com.intellij.psi.PsiElement
import com.xxxlin.jsgf.psi.JsgfRule
import javax.swing.Icon

class JsgfPropertyIconProvider : IconProvider() {

    override fun getIcon(element: PsiElement, flags: Int): Icon? {
        return if (element is JsgfRule) JsgfIcons.FILE else null
    }
}