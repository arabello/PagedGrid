package it.matteopellegrino.pagedgrid.element

/**
 * Describes the common property for every icon [AbstractElement].
 * Each icon element should provide a [title]
 *
 * @author Matteo Pellegrino matteo.pelle.pellegrino@gmail.com
 */
abstract class AbstractIcon(open val title: String) : AbstractElement()