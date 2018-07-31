package com.github.roschlau.numberconverter.cli

import com.github.roschlau.numberconverter.Result
import com.github.roschlau.numberconverter.convert


fun main(args: Array<String>) {
    while (true) {
        print("> ")
        readLine()
            .map { it!! }
            .map(::convert)
            .flatMap(::display)
            .run()
    }
}

fun display(result: Result<String>): IO<Unit> = result.fold(
    ifLeft = { displayError(it.msg) },
    ifRight = { displayResult(it) }
)
fun displayResult(result: String): IO<Unit> = println(result)
fun displayError(msg: String): IO<Unit> = println("ERROR: $msg")
