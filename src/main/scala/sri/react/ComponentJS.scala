package sri.react

import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.reflect.Reflect
import scala.scalajs.reflect.annotation.EnableReflectiveInstantiation

@js.native
@JSImport("react", "Component")
private sealed abstract class ComponentJS[P, S] extends js.Object {
  protected def render(): ReactRenders

  final type JsProps = JsPropsWrapper[P]
  final type JsState = JsStateWrapper[S]

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

// ----- Real React component -----
private final class PrototypeComponent
[P <: AnyRef, S <: AnyRef, C <: BaseComponent {type Props = P; type State = S}](ctorProps: JsPropsWrapper[P])
  extends ComponentJS[P, S] {
  private[this] var instance: C = _
  instance = Reflect.lookupInstantiatableClass(ctorProps.clazz.getName).get.newInstance().asInstanceOf[C]
  if (instance.getInitialState != js.undefined) this.state = JsWrapper(instance.getInitialState.get)
  instance.inner = this
  println(s"Prototype construct complete")
  println(instance)

  override def render(): ReactRenders = instance.render()

  override def componentWillMount(): Unit = instance.componentWillMount()
  override def componentDidMount(): Unit = instance.componentDidMount()
  override def componentWillUnmount(): Unit = instance.componentWillUnmount()

  override def componentWillReceiveProps(nextProps: JsProps): Unit =
    instance.componentWillReceiveProps(nextProps.value)
  override def shouldComponentUpdate(nextProps: JsProps, nextState: JsState): Boolean =
    instance.shouldComponentUpdate(nextProps.value, nextState.value)
  override def componentWillUpdate(nextProps: JsProps, nextState: JsState): Unit =
    instance.componentWillUpdate(nextProps.value, nextState.value)
  override def componentDidUpdate(prevProps: JsProps, prevState: JsState): Unit =
    instance.componentDidUpdate(prevProps.value, prevState.value)

  override def displayName: String = instance.displayName

  def modState(updater: S => S): Unit = {
    this.setState((prevState, _) => JsWrapper(updater.apply(prevState.value)))
  }
}