const fs = require('fs');
const path = require('path');
function resolve (dir) {
  return path.join(__dirname, '..', dir);
}
const mock = {};
fs.readdirSync(resolve('mocker')).forEach(function (file) {
  if(file !== 'index.js'){
    Object.assign(mock, require('./' + file));
  }
});
module.exports = mock;
