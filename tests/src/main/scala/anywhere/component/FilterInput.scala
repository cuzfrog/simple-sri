package anywhere.component

import anywhere.{Action, FilterChange, Store}
import sri.core.{ComponentP, CreateElement, ReactElement}
import sri.web.vdom.tagsPrefix_<^._

class FilterInput extends ComponentP[FilterInput.Props] {
  override def render() = {
    <.input(
      ^.value := props.value,
      ^.onChange := props.onChange
    )
  }
}

object FilterInput {
  class Props(store: Store) {
    val value: String = store.getState().filterValue
    val onChange: ReactEventI => Action = (event: ReactEventI) => {
      store.dispatch(FilterChange(event.target.value))
    }
  }

  def apply()(implicit store: Store): ReactElement = CreateElement[FilterInput](new Props(store))
}