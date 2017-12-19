package sri.global

import io.scalajs.JSON
import io.scalajs.nodejs.console
import sri.BaseSuite

import sri.react._

import scala.scalajs.js
import scala.util.Random

object JsObjectConvertibleTest extends BaseSuite {

  private def genTestClass() =
    TestClass(
      Random.genAlphanumeric(10),
      Random.nextInt(),
      () => println("callback!"),
      Option(new js.Object())
    )

  test("to js object") {
    val testClass = this.genTestClass()
    val json = JSON.stringify(testClass.toJsObject)
    val expectedJson = s"""{"f1":"${testClass.f1}","v1":${testClass.v1},"nested":{"value$$2":{}}}"""
    expect(json).toBe(expectedJson)
    //console.log(JSON.stringify(testClass.toJsObject))
  }

  test("from js object") {
    val testClass = this.genTestClass()
    val obj = js.Dynamic.literal(
      f1 = testClass.f1,
      v1 = testClass.v1,
      callback = testClass.callback,
      nested = testClass.nested.asInstanceOf[js.Any]
    ).asInstanceOf[JsObject[TestClass]]
    val parsed = obj.toScalaClass
    expect(parsed).toEqual(testClass)
  }

  test("from js object negatively") {
    val obj = new JsObject[TestClass]
    expectFunc(() => obj.toScalaClass)
      .toThrow("Cannot convert js object to scala class, 'f1' is undefined")
  }

  test("transitivity") {
    val testClass = this.genTestClass()
    //console.log(testClass.toJsObject)
    expect(testClass.toJsObject.toScalaClass).toEqual(testClass)
  }

  test("call on js object") {
    val testClass = this.genTestClass()
    val jsObject = testClass.toJsObject

    expect(jsObject.call(_.f1)).toBe(testClass.f1)
    expect(jsObject.call(_.v1)).toBe(testClass.v1)
    expect(jsObject.call(_.callback)).toBe(testClass.callback)
    expect(jsObject.call(_.nested.get)).toBe(testClass.nested.get)
  }

  private case class TestClass(f1: String,
                               v1: Int,
                               callback: js.Function0[Unit],
                               nested: Option[AnyRef])
}
