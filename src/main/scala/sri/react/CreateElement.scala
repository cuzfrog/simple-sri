package sri.react

import scala.scalajs.js

object CreateElement {
  def apply[C <: Component](props: C#P,
                            children: ReactNode*)
                           (implicit ctorTag: js.ConstructorTag[C#InnerComponent]): ReactElement = {
    ReactJS.createElement(js.constructorOf[C#InnerComponent], JsWrapper(props), children)
  }

  def apply[C <: Component](children: ReactNode*)
                           (implicit ctorTag: js.ConstructorTag[C#InnerComponent]): ReactElement = {
    ReactJS.createElement(js.constructorOf[C#InnerComponent], js.undefined, children)
  }
}

