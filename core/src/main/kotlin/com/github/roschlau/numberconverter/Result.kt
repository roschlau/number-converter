package com.github.roschlau.numberconverter

import arrow.core.Either

typealias Result<T> = Either<ErrorMessage, T>

class ErrorMessage(val msg: String)

fun <T> success(value: T): Result<T> = Either.right(value)
fun error(msg: String): Result<Nothing> =
    Either.left(ErrorMessage(msg))
