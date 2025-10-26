package com.mercadolivro.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class PostCustomerRequest(
    @field:NotBlank(message = "Name must not be blank")
    var name: String,

    @field:NotBlank(message = "Email must not be blank")
    @field:Email(message = "Email must be valid")
    var email: String
)
