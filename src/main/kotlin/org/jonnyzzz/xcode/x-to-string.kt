package org.jonnyzzz.xcode


fun StringBuilder.xWriter(f: XWriter.() -> Unit) {
  val sb = this
  object : XWriter {
    override fun appendLine() {
      sb.appendln()
    }

    override fun appendLine(x: String) {
      sb.appendln(x)
    }
  }.f()
}

