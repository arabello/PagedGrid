package it.matteopellegrino.pagedgrid.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import it.matteopellegrino.pagedgrid.element.DrawableIcon
import it.matteopellegrino.pagedgrid.element.Empty
import it.matteopellegrino.pagedgrid.grid.EmptyGrid
import it.matteopellegrino.pagedgrid.grid.Grid
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pages = mutableListOf<Grid>()

        val page = EmptyGrid(4, 4)

        page[1, 2] = DrawableIcon("Assignment", R.drawable.ic_outline_assignment_24px)
        page[2, 3] = DrawableIcon("Assignment", R.drawable.ic_outline_assignment_24px)
        page[3, 1] = DrawableIcon("Assignment", R.drawable.ic_outline_assignment_24px)

        pages += page

        pages += EmptyGrid(3, 4)
        pages += EmptyGrid(4, 3)

        pagedGridView.pages = pages
    }
}
