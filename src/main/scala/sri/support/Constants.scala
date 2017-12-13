package sri.support

import scala.scalajs.js

private[sri] object Constants {
  val REACT_ELEMENT_TYPE: js.Any =
    if (js.typeOf(js.Dynamic.global.`Symbol`) != "undefined" && !js
      .isUndefined(js.Dynamic.global.`Symbol`.`for`))
      js.Symbol.forKey("react.element")
    else 0xeac7
}
