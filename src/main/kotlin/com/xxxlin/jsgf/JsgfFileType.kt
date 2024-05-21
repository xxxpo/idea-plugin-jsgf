package com.xxxlin.jsgf

import com.intellij.openapi.fileTypes.LanguageFileType
import org.jetbrains.annotations.NotNull
import javax.swing.Icon

class JsgfFileType : LanguageFileType(JsgfLanguage.INSTANCE) {

    companion object {
        val INSTANCE = JsgfFileType()
    }

    override fun getName(): String {
        return "Jsgf File"
    }

    @NotNull
    override fun getDescription(): String {
        return "Java Speech Grammar Format"
    }

    @NotNull
    override fun getDefaultExtension(): String {
        return "jsgf"
    }

    override fun getIcon(): Icon {
        return JsgfIcons.FILE
    }

}
