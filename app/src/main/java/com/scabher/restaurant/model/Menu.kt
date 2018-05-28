package com.scabher.restaurant.model

import com.scabher.restaurant.R

object Menu {

    private var plates: List<Plate> = listOf(
        Plate(1, "Arroz a la cubana", "Arroz blanco, huevo frito, plátano frito",
                R.drawable.arroz_cubana, 7.5F, listOf(Allergens.getByPos(0)!!)),
        Plate(2, "Merluza a la romana", "Merluza rebozada, patatas fritas, ensalada",
                R.drawable.merluza_romana, 8.5F, listOf(Allergens.getByPos(5)!!)),
        Plate(3, "Espaguetti_Carbonara", "Espaguettis, salsa carbonara, bacon",
            R.drawable.spaguetti_carbonara, 7.5F, listOf(Allergens.getByPos(2)!!, Allergens.getByPos(4)!!)),
        Plate(4, "Cangrejos en salsa", "Cangrejo de río en salsa",
            R.drawable.cangrejo_salsa, 7.5F, listOf(Allergens.getByPos(3)!!)),
        Plate(5, "Solomillo a la pimienta", "Solomillo de cerdo, salsa a la pimienta patatas fritas",
            R.drawable.egg_allergen, 7.5F, null)
    )


    fun getPlate(plateId: Int): Plate? {
        return plates.find { plate: Plate -> plate.id == plateId }
    }

    fun getPlates(): List<Plate> {
        return plates
    }
}