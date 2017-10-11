package sri.web.vdom

import org.scalajs.dom
import sri.web.vdom.styled.{StyledAttributes, StyledTags, SyntheticEventCallback}
import sri.web.vdom.{ReactEventAliases, SyntheticEvent}

import scala.language.implicitConversions
import scala.scalajs.js

/**
  * Created by cuz on 17-4-26.
  */
object styledtags extends StyledTags with StyledAttributes with ReactEventAliases with IntellijHelper {
  type SyntheticEventCallback = sri.web.vdom.styled.SyntheticEventCallback
}

object styledtagsPrefix_<^ extends ReactEventAliases with IntellijHelper {
  type SyntheticEventCallback = sri.web.vdom.styled.SyntheticEventCallback
  object < extends StyledTags
  object ^ extends StyledAttributes
}


trait IntellijHelper {
  /** Do not change name of this function, which is used in macro.*/
  implicit def intellijIdeaEventConversion[N <: dom.Node, T <: SyntheticEvent[N]](in: T => _): js.UndefOr[SyntheticEventCallback] =
    throw new AssertionError("This implicit method should be stripped off by macro, thus should not be accessed.")
}