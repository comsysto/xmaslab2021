package com.comsysto.xmaslab2021.order

import com.comsysto.xmaslab2021.util.Location
import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Order(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val orderId: Long ?,
        val offerId: String,
        @Embedded
        val deliveryLocation: Location,
        val amount: Int
    )