angular.module("toDoList",["ui.router"])

    .config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
        $urlRouterProvider.otherwise("/");
        $stateProvider
            .state("home",{
                url: "/",
                templateUrl: "home.html",
                controller: "toDoListCtrl"
            })
            .state("contact",{
                url: "/contact",
                templateUrl: "contact.html"
            })
            .state("createList",{
            	url: "/createList",
            	templateUrl: "createList.html",
            	controller: "toDoListCtrl"
            })
    }]);
