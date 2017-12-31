myApp.filter('filterJobsBySkills', [function(){
    return function(allJobs, companiesNames, skills) {
       let filteredJob = [];
       let skillsArr = [];

        for(let job of allJobs){
            for(jobSkill of job.jobSkills){
                skillsArr = skills.filter((skill => skill.label == jobSkill.skillName));
    
                if(skillsArr.length){
                    filteredJob.push(job);
                    continue;
                }
            }
            
            skillsArr = [];            
        }
        
       return filteredJob;
   }     
}]);
