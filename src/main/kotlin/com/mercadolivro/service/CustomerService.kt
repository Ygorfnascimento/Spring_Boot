package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.extension.toCustomerModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService
) {

    fun getAll(name: String?): List<CustomerModel> {
        return if (name != null) {
            customerRepository.findByNameContainingIgnoreCase(name)
        } else {
            customerRepository.findAll()
        }
    }

    fun create(customer: PostCustomerRequest) {
        val newCustomer = customer.toCustomerModel() // ✅ usa a extensão que já define status = ATIVO
        customerRepository.save(newCustomer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id)
            .orElseThrow { RuntimeException("Customer not found") }
    }

    fun update(id: Int, customer: PutCustomerRequest) {
        val existingCustomer = findById(id)
        customer.name?.let { existingCustomer.name = it }
        customer.email?.let { existingCustomer.email = it }
        customerRepository.save(existingCustomer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }
}
