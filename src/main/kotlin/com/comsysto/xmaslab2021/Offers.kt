package com.comsysto.xmaslab2021

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Offer(@Id val offerId: String, val description: String)

@Repository
interface OfferRepository : JpaRepository<Offer, String>

@Service
class OfferService(private val offers: OfferRepository) {
    fun allOffers(): List<Offer> {
        return offers.findAll()
    }

    fun saveOffer(offer: Offer){
        offers.save(offer)
    }
}

@RestController
@RequestMapping("/offers")
class OfferController(private val offers: OfferService) {

    @PutMapping("/{offerId}")
    fun createOffer(@PathVariable offerId: String, @RequestBody offerDescription: String){
        offers.saveOffer(Offer(offerId, offerDescription))
    }

    @GetMapping
    fun getAll(): List<Offer> = offers.allOffers()
}