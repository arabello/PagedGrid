package it.matteopellegrino.pagedgrid.element

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Space
import android.widget.TextView
import it.matteopellegrino.pagedgrid.R

class ConcreteElementFactory(val context: Context) : ElementFactory {

    private val layoutInflater = LayoutInflater.from(context)!!

    override fun createEmpty(): View = Space(context)

    override fun createIconButton(title: String, img: Bitmap): View {
        val v = layoutInflater.inflate(R.layout.element_icon, null)

        v.findViewById<TextView>(R.id.elementIconTitle).text = title
        v.findViewById<ImageView>(R.id.elementIconImage).setImageBitmap(img)

        return v
    }

    override fun createIconButton(title: String, @DrawableRes res: Int): View {
        val v = layoutInflater.inflate(R.layout.element_icon, null)

        v.findViewById<TextView>(R.id.elementIconTitle).text = title
        v.findViewById<ImageView>(R.id.elementIconImage).setImageResource(res)

        return v
    }

}