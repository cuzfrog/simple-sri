package anywhere.component.filterlist

import anywhere.{AppCircuit, Constants, FilterChange, Reset}
import io.scalajs.nodejs.console
import sri.react.testutils.enzyme.Enzyme

object ListViewTest extends sjest.JestSuite {

  import ListView.Props

  private def createWrapper = Enzyme.mount[Props, Null](ListView())

  beforeEach(AppCircuit.dispatch(Reset))

  test("initial view contents") {
    val lis = createWrapper.find("li")
    expect(lis.length).toBe(Constants.elements.length)
    //lis.map(_.text()).foreach(t => console.log(t))
    expect(lis.map(_.text()).toSeq).toEqual(Constants.elements)
  }

  test("filter changed"){
    AppCircuit.dispatch(FilterChange("a"))
    val lis = createWrapper.find("li")
    val contents = lis.map(_.text()).toSeq
    contents.foreach(println)
    val expectedContents = Constants.elements.filter(_.contains("a"))
    expect(contents).toEqual(expectedContents)
  }

}
