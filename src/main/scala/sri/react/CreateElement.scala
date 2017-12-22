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
  def apply[C <: BaseComponent : ClassTag]
  (props: C#Props,
   key: js.UndefOr[String | Int] = js.undefined,
   ref: js.UndefOr[js.Function1[_, Unit]] = js.undefined): CompositeElement = {

    this.withChildren[C](props, key, ref)()
  }

  @inline
  def withChildren[C <: BaseComponent : ClassTag]
  (props: C#Props,
   key: js.UndefOr[String | Int] = js.undefined,
   ref: js.UndefOr[js.Function1[_, Unit]] = js.undefined)
  (children: ReactNode*): CompositeElement = {

    val fqcn = implicitly[ClassTag[C]].runtimeClass.getName
    val instance = Reflect.lookupInstantiatableClass(fqcn) match {
      case Some(clazz) => clazz.newInstance().asInstanceOf[C]
      case None => throw new AssertionError(s"Cannot find registered class [$fqcn].")
    }
    ReactJS.createElement(
      js.constructorOf[PrototypeComponent[C#Props, C#State, C]],
      JsPropsWrapper(instance)(props, key, ref),
      children: _*
    ).asInstanceOf[CompositeElement]
  }
}

