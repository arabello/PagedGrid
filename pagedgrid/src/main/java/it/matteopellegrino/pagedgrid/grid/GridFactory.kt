package it.matteopellegrino.pagedgrid.grid

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
interface GridFactory {
    fun createEmpty(columns: Int, rows: Int): Grid
}