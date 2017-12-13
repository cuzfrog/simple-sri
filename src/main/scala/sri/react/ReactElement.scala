package sri.react

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
sealed trait ReactElement extends js.Object {
  type Props
  //def `$$typeof` = js.native
  def `type`: String | js.Function1[JsWrapper[Props], ReactElement] = js.native
  def props: JsWrapper[Props] = js.native
  def ref: String | Boolean | Double | Null = js.native
  def key: String | Null = js.native
}
