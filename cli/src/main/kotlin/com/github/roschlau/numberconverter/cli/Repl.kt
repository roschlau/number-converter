package com.github.roschlau.numberconverter.cli

import java.io.BufferedReader
import java.io.PrintStream

fun repl(
    prompt: String = "> ",
    exitKeywords: Set<String> = setOf("exit"),
    `in`: BufferedReader = System.`in`.bufferedReader(),
    out: PrintStream = System.out,
    eval: (String) -> String
) {
    readLoop(prompt, exitKeywords, `in`, out)
        .map(eval)
        .forEach(out::println)
}

fun readLoop(
    prompt: String,
    exitKeywords: Set<String>,
    `in`: BufferedReader,
    out: PrintStream
): Sequence<String> =
    generateSequence {
        out.print(prompt)
        `in`.readLine()
            ?.trim()
            ?.takeUnless { it in exitKeywords }
    }
