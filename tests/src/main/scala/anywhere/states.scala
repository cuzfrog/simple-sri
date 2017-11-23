package anywhere

import sri.redux.Redux

object Constants {
  val elements: Seq[String] = Array(
    "java", "scala", "groovy", "kotlin"
  )
}

case class State(filterValue: String,
                 filteredElements: Seq[String])
//abstract class BaseProps(implicit store: Store[State])

object StoreFactory {
  private val reducer: Function2[State, Action, State] =
    (state: State, action: Action) => action match {
      case FilterChange(v) =>
        println(s"filter changed: $v")
        state.copy(
          filterValue = v,
          filteredElements = Constants.elements.filter(e => v.isEmpty || e.contains(v)))
    }

  def init: Store = {
    Redux.createStore(
      reducer,
      State(
        filterValue = "",
        filteredElements = Constants.elements
      )
    )
  }
}