'use strict';

var compression = require('compression');
var express = require('express');
var app = express();

// compress all requests
app.use(compression());

app.use(express.static(__dirname));

app.get('/*', function (req, res) {
  res.sendFile(__dirname + '/index.html');
});

var server = app.listen(process.env.PORT || 4200, function () {
  var host = server.address().address;
  var port = server.address().port;

  console.log('App listening at http://%s:%s', host, port);
});
