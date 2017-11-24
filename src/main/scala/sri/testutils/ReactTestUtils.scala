package sri.testutils

import org.scalajs.dom
import sri.core.{ReactComponent, ReactElement}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-dom/test-utils", JSImport.Namespace)
object ReactTestUtils extends js.Object {
  def renderIntoDocument(element: ReactElement): ReactElement = js.native
  def isElement(element: ReactElement): Boolean = js.native
  def isElementOfType[T <: ReactComponent](element: ReactElement, componentClass: T): Boolean = js.native
  def isDOMComponent[T <: ReactComponent](instance: T): Boolean = js.native
  def isCompositeComponent[T <: ReactComponent](instance: T): Boolean = js.native

  val Simulate: Simulate = js.native
}

@js.native
trait Simulate extends js.Object{
  def keyDown(element: dom.Node, eventData: KeyEventData): Unit = js.native
  def keyPress(element: dom.Node, eventData: KeyEventData): Unit = js.native
}

final class KeyEventData(val key: String, val keyCode: Int) extends js.Object
