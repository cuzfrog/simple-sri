package sri.redux

import scala.concurrent.Promise
import scala.scalajs.js

@js.native
private sealed trait StoreJS[S] extends js.Object {

  def getState(): S = js.native

  def dispatch(action: js.Object): js.Object = js.native

  def subscribe(listener: js.Function): js.Function = js.native

  def replaceReducer(nextReducer: js.Function): Unit = js.native

}

final class Store[S, A](storeJS: StoreJS[S]) {
  def getState: S = storeJS.getState()

  def dispatch(action: A): A = {
    storeJS.dispatch(WrappedAction(action))
      .asInstanceOf[WrappedAction].scalaJsReduxAction.asInstanceOf[A]
  }

//  def dispatchAsync(action: A): Promise[A] = {
//    val promise = Promise[A]()
//    storeJS.dispatch(promise.asInstanceOf[js.Object])
//    promise.success(action)
//  }
}