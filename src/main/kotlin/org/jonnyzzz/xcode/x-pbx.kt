package org.jonnyzzz.xcode

interface XPbxWriter {

}


fun XWriter.xPbx(f: XPbxWriter.() -> Unit) {
  +"// !$*UTF8*$!"
  block {
    "archiveVersion" to 1
    "classes" to { }
    "objectVersion" to 50

    val rootObjectId = "2CED25D21F75246200A6326D"
    "objects" to {

      rootObjectId to {

      }
    }

    "rootObject" to rootObjectId
  }
}

