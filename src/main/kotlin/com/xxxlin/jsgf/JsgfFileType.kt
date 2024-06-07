package com.xxxlin.jsgf

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class JsgfFileType : LanguageFileType(JsgfLanguage.INSTANCE) {

    companion object {
        val INSTANCE = JsgfFileType()
    }

    override fun getName(): String {
        return "JSGF File"
    }

    override fun getDescription(): String {
        return "JSpeech Grammar Format"
    }

    override fun getDefaultExtension(): String {
        return "JSGF"
    }

    override fun getIcon(): Icon {
        return JsgfIcons.FILE
    }

}
