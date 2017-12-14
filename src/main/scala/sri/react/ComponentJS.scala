package sri.react

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react", "PureComponent")
private sealed abstract class ComponentJS[P, S] extends js.Object {
  protected def render(): ReactRenders

  final type JsProps = JsPropsWrapper[P]
  final type JsState = JsStateWrapper[S]

  def componentWillMount(): Unit = js.native
  def componentDidMount(): Unit = js.native
  def componentWillUnmount(): Unit = js.native
  def forceUpdate(): Unit = js.native
  def displayName: String = js.native

  def componentDidCatch(error: js.Object, info: js.Object): Unit = js.native

  // ---- native methods that need to be wrapped ----
  def componentWillReceiveProps(nextProps: JsProps): Unit = js.native
  final def shouldComponentUpdate(nextProps: JsProps, nextState: JsState): Boolean = js.native
  def componentWillUpdate(nextProps: JsProps, nextState: JsState): Unit = js.native
  def componentDidUpdate(prevProps: JsProps, prevState: JsState): Unit = js.native

  final def setState(updater: (JsState, JsProps) => JsState): Unit = js.native
  val props: JsProps = js.native
  var state: JsState = js.native
}

// ----- Real React component -----
private final class PrototypeComponent
[P <: AnyRef, S <: AnyRef, C <: BaseComponent {type Props = P; type State = S}]
(ctorProps: JsPropsWrapper[P]) extends ComponentJS[P, S] {

  // ----- construction ----- {
  private[this] val instance: C = ctorProps.instance.asInstanceOf[C]
  if (instance.getInitialState != js.undefined) {
    this.state = JsStateWrapper(instance.getInitialState.get)
  }
  instance.inner = this
  println(s"Prototype construct complete")
  println(instance)
  // ----- }

  @inline override def render(): ReactRenders = instance.render()

  @inline override def componentWillMount(): Unit = instance.componentWillMount()
  @inline override def componentDidMount(): Unit = instance.componentDidMount()
  @inline override def componentWillUnmount(): Unit = instance.componentWillUnmount()

  @inline override def componentWillReceiveProps(nextProps: JsProps): Unit =
    instance.componentWillReceiveProps(nextProps.unwrap)
  @inline override def componentWillUpdate(nextProps: JsProps, nextState: JsState): Unit =
    instance.componentWillUpdate(nextProps.unwrap, nextState.unwrap)
  @inline override def componentDidUpdate(prevProps: JsProps, prevState: JsState): Unit =
    instance.componentDidUpdate(prevProps.unwrap, prevState.unwrap)

  @inline override def displayName: String = instance.displayName

  def modState(updater: S => S): Unit = {
    this.setState((prevState, _) => JsStateWrapper(updater.apply(prevState.unwrap)))
  }
}