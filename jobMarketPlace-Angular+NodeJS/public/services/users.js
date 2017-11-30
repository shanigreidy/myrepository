let usersService = angular.module("usersService",[]);

usersService.factory("users", ['$http', function($http){
    return usersObj = {
        currentUserName: '',
        addUser: function(user, callBack){
            $http({
                method: 'POST',
                url: 'users/addUser',
                data: user
            }).then(function (response) {       
                callBack(true);
            }, function (response) {
                callBack(false);    
            });
        },
        isUserExists: function(userName, callBack){
            $http({
                method: 'POST',
                url: 'users/isUserExists',
                data: {userName}
            }).then(function (response) {    
                if(response.data.length > 0){
                    callBack(true);               
                }
                else{
                    callBack(false);                                   
                }
            }, function (response) {
                callBack(false);    
            });         
        },
        isUserNameMatchPassword: function(user, callBack){
            $http({
                method: 'POST',
                url: 'users/isUserNameMatchPassword',
                data: user
            }).then(function (response) {    
                if(response.data.length > 0){
                    callBack(true);               
                }
                else{
                    callBack(false);                                   
                }
            }, function (response) {
                callBack(false);    
            });         
        },
        logInUser: function(userName){
            this.currentUserName = userName;
            $http({
                method: 'POST',
                url: 'users/logInUser',
                data: {userName}
            }).then(function (response) {       
                }, function (response) {
                     console.log('userNotLogIn');             
            });
        },
        getCurrentUser: function(userName, callBack){         
            $http({
                method: 'POST',
                url: 'users/getCurrentUser',
                data: {userName}
            }).then(function (response) {    
                callBack(response.data[0]);                                                   
            }, function (response) {
                callBack(response);    
            });         
        },
        updateCurrentUser: function(user, currentUserName, callBack){         
            $http({
                method: 'POST',
                url: 'users/updateCurrentUser',
                data: {user, currentUserName}
            }).then(function (response) {
                 callBack(true);
            }, function (response) { 
                 callBack(false);
            });         
        },
        logOutUser: function(){
             this.currentUserName = ''; 
             $http({
                method: 'POST',
                url: 'users/logOutUser',
            }).then(function (response) {       
                }, function (response) {
                    console.log('userNotLogOut');             
            });
        },
        userAddJob: function(jobId){
            $http({
               method: 'POST',
               url: 'users/addJob',
               data: {currentUserName: this.currentUserName, jobId}
           }).then(function (response) {       
               }, function (response) {
                   console.log('userNotLogOut');             
           });
       },        
    }
}]); 