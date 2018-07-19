package it.matteopellegrino.pagedgrid.element

import android.view.View
import android.view.ViewGroup

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
interface Element {
    fun setOnInflateViewListener(listener: (View) -> Unit)
    fun inflateView(parent: ViewGroup): View
}