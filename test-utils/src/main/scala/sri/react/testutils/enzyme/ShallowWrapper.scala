package sri.react.testutils.enzyme

import scala.scalajs.js

@js.native
sealed trait ShallowWrapper[P, S] extends EnzymeWrapper[P, S] {

  override type W = ShallowWrapper[P, S]

  /** Simulates an event on the current node. */
  def simulate(event: String, args: js.Any*): ShallowWrapper[P, S] = js.native

  /** Shallow render the one non-DOM child of the current wrapper, and return a wrapper around the result.
   * <br>*
   * NOTE: can only be called on wrapper of a single non-DOM component element node. */
  def dive(options: js.UndefOr[js.Object] = js.undefined): ShallowWrapper[P, S] = js.native

  /** Shallow renders the current node and returns a shallow wrapper around it. */
  def shallow(options: js.UndefOr[js.Object] = js.undefined): ShallowWrapper[P, S] = js.native
}
