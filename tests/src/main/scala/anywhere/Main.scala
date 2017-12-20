package anywhere

import anywhere.component.BasicInput
import anywhere.component.filterlist.FilterList
import org.scalajs.dom
import sri.web.ReactDOM

object Main {
  def main(args: Array[String]): Unit = {
    try {
      ReactDOM.render(FilterList(), dom.document.getElementById("app"))
      println("Client render completed.")

    } catch {
      case t:Throwable => t.printStackTrace()
        throw t
    }
  }
}
