package anywhere

import sri.web.ReactDOM
import org.scalajs.dom

object Main {
  def main(args: Array[String]): Unit = {
    ReactDOM.render(BasicInput(), dom.document.getElementById("app"))
    println("Client render completed.")
  }
}
