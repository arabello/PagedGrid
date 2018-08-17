package it.matteopellegrino.pagedgrid.element

import android.view.View

/**
 * Describes common behaviour for a [Element] implementation.
 * Provides a property for the on inflate element view event
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
abstract class AbstractElement : Element{
    internal open var onInflate: ((View) -> Unit)? = null
    override fun setOnInflateViewListener(listener: (View) -> Unit) {
        onInflate = listener
    }
}