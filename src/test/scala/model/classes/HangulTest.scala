package model.classes

import org.scalatest.FlatSpec
/**
  * Created by Hien Nguyen on 21.12.2015.
  */
class HangulTest extends FlatSpec {

    behavior of "HangulTest"

    def h = new Hangul("231",2323)

    it should "romanize" in {
        val testRom = "testRom"
        assert(h.romanize(12,312,12) == "testROM")
    }

}
