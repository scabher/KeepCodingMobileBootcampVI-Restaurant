package com.scabher.restaurant.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.scabher.restaurant.R
import com.scabher.restaurant.adapter.MenuRecyclerViewAdapter
import com.scabher.restaurant.model.Menu
import com.scabher.restaurant.model.Plate
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    private var onPlateSelectedListener: OnPlateSelectedListener? = null

    interface OnPlateSelectedListener {
        fun onPlateSelected(plate: Plate, position: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Muestra el botón de back

        menu_list.layoutManager = LinearLayoutManager(this)

        // Si alguien pulsa un elemento del RecyclerView, nos queremos enterar aquí
        val adapter = MenuRecyclerViewAdapter(Menu.getPlates())

        adapter?.onClickListener = View.OnClickListener { view ->
            val plate = view.tag as Plate
            onPlateSelectedListener?.onPlateSelected(plate, Menu.getIndex(plate))
        }

        menu_list.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> { // Flecha de back del toolbar
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}
