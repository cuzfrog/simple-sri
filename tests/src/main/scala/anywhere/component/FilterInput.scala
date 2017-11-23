package anywhere.component

import anywhere.{Action, FilterChange, Store}
import sri.core.{ComponentP, CreateElement, ReactElement}
import sri.web.vdom.tagsPrefix_<^._

class FilterInput extends ComponentP[FilterInput.Props] {
  override def render() = {
    println(s"filter input rendered with value: ${props.value}")
    <.input(
      ^.value := props.value,
      ^.onChange := props.onChange
    )
  }
}

object FilterInput {
  class Props(store: Store) {
    def value: String = store.getState.filterValue
    def onChange: ReactEventI => Unit = (event: ReactEventI) => {
      event.defaultPrevented
      store.dispatch(FilterChange(event.target.value))
    }
  }
  
  def apply()(implicit store: Store): ReactElement = CreateElement[FilterInput](new Props(store))
}