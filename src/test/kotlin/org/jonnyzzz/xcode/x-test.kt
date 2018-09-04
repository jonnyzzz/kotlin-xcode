package org.jonnyzzz.xcode

import org.junit.Test

class XTest {

  @Test
  fun test() {
    val code = buildString {
      xWriter {
        xPbx {
          XPbxRef(id = "2CED25D21F75246200A6326D", comment = "aaaa") {}
        }
      }
    }
    println(code)
  }
}

