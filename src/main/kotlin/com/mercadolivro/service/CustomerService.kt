package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository) {

    fun getAll(name: String?): List<CustomerModel> {
        return if (name != null) {
            customerRepository.findByNameContainingIgnoreCase(name)
        } else {
            customerRepository.findAll()
        }
    }

    fun create(customer: PostCustomerRequest) {
        val newCustomer = CustomerModel(name = customer.name, email = customer.email)
        customerRepository.save(newCustomer)
    }

    fun getCustomer(id: Int): CustomerModel {
        return customerRepository.findById(id)
            .orElseThrow { RuntimeException("Customer not found") }
    }

    fun update(id: Int, customer: PutCustomerRequest) {
        val existingCustomer = getCustomer(id)
        customer.name?.let { existingCustomer.name = it }
        customer.email?.let { existingCustomer.email = it }
        customerRepository.save(existingCustomer)
    }

    fun delete(id: Int) {
        val existingCustomer = getCustomer(id)
        customerRepository.delete(existingCustomer)
    }
}
