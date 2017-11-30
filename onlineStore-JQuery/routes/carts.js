var express = require('express');
var router = express.Router();
var cartsModel = require('../models/cartsModel.js');

/* GET users listing. */
router.get('/cart', function(req, res, next) {
  var cartData= cartsModel.createCart();
  res.render('cart',{cartData: cartData ,title: 'Web Store' , subTitle: 'Smarter Shopping, Better Living!'}); 

});

router.get('/addProductToCart', function(req, res, next) {
    var product = req.query;
    cartsModel.addProductToCart(product);
});

router.get('/cartData', function(req, res, next) {
    var cartData = cartsModel.getCartData();
    res.send(cartData);
});

 router.get('/delete',function(req, res, next){
  var product = req.query; 
  cartsModel.deleteProduct(product.name);
});

router.get('/changeQuantity', function(req, res, next) {
  var product = req.query; 
  cartsModel.changeProductQuantity(product);
});

router.get('/getNumberOfProductsInCart', function(req, res, next) {
  var numberOfProductsInCart = cartsModel.getNumberOfProductsInCart(); 
  res.send(""+numberOfProductsInCart+"");
});

module.exports = router;
