package io.matteopellegrino.pagedgrid.grid

import io.matteopellegrino.pagedgrid.element.Element
import io.matteopellegrino.pagedgrid.element.Empty

/**
 * Basic business logic every [Grid] implementation should have
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
abstract class AbstractGrid: Grid {
    internal abstract var m:Array<Array<Element>>

    override fun get(columnIndex: Int, rowIndex: Int): Element {
        if (columnIndex > columns || rowIndex > rows || columnIndex < 0 || rowIndex < 0)
            throw IndexOutOfBoundsException()

        return m[columnIndex][rowIndex]
    }

    override fun set(columnIndex: Int, rowIndex: Int, element: Element) {
        if (columnIndex > columns || rowIndex > rows || columnIndex < 0 || rowIndex < 0)
            throw IndexOutOfBoundsException()

        m[columnIndex][rowIndex] = element
    }

    override fun contains(element: Element): Boolean {
        forEach {
            if (it == element)
                return true
        }
        return false
    }
    override fun clear() {
        m = Array(columns) { Array(rows) { Empty() as Element } }
    }

    override fun clear(columnIndex: Int, rowIndex: Int) {
        m[columnIndex][rowIndex] = Empty()
    }

    private fun indexToCord(index: Int): Pair<Int, Int> = Pair(index % columns, index / columns)

    override fun iterator(): Iterator<Element> {
        return object : Iterator<Element>{
            var cursor = 0
            val end = columns * rows

            override fun hasNext(): Boolean = cursor < end

            override fun next(): Element {
                val cord = indexToCord(cursor)
                val v = m[cord.first][cord.second]
                cursor++
                return v
            }

        }
    }

    override fun forEachIndexed(action: (x: Int, y: Int, cell: Element) -> Unit) =
            forEachIndexed { index, elem ->
                val cord = indexToCord(index)
                action(cord.first, cord.second, elem)
            }
}