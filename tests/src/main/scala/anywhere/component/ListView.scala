package anywhere.component

import anywhere.State
import sri.core.{ComponentP, CreateElement, ReactElement, ReactRenderNode}
import sri.redux.Store
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
  case class Props(implicit store: Store) {
    val elements = store.getState().filteredElements
  }
  def apply()(implicit store: Store): ReactElement = CreateElement[ListView](Props())
}
