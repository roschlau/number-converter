package com.github.roschlau.numberconverter.cli

import java.io.BufferedReader
import java.io.PrintStream

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
