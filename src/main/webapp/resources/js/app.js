'use strict';

var webApp = angular.module('webApp',['ui.router']); 

webApp
.config(['$stateProvider','$urlRouterProvider','$locationProvider'
		,function($stateProvider,$urlRouterProvider,$locationProvider){

			$urlRouterProvider.otherwise('/index');

			$stateProvider
				.state('index',{
					url:'/index',
					views:{
						'content':{templateUrl:'/ctp/user/userInfo'}
					}
				});
				
			$locationProvider.html5Mode(true).hashPrefix('!');
}])
.controller('indexController',['$scope'
		,function($scope){
			$scope.hello = "hello";
}])