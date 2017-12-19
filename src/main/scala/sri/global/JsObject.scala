package sri.global

import scala.annotation.tailrec
import scala.language.experimental.macros
import scala.reflect.macros.whitebox
import scala.scalajs.js

class JsObject[T] extends js.Object { jsObjectSelf =>
  def call[R](selectFunc: T => R): R = macro JsObjectMacroImpl.selectImpl[T, R]
}

private class JsObjectMacroImpl(val c: whitebox.Context) {

  import c.universe._

  def selectImpl[T: c.WeakTypeTag, R: c.WeakTypeTag](selectFunc: c.Tree): c.Tree = {
    val tpe = weakTypeOf[T]
    val returnType = weakTypeOf[R]

    val select = selectFunc match {
      case Function(_, tree) => tree
      case _ => c.abort(c.enclosingPosition, "Can only be select function like: _.fieldname")
    }
    val firstName :: tailNames = recursivelySelect(select)
    val firstType = tpe.decl(firstName).typeSignature

    val firstSelection =
      q"${c.prefix}.asInstanceOf[js.Dynamic].selectDynamic(${firstName.toString}).asInstanceOf[$firstType]"

    val dynamicSelect = tailNames.foldLeft(firstSelection) { (l, r) => q"$l.${r.toTermName}" }

    q"""import scala.scalajs.js
       $dynamicSelect.asInstanceOf[$returnType]"""
  }

  @tailrec
  private def recursivelySelect(select: Tree, acc: Seq[Name] = Seq.empty): Seq[Name] = {
    select match {
      case Apply(_, subselect) => recursivelySelect(subselect.head, acc)
      case Select(Ident(_), name) => name +: acc
      case Select(preTree, name) => recursivelySelect(preTree, name +: acc)
      case bad => c.abort(c.enclosingPosition, s"${showCode(bad)} not supported here.")
    }
  }
}