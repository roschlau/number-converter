package com.github.roschlau.numberconverter

import com.github.roschlau.numberconverter.NumberType.*


fun convert(number: String): Result<String> =
    determineNumberType(number).map {
        when (it) {
            Roman -> RomanToDecimal.convert(number)
            Arabic -> DecimalToRoman.convert(number)
        }
    }

enum class NumberType { Roman, Arabic }

fun determineNumberType(number: String): Result<NumberType> = when {
    number.all { it in RomanToDecimal.validDigits } -> success(Roman)
    number.all { it in DecimalToRoman.validDigits } -> success(Arabic)
    else -> error("Not all digits of \"$number\" are in the valid range for any single number type")
}

val romanDigitValues = mapOf(
    'I' to 1,
    'V' to 5,
    'X' to 10,
    'L' to 50,
    'C' to 100,
    'D' to 500,
    'M' to 1000
)
