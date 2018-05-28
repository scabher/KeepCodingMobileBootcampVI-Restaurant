package com.scabher.restaurant.model

import com.scabher.restaurant.R

object Menu {

    private var plates: List<Plate> = listOf(
        Plate(1, "Arroz a la cubana", "Arroz blanco, huevo frito, plátano frito",
                R.drawable.milk_allergen, 7.5F, listOf(Allergens.getByPos(0)!!))
    )


    fun getPlate(plateId: Int): Plate? {
        return plates.find { plate -> plate.id() == plateId }
    }
}