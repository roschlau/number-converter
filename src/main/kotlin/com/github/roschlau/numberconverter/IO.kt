package com.github.roschlau.numberconverter

/**
 * Just for fun, trying to imitate the Haskell-way of doing referentially transparent side effects here.
 */
class IO<T>(val run: () -> T) {

    fun <R> map(transform: (T) -> R): IO<R> = IO { transform(run()) }
    fun <R> flatMap(t: (T) -> IO<R>): IO<R> = IO { t(run()).run() }

    companion object {

        fun readLine(): IO<String?> = IO { kotlin.io.readLine() }

        fun display(result: Result<String>): IO<Unit> = result.fold(
            ifLeft = { displayError(it.msg) },
            ifRight = { displayResult(it) }
        )
        fun displayResult(result: String): IO<Unit> = IO { println(result) }
        fun displayError(msg: String): IO<Unit> = IO { println("ERROR: $msg") }
    }
}