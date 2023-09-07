package com.android.contectapp

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.net.Uri
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CallHelper(val con: Context, val rv : Adapter): ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
    ): Int { return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) }

    override fun onMove(recycleXrView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
    ): Boolean { return false }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val holder = viewHolder as Adapter.Holder
        val phoneNumber = holder.phone.text
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
            val view = (viewHolder as Adapter.Holder).mainRv
            getDefaultUIUtil().onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive)
        }
    }
    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
    ) {
        getDefaultUIUtil().clearView((viewHolder as Adapter.Holder).mainRv)
    }
}
interface ItemTouchHelperListener {
    fun onItemMove(from_position: Int, to_position: Int): Boolean
    fun onItemSwipe(position: Int)
}