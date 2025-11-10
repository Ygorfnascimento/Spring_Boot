package com.mercadolivro.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.mercadolivro.enums.CustomerStatus
import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus = CustomerStatus.ATIVO,

    @OneToMany(mappedBy = "customer", cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.LAZY)
    @JsonIgnore
    var books: List<BookModel> = emptyList()
)
