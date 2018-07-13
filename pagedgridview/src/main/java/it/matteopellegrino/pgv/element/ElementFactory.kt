package it.matteopellegrino.pgv.element

import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.view.View

/**
 * TODO: Add class description
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
interface ElementFactory {
    fun createEmpty(): View
    fun createIconButton(title: String, img: Bitmap): View
    fun createIconButton(title: String, @DrawableRes res: Int): View
}