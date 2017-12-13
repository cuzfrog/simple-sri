package diode.react

import diode._
import sri.react.{CreateElement, ReactElement}

trait ReactConnector[M <: AnyRef] { circuit: Circuit[M] =>

  /**
   * Wraps a React component by providing it an instance of ModelProxy
   * for easy access to the model and dispatcher.
   *
   * @param zoomFunc Function to retrieve relevant piece from the model
   * @param compB    Function that creates the wrapped component
   * @return The component returned by `compB`
   */
  private def wrap[S <: AnyRef, C](zoomFunc: M => S)(compB: ModelProxy[S] => C)
                          (implicit feq: FastEq[_ >: S]): C = {
    wrap(circuit.zoom(zoomFunc))(compB)
  }

  /**
   * Wraps a React component by providing it an instance of ModelProxy
   * for easy access to the model and dispatcher.
   *
   * @param modelReader A reader that returns the piece of model we are interested in
   * @param compB       Function that creates the wrapped component
   * @return The component returned by `compB`
   */
  private def wrap[S <: AnyRef, C](modelReader: ModelRO[S])(compB: ModelProxy[S] => C): C = {
    implicit object aType extends ActionType[Any]
    compB(ModelProxy(modelReader, action => circuit.dispatch(action), ReactConnector.this))
  }

  /**
   * Connects a React component into the Circuit by wrapping it in another component that listens to
   * relevant state changes and updates the wrapped component as needed.
   *
   * @param zoomFunc Function to retrieve relevant piece from the model
   * @param buildFunc    Function that creates the wrapped component
   * @return A connected container ReactElement
   */
  def connect[S >: Null <: AnyRef](zoomFunc: M => S)
                                  (buildFunc: ModelProxy[S] => ReactElement): ReactElement = {
    val wrapFunc: () => ReactElement = () => wrap(zoomFunc)(buildFunc)
    val subscrFunc = circuit.subscribe(circuit.zoom(zoomFunc)) _
    CreateElement[ContainerComponent[M, S]](ContainerComponent.Props(subscrFunc, wrapFunc))
  }
}
