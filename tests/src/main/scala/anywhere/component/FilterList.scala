package anywhere.component

import sri.core.{ComponentP, CreateElement, ReactElement, ReactRenderNode}
import sri.web.vdom.tagsPrefix_<^._

class FilterList extends ComponentP[FilterList.Props] {

  override def render(): ReactRenderNode = {
    <.div()(
      FilterInput(),
      ListView()
    )
  }
}

object FilterList {
  case class Props()
  def apply(): ReactElement = CreateElement[FilterList](Props())
}