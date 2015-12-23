package model.classes

import org.scalatest.FlatSpec

class HangulTest extends FlatSpec {

    val oh = 50724.toChar
    val ppan = 48740.toChar
    var sentence: String = List(50724, 48740, 32, 44053, 45224, 49828, 53440, 51068, 10, 44053, 45224, 49828, 53440, 51068).map(u => u.toChar).mkString
    val oppan = oh.toString + ppan.toString
    def h = new Hangul(oppan)

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
    }

    "romanize" should "romanize a string" in {
        println(sentence)
        assert(h.romanize() == "obban")
        val line = new Hangul(sentence)
        assert(line.romanize() == "obban gangnamseutail" + "\n" + "gangnamseutail")
        println(line.romanize())
    }
}
