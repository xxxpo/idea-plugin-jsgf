package com.xxxlin.jsgf

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.xxxlin.jsgf.psi.JsgfRule

object JsgfElementFactory {

    @JvmStatic
    fun createProperty(project: Project, name: String): JsgfRule {
        val file = createFile(project, name)
        return file.firstChild as JsgfRule
    }

    @JvmStatic
    fun createFile(project: Project, text: String): JsgfFile {
        val name = "dummy.jsgf"
        return PsiFileFactory.getInstance(project).createFileFromText(name, JsgfFileType.INSTANCE, text) as JsgfFile
    }
}