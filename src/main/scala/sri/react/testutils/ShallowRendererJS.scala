package sri.react.testutils

import sri.react.{ReactElement, ReactRenders}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-test-renderer/shallow", JSImport.Namespace)
private final class ShallowRendererJS extends js.Object {
  def render(element: ReactElement): Unit = js.native
  def getRenderOutput(): ReactRenders = js.native
}

object ShallowRenderer {
  private val shallowRendererJS = new ShallowRendererJS

  def render(element: ReactElement): ReactRenders = {
    shallowRendererJS.render(element)
    shallowRendererJS.getRenderOutput()
  }
}