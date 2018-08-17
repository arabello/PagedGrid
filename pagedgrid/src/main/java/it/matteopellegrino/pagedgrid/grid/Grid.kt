package it.matteopellegrino.pagedgrid.grid

import it.matteopellegrino.pagedgrid.element.Element

/**
 * Simple specialization of [Matrix] which adds the feature to
 * remove an element and remove all elements.
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
interface Grid : Matrix<Element> {
    fun clear(columnIndex: Int, rowIndex: Int)
    fun clear()
}