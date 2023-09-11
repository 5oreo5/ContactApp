package com.android.contectapp

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CallHelper(private val con: Context, private val rv : Adapter): ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
    ): Int { return makeMovementFlags(0, ItemTouchHelper.RIGHT) }

    override fun onMove(recycleXrView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
    ): Boolean { return false }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        (viewHolder as? Adapter.ItemListHolder)?.apply {
            val phoneNumber = phone.text
            val callUriSwipedPerson = Uri.parse("tel:$phoneNumber")
            val callIntent = Intent(Intent.ACTION_CALL, callUriSwipedPerson)
            con.startActivity(callIntent)
            // 전화 끊고 다시 돌아왔을 때
            rv.notifyItemChanged(absoluteAdapterPosition)
        }

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
            } else return

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
        } else return
        view.let {
            getDefaultUIUtil().clearView(it as View?)
        }
    }
}
interface ItemTouchHelperListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onItemSwipe(position: Int)
}