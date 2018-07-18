package it.matteopellegrino.pagedgrid.grid

import android.content.Context
import it.matteopellegrino.pagedgrid.element.Element
import it.matteopellegrino.pagedgrid.element.Empty

class EmptyGrid(override var columns: Int, override var rows: Int) : AbstractGrid() {
    override var m: Array<Array<Element>> = Array(columns) { Array(rows) {Empty() as Element} }
}