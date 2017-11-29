let skillsService = angular.module("skillsService",[]);

skillsService.factory("skills", ['$http',function($http){
    return skillsObj = { 
        allSkills: [],
        initAllSkills: function(){
            this.getAllSkillsFromDB(function(res){ this.allSkills = res.data }.bind(this));
        },
        getAllSkillsFromDB : function(callBack){
            $http({
                method: 'GET',
                url: '/skills'
            }).then(function (response) {       
                callBack(response); //handle Asynchrony
            }, function (response) {
                console.log('error get jobs');        
            });
        }, 
    }
}]); 