package anywhere.component

import sri.core.{Component, CreateElement, ReactElement, ReactRenderNode}
import sri.web.vdom.tagsPrefix_<^._

class BasicInput extends Component[BasicInput.Props, BasicInput.State] {
  initialState(BasicInput.State(""))

  override def render(): ReactRenderNode = {
    <.input(
      ^.value := state.value,
      ^.onChange := callback
    )
  }

  private val callback = (event: ReactEventI) => {
    val value = event.target.value //event value must be fetched synchronously.
    setState(s => s.copy(value = value))
    println(value)
  }
}

object BasicInput {
  def apply(props: Props = Props()): ReactElement = CreateElement[BasicInput](props)

  case class State(value: String)
  case class Props()
}

