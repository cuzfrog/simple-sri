package sri.testutils

import anywhere.component.BasicInput
import utest._

object ReactTestUtilsTest extends TestSuite{
  private val component = BasicInput()

  val tests = this{
    'isElement{
      assert(ReactTestUtils.isElement(component))
    }
  }
}
