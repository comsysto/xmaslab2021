package com.comsysto.xmaslab2021.offer.dto

import com.comsysto.xmaslab2021.Location
import java.math.BigDecimal

open class OfferCreationModel (val description: String, val location: Location, val price: BigDecimal, val stock: Int)