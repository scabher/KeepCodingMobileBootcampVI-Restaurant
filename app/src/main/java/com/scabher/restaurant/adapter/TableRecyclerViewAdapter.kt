package com.scabher.restaurant.adapter

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.scabher.restaurant.R
import com.scabher.restaurant.activity.TableDetailActivity
import com.scabher.restaurant.activity.TableListActivity
import com.scabher.restaurant.fragment.tableDetailFragment
import com.scabher.restaurant.model.Table
import kotlinx.android.synthetic.main.table_list_content.view.*

class TableRecyclerViewAdapter(private val parentActivity: TableListActivity,
                               private val values: List<Table>,
                               private val twoPane: Boolean) :
        RecyclerView.Adapter<TableRecyclerViewAdapter.TableViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { view ->
            val table = view.tag as Table
            if (twoPane) {
                val fragment = tableDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(tableDetailFragment.ARG_TABLE_ID, table.id.toString())
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.table_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(view.context, TableDetailActivity::class.java).apply {
                    putExtra(tableDetailFragment.ARG_TABLE_ID, table.id)
                }
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.table_list_content, parent, false)

        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val table = values[position]
        holder.idView.text = table.id.toString()
        holder.contentView.text = table.name

        with(holder.itemView) {
            tag = table
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class TableViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content
    }
}