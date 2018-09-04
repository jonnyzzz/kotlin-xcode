package org.jonnyzzz.xcode

import org.junit.Test

class XTest {

  @Test
  fun test() {
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
  }

}

