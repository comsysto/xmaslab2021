package com.comsysto.xmaslab2021.offer.dto

import com.comsysto.xmaslab2021.Location
import com.comsysto.xmaslab2021.offer.Offer

class OfferModel(val id: String, description: String, location: Location) : OfferCreationModel(description, location) {
    constructor(offer: Offer) : this(offer.offerId, offer.description, offer.location)
}