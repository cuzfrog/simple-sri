package sri.testutils

import anywhere.component.BasicInput

object ReactTestUtilsTest extends sjest.JestSuite {
  private val element = BasicInput()

  test("test component") {
    expect(ReactTestUtils.isElement(element)).toBeTruthy()
    expect(ReactTestUtils.isElementOfType(element, "BasicInput")).toBeTruthy()
  }

}
