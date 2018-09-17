package io.matteopellegrino.pagedgrid

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import io.matteopellegrino.pagedgrid.adapter.GridAdapter
import io.matteopellegrino.pagedgrid.grid.Grid
import kotlinx.android.synthetic.main.pagedgridview.view.*
import kotlin.properties.Delegates


/**
 * Contains a [ViewPager] and a [com.rd.PageIndicatorView].
 * The property [pages] represents the model rendered as a collection of pages, each one contains a grid.
 * Set this property to change what [PagedGridView] should render,
 * see [Grid] and [it.matteopellegrino.pagedgrid.element.Element] for details.
 * This view as a particular behaviour for landscape mode: the grid always rotate clockwise.
 * For example, in portrait mode an element positioned at (3,1) (3 column index, 1 row index),
 * in landscape mode will be on the (2, 3) cell.
 * This has been implemented to maintain the same position of every element whatever the orientation of the device is.
 * The orientation must be specified manually using the orientation attribute through XMl.
 * Please note: This orientation (app:orientation) is different from android:orientation,
 * which has another effect.
 */
class PagedGridView(context: Context, attrs: AttributeSet?) : android.support.constraint.ConstraintLayout(context, attrs) {

    private lateinit var viewPager: ViewPager

    var adapter by Delegates.observable(GridAdapter(arrayOf())){ _, _, newValue ->
        newValue.pageIndicator = pageIndicator
        newValue.pageIndicator?.setViewPager(viewPager)
        viewPager.adapter = newValue
    }

    constructor(context: Context) : this(context, null)

    init{
        LayoutInflater.from(context).inflate(R.layout.pagedgridview, this, true)

        viewPager = findViewById(R.id.viewPager)
        adapter.pageIndicator = findViewById(R.id.pageIndicator)
        adapter.pageIndicator?.setViewPager(viewPager)

        val arr = context.theme.obtainStyledAttributes(attrs, R.styleable.PagedGridView, 0,0)

        try{
            pageIndicator.unselectedColor = arr.getColor(R.styleable.PagedGridView_pageIndicatorUnselected, Color.GRAY)
            pageIndicator.selectedColor = arr.getColor(R.styleable.PagedGridView_pageIndicatorSelected, Color.BLACK)
            adapter.orientation = arr.getInt(R.styleable.PagedGridView_orientation, GridAdapter.ORIENTATION_PORTRAIT)
            adapter.isAnimationEnabled = arr.getBoolean(R.styleable.PagedGridView_animationEnabled, true)
        }finally {
            arr.recycle()
        }

        viewPager.adapter = adapter
    }
}