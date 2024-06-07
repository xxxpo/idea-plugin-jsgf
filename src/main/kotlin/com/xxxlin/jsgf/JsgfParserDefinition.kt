package com.xxxlin.jsgf

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.xxxlin.jsgf.parser.JsgfParser
import com.xxxlin.jsgf.psi.JsgfTypes

class JsgfParserDefinition : ParserDefinition {

    companion object {
        val FILE: IFileElementType = IFileElementType(JSGFLanguage.INSTANCE)
    }

    override fun createLexer(project: Project): Lexer {
        return JsgfLexerAdapter()
    }

    override fun getCommentTokens(): TokenSet {
        return JsgfTokenSets.COMMENTS
    }

    override fun getStringLiteralElements(): TokenSet {
        return JsgfTokenSets.LITERAL
    }

    override fun createParser(project: Project): PsiParser {
        return JsgfParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return JsgfFile(viewProvider)
    }

    override fun createElement(node: ASTNode): PsiElement {
        return JsgfTypes.Factory.createElement(node)
    }
}