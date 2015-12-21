package model.classes

import model.traits.Romanize

class Hangul(val test: String, override val unicode: Int) extends Symbol("a", 2) with Romanize {
    override def romanize(u: Int, offset: Int, offsetMax: Int): String = "testROM"
}