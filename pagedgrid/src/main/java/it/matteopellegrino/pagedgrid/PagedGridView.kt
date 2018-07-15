package it.matteopellegrino.pagedgrid

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import it.matteopellegrino.pagedgrid.adapter.ViewPagerAdapter
import it.matteopellegrino.pagedgrid.grid.Grid
import kotlinx.android.synthetic.main.pagedgridview.view.*
import kotlin.properties.Delegates

class PagedGridView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    var pages: List<Grid> by Delegates.observable(mutableListOf()){ _, _, new ->
        viewPagerAdapter.pages = new
        viewPagerAdapter.notifyDataSetChanged()
    }

    private val viewPagerAdapter = ViewPagerAdapter(pages)

    constructor(context: Context) : this(context, null)


    init{
        val arr = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.PagedGridView, 0,0
        )

        LayoutInflater.from(context).inflate(R.layout.pagedgridview, this, true)

        try{
            pageIndicator.unselectedColor = arr.getColor(R.styleable.PagedGridView_pageIndicatorUnselected, Color.GRAY)
            pageIndicator.selectedColor = arr.getColor(R.styleable.PagedGridView_pageIndicatorSelected, Color.BLACK)
        }finally {
            arr.recycle()
        }

        viewPager.adapter = viewPagerAdapter
        pageIndicator.setViewPager(viewPager)
    }
}