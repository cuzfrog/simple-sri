package sri.redux

import scala.scalajs.js

@js.native
trait Store[T] extends js.Object {

  def getState(): T = js.native

  def dispatch(action: js.Object): js.Object = js.native

  def subscribe(listener: js.Function): js.Function = js.native

  def replaceReducer(nextReducer: js.Function): Unit = js.native

}