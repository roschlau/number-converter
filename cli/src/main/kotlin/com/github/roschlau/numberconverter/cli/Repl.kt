package com.github.roschlau.numberconverter.cli

import java.io.BufferedReader
import java.io.PrintStream

/**
 * Starts a Read-Eval-Print-Loop.
 *
 * It prints a [prompt] to [print], reads the next line from [read], call [eval] with that line, print the result
 * to [print] and then starts over again.
 *
 * The loop will exit if [read].[readLine()][BufferedReader.readLine] return one of the [exitKeywords] or null.
 */
fun repl(
    prompt: String = "> ",
    exitKeywords: Set<String> = setOf("exit"),
    read: BufferedReader = System.`in`.bufferedReader(),
    print: PrintStream = System.out,
    eval: (String) -> String
) {
    readLoop(prompt, read, print)
        .takeWhile { it !in exitKeywords }
        .map(eval)
        .forEach(print::println)
}

/**
 * Provides the Read-Loop of a REPL.
 *
 * It generates a sequence that will print the [prompt] to [print], read the next line from [read], trim it and return it
 * every time a new value is requested. The sequence will terminate if the read line is null (e.g. when EOF is reached)
 */
fun readLoop(
    prompt: String,
    read: BufferedReader,
    print: PrintStream
): Sequence<String> =
    generateSequence {
        print.print(prompt)
        read.readLine()?.trim()
    }
