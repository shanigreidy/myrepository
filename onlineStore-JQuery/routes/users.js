var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var urlEncodedParser = bodyParser.urlencoded({extended: false});
var usersModel = require('../models/usersModel.js');

/* GET users listing. */

router.get('/', function(req, res, next) {
  var allUsers = usersModel.getAllUsers();
  res.send(allUsers);
});

router.post('/register',urlEncodedParser, function(req, res, next) {
  var userRegistrationData = {
     name: req.body.name,
     password: req.body.password
  };
  usersModel.addUser(userRegistrationData);
});

router.post('/login', function(req, res, next) {
  var userLoginData = {
     name: req.body.name,
     password: req.body.password
  };
  usersModel.loginUser(userLoginData.name);
});

router.get('/getCurrentUser', function(req, res, next) {
  var currentUser = usersModel.getCurrentUser();
  res.send(currentUser);
});

module.exports = router;
