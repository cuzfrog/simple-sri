package anywhere

sealed trait Action

case class FilterChange(v: String) extends Action