package com.scabher.restaurant.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.scabher.restaurant.R
import com.scabher.restaurant.model.Table
import kotlinx.android.synthetic.main.content_table_list.view.*

class TableRecyclerViewAdapter(private val tables: List<Table>) :
        RecyclerView.Adapter<TableRecyclerViewAdapter.TableViewHolder>() {

    var onClickListener: View.OnClickListener? = null

/*
    init {
        onClickListener = View.OnClickListener { view ->
            val table = view.tag as Table
            if (twoPane) {
                val fragment = TableDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(TableDetailFragment.ARG_TABLE_ID, table.id.toString())
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.table_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(view.context, TableDetailActivity::class.java).apply {
                    putExtra(TableDetailFragment.ARG_TABLE_ID, table.id)
                }
                view.context.startActivity(intent)
            }
        }
    }
*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.content_table_list, parent, false)

        view.setOnClickListener(onClickListener)

        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bindTable(tables[position])
    }

    override fun getItemCount() = tables.size

    inner class TableViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content

        fun bindTable(table: Table) {
            idView.text = table.id.toString()
            contentView.text = table.name

            with(itemView) {
                tag = table
                setOnClickListener(onClickListener)
            }
        }
    }
}