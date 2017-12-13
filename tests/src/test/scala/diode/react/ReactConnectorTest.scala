package diode.react

import diode._
import io.scalajs.nodejs.console
import org.scalajs.dom
import sjest.JestSuite
import sri.react.{ComponentP, CreateElement}
import sri.react.testutils.ReactTestUtils
import sri.web.ReactDOM
import sri.web.vdom.tagsPrefix_<^._

import scala.language.implicitConversions
import scala.scalajs.js

object ReactConnectorTest extends JestSuite {

  private val incrementKey = new KeyEventData("i", 73)
  private val decrementKey = new KeyEventData("d", 68)
  private val resetKey = new KeyEventData("r", 82)


  test("testConnect") {
    val connectedElement = TestCircuit.connect(_.subModel) { proxy =>
      val increment = () => proxy.dispatch(Increment())
      val decrement = () => proxy.dispatch(Decrement())
      val reset = () => proxy.dispatch(Reset)
      CreateElement[TestComponent](TestProps(proxy.value, increment, decrement, reset))
    }
    val appDomDiv = dom.document.createElement("div")
    ReactDOM.render(connectedElement, appDomDiv)

    def currentValue = TestCircuit.model.subModel.v1
    def simulateKeyPress(keyEventData: KeyEventData): Unit =
      ReactTestUtils.Simulate.keyPress(appDomDiv.firstChild, keyEventData)

    simulateKeyPress(incrementKey)
    assert(currentValue == 1)

    simulateKeyPress(incrementKey)
    assert(currentValue == 2)

    simulateKeyPress(decrementKey)
    assert(currentValue == 1)

    simulateKeyPress(resetKey)
    assert(currentValue == 0)
  }


  //models:
  private case class RootModel(subModel: SubModel = SubModel())
  private case class SubModel(v1: Int = 0, lastAction: String = "")

  //actions:
  private case class Increment(v: Int = 1) extends Action
  private case class Decrement(v: Int = 1) extends Action
  private case object Reset extends Action

  //circuit:
  private object TestCircuit extends Circuit[RootModel] with ReactConnector[RootModel] {
    override protected def initialModel: RootModel = RootModel()

    private val counterHandler = new ActionHandler(zoomTo(_.subModel)) {
      override protected def handle = {
        case Increment(v) => updated(value.copy(v1 = value.v1 + v, lastAction = "increment"))
        case Decrement(v) => updated(value.copy(v1 = value.v1 - v, lastAction = "decrement"))
        case Reset => updated(SubModel(lastAction = "reset"))
      }
    }

    override protected def actionHandler = composeHandlers(counterHandler)
  }

  private case class TestProps(v: SubModel,
                               increment: () => Unit,
                               decrement: () => Unit,
                               reset: () => Unit)

  private class TestComponent extends ComponentP[TestProps] {
    override protected def render() = <.div(
      ^.onKeyPress := eventHandler
    )(
      <.div()(s"current value: ${props.v.v1}"),
      <.div()(s"last action: ${props.v.lastAction}")
    )

    private val eventHandler = (event: ReactKeyboardEventI) => {
      val keyStr = event.key
      console.log(s"Key '$keyStr' pressed on test component")
      keyStr match {
        case "i" => props.increment()
        case "d" => props.decrement()
        case "r" => props.reset()
        case _ => //ignore
      }
    }
  }
}

private final class KeyEventData(val key: String, val keyCode: Int) extends js.Object