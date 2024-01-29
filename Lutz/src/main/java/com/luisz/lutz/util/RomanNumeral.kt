package com.luisz.lutz.util

enum class RomanNumeral(val text: String) {
    I("I"),
    II("II"),
    III("III"),
    IV("IV"),
    V("V"),
    VI("VI"),
    VII("VII"),
    VIII("VIII"),
    IX("IX"),
    X("X");

    companion object {
        fun fromNumber(n: Int): RomanNumeral?{
            return when(n){
                1 -> I
                2 -> II
                3 -> III
                4 -> IV
                5 -> V
                6 -> VI
                7 -> VII
                8 -> VIII
                9 -> IX
                10 -> X
                else -> null
            }
        }
    }
}