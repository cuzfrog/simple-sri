package sri.react

import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react", "PureComponent")
private sealed abstract class PureComponentJS[P, S] extends js.Object {
  protected def render(): ReactRenders

  final type JsProps = JsWrapper[P]
  final type JsState = JsWrapper[S]

  def constructor(props: JsProps): Unit = js.native

  def componentWillMount(): Unit = js.native
  def componentDidMount(): Unit = js.native
  def componentWillUnmount(): Unit = js.native
  def forceUpdate(): Unit = js.native
  def displayName: String = js.native

  def componentDidCatch(error: js.Object, info: js.Object): Unit = js.native

  // ---- native methods that need to be wrapped ----
  def componentWillReceiveProps(nextProps: JsProps): Unit = js.native
  def shouldComponentUpdate(nextProps: JsProps, nextState: JsState): Boolean = js.native
  def componentWillUpdate(nextProps: JsProps, nextState: JsState): Unit = js.native
  def componentDidUpdate(prevProps: JsProps, prevState: JsState): Unit = js.native

  final def setState(updater: (JsState, JsProps) => JsState): Unit = js.native
  val props: JsProps = js.native
  var state: JsState = js.native
}

abstract class ComponentP[P <: AnyRef] extends Component[P, Null]
abstract class ComponentS[S <: AnyRef] extends Component[Null, S]
abstract class Component[P <: AnyRef, S <: AnyRef] extends BaseComponent {
  override type Props = P
  override type State = S
}

sealed abstract class BaseComponent { self =>
  type Props <: AnyRef
  type State <: AnyRef

  protected def render(): ReactRenders

  protected def getInitialState: js.UndefOr[State] = js.undefined

  protected def componentWillMount(): Unit = ()
  protected def componentDidMount(): Unit = ()
  protected def componentWillUnmount(): Unit = ()
  protected def displayName: String = this.getClass.getSimpleName

  protected def componentWillReceiveProps(nextProps: Props): Unit = ()
  protected def shouldComponentUpdate(currentProps: Props, currentState: State,
                                      nextProps: Props, nextState: State): Boolean = {
    (nextProps ne currentProps) || (nextState ne currentState)
  }
  protected def componentWillUpdate(nextProps: Props, nextState: State): Unit = ()
  protected def componentDidUpdate(prevProps: Props, prevState: State): Unit = ()


  private var inner: InnerComponent[_] = _
  protected def props: Props = inner.props
  protected def state: State = inner.state
  protected final def setState(updater: State => State): Unit = inner.setState(updater)

  // ----- Real React component -----
  private[react] final class InnerComponent[C <: BaseComponent : ClassTag]
    extends PureComponentJS[Props, State] {

    override def constructor(props: JsProps): Unit = {
      super.constructor(props)
      if (getInitialState != js.undefined) this.state = JsWrapper[State, C](getInitialState.get)
      self.inner = this
    }

    override def render(): ReactRenders = self.render()

    override def componentWillMount(): Unit = self.componentWillMount()
    override def componentDidMount(): Unit = self.componentDidMount()
    override def componentWillUnmount(): Unit = self.componentWillUnmount()

    override def componentWillReceiveProps(nextProps: JsProps): Unit =
      self.componentWillReceiveProps(nextProps)
    override def shouldComponentUpdate(nextProps: JsProps, nextState: JsState): Boolean =
      self.shouldComponentUpdate(this.props, this.state, nextProps, nextState)
    override def componentWillUpdate(nextProps: JsProps, nextState: JsState): Unit =
      self.componentWillUpdate(nextProps, nextState)
    override def componentDidUpdate(prevProps: JsProps, prevState: JsState): Unit =
      self.componentDidUpdate(prevProps, prevState)

    override val displayName: String = self.displayName

    def setState(updater: State => State): Unit = {
      this.setState((prevState, _) => JsWrapper[State, C](updater.apply(prevState.value)))
    }
  }
}