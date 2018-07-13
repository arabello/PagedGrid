package it.matteopellegrino.pgv.grid

import android.content.Context

class ConcreteGridFactory(val context: Context) : GridFactory {
    override fun createEmpty(columns: Int, rows: Int): Grid = ConcreteGrid(context, columns, rows)
}