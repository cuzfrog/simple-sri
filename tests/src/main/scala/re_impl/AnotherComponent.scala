package re_impl

import sri.react.{Component, CreateElement, ReactElement, ReactRenders}

final class AnotherComponent extends Component {
  override type P = AnotherComponent.Props

  override def render(props: P,
                      getState: () => S,
                      setState: (S => S) => Unit): ReactRenders = {
    s"Text(${props.value})"
  }
}

object AnotherComponent {
  case class Props(value: String)
  def apply(value: String): ReactElement = CreateElement[AnotherComponent](Props(value))
}
