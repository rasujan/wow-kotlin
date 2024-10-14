package com.wow.wowapi.DTO

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Invalid email format")
    val email: String
)
