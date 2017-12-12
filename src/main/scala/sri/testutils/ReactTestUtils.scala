package sri.testutils

import org.scalajs.dom
import sri.core.{ReactComponent, ReactElement}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-dom/test-utils", JSImport.Namespace)
object ReactTestUtils extends js.Object {
  def renderIntoDocument(element: ReactElement): ReactElement = js.native
  @deprecated("We recommend using shallow rendering or jest.mock() instead", "react")
  def mockComponent(componentClass: String, mockTagName: String): ReactElement = js.native
  def isElement(element: ReactElement): Boolean = js.native
  def isElementOfType(element: ReactElement,
                      componentClass: String): Boolean = js.native
  def isDOMComponent(instance: dom.Node): Boolean = js.native
  def isCompositeComponent[T <: ReactComponent](instance: T): Boolean = js.native

  val Simulate: Simulate = js.native
}


