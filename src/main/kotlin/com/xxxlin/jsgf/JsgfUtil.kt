package com.xxxlin.jsgf

import com.google.common.collect.Lists
import com.intellij.lang.ASTNode
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import com.xxxlin.jsgf.psi.JsgfRule
import com.xxxlin.jsgf.psi.JsgfTypes
import java.util.*

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

    fun findDefRuleName(e: PsiElement): List<ASTNode> {
        return findByType(e, JsgfTypes.DEF_RULE_NAME)
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

    fun findDefRuleName(project: Project, ruleName: String): List<PsiElement> {
        val result = ArrayList<PsiElement>()
        val virtualFiles =
            FileTypeIndex.getFiles(JsgfFileType.INSTANCE, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val file = PsiManager.getInstance(project).findFile(virtualFile) as JsgfFile? ?: continue
            findDefRuleName(file).forEach {
                if (it.text == ruleName) {
                    result.add(it.psi)
                }
            }
        }
        return result
    }

    fun findRules(e: PsiElement): List<JsgfRule> {
        return findByType(e, JsgfTypes.RULE).map {
            it.psi as JsgfRule
        }
    }

    fun findRules(project: Project, key: String): List<JsgfRule> {
        val result: MutableList<JsgfRule> = ArrayList<JsgfRule>()
        val virtualFiles =
            FileTypeIndex.getFiles(JsgfFileType.INSTANCE, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val simpleFile = PsiManager.getInstance(project).findFile(virtualFile) as JsgfFile?
            if (simpleFile != null) {
                val properties = findRules(simpleFile)
                for (property in properties) {
                    if (key == "<${property.getKey()}>") {
                        result.add(property)
                    }
                }
            }
        }
        return result
    }

    fun findProperties(project: Project): List<JsgfRule> {
        println("begin----findProperties")
        val result = ArrayList<JsgfRule>()
        val virtualFiles =
            FileTypeIndex.getFiles(JsgfFileType.INSTANCE, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val jsgfFile = PsiManager.getInstance(project).findFile(virtualFile!!) as JsgfFile?
            if (jsgfFile != null) {
                val properties = PsiTreeUtil.getChildrenOfType(jsgfFile, JsgfRule::class.java) ?: return result
                result.addAll(properties)
            }
        }
        return result
    }

    fun findDocumentationComment(property: JsgfRule): String {
        val result: MutableList<String> = LinkedList()
        var element: PsiElement = property.prevSibling
        while (element is PsiComment || element is PsiWhiteSpace) {
            if (element is PsiComment) {
                val commentText = element.getText().replaceFirst("[!# ]+".toRegex(), "")
                result.add(commentText)
            }
            element = element.prevSibling
        }
        return StringUtil.join(Lists.reverse(result), "\n ")
    }
}