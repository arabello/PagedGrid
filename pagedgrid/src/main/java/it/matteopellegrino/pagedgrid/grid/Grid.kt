package it.matteopellegrino.pagedgrid.grid

import android.view.View

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
interface Grid : Matrix<View> {
    fun clear(columnIndex: Int, rowIndex: Int)
    fun clear()
}