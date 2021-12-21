package com.comsysto.xmaslab2021.healthcheck

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    companion object {
        const val HEALTHY_MESSAGE = "I am healthy!"
    }

    @GetMapping("/healthCheck")
    fun healthCheck(): String {
        return HEALTHY_MESSAGE
    }

}