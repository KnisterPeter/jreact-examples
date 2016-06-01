var React = require('react');
var ReactDOM = require('react-dom');
var App = require('./app');
var fetch = require('isomorphic-fetch');

function render(props) {
  ReactDOM.render(<App {...props} />, document.querySelector('[data-mount=app]'));
}

var props = JSON.parse(document.querySelector('[data-initial=app]').innerHTML);
render(props);

var names = [
  'Lorem',
  'ipsum',
  'dolor',
  'sit',
  'amet',
  'consetetur',
  'sadipscing',
  'elitr'
];

setInterval(() => {
  fetch(`/?name=${names[Math.ceil((names.length - 1) * Math.random())]}`)
    .then((response) => response.json())
    .then((props) => render(props));
}, 1000);
