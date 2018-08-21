package io.matteopellegrino.pagedgrid.grid

/**
 * Generic representation of a matrix and basic model utilities.
 * The coordinates are the Android standard ones:
 * the first coordinate is the column index, the second one is the row index.
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
interface Matrix<E> : Iterable<E> {
    var columns: Int
    var rows: Int

    @Throws(IndexOutOfBoundsException::class)
    operator fun get(columnIndex: Int, rowIndex: Int): E

    @Throws(IndexOutOfBoundsException::class)
    operator fun set(columnIndex: Int, rowIndex: Int, element: E)

    fun contains(element: E): Boolean

    /**
     * Iterate the matrix invoking [action] for each element.
     * [action#x] is the column index
     * [action#y] is the row index
     * [action#element] the element positioned at the coordinate (x,y)
     */
    fun forEachIndexed(action: (x: Int, y: Int, element: E) -> Unit)
}