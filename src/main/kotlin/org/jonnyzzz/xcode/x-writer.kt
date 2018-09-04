package org.jonnyzzz.xcode


interface XWriter {
  fun appendLine()
  fun appendLine(x: String)

  operator fun String.unaryPlus() = appendLine(this)
  operator fun String.unaryMinus() = appendLine(this)
}

fun XWriter.indent() : XWriter {
  val that = this
  return object : XWriter {
    override fun appendLine() = that.appendLine()
    override fun appendLine(x: String) = that.appendLine("    $x")
  }
}
