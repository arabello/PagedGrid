package it.matteopellegrino.pagedgrid.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
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

    fun measureMinMax(first: Int, second: Int): Pair<Int, Int> = if (first < second) Pair(first, second) else Pair(second, first)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val tableLayout = TableLayout(container.context)
        val tableParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT)

        tableLayout.layoutParams = tableParams
        tableLayout.isStretchAllColumns = true

        val grid = pages[position]

        val cellMinWidth = container.measuredWidth / grid.columns
        val cellMinHeight = container.measuredHeight / grid.rows

        for (y in 0 until grid.rows){
            val tableRow = TableRow(container.context)

            for (x in 0 until grid.columns) {
                val cell = grid[x, y]
                cell.minimumWidth = cellMinWidth
                cell.minimumHeight = cellMinHeight
                tableRow.addView(cell)
            }

            tableLayout.addView(tableRow)
        }

        container.addView(tableLayout)
        return tableLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}