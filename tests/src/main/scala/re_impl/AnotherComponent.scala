package re_impl

import sri.react._

import scala.scalajs.js.UndefOr

final class AnotherComponent extends ComponentS[AnotherComponent.State] {
  override def getInitialState: UndefOr[AnotherComponent.State] = AnotherComponent.State()
  override def render(): ReactRenders = {
    s"Text(${state.value})"
  }
}

object AnotherComponent {
  case class State(value: String = "initial value")
  def apply(value: String): ReactElement = CreateElement[AnotherComponent](null)
}
