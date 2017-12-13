package sri.react

import scala.reflect.ClassTag
import scala.scalajs.js
import scala.scalajs.reflect.Reflect

/**
 * React element factory.
 */
object CreateElement {
  def apply[C <: BaseComponent : ClassTag](props: C#Props,
                                           children: ReactNode*): ReactElement = {

    val clazz = Reflect.lookupInstantiatableClass(implicitly[ClassTag[C]].runtimeClass.getName)
    val instance = clazz.get.newInstance().asInstanceOf[C]
    ReactJS.createElement(
      js.constructorOf[instance.InnerComponent[C]],
      JsWrapper[C#Props, C](props),
      children: _*)
  }

  def apply[C <: BaseComponent : ClassTag](children: ReactNode*): ReactElement = {
    ReactJS.createElement(
      js.constructorOf[C#InnerComponent[C]],
      js.undefined,
      children: _*)
  }

  def apply[C <: BaseComponent : ClassTag](props: C#Props): ReactElement = {
    ReactJS.createElement(
      js.constructorOf[C#InnerComponent[C]],
      JsWrapper[C#Props, C](props))
  }

  // ----- alias -----
  def withPropsAndChildren
  [C <: BaseComponent : ClassTag](props: C#Props,
                                  children: ReactNode*): ReactElement = {
    this.apply(props, children: _*)
  }

  def withChildren(children: ReactNode*): ReactElement = {
    this.apply(children: _*)
  }

  def withProps[C <: BaseComponent : ClassTag](props: C#Props): ReactElement = {
    this.apply(props)
  }
}

