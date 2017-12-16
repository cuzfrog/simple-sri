package sri.react

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.|

/**
 * Case classes cannot extends js.Object.
 */
@js.native
sealed trait JsWrapper[T] extends js.Object {
  protected val value: T = js.native
}

@js.native
sealed trait JsPropsWrapper[T] extends JsWrapper[T] {
  type ComponentClass <: BaseComponent {type Props = T}
  val instance: ComponentClass = js.native
  val key: js.UndefOr[String | Int] = js.native
  val ref: js.UndefOr[js.Function1[_, Unit]] = js.native
}

@js.native
private sealed trait JsStateWrapper[T] extends JsWrapper[T]

private object JsStateWrapper {
  final def apply[T](t: T): JsStateWrapper[T] = {
    js.Dynamic.literal(
      value = t.asInstanceOf[js.Any]
    ).asInstanceOf[JsStateWrapper[T]]
  }
}
private object JsPropsWrapper {
  final def apply[C <: BaseComponent]
  (inst: C)(t: C#Props,
            key: js.UndefOr[String | Int] = js.undefined,
            ref: js.UndefOr[js.Function1[_, Unit]] = js.undefined): JsPropsWrapper[C#Props] = {

    val props = js.Dynamic.literal(
      instance = inst.asInstanceOf[js.Any],
      value = t.asInstanceOf[js.Any]
    )
    key.foreach(k => props.updateDynamic("key")(k.asInstanceOf[js.Any]))
    ref.foreach(r => props.updateDynamic("ref")(r.asInstanceOf[js.Any]))
    props.asInstanceOf[JsPropsWrapper[C#Props]]
  }
}

private object JsWrapper{
  implicit final class WrapperOps[T](w: JsWrapper[T]) {
    def unwrap: T = {
      if (w != null) w.value
      else js.undefined.asInstanceOf[T]
    }
  }
}