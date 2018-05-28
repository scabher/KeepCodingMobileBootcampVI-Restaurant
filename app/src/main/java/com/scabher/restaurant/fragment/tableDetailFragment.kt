package com.scabher.restaurant.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scabher.restaurant.R
import com.scabher.restaurant.model.Table
import com.scabher.restaurant.model.Tables
import kotlinx.android.synthetic.main.activity_table_detail.*
import kotlinx.android.synthetic.main.table_detail.view.*

class tableDetailFragment : Fragment() {

    companion object {
        const val ARG_TABLE_ID = "table_id"
    }

    private var table: Table? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_TABLE_ID) && it.getString(ARG_TABLE_ID) != null) {
                table = Tables.getTableById(it.getString(ARG_TABLE_ID).toInt())
                activity?.toolbar_layout?.title = table?.name
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.table_detail, container, false)

        // Show the dummy content as text in a TextView.
        table?.let {
            rootView.table_detail.text = it.description
        }

        return rootView
    }
}
