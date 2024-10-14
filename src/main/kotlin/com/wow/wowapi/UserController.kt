package com.wow.wowapi


import com.wow.wowapi.DTO.UserRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@Validated
class UserController {

    @PostMapping
    fun createUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<String> {
        return ResponseEntity.ok("User created successfully.")
    }
}
