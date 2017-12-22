package anywhere.component

import sri.react._
import sri.web.vdom.tagsPrefix_<^._

final class BasicInput extends Component[BasicInput.Props, BasicInput.State] {
  override def getInitialState = BasicInput.State(props.initialValue)

  override def render(): ReactRenders = {
    println(s"BasicInput rendered with state:$state")
    <.input(
      ^.value := state.value,
      ^.onChange := callback
    )
  }

  private val callback = (event: ReactEventI) => {
    val value = event.target.value
    setState(s => s.copy(value = value))
    println(s"BasicInput onChange, new value: '$value'")
  }
}

object BasicInput {
  def apply(props: Props = Props()): CompositeElement = CreateElement[BasicInput](props)

  case class State(value: String)
  case class Props(initialValue: String = "", v2: Int = 0)
}

