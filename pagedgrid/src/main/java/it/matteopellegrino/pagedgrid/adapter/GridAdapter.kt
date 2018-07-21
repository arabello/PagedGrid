package it.matteopellegrino.pagedgrid.adapter

import android.content.res.Configuration
import android.support.v4.view.PagerAdapter
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import it.matteopellegrino.pagedgrid.PagedGridView
import it.matteopellegrino.pagedgrid.grid.EmptyGrid
import it.matteopellegrino.pagedgrid.grid.Grid


/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
internal class GridAdapter(var pages: List<Grid>) : PagerAdapter() {
    var orientation: Int = PagedGridView.ORIENTATION_PORTRAIT

    override fun getCount(): Int {
        return pages.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` === view
    }

    override fun getItemPosition(obj: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    // Rotate clockwise
    private fun Grid.rotate(): Grid{
        val newColumns = this.rows
        val newRows = this.columns
        val new = EmptyGrid(newColumns, newRows)

        this.forEachIndexed { x, y, element ->
            val newX = newColumns - 1 - y
            val newY = x
            new[newX, newY] = element
        }

        return new
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var grid = pages[position]
        if (orientation != PagedGridView.ORIENTATION_PORTRAIT)
            grid = grid.rotate()

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