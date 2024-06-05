package com.xxxlin.jsgf

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordOccurrence
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.lexer.Lexer
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import com.xxxlin.jsgf.psi.JsgfRule

class JsgfFindUsagesProvider : FindUsagesProvider {

    class MyDefaultWordsScanner(
        lexer: Lexer, identifierTokenSet: TokenSet, commentTokenSet: TokenSet, literalTokenSet: TokenSet
    ) : DefaultWordsScanner(lexer, identifierTokenSet, commentTokenSet, literalTokenSet) {

    }

    override fun getWordsScanner(): WordsScanner {
        return MyDefaultWordsScanner(
            JsgfLexerAdapter(), JsgfTokenSets.IDENTIFIERS, JsgfTokenSets.COMMENTS, TokenSet.EMPTY
        )
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean {
        return psiElement is PsiNamedElement
    }

    override fun getHelpId(psiElement: PsiElement): String? {
        return null
    }

    override fun getType(element: PsiElement): String {
        if (element is JsgfRule) {
            return "${element.key}"
        }
        return ""
    }

    override fun getDescriptiveName(element: PsiElement): String {
        if (element is JsgfRule) {
            return "${element.value}"
        }
        return ""
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        println("------getNodeText ${element.text}")
        if (element is JsgfRule) {
            return "${element.key}"
        }
        return ""
    }

}