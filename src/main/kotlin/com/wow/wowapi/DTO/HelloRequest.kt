package com.wow.wowapi.DTO

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class HelloRequest(
    @field:NotBlank(message = "Name is required")
    @field:Size(max = 50, message = "Name must be less than 50 characters")
    @field:Size(min = 5, message = "Name must be at least 5 characters")
    val name: String,

    val age: Int,

    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email should be valid")
    val email: String,

    )