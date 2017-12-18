package sri

import sri.support.JsObjectConvertible

import scala.scalajs.js
import scala.scalajs.js.|

package object react {
  type Fragments[T] = js.Array[T]

  type ReactText = String | Double
  type ReactEmpty = Boolean | Null

  type ReactElementNode = ReactElement | js.Array[ReactElement]
  type ReactNode = ReactElementNode | ReactText | js.Array[String] | js.Array[Double]

  type ReactRender = ReactText | ReactEmpty | ReactElement
  type ReactRenders = ReactRender | Fragments[ReactRender]

  implicit final class CaseClass2JsObjectOps
  [T <: Product with Serializable](in: T)
                                  (implicit ev: JsObjectConvertible[T]) {
    def toJsObject: js.Object = ev.toJs(in)
  }

  implicit final class JsObject2CaseClassOps(in: js.Object) {
    def toScalaClass[T <: Product with Serializable](implicit ev: JsObjectConvertible[T]): T = ev.fromJs(in)
  }
}
