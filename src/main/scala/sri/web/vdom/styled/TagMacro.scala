package sri.web.vdom.styled

import scala.language.experimental.macros
import scala.reflect.macros.whitebox

trait TypedProp[+A]
private case object DummyProp extends TypedProp[Nothing]

private class MacroImpl(val c: whitebox.Context) {
  import c.universe._
  def domChooseImpl(props: c.Tree*)(children: c.Tree*): c.Tree = {
    val domName = c.macroApplication.symbol.asTerm.name.toString
    val allProps = parseProps(props)
    q"""
       {
         import scala.scalajs.js
         val domProps = js.Dynamic.literal()
         ..$allProps
         val _children = js.Array[sri.react.ReactNode](..$children)
         sri.web.vdom.CreateDOMElement(ctor = $domName, props = domProps, children = _children )
       }
       """
  }

  def domChooseNoChildrenImpl(props: c.Tree*): c.Tree = {
    val tree = domChooseImpl(props: _*)()
    q"$tree"
  }

  private def parseProps(props: Seq[c.Tree]): Seq[c.Tree] = {
    val allProps = props.map { prop =>
      val argTpe = scala.util.Try(prop.symbol.typeSignature.paramLists.head.head.typeSignature).toOption
      if (argTpe.isEmpty) c.abort(c.enclosingPosition, "Param type cannot be parsed by macros.")

      val (propName, propValue) = prop match {
        case q"$propName := $ideaFunc($propValue)"
          if ideaFunc.symbol.name.toString == "intellijIdeaEventConversion" => (propName, propValue)
        case q"$propName := $propValue" => (propName, propValue)
        case bad => c.abort(c.enclosingPosition,
          s"Content after :=($bad) cannot be parsed by macros. Check your argument types.")
      }
      val propTermName = propName.symbol.asTerm.name.toString
      val v = {
        val funcTpe = typeOf[scala.scalajs.js.UndefOr[sri.web.vdom.styled.SyntheticEventCallback]].toString
        if (argTpe.get.toString == funcTpe) q"$propValue"
        else q"$propValue.asInstanceOf[js.Any]"
      }
      //println(typeOf[scala.scalajs.js.UndefOr[sri.macros.web.SyntheticEventCallback]])
      q"""
          domProps.updateDynamic($propTermName)($v)
        """
    }
    //allProps.foreach(t => println("macro-" + showCode(t)))
    allProps
  }
}