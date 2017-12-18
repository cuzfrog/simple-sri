const TestRenderer = require('react-test-renderer');
const React = require('react');
const ReactDOM = require('react-dom');
const TestUtils = require('react-dom/test-utils');
const jsdom = require('jsdom');
const { JSDOM } = jsdom;
const dom = new JSDOM(`<!DOCTYPE html><p>Hello world</p>`);
const ShallowRenderer = require('react-test-renderer/shallow');
const Enzyme = require('enzyme');
const Adapter = require('enzyme-adapter-react-16');
Enzyme.configure({ adapter: new Adapter() });

class Greeting extends React.Component {
  render() {
    return [
      React.createElement(SubComponent,{name:this.props.name, key:1}),
      React.createElement(SubComponent,{name:'myName2', key:2})
    ];
  }

  constructor(props){
    super(props);
    console.log('Greeting constructed.');
  }
}

class SubComponent extends React.Component {
  render() {
    return createDiv(this.props.name);
  }
}

const createDiv = function(children){
  return React.createElement('div',{id:'test-div'},children);
}

const element = React.createElement(Greeting, {name:'myName'});
//const testRenderer = TestRenderer.create(element);
//const testInstance = testRenderer.root;
//
//const node = dom.window.document.createElement('div');
//
//const shallowRenderer = new ShallowRenderer();
//shallowRenderer.render(element);
//const result = shallowRenderer.getRenderOutput();

const shallow = Enzyme.shallow(element);
console.log(shallow.find('#test-div').name());