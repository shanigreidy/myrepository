var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var sessions = require('express-session');
var mysql = require('mysql');
var router = express.Router();

var con = mysql.createConnection({host:'127.0.0.1', user:'root', password:'shani', database:"jobmarketplacedb"});

var index = require('./routes/index.js');
var api = require('./routes/api.js');
var users = require('./routes/users.js');
var jobs = require('./routes/jobs.js');
var skills = require('./routes/skills.js');

var app = express();

app.set('views', path.join(__dirname, 'views'));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(sessions({
  secret:'*#*$*#*shani*#*$*#*',
  resave: false,
  saveUninitialized: true,
  // cookie: { secure: true }
}))
app.use(logger('dev'));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/api', api);
app.use('/users', users);
app.use('/jobs', jobs);
app.use('/skills', skills);
app.use('/', index);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

 con.connect(function(err){
    if(err) throw new Error ('No connect to mysql');
 });

module.exports = app;
