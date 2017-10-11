const path = require('path');

module.exports = {
  entry: './webtest/target/scala-2.12/webtest-fastopt.js',
  output: {
    filename: 'bundle.test.js',
    path: path.resolve(__dirname, 'webtest/dist')
  }
};