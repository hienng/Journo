package model.classes

import model.traits.Romanize

class Hangul(in: String) extends Symbol("Hangul") with Romanize {

    val oh = 50724.toChar

    val UNICODE_OFFSET = 44032
    val UNICODE_MAX = 55215

    // Hangul are composed of letters, the Jamo 자모, in a systematic way.
    // Use of List instead Arrays, because List are immutable.
    val lead = List(
        "g" ,           // ᄀ
        "gg",           // ᄁ
        "n" ,           // ㄴ
        "d" ,           // ㄷ
        "dd",           // ㄸ
        "r" ,    	    // ᄅ
        "m" ,	        // ᄆ
        "b"	,           // ᄇ
        "bb",	        // ᄈ
        "s"	,           // ᄉ
        "ss",	        // ᄊ
        ""  ,           // ᄋ
        "j"	,           // ᄌ
        "jj",	        // ᄍ
        "c"	,           // ᄎ
        "k"	,           // ᄏ
        "t"	,           // ᄐ
        "p"	,           // ᄑ
        "h"	            // ᄒ
    )

    val vowels = List(
    	"a"  ,	        //ᅡ
    	"ae" ,	        //ᅢ
    	"ya" ,	        //ᅣ
    	"yae",	        //ᅤ
    	"yo" ,	        //ᅥ
    	"e"	 ,          //ᅦ
    	"yeo",	        //ᅧ
    	"ye" ,	        //ᅨ
    	"o"	 ,          //ᅩ
    	"wa" ,	        //ᅪ
    	"wae",	        //ᅫ
    	"oe" ,	        //ᅬ
    	"yo" ,	        //ᅭ
    	"u"	 ,          //ᅮ
    	"weo",	        //ᅯ
    	"we" ,	        //ᅰ
    	"wi" ,	        //ᅱ
    	"yu" ,	        //ᅲ
    	"eu" ,	        //ᅳ
    	"wi" ,	        //ᅴ
    	"i"             //ᅵ
    )

    val tail = List(
        ""  ,           // empty jamo
        "g" ,           // ᄀ
        "gg",           // ᄁ
        "gs",           // ㄳ
        "n" ,           // ㄴ
        "nj",           // ㄵ
        "nh",           // ㄶ
        "d" ,           // ㄷ
        "l" ,           // ㄹ
        "lg",           // ㄺ
        "lm",           // ㄻ
        "lb",           // ㄼ
        "ls",           // ㄽ
        "lt",           // ㄾ
        "lp",           // ㄿ
        "lh",           // ㅀ
        "m" ,	        // ᄆ
        "b"	,           // ᄇ
        "bs",           // ㅄ
        "s"	,           // ᄉ
        "ss",	        // ᄊ
        "ng",           // ㅇ
        "j"	,           // ᄌ
        "c"	,           // ᄎ
        "k"	,           // ᄏ
        "t"	,           // ᄐ
        "p"	,           // ᄑ
        "h"	            // ᄒ
    )

    override def charToInt(input: Char): Int = input.toInt

    override def romanize(): String = ???

    def romBlock(input: Char): String = {
        val block = charToInt(input)
        val isHangul = block >= UNICODE_OFFSET && block <= UNICODE_MAX
        if (isHangul) {
            val hangulCodePoint = block
            val codePoint = hangulCodePoint - UNICODE_OFFSET
            var unicodeOffset = codePoint

            val tailOffset = unicodeOffset % tail.length
            unicodeOffset -= tailOffset
            unicodeOffset /= tail.length

            val vowelOffset = unicodeOffset % vowels.length
            unicodeOffset -= vowelOffset
            unicodeOffset /= vowels.length

            val leadOffset = unicodeOffset

            // return
            lead(leadOffset) + vowels(vowelOffset) + tail(tailOffset)
        } else {
            // return
            input.toString
        }
    }


}