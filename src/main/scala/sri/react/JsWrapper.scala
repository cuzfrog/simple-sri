package sri.react

import scala.language.implicitConversions
import scala.reflect.ClassTag
import scala.scalajs.js

/**
 * Case classes cannot extends js.Object.
 */
@js.native
sealed trait JsWrapper[T] extends js.Object {
  val clazz: js.UndefOr[Class[_]] = js.native
  val value: T = js.native
}

private object JsWrapper {
  final def apply[T, C <: Component : ClassTag](t: T): JsWrapper[T] = {
    val tpe = implicitly[ClassTag[C]].runtimeClass match{
      case rc if rc == classOf[Nothing] => js.undefined
      case clazz => clazz
    }
    js.Dynamic.literal(
      clazz = tpe.asInstanceOf[js.Any],
      value = t.asInstanceOf[js.Any]
    ).asInstanceOf[JsWrapper[T]]
  }
  implicit final def unwrap[T](w: JsWrapper[T]): T = w.value
}