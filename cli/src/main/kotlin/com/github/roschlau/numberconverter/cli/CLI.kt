package com.github.roschlau.numberconverter.cli

import arrow.core.andThen
import com.github.roschlau.numberconverter.Result
import com.github.roschlau.numberconverter.convert


fun main(args: Array<String>) {
    repl(eval = ::convert andThen ::show)
}

fun repl(
    prompt: String = "> ",
    exitKeywords: Set<String> = setOf("exit"),
    eval: (String) -> String
) {
    readLoop(prompt, exitKeywords)
        .map(eval)
        .forEach(::println)
}

fun readLoop(prompt: String, exitKeywords: Set<String>): Sequence<String> =
    generateSequence {
        print(prompt)
        readLine()?.takeUnless { it in exitKeywords }
    }

fun show(result: Result<String>): String = result.fold(
    ifLeft = { "ERROR: ${it.msg}" },
    ifRight = { it }
)
