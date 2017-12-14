const TestRenderer = require('react-test-renderer');
const React = require('react');
const ReactDOM = require('react-dom');
const TestUtils = require('react-dom/test-utils');
const jsdom = require('jsdom');
const { JSDOM } = jsdom;
const dom = new JSDOM(`<!DOCTYPE html><p>Hello world</p>`);
const ShallowRenderer = require('react-test-renderer/shallow');

class Greeting extends React.Component {
  render() {
    return [
      React.createElement(SubComponent,this.props),
      React.createElement(SubComponent,{name:'myName2'})
    ];
  }

  constructor(props){
    super(props);
    console.log('Greeting constructed.');
  }
}

class SubComponent extends React.Component {
  render() {
    return this.props.name;
  }
}

const element = React.createElement(Greeting, {name:'myName'});
const testRenderer = TestRenderer.create(element);
const testInstance = testRenderer.root;

const node = dom.window.document.createElement('div');

const shallowRenderer = new ShallowRenderer();
shallowRenderer.render(element);
const result = shallowRenderer.getRenderOutput();

console.log(result);
console.log(element);