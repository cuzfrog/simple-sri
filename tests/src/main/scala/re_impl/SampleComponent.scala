package re_impl

import sri.react._

final class SampleComponent extends ComponentP[SampleComponent.Props] {
  override def render(): ReactRenders = {
    s"Value(${props.value})"
  }
}

object SampleComponent {
  case class Props(value: Int)
  def apply(value: Int): ReactElement = CreateElement[SampleComponent](Props(value))
}