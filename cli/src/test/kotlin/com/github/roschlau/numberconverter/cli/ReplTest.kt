package com.github.roschlau.numberconverter.cli

import com.github.roschlau.numberconverter.times
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ReplTest {

    @Test
    fun `test repl`() {
        val userInput = listOf("one", "two", "some unusual exit keyword", "three")
        val prompt = "$ "

        val collector = ByteArrayOutputStream()

        repl(
            prompt = prompt,
            exitKeywords = setOf("some unusual exit keyword"),
            read = bufferedReader(userInput),
            print = PrintStream(collector),
            eval = { it }
        )

        assertEquals(
            prompt +
                "one" + System.lineSeparator() + prompt +
                "two" + System.lineSeparator() + prompt,
            collector.toString()
        )
    }

    @Test
    fun `read loop reads values and terminates on EOF`() {
        val userInput = listOf("one", "two")
        val prompt = "> "

        val collector = ByteArrayOutputStream()

        val read = readLoop(
            prompt = prompt,
            read = bufferedReader(userInput),
            print = PrintStream(collector)
        )
        assertEquals(userInput, read.toList())
    }

    @Test
    fun `read loop prints prompt only when next value is requested`() {
        val userInput = listOf("one", "two", "three", "four")
        val prompt = "> "

        val collector = ByteArrayOutputStream()

        val read = readLoop(
            prompt = prompt,
            read = bufferedReader(userInput),
            print = PrintStream(collector)
        )

        // Nothing should have happened yet
        assertEquals("", collector.toString())

        // Consume exactly two lines from the read loop
        read.take(2).toList()

        // Prompt should have been printed twice
        assertEquals(prompt * 2, collector.toString())
    }

}

fun bufferedReader(lines: List<String>) =
    lines.joinToString(System.lineSeparator()).reader().buffered()
