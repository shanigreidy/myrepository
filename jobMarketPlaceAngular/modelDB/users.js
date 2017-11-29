var mysql = require('mysql');
var con = mysql.createConnection({host:'127.0.0.1', user:'root', password:'shani', database:"jobmarketplacedb"});

module.exports = (function(){
    function addUser(user, callback) {
        var sql = "insert into users(userName, userPassword, userEmail, userFirstName, userLastName, userWorkDescription) values ?";
        var values = [[user.userName, user.password, user.email, user.firstName, user.lastName, user.workExperience]];
      
        con.query(sql,[values], function(err, result){
            if(err){
                throw new Error ('ERROR: NO TABLE CREATED');
            }
            else{
                callback(result);            
            }
        });
    }

    function isUserExists(userName, callback){
        var sql = "select userName from users where userName='"+userName+"';" 
      
        con.query(sql, function(err, result){
            if(err){
                throw new Error ('ERROR: NO TABLE CREATED');
            }
            else{
                callback(result);            
            }
        });
    }

    function isUserNameMatchPassword(user, callback){
        var sql = "select userName from users where userName='"+user.userName+"'and userPassword='"+user.password+"';" 
        
        con.query(sql, function(err, result){
            if(err){
                throw new Error ('ERROR: NO TABLE CREATED');
            }
            else{
                callback(result);            
            }
        });
    }

    function getCurrentUser(userName, callback){
        var sql = "select * from users where userName='"+userName+"';" 
        
        con.query(sql, function(err, result){
            if(err){
                throw new Error ('ERROR: NO TABLE CREATED');
            }
            else{
                callback(result);            
            }
        });
    }

    function updateCurrentUser(user, currentUserName, callback){
        var sql = "update users set userName='"+user.userName+"',userEmail='"+user.email+"',userFirstName='"+user.firstName+
                  "',userLastName='"+user.lastName+"',userPassword='"+user.password+"',userWorkDescription='"+user.workExperience+
                  "'where userName='"+currentUserName+"';" 
       
        con.query(sql, function(err, result){
            if(err){
                throw new Error ('ERROR: NO TABLE CREATED');
            }
            else{
                callback(result);
            }
        });
    }

    return{
        addUser: addUser,
        isUserExists: isUserExists,
        isUserNameMatchPassword: isUserNameMatchPassword,
        getCurrentUser: getCurrentUser,
        updateCurrentUser: updateCurrentUser
    }
})();