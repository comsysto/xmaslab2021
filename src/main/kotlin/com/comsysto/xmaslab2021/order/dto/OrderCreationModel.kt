package com.comsysto.xmaslab2021.order.dto

import com.comsysto.xmaslab2021.util.Location
import java.math.BigDecimal

open class OrderCreationModel (val offerId: String, val deliveryLocation: Location, val amount: Int)