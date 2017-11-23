package diode.react

import diode._

/**
  * Wraps a model reader, dispatcher and React connector to be passed to React components
  * in props.
  */
case class ModelProxy[S](modelReader: ModelRO[S], theDispatch: Any => Unit, connector: ReactConnector[_ <: AnyRef]) {
  def value = modelReader()

  /**
    * Perform a dispatch action in a `Callback`
    */
  //def dispatch[A: ActionType](action: A): () => Unit = () => theDispatch(action)

  /**
    * Dispatch an action right now
    */
  def dispatchNow[A: ActionType](action: A): Unit = theDispatch(action)

  def apply() = modelReader()

  def zoom[T](f: S => T)(implicit feq: FastEq[_ >: T]) = ModelProxy(modelReader.zoom(f), theDispatch, connector)

  def wrap[T <: AnyRef, C](f: S => T)(compB: ModelProxy[T] => C)(implicit feq: FastEq[_ >: T]): C = {
    compB(zoom(f))
  }

}

trait ReactConnector[M <: AnyRef] { circuit: Circuit[M] =>

  /**
    * Wraps a React component by providing it an instance of ModelProxy for easy access to the model and dispatcher.
    *
    * @param zoomFunc Function to retrieve relevant piece from the model
    * @param compB    Function that creates the wrapped component
    * @return The component returned by `compB`
    */
  def wrap[S <: AnyRef, C](zoomFunc: M => S)(compB: ModelProxy[S] => C)(implicit feq: FastEq[_ >: S]): C = {
    wrap(circuit.zoom(zoomFunc))(compB)
  }

  /**
    * Wraps a React component by providing it an instance of ModelProxy for easy access to the model and dispatcher.
    *
    * @param modelReader A reader that returns the piece of model we are interested in
    * @param compB       Function that creates the wrapped component
    * @return The component returned by `compB`
    */
  def wrap[S <: AnyRef, C](modelReader: ModelRO[S])(compB: ModelProxy[S] => C): C = {
    implicit object aType extends ActionType[Any]
    compB(ModelProxy(modelReader, action => circuit.dispatch(action), ReactConnector.this))
  }
}
