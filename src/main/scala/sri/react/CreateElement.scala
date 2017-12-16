package sri.react

import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.reflect.Reflect

/**
 * React element factory.
 */
object CreateElement {
  @inline
  def apply[C <: BaseComponent](instance: C)
                               (props: C#Props,
                                key: js.UndefOr[String | Int] = js.undefined,
                                ref: js.UndefOr[js.Function1[_, Unit]] = js.undefined): ReactElement = {
    this.withChildren(instance)(props, key, ref)()
  }

  @inline
  def withChildren[C <: BaseComponent](instance: C)
                                      (props: C#Props,
                                       key: js.UndefOr[String | Int] = js.undefined,
                                       ref: js.UndefOr[js.Function1[_, Unit]] = js.undefined)
                                      (children: ReactNode*): ReactElement = {
    ReactJS.createElement(
      js.constructorOf[PrototypeComponent[C#Props, C#State, C]],
      JsPropsWrapper(instance)(props, key, ref),
      children: _*
    )
  }
}

