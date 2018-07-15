package it.matteopellegrino.pagedgrid.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import it.matteopellegrino.pagedgrid.grid.ConcreteGridFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pageFactory = ConcreteGridFactory(baseContext)
        val page = pageFactory.createEmpty(4, 4)
        pagedGridView.pages = listOf(page)
    }
}
