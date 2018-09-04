package org.jonnyzzz.xcode

data class XPbxRef(
        val id: String,
        val comment: String? = null,
        val body: XPbxWriter.() -> Unit
)

interface XPbxHost {

}

private fun XWriter.extend() = object : XPbxWriter, XWriter by this { }

private fun commentOrEmpty(comment: String?, prefix : String = " ") = when(comment) {
  null -> ""
  else -> "$prefix/* $comment */"
}

interface XPbxWriter : XWriter {
  infix fun String.to(x: Any) = this.to(null, x)
  fun String.to(comment: String?, x: Any) = appendLine("$this = $x${commentOrEmpty(comment)};")

  infix fun String.to(x: XPbxWriter.() -> Unit) = this.to(null, x)
  fun String.to(comment: String? = null, x: XPbxWriter.() -> Unit) = block(prefix = "$this =${commentOrEmpty(comment)} ", suffix = ";", x = x)

  fun indent() : XPbxWriter = (this as XWriter).indent().extend()

  fun block(prefix: String = "", suffix : String = "", x : XPbxWriter.() -> Unit) {
    + "$prefix{"
    indent().apply(x)
    + "}$suffix"
  }

  fun comment(x: String) = appendLine(commentOrEmpty(prefix = "", comment = x))
}

fun XWriter.xPbx(f: XPbxHost.() -> XPbxRef) {
  val rootRef = object : XPbxHost {}.f()

  object : XPbxWriter, XWriter by this {}
          .apply {
            +"// !$*UTF8*$!"
            block {
              "archiveVersion" to 1
              "classes" to { }
              "objectVersion" to 50

              "objects" to {
                rootRef.id to {
                  //TODO: implement body
                  rootRef.body
                }
              }

              "rootObject".to(x = rootRef.id, comment = rootRef.comment)
            }
          }
}

