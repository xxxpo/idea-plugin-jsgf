package com.xxxlin.jsgf

import com.intellij.codeInsight.completion.PlainTextSymbolCompletionContributor
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.PsiFile

class JsgfPlainTextSymbolCompletionContributor: PlainTextSymbolCompletionContributor {

    override fun getLookupElements(
        file: PsiFile,
        invocationCount: Int,
        prefix: String
    ): MutableCollection<LookupElement> {
        val list = mutableListOf<LookupElement>()
        list.add(LookupElementBuilder.create("helloworld").withTailText("abc"))
        return list
    }
}