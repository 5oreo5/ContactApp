package com.android.contectapp

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CallHelper(val con: Context, val rv : Adapter): ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
    ): Int { return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) }

    override fun onMove(recycleXrView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
    ): Boolean { return false }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val holder = viewHolder
        val phoneNumber = if (holder is Adapter.ItemListHolder) {
            holder.phone.text
        } else { "" }
        val callUriSwipedPerson = Uri.parse("tel:$phoneNumber")
        val callIntent = Intent(Intent.ACTION_CALL, callUriSwipedPerson)
        con.startActivity(callIntent)
        // 전화 끊고 다시 돌아왔을 때
        rv.notifyItemChanged(holder.absoluteAdapterPosition)

    }
    override fun isItemViewSwipeEnabled(): Boolean { return true }
    override fun isLongPressDragEnabled(): Boolean { return false }
    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val view = if (viewHolder is Adapter.ItemListHolder) {
                viewHolder.mainRv
            } else { "" }

            view?.let {
                getDefaultUIUtil().onDraw(c, recyclerView,
                    it as View?, dX, dY, actionState, isCurrentlyActive)
            }
        }
    }
    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
    ) {
        val view = if (viewHolder is Adapter.ItemListHolder) {
            viewHolder.mainRv
        } else { "" }
        view?.let {
            getDefaultUIUtil().clearView(it as View?)
        }
    }
}
interface ItemTouchHelperListener {
    fun onItemMove(from_position: Int, to_position: Int): Boolean
    fun onItemSwipe(position: Int)
}