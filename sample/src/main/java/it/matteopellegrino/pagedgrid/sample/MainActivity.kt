package it.matteopellegrino.pagedgrid.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import it.matteopellegrino.pagedgrid.element.ConcreteElementFactory
import it.matteopellegrino.pagedgrid.grid.ConcreteGridFactory
import it.matteopellegrino.pagedgrid.grid.Grid
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val elementFactory = ConcreteElementFactory(baseContext)
        val pageFactory = ConcreteGridFactory(baseContext)
        val pages = mutableListOf<Grid>()

        var page = pageFactory.createEmpty(4, 4)

        page[1, 2] = elementFactory.createIconButton("Assignment", R.drawable.ic_outline_assignment_24px)
        page[2, 3] = elementFactory.createIconButton("Assignment", R.drawable.ic_outline_assignment_24px)
        page[3, 1] = elementFactory.createIconButton("Assignment", R.drawable.ic_outline_assignment_24px)

        pages += page

        pages += pageFactory.createEmpty(3, 4)
        pages += pageFactory.createEmpty(4, 3)

        pagedGridView.pages = pages
    }
}
