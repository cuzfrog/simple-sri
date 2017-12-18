/* http://airbnb.io/enzyme/ */

package sri.react.testutils.enzyme

import sri.react.testutils.EnzymeSelector
import sri.react.{JsPropsWrapper, JsStateWrapper, ReactElement, ReactNode}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@js.native
@JSImport("enzyme", JSImport.Namespace)
object Enzyme extends js.Object {

  /**
   * Shallow rendering is useful to constrain yourself to testing a component as a unit,
   * and to ensure that your tests aren't indirectly asserting on behavior of child components.
   *
   * @param node    (ReactElement): The node to render
   * @param options options.context: (Object [optional]): Context to be passed into the component<br>
   *                options.disableLifecycleMethods: (Boolean [optional]): If set to true, componentDidMount is not called on the component, and componentDidUpdate is not called after setProps and setContext. Default to false.
   * @return
   */
  def shallow[P, S](node: ReactElement,
              options: js.UndefOr[js.Object] = js.undefined): ShallowWrapper[P, S] = js.native

  /**
   *
   * Full DOM rendering is ideal for use cases where you have components that may interact with DOM APIs,
   * or may require the full lifecycle in order to fully test the component (i.e., componentDidMount etc.)
   * <br><br>
   * Full DOM rendering requires that a full DOM API be available at the global scope.
   * This means that it must be run in an environment that at least “looks like” a browser environment.
   * If you do not want to run your tests inside of a browser,
   * the recommended approach to using mount is to depend on a library
   * called jsdom which is essentially a headless browser implemented completely in JS.
   * <br><br>*
   * Note: unlike shallow or static rendering,
   * full rendering actually mounts the component in the DOM,
   * which means that tests can affect each other if they are all using the same DOM.
   * Keep that in mind while writing your tests and,
   * if necessary, use .unmount() or something similar as cleanup.
   *
   * @param node    (ReactElement): The node to render
   * @param options options.context: (Object [optional]): Context to be passed into the component<br>
   *                options.attachTo: (DOMElement [optional]): DOM Element to attach the component to.<br>
   *                options.childContextTypes: (Object [optional]): Merged contextTypes for all children of the wrapper.<br>
   */
  def mount[P, S](node: ReactElement,
            options: js.UndefOr[js.Object] = js.undefined): ReactWrapper[P, S] = js.native
}

/** Common methods */
@js.native
trait EnzymeWrapper[P, S] extends js.Object {

  type W <: EnzymeWrapper[P, S]

  def length: Int = js.native

  /** Find every node in the render tree that matches the provided selector. */
  def find(selector: EnzymeSelector): W = js.native

  /** Find every node in the render tree that returns true for the provided predicate function. */
  def findWhere(predicate: W => Boolean): W = js.native

  /** Remove nodes in the current wrapper that do not match the provided selector. */
  def filter(selector: EnzymeSelector): W = js.native

  /** Remove nodes in the current wrapper that do not return true for the provided predicate function. */
  def filterWhere(predicate: W => Boolean): W = js.native

  /** Removes nodes that are not host nodes; e.g., this will only return HTML nodes. */
  def hostNodes(): W = js.native

  /** Returns whether or not a given node or array of nodes is somewhere in the render tree. */
  def contains(node: ReactNode*): Boolean = js.native

  /** Returns whether or not a given react element exists in the shallow render tree. */
  def containsMatchingElement(node: ReactNode): Boolean = js.native

  /** Returns whether or not all the given react elements exists in the shallow render tree. */
  def containsAllMatchingElements(nodes: ReactNode*): Boolean = js.native

  /** Returns whether or not one of the given react elements exists in the shallow render tree. */
  def containsAnyMatchingElements(nodes: ReactNode*): Boolean = js.native

  /** Returns whether or not the current render tree is equal to the given node, based on the expected value. */
  def equals(node: ReactNode): Boolean = js.native

  /** Returns whether or not a given react element matches the shallow render tree. */
  def matchesElement(node: ReactElement): Boolean = js.native

  /** Returns whether or not the current node has the given class name or not. */
  def hasClass(className: String): Boolean = js.native

  /** Returns whether or not the current node matches a provided selector. */
  def is(selector: EnzymeSelector): Boolean = js.native

  /** Returns whether or not the current component returns a falsy value. */
  def isEmptyRender(): Boolean = js.native

  /** Remove nodes in the current wrapper that match the provided selector. (inverse of .filter()) */
  def not(selector: EnzymeSelector): W = js.native

  /** Get a wrapper with all of the children nodes of the current wrapper. */
  def children(): W = js.native

  /** Returns a new wrapper with child at the specified index. */
  def childAt(index: Int): W = js.native

  /** Get a wrapper with all of the children nodes of the current wrapper. */
  def parents(): W = js.native

  /** Get a wrapper with the direct parent of the current node. */
  def parent(): W = js.native

  /** Get a wrapper with the first ancestor of the current node to match the provided selector. */
  def closest(selector: EnzymeSelector): W = js.native

  /** Returns a CheerioWrapper around the rendered HTML of the current node's subtree.
   * *
   * Note: can only be called on a wrapper of a single node. */
  def render(): CheerioWrapper = js.native

  /** A method that unmounts the component.
   * This can be used to simulate a component going through an unmount/mount lifecycle. */
  def unmount(): W = js.native

  /** Returns a string of the rendered text of the current render tree.
   * This function should be looked at with skepticism
   * if being used to test what the actual HTML output of the component will be.
   * If that is what you would like to test, use enzyme's render function instead.
   * *
   * Note: can only be called on a wrapper of a single node. */
  def text(): String = js.native

  /** Returns a static HTML rendering of the current node. */
  def html(): String = js.native

  /** Returns the node at a given index of the current wrapper. */
  def get(index: Int): ReactElement = js.native

  /** Returns the wrapper's underlying node. */
  def getNode(): ReactElement = js.native

  /** Returns the wrapper's underlying nodes. */
  def getNodes(): js.Array[ReactElement] = js.native

  /** Returns a wrapper of the node at the provided index of the current wrapper. */
  def at(index: Int): W = js.native

  /** Returns a wrapper of the first node of the current wrapper. */
  def first(): W = js.native

  /** Returns a wrapper of the last node of the current wrapper. */
  def last(): W = js.native

  /** Returns the state hash for the root node of the wrapper.
   * Optionally pass in a prop name and it will return just that value. */
  def state(key: js.UndefOr[String] = js.undefined): JsStateWrapper[S] = js.native

  /** Returns the context hash for the root node of the wrapper.
   * Optionally pass in a prop name and it will return just that value. */
  def context(key: js.UndefOr[String] = js.undefined): js.Any = js.native

  /** Returns the props hash for the root node of the wrapper.
   * .props() can only be called on a wrapper of a single node.
   * *
   * NOTE: When called on a shallow wrapper,
   * .props() will return values for props on the root node that the component renders,
   * not the component itself. To return the props for the entire React component,
   * use wrapper.instance().props. See .instance() => ReactComponent */
  def props(): JsPropsWrapper[P] = js.native

  /** Returns the prop value for the root node of the wrapper with the provided key.
   * .prop(key) can only be called on a wrapper of a single node. */
  def prop(key: String): js.Any = js.native

  /** Returns the key value for the node of the current wrapper.
   * *
   * NOTE: can only be called on a wrapper of a single node. */
  def key(): String = js.native

  /** A method to invoke setState() on the root component instance
   * similar to how you might in the definition of the component, and re-renders.
   * This method is useful for testing your component in hard to achieve states,
   * however should be used sparingly.
   * If possible, you should utilize your component's external API
   * (which is accessible via .instance()) in order to get it into whatever state you want to test,
   * in order to be as accurate of a test as possible. This is not always practical, however.
   * *
   * NOTE: can only be called on a wrapper instance that is also the root instance. */
  def setState(nextState: JsStateWrapper[S],
               callback: js.UndefOr[js.Function] = js.undefined): W = js.native

  /**
   * A method that sets the props of the root component, and re-renders.
   * Useful for when you are wanting to test how the component behaves over time with changing props.
   * Calling this, for instance, will call the componentWillReceiveProps lifecycle method.
   * *
   * Similar to setState, this method accepts a props object and will merge it in with the already existing props.
   * *
   * NOTE: can only be called on a wrapper instance that is also the root instance.
   */
  def setProps(nextProps: JsPropsWrapper[P]): W = js.native

  /** A method that sets the context of the root component, and re-renders.
   * Useful for when you are wanting to test how the component behaves over time with changing contexts.
   * *
   * NOTE: can only be called on a wrapper instance that is also the root instance. */
  def setContext(context: js.Object): W = js.native

  /** Gets the instance of the component being rendered as the root node passed into shallow(). */
  def instance(): js.Object = js.native

  /** Forces a re-render. Useful to run before checking the render output
   * if something external may be updating the state of the component somewhere. */
  def update(): W = js.native

  /** Returns a string representation of the current shallow render tree for debugging purposes. */
  def debug(): String = js.native

  /** Returns the type of the current node of this wrapper.
   * If it's a composite component, this will be the component constructor.
   * If it's native DOM node, it will be a string of the tag name. If it's null, it will be null. */
  def `type`(): String | js.Function | Null = js.native

  /**
   * Returns the name of the current node of this wrapper. If it's a composite component,
   * this will be the name of the top-most rendered component.
   * If it's native DOM node, it will be a string of the tag name. If it's null, it will be null.
   * *
   * The order of precedence on returning the name is: type.displayName -> type.name -> type.
   */
  def name(): String | Null = js.native

  /**
   * Iterates through each node of the current wrapper and executes the provided function
   * with a wrapper around the corresponding node passed in as the first argument.
   *
   * @param fn Function ( W node, Number index )
   */
  def forEach(fn: js.Function2[W, Int, _]): W = js.native

  /** Maps the current array of nodes to another array.
   * Each node is passed in as a W to the map function. */
  def map[A](fn: js.Function2[W, Int, A]): js.Array[A] = js.native

  /**
   * Applies the provided reducing function to every node in the wrapper to reduce to a single value.
   * Each node is passed in as a W, and is processed from left to right.
   *
   * @param fn           (Function): A reducing function to be run for every node in the collection,
   *                     with the following arguments:
   *                     <br>
   *                     value (T): The value returned by the previous invocation of this function<br>
   *                     node (W): A wrapper around the current node being processed<br>
   *                     index (Number): The index of the current node being processed<br>
   * @param initialValue (T [optional]): If provided, this will be passed in as the first argument
   *                     to the first invocation of the reducing function.
   *                     If omitted, the first node will be provided and the iteration will
   *                     begin on the second node in the collection.
   */
  def reduce[A, B](fn: js.Function3[A, W, Int, A],
                   initialValue: js.UndefOr[A] = js.undefined): A = js.native

  /** See reduce() */
  def reduceRight[A, B](fn: js.Function3[A, W, Int, A],
                        initialValue: js.UndefOr[A] = js.undefined): A = js.native

  /** Returns a new wrapper with a subset of the nodes of the original wrapper,
   * according to the rules of Array#slice. */
  def slice(begin: Int = 0, end: js.UndefOr[Int] = js.undefined): W = js.native

  /** Invokes interceptor and returns itself. interceptor is called with itself.
   * This is helpful when debugging nodes in method chains. */
  def tap(interceptor: js.Function1[W, _]): W = js.native

  /** Returns whether or not any of the nodes in the wrapper match the provided selector. */
  def some(selector: EnzymeSelector): Boolean = js.native

  /** Returns whether or not any of the nodes in the wrapper pass the provided predicate function. */
  def someWhere(predicate: js.Function1[W, Boolean]): Boolean = js.native

  /** Returns whether or not all of the nodes in the wrapper match the provided selector. */
  def every(selector: EnzymeSelector): Boolean = js.native

  /** Returns whether or not all of the nodes in the wrapper pass the provided predicate function. */
  def everyWhere(predicate: js.Function1[W, Boolean]): Boolean = js.native

}