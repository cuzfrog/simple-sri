package sri.global

import scala.annotation.tailrec
import scala.language.experimental.macros
import scala.reflect.macros.whitebox
import scala.scalajs.js

class JsObject[T] extends js.Object {
  def call[R](selectFunc: T => R): R = macro JsObjectMacroImpl.selectImpl[R]
}

private class JsObjectMacroImpl(val c: whitebox.Context) {

  import c.universe._

  def selectImpl[R: c.WeakTypeTag](selectFunc: c.Tree): c.Tree = {
    val tpe = weakTypeOf[R]

    //c.info(c.enclosingPosition, showRaw(selectFunc), false)

    val select = selectFunc match {
      case Function(_, tree) => tree
      case _ => c.abort(c.enclosingPosition, "Can only be select function like: _.fieldname")
    }
    val names = recursivelySelect(select)

    val dynamicSelect = names.foldLeft(q"this.asInstanceOf[js.Dynamic]": Tree) { (l, r) =>
      q"$l.selectDynamic(${r.toString})"
    }

    c.info(c.enclosingPosition, showCode(dynamicSelect), false)

    q"""import scala.scalajs.js
       $dynamicSelect.asInstanceOf[$tpe]"""
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