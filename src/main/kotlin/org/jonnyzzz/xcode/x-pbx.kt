package org.jonnyzzz.xcode

data class XPbxRef(
        val id: String,
        val comment: String? = null,
        val body: XPbxWriter.() -> Unit
)

interface XPbxWriter {

}


fun XWriter.xPbx(f: XPbxWriter.() -> XPbxRef) {

  val rootRef = object:XPbxWriter {}.f()

  +"// !$*UTF8*$!"
  block {
    "archiveVersion" to 1
    "classes" to { }
    "objectVersion" to 50

    val rootObjectId = "2CED25D21F75246200A6326D"
    "objects" to {

      rootRef.id to {
        //TODO: implement body
        rootRef.body
      }
    }

    "rootObject".to(x = rootRef.id, comment = rootRef.comment)
  }
}

