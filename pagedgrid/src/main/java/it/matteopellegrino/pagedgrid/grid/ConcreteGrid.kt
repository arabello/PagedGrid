package it.matteopellegrino.pagedgrid.grid

import android.content.Context
import android.view.View
import android.widget.Space

internal class ConcreteGrid(val context: Context, override var columns: Int, override var rows: Int) : Grid {

    private var m:Array<Array<View>> = Array(columns) { Array(rows) { Space(context) as View } }

    override fun get(columnIndex: Int, rowIndex: Int): View {
        if (columnIndex > columns || rowIndex > rows || columnIndex < 0 || rowIndex < 0)
            throw IndexOutOfBoundsException()

        return m[columnIndex][rowIndex]
    }

    override fun set(columnIndex: Int, rowIndex: Int, element: View) {
        if (columnIndex > columns || rowIndex > rows || columnIndex < 0 || rowIndex < 0)
            throw IndexOutOfBoundsException()

        m[columnIndex][rowIndex] = element
    }

    override fun contains(element: View): Boolean {
        forEach {
            if (it == element)
                return true
        }
        return false
    }
    override fun clear() {
        m = Array(columns) { Array(rows) { Space(context) as View } }
    }

    override fun clear(columnIndex: Int, rowIndex: Int) {
        m[columnIndex][rowIndex] = Space(context) as View
    }

    private fun indexToCord(index: Int): Pair<Int, Int> = Pair(index % columns, index / columns)

    override fun iterator(): Iterator<View> {
        return object : Iterator<View>{
            var cursor = 0
            val end = columns * rows

            override fun hasNext(): Boolean = cursor < end

            override fun next(): View {
                val cord = indexToCord(cursor)
                val v = m[cord.first][cord.second]
                cursor++
                return v
            }

        }
    }

    override fun forEachIndexed(action: (x: Int, y: Int, cell: View) -> Unit) =
            forEachIndexed { index, view ->
                val cord = indexToCord(index)
                action(cord.first, cord.second, view)
            }
}