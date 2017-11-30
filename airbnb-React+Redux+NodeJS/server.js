var express = require("express");
var mysql = require('mysql');
var path = require('path');

var app = express();
var con = mysql.createConnection({host:'127.0.0.1', user:'root', password:'student', database:"airbnbDB"});

app.use(express.static('public'));

app.get('/', function (req, res) {
  res.sendfile(`${__dirname}/index.html`)
})

var index = require('./routes/index');
var api = require('./routes/api');

app.use('/api', api);
app.use('/', index);
app.get("*", function(req, res, next) {
   res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.listen(3000);

 con.connect(function(err){
    if(err) throw new Error ('No connect to mysql');
 });

module.exports = app;