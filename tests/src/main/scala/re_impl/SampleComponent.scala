package re_impl

import sri.react._

final class SampleComponent extends Component {
  override type P = SampleComponent.Props

  override def render(props: P,
                      getState: () => S,
                      setState: (S => S) => Unit): ReactRenders = {
    s"Value(${props.value})"
  }
}

object SampleComponent {
  case class Props(value: Int)
  def apply(value: Int): ReactElement = CreateElement[SampleComponent](Props(value))
}