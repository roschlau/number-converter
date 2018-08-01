package com.github.roschlau.numberconverter.cli

import arrow.core.andThen
import com.github.roschlau.numberconverter.Result
import com.github.roschlau.numberconverter.convert
import java.io.BufferedReader
import java.io.PrintStream


fun main(args: Array<String>) {
    repl(eval = ::convert andThen ::show)
}

fun repl(
    prompt: String = "> ",
    exitKeywords: Set<String> = setOf("exit"),
    readFrom: BufferedReader = System.`in`.bufferedReader(),
    printTo: PrintStream = System.out,
    eval: (String) -> String
) {
    readLoop(prompt, exitKeywords, readFrom)
        .map(eval)
        .forEach(printTo::println)
}

fun readLoop(
    prompt: String,
    exitKeywords: Set<String>,
    readFrom: BufferedReader
): Sequence<String> =
    generateSequence {
        print(prompt)
        readFrom.readLine()
            ?.trim()
            ?.takeUnless { it in exitKeywords }
    }

fun show(result: Result<String>): String = result.fold(
    ifLeft = { "ERROR: ${it.msg}" },
    ifRight = { it }
)
