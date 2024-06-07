package com.xxxlin.jsgf

import com.intellij.lang.ASTNode
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.tree.IElementType
import com.xxxlin.jsgf.psi.JsgfDefRuleNameWrapper
import com.xxxlin.jsgf.psi.JsgfRule
import com.xxxlin.jsgf.psi.JsgfTypes

object JsgfUtil {

    private fun findByType(e: PsiElement, type: IElementType): List<ASTNode> {
        val result = mutableListOf<ASTNode>()
        findByType(e, type, result)
        return result
    }

    private fun findByType(e: PsiElement, type: IElementType, result: MutableList<ASTNode>) {
        val node = e.node.findChildByType(type)
        if (node != null) {
            result.add(node)
        }
        e.children.forEach {
            findByType(it, type, result)
        }
    }

    fun findAllJsgfFile(project: Project): List<JsgfFile> {
        val result = ArrayList<JsgfFile>()
        val virtualFiles = FileTypeIndex.getFiles(JsgfFileType.INSTANCE, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val file = PsiManager.getInstance(project).findFile(virtualFile) as JsgfFile? ?: continue
            result.add(file)
        }
        return result
    }

    fun findRules(project: Project): List<JsgfRule> {
        val result = ArrayList<JsgfRule>()
        findAllJsgfFile(project).forEach { file ->
            result.addAll(findRules(file))
        }
        return result
    }

    fun findRules(e: PsiElement): List<JsgfRule> {
        return findByType(e, JsgfTypes.RULE).map {
            it.psi as JsgfRule
        }
    }

    fun findRules(project: Project, ruleName: String): List<JsgfRule> {
        return findRules(project).filter {
            it.defRuleName == ruleName
        }
    }

    fun findRules(element: PsiElement, ruleName: String): List<JsgfRule> {
        return findRules(element).filter {
            it.defRuleName == ruleName
        }
    }

    fun findDefRuleName(e: PsiElement): List<ASTNode> {
        return findByType(e, JsgfTypes.DEF_RULE_NAME)
    }

    fun findDefRuleName(project: Project, ruleName: String): List<PsiElement> {
        val result = ArrayList<PsiElement>()
        findAllJsgfFile(project).forEach { file ->
            findDefRuleName(file).forEach { node ->
                if (node.text == ruleName) {
                    result.add(node.psi)
                }
            }
        }
        return result
    }

    fun findDefRuleNameWrapper(e: PsiElement): List<JsgfDefRuleNameWrapper> {
        return findByType(e, JsgfTypes.DEF_RULE_NAME_WRAPPER).map {
            it.psi as JsgfDefRuleNameWrapper
        }
    }

    fun findDefRuleNameWrapper(project: Project): List<JsgfDefRuleNameWrapper> {
        val result = ArrayList<JsgfDefRuleNameWrapper>()
        findAllJsgfFile(project).forEach { file ->
            result.addAll(findDefRuleNameWrapper(file))
        }
        return result
    }

    fun findDefRuleNameWrapper(project: Project, ruleName: String): List<JsgfDefRuleNameWrapper> {
        return findDefRuleNameWrapper(project).filter {
            it.text == ruleName
        }
    }

    fun findDefRuleNameWrapper(element: PsiElement, ruleName: String): List<JsgfDefRuleNameWrapper> {
        return findDefRuleNameWrapper(element).filter {
            it.text == ruleName
        }
    }
}