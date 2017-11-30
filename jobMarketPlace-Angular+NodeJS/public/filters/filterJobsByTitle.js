myApp.filter('filterJobsByTitle', [function(){
    return function( jobsData, val) {
       let filteredJobsData = [];
       if(jobsData.length > 0 && val){
           for(let job of jobsData){
               if(job.name.toLowerCase().includes(val.toLowerCase())){
                   filteredJobsData.push(job);
               } 
           }
       }
       else{
           return jobsData;
       }
       
       return filteredJobsData;
   }     
}]);
