package sri.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react", JSImport.Namespace)
private object ReactJS {
  def createElement(tpe: js.Any,
                    props: js.Any,
                    children: js.Any*): Element = js.native

  def cloneElement(element: Element,
                   props: js.Any,
                   children: js.Any*): Element = js.native

  def isValidElement(obj: js.Object): Boolean = js.native

  val Children: Children = js.native
}

@js.native
private sealed trait Children extends js.Object {
  def map(children: js.Object,
          fn: js.Function1[js.Object, _]): js.Array[js.Object] = js.native
  def forEach(children: js.Object, fn: js.Function1[js.Object, _]): Unit = js.native
  def count(children: js.Object): Int = js.native
  def only(children: js.Object): js.Object = js.native
  def toArray(children: js.Object): js.Array[js.Object] = js.native
}

object React{

}