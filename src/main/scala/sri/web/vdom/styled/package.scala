package sri.web.vdom

import scala.scalajs.js

/**
  * Created by cuz on 17-4-26.
  */
package object styled {
  /** When function is assigned to val/var field, it has to be explicitly typed out as js.Function(N)
    * to make the val/var field recognized by node/browser as `Function` instead of an object named
    * AnnonymousFunctionXX.
    *
    * Note: Syntax sugar of scala function cannot achieve this.
    */
  type SyntheticEventCallback = js.Function1[_ <: SyntheticEvent[_], _]
}
