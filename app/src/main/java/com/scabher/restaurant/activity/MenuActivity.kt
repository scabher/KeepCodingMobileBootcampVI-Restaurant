package com.scabher.restaurant.activity

import android.content.Context
import android.content.Intent
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

    companion object {
        val EXTRA_TABLE_ID = "EXTRA_TABLE_ID"

        fun intent(context: Context, tableId: Int): Intent {
            val intent = Intent(context, MenuActivity::class.java)
            intent.putExtra(EXTRA_TABLE_ID, tableId)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setSupportActionBar(menu_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Muestra el botón de back

        menu_list.layoutManager = LinearLayoutManager(this)

        // Si alguien pulsa un elemento del RecyclerView, nos queremos enterar aquí
        val adapter = MenuRecyclerViewAdapter(Menu.getPlates())

        adapter?.onClickListener = View.OnClickListener { view ->
            val plate = view.tag as Plate
            val tableId = intent.getIntExtra(EXTRA_TABLE_ID, 0)
            val intent = PlateActivity.intent(view.context, plate.id, "", tableId)
            view.context.startActivity(intent)
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
