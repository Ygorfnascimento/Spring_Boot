package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        return if (name != null) {
            customers.filter { it.name.contains(name, ignoreCase = true) }
        } else {
            customers
        }
    }

    fun create(customer: PostCustomerRequest) {
        val id = if (customers.isEmpty()) {
            "1"
        } else {
            (customers.last().id.toInt() + 1).toString()
        }

        customers.add(CustomerModel(id, customer.name, customer.email))
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.first { it.id == id }
    }

    fun update(id: String, customer: PutCustomerRequest) {
        customers.firstOrNull { it.id == id }?.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }
}
