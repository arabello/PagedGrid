package it.matteopellegrino.pagedgrid.adapter

import android.support.v4.view.PagerAdapter
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import it.matteopellegrino.pagedgrid.grid.Grid


/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
internal class ViewPagerAdapter(var pages: List<Grid>) : PagerAdapter() {

    override fun getCount(): Int {
        return pages.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` === view
    }

    override fun getItemPosition(obj: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val grid = pages[position]
        val gridLayout = GridLayout(container.context)

        val gridLayoutParams = ViewGroup.LayoutParams(
                GridLayout.LayoutParams.MATCH_PARENT,
                GridLayout.LayoutParams.MATCH_PARENT
        )
        gridLayout.layoutParams = gridLayoutParams

        val cellWidth = container.measuredWidth / grid.columns
        val cellHeight = container.measuredHeight / grid.rows
        val span = 1
        val alignment = GridLayout.CENTER

        grid.forEachIndexed { x, y, element ->
            val cellParams = GridLayout.LayoutParams()
            cellParams.columnSpec = GridLayout.spec(x, span, alignment)
            cellParams.rowSpec = GridLayout.spec(y, span, alignment)
            cellParams.setGravity(Gravity.CENTER)
            cellParams.width = cellWidth
            cellParams.height = cellHeight

            val cell = element.inflateView(gridLayout)
            cell.layoutParams = cellParams
            gridLayout.addView(cell)
        }

        container.addView(gridLayout)
        return gridLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}