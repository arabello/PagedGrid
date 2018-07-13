package it.matteopellegrino.pgv.adapter

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import it.matteopellegrino.pgv.R
import it.matteopellegrino.pgv.grid.Grid

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
        val gridView = LayoutInflater.from(container.context).inflate(R.layout.gridview, null) as GridView

        gridView.isVerticalScrollBarEnabled = false
        gridView.numColumns = pages[position].columns
        gridView.adapter = GridViewAdapter(pages[position])
        gridView.stretchMode = GridView.STRETCH_COLUMN_WIDTH
        gridView.verticalSpacing = container.height / pages[position].rows

        container.addView(gridView)
        return gridView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}