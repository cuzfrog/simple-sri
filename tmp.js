const TestRenderer = require('react-test-renderer');
const React = require('react');
const ReactDOM = require('react-dom');
const TestUtils = require('react-dom/test-utils');
const jsdom = require('jsdom');
const { JSDOM } = jsdom;
const dom = new JSDOM(`<!DOCTYPE html><p>Hello world</p>`);

class Greeting extends React.Component {
  render() {
    return this.props.name;
  }
}

const element = React.createElement(Greeting, {name:'myName'});
const testRenderer = TestRenderer.create(element);
const testInstance = testRenderer.root

const node = dom.window.document.createElement('div')

console.log(TestUtils.isElementOfType(element, Greeting));