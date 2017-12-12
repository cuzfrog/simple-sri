package sri

import scala.scalajs.js
import scala.scalajs.js.|

package object react {
  type Fragments[T] = js.Array[T]

  type ReactText = String | Double
  type ReactEmpty = Boolean | Null

//  type ReactElementNode = ReactElement | js.Array[ReactElement]
//  type ReactNode = ReactElementNode | ReactText | js.Array[String] | js.Array[Double]
//


  type ReactRender = ReactText | ReactEmpty | Element
  type ReactRenders = ReactRender | Fragments[ReactRender]
}
