package com.scabher.restaurant.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.scabher.restaurant.R
import com.scabher.restaurant.model.Allergen
import com.scabher.restaurant.model.Allergens
import com.scabher.restaurant.model.Menu
import kotlinx.android.synthetic.main.activity_plate.*

class PlateActivity : AppCompatActivity() {
    companion object {
        val EXTRA_PLATE_ID = "EXTRA_PLATE_ID"
        val EXTRA_PLATE_NOTES = "EXTRA_PLATE_NOTES"

        fun intent(context: Context, plateId: Int, notes: String): Intent {
            val intent = Intent(context, PlateActivity::class.java)
            intent.putExtra(EXTRA_PLATE_ID, plateId)
            intent.putExtra(EXTRA_PLATE_NOTES, notes)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plate)

        val plateId = intent.getIntExtra(EXTRA_PLATE_ID, 0)
        val plate = Menu.getPlate(plateId)

        // Se actualiza la interfaz
        if (plate != null) {
            plate_name.text = plate.name
            plate_description.text = plate.description
            plate_price.text = plate.price.toString()
            plate_image.setImageResource(plate.image)
            plate_notes.text = intent.getStringExtra(EXTRA_PLATE_NOTES)

            plate.allergens?.let {
                egg_allergen_image.visibility = if (it.contains(Allergens.getById(0))) View.VISIBLE else View.GONE
                gluten_allergen_image.visibility = if (it.contains(Allergens.getById(1))) View.VISIBLE else View.GONE
                crustaceans_allergen_image.visibility = if (it.contains(Allergens.getById(2))) View.VISIBLE else View.GONE
                milk_allergen_image.visibility = if (it.contains(Allergens.getById(3))) View.VISIBLE else View.GONE
                fish_allergen_image.visibility = if (it.contains(Allergens.getById(4))) View.VISIBLE else View.GONE
            }
        }
    }
}
