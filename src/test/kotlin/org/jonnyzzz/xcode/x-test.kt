package org.jonnyzzz.xcode

import org.junit.Test

class XTest {

  @Test
  fun test() {
    val code = buildString {
      xWriter {
        xPbx()
      }
    }

    println(code)
  }

}

