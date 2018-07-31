package com.github.roschlau.numberconverter.web.api

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.HttpHeaders
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Application.main() {
    install(DefaultHeaders) {
        header(HttpHeaders.Server, "Number Converter Web API")
    }
    install(CallLogging)
    install(Routing) {
        get("/health") {
            call.respondText("Hello there")
        }
    }
}
