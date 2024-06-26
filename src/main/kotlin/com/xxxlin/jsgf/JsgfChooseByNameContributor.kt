package com.xxxlin.jsgf

import com.intellij.navigation.ChooseByNameContributorEx
import com.intellij.navigation.NavigationItem
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.Processor
import com.intellij.util.indexing.FindSymbolParameters
import com.intellij.util.indexing.IdFilter
import com.xxxlin.utils.LogUtil

class JsgfChooseByNameContributor : ChooseByNameContributorEx {

    override fun processNames(
        processor: Processor<in String>,
        scope: GlobalSearchScope,
        filter: IdFilter?
    ) {
        val project = scope.project ?: return
        val ruleList = JsgfUtil.findRules(project)
        LogUtil.log("ret size=${ruleList.size}")
        ruleList.forEach {
            val ruleName = it.defRuleName
            if(ruleName.isNotEmpty()) {
                processor.process(ruleName)
            }
        }
    }

    override fun processElementsWithName(
        name: String,
        processor: Processor<in NavigationItem>,
        parameters: FindSymbolParameters
    ) {
        LogUtil.log("processElementsWithName name=$name")
        JsgfUtil.findDefRuleName(parameters.project, name).map {
            it as NavigationItem
        }.forEach {
            processor.process(it)
        }
    }
}