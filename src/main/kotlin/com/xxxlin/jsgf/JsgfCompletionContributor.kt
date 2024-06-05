package com.xxxlin.jsgf

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import com.xxxlin.jsgf.psi.JsgfTypes

/**
 * 代码提示
 */
class JsgfCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC,
            PlatformPatterns.psiElement(JsgfTypes.SLOP_STRING_LITERAL),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet
                ) {
                    val properties = JsgfUtil.findDefRuleName(parameters.originalFile)
                    properties.forEach {
                        val text = it.text.replace("<", "").replace(">", "")
                        result.addElement(LookupElementBuilder.create(text))
                    }
                }
            })
    }
}