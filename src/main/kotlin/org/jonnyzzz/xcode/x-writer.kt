package org.jonnyzzz.xcode


interface XWriter {
  fun appendLine()
  fun appendLine(x: String)

  operator fun String.unaryPlus() = appendLine(this)

  infix fun String.to(x: Int) = appendLine("$this = $x;")
  infix fun String.to(x: String) = appendLine("$this = $x;")
  infix fun String.to(x: XWriter.() -> Unit) = block(prefix = "$this = ", suffix = ";", x = x)
  fun String.to(comment: String, x: XWriter.() -> Unit) = block(prefix = "$this = /* $comment */ ", suffix = ";", x = x)

  fun comment(x: String) = appendLine("/* $x */")

  fun indent() : XWriter {
    val that = this
    return object : XWriter {
      override fun appendLine() = that.appendLine()
      override fun appendLine(x: String) = that.appendLine("    $x")
    }
  }

  fun block(prefix: String = "", suffix : String = "", x : XWriter.() -> Unit) {
    + "$prefix{"
    indent().apply(x)
    + "}$suffix"
  }
}

