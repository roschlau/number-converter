package com.github.roschlau.numberconverter.web.api

import arrow.core.Either
import com.github.roschlau.numberconverter.convert
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Application.main() {
    install(DefaultHeaders) {
        header(HttpHeaders.Server, "Number Converter Web API")
    }
    install(CallLogging)

    install(Routing) {
        get("/convert/{number}") {
            val number = call.parameters["number"]
                ?: return@get call.respond(HttpStatusCode.BadRequest)
            val result = convert(number)
            when (result) {
                is Either.Right -> call.respondText(result.b)
                is Either.Left -> call.respond(HttpStatusCode.BadRequest, result.a.msg)
            }
        }

        get("/health") {
            call.respondText("Hello there")
        }
    }
}
