package com.comsysto.xmaslab2021.offer

import com.comsysto.xmaslab2021.Location
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Offer(
    @Id val offerId: String,
    val description: String,
    val location: Location
    )