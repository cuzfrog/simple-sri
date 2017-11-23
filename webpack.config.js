const path = require('path');
const webpack = require('webpack');

const config = {
  entry: {
    main: './tests/target/scala-2.12/tests-fastopt.js'
//    vendor: [
//      'react', 'react-dom', 'redux', 'react-redux'
//    ]
  },
//  plugins: [
//    new webpack.optimize.CommonsChunkPlugin({
//      name: 'common',
//      minChunks: Infinity
//    })
//  ],
  output: {
    filename: '[name].bundle.js',
    path: path.resolve(__dirname, 'tests/target/dist')
  }
};

module.exports = config;