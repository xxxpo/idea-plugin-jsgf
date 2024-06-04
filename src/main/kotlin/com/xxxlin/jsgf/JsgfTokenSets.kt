package com.xxxlin.jsgf

import com.intellij.psi.tree.TokenSet
import com.xxxlin.jsgf.psi.JsgfTypes

object JsgfTokenSets {

    val IDENTIFIERS = TokenSet.create(
        JsgfTypes.DEF_RULE_NAME,
        JsgfTypes.SLOP_STRING_LITERAL,
    )

    val COMMENTS = TokenSet.create(
        JsgfTypes.LINE_COMMENT,
        JsgfTypes.BLOCK_COMMENT,
    )

    val LITERAL = TokenSet.create(
        JsgfTypes.RULE_NAME,
        JsgfTypes.SLOP_STRING_LITERAL,
        JsgfTypes.DEF_RULE_NAME,
    )
}