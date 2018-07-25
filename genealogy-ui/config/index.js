'use strict'

const path = require('path')

module.exports = {
  dev: {
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    proxyTable: {},
    host: 'localhost',
    port: 9528,
    autoOpenBrowser: true,
    errorOverlay: true,
    notifyOnErrors: false,
    poll: false,
    useEslint: false,
    showEslintErrorsInOverlay: false,
    devtool: 'cheap-source-map',
    cacheBusting: true,
    cssSourceMap: false,
    mocker:'local'
  },
  build: {
    index: path.resolve(__dirname, '../../resources/static/index.html'),
    assetsRoot: path.resolve(__dirname, '../../resources/static'),
    assetsSubDirectory: '',
    assetsPublicPath: '/',
    productionSourceMap: false,
    devtool: '#source-map',
    productionGzip: false,
    productionGzipExtensions: ['js', 'css'],
    bundleAnalyzerReport: process.env.npm_config_report
  }
}
