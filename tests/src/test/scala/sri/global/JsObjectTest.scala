package sri.global

import io.scalajs.nodejs.console

import scala.scalajs.js
import scala.util.Random

object JsObjectTest extends sjest.JestSuite {
  private def genObj = new JsObject[Dummy] {}

  test("dynamically call") {
    val obj = genObj
    val v = Random.nextInt()
    obj.asInstanceOf[js.Dynamic].updateDynamic("field")(v)

    expect(obj.call(_.field)).toBe(v)
  }

  test("nested") {
    val v = Random.nextInt()
    val (obj, inner) = (genObj, Dummy(v))

    obj.asInstanceOf[js.Dynamic].updateDynamic("nested")(Option(inner).asInstanceOf[js.Any])

    expect(obj.call(_.nested.get.field)).toBe(v)
  }

  private case class Dummy(field: Int, nested: Option[Dummy] = None)
}
