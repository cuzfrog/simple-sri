package sri.web.vdom

import org.scalajs.dom
import sri.react._
import sri.global.Constants

import scala.scalajs.js.|
import scala.scalajs.{LinkingInfo, js}

object CreateDOMElement {

  @inline
  def apply[C <: dom.Node](ctor: String,
                           props: js.Any,
                           key: String | Int = null,
                           ref: js.Function1[C, Unit] = null,
                           children: js.Array[ReactNode] = js.Array())
    : ReactElement { type Instance = C } = {

    if (LinkingInfo.developmentMode) {
      if (ref != null)
        props.asInstanceOf[js.Dynamic].updateDynamic("ref")(ref)
      if (key != null)
        props
          .asInstanceOf[js.Dynamic]
          .updateDynamic("key")(key.asInstanceOf[js.Any])
      ReactJS
        .createElement(ctor, props, children: _*)
        .asInstanceOf[ReactElement { type Instance = C }]
    } else { // https://babeljs.io/docs/plugins/transform-react-inline-elements/
      if (children.length == 1) {
        props
          .asInstanceOf[js.Dynamic]
          .updateDynamic("children")(children(0).asInstanceOf[js.Any])
      } else if (children.length > 1)
        props
          .asInstanceOf[js.Dynamic]
          .updateDynamic("children")(js.Array(children: _*))
      js.Dynamic
        .literal(
          `$$typeof` = Constants.REACT_ELEMENT_TYPE,
          `type` = ctor,
          props = props,
          ref = ref,
          key =
            if (key != null) "" + key
            else key.asInstanceOf[js.Any]
        )
        .asInstanceOf[ReactElement { type Instance = C }]
    }

  }

}
