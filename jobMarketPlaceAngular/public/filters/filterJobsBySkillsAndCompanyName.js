myApp.filter('filterJobsBySkillsAndCompanyName', [function(){
    return function(allJobs, companiesNames, skills) {
       let filteredJobsBySkills = [];
       let companiesNameArr = [];
       let skillsArr = [];
       let isJobInArray = false;

        for(let job of allJobs){
            companiesNameArr = companiesNames.filter(companyName => companyName.label == job.companyName);

            if(companiesNameArr.length){
                filteredJobsBySkills.push(job);
                isJobInArray = true;
            }

            if(!isJobInArray){
                for(jobSkill of job.jobSkills){
                    skillsArr = skills.filter((skill => skill.label == jobSkill.skillName));
     
                    if(skillsArr.length){
                         filteredJobsBySkills.push(job);
                    }
                }
            }
            
            companiesNameArr = [];
            skillsArr = [];
            isJobInArray = false;
        }
        
       return filteredJobsBySkills;
   }     
}]);
