package com.mercadolivro.controller.request

import jakarta.validation.constraints.Email

data class PutCustomerRequest(
    var name: String? = null,

    @field:Email(message = "Email must be valid")
    var email: String? = null
)
