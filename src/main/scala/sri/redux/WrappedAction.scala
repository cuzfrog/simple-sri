package sri.redux

import scala.scalajs.js

private trait WrappedAction extends js.Object {
  val `type`: String
  val scalaJsReduxAction: js.Any
}

private object WrappedAction {
  val ActionType = "scalaJsReduxAction"
  def apply[A](a: A): WrappedAction = js.Dynamic.literal(
    `type` = ActionType,
    scalaJsReduxAction = a.asInstanceOf[js.Any]
  ).asInstanceOf[WrappedAction]
}
