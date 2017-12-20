package anywhere.component.filterlist

import anywhere.{AppCircuit, Constants, Reset}
import io.scalajs.nodejs.console
import sri.react.testutils.enzyme.Enzyme

import scala.scalajs.js

object FilterListTest extends sjest.JestSuite {

  private lazy val wrapper = Enzyme.mount[FilterList.Props,Null](FilterList())

  beforeEach(AppCircuit.dispatch(Reset))

  test("structure") {
    val div = wrapper.find("div")
    expect(div.children().length).toBe(2)
    //console.log(div.debug())
    val input = div
      .childAt(0) //Connector wrapper
      .childAt(0) //Prototype component
      .childAt(0) //Input

    console.log(input.debug())
    expect(input.`type`()).toBe("input")
  }

  test("change on input updates list") {
    val v = "k"
    val event = createEvent(v)
    wrapper.find("input").simulate("change", event)
    val contents = wrapper.find("ul").children().map(_.text()).toSeq

    val expectedContents = Constants.elements.filter(_.contains(v))
    expect(contents).toEqual(expectedContents)
  }

  private def createEvent(v: js.Any): js.Object = {
    new js.Object {val target: js.Object = new js.Object {val value: js.Any = v}}
  }
}
