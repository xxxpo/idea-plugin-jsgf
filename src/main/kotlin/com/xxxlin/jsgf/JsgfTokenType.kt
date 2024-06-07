package com.xxxlin.jsgf

import com.intellij.psi.tree.IElementType

class JsgfTokenType(
    debugName: String
) : IElementType(
    debugName,
    JSGFLanguage.INSTANCE
) {

    override fun toString(): String {
        return "JsgfTokenType." + super.toString()
    }
}