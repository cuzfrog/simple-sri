package sri.react.testutils.enzyme

import anywhere.component.BasicInput
import sri.react.JsStateWrapper

object EnzymeTest extends sjest.JestSuite {

  import BasicInput.{Props, State}

  private val basicInputElement = BasicInput(Props("123", 321))

  test("shallow") {
    val shallow = Enzyme.shallow[Props, State](basicInputElement)
    val input = shallow.find(".input")
    expect(input.prop("value")).toBe("123")
    val newState = JsStateWrapper(BasicInput.State("456"))
    input.setState(newState)
    expect(input.state().unwrap).toBe(newState.unwrap)
  }
}
