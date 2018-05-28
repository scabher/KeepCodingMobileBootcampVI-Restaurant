package com.scabher.restaurant.model

data class Plate(private val id: Int, private var name: String, private var description: String,
                 private val image: Int, private var price: Float, private var allergens: List<Allergen>?) {

}