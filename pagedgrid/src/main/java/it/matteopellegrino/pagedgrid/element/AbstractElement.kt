package it.matteopellegrino.pagedgrid.element

import android.view.View

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
abstract class AbstractElement : Element{
    internal open var onInflate: ((View) -> Unit)? = null

    override fun setOnInflateViewListener(listener: (View) -> Unit) {
        onInflate = listener
    }
}