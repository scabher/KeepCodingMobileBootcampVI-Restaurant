package com.scabher.restaurant.model

import com.scabher.restaurant.R

object Allergens {

    private val allergens: List<Allergen> = listOf(
        Allergen(1, "Huevo", R.drawable.egg_allergen),
        Allergen(2, "Gluten", R.drawable.gluten_allergen),
        Allergen(3, "Crustáceos", R.drawable.crustaceans_allergen),
        Allergen(4, "Leche", R.drawable.milk_allergen),
        Allergen(5, "Pescado", R.drawable.fish_allergen)
    )

    fun getByPos(position: Int): Allergen? {
        if (position !in  0..(allergens.size-1))
            return null

        return allergens[position]
    }
}