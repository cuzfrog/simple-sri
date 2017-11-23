package sri.redux

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@JSImport("redux", JSImport.Namespace)
@js.native
private object ReduxJS extends js.Object {

  def createStore[S, A](reducer: js.Function,
                        initialState: S = js.undefined,
                        enhancer: js.Function = ???): StoreJS[S] = js.native

  def combineReducers(reducers: js.Dictionary[js.Function]): js.Function = js.native

  def applyMiddleware(middlewares: js.Function*): js.Function = js.native

  def bindActionCreators(actionCreators: js.Function,
                         dispatch: js.Function): js.Function = js.native

  def compose(functions: js.Function*): js.Function = js.native
}

object Redux {
  @inline
  def createStore[S, A](reducer: Function2[S, A, S],
                        initialState: S = js.undefined,
                        enhancer: js.UndefOr[js.Function] = js.undefined): Store[S, A] = {
    val wrappedReducer: (S, |[A, js.Object]) => S = (s: S, a: A | js.Object) => {
      val aDyn = a.asInstanceOf[js.Dynamic]
      if (aDyn.`type`.asInstanceOf[String] == WrappedAction.ActionType) {
        reducer(s, aDyn.scalaJsReduxAction.asInstanceOf[A])
      }
      else s
    }

    val storeJS = ReduxJS.createStore[S, A](wrappedReducer, initialState)
    new Store[S, A](storeJS)
  }
}

//  @JSImport("react-redux", "Provider")
//  private object Provider extends js.Any
//
//  def provider[T](store: Store[T])(children: ReactNode*): ReactElement = {
//    React.createElement(Provider, store, children: _*)
//  }

