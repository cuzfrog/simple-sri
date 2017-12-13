package sri.react.testutils

import io.scalajs.nodejs.console
import org.scalajs.dom
import re_impl.{AnotherComponent, SampleComponent}
import sri.web.ReactDOM

import scala.scalajs.js

object ReactTestUtilsTest extends sjest.JestSuite {
  private val element = SampleComponent(0)
  private val impl = ReactTestUtils

  test("isElement") {
    console.log(element)
    expect(impl.isElement(element)).toBeTruthy()
  }
  test("isElementOfType+") {
    expect(impl.isElementOfType(element, classOf[SampleComponent])).toBeTruthy()
  }
  test("isElementOfType-") {
    expect(impl.isElementOfType(element, classOf[AnotherComponent])).toBeFalsy()
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
