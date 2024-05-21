package com.xxxlin.jsgf

import com.google.common.collect.Lists
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.xxxlin.jsgf.psi.JsgfExp
import java.util.*

object JsgfUtil {

    /**
     * Searches the entire project for Simple language files with instances of the Simple property with the given key.
     *
     * @param project current project
     * @param key     to check
     * @return matching properties
     */
    fun findProperties(project: Project, key: String): List<JsgfExp> {
        val result = ArrayList<JsgfExp>()
        val virtualFiles =
            FileTypeIndex.getFiles(JsgfFileType.INSTANCE, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val simpleFile = PsiManager.getInstance(project).findFile(virtualFile!!) as JsgfFile?
            if (simpleFile != null) {
                val properties = PsiTreeUtil.getChildrenOfType(
                    simpleFile,
                    JsgfExp::class.java
                )
                if (properties != null) {
                    for (property: JsgfExp in properties) {
                        if (key == property.getKey()) {
                            result.add(property)
                        }
                    }
                }
            }
        }
        return result
    }

    fun findProperties(project: Project): List<JsgfExp> {
        val result = ArrayList<JsgfExp>()
        val virtualFiles =
            FileTypeIndex.getFiles(JsgfFileType.INSTANCE, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val simpleFile = PsiManager.getInstance(project).findFile(virtualFile!!) as JsgfFile?
            if (simpleFile != null) {
                val properties = PsiTreeUtil.getChildrenOfType(simpleFile, JsgfExp::class.java) ?: return result
                result.addAll(properties)
            }
        }
        return result
    }

    fun findDocumentationComment(property: JsgfExp): String {
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