package sri.react.testutils

import anywhere.component.BasicInput
import io.scalajs.JSON
import io.scalajs.nodejs.console
import sri.react.DomElement

import scala.language.reflectiveCalls
import scala.scalajs.js

object ShallowRendererTest extends sjest.JestSuite {
  private val rendererJS = new ShallowRendererJS
  private val props = BasicInput.Props("some 902sd initial value")
  private val element = BasicInput(props)

  private type RenderedElement = DomElement {
    type Props = RenderedPropsOfBasicInput
  }
  private val resultJS = {
    rendererJS.render(element)
    rendererJS.getRenderOutput().asInstanceOf[RenderedElement]
  }
  private val result = ShallowRenderer.render(element).asInstanceOf[RenderedElement]

  test("ShallowRendererJS") {
    console.log(resultJS)
    expect(resultJS.`type`).toBe("input")
  }
  test("ShallowRenderer") {
    console.log(result)
    expect(JSON.stringify(result)).toEqual(JSON.stringify(resultJS))
    expect(result.props.value).toBe(props.initialValue)
  }
}

@js.native
sealed trait RenderedPropsOfBasicInput extends js.Object {
  val value: String
  val onChange: js.Function
}