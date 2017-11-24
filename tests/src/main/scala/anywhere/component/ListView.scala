package anywhere.component

import anywhere.AppCircuit
import sri.core.{ComponentP, CreateElement, ReactElement, ReactRenderNode}
import sri.web.vdom.tagsPrefix_<^._

class ListView extends ComponentP[ListView.Props] {
  override def render(): ReactRenderNode = {
    <.ul()(
      props.elements.zipWithIndex.map { case (v, i) =>
        <.li(^.key := i)(v)
      }: _*
    )
  }
}

object ListView {

  case class Props(elements: Seq[String])

  def apply(): ReactElement = {
    AppCircuit.connect(_.filterModel.filteredElements)(proxy =>
      CreateElement[ListView](Props(proxy.apply())))
  }
}