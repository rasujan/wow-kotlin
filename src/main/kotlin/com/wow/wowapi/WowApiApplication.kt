package com.wow.wowapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WowApiApplication

fun main(args: Array<String>) {
    runApplication<WowApiApplication>(*args)
}
