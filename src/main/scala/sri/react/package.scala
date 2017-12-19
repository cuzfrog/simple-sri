package sri

import sri.global.{JsObject, JsObjectConvertible}

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
    def toJsObject: JsObject[T] = ev.toJs(in)
  }

  implicit final class JsObject2CaseClassOps[T <: Product with Serializable](in: JsObject[T]) {
    def toScalaClass(implicit ev: JsObjectConvertible[T]): T = ev.fromJs(in)
  }
}
