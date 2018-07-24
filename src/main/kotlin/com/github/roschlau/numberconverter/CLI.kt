package com.github.roschlau.numberconverter


fun main(args: Array<String>) {
    while (true) {
        print("> ")
        IO.readLine()
            .map { it!! }
            .map(::convert)
            .flatMap(IO.Companion::display)
            .run()
    }
}