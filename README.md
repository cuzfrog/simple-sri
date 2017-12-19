# Simple Scalajs-React-Interface
[![Build Status](https://travis-ci.org/cuzfrog/simple-sri.svg?branch=master)](https://travis-ci.org/cuzfrog/simple-sri)
[![Scala.js](https://www.scala-js.org/assets/badges/scalajs-0.6.17.svg)](https://www.scala-js.org)

Lightweight scalajs react solution.

Ported react version: 16

## Features

* Simple and straightforward style to build react component. 
* Use [diode](https://diode.suzaku.io/) instead of redux to manage state.
* Write your DOM tests side by side with [scala-jest](https://github.com/cuzfrog/scala-jest).

## Setup

Dependencies:

    "com.github.cuzfrog" %%% "simple-sri" % "0.2.0-SNAPSHOT"
    
Diode connector:

    "com.github.cuzfrog" %%% "simple-sri-diode" % "0.2.0-SNAPSHOT"

Test utilities:

    "com.github.cuzfrog" %%% "simple-sri-test-utils" % "0.2.0-SNAPSHOT" % Test

## Example

#### Define React component:
```scala
import sri.react._
import sri.web.vdom.tagsPrefix_<^._

class BasicInput extends Component[BasicInput.Props, BasicInput.State] {
  override def getInitialState = BasicInput.State("")

  override def render(): ReactRenders = {
    <.input(
      ^.value := state.value,
      ^.onChange := callback
    )
  }

  private val callback = (event: ReactEventI) => {
    val value = event.target.value
    setState(s => s.copy(value = value))
    println(value)
  }
}

object BasicInput {
  def apply(props: Props = Props()): ReactElement = CreateElement(new BasicInput)(props)

  case class State(value: String)
  case class Props()
}
```

You could use `ComponentP`, `ComponentS` to build component that only has Props or State.

#### Connect with diode circuit:

Define actions and models:
```scala
import diode.Action
case class FilterChange(v: String) extends Action
case class RootModel(filterModel: FilterModel)
case class FilterModel(filterValue: String, filteredElements: Seq[String])
```

Define diode circuit:
```scala
object AppCircuit extends Circuit[RootModel] with ReactConnector[RootModel] {...}
```

Connect to component:
```scala
class FilterInput extends ComponentP[FilterInput.Props] {...}

object FilterInput {
  case class Props(value: String, onChange: ReactEventI => Unit)

  def apply(): ReactElement = {
    AppCircuit.connect(_.filterModel) { proxy =>
      def value: String = proxy().filterValue
      val onChange: ReactEventI => Unit = (event: ReactEventI) => {
        val v = event.target.value
        println(s"filter input event! $v | $value")
        event.defaultPrevented
        proxy.dispatch(FilterChange(v))
      }
      CreateElement(new FilterInput)(Props(value, onChange))
    }
  }
}
```

You can find examples in `tests` sub-project.

## Document

#### Component detail

#### Test

## About

This project derives from [scalajs-react-interface](https://github.com/scalajs-react-interface) by @chandu0101.

Author: Cause Chung (cuzfrog@139.com)
 
License: Apache License Version 2.0