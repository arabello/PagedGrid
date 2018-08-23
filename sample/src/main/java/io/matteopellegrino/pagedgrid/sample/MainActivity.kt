package io.matteopellegrino.pagedgrid.sample

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import io.matteopellegrino.pagedgrid.element.AbstractElement
import io.matteopellegrino.pagedgrid.element.BitmapIcon
import io.matteopellegrino.pagedgrid.element.DrawableIcon
import io.matteopellegrino.pagedgrid.grid.EmptyGrid
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a simple empty grid, with 4 columns and 5 rows. This will be the first page
        val page1 = EmptyGrid(4, 5)

        // Fill the grid with drawables from resources.
        // DrawableIcon is a ready to use layout to show an image with a title
        page1[0, 0] = DrawableIcon("Activity", R.drawable.ic_format_align_justify_black_24dp)
        page1[1, 0] = DrawableIcon("Airplay", R.drawable.ic_format_align_center_black_24dp)
        page1[2, 0] = DrawableIcon("Award", R.drawable.ic_format_align_left_black_24dp)
        page1[3, 0] = DrawableIcon("Chart", R.drawable.ic_format_align_right_black_24dp)

        page1[0, 2] = DrawableIcon("Photo Filter", R.drawable.ic_photo_filter_black_24dp)
        page1[2, 2] = DrawableIcon("Portrait", R.drawable.ic_portrait_black_24dp)

        // Create a second page.
        val page2 = EmptyGrid(2, 3)

        // Prepare some resources to load as bitmaps.
        // In a realistic case these should be load from internal storage or via network.
        val imgs = arrayOf(
                arrayOf(R.mipmap.img_1, R.mipmap.img_2, R.mipmap.img_3),
                arrayOf(R.mipmap.img_4, R.mipmap.img_5, R.mipmap.img_6))

        // Iterate over the empty grid of the second page.
        // An helper method allows accessing the correct coordinates
        page2.forEachIndexed { x, y, _ ->
            // Assign the cell with coordinate (x, y) a new Element using the BitmapIcon implementation
            page2[x, y] = BitmapIcon("($x, $y)", BitmapFactory.decodeResource(resources, imgs[x][y]))
        }

        val page3 = EmptyGrid(1, 3)

        val cards = arrayOf(R.mipmap.img_7, R.mipmap.img_8, R.mipmap.img_9)

        page3.forEachIndexed { x, y, _ ->

            // For custom view you only need to implemente Element or extend AbstractElement (recommended)
            // Here an example using an anonymous object to create a simple ImageView (avoiding the title mandatory in DrawableIcon/BitmapIcon)
            page3[x, y] = object : AbstractElement() {
                override fun inflateView(parent: ViewGroup): View {
                    val img = ImageView(baseContext)
                    img.scaleType = ImageView.ScaleType.CENTER_CROP
                    img.setImageBitmap(BitmapFactory.decodeResource(resources, cards[y]))
                    return img
                }
            }
        }
        // Effectively show the pages assigning them to the corresponding property of PagedGridView
        // No notify is required. The adapter will update by itself.
        pagedGridView.pages = mutableListOf(page1, page2, page3)

        // If you want some iteration with the user
        // you can access to every view of each cell, but the empty ones.
        pagedGridView.pages.forEach { page ->
            page.forEachIndexed { x, y, elem ->

                // This method offers by Element allows you accessing the view when inflating (created)
                // in order to attach whatever android event to the view, such as a click listener
                elem.setOnInflateViewListener { view ->
                    view.setOnClickListener {
                        Toast.makeText(baseContext, "View on ($x, $y) clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}
