package com.comsysto.xmaslab2021

import javax.persistence.Embeddable

@Embeddable
class Location(longitude:Double, latitude: Double) {
    val longitude: Double = longitude
    val latitude: Double  = latitude
}