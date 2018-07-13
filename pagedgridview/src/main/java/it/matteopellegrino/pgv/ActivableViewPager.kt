package it.matteopellegrino.pgv

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
internal class ActivableViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    var enable = true

    constructor(context: Context) : this(context, null)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (enable) {
            super.onTouchEvent(event)
        } else false
        this.isEnabled
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (enable) {
            super.onInterceptTouchEvent(event)
        } else false
    }
}