package diode.react

import diode._
import org.scalajs.dom
import sri.core.{ComponentP, CreateElement, ReactComponent}
import sri.testutils.{KeyEventData, TestRenderer}
import sri.web.ReactDOM
import sri.web.vdom.tagsPrefix_<^._
import utest._

import scala.scalajs.js

object ReactConnectorTest extends TestSuite {

  private val incrementKey = new KeyEventData("i", 73)
  private val decrementKey = new KeyEventData("d", 68)
  private val resetKey = new KeyEventData("r", 82)

  val tests = Tests {
    'testConnect - {


      val connectedComponent = TestCircuit.connect(_.subModel) { proxy =>
        val increment = () => proxy.dispatch(Increment(1))
        val decrement = () => proxy.dispatch(Decrement(1))
        val reset = () => proxy.dispatch(Reset)
        CreateElement[TestComponent](TestProps(proxy.value, increment, decrement, reset))
      }
      val props = TestRenderer.create(connectedComponent).root.props
      //ReactTestUtils.Simulate.keyPress(component.firstChild, incrementKey)
      js.Dynamic.global.console.log(props)
      val modelValue = TestCircuit.model.subModel.v1
      assert(modelValue == 0)
    }
  }

  //models:
  private case class RootModel(subModel: SubModel = SubModel())
  private case class SubModel(v1: Int = 0, lastAction: String = "")

  //actions:
  private case class Increment(v: Int) extends Action
  private case class Decrement(v: Int) extends Action
  private case object Reset extends Action

  //circuit:
  private object TestCircuit extends Circuit[RootModel] with ReactConnector[RootModel] {
    override protected def initialModel: RootModel = RootModel()

    private val counterHandler = new ActionHandler(zoomTo(_.subModel)) {
      override protected def handle = {
        case Increment(v) => updated(value.copy(v1 = value.v1 + 1, lastAction = "increment"))
        case Decrement(v) => updated(value.copy(v1 = value.v1 - 1, lastAction = "decrement"))
        case Reset => updated(SubModel(lastAction = "increment"))
      }
    }

    override protected def actionHandler = composeHandlers(counterHandler)
  }

  private case class TestProps(v: SubModel,
                               increment: () => Unit,
                               decrement: () => Unit,
                               reset: () => Unit)
  private class TestComponent extends ComponentP[TestProps] {
    override protected def render() = <.div()(
      <.div()(s"current value: ${props.v.v1}"),
      <.div()(s"last action: ${props.v.lastAction}")
    )
  }

  implicit def toDom(reactElement: ReactComponent): dom.Node = {
    ReactDOM.findDOMNode(reactElement)
  }

}

