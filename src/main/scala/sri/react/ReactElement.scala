package sri.react

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
sealed trait ReactElement extends js.Object{
  protected type Props
  protected type Constructor = js.Function1[JsPropsWrapper[Props], ReactElement]
  //def `$$typeof` = js.native
  def ref: String | Boolean | Double | Null = js.native
  def key: String | Null = js.native
}

@js.native
sealed trait CompositeElement extends ReactElement {
  def `type`: Constructor = js.native
  def props: JsPropsWrapper[Props] = js.native
}

@js.native
sealed trait DomElement extends ReactElement{
  def `type`: String = js.native
  def props: Props = js.native
}