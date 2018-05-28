package com.scabher.restaurant.model

data class Plate(val id: Int, val name: String, val description: String,
                 val image: Int, val price: Float, val allergens: List<Allergen>?) {

}