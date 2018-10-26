package com.github.roschlau.numberconverter

operator fun String.times(times: Int) = Array(times) { this }.joinToString("")
