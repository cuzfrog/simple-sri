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
  def toJSON(): String = js.native
  def toTree(): js.Object = js.native
  def update(element: ReactElement): Unit = js.native
  def unmount(): Unit = js.native
  //def getInstance(): ReactElement = js.native
}

@js.native
sealed trait TestInstance extends js.Object {

  def find(testFunc: TestInstance => Boolean): TestInstance = js.native
  def findByType(tpe: String): TestInstance = js.native
  def findByProps(props: js.Object): TestInstance = js.native
  def findAll(testFunc: TestInstance => Boolean): js.Array[TestInstance] = js.native
  def findAllByType(tpe: String): js.Array[TestInstance] = js.native
  def findAllByProps(props: js.Object): js.Array[TestInstance] = js.native

  //def instance: ReactComponent = js.native
  def `type`: String = js.native
  def props: js.Object = js.native
  def parent: TestInstance = js.native
  def children: js.Array[TestInstance] = js.native
}