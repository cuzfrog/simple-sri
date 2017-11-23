package anywhere

import anywhere.component.ListView
import org.scalajs.dom
import sri.web.ReactDOM

object Main {
  def main(args: Array[String]): Unit = {
    try {

      implicit val store = Store.init
      ReactDOM.render(ListView(), dom.document.getElementById("app"))
      println("Client render completed.")

    } catch {
      case t:Throwable => t.printStackTrace()
        throw t
    }
  }
}
