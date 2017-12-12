package sri.react

import scala.scalajs.js

@js.native
private sealed trait JsWrapper[T] extends js.Object {
  val value: T = js.native
}

private object JsWrapper {
  def apply[T](t: T): JsWrapper[T] = {
    js.Dynamic.literal("value" -> t.asInstanceOf[js.Any]).asInstanceOf[JsWrapper[T]]
  }
}