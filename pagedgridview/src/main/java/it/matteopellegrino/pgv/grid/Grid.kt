package it.matteopellegrino.pgv.grid

import android.view.View
import it.matteopellegrino.pgv.element.ElementFactory

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
interface Grid : Matrix<View> {
    fun clear(columnIndex: Int, rowIndex: Int)
    fun clear()
}