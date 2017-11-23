package anywhere.component

import anywhere.Store
import sri.core.{ComponentP, CreateElement, ReactElement, ReactRenderNode}
import sri.web.vdom.tagsPrefix_<^._

class ListView extends ComponentP[ListView.Props] {
  override def render(): ReactRenderNode = {
    <.ul()(
      props.elements.map { v =>
        <.li()(v)
      }: _*
    )
  }
}

object ListView {
  class Props(store: Store) {
    val elements: Seq[String] = store.getState().filteredElements
  }
  def apply()(implicit store: Store): ReactElement = CreateElement[ListView](new Props(store))
}
