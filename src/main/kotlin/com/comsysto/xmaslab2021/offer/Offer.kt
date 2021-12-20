package com.comsysto.xmaslab2021.offer

import com.comsysto.xmaslab2021.Location
import java.math.BigDecimal
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Offer(
    @Id val offerId: String,
    val description: String,
    @Embedded
    val location: Location,
    val price: BigDecimal,
    val stock: Int
    )