package it.matteopellegrino.pgv.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import it.matteopellegrino.pgv.grid.Grid

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
internal class GridViewAdapter(val grid: Grid): BaseAdapter() {
    override fun getCount(): Int = grid.columns * grid.rows

    override fun getItem(position: Int): View = grid[position / grid.columns, position % grid.rows]

    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val cell = grid[position / grid.columns, position % grid.rows]
        val maxDimen = if (parent.width < parent.height) parent.width else parent.height
        val maxNum = if (grid.columns < grid.rows) grid.columns else grid.rows

        cell.minimumWidth = maxDimen / maxNum
        cell.minimumHeight = 500

        return cell
    }
}