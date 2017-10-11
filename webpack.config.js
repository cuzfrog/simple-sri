const path = require('path');

module.exports = {
  entry: './tests/target/scala-2.12/tests-fastopt.js',
  output: {
    filename: 'bundle.test.js',
    path: path.resolve(__dirname, 'tests/dist')
  }
};