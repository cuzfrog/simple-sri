package anywhere

import diode.Action

case class FilterChange(v: String) extends Action

case object Reset extends Action