package anywhere

import sri.redux.{Redux, Store}

object Constants {
  val elements: Seq[String] = Array(
    "java", "scala", "groovy", "kotlin"
  )
}

case class State(filterValue: String,
                 filteredElements: Seq[String])
//abstract class BaseProps(implicit store: Store[State])

object Store {
  private val reducer: Function2[State, Action, State] = new Function2[State, Action, State] {
    override def apply(state: State,
                       action: Action): State = action match {
      case FilterChange(v) => state.copy(
        filterValue = v,
        filteredElements = Constants.elements.filter(e => v.isEmpty || e.contains(v)))
    }
  }

  def init: Store[State] = {
    Redux.createStore(
      reducer,
      State(
        filterValue = "",
        filteredElements = Constants.elements
      )
    )
  }
}