package sri.react.testutils

import io.scalajs.nodejs.console
import re_impl.{AnotherComponent, SampleComponent}

import scala.scalajs.js

object ReactTestUtilsTest extends sjest.JestSuite {
  private val element = SampleComponent(0)
  private val impl = ReactTestUtils

  test("isElement") {
    console.log(element)
    expect(impl.isElement(element)).toBeTruthy()
  }
  test("isElementOfType-positive"){
    expect(impl.isElementOfType(element, classOf[SampleComponent])).toBeTruthy()
  }
  test("isElementOfType-negative"){
    console.log(element.props.asInstanceOf[js.Any])
    expect(impl.isElementOfType(element, classOf[AnotherComponent])).toBeFalsy()
  }
}
