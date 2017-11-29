myApp.filter('removeDuplicateJobsCompanyName', [function(){
    return function(jobsCompaniesNames) {
        let uniqueCompaniesNames = [];
        let filteredJobsCompaniesNames = [];

        for(i = 0; i< jobsCompaniesNames.length; i++){    
            if(uniqueCompaniesNames.indexOf(jobsCompaniesNames[i].label) === -1){
                uniqueCompaniesNames.push(jobsCompaniesNames[i].label);
                filteredJobsCompaniesNames.push({id: jobsCompaniesNames[i].id, label: jobsCompaniesNames[i].label});        
            }        
        }
        return filteredJobsCompaniesNames;
   }     
}]);
