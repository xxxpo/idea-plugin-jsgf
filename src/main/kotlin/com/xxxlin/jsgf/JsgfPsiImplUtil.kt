package com.xxxlin.jsgf

import com.xxxlin.jsgf.psi.JsgfExp
import com.xxxlin.jsgf.psi.JsgfTypes

object JsgfPsiImplUtil {

    @JvmStatic
    fun getKey(element: JsgfExp): String? {
        val keyNode = element.node.findChildByType(JsgfTypes.VARIABLE_NAME) ?: return null
        return keyNode.text.replace("<", "").replace(">", "")
    }

    @JvmStatic
    fun getValue(element: JsgfExp): String? {
        val valueNode = element.node.findChildByType(JsgfTypes.VARIABLE_NAME) ?: return null
        return valueNode.text.trim()
    }

}