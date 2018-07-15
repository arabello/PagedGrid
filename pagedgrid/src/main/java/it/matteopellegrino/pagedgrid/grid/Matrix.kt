package it.matteopellegrino.pagedgrid.grid

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
interface Matrix<E> : Iterable<E> {
    var columns: Int
    var rows: Int

    @Throws(IndexOutOfBoundsException::class)
    operator fun get(columnIndex: Int, rowIndex: Int): E

    @Throws(IndexOutOfBoundsException::class)
    fun set(columnIndex: Int, rowIndex: Int, element: E)

    fun contains(element: E): Boolean
}