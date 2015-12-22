package model.classes

import org.scalatest.FlatSpec

class HangulTest extends FlatSpec {

    def h = new Hangul("231")
    val oh = 50724.toChar
    val ppan = 48740.toChar
    val oppan = oh.toString + ppan.toString


    "stringToInt" should "convert a string to a list of ints" in {
        assert(h.stringToInt(oppan) == List(50724, 48740))
    }

    "blockToInt" should "convert a string to its int value/unicode" in {
        assert(h.charToInt(oh) == 50724)
    }


    "lead" should "have 19 jamos" in {
        val leadLength = h.lead.length
        assert(leadLength == 19)
    }

    "vowels" should "have 21 jamos" in {
        val vowelLength = h.vowels.length
        assert(vowelLength == 21)
    }

    "tail" should "have 28 jamos" in {
        val tailLength = h.tail.length
        assert(tailLength == 28)
    }

    "romBlock" should "romanize a block" in {
        assert(h.romBlock(oh) == "o")
        print(h.romBlock(oh))
    }
}
