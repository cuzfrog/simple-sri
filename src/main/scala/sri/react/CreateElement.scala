package sri.react

import scala.reflect.ClassTag
import scala.scalajs.js

object CreateElement {
  def apply[C <: Component : ClassTag](props: C#P,
                                       children: ReactNode*): ReactElement = {
    ReactJS.createElement(js.constructorOf[C#InnerComponent[C]], JsWrapper[C#P, C](props), children)
  }

  def apply[C <: Component : ClassTag](children: ReactNode*): ReactElement = {
    ReactJS.createElement(js.constructorOf[C#InnerComponent[C]], js.undefined, children)
  }
}

