
var usersListing = angular.module('usersListing', []);

usersListing.controller('userListController', function($scope, $http) {
	$scope.usersList = [];
	 
	 var StringUrl= "http://localhost:8080/api/v1/users";
	 
	 $http({
		    method : "GET",
		    url : StringUrl
		  }).then(function mySuccess(response) {
			  $scope.usersList = response.data;
		   
		    }, function myError(response) {
		    	$scope.usersList = response.statusText;
		  });	 
});

usersListing.controller('userUpdate', function($scope, $http) {
	$scope.user;
	$scope.updateUserData = function(){		 
		 $scope.message;
		 var StringUrl= "http://localhost:8080/api/v1/users";
		 $http({
			    method : "put",
			    url : StringUrl,
			    data: $scope.user,
			  }).then(function mySuccess(response) {
				  $scope.message =  response.data;
			    }, function myError(response) {
			    	$scope.message =  response;
			  });
	 }
});
usersListing.controller('saveUserData', function($scope, $http) {
	$scope.user;
	$scope.saveUser = function(){		 		 
		 $scope.message;
		 var StringUrl= "http://localhost:8080/api/v1/users";
		 $http({
			    method : "post",
			    url : StringUrl,
			    data: $scope.user,
			  }).then(function mySuccess(response) {
			      $scope.message = response.data;
			    }, function myError(response) {
			      $scope.message = response;
			  });
	 }
});
usersListing.controller('deleteUserData', function($scope, $http) {
	$scope.id;
	$scope.deleteUser = function(){		 		 
		 $scope.message;
		 var StringUrl= "http://localhost:8080/api/v1/users?id=" + $scope.id;
		 $http({
			    method : "delete",
			    url : StringUrl,
			  }).then(function mySuccess(response) {
			      $scope.message = response.data;
			    }, function myError(response) {
			      $scope.message = response.status;
			  });
	 }
});

