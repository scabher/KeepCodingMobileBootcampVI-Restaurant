package com.scabher.restaurant.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.scabher.restaurant.R
import com.scabher.restaurant.model.Plate

class MenuRecyclerViewAdapter(private val plates: List<Plate>): RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder>() {

    var onClickListener: View.OnClickListener? = null

/*    init {
        onClickListener = View.OnClickListener { view ->
            val plate = view.tag as Plate
            val intent = Intent(view.context, PlateActivity::class.java).apply {
                putExtra(PlateActivity.EXTRA_PLATE_ID, plate.id)
            }
            view.context.startActivity(intent)
        }
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.content_plate_row, parent, false)

        view.setOnClickListener(onClickListener)

        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindPlate(plates[position])
    }

    override fun getItemCount(): Int = plates.size

    inner class MenuViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val plateIdView = itemView.findViewById<TextView?>(R.id.id_text)
        private val plateContentView = itemView.findViewById<TextView?>(R.id.content)

        fun bindPlate(plate: Plate) {
            plateIdView?.text = plate.id.toString()
            plateContentView?.text = plate.name

            with(itemView) {
                tag = plate
                setOnClickListener(onClickListener)
            }
        }
    }
}
