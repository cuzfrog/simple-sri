package sri.core

import scala.scalajs.js
import scala.scalajs.js.UndefOr

@js.native
trait ReactElement extends js.Object {

  type Instance <: ReactClass

  def key: UndefOr[String] = js.native

  def ref: UndefOr[js.Function] = js.native
}
