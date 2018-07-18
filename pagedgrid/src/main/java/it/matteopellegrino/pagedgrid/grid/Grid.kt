package it.matteopellegrino.pagedgrid.grid

import it.matteopellegrino.pagedgrid.element.Element

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
interface Grid : Matrix<Element> {
    fun clear(columnIndex: Int, rowIndex: Int)
    fun clear()
    fun forEachIndexed(action: (x: Int, y: Int, elem: Element) -> Unit)
}