package com.github.roschlau.numberconverter


object DecimalToRoman {

    val validDigits = '0'..'9'

    fun convert(decimalNumber: String): String =
        convert(decimalNumber.toInt())

    tailrec fun convert(number: Int, appendTo: String = ""): String = when (number) {
        0 -> appendTo
        else -> {
            // Find the biggest roman digit that fits into the given number and its value
            val (romanDigit, value) = romanDigitValues.entries
                .sortedByDescending { it.value }
                .first { it.value <= number }
            // Calculate how often the roman digit has to be repeated
            val repeat = number / value
            // Calculate what's left for the smaller roman digits
            val remainder = number % value
            // Recurse to calculate the remaining digits
            convert(remainder, appendTo + romanDigit * repeat)
        }
    }
}

operator fun Char.times(times: Int) = String(CharArray(times) { this })