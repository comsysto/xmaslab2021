package com.comsysto.xmaslab2021

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import javax.persistence.Entity
import javax.persistence.Id


// --- Domain ----
@Entity
data class Offer(@Id val offerId: String, val description: String)

// --- Database Repositories ---
@Repository
interface OfferRepository : JpaRepository<Offer, String>



// --- Services ---
@Service
class OfferService(private val offers: OfferRepository) {
    fun allOffers(): List<Offer> = offers.findAll()
    fun saveOffer(offer: Offer) = offers.save(offer)
}


// --- Rest ---

open class OfferCreationModel (val description: String)
class OfferModel(val id: String, description: String) : OfferCreationModel(description) {
    constructor(offer: Offer) : this(offer.offerId, offer.description)
}

@RestController
@RequestMapping("/offers")
class OfferController(private val offers: OfferService) {

    @PutMapping("/{offerId}")
    fun createOffer(@PathVariable offerId: String, @RequestBody offerCreation: OfferCreationModel){
        offers.saveOffer(Offer(offerId, offerCreation.description))
    }

    @GetMapping
    fun getAll(): List<OfferModel> = offers.allOffers().map { o -> OfferModel(o) }
}