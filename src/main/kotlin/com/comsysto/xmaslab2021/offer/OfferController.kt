package com.comsysto.xmaslab2021.offer

import com.comsysto.xmaslab2021.offer.dto.OfferCreationModel
import com.comsysto.xmaslab2021.offer.dto.OfferModel
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/offers")
class OfferController(private val offers: OfferService) {

    @PutMapping("/{offerId}")
    fun createOffer(@PathVariable offerId: String, @RequestBody offerCreation: OfferCreationModel){
        offers.saveOffer(Offer(offerId, offerCreation.description, offerCreation.location, offerCreation.price, offerCreation.stock))
    }

    @GetMapping
    fun getAll(): List<OfferModel> = offers.allOffers().map { o -> OfferModel(o) }
}