package com.github.roschlau.numberconverter.cli

import java.io.BufferedReader
import java.io.PrintStream

/**
 * Starts a Read-Eval-Print-Loop.
 *
 * It prints a [prompt] to [out], read the next line from [in], call [eval] with that line, print the result to [out]
 * and then starts over again.
 *
 * The loop will exit if the line read from [in] equals one of the [exitKeywords].
 */
fun repl(
    prompt: String = "> ",
    exitKeywords: Set<String> = setOf("exit"),
    `in`: BufferedReader = System.`in`.bufferedReader(),
    out: PrintStream = System.out,
    eval: (String) -> String
) {
    readLoop(prompt, `in`, out)
        .takeWhile { it !in exitKeywords }
        .map(eval)
        .forEach(out::println)
}

/**
 * Provides the Read-Loop of a REPL.
 *
 * It generates a sequence that will print the [prompt] to [out], read the next line from [in], trim it and return it
 * every time a new value is requested. The sequence will terminate if the read line is null (e.g. when EOF is reached)
 */
fun readLoop(
    prompt: String,
    `in`: BufferedReader,
    out: PrintStream
): Sequence<String> =
    generateSequence {
        out.print(prompt)
        `in`.readLine()
            ?.trim()
    }
