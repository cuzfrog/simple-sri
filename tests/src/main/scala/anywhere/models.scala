package anywhere

import diode._
import diode.react.ReactConnector

object Constants {
  val elements: Seq[String] = Array(
    "java", "scala", "groovy", "kotlin"
  )
}

case class RootModel(filterModel: FilterModel)

case class FilterModel(filterValue: String,
                       filteredElements: Seq[String])

object AppCircuit extends Circuit[RootModel] with ReactConnector[RootModel] {
  override protected def initialModel: RootModel = RootModel(
    FilterModel(
      filterValue = "",
      filteredElements = Constants.elements)
  )

  private val filterHandler = new ActionHandler(zoomTo(_.filterModel)) {
    override protected def handle: PartialFunction[Any, ActionResult[RootModel]] = {
      case FilterChange(v) => updated(
        FilterModel(filterValue = v,
          filteredElements = Constants.elements.filter(e => v.isEmpty || e.contains(v)))
      )
    }
  }

  override protected def actionHandler = composeHandlers(filterHandler)
}