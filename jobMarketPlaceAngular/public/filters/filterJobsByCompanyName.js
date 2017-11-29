myApp.filter('filterJobsByCompanyName', [function(){
    return function(allJobs, companiesNames) {
       let filteredJobs = [];
       let companiesNameArr = [];

        for(let job of allJobs){
            companiesNameArr = companiesNames.filter(companyName => companyName.label == job.companyName);

            if(companiesNameArr.length > 0){
                filteredJobs.push(job);
            }
            
            companiesNameArr = [];
        }
        
       return filteredJobs;
   }     
}]);
