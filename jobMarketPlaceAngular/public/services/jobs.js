let jobsService = angular.module("jobsService",[]);

jobsService.factory("jobs", ['$http','$filter',function($http, $filter){
    return jobsObj = { 
        allJobs: [],
        initAllJobs: function(){
            this.getAllJobsFromDB(function(res){ this.allJobs = res.data }.bind(this));
        },
        addSkillsToJobs: function(){
            let skills = [];
            for(let i in this.allJobs){
                setTimeout(function(i) {
                  this.getJobSkills(this.allJobs[i].jobId, function(res){ this.allJobs[i].jobSkills = res.data }.bind(this));     
                }.bind(this, i), 500);         
            }
        },
        getAllJobsFromDB : function(callBack){
            $http({
                method: 'GET',
                url: '/jobs'
            }).then(function (response) {       
                callBack(response); //handle Asynchrony
            }, function (response) {
                console.log('error get jobs');        
            });
        },
        getJobSkills: function(jobId, callBack){
            $http({
                method: 'POST',
                url: 'jobs/jobSkills',
                data: {jobId}
            }).then(function (response) {       
                callBack(response); //handle Asynchrony
            }, function (response) {
                console.log('error get jobs');        
            });
        },
    }
}]); 