package io.matteopellegrino.pagedgrid

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import io.matteopellegrino.pagedgrid.adapter.GridAdapter
import io.matteopellegrino.pagedgrid.grid.Grid
import kotlinx.android.synthetic.main.pagedgridview.view.*
import kotlin.properties.Delegates

/**
 * [LinearLayout] containing a [ViewPager] and a [com.rd.PageIndicatorView].
 * The property [pages] represents the model rendered as a collection of pages, each one contains a grid.
 * Set this property to change what [PagedGridView] should render,
 * see [Grid] and [it.matteopellegrino.pagedgrid.element.Element] for details.
 * This view as a particular behaviour for landscape mode: the grid always rotate clockwise.
 * For example, in portrait mode an element positioned at (3,1) (3 column index, 1 row index),
 * in landscape mode will be on the (2, 3) cell.
 * This has been implemented to maintain the same position of every element whatever the orientation of the device is.
 * The orientation must be specified manually using the orientation attribute through XMl.
 * Please note: This orientation (app:orientation) is different from android:orientation inherited by [LinearLayout],
 * which has another effect.
 */
class PagedGridView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    companion object {
        const val ORIENTATION_PORTRAIT = 1
        const val ORIENTATION_LANDSCAPE = 2
    }

    var pages: List<Grid> by Delegates.observable(mutableListOf()){ _, _, new ->
        viewPagerAdapter.pages = new
        viewPagerAdapter.notifyDataSetChanged()
    }

    private val viewPagerAdapter = GridAdapter(pages)

    constructor(context: Context) : this(context, null)

    init{
        LayoutInflater.from(context).inflate(R.layout.pagedgridview, this, true)

        val arr = context.theme.obtainStyledAttributes(attrs, R.styleable.PagedGridView, 0,0)

        try{
            pageIndicator.unselectedColor = arr.getColor(R.styleable.PagedGridView_pageIndicatorUnselected, Color.GRAY)
            pageIndicator.selectedColor = arr.getColor(R.styleable.PagedGridView_pageIndicatorSelected, Color.BLACK)
            viewPagerAdapter.orientation = arr.getInt(R.styleable.PagedGridView_orientation, ORIENTATION_PORTRAIT)
        }finally {
            arr.recycle()
        }

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = viewPagerAdapter
        pageIndicator.setViewPager(viewPager)

    }
}