package sri.global

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

trait JsObjectConvertible[T <: Product with Serializable] {
  def toJs(t: T): JsObject[T]
  def fromJs(obj: JsObject[T]): T
}

private object JsObjectConvertible {
  implicit def macroEvidence[T]: JsObjectConvertible[T] = macro MacroImpl.provideEvidence[T]

  private class MacroImpl(val c: blackbox.Context) {

    import c.universe._

    def provideEvidence[T: c.WeakTypeTag]: c.Tree = {
      val tpe = weakTypeOf[T]
      val companion = tpe.typeSymbol.companion

      val fields = tpe.decls.collectFirst {
        case m: MethodSymbol if m.isPrimaryConstructor => m
      }.get.paramLists.head

      val (checkStats, toStats, fromParams) = fields.map { field =>
        val name = field.asTerm.name
        val key = name.decodedName.toTermName
        val returnType = tpe.decl(name).typeSignature

        val checkParam =
          q"""if(!obj.hasOwnProperty(${name.toString}))
             throw new scala.scalajs.runtime.UndefinedBehaviorError(
             "Cannot convert js object to scala class, '"+ ${name.toString} + "' is undefined")"""

        val toParam = q"jsDynamic.updateDynamic(${key.toString})(t.$name.asInstanceOf[js.Any])"

        val fromParam = q"obj.asInstanceOf[js.Dynamic].$key.asInstanceOf[$returnType]"

        (checkParam, toParam, fromParam)
      }.unzip3

      q"""
      import scala.scalajs.js
      new sri.global.JsObjectConvertible[$tpe] {

        def toJs(t: $tpe): JsObject[$tpe] = {
          val jsObject = new JsObject[$tpe]
          val jsDynamic = jsObject.asInstanceOf[js.Dynamic]
          ..$toStats
          jsObject
        }
        def fromJs(obj: JsObject[$tpe]): $tpe = {
          ..$checkStats
          $companion(..$fromParams)
        }
      }
      """
    }

    private def typeConversion(tpe: Type): Type = {
      ???
    }
  }
}

