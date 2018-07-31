package com.github.roschlau.numberconverter.cli

/**
 * Just for fun, trying to imitate the Haskell-way of doing referentially transparent side effects here.
 */
class IO<T>(val run: () -> T) {

    fun <R> map(transform: (T) -> R): IO<R> = IO { transform(run()) }
    fun <R> flatMap(t: (T) -> IO<R>): IO<R> = IO { t(run()).run() }

}

fun readLine(): IO<String?> = IO { kotlin.io.readLine() }
fun println(msg: String = "") = IO { kotlin.io.println(msg) }
