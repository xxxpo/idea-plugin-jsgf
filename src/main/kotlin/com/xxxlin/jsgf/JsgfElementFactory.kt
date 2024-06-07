package com.xxxlin.jsgf

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementFactory
import com.intellij.psi.PsiFileFactory
import com.xxxlin.jsgf.psi.JsgfReferenceRuleName
import com.xxxlin.jsgf.psi.JsgfTypes

object JsgfElementFactory {

    @JvmStatic
    fun createProperty(project: Project, name: String): PsiElement {
        return PsiElementFactory
            .getInstance(project)
            .createDummyHolder(name, JsgfTypes.DEF_RULE_NAME_WRAPPER, null)
    }

    @JvmStatic
    fun createRuleName(project: Project, name: String): JsgfReferenceRuleName {
        val file = createFile(project, name)
        return file.firstChild as JsgfReferenceRuleName
    }

    @JvmStatic
    fun createFile(project: Project, text: String): JsgfFile {
        val name = "dummy.jsgf"
        return PsiFileFactory.getInstance(project).createFileFromText(name, JsgfFileType.INSTANCE, text) as JsgfFile
    }
}