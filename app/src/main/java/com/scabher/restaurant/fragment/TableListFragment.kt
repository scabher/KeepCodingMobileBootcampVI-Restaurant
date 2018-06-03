package com.scabher.restaurant.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scabher.restaurant.R
import com.scabher.restaurant.adapter.TableRecyclerViewAdapter
import com.scabher.restaurant.model.Table
import com.scabher.restaurant.model.Tables
import kotlinx.android.synthetic.main.fragment_table_list.*

class TableListFragment: Fragment() {

    companion object {
        fun newInstance() = TableListFragment()
    }

    private var onTableSelectedListener: OnTableSelectedListener? = null

    interface OnTableSelectedListener {
        fun onTableSelected(table: Table, position: Int)
    }

    private var tables: List<Table>? = null
        set(value) {
            field = value

            if (value != null) {
                val adapter = TableRecyclerViewAdapter(value)
                table_list.adapter = adapter
                setRecyclerViewClickListener()
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Se configura el RecyclerView. Primero se dice cómo se visualizan sus elementos
        table_list.layoutManager = LinearLayoutManager(activity)

        // Datos para rellenar el RecyclerView, tarea del setter de tables
        tables = Tables.getTables()
    }

    fun setRecyclerViewClickListener() {
        // Si alguien pulsa un elemento del RecyclerView, nos queremos enterar aquí
        val adapter = table_list?.adapter as? TableRecyclerViewAdapter

        adapter?.onClickListener = View.OnClickListener { view ->
            val table = view.tag as Table
            onTableSelectedListener?.onTableSelected(table, Tables.getIndex(table))
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context as Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onTableSelectedListener = null
    }

    private fun commonAttach(activity: Activity?) {
        if (activity is OnTableSelectedListener) {
            onTableSelectedListener = activity
        }
        else {
            onTableSelectedListener = null
        }
    }
}