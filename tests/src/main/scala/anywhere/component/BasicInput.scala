package anywhere.component

import sri.react._
import sri.web.vdom.tagsPrefix_<^._

class BasicInput extends Component[BasicInput.Props, BasicInput.State] {
  override def getInitialState = BasicInput.State("")

  override def render(): ReactRenders = {
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
  def apply(props: Props = Props()): ReactElement = CreateElement(new BasicInput)(props)

  case class State(value: String)
  case class Props()
}

