package io.matteopellegrino.pagedgrid.adapter

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import io.matteopellegrino.pagedgrid.grid.EmptyGrid
import io.matteopellegrino.pagedgrid.grid.Grid


/**
 * Specialization of [PagerAdapter] responsible to adapt each [Grid]
 * (specified by the constructor argument) into a [GridLayout] view.
 * This adapter is manly used by a [android.support.v4.view.ViewPager]
 * to render each [GridLayout].
 * This class also contains logic for landscape mode.
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
class GridAdapter(var pages: Array<Grid>) : PagerAdapter() {
    companion object {
        const val ORIENTATION_PORTRAIT = 1
        const val ORIENTATION_LANDSCAPE = 2

        private const val FADE_IN_DURATION: Long = 200
        private const val FADE_OUT_DURATION: Long = 400
    }

    internal var orientation: Int = ORIENTATION_PORTRAIT
    private var old: Array<Grid> = pages.clone()

    var isAnimationEnabled = true

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

    private fun ViewGroup.fadeAddView(v: View){
        v.alpha = 0f
        addView(v)
        v.animate().alpha(1f).setDuration(FADE_IN_DURATION).start()
    }

    private fun ViewGroup.fadeRemoveView(v: View){
        v.animate().alpha(0f).setDuration(FADE_OUT_DURATION).withEndAction {
            removeView(v)
        }.start()
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        old = pages.clone()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var grid = pages[position]
        if (orientation != ORIENTATION_PORTRAIT)
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


        if (isAnimationEnabled && old.size > position && pages.size > position &&
                old[position] != pages[position] && (container as ViewPager).currentItem == position)
            container.fadeAddView(gridLayout)
        else
            container.addView(gridLayout)
        return gridLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        if (isAnimationEnabled && old.size > position && pages.size > position &&
                old[position] != pages[position] && (container as ViewPager).currentItem == position)
            container.fadeRemoveView(obj as View)
        else
            container.removeView(obj as View)
    }
}