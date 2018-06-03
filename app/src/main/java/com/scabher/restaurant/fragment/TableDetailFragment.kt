package com.scabher.restaurant.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.ArrayAdapter
import com.scabher.restaurant.R
import com.scabher.restaurant.activity.MenuActivity
import com.scabher.restaurant.activity.PlateActivity
import com.scabher.restaurant.model.*
import com.scabher.restaurant.model.Menu
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

    val REQUEST_BILL = 1
    private var tableId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_table_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        show_menu_button.setOnClickListener {
            startActivity(MenuActivity.intent(activity!!, tableId))
        }

        updateTableInfo(tableId)
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        // Pilla el xml donde se define el menú y lo transforma dentro del objeto menú
        inflater?.inflate(R.menu.activity_table_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val table = Tables.get(tableId)

        when (item?.itemId) {
            R.id.action_show_bill -> {
                val dialog = BillDialog.newInstance(table)
                dialog.setTargetFragment(this, REQUEST_BILL)
                dialog.show(fragmentManager, null)

                return true
            }
            R.id.action_new_bill -> {
                table.emptyOrders()
                updateTableInfo(tableId)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateTableInfo(newTableId: Int) {
        tableId = newTableId
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
                val intent = PlateActivity.intent(activity!!, order.plate.id, order.notes, table.id)

                startActivity(intent)
            }
        }
    }
}
