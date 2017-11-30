var express = require('express');
var router = express.Router();
var productsModel = require('../models/productsModel.js');
var usersModel = require('../models/usersModel.js');
var cartsModel = require('../models/cartsModel.js');

/* GET users listing. */
router.get('/', function(req, res, next) {
  var productsCategories = productsModel.getCategories();
  var randomProducts = productsModel.getRandomProducts();

  res.render('index', { title: 'Web Store' , subTitle: 'Smarter Shopping, Better Living!', 
  productsCategories: productsCategories, displayProducts: randomProducts});
});

router.get('/register', function(req, res, next) {
  res.render('register', { title: 'Web Store' , subTitle: 'Smarter Shopping, Better Living!'});
});

router.get('/login', function(req, res, next) {
  res.render('login', { title: 'Web Store' , subTitle: 'Smarter Shopping, Better Living!'});
});

router.get('/logout', function(req, res, next) {
  usersModel.logOut();
  res.redirect("/");
});


module.exports = router;
