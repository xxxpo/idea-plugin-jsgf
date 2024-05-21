package com.xxxlin.jsgf

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import com.xxxlin.jsgf.psi.JsgfExp
import com.xxxlin.jsgf.psi.JsgfTypes

/**
 * 代码提示
 */
class JsgfCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC, PlatformPatterns.psiElement(JsgfTypes.SLOP_STRING_LITERAL),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    val properties = find(parameters.originalFile)
                    properties.forEach {
                        val text = it.replace("<", "")
                            .replace(">", "")
                        result.addElement(LookupElementBuilder.create(text))
                    }
                }
            }
        )
    }

    fun find(e: PsiElement): List<String> {
        val result = mutableListOf<String>()
        find(e, result)
        return result
    }

    private fun find(e: PsiElement, result: MutableList<String>) {
        if (e is JsgfExp) {
            val key = e.key
            if (key != null) {
                result.add(key)
            }
        }
        if (e == JsgfTypes.VARIABLE_NAME) {
            result.add(e.node.text)
        }
        e.children.forEach {
            find(it, result)
        }
    }
}