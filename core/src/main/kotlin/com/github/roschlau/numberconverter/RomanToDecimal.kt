package com.github.roschlau.numberconverter


object RomanToDecimal {

    val validDigits by lazy { romanNumerals.keys }

    fun convert(romanNumber: String): String =
        romanNumber
            .let(::mapRomanToValues)
            .let(::applySubtractionRule)
            .sum()
            .toString()

}

private fun mapRomanToValues(romanNumber: String): List<Int> = romanNumber
    .map { romanNumerals[it] ?: throw IllegalArgumentException("Invalid roman digit: $it") }

private fun applySubtractionRule(values: List<Int>) = when {
    values.isEmpty() -> values
    else -> values
        .dropLast(1)
        .mapIndexed { i, it -> if (it < values[i + 1]) -it else it }
        .plus(values.last())
}

val romanNumerals = mapOf(
    'I' to 1,
    'V' to 5,
    'X' to 10,
    'L' to 50,
    'C' to 100,
    'D' to 500,
    'M' to 1000
)
