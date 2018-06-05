﻿export default class HomeController {
    constructor($scope, $http, logger) {
        this.$http = $http;
        this.messages = [];
        this.logger = logger;
        this.activate();
    }


    activate() {
        var self = this;
        self.$http.get("/rss/list").then(function(response) {
            self.allRss = response.data;
        });
    }
}

HomeController.$inject = ['$scope', '$http', 'logger'];
