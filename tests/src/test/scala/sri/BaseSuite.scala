package sri

import scala.util.Random

abstract class BaseSuite extends sjest.JestSuite with AddTestUtilities {

}

trait AddTestUtilities {
  protected implicit final class ExRandom(r: Random) {
    def genAlphanumeric(n: Int): String = r.alphanumeric.take(n).mkString
  }
}
