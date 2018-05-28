package com.scabher.restaurant.model

data class Table(private val id: Int, private val Orders: List<Order>,
                 private val name: String, private val Description: String) {

    fun getTotal() : Float {
        var total = 0.0
        for (order in Orders) {
            total += order.plateId
        }
    }
}