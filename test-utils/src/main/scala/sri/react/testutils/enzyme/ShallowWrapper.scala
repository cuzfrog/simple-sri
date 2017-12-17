package sri.react.testutils.enzyme

import scala.scalajs.js

@js.native
sealed trait ShallowWrapper extends EnzymeWrapper[ShallowWrapper] {

  /** Simulates an event on the current node. */
  def simulate(event: String, args: js.Any*): ShallowWrapper


  /** Shallow render the one non-DOM child of the current wrapper, and return a wrapper around the result.
   * <br>*
   * NOTE: can only be called on wrapper of a single non-DOM component element node. */
  def dive(options: js.UndefOr[js.Object] = js.undefined): ShallowWrapper
}
