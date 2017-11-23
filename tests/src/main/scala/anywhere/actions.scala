package anywhere

import scala.scalajs.js

sealed trait Action

case class FilterChange(v: String) extends Action