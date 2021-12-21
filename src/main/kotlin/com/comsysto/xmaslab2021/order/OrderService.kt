package com.comsysto.xmaslab2021.order

import org.springframework.stereotype.Service

@Service
class OrderService(private val orders: OrderRepository) {
    fun allOrders(): List<Order> = orders.findAll()
    fun saveOrder(order: Order) = orders.save(order)
}