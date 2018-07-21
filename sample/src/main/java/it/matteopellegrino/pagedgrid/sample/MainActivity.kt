package it.matteopellegrino.pagedgrid.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import it.matteopellegrino.pagedgrid.element.DrawableIcon
import it.matteopellegrino.pagedgrid.grid.EmptyGrid
import it.matteopellegrino.pagedgrid.grid.Grid
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pages = mutableListOf<Grid>()

        val page = EmptyGrid(4, 4)

        page[1, 2] = DrawableIcon("(1,2)", R.drawable.ic_outline_assignment_24px)
        page[2, 3] = DrawableIcon("(2,3)", R.drawable.ic_outline_assignment_24px)
        page[3, 1] = DrawableIcon("(3,1)", R.drawable.ic_outline_assignment_24px)

        pages += page

        pages += EmptyGrid(3, 4)
        pages += EmptyGrid(4, 3)

        pagedGridView.pages = pages

        pagedGridView.pages.forEach {
            it.forEachIndexed { x, y, elem ->
                elem.setOnInflateViewListener { view ->
                    view.setOnClickListener {
                        Toast.makeText(baseContext, "View on ($x, $y) clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}
