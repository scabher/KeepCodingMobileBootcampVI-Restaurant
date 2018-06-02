package com.scabher.restaurant.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.scabher.restaurant.R
import com.scabher.restaurant.activity.PlateActivity
import com.scabher.restaurant.model.Plate

class MenuRecyclerViewAdapter(private val plates: List<Plate>): RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { view ->
            val plate = view.tag as Plate
            val intent = Intent(view.context, PlateActivity::class.java).apply {
                putExtra(PlateActivity.EXTRA_PLATE_ID, plate.id)
            }
            view.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.content_plate_row, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindPlate(plates[position])
    }

    override fun getItemCount(): Int = plates.size

    inner class MenuViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val plateIdView = itemView.findViewById<TextView?>(R.id.id_text)
        val plateContentView = itemView.findViewById<TextView?>(R.id.content)

        fun bindPlate(plate: Plate) {
            plateIdView?.text = plate.id.toString()
            plateContentView?.text = plate.name

            with(itemView) {
                setOnClickListener(onClickListener)
            }
        }
    }
}
