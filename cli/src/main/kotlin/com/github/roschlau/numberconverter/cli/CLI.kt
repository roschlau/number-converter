package com.github.roschlau.numberconverter.cli

import arrow.core.andThen
import com.github.roschlau.numberconverter.Result
import com.github.roschlau.numberconverter.convert


fun main(args: Array<String>) {
    repl(handleInput = ::convert andThen ::display)
}

inline fun repl(prompt: String = "> ", handleInput: (String) -> Unit) {
    while (true) {
        print(prompt)
        readLine()?.let(handleInput)
    }
}

fun display(result: Result<String>): Unit = result.fold(
    ifLeft = { displayError(it.msg) },
    ifRight = { displayResult(it) }
)
fun displayResult(result: String): Unit = println(result)
fun displayError(msg: String): Unit = println("ERROR: $msg")
