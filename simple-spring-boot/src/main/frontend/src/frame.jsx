var React = require('react');

var Frame = React.createClass({

  propTypes: {
    body: React.PropTypes.string
  },

  render: function() {
    return (
      <html>
        <head>
        </head>
        <body>
          <div dangerouslySetInnerHTML={{__html: this.props.body}}></div>
          <script src="/bundle.js"></script>
        </body>
      </html>
    );
  }
});

module.exports = Frame;
