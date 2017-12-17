package sri.react.testutils.enzyme

import scala.scalajs.js

@js.native
sealed trait ReactWrapper[P, S] extends EnzymeWrapper[P, S] {

  override type W = ReactWrapper[P, S]

  def getDOMNode(): js.Object = js.native

  /**
   * Simulate events
   *
   * @param event The event name to be simulated
   * @param mock  A mock event object that will be merged with the event object passed to the handlers.
   */
  def simulate(event: String, mock: js.UndefOr[js.Object] = js.undefined): ReactWrapper[P, S] = js.native

  /** A method that re-mounts the component. */
  def mount(): ReactWrapper[P, S] = js.native

  /** Returns a wrapper of the node that matches the provided reference name.
   * *
   * NOTE: can only be called on a wrapper instance that is also the root instance. */
  def ref(refName: String): ReactWrapper[P, S] = js.native

  /** Detaches the react tree from the DOM. Runs ReactDOM.unmountComponentAtNode() under the hood.
   * <br><br>
   * This method will most commonly be used as a "cleanup" method
   * if you decide to use the attachTo option in mount(node, options).
   * <br><br>
   * The method is intentionally not "fluent" (in that it doesn't return this)
   * because you should not be doing anything with this wrapper after this method is called.
   * <br><br>
   * Using the attachTo is not generally recommended unless it is absolutely necessary to test something.
   * It is your responsibility to clean up after yourself at the end of the test if you do decide to use it, though. */
  def detach(): Unit = js.native
}
