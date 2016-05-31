var React = require('react');

var App = React.createClass({

  propTypes: {
    message: React.PropTypes.string
  },

  render: function() {
    return (
      <div>Hello, {this.props.message}</div>
    );
  }
});

module.exports = App;
