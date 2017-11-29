myApp.filter("trustUrl", ['$sce', function ($sce) {
    return function (url) {
        return $sce.trustAsResourceUrl(url);
    };
}]);