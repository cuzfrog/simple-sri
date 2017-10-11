package sri.core

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.|.Evidence

/**
  * https://github.com/scala-js/scala-js/issues/2714
  *
  * @tparam A
  */
abstract sealed class OptionalParam[+A] {
  def foreach[U](f: A => U): Unit
}

case object OptDefault extends OptionalParam[Nothing] {
  def foreach[U](f: Nothing => U): Unit = ()
}

@inline final case class OptSpecified[+A](val get: A)
  extends OptionalParam[A] {
  def foreach[U](f: A => U): Unit = f(get)
}

sealed abstract class LowPriorityImplicits3 {
  implicit def any2OptOrUnion[A, B1, B2](a: A)(
    implicit ev: Evidence[A, B1 | B2]): OptionalParam[B1 | B2] = {
    OptSpecified(a).asInstanceOf[OptionalParam[B1 | B2]]
  }
}

sealed abstract class LowPriorityImplicits2 extends LowPriorityImplicits3 {
  implicit def undef2Opt[A](undef: js.UndefOr[A]): OptionalParam[A] =
    undef.fold[OptionalParam[A]](OptDefault)(OptionalParam.specified)
}

sealed abstract class LowPriorityImplicits extends LowPriorityImplicits2 {
  implicit def undef2OptOrUnion[A, B1, B2](undef: js.UndefOr[A])
                                          (implicit ev: Evidence[A, B1 | B2]): OptionalParam[B1 | B2] = undef2Opt(undef)
}

object OptionalParam extends LowPriorityImplicits {
  implicit def specified[A](a: A): OptSpecified[A] = OptSpecified(a)

}
