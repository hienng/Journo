package model.classes

import model.traits.Romanize

class Hangul(in: String) extends Symbol("Hangul") with Romanize {

    val oh = 50724.toChar

    val UNICODE_OFFSET = 44032
    val UNICODE_MAX = 55215

    // Hangul are composed of letters, the Jamo 자모, in a systematic way.
    // Use of List instead Arrays, because List are immutable.
    // Transcription Rules for Revised Romanization
    // TODO: add Transcription Rules of RR: https://en.wikipedia.org/wiki/Revised_Romanization_of_Korean
    // TODO: Add McCuneReischauer Transcription Rules
    val leadRR = List(
        "g" ,           // ᄀ
        "kk",           // ᄁ
        "n" ,           // ㄴ
        "d" ,           // ㄷ
        "tt",           // ㄸ
        "r" ,    	    // ᄅ
        "m" ,	        // ᄆ
        "b"	,           // ᄇ
        "pp",	        // ᄈ
        "s"	,           // ᄉ
        "ss",	        // ᄊ
        ""  ,           // ᄋ
        "j"	,           // ᄌ
        "jj",	        // ᄍ
        "ch",           // ᄎ
        "k"	,           // ᄏ
        "t"	,           // ᄐ
        "p"	,           // ᄑ
        "h"	            // ᄒ
    )

    val vowelsRR = List(
    	"a"  ,	        //ᅡ
    	"ae" ,	        //ᅢ
    	"ya" ,	        //ᅣ
    	"yae",	        //ᅤ
    	"eo" ,	        //ᅥ
    	"e"	 ,          //ᅦ
    	"yeo",	        //ᅧ
    	"ye" ,	        //ᅨ
    	"o"	 ,          //ᅩ
    	"wa" ,	        //ᅪ
    	"wae",	        //ᅫ
    	"oe" ,	        //ᅬ
    	"yo" ,	        //ᅭ
    	"u"	 ,          //ᅮ
    	"wo",	        //ᅯ
    	"we" ,	        //ᅰ
    	"wi" ,	        //ᅱ
    	"yu" ,	        //ᅲ
    	"eu" ,	        //ᅳ
    	"ui" ,	        //ᅴ
    	"i"             //ᅵ
    )

    val tailRR = List(
        ""  ,           // empty jamo
        "k" ,           // ᄀ
        "k" ,           // ᄁ
        "gs",           // ㄳ
        "n" ,           // ㄴ
        "nj",           // ㄵ
        "nh",           // ㄶ
        "t" ,           // ㄷ
        "l" ,           // ㄹ
        "lg",           // ㄺ
        "lm",           // ㄻ
        "lb",           // ㄼ
        "ls",           // ㄽ
        "lt",           // ㄾ
        "lp",           // ㄿ
        "lh",           // ㅀ
        "m" ,	        // ᄆ
        "p"	,           // ᄇ
        "bs",           // ㅄ
        "t"	,           // ᄉ
        "t",	        // ᄊ
        "ng",           // ㅇ
        "j"	,           // ᄌ
        "t"	,           // ᄎ
        "k"	,           // ᄏ
        "t"	,           // ᄐ
        "p"	,           // ᄑ
        "h"	            // ᄒ
    )

    override def charToInt(input: Char): Int = input.toInt

    override def romanize(): String = in.map(c => romBlock(c)).mkString

    // modification of http://gernot-katzers-spice-pages.com/var/korean_hangul_unicode.html
    def romBlock(input: Char): String = {
        val block = charToInt(input)
        val isHangul = block >= UNICODE_OFFSET && block <= UNICODE_MAX
        if (isHangul) {
            val hangulCodePoint = block
            val codePoint = hangulCodePoint - UNICODE_OFFSET
            var unicodeOffset = codePoint

            val tailOffset = unicodeOffset % tailRR.length
            unicodeOffset -= tailOffset
            unicodeOffset /= tailRR.length

            val vowelOffset = unicodeOffset % vowelsRR.length
            unicodeOffset -= vowelOffset
            unicodeOffset /= vowelsRR.length

            val leadOffset = unicodeOffset

            // return
            leadRR(leadOffset) + vowelsRR(vowelOffset) + tailRR(tailOffset)
        } else {
            // return
            input.toString
        }
    }


}