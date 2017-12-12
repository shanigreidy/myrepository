var express = require('express');
var mysql = require('mysql');
var router = express.Router();
var con = mysql.createConnection({host:'127.0.0.1', user:'root', password:'shani', database:"jobmarketplacedb"});

router.get('/jobs', function(req, res, next) {
    var sql = "select alljobs.* from alljobs";
    
    con.query(sql, function(err, result){
        if(err){
            throw new Error ('ERROR: NO TABLE CREATED');
        }
        else{
            res.send(result);
        }
    });
});

module.exports = router;