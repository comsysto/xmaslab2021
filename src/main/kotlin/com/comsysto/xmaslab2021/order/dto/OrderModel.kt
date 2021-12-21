package com.comsysto.xmaslab2021.order.dto

import com.comsysto.xmaslab2021.util.Location
import com.comsysto.xmaslab2021.offer.Offer
import com.comsysto.xmaslab2021.order.Order
import java.math.BigDecimal

class OrderModel(val orderId: Long?, offerId: String, deliveryLocation: Location, amount: Int) :
        OrderCreationModel(offerId, deliveryLocation, amount) {
    constructor(order: Order) : this(order.orderId, order.offerId, order.deliveryLocation, order.amount)
}