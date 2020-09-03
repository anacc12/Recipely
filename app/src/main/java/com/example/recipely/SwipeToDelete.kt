package com.example.recipely

import android.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recipely.Adapters.CustomMealsAdapter


class SwipeToDelete(var adapter: CustomMealsAdapter): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val alert: AlertDialog.Builder = AlertDialog.Builder(
            viewHolder.itemView.context
        )
        alert.setMessage("Are you sure you want to delete the recipe?")
        alert.setPositiveButton("YES"
        ) { dialog, _ ->
            val position: Int = viewHolder.adapterPosition
            adapter.deleteItem(position)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        alert.setNegativeButton("NO"
        ) { dialog, _ ->
            adapter.notifyDataSetChanged()
            dialog.dismiss()

        }

        alert.create()
        alert.show()

    }
}