package sri.react.testutils

import anywhere.component.{BasicInput, FilterInput}
import io.scalajs.nodejs.console
import org.scalajs.dom
import sri.web.ReactDOM

import scala.scalajs.js

object ReactTestUtilsTest extends sjest.JestSuite {
  private val element = BasicInput()
  private val impl = ReactTestUtils

  test("isElement") {
    console.log(element)
    expect(impl.isElement(element)).toBeTruthy()
  }
  test("isElementOfType+") {
    expect(impl.isElementOfType(element, classOf[BasicInput])).toBeTruthy()
  }
  test("isElementOfType-") {
    expect(impl.isElementOfType(element, classOf[FilterInput])).toBeFalsy()
  }

  private lazy val div = dom.document.createElement("div")

  test("isDOMComponent+") {
    expect(impl.isDOMComponent(div)).toBeTruthy()
  }
  test("isDOMComponent-") {
    val instance = ReactDOM.render(element, div)
    console.log(instance)
    expect(impl.isDOMComponent(instance)).toBeFalsy()
  }
}
