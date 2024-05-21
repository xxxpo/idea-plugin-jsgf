package com.xxxlin.jsgf

import com.xxxlin.jsgf.psi.JsgfRule
import com.xxxlin.jsgf.psi.JsgfTypes

object JsgfPsiImplUtil {

    @JvmStatic
    fun getKey(element: JsgfRule): String? {
        val keyNode = element.node.findChildByType(JsgfTypes.RULE_NAME) ?: return null
        return keyNode.text.replace("<", "").replace(">", "")
    }

    @JvmStatic
    fun getValue(element: JsgfRule): String? {
        val valueNode = element.node.findChildByType(JsgfTypes.RULE_NAME) ?: return null
        return valueNode.text.trim()
    }

}