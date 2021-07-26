package com.section11.movieknight.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.math.abs

/**
 * This class fixes the issue of having a [SwipeRefreshLayout] with a horizontal scroll view inside
 * In our case a recyclerView.
 *
 * The problem is that [SwipeRefreshLayout] is intercepting horizontal swipes also.
 *
 * Source: https://stackoverflow.com/questions/34136178/swiperefreshlayout-blocking-horizontally-scrolled-recyclerview
 */
class OnlyVerticalSwipeRefreshLayout(context: Context?, attrs: AttributeSet?) :
    SwipeRefreshLayout(context!!, attrs) {
    private val touchSlop: Int = ViewConfiguration.get(context).scaledTouchSlop
    private var prevX = 0f
    private var declined = false
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                prevX = MotionEvent.obtain(event).x
                declined = false
            }
            MotionEvent.ACTION_MOVE -> {
                val eventX = event.x
                val xDiff = abs(eventX - prevX)
                if (declined || xDiff > touchSlop) {
                    declined = true
                    return false
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }
}
