package com.scabher.restaurant.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.scabher.restaurant.R
import com.scabher.restaurant.model.*
import kotlinx.android.synthetic.main.activity_plate.*

class PlateActivity : AppCompatActivity() {
    companion object {
        val EXTRA_PLATE_ID = "EXTRA_PLATE_ID"
        val EXTRA_PLATE_NOTES = "EXTRA_PLATE_NOTES"
        val EXTRA_PLATE_SHOW_NOTES = "EXTRA_PLATE_SHOW_NOTES"
        val EXTRA_TABLE_ID = "EXTRA_TABLE_ID"

        fun intent(context: Context, plateId: Int, notes: String, tableId: Int): Intent {
            val intent = Intent(context, PlateActivity::class.java)
            intent.putExtra(EXTRA_PLATE_ID, plateId)
            intent.putExtra(EXTRA_PLATE_NOTES, notes)
            intent.putExtra(EXTRA_TABLE_ID, tableId)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plate)

        val plateId = intent.getIntExtra(EXTRA_PLATE_ID, 0)
        val notes = intent.getStringExtra(EXTRA_PLATE_NOTES)
        val tableId = intent.getIntExtra(EXTRA_TABLE_ID, 0)

        val plate = Menu.getPlate(plateId)
        val table = Tables.getTableById(tableId)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Muestra el botón de back
        supportActionBar?.title = plate?.name

        // Se actualiza la interfaz
        if (plate != null) {
            plate_name.text = plate.name
            plate_description.text = plate.description
            plate_price.text = "${plate.price.toString()}€"
            plate_image.setImageResource(plate.image)
            plate_notes.setText(notes)

            val isPlateInTable = table != null && table.hasPlate(plateId)
            save_plate_btn.text = if (isPlateInTable) getString(R.string.update_plate_btn_text) else getString(R.string.add_plate_btn_text)

            if (plate.allergens != null) {
                plate_no_allergens.visibility = View.GONE
                plate_allergens.visibility = View.VISIBLE
                egg_allergen_image.visibility = if (plate.allergens.contains(Allergens.getById(0))) View.VISIBLE else View.GONE
                gluten_allergen_image.visibility = if (plate.allergens.contains(Allergens.getById(1))) View.VISIBLE else View.GONE
                crustaceans_allergen_image.visibility = if (plate.allergens.contains(Allergens.getById(2))) View.VISIBLE else View.GONE
                milk_allergen_image.visibility = if (plate.allergens.contains(Allergens.getById(3))) View.VISIBLE else View.GONE
                fish_allergen_image.visibility = if (plate.allergens.contains(Allergens.getById(4))) View.VISIBLE else View.GONE
            } else {
                plate_no_allergens.visibility = View.VISIBLE
                plate_allergens.visibility = View.GONE
            }

            save_plate_btn.isEnabled = true

            save_plate_btn.setOnClickListener {
                table?.let {
                    if (isPlateInTable) {
                        table.updateOrder(plateId, plate_notes.text.toString())
                    }
                    else {
                        table.addOrder(Order(plate, plate_notes.text.toString()))
                    }
                }
                finish()
            }
        }
        else {
            save_plate_btn.isEnabled = false
        }

        cancel_btn.setOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> { // Flecha de back del toolbar
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
