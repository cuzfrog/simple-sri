/* http://airbnb.io/enzyme/docs/api/shallow.html */

package sri.react.testutils

import sri.react.ReactNode

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("enzyme", JSImport.Namespace)
object Enzyme extends js.Object {
  def shallow(node: ReactNode,
              options: js.UndefOr[js.Object] = js.undefined): ShallowWrapper = js.native
}

@js.native
sealed trait ShallowWrapper extends js.Object {
  /** Find every node in the render tree that matches the provided selector. */
  def find(selector: String): ShallowWrapper = js.native

  /** Find every node in the render tree that returns true for the provided predicate function. */
  def findWhere(predicate: ShallowWrapper => Boolean): ShallowWrapper = js.native

  /** Remove nodes in the current wrapper that do not match the provided selector. */
  def filter(selector: String): ShallowWrapper = js.native

  /** Remove nodes in the current wrapper that do not return true for the provided predicate function. */
  def filterWhere(predicate: ShallowWrapper => Boolean): ShallowWrapper = js.native

  /** Removes nodes that are not host nodes; e.g., this will only return HTML nodes. */
  def hostNodes(): ShallowWrapper = js.native

  /** Returns whether or not a given node or array of nodes is somewhere in the render tree. */
  def contains(node: ReactNode*): Boolean = js.native

  /** Returns whether or not a given react element exists in the shallow render tree. */
  def containsMatchingElement(node: ReactNode): Boolean = js.native

  
}