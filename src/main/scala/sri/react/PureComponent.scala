package sri.react

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}

@js.native
@JSImport("react", "Component")
private sealed abstract class ComponentJS[P, S] extends js.Object {
  protected def render(): ReactRenders

  private[react] type Props = JsWrapper[P]
  private[react] type State = JsWrapper[S]

  protected def componentWillMount(): Unit = js.native
  protected def componentDidMount(): Unit = js.native
  protected def componentWillUnmount(): Unit = js.native
  protected def forceUpdate(): Unit = js.native
  protected def displayName: String = js.native

  protected def componentDidCatch(error: js.Object, info: js.Object): Unit = js.native


  // ---- native methods that need to be wrapped ----
  @JSName("componentWillReceiveProps")
  private[react] def _componentWillReceiveProps(nextProps: Props): Unit = js.native
  @JSName("shouldComponentUpdate")
  private[react] def _shouldComponentUpdate(nextProps: Props, nextState: State): Boolean = js.native
  @JSName("componentWillUpdate")
  private[react] def _componentWillUpdate(nextProps: Props, nextState: State): Unit = js.native
  @JSName("componentDidUpdate")
  private[react] def _componentDidUpdate(prevProps: Props, prevState: State): Unit = js.native

  @JSName("constructor")
  private[react] def _constructor(props: Props): Unit = js.native

  @JSName("setState")
  private[react] def _setState(updater: (State, Props) => State): Unit = js.native
  @JSName("props")
  private[react] def _props: Props = js.native
  @JSName("state")
  private[react] var _state: State = js.native
}


abstract class Component[P <: AnyRef, S <: AnyRef] extends ComponentJS[P, S] { self =>
  protected def getInitialState: js.UndefOr[S] = js.undefined
  override private[react] def _constructor(props: Props): Unit = {
    super._constructor(props)
    getInitialState.foreach { s =>
      this._state = JsWrapper(s)
    }
  }

  protected def componentWillReceiveProps(nextProps: P): Unit = ()
  override private[react] def _componentWillReceiveProps(nextProps: Props): Unit = {
    componentWillReceiveProps(nextProps.value)
  }

  protected def shouldComponentUpdate(nextProps: P, nextState: S): Boolean = {
    (props ne nextProps) || (state ne nextState)
  }
  override private[react] def _shouldComponentUpdate(nextProps: Props, nextState: State): Boolean = {
    shouldComponentUpdate(nextProps.value, nextState.value)
  }

  protected def componentWillUpdate(nextProps: P, nextState: S): Unit = ()
  override private[react] def _componentWillUpdate(nextProps: Props, nextState: State): Unit = {
    componentWillUpdate(nextProps.value, nextState.value)
  }

  protected def componentDidUpdate(prevProps: P, prevState: S): Unit = ()
  override private[react] def _componentDidUpdate(prevProps: Props, prevState: State): Unit = {
    componentDidUpdate(prevProps.value, prevState.value)
  }

  protected final def props: P = _props.value
  protected final def state: S = _state.value

  protected final def setState(updater: S => S): Unit = {
    this._setState((prevState, _) =>
      JsWrapper(updater.apply(prevState.value))
    )
  }

  protected final def setState(updater: (S, P) => S): Unit = {
    this._setState((prevState, prevProps) =>
      JsWrapper(updater.apply(prevState.value, prevProps.value))
    )
  }
}