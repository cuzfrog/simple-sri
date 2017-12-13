package sri.react

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
sealed trait ReactElement extends js.Object {
  type P
  //def `$$typeof` = js.native
  def `type`: String | js.Function1[JsWrapper[P], ReactElement] = js.native
  def props: JsWrapper[P] = js.native
  def ref: String | Boolean | Double | Null = js.native
  def key: String | Null = js.native
}
