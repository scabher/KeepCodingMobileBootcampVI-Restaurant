package com.scabher.restaurant.model

import java.io.Serializable

data class Order(val plate: Plate, val notes: String) {
    // Se usará para mostrar el order en el detalle de la mesa
    override fun toString(): String {
        return plate.name
    }
}