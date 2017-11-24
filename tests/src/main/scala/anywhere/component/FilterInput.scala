package anywhere.component

import anywhere.{AppCircuit, FilterChange}
import sri.core.{ComponentP, CreateElement, ReactElement, ReactRenderNode}
import sri.web.vdom.tagsPrefix_<^._

class FilterInput extends ComponentP[FilterInput.Props] {
  override def render(): ReactRenderNode = {
    println(s"filter input rendered with value: ${props.value}")
    <.input(
      ^.value := props.value,
      ^.onChange := props.onChange
    )
  }
}

object FilterInput {
  case class Props(value: String, onChange: ReactEventI => Unit)

  def apply(): ReactElement = {
    AppCircuit.connect(_.filterModel) { proxy =>
      def value: String = proxy().filterValue
      val onChange: ReactEventI => Unit = (event: ReactEventI) => {
        val v = event.target.value
        println(s"filter input event! $v | $value")
        event.defaultPrevented
        proxy.dispatch(FilterChange(v))
      }
      CreateElement[FilterInput](Props(value, onChange))
    }
  }
}