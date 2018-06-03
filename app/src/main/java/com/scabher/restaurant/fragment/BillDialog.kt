package com.scabher.restaurant.fragment


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.TaskStackBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.scabher.restaurant.R
import com.scabher.restaurant.model.Table
import kotlinx.android.synthetic.main.fragment_bill.*

class BillDialog : DialogFragment() {
    companion object {
        val ARG_TABLE = "ARG_TABLE_ID"

        fun newInstance(table: Table): BillDialog {
            val arguments = Bundle()
            arguments.putSerializable(ARG_TABLE, table)

            val dialog = BillDialog()
            dialog.arguments = arguments

            return dialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val table = arguments?.getSerializable(ARG_TABLE) as Table
        bill_total_euros.text = "${table.getTotal().toString()}€"
        bill_total_plates.text = table.count.toString()

        close_btn.setOnClickListener { closeBill() }
    }

    private fun closeBill() {
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_CANCELED, null)
        dismiss()
    }
}
