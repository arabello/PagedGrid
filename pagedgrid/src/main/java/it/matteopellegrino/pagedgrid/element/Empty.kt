package it.matteopellegrino.pagedgrid.element

import android.view.View
import android.view.ViewGroup
import android.widget.Space

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
class Empty: AbstractElement() {
    override fun inflateView(parent: ViewGroup): View {
        val v = Space(parent.context)

        onInflate?.invoke(v)

        return v
    }
}