package anywhere.component.filterlist

import anywhere.AppCircuit
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

class ListView extends ComponentP[ListView.Props] {
  override def render(): ReactRenders = {
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
      CreateElement(new ListView)(Props(proxy.apply())))
  }
}
