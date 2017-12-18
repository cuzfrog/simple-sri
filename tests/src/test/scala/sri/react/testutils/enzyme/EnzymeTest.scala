package sri.react.testutils.enzyme

import anywhere.component.BasicInput
import io.scalajs.nodejs.console
import sri.react.JsStateWrapper

object EnzymeTest extends sjest.JestSuite {

  import BasicInput.{Props, State}

  private val basicInputElement = BasicInput(Props("123", 321))

  test("shallow") {
    val shallow = Enzyme.shallow[Props, State](basicInputElement)
    testEnzyme(shallow)
  }

  test("mount") {
    val wrapper = Enzyme.mount[Props, State](basicInputElement)
    testEnzyme(wrapper)
  }

  private def testEnzyme[W <: EnzymeWrapper[Props, State]](rootWrapper: W): Unit = {
    val input = rootWrapper.find("input")
    expect(input.length).toBe(1)
    expect(input.prop("value")).toBe("123")

    val newState = JsStateWrapper(BasicInput.State("456"))
    rootWrapper.setState(newState)
    expect(rootWrapper.state().unwrap).toBe(newState.unwrap)
  }
}
