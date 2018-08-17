package it.matteopellegrino.pagedgrid.element

import android.view.View
import android.view.ViewGroup

/**
 * Generic element part of a [it.matteopellegrino.pagedgrid.grid.Grid].
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
interface Element {

    /**
     * Create the view for this specific element,
     * attach it to the [parent] and returns the view created.
     */
    fun inflateView(parent: ViewGroup): View

    /**
     *  Set the on inflate element view event.
     *  Through this method you can access the view when created by
     *  the argument of the callback specified.
     */
    fun setOnInflateViewListener(listener: (View) -> Unit)
}