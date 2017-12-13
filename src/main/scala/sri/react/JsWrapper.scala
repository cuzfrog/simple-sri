package sri.react

import scala.language.implicitConversions
import scala.reflect.ClassTag
import scala.scalajs.js

/**
 * Case classes cannot extends js.Object.
 */
@js.native
sealed trait JsWrapper[+T] extends js.Object {
  val value: T = js.native
}

@js.native
sealed trait JsPropsWrapper[+T] extends JsWrapper[T] {
  type Context
  val clazz: Class[Context] = js.native
}

@js.native
private sealed trait JsStateWrapper[+T] extends JsWrapper[T]

private object JsWrapper {
  final def apply[T](t: T): JsStateWrapper[T] = {
    js.Dynamic.literal(
      value = t.asInstanceOf[js.Any]
    ).asInstanceOf[JsStateWrapper[T]]
  }

  final def apply[T](t: T, componentClass: Class[_]): JsPropsWrapper[T] = {
    js.Dynamic.literal(
      clazz = componentClass.asInstanceOf[js.Any],
      value = t.asInstanceOf[js.Any]
    ).asInstanceOf[JsPropsWrapper[T]]
  }

  //implicit final def unwrap[T, W <: JsWrapper[T]](w: W): T = w.value
}