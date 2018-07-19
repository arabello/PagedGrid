package it.matteopellegrino.pagedgrid.element

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import it.matteopellegrino.pagedgrid.R

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
class BitmapIcon(override val title: String, val bitmap: Bitmap): AbstractIcon(title) {
    override fun inflateView(parent: ViewGroup): View {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.element_icon, parent, false)

        v.findViewById<TextView>(R.id.elementIconTitle).text = title
        v.findViewById<ImageView>(R.id.elementIconImage).setImageBitmap(bitmap)

        onInflate?.invoke(v)

        return v
    }
}