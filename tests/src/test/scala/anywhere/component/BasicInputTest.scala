package anywhere.component

import io.scalajs.nodejs.console
import sri.react.testutils.enzyme.Enzyme

import scala.scalajs.js

object BasicInputTest extends sjest.JestSuite {

  import BasicInput.{Props, State}

  private val element = BasicInput()

  test("input value changes on change") {
    val wrapper = Enzyme.shallow[Props, State](element)
    expect(wrapper.state().unwrap.value).toBe("")

    val event = new js.Object {val target = new js.Object{val value = "123"}}
    wrapper.simulate("change", event)
    expect(wrapper.state().unwrap.value).toBe("123")
  }
}
