package com.github.roschlau.numberconverter.cli

import com.github.roschlau.numberconverter.Result
import com.github.roschlau.numberconverter.convert


fun main(args: Array<String>) {
    while (true) {
        print("> ")
        readLine()
            ?.let(::convert)
            ?.let(::display)
    }
}

fun display(result: Result<String>): Unit = result.fold(
    ifLeft = { displayError(it.msg) },
    ifRight = { displayResult(it) }
)
fun displayResult(result: String): Unit = println(result)
fun displayError(msg: String): Unit = println("ERROR: $msg")
