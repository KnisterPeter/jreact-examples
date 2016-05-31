'use strict';

var React = require('react');

var App = React.createClass({
  displayName: 'App',


  propTypes: {
    message: React.PropTypes.string
  },

  render: function render() {
    return React.createElement(
      'div',
      null,
      'Hello, ',
      this.props.message
    );
  }
});

module.exports = App;