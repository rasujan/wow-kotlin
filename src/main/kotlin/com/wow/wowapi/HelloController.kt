package com.wow.wowapi

import com.wow.wowapi.DTO.HelloRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated

@RestController
@RequestMapping("/hello")
@Validated
class HelloWorldController {

    @GetMapping("/springboot")
    fun helloSpring(): String = "Hello Spring"
    
    @GetMapping
    fun helloWorld(): String = "Hello World"


    @PostMapping
    fun helloName(@Valid @RequestBody helloRequest: HelloRequest): ResponseEntity<String> {
        val res = "Hello ${helloRequest.name}, your age ${helloRequest.age}"

        return ResponseEntity.ok(res)
    }


}

