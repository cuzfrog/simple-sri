package re_impl

import io.scalajs.nodejs.console
import sri.testutils.ReactTestUtils

object SampleComponentTest extends sjest.JestSuite {
  private val element = SampleComponent(0)

  test("test element"){
    console.log(element)

    expect(ReactTestUtils.isElement(element)).toBeTruthy()
  }
}
