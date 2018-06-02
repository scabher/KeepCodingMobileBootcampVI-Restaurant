package com.scabher.restaurant.fragment

import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.scabher.restaurant.R
import com.scabher.restaurant.activity.PlateActivity
import com.scabher.restaurant.model.*
import kotlinx.android.synthetic.main.activity_table_detail.*
import kotlinx.android.synthetic.main.fragment_table_detail.*

class TableDetailFragment : Fragment() {

    companion object {
        const val ARG_TABLE_ID = "ARG_TABLE_ID"

        fun newInstance(tableId: Int): TableDetailFragment {
            val arguments = Bundle()
            arguments.putInt(ARG_TABLE_ID, tableId)

            val fragment = TableDetailFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    private var onOrderSelectedListener: OnOrderSelectedListener? = null

    interface OnOrderSelectedListener {
        fun onOrderSelected(order: Order, position: Int)
    }

    private var tableId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_TABLE_ID) && it.getInt(ARG_TABLE_ID) != null) {
                val table = Tables.getTableById(it.getInt(ARG_TABLE_ID))
                val order = Order(Menu.getPlate(1)!!, "Notes...")
                table?.addOrder(order)
                activity?.toolbar?.title = table?.name
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_table_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateTableInfo(tableId)
    }

    fun updateTableInfo(position: Int) {
        tableId = position
        val table = Tables.get(tableId)

        val adapter = ArrayAdapter<Order>(
                activity,
                android.R.layout.simple_list_item_1,
                table?.getOrdersArray()
        )

        order_list.adapter = adapter

        order_list.setOnItemClickListener { _, _, index, _ ->
            table?.let {
                onOrderSelectedListener?.onOrderSelected(it.getOrdersArray()[index], index)
                val order = it.getOrdersArray()[index]
                val intent = PlateActivity.intent(activity!!, order.plate.id, order.notes)

                // Opciones especiales para navegar con vistas comunes
//                val animationOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        activity!!,
//                        it,
//                        getString(R.string.transition_to_detail)
//                )

                startActivity(intent)
            }
        }
    }
}
