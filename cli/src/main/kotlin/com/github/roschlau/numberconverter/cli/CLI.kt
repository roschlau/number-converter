package com.github.roschlau.numberconverter.cli

import arrow.core.andThen
import com.github.roschlau.numberconverter.Result
import com.github.roschlau.numberconverter.convert


fun main(args: Array<String>) {
    repl(eval = ::convert andThen ::show)
}

fun show(result: Result<String>): String = result.fold(
    ifLeft = { "ERROR: ${it.msg}" },
    ifRight = { it }
)
