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

    fun getOrdersArray(): Array<Order> {
        return orders.toTypedArray()
    }

    fun emptyOrders() {
        orders = emptyList<Order>()
        total = 0.0f
    }
}