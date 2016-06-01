var React = require('react');

var Frame = React.createClass({

  propTypes: {
    body: React.PropTypes.string,
    state: React.PropTypes.object
  },

  render: function() {
    return (
      <html>
        <head>
        </head>
        <body>
          <div data-mount="app" dangerouslySetInnerHTML={{__html: this.props.body}}></div>
          <script type="application/data" data-initial="app"
            dangerouslySetInnerHTML={{__html: JSON.stringify(this.props.state)}}></script>
          <script src="/bundle.js"></script>
        </body>
      </html>
    );
  }
});

module.exports = Frame;
