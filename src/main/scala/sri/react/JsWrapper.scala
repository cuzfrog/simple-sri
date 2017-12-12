package sri.react

import scala.language.implicitConversions
import scala.scalajs.js

/**
 * Case classes cannot extends js.Object.
 */
@js.native
private sealed trait JsWrapper[T] extends js.Object {
  val value: T = js.native
}

private object JsWrapper {
  implicit final def apply[T](t: T): JsWrapper[T] = {
    js.Dynamic.literal("value" -> t.asInstanceOf[js.Any]).asInstanceOf[JsWrapper[T]]
  }

  implicit final def convert[T](w: JsWrapper[T]): T = w.value
}