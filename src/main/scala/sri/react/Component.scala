package sri.react

import scala.scalajs.js
import scala.scalajs.reflect.annotation.EnableReflectiveInstantiation

abstract class ComponentP[P <: AnyRef] extends Component[P, Null]
abstract class ComponentS[S <: AnyRef] extends Component[Null, S]
abstract class Component[P <: AnyRef, S <: AnyRef] extends BaseComponent {
  type Props = P
  type State = S
}

@EnableReflectiveInstantiation
sealed abstract class BaseComponent { self =>
  type Props <: AnyRef
  type State <: AnyRef

  def render(): ReactRenders

  def getInitialState: js.UndefOr[State] = js.undefined

  def componentWillMount(): Unit = ()
  def componentDidMount(): Unit = ()
  def componentWillUnmount(): Unit = ()
  def displayName: String = this.getClass.getSimpleName

  def componentWillReceiveProps(nextProps: Props): Unit = ()
  def componentWillUpdate(nextProps: Props, nextState: State): Unit = ()
  def componentDidUpdate(prevProps: Props, prevState: State): Unit = ()

  private[react] var inner: PrototypeComponent[Props, State, _] = _

  @inline
  protected final def props: Props = inner.props.unwrap
  @inline
  protected final def state: State = inner.state.unwrap
  @inline
  protected final def setState(updater: State => State): Unit = inner.modState(updater)
}
