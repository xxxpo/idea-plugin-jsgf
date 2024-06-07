package com.xxxlin.jsgf

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class JsgfFile(
    viewProvider: FileViewProvider
) : PsiFileBase(viewProvider, JSGFLanguage.INSTANCE) {

    override fun getFileType(): FileType {
        return JsgfFileType.INSTANCE
    }

    override fun toString(): String {
        return "JSGF File"
    }

}