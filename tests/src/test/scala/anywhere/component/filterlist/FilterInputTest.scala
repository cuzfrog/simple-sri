package anywhere.component.filterlist

import anywhere.{AppCircuit, FilterChange, Reset}
import io.scalajs.nodejs.console
import sri.react.testutils.enzyme.Enzyme

import scala.scalajs.js
import scala.util.Random

object FilterInputTest extends sjest.JestSuite {

  import FilterInput.Props

  beforeEach(AppCircuit.dispatch(Reset))

  test("input change affects circuit") {
    val wrapper = createWrapper
    expect(wrapper.find("input").prop("value")).toBe("")

    val v = Random.nextInt().toString
    val event = createEvent(v)
    wrapper.simulate("change", event)
    expect(wrapper.find("input").prop("value")).toBe(v)

    val circuitValue = AppCircuit.zoomTo(_.filterModel.filterValue).value
    expect(circuitValue).toBe(v)
  }

  test("circuit dispatch yields new input value") {
    val v = Random.nextInt().toString
    AppCircuit.dispatch(FilterChange(v))

    val wrapper = createWrapper
    expect(wrapper.find("input").prop("value")).toBe(v)
  }


  private def createWrapper = Enzyme.mount[Props, Null](FilterInput())
  private def createEvent(v: js.Any): js.Object = {
    new js.Object {val target: js.Object = new js.Object {val value: js.Any = v}}
  }
}
