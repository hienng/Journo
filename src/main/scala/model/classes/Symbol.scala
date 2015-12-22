package model.classes

import scala.collection.breakOut

abstract class Symbol(val lang: String){
    // free for additional default constructors etc.
    def stringToInt(input: String): List[Int] = input.map(s => s.toInt)(breakOut)
    def charToInt(input: Char): Int
}

