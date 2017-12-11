const TestRenderer = require('react-test-renderer');
const React = require('react');
const ReactDOM = require('react-dom');

class Greeting extends React.Component {
  render() {
    return this.props.name;
  }
}

const element = React.createElement('Greeting',{name:'myName'});
const testRenderer = TestRenderer.create(element);
const testInstance = testRenderer.root

console.log(testInstance.findByProps({name:'myName1'}));