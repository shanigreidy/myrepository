var express = require('express');
var mysql = require('mysql');

var router = express.Router();
var con = mysql.createConnection({host:'127.0.0.1', user:'root', password:'student', database:"airbnbDB"});

router.get('/hotels', function(req, res, next) {
    var sql = "select hotels.* from hotels";
    
    con.query(sql, function(err, result){
        if(err){
            throw new Error ('ERROR: NO TABLE CREATED');
        }
        else{
            res.send(result);
        }
    });
});

router.get('/cities', function(req, res, next) {
    var sql = "select cities.* from cities";
    
    con.query(sql, function(err, result){
        if(err){
            throw new Error ('ERROR: NO TABLE CREATED');
        }
        else{
            res.send(result);
        }
    });
});

router.get('/countries', function(req, res, next) {
    // con.connect(function(err){
    // if(err){
    //     throw new Error ('No connect to mysql');
    // }
    // else{
    // }
});

router.get('/:reviewsTableName', function(req, res, next) {
    let tableName = req.params.reviewsTableName + "Reviews";
    var sql = "select " + tableName +".* from " + tableName;
    
    con.query(sql, function(err, result){
        if(err){
            throw new Error ('ERROR: NO TABLE CREATED');
        }
        else{
console.log(result);
            res.send(result);
        }
    });
});

module.exports = router;