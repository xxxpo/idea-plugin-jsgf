package com.xxxlin.jsgf

import com.intellij.psi.tree.TokenSet
import com.xxxlin.jsgf.psi.JsgfTypes

object JsgfTokenSets {

    var COMMENTS: TokenSet = TokenSet.create(JsgfTypes.LINE_COMMENT, JsgfTypes.BLOCK_COMMENT)
    var STRING: TokenSet = TokenSet.create(JsgfTypes.STRING)
}