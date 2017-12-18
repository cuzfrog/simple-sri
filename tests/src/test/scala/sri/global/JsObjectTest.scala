package sri.global

import io.scalajs.nodejs.console

import scala.scalajs.js
import scala.util.Random

object JsObjectTest extends sjest.JestSuite {
  private val obj = new JsObject[Dummy] {}

  test("dynamically call") {
    val v = Random.nextInt()
    obj.asInstanceOf[js.Dynamic].updateDynamic("field")(v)
    expect(obj.call(_.field)).toBe(v)
  }

  test("nested") {
    ???
  }

  private case class Dummy(field: Int, nested: Option[Dummy])
}
