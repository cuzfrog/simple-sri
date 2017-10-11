package sri.core

import sri.core

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, JSName, ScalaJSDefined}
import scala.scalajs.js.{UndefOr, undefined}

@js.native
trait React extends js.Object {

  def createElement(tpe: js.Any,
                    props: js.Any,
                    children: ReactNode*): ReactElement = js.native

  def cloneElement(element: ReactElement,
                   props: js.Any = ???,
                   children: js.Any = ???): ReactElement = js.native

  def createFactory(tpe: js.Any): js.Any = js.native

  def Children: ReactChildren = js.native

}

@js.native
@JSImport("react", JSImport.Namespace)
object React extends React

@js.native
trait ReactElement extends js.Object {

  type Instance <: ReactClass

  def key: UndefOr[String] = js.native

  def ref: UndefOr[js.Function] = js.native
}


trait ReactClass extends js.Object {
  type PropsType
  type StateType
  var contextTypes: js.UndefOr[js.Any] = js.undefined
  var childContextTypes: js.UndefOr[js.Any] = js.undefined
}

trait ReactScalaClass extends ReactClass {
  type ScalaPropsType
  type ScalaStateType
  override type PropsType = JSProps { type ScalaProps = ScalaPropsType }
  override type StateType = JSState { type ScalaState = ScalaStateType }
}

trait ReactScalaClassJS extends ReactClass {
//  override type PropsType
  type ScalaStateType
  override type StateType = JSState { type ScalaState = ScalaStateType }
}

@js.native
trait JSProps extends js.Object {
  type ScalaProps
  def key: UndefOr[String] = js.native

  def ref: UndefOr[js.Function] = js.native

  def scalaProps: ScalaProps = js.native

  def children: ReactNode = js.native
}

object JSProps {
  @inline
  def apply[P](scalaProps: P): JSProps { type ScalaProps = P } = {
    val p = js.Dynamic.literal("scalaProps" -> scalaProps.asInstanceOf[js.Any])
    p.asInstanceOf[JSProps { type ScalaProps = P }]
  }
}

@js.native
trait JSState extends js.Object {
  type ScalaState
  var scalaState: ScalaState = js.native
}

object JSState {
  @inline
  def apply[S](scalaState: S) =
    js.Dynamic
      .literal("scalaState" -> scalaState.asInstanceOf[js.Any])
      .asInstanceOf[JSState { type ScalaState = S }]
}

@js.native
trait ReactChildren extends ReactElement {

  def map(children: js.Object,
          fn: js.Function1[js.Object, _]): js.UndefOr[js.Object] = js.native

  def forEach(children: js.Object, fn: js.Function1[js.Object, _]): Unit =
    js.native

  def count(children: js.Object): Int = js.native

  def only(children: js.Object): js.Object = js.native

}

@js.native
@JSImport("react", "Component")
abstract class ComponentRaw[P, S] extends ReactClass {

  override type PropsType = P

  override type StateType = S

  def render(): ReactRenderNode

  def props: P = js.native

  var state: S = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native

  def setState(newState: S, callback: UndefOr[() => _] = js.undefined): Unit =
    js.native

  def setState(func: js.Function2[S, P, S]): Unit = js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  def componentWillReceiveProps(nextProps: P): Unit = js.native

  def shouldComponentUpdate(nextProps: P, nextState: S): Boolean = js.native

  def componentWillUpdate(nextProps: P, nextState: S): Unit = js.native

  def componentDidUpdate(prevProps: P, prevState: S): Unit = js.native

}

@js.native
@JSImport("react", "Component")
private[sri] abstract class InternalComponentSecondary[P >: Null <: AnyRef,
                                                       S >: Null <: AnyRef](
    initialJSProps: JSProps { type ScalaProps = P })(
    implicit ev: =:!=[P, Null])
    extends ReactScalaClass {

  override type ScalaPropsType = P

  override type ScalaStateType = S

  def render(): ReactRenderNode

  @JSName("props") def jsProps: PropsType = js.native

  @JSName("state") var jsState: StateType = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native
  @JSName("setState")
  def jsSetState(func: js.Function2[StateType, PropsType, StateType]): Unit =
    js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  def componentWillReceiveProps(nextJSProps: JSProps { type ScalaProps = P })
    : Unit = js.native

  def shouldComponentUpdate(nextJSProps: JSProps { type ScalaProps = P }, nextJSState: JSState {
    type ScalaState = S
  }): Boolean =
    js.native

  def componentWillUpdate(nextJSProps: JSProps { type ScalaProps = P }, nextJSState: JSState {
    type ScalaState = S
  }): Unit =
    js.native

  def componentDidUpdate(prevJSProps: JSProps { type ScalaProps = P }, nextJSState: JSState {
    type ScalaState = S
  }): Unit =
    js.native

}

abstract class ComponentSecondary[P >: Null <: AnyRef, S >: Null <: AnyRef](
    initialProps: JSProps { type ScalaProps = P })(implicit ev: =:!=[P, Null],
                                                   ev1: =:!=[S, Null])
    extends InternalComponentSecondary[P, S](initialProps)
    with ReactScalaClass {

  if (js.isUndefined(jsState) || jsState == null) {
    jsState = JSState(null).asInstanceOf[StateType]
  }

  @JSName("scalaProps")
  def props: P = jsProps.scalaProps

  @JSName("scalaState")
  def state: S = jsState.scalaState

  @inline
  def propsDynamic = jsProps.asInstanceOf[js.Dynamic]

  @inline
  def children: ReactNode = jsProps.children

  @inline
  def initialState(s: S): Unit = {
    jsState = JSState(s)
  }

  @JSName("setStateFromStateAndProps")
  @inline
  def setState(func: js.Function2[S, P, S]): Unit = {
    jsSetState((s: StateType, p: PropsType) =>
      JSState(func(s.scalaState, p.scalaProps)))
  }

  @JSName("setStateFromState")
  @inline
  def setState(func: js.Function1[S, S]): Unit = {
    jsSetState((s: StateType, p: PropsType) => JSState(func(s.scalaState)))
  }

  @inline
  def getRef[T](name: String, cls: Class[T]) = {
    refs.selectDynamic(name).asInstanceOf[T]
  }

  override def shouldComponentUpdate(nextJSProps: JSProps {
    type ScalaProps = P
  }, nextJSState: JSState { type ScalaState = S }): Boolean = {

    (nextJSProps.scalaProps ne props) || (nextJSState.scalaState ne state)

  }

}

@js.native
@JSImport("react", "Component")
private[sri] abstract class InternalComponent[P <: AnyRef, S <: AnyRef]
    extends js.Object
    with ReactScalaClass {

  override type ScalaPropsType = P

  override type ScalaStateType = S

  @JSName("props") def jsProps: PropsType = js.native

  @JSName("state") var jsState: StateType = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native

  @JSName("setState")
  def jsSetState(func: js.Function2[StateType, PropsType, StateType]): Unit =
    js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def render(): ReactRenderNode

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  def componentWillReceiveProps(nextJSProps: JSProps { type ScalaProps = P })
    : Unit = js.native

  def shouldComponentUpdate(nextJSProps: JSProps { type ScalaProps = P }, nextJSState: JSState {
    type ScalaState = S
  }): Boolean =
    js.native

  def componentWillUpdate(nextJSProps: JSProps { type ScalaProps = P }, nextJSState: JSState {
    type ScalaState = S
  }): Unit =
    js.native

  def componentDidUpdate(prevJSProps: JSProps { type ScalaProps = P }, nextJSState: JSState {
    type ScalaState = S
  }): Unit =
    js.native

}

abstract class Component[P >: Null <: AnyRef, S >: Null <: AnyRef](
    implicit ev: =:!=[P, Null],
    ev2: =:!=[S, Null])
    extends InternalComponent[P, S]
    with ReactScalaClass {

  if (js.isUndefined(jsState) || jsState == null) {
    jsState = JSState(null).asInstanceOf[StateType]
  }

  @JSName("scalaProps")
  @inline
  def props: P = jsProps.scalaProps

  @JSName("scalaState")
  @inline
  def state: S = jsState.scalaState

  @inline
  def propsDynamic = jsProps.asInstanceOf[js.Dynamic]

  @inline
  def children: ReactNode = jsProps.children

  @inline
  def initialState(s: S): Unit = {
    jsState = JSState(s)
  }

  @JSName("setStateFromStateAndProps")
  @inline
  def setState(func: js.Function2[S, P, S]): Unit = {
    jsSetState((s: StateType, p: PropsType) =>
      JSState(func(s.scalaState, p.scalaProps)))
  }

  @JSName("setStateFromState")
  @inline
  def setState(func: js.Function1[S, S]): Unit = {
    jsSetState((s: StateType, p: PropsType) => JSState(func(s.scalaState)))
  }

  @inline
  def getRef[T](name: String, cls: Class[T]) = {
    refs.selectDynamic(name).asInstanceOf[T]
  }

  override def shouldComponentUpdate(nextJSProps: JSProps {
    type ScalaProps = P
  }, nextJSState: JSState { type ScalaState = S }): Boolean = {

    (nextJSProps.scalaProps ne props) || (nextJSState.scalaState ne state)

  }

}

@js.native
@JSImport("react", "Component")
private[sri] abstract class InternalComponentP[P <: AnyRef]
    extends js.Object
    with ReactScalaClass {

  override type ScalaPropsType = P

  @JSName("props") def jsProps: PropsType = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def render(): ReactRenderNode

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  def componentWillReceiveProps(nextJSProps: JSProps { type ScalaProps = P })
    : Unit = js.native

  def shouldComponentUpdate(nextJSProps: JSProps { type ScalaProps = P })
    : Boolean =
    js.native

  def componentWillUpdate(nextJSProps: JSProps { type ScalaProps = P }): Unit =
    js.native

  def componentDidUpdate(prevJSProps: JSProps { type ScalaProps = P }): Unit =
    js.native

}

abstract class ComponentP[P >: Null <: AnyRef](implicit ev: =:!=[P, Null])
    extends InternalComponentP[P]
    with ReactScalaClass {

  @JSName("scalaProps")
  @inline
  def props: P = jsProps.scalaProps

  @inline
  def children: ReactNode = jsProps.children

  @inline
  def getRef[T](name: String, cls: Class[T]) = {
    refs.selectDynamic(name).asInstanceOf[T]
  }

  override def shouldComponentUpdate(nextJSProps: JSProps {
    type ScalaProps = P
  }): Boolean = {
    props ne nextJSProps.scalaProps
  }
}

@js.native
@JSImport("react", "Component")
private[sri] abstract class InternalComponentS[S <: AnyRef]
    extends js.Object
    with ReactScalaClass {

  override type ScalaPropsType = Null

  override type ScalaStateType = S

  @JSName("props") def jsProps: PropsType = js.native

  @JSName("state") var jsState: StateType = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native

  @JSName("setState")
  def jsSetState(func: js.Function1[StateType, StateType]): Unit =
    js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def render(): ReactRenderNode

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  def shouldComponentUpdate(nextJSProps: JSProps { type ScalaProps = Null }, nextJSState: JSState {
    type ScalaState = S
  }): Boolean =
    js.native

  def componentWillUpdate(nextJSProps: JSProps { type ScalaProps = Null }, nextJSState: JSState {
    type ScalaState = S
  }): Unit =
    js.native

  def componentDidUpdate(prevJSProps: JSProps { type ScalaProps = Null }, nextJSState: JSState {
    type ScalaState = S
  }): Unit =
    js.native

}

abstract class ComponentS[S >: Null <: AnyRef](implicit ev: =:!=[S, Null])
    extends InternalComponentS[S]
    with ReactScalaClass {

  if (js.isUndefined(jsState) || jsState == null) {
    jsState = js.Dictionary[Any]("scala" -> null).asInstanceOf[StateType]
  }

  @JSName("scalaState")
  @inline
  def state: S = jsState.scalaState

  @inline
  def children: ReactNode = jsProps.children

  @inline
  def initialState(s: S): Unit = {
    jsState = JSState(s)
  }

  @JSName("setStateFromState")
  @inline
  def setState(func: js.Function1[S, S]): Unit = {
    jsSetState((s: StateType) => JSState(func(s.scalaState)))
  }

  @inline
  def getRef[T](name: String, cls: Class[T]) = {
    refs.selectDynamic(name).asInstanceOf[T]
  }

  override def shouldComponentUpdate(nextJSProps: JSProps {
    type ScalaProps = Null
  }, nextJSState: JSState { type ScalaState = S }): Boolean = {
    state ne nextJSState.scalaState
  }
}

@js.native
@JSImport("react", "Component")
private[sri] abstract class InternalComponentJS[P <: js.Object, S <: AnyRef]
    extends js.Object
    with ReactScalaClassJS {

  override type PropsType = P

  override type ScalaStateType = S

  def props: PropsType = js.native

  @JSName("state") var jsState: StateType = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native

  @JSName("setState")
  def jsSetState(func: js.Function2[StateType, PropsType, StateType]): Unit =
    js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def render(): ReactRenderNode

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  def componentWillReceiveProps(nextJSProps: P): Unit = js.native

  def shouldComponentUpdate(nextJSProps: P, nextJSState: JSState {
    type ScalaState = S
  }): Boolean =
    js.native

  def componentWillUpdate(nextJSProps: P, nextJSState: JSState {
    type ScalaState = S
  }): Unit =
    js.native

  def componentDidUpdate(prevJSProps: P, nextJSState: JSState {
    type ScalaState = S
  }): Unit =
    js.native

}

@js.native
@JSImport("react", "Component")
private[sri] abstract class InternalComponentJSP[P <: AnyRef]
    extends js.Object
    with ReactScalaClassJS {

  override type PropsType = P

  def props: PropsType = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def render(): ReactRenderNode

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  def componentWillReceiveProps(nextJSProps: P): Unit = js.native

  def shouldComponentUpdate(nextJSProps: P): Boolean =
    js.native

  def componentWillUpdate(nextJSProps: P): Unit =
    js.native

  def componentDidUpdate(prevJSProps: P): Unit =
    js.native

}

abstract class ComponentJS[P <: js.Object, S <: AnyRef]
    extends InternalComponentJS[P, S] {

  if (js.isUndefined(jsState) || jsState == null) {
    jsState = JSState(null).asInstanceOf[StateType]
  }

  @JSName("scalaState")
  @inline
  def state: S = jsState.scalaState

  @inline
  def children: ReactNode =
    props.asInstanceOf[js.Dynamic].children.asInstanceOf[ReactNode]

  @inline
  def initialState(s: S): Unit = {
    jsState = JSState(s)
  }

  @JSName("setStateFromStateAndProps")
  @inline
  def setState(func: js.Function2[S, P, S]): Unit = {
    jsSetState((s: StateType, p: PropsType) => JSState(func(s.scalaState, p)))
  }

  @JSName("setStateFromState")
  @inline
  def setState(func: js.Function1[S, S]): Unit = {
    jsSetState((s: StateType, p: PropsType) => JSState(func(s.scalaState)))
  }

  @inline
  def getRef[T](name: String, cls: Class[T]) = {
    refs.selectDynamic(name).asInstanceOf[T]
  }

}

abstract class ComponentNotPure[P >: Null <: AnyRef, S >: Null <: AnyRef](
    implicit ev: =:!=[P, Null],
    ev2: =:!=[S, Null])
    extends Component[P, S] {
  override def shouldComponentUpdate(nextJSProps: JSProps {
    type ScalaProps = P
  }, nextJSState: JSState { type ScalaState = S }): Boolean = {
    true
  }
}

abstract class ComponentNotPureP[P >: Null <: AnyRef](
    implicit ev: =:!=[P, Null])
    extends ComponentP[P] {
  override def shouldComponentUpdate(nextJSProps: JSProps {
    type ScalaProps = P
  }): Boolean = {
    true
  }
}

abstract class ComponentNotPureS[S >: Null <: AnyRef](
    implicit ev2: =:!=[S, Null])
    extends ComponentS[S] {
  override def shouldComponentUpdate(nextJSProps: JSProps {
    type ScalaProps = Null
  }, nextJSState: JSState { type ScalaState = S }): Boolean = {
    true
  }
}

@js.native
@JSImport("react", "Component")
private[sri] abstract class InternalComponentNoPS
    extends js.Object
    with ReactScalaClass {

  override type ScalaPropsType = Null

  @JSName("props") def jsProps: PropsType = js.native

  var refs: js.Dynamic = js.native

  var context: js.Dynamic = js.native

  def forceUpdate(callback: js.Function = ???): Unit = js.native

  def render(): ReactRenderNode

  def componentWillReceiveProps(): Unit = js.native

  def componentWillMount(): Unit = js.native

  def componentDidMount(): Unit = js.native

  def componentWillUnmount(): Unit = js.native

  def shouldComponentUpdate(): Boolean = js.native

  def componentWillUpdate(): Unit = js.native

  def componentDidUpdate(): Unit = js.native

}

abstract class ComponentNoPS extends InternalComponentNoPS {
  def children: ReactNode =
    jsProps.children
}

abstract class ReactJSProps {
  val key: js.UndefOr[String] = js.undefined
  val ref: UndefOr[js.Function] = js.undefined
  val children: js.UndefOr[ReactNode] = js.undefined
}

/**
  * typed version of js.concstructorOf[ C <: ReactClass]
  */
trait ComponentConstructor extends ReactClass {
  type ComponentType <: ReactClass
}

trait JSComponent[P] extends ComponentConstructor {
  override type ComponentType = this.type
  override type PropsType = P
}
