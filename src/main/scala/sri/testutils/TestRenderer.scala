package sri.testutils

import sri.core.{ReactComponent, ReactElement}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("react-test-renderer", JSImport.Namespace)
object TestRenderer extends js.Object {
  def create(element: ReactElement,
             options: js.UndefOr[js.Object] = js.undefined): TestRenderer = js.native
}

@js.native
sealed trait TestRenderer extends js.Object {
  def root: TestInstance = js.native
}

@js.native
sealed trait TestInstance extends js.Object {
  def instance: ReactComponent = js.native
  def props: js.Dynamic = js.native
}