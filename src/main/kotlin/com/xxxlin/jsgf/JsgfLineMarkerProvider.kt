package com.xxxlin.jsgf

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl

/**
 * 提供线标能力，行号右边的图标
 */
class JsgfLineMarkerProvider : RelatedItemLineMarkerProvider() {

    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>?>
    ) {
        if (element !is PsiJavaTokenImpl || element.getParent() !is PsiLiteralExpression) {
            return
        }

        val literalExpression = element.getParent() as PsiLiteralExpression
        val value = literalExpression.value as? String? ?: return
        if (!value.startsWith("<") && !value.endsWith(">")) {
            return
        }

        val project = element.getProject()
        val ruleList = JsgfUtil.findRules(project, value)
        if (ruleList.isNotEmpty()) {
            val builder =
                NavigationGutterIconBuilder.create(JsgfIcons.FILE)
                    .setTargets(ruleList)
                    .setTooltipText("Navigate to $value")
            result.add(builder.createLineMarkerInfo(element))
        }
    }

}