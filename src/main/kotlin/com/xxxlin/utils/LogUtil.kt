package com.xxxlin.utils

/**
 * @author xiaolin
 * time 2021-12-30 20:24
 */
object LogUtil {

    private val LOG_ENABLE: Boolean = true

    fun log(tag: String?, content: String) {
        showLogWithLineNum(tag, content)
    }

    fun log(content: String) {
        showLogWithLineNum(null, content)
    }

    private fun showLogWithLineNum(tag: String?, content: String) {
        if (!LOG_ENABLE) {
            return
        }
        val stackTrace = getAutoJumpLogInfos()
        val location = stackTrace[0]
        val className = stackTrace[1]
        val newTag = if (tag.isNullOrEmpty()) "[$className]" else "[$tag]"

        var index = 0
        var size = 1024 * 3
        while (index < content.length) {
            if (content.length < (index + size)) {
                size = content.length - index
            }
            val subContent = content.substring(index, index + size)
            index += size
            print(location, newTag, subContent)
        }
    }

    private fun print(fileLine: String, tag: String, content: String) {
        val formatter = ANSIUtils.Formatter()
        formatter.print(tag, ANSIUtils.GREEN)
        formatter.print(" ")
        formatter.print(content)
        formatter.print(" ")
        formatter.print(fileLine, ANSIUtils.BLUE, ANSIUtils.ITATIC)
        println(formatter)
    }

    /**
     * 获取打印信息所在方法名，行号等信息
     *
     * @return
     */
    private fun getAutoJumpLogInfos(): Array<String> {
        val contents = arrayOf("", "", "")
        val stackTrace = Thread.currentThread().stackTrace
        if (stackTrace.size < 5) {
            return contents
        } else {
            val trace = stackTrace[4]
            // 文件名和行号
            contents[0] = "at ${trace.className}.${trace.methodName}(${trace.fileName}:${trace.lineNumber})"
            // 类名
            contents[1] = trace.className.substring(trace.className.lastIndexOf(".") + 1)
            // 方法
            contents[2] = "${trace.methodName}()"
            return contents
        }
    }

    fun printBits(n: Int) {
        for (i in 31 downTo 0) {
            if ((n shr i and 0x01) == 1) {
                print(1)
            } else {
                print(0)
            }
            if (i % 8 == 0 && i != 0) {
                print(" ")
            }
        }
        println()
    }
}
