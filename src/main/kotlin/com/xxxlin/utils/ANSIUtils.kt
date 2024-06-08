package com.xxxlin.utils

/**
 * 无功能说明
 * Date:    2020年03月06日 8:04 下午
 *
 * @author xiaolin
 * @version 0.1
 */
object ANSIUtils {

    /**
     * 是否使用ANSI格式输
     */
    private const val ENABLE_ANSI: Boolean = true

    const val WHITE: Int = 30 // 白色
    const val WHITE_BACKGROUND: Int = 40 // 白色背景
    const val RED: Int = 31 // 红色
    const val RED_BACKGROUND: Int = 41 // 红色背景
    const val GREEN: Int = 32 // 绿色
    const val GREEN_BACKGROUND: Int = 42 // 绿色背景
    const val YELLOW: Int = 33 // 黄色
    const val YELLOW_BACKGROUND: Int = 43 // 黄色背景
    const val BLUE: Int = 34 // 蓝色
    const val BLUE_BACKGROUND: Int = 44 // 蓝色背景
    const val MAGENTA: Int = 35 // 品红（洋红）
    const val MAGENTA_BACKGROUND: Int = 45 // 品红背景
    const val CYAN: Int = 36 // 蓝绿
    const val CYAN_BACKGROUND: Int = 46 // 蓝绿背景
    const val BLACK: Int = 37 // 黑色
    const val BLACK_BACKGROUND: Int = 47 // 黑色背景

    const val BOLD: Int = 1 // 粗体
    const val ITATIC: Int = 3 // 斜体
    const val UNDERLINE: Int = 4 // 下划线
    const val REVERSE: Int = 7 // 反转

    private fun format(txt: String, vararg codes: Int): String {
        if (!ENABLE_ANSI || codes.isEmpty()) {
            return txt
        }
        val sb = StringBuilder()
        for (code in codes) {
            sb.append(code).append(";")
        }
        var string = sb.toString()
        if (string.endsWith(";")) {
            string = string.substring(0, string.length - 1)
        }
        return 27.toChar().toString() + "[" + string + "m" + txt + 27.toChar() + "[0m"
    }

    class Formatter {

        private val sb = StringBuilder()

        fun print(txt: String, vararg codes: Int): Formatter {
            sb.append(format(txt, *codes))
            return this
        }

        override fun toString(): String {
            return sb.toString()
        }
    }
}
