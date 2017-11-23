package anywhere.component

import anywhere.Store
import sri.core.{ComponentP, CreateElement, ReactElement, ReactRenderNode}
import sri.web.vdom.tagsPrefix_<^._

class FilterList extends ComponentP[FilterList.Props] {

  override def render(): ReactRenderNode = {
    implicit val store: Store = props.store
    <.div()(
      FilterInput(),
      ListView()
    )
  }
}

object FilterList {
  class Props(val store: Store)

  def apply()(implicit store: Store): ReactElement = CreateElement[FilterList](new Props(store))
}