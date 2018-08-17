package it.matteopellegrino.pagedgrid.sample

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import it.matteopellegrino.pagedgrid.element.BitmapIcon
import it.matteopellegrino.pagedgrid.element.DrawableIcon
import it.matteopellegrino.pagedgrid.grid.EmptyGrid
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val page1 = EmptyGrid(4, 4)

        page1[1, 2] = DrawableIcon("(1,2)", R.drawable.ic_outline_assignment_24px)
        page1[2, 3] = DrawableIcon("(2,3)", R.drawable.ic_outline_assignment_24px)
        page1[3, 1] = DrawableIcon("(3,1)", R.drawable.ic_outline_assignment_24px)

        val page2 = EmptyGrid(3, 4)
        page2[2, 2] = BitmapIcon("(3,2)", BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))

        val page3 = EmptyGrid(4, 3)

        pagedGridView.pages = mutableListOf(page1, page2, page3)

        pagedGridView.pages.forEach { page ->
            page.forEachIndexed { x, y, elem ->
                elem.setOnInflateViewListener { view ->
                    view.setOnClickListener {
                        Toast.makeText(baseContext, "View on ($x, $y) clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}
