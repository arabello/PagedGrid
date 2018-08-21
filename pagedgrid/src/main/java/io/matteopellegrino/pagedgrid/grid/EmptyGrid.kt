package io.matteopellegrino.pagedgrid.grid

import io.matteopellegrino.pagedgrid.element.Element
import io.matteopellegrino.pagedgrid.element.Empty

/**
 * Concrete [Grid] with all empty elements.
 * An instance of this class should be used as starting point to build up
 * complex grids, avoiding rendering problems if a grid presents no elements.
 */
class EmptyGrid(override var columns: Int, override var rows: Int) : AbstractGrid() {
    override var m: Array<Array<Element>> = Array(columns) { Array(rows) { Empty() as Element} }
}