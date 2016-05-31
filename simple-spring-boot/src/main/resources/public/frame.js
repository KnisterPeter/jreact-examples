"use strict";

var React = require('react');

var Frame = React.createClass({
  displayName: "Frame",


  propTypes: {
    body: React.PropTypes.string
  },

  render: function render() {
    return React.createElement(
      "html",
      null,
      React.createElement("head", null),
      React.createElement(
        "body",
        null,
        React.createElement("div", { dangerouslySetInnerHTML: { __html: this.props.body } }),
        React.createElement("script", { src: "/bundle.js" })
      )
    );
  }
});

module.exports = Frame;