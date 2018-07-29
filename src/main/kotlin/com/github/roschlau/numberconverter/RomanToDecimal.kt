package com.github.roschlau.numberconverter


object RomanToDecimal {

    val validDigits by lazy { romanDigitValues.keys }

    fun convert(romanNumber: String): String =
        romanNumber
            .let(::mapRomanToValues)
            .let(::applySubtractionRule)
            .sum()
            .toString()

}

private fun mapRomanToValues(romanNumber: String): List<Int> = romanNumber
    .map { romanDigitValues[it] ?: throw IllegalArgumentException("Invalid roman digit: $it") }

private fun applySubtractionRule(values: List<Int>) = when {
    values.isEmpty() -> values
    else -> values
        .dropLast(1)
        .mapIndexed { i, it -> if (it < values[i + 1]) -it else it }
        .plus(values.last())
}
