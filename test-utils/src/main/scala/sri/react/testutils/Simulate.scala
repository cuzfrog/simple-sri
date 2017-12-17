package sri.react.testutils

import org.scalajs.dom

import scala.scalajs.js

@js.native
sealed trait Simulate extends js.Object {
  def beforeInput(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def blur(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def change(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def click(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def compositionEnd(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def compositionStart(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def compositionUpdate(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def contextMenu(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def copy(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def cut(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def doubleClick(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def drag(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def dragEnd(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def dragEnter(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def dragExit(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def dragLeave(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def dragOver(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def dragStart(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def drop(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def error(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def focus(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def input(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def keyDown(element: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def keyPress(element: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def keyUp(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def load(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def mouseDown(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def mouseEnter(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def mouseLeave(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def mouseMove(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def mouseOut(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def mouseOver(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def mouseUp(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def paste(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def reset(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def scroll(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def select(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def submit(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def touchCancel(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def touchEnd(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def touchMove(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def touchStart(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
  def wheel(node: dom.Node, eventData: js.Object = js.native): Unit = js.native
}
