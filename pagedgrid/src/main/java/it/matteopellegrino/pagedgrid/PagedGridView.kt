package it.matteopellegrino.pagedgrid

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import it.matteopellegrino.pagedgrid.adapter.GridAdapter
import it.matteopellegrino.pagedgrid.grid.Grid
import kotlinx.android.synthetic.main.pagedgridview.view.*
import kotlin.properties.Delegates

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