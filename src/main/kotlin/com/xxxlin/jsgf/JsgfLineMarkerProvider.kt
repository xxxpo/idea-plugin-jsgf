package com.xxxlin.jsgf

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression
import com.xxxlin.jsgf.psi.JsgfReferenceRuleName

/**
 * 提供线标能力，行号右边的图标
 */
class JsgfLineMarkerProvider : RelatedItemLineMarkerProvider() {

    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>?>
    ) {
        if (element is PsiLiteralExpression) {
            markPsiLiteralExpression(element, result)
        } else if(element is JsgfReferenceRuleName) {
            markRuleName(element, result)
        }
    }

    private fun markPsiLiteralExpression(
        element: PsiLiteralExpression,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>?>
    ) {
        val value = element.value as? String? ?: return
        if (!value.startsWith("<") || !value.endsWith(">")) {
            return
        }

        val project = element.project
        val ruleList = JsgfUtil.findRules(project, value)
        if (ruleList.isNotEmpty()) {
            val builder =
                NavigationGutterIconBuilder.create(JsgfIcons.FILE)
                    .setTargets(ruleList)
                    .setTooltipText("Navigate to $value")
            result.add(builder.createLineMarkerInfo(element))
        }
    }

    private fun markRuleName(
        element: JsgfReferenceRuleName,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>?>
    ) {
        val value = element.text as? String? ?: return
        if (!value.startsWith("<") || !value.endsWith(">")) {
            return
        }

        val project = element.project
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