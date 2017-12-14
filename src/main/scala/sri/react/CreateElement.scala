package sri.react

import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.reflect.Reflect

/**
 * React element factory.
 */
object CreateElement {
  //  def apply[C <: Component[C#Props, C#State] : ClassTag](props: C#Props,
  //                                                         children: ReactNode*): ReactElement = {
  //    ReactJS.createElement(
  //      js.constructorOf[PrototypeComponent[C#Props, C#State, C]],
  //      JsWrapper[P, C](props),
  //      children: _*)
  //  }

  //  def apply[C <: Component[P, S] : ClassTag](children: ReactNode*): ReactElement = {
  //    ReactJS.createElement(
  //      js.constructorOf[PrototypeComponent[P, S, C]],
  //      js.undefined,
  //      children: _*)
  //  }

  def apply[C <: BaseComponent](instance: C)(props: C#Props): ReactElement = {
    ReactJS.createElement(
      js.constructorOf[PrototypeComponent[C#Props, C#State, C]],
      JsPropsWrapper(instance)(props)
    )
  }

  // ----- alias -----
  //  def withPropsAndChildren
  //  [C <: Component[P, S] : ClassTag](props: P,
  //                                    children: ReactNode*): ReactElement = {
  //    this.apply[P, S, C](props, children: _*)
  //  }
  //
  //  def withChildren[C <: Component[P, S] : ClassTag](children: ReactNode*): ReactElement = {
  //    this.apply[P, S, C](children: _*)
  //  }
  //
  //    def withProps[C <: Component[_, _] : ClassTag](props: C#Props): ReactElement = {
  //      this.apply[C](props)
  //    }
}

