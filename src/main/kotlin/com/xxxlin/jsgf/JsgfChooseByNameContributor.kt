package com.xxxlin.jsgf

import com.intellij.navigation.ChooseByNameContributorEx
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.Processor
import com.intellij.util.containers.ContainerUtil
import com.intellij.util.indexing.FindSymbolParameters
import com.intellij.util.indexing.IdFilter
import com.xxxlin.jsgf.psi.JsgfRule

class JsgfChooseByNameContributor : ChooseByNameContributorEx {

    override fun processNames(
        processor: Processor<in String>,
        scope: GlobalSearchScope,
        filter: IdFilter?
    ) {
        val project: Project = scope.project!!
        val ret = JsgfUtil.findRules(project)
        println("ret size=${ret.size}")
        val propertyKeys = ContainerUtil.map(
            ret, JsgfRule::getKey
        ).map { it ?: "" }
        ContainerUtil.process(propertyKeys, processor)
    }

    override fun processElementsWithName(
        name: String,
        processor: Processor<in NavigationItem>,
        parameters: FindSymbolParameters
    ) {
        println("processElementsWithName name=$name")
        val properties = ContainerUtil.map(
            JsgfUtil.findDefRuleName(parameters.project, name)
        ) { property -> property as NavigationItem }
        ContainerUtil.process(properties, processor)
    }
}