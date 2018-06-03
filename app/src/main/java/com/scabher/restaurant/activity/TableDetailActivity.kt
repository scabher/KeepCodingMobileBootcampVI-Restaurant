package com.scabher.restaurant.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.scabher.restaurant.R
import com.scabher.restaurant.fragment.TableDetailFragment
import com.scabher.restaurant.model.Tables

class TableDetailActivity : AppCompatActivity() {

    companion object {

        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, TableDetailActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_detail)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val table = Tables.get(intent.getIntExtra(EXTRA_TABLE_INDEX, 0))

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = TableDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(TableDetailFragment.ARG_TABLE_ID, table.id.toString())
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.table_detail_fragment, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                navigateUpTo(Intent(this, RestaurantActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

}
