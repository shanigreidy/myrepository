var express = require('express');
var router = express.Router();
var productsModel = require('../models/productsModel.js');
/* GET users listing. */
router.get('/:cat/:subcat',function(req, res, next){
  var productsCategories = productsModel.getCategories();
  var productsByCatAndSubCat = productsModel.getProductsByCatAndSubCat(req.params.cat, req.params.subcat);
  
  res.render('index', { title: 'Web Store' , subTitle: 'Smarter Shopping, Better Living!', 
  productsCategories: productsCategories, displayProducts: productsByCatAndSubCat});

});

router.get('/:cat/:subcat/:prod',function(req, res, next){
  var productsCategories = productsModel.getCategories();
  var selectedProduct = productsModel.getSelectedProduct(req.params.cat, req.params.subcat, req.params.prod);

  res.render('selectedProduct', { title: 'Web Store' , subTitle: 'Smarter Shopping, Better Living!', 
  productsCategories: productsCategories, selectedProduct: selectedProduct});

});

 router.get('/getSelectedProduct',function(req, res, next){
  var productObj = req.query; 
  var selectedProduct = productsModel.getSelectedProduct(productObj.category, productObj.subCategory, productObj.product);
    
  res.send(selectedProduct);  

});

module.exports = router;