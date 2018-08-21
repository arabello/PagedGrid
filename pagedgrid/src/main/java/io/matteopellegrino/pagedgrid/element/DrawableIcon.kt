package io.matteopellegrino.pagedgrid.element

import android.support.annotation.DrawableRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.matteopellegrino.pagedgrid.R

/**
 * Concrete representation of [AbstractIcon] using a [res] drawable id as icon.
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
data class DrawableIcon(override val title: String, @DrawableRes val res: Int): AbstractIcon(title) {
    override fun inflateView(parent: ViewGroup): View {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.element_icon, parent, false)

        v.findViewById<TextView>(R.id.elementIconTitle).text = title
        v.findViewById<ImageView>(R.id.elementIconImage).setImageResource(res)

        onInflate?.invoke(v)

        return v
    }
}