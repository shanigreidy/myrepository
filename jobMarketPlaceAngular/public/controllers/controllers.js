myApp.controller('appController',['$scope', 'users', 'jobs', 'skills','$timeout', function($scope, users, jobs, skills, $timeout){
    $scope.initAllData= function(){
        jobsObj.initAllJobs();
        skillsObj.initAllSkills();        
        $timeout(function() {
            jobsObj.addSkillsToJobs();                    
        }, 500);
    }
}]);

myApp.controller('homeController',['$scope', '$rootScope', 'users', function($scope, $rootScope, users){
    $scope.showUserMenu = true;
    $scope.userMessage = '';

    $scope.$on('userLogIn', function (evt, userName) {
        $scope.userMessage = 'Welcome '+ userName;
        $scope.showUserMenu = false;
    });

    $scope.$on('userLogOut', function (evt, userName) {
        $scope.showUserMenu = true;
        $scope.userMessage = '';        
        $scope.userName = userName;
    });

    $scope.onLogOutUser = function(){
        usersObj.logOutUser();
        $rootScope.$broadcast("userLogOut", '');  
    }
}]);

myApp.controller('loginController',['$scope', 'users', '$rootScope', '$location', function($scope, users, $rootScope, $location){  
    $scope.user= {
        userName: '',
        password: ''
    };

    $scope.loginUser = function(user){
        usersObj.isUserExists($scope.user.userName, function(isUserExists){
            if(isUserExists){
                usersObj.isUserNameMatchPassword($scope.user, function(isUserNameMatchPassword){
                    if(isUserNameMatchPassword){
                        usersObj.logInUser($scope.user.userName);
                        $rootScope.$broadcast("userLogIn", $scope.user.userName);
                        $location.path('/');   
                    }
                    else{
                        //msg: userName not match password
                         console.log('userName not match password');                
                    }
                });
            }
            else{
                //msg: user not exists, should register
                 console.log('user not exists, should register');                
            }
        });
    }
}]);

myApp.controller('registerController',['$scope', 'users', '$rootScope', '$location', function($scope, users, $rootScope, $location){
    $scope.user= { 
        personalDetails: {
            firstName:'',
            lastName:'',
            userName: '',
            email:'',
            password: '',
            confirmPassword:'',
            workExperience: '',
        },
        location: {
            country: '',
            city: '',
            address: ''
        }
    };

    $scope.registerUser = function(user){
        usersObj.isUserExists($scope.user.personalDetails.userName, function(isUserExists){
            if(isUserExists){
                //msg: user Name Exists  
                console.log('user Name Exists'); 
            }
            else{
               usersObj.addUser($scope.user, function(isUserAdded){ 
                   if(isUserAdded){
                        usersObj.logInUser($scope.user.personalDetails.userName);
                        $rootScope.$broadcast("userLogIn", $scope.user.personalDetails.userName);
                        $location.path('/');   
                   }
                   else{
                       //msg: user not added successfuly
                        console.log('user Not Added');
                   }
               });               
        }});   
    }
}]);

myApp.controller('findJobsController',['$scope','$http', '$filter','jobs', 'skills', function($scope, $http, $filter, jobs, skills){
    $scope.initSearchData = function(){
        $scope.title = '';
        $scope.allCompanies = []; //jobs companies name
        $scope.allSkills = [];
        $scope.initJobs = jobsObj.allJobs;
        $scope.jobs = jobsObj.allJobs;
        $scope.selectedCompanies = []; 
        $scope.selectedTitles = []; 
        $scope.selectedSkills = []; 
        $scope.showTextSearch = true;
        $scope.showOptionsSearch = false;
 
        for(let job of jobsObj.allJobs){
            $scope.allCompanies.push({id: job.jobId, label: job.companyName});
        }

        $scope.allCompanies = $filter('removeDuplicateJobsCompanyName')($scope.allCompanies);

        for(let skill of skillsObj.allSkills){
            $scope.allSkills.push({id: skill.skillId, label: skill.skillName});
        }
    }

    $scope.$watch('title', function(newValue, oldValue) {
        $scope.jobs = $filter('filterJobsByTitle')($scope.initJobs, newValue);
    });

    $scope.onSearchJobs = function(){
        if($scope.selectedCompanies.length && $scope.selectedSkills.length){
            $scope.jobs = $filter('filterJobsBySkillsAndCompanyName')($scope.initJobs, $scope.selectedCompanies, $scope.selectedSkills);    
        }
        else if($scope.selectedCompanies.length){
            $scope.jobs = $filter('filterJobsByCompanyName')($scope.initJobs, $scope.selectedCompanies);    
            
        }
        else if($scope.selectedSkills.length){
            $scope.jobs = $filter('filterJobsBySkills')($scope.initJobs, $scope.selectedSkills);                
        }
        else{
            $scope.jobs = $scope.initJobs;
        }
    }
}]);

myApp.controller('videoController',['$scope', function($scope){
    $scope.videos = [{
            url: 'https://www.youtube.com/embed/jfNtvjD3MqM', 
            title: 'Posting Jobs In Facebook Marketplace', 
            description: 'Posting jobs targeting candidates in their local areas The lesson is practical and detailed. Find those important affiliates for your team.'
        },
        {
            url: 'https://www.youtube.com/embed/fjs2gPa5sD0', 
            title: 'IQ and The Job Market', 
            description: 'Psychology Professor Dr. Jordan B.Peterson explains why IQ is a good predictor of success in the job market'
        },
        {
            url: 'https://www.youtube.com/embed/1Aoqut4SSC4', 
            title: 'A System for Job Application', 
            description: 'Online professional social networks such as LinkedIn serve as a marketplace,wherein job seekers can find right career opportunities and job providers can reach out to potential candidates.'
        }     
    ]
}]);

myApp.controller('userProfileController',['$scope', 'users', '$location', '$rootScope', function($scope ,users, $location, $rootScope){
    $scope.initCurrentUser = function(){
        usersObj.getCurrentUser(usersObj.currentUserName, function(currentUser){
            $scope.user= { 
                personalDetails: {
                    firstName: currentUser.userFirstName,
                    lastName: currentUser.userLastName,
                    userName: currentUser.userName,
                    email: currentUser.userEmail,
                    password: currentUser.userPassword,
                    confirmPassword: currentUser.userPassword,
                    workExperience: currentUser.userWorkDescription,
                },
                location: {
                    country: '',
                    city: '',
                    address: ''
                }
            };
        });
    }

    $scope.updateUser = function(user){
        if($scope.user.personalDetails.userName !== usersObj.currentUserName){
            usersObj.isUserExists($scope.user.personalDetails.userName, function(isUserExists){
                if(isUserExists){
                    //msg: user Name Exists 
                    console.log('user Name Exists'); 
                }
                else{
                    updateCurrentUser();
            }});   
        }
        else{
            updateCurrentUser();
        }   
    }

    var updateCurrentUser = function(){
        usersObj.updateCurrentUser($scope.user, usersObj.currentUserName, function(isUserUpdated){ 
            if(isUserUpdated){
                usersObj.currentUserName = $scope.user.personalDetails.userName;
                $rootScope.$broadcast("userLogIn",$scope.user.personalDetails.userName);
                $location.path('/');   
            }
            else{
                //msg: user Not Updated
                console.log('user Not Updated');
            }
        });               
    }
}]);