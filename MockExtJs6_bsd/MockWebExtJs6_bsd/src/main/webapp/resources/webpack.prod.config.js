const path               = require('path');
const webpack            = require('webpack');
const CommonsChunkPlugin = require('webpack/lib/optimize/CommonsChunkPlugin');
const CompressionPlugin    = require('compression-webpack-plugin');
const CopyWebpackPlugin  = require('copy-webpack-plugin');
const DedupePlugin         = require('webpack/lib/optimize/DedupePlugin');
const DefinePlugin       = require('webpack/lib/DefinePlugin');
const OccurenceOrderPlugin = require('webpack/lib/optimize/OccurenceOrderPlugin');
const ProvidePlugin      = require('webpack/lib/ProvidePlugin');
const UglifyJsPlugin       = require('webpack/lib/optimize/UglifyJsPlugin');
const ENV  = process.env.NODE_ENV = 'development';
const HOST = process.env.HOST || 'localhost';
const PORT = process.env.PORT || 8080;

const metadata = {
  baseUrl: '/',
  ENV    : ENV,
  host   : HOST,
  port   : PORT
};

module.exports = {
  debug: false,
  devServer: {
    contentBase: 'src',
    historyApiFallback: true,
    host: metadata.host,
    port: metadata.port,
    proxy: {
      '/api/*': {
        target: 'http://localhost:8000',
        secure: false
      }
    }
  },
  devtool: 'source-map',
  entry: {
    'main'  : './app/main.ts',
    'vendor': './app/vendor.ts'
  },
  module: {
    loaders: [
      {test: /\.css$/,   loader: 'raw', exclude: /node_modules/},
      {test: /\.css$/,  loader: 'style-loader!css-loader'}, 
      {test: /\.css$/,   loader: 'style!css?-minimize', exclude: /app/},
       
      {test: /\.html$/,  loader: 'raw'},
      {test: /\.ts$/,    loader: 'ts', query: {compilerOptions: {noEmit: false}}},
      {test: /\.woff$/,  loader: 'url?limit=50000&mimetype=application/font-woff'},
      {test: /\.woff2$/, loader: 'url?limit=50000&mimetype=application/font-woff'},
      {test: /\.ttf$/,   loader: 'url?limit=50000&mimetype=application/octet-stream'},
      {test: /\.svg$/,   loader: 'url?limit=50000&mimetype=image/svg+xml'},
      {test: /\.eot$/,   loader: 'file'}
    ]
  },
  output: {
    path    : './dist',
    filename: 'bundle.js'
  },
  plugins: [
    new CommonsChunkPlugin({name: 'vendor', filename: 'vendor.bundle.js', minChunks: Infinity}),
    new CompressionPlugin({regExp: /\.css$|\.html$|\.js$|\.map$/, threshold: 1500}),
    new CopyWebpackPlugin([{from: './src/index.html', to: 'index.html'}]),
    new DedupePlugin(),
    new DefinePlugin({'webpack': {'ENV': JSON.stringify(metadata.ENV)}}),
    new OccurenceOrderPlugin(true),
    new UglifyJsPlugin({
      compress : {screw_ie8 : true},
      mangle: {screw_ie8 : true } 
    }),
    new ProvidePlugin({jQuery: 'jquery', jquery: 'jquery', $: 'jquery'})
  ],
  resolve: {
    extensions: ['', '.ts', '.js']
  }
};
