package com.comsysto.xmaslab2021.offer.dto

import com.comsysto.xmaslab2021.Location
import com.comsysto.xmaslab2021.offer.Offer
import java.math.BigDecimal

class OfferModel(val id: String, description: String, location: Location, price: BigDecimal, stock: Int) : OfferCreationModel(description, location, price, stock) {
    constructor(offer: Offer) : this(offer.offerId, offer.description, offer.location, offer.price, offer.stock)
}