package sri.redux

import sri.core.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("redux", "connect")
@js.native
private object connectJS {
  def apply(mapStateToProps: js.Function,
            mapDispatchToProps: js.Function,
            mergeProps: js.Function,
            options: js.Object): js.Object = js.native
}

object connect {
  def apply[S, P, A](mapStateToProps: Function1[S, P],
                     mapDispatchToProps: Function1[Function1[A, A], P]) = {
    connectJS.apply(mapStateToProps, mapDispatchToProps, null, null)
  }
}