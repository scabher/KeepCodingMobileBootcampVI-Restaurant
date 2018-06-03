package com.scabher.restaurant.model

import java.io.Serializable

data class Table(val id: Int, private var orders: List<Order>, val name: String, val description: String): Serializable {
    private var total: Float = 0.0f

    fun getTotal() : Float {
        return total
    }

    fun addOrder(order: Order) {
        orders = orders.plus(order)
        total += order.plate.price
    }

    fun removeOrder(order: Order) {
        orders = orders.minus(order)
        total += order.plate.price
    }

    fun updateOrder(plateId: Int, notes: String) {
        var order = orders.find { order -> order.plate.id == plateId }

        order?.let {
            it.notes = notes
        }
    }

    fun getOrdersArray(): Array<Order> {
        return orders.toTypedArray()
    }

    fun hasPlate(plateId: Int): Boolean {
        return orders.find { order -> order.plate.id == plateId } != null
    }

    fun emptyOrders() {
        orders = emptyList<Order>()
        total = 0.0f
    }

    val count
        get() = orders.size
}