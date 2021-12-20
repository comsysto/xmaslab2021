package com.comsysto.xmaslab2021.offer

import org.springframework.stereotype.Service

@Service
class OfferService(private val offers: OfferRepository) {
    fun allOffers(): List<Offer> = offers.findAll()
    fun saveOffer(offer: Offer) = offers.save(offer)
}