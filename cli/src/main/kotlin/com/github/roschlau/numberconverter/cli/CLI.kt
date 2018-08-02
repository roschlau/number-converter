package com.github.roschlau.numberconverter.cli

import arrow.core.andThen
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.roschlau.numberconverter.Result
import com.github.roschlau.numberconverter.convert


fun main(args: Array<String>) =
    Convert().main(args)

class Convert : CliktCommand() {

    private val interactive by option("-i", "--interactive").flag()
    private val number by argument().optional()

    override fun run() {
        when {
            interactive -> startInteractiveMode()
            number == null -> {
                println("No number given, starting in interactive mode.")
                println("If you want to show available command line options, call with --help")
                startInteractiveMode()
            }
            else -> println(show(convert(number!!)))
        }
    }
}

fun startInteractiveMode() =
    repl(eval = ::convert andThen ::show)

fun show(result: Result<String>): String = result.fold(
    ifLeft = { "ERROR: ${it.msg}" },
    ifRight = { it }
)
