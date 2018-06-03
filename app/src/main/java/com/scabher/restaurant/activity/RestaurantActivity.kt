package com.scabher.restaurant.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.scabher.restaurant.R
import com.scabher.restaurant.fragment.TableDetailFragment
import com.scabher.restaurant.fragment.TableListFragment
import com.scabher.restaurant.fragment.TableListFragment.OnTableSelectedListener
import com.scabher.restaurant.model.Table
import kotlinx.android.synthetic.main.activity_restaurant.*


class RestaurantActivity : AppCompatActivity(), OnTableSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        // Se averigua qué interfaz hemos cargado
        // preguntando si en la interfaz tenemos un Framelayout concreto
        if (findViewById<ViewGroup>(R.id.table_list_fragment) != null) {
            // Se ha cargado una interfaz que tienen el hueco para el fragment TableListFragment

            // Se comprueba que todavía no está el fragment en la jerarquía
            if (supportFragmentManager.findFragmentById(R.id.table_list_fragment) == null) {

                // Se añade el fragment de forma dinámica
                val fragment = TableListFragment.newInstance()

                // Transacción para añadir un fragment en la pantalla indicando en qué hueco y qué fragment
                supportFragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, fragment)
                        .commit()
            }
        }

        if (findViewById<ViewGroup>(R.id.table_detail_fragment) != null) {
            // Se ha cargado una interfaz que tienen el hueco para el fragment TableDetailFragment

            // Se comprueba que todavía no está el fragment en la jerarquía
            if (supportFragmentManager.findFragmentById(R.id.table_detail_fragment) == null) {
                // Transacción para añadir un fragment en la pantalla indicando en qué hueco y qué fragment
                supportFragmentManager.beginTransaction()
                        .add(R.id.table_detail_fragment, TableDetailFragment.newInstance(0))
                        .commit()
            }
        }
    }


    override fun onTableSelected(table: Table, position: Int) {
        val tableDetailFragment = supportFragmentManager.findFragmentById(R.id.table_detail_fragment) as? TableDetailFragment

        if (tableDetailFragment != null) {
            supportActionBar?.title = "Restaurant - ${table.name}"

            // Está en una interfaz donde existe el TableDetailFragment, se carga el pedido de esa mesa
            tableDetailFragment.updateTableInfo(position)
        }
        else {
            // Está en una interfaz donde sólo hay lista. Lanzamos la actividad TableDetailActivity
            val intent = TableDetailActivity.intent(this, position)
            startActivity(intent)
        }
    }
}
