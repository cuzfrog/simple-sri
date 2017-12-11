package sri.testutils

import anywhere.component.BasicInput

object ReactTestUtilsTest extends sjest.JestSuite {
  private val component = BasicInput()

  test("test component"){
    expect(ReactTestUtils.isElement(component)).toBeTruthy()
  }
}
