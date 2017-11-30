
let myApp = angular.module('jobMarketPlaceApp', ['ngRoute','ngLodash', 'usersService', 'jobsService', 'skillsService', 'angularjs-dropdown-multiselect']);

myApp.config(function($routeProvider, $locationProvider) {   
  $locationProvider.html5Mode(false);
  $locationProvider.hashPrefix('');
  $routeProvider.
  when('/', {
     templateUrl: './view/home.html',
  }).
  when('/login', {
     templateUrl: './view/login.html',
     controller : 'loginController'
  }).
   when('/register', {
     templateUrl: './view/register.html',
     controller : 'registerController'
  }).
  when('/findJobs', {
     templateUrl: './view/findJobs.html',
     controller : 'findJobsController'
  }).
  when('/userProfile', {
    templateUrl: './view/userProfile.html',
    controller : 'userProfileController'
 }).
   otherwise({
     redirectTo: '/'
  });
});

