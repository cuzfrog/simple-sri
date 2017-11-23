package sri.redux

import scala.scalajs.js

@js.native
sealed trait Store[S, A] extends js.Object {

  def getState(): S = js.native

  def dispatch(action: A): A = js.native

  def subscribe(listener: js.Function): js.Function = js.native

  def replaceReducer(nextReducer: js.Function): Unit = js.native

}