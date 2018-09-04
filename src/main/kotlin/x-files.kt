
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
      override fun appendLine(x: String) = that.appendLine("    " + x)
    }
  }

  fun block(prefix: String = "", suffix : String = "", x : XWriter.() -> Unit) {
    + "$prefix{"
    indent().apply(x)
    + "}$suffix"
  }
}


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

val code = buildString {
  xWriter {
    +"// !$*UTF8*$!"
    block {
      "archiveVersion" to 1
      "classes" to { }
      "objectVersion" to 48

      val rootObjectId = "2CED25D21F75246200A6326D"
      "objects" to {

        rootObjectId to {

        }
      }

      "rootObject" to rootObjectId
    }
  }
}

println(code)




