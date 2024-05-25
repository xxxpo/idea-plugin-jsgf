package com.xxxlin.jsgf

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.xxxlin.jsgf.psi.JsgfTypes

class JsgfPairedBraceMatcher : PairedBraceMatcher {

    override fun getPairs(): Array<BracePair> {
        return arrayOf(
            BracePair(JsgfTypes.LEFT_PAREN, JsgfTypes.RIGHT_PAREN, false),
            BracePair(JsgfTypes.LEFT_BRACKET, JsgfTypes.RIGHT_BRACKET, false),
            BracePair(JsgfTypes.LEFT_BRACE, JsgfTypes.RIGHT_BRACE, true),
        )
    }

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean {
        if (contextType == null) {
            return false
        }
        if (lbraceType.isLeftBound == contextType.isLeftBound) {
            return false
        }
        if (lbraceType.language != contextType.language) {
            return false
        }
        if (lbraceType == JsgfTypes.LEFT_BRACKET && contextType == JsgfTypes.RIGHT_BRACE) {
            return true
        }
        if (lbraceType == JsgfTypes.LEFT_PAREN && contextType == JsgfTypes.RIGHT_PAREN) {
            return true
        }
        if (lbraceType == JsgfTypes.LEFT_BRACE && contextType == JsgfTypes.RIGHT_BRACKET) {
            return true
        }
        return false
    }

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int {
        return openingBraceOffset
    }
}