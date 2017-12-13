package diode.react

import diode.ModelRO
import sri.react.{Component, ReactElement, ReactRenders}

private final class ContainerComponent[M, S >: Null <: AnyRef]
  extends Component[ContainerComponent.Props[M, S], S] {

  private var unsubscribe = Option.empty[() => Unit]

  override def componentDidMount(): Unit = {
    unsubscribe = Some(props.subcrFunc { modelReader =>
      setState(_ => modelReader.value)
    })
  }

  override def componentWillUnmount(): Unit = {
    unsubscribe.foreach(f => f())
    unsubscribe = None
  }

  //override def shouldComponentUpdate already implemented

  override def render(): ReactRenders = props.wrapFunc()
}

private object ContainerComponent {
  case class Props[M, S](subcrFunc: (ModelRO[S] => Unit) => () => Unit,
                         wrapFunc: () => ReactElement)
}
