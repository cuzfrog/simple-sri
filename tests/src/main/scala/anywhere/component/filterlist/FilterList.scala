package anywhere.component.filterlist

import sri.react._
import sri.web.vdom.tagsPrefix_<^._

class FilterList extends ComponentP[FilterList.Props] {

  override def render(): ReactRenders = {
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