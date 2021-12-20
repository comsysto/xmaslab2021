package com.comsysto.xmaslab2021.order

import com.comsysto.xmaslab2021.order.dto.OrderCreationModel
import com.comsysto.xmaslab2021.order.dto.OrderModel
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(private val orders: OrderService) {

    @PostMapping
    fun createOrder(@RequestBody orderCreation: OrderCreationModel){
        orders.saveOrder(Order(null, orderCreation.offerId, orderCreation.deliveryLocation, orderCreation.amount))
    }

    @GetMapping
    fun getAll(): List<OrderModel> = orders.allOrders().map { o -> OrderModel(o) }
}