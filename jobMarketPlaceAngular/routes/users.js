var express = require('express');
var router = express.Router();
var usersModel = require('../modelDB/users');

router.post('/addUser', function(req, res, next){
    usersModel.addUser(req.body.personalDetails, function(result) {
        res.send(result);        
    });
}); 

router.post('/isUserExists', function(req, res, next){
    usersModel.isUserExists(req.body.userName, function(result) {
        res.send(result);        
    });
});

router.post('/isUserNameMatchPassword', function(req, res, next){
    usersModel.isUserNameMatchPassword(req.body, function(result) {
        res.send(result);        
    });
});

router.post('/getCurrentUser', function(req, res, next){
    usersModel.getCurrentUser(req.body.userName, function(result) {
        res.send(result);        
    });
});

router.post('/updateCurrentUser', function(req, res, next){
    usersModel.updateCurrentUser(req.body.user.personalDetails, req.body.currentUserName, function(result) {
        res.send(result);        
    });
});

router.post('/logInUser', function(req, res, next){
    req.session.id = req.body.userName;
});

router.post('/logOutUser', function(req, res, next){
    req.session.id = '';
});

module.exports = router;