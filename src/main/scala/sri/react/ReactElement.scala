package sri.react

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
sealed trait ReactElement extends js.Object {
  //def `$$typeof` = js.native
  def `type`: String | js.Dynamic = js.native
  def props: js.Object = js.native
  def ref: String | Boolean | Double | Null = js.native
  def key: String | Null = js.native
}
