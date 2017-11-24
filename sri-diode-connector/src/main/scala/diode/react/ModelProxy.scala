package diode.react

import diode.{ActionType, FastEq, ModelRO}

/**
 * Wraps a model reader, dispatcher and React connector to be passed to React components
 * in props.
 */
final case class ModelProxy[S](modelReader: ModelRO[S],
                               theDispatch: Any => Unit, connector: ReactConnector[_ <: AnyRef]) {
  def value = modelReader()

  /**
   * Dispatch an action right now
   */
  def dispatch[A: ActionType](action: A): Unit = theDispatch(action)

  def apply() = modelReader()

  def zoom[T](f: S => T)(implicit feq: FastEq[_ >: T]) =
    ModelProxy(modelReader.zoom(f), theDispatch, connector)

  def wrap[T <: AnyRef, C](f: S => T)(compB: ModelProxy[T] => C)
                          (implicit feq: FastEq[_ >: T]): C = {
    compB(zoom(f))
  }
}
