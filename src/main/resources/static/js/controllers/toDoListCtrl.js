var toDoList = angular.module('toDoList');

    toDoList.directive('childScope', function() {
        return { scope: true, restrict:'AE' }
    });
    toDoList.controller("toDoListCtrl", ['$scope','$window', '$http', function($scope, $window,$http){
    	
        $scope.menuItems = {home:'Home',
                            createList: 'Create new list',
                            contact: 'Contact'};

        $scope.priorities = {high: "High",
                            medium: "Medium",
                            low: "Low"};

        $scope.categories = {other: "Other",
                            work: "Work",
                            university: "University",
                            home: "Home"};
        
        var initialTasks = [{description:"Finish SI1's Project", detailedDescription: "See how spring works and finish some design work.",
            finished: false, priority: $scope.priorities.medium, category: $scope.categories.university, subtasks: []}, 
           {description:"Clean the dishes", detailedDescription: "I better do it fast before my mom kills me.",
           finished: true, priority: $scope.priorities.high, category: $scope.categories.home, subtasks: []}, 
           {description:"Get tanned.", detailedDescription: "Need to be careful not to get burned.",
           finished:false, priority: $scope.priorities.low, category: $scope.categories.other,subtasks: ["Buy a sunscreen"]}];


        $scope.categoryStatus = {Home: true,
                                Work: true,
                                University: true,
                                Other: true}; 

        $scope.priorityStatus = {High: true,
                                Medium: true,
                                Low: true}; 

        $scope.activeMenu = $scope.menuItems.home;
        
        $scope.progress = 0.00;

        $scope.selectCategory = function(category){
            $scope.categoryStatus[category] = true;
        };

        $scope.deselectCategory = function(category){
            $scope.categoryStatus[category] = false;
        };

        $scope.selectPriority = function(priority){
            $scope.priorityStatus[priority] = true;
        };

        $scope.deselectPriority = function(priority){
            $scope.priorityStatus[priority] = false;
        };

        function clearTaskInput(){
            $scope.input.task.description = "";
            $scope.input.task.detailedDescription = "";
            $scope.input.task.category = "";
            $scope.input.task.otherCategory = "";
            $scope.input.task.priority = "";
            $scope.input.task.subtask = "";
        };
        
        function clearListInput(){
            $scope.input.list.description = "";
        };
        
        function updateCurrentList(){
        	$http({
                method: 'PUT',
                url: 'https://si1-to-do-list.herokuapp.com/toDoList',
                data: $scope.currentList
              }).then(function successCallback(response) {
             });
        }

        function detectCategoryInput(){
            var category;
            if($scope.input.task.otherCategory=="" || !$scope.input.task.otherCategory){
                category = $scope.input.task.category;
            }
            else{
                category = $scope.input.task.otherCategory;
                $scope.categories[$scope.input.task.category] = category;
                $scope.categoryStatus[category] = true;
            }
            return category;
        }
        
        $scope.loadLists = function(){$http({
            method: 'GET',
            url: 'https://si1-to-do-list.herokuapp.com/toDoList'
          }).then(function successCallback(response) {
        	if(response.data.length>0){
        		$scope.lists = response.data;
              	$scope.currentList = $scope.lists[0];
         
        	}
        	else{
        		$scope.lists = [];
        	}
            }, function errorCallback(response) {});
          };
        

        $scope.addNewTask = function(){
        	var description = $scope.input.task.description;
        	var category = detectCategoryInput();	
        	var priority = $scope.input.task.priority;
        	var detailedDescription = $scope.input.task.detailedDescription;
            
            if (description && priority && category){
                if(containsTask(description))
                    $window.alert("A task with this description already exists.");
                else{
                	var newTask = {description: description, detailedDescription: detailedDescription,
                        finished: false, priority: priority, category: category, subtasks: []};
                	$scope.currentList.tasks.push(newTask)
                	updateCurrentList();
                    clearTaskInput();
                }
            }
            else{
                $window.alert("Please write a description, select a priority and a category!");
            }
        };

        $scope.addNewSubtask = function(task){
            $scope.addingSubtask = false;
            task.subtasks.push($scope.input.task.subtask);
            if($scope.input.task.subtask){
            	$http({
                    method: 'PUT',
                    url: 'https://si1-to-do-list.herokuapp.com/tasks',
                    data: task
                  }).then(function successCallback(response) {
                });
                clearTaskInput();  
            }  
        };

        $scope.addNewList = function(){
            var description = $scope.input.list.description;
            var tasks = [];
            if (description){
                var newList = {description: description, tasks: tasks};
                $http({
                    method: 'POST',
                    url: 'https://si1-to-do-list.herokuapp.com/toDoList',
                    data: newList
                  }).then(function successCallback(response) {
                  	$scope.lists.push(response.data);
                  	$scope.currentList = $scope.lists[$scope.lists.length-1];
                });
                clearListInput();
            }
            else{
                $window.alert("Please give the list a name.");
            }
        };

        $scope.setActive = function(menuItem){
            $scope.activeMenu = menuItem;
        }

        function containsTask(description){
            var contains = false;
            $scope.currentList.tasks.forEach(function(task) {
                if (task.description==description){
                    contains = true;
                }
            });
            return contains;
        };

        $scope.removeTask = function (taskToBeRemoved) {
            var counter = 0
            $scope.currentList.tasks.forEach(function(task) {
            	console.log("4");
                if (task.description==taskToBeRemoved.description){
                	console.log("6");
                	$scope.currentList.tasks.splice(counter,1);
                	updateCurrentList();
                    counter+=1
                }
                else
                	counter +=1
            });
        };

        $scope.clearTasks = function(){
        	while($scope.currentList.tasks.length!=0){
        		$scope.removeTask($scope.currentList.tasks[0]);
        	}
        };
        
        function removeListFromDB(listToBeRemoved){
        	$http({
                method: 'DELETE',
                url: 'https://si1-to-do-list.herokuapp.com/toDoList/'+listToBeRemoved.id
              }).then(function successCallback(response) {
            }); 
        }

        $scope.removeList = function (listToBeRemoved) {
            var counter = 0
            $scope.lists.forEach(function(list) {
                if (list.id==listToBeRemoved.id){
                	removeListFromDB(listToBeRemoved);
                	$scope.lists.splice(counter,1);
                }
                else
                	counter +=1
            });
            $scope.currentList = $scope.lists[0];
        };

        $scope.clearLists = function(){
            $scope.lists.forEach(function(list){
            	removeListFromDB(list);
            });
            $scope.lists = [];
        };

        $scope.sortByDescription = function(){
            $scope.currentList.tasks.sort(function(task, taskToBeCompared){
                return task.description.toLowerCase() > taskToBeCompared.description.toLowerCase();
            });
        };

        $scope.sortByPriority = function(){
            $scope.currentList.tasks.sort(function(task, taskToBeCompared){
                var order = {High: 3, Medium:2, Low:1};
                return order[task.priority] < order[taskToBeCompared.priority];
            });
        };

        $scope.$watch('[currentList.tasks, categoryStatus, priorityStatus]',function(){
            var totalTasks = 0;
            var totalTasksCompleted = 0;
            if($scope.currentList!=null){
            	$scope.currentList.tasks.forEach(function (task) {
                    if($scope.categoryStatus[task.category] && $scope.priorityStatus[task.priority]){
                        if(task.finished){
                            totalTasksCompleted+=1;
                        }
                        totalTasks+=1;
                    }
                });
            }
            
            if (totalTasks==0){
                $scope.progress = parseFloat(Math.round(0  * 100) / 100).toFixed(2);
            }else{
                $scope.progress = parseFloat(Math.round(((totalTasksCompleted/totalTasks)*100) * 100) / 100).toFixed(2);
            }
        }, true);
        
        function updateTotalTasksStatus(){
        	var totalCompleted = 0
        	var totalNotCompleted = 0
        	$scope.lists.forEach(function(list){
        		list.tasks.forEach(function(task){
        			if(task.finished){
        				totalCompleted+=1
        			}
        			else
        				totalNotCompleted+=1
        		});
        	});
        	$scope.totalTasksCompleted = totalCompleted;
        	$scope.totalTasksNotCompleted = totalNotCompleted;
        }
        
        $scope.$watch('currentList.tasks',function(){
        	var updatedList = $scope.currentList
        	if(updatedList){
        		updateCurrentList();
        		updateTotalTasksStatus();
        	}
        },true);
        
        $scope.loadLists();
    }]);
