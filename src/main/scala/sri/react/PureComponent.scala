package sri.react

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName}

@js.native
@JSImport("react", "PureComponent")
private sealed abstract class PureComponentJS extends js.Object {
  protected def render(): ReactRenders

  type ScalaProps
  type ScalaState

  final type Props = JsWrapper[ScalaProps]
  final type State = JsWrapper[ScalaState]

  def constructor(props: Props): Unit = js.native

  def componentWillMount(): Unit = js.native
  def componentDidMount(): Unit = js.native
  def componentWillUnmount(): Unit = js.native
  def forceUpdate(): Unit = js.native
  def displayName: String = js.native

  def componentDidCatch(error: js.Object, info: js.Object): Unit = js.native

  // ---- native methods that need to be wrapped ----
  def componentWillReceiveProps(nextProps: Props): Unit = js.native
  def shouldComponentUpdate(nextProps: Props, nextState: State): Boolean = js.native
  def componentWillUpdate(nextProps: Props, nextState: State): Unit = js.native
  def componentDidUpdate(prevProps: Props, prevState: State): Unit = js.native

  final def setState(updater: (State, Props) => State): Unit = js.native
  val props: Props = js.native
  var state: State = js.native
}

abstract class Component { self =>
  type P <: AnyRef
  type S <: AnyRef

  protected def render(props: P, getState: () => S, setState: (S => S) => Unit): ReactRenders

  protected def getInitialState: js.UndefOr[S] = js.undefined

  protected def componentWillMount(): Unit = ()
  protected def componentDidMount(): Unit = ()
  protected def componentWillUnmount(): Unit = ()
  protected def displayName: String = this.getClass.getSimpleName

  protected def componentWillReceiveProps(nextProps: P): Unit = ()
  protected def shouldComponentUpdate(prevProps: P, prevState: S,
                                      nextProps: P, nextState: S): Boolean = {
    (nextProps ne prevProps) || (nextState ne prevState)
  }
  protected def componentWillUpdate(nextProps: P, nextState: S): Unit = ()
  protected def componentDidUpdate(prevProps: P, prevState: S): Unit = ()

  private[react] final class InnerComponent extends PureComponentJS {
    override type ScalaProps = P
    override type ScalaState = S

    override def constructor(props: Props): Unit = {
      super.constructor(props)
      if (getInitialState != js.undefined) this.state = getInitialState.get
    }

    override def render(): ReactRenders = self.render(this.props, () => this.state, this.stateUpdater)

    override def componentWillMount(): Unit = self.componentWillMount()
    override def componentDidMount(): Unit = self.componentDidMount()
    override def componentWillUnmount(): Unit = self.componentWillUnmount()

    override def componentWillReceiveProps(nextProps: Props): Unit =
      self.componentWillReceiveProps(nextProps)
    override def shouldComponentUpdate(nextProps: Props, nextState: State): Boolean =
      self.shouldComponentUpdate(this.props, this.state, nextProps, nextState)
    override def componentWillUpdate(nextProps: Props, nextState: State): Unit =
      self.componentWillUpdate(nextProps, nextState)
    override def componentDidUpdate(prevProps: Props, prevState: State): Unit =
      self.componentDidUpdate(prevProps, prevState)

    override val displayName: String = self.displayName

    private val stateUpdater: (S => S) => Unit = (updater: S => S) => {
      this.setState((prevState, _) => JsWrapper(updater.apply(prevState.value)))
    }
  }
}