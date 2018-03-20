angular
    .module('myApp')
    .controller('OtherAccountCtrl', otherAccountController);

otherAccountController.$inject = ['$location','UserAccountService', 'OtherAccountService'];

function otherAccountController($location, UserAccountService, OtherAccountService) {
    var vm = this;

    vm.backToSearch = backToSearch;

    vm.picPath1 = '';

    vm.favUsername = '';

    vm.addToFav = addToFav;

    vm.allowReview = false;

    vm.writeReview = writeReview;

    vm.saveReview = saveReview;

    vm.editReview = editReview;

    vm.review;



    var localUserData = localStorage['localOtherUser'];

    if(localUserData !== undefined) {
        vm.user = JSON.parse(localUserData);
        getProfilePic(vm.user.username);
        var userData = localStorage['userInfo'];
        var loggedInUser = JSON.parse(userData);
        checkIfFav(loggedInUser.username, vm.user.username);
    }

    //ALLOW USER TO WRITE A REVIEW
    function writeReview() {
        $("#writeReviewBox").attr('readonly', false);
        $("#writeReviewBox").removeClass("writeReviewBox");
        vm.allowReview = !vm.allowReview;
    }

    // ALLOWS USER TO SAVE REVIEW
    function saveReview() {
        $("#writeReviewBox").attr('readonly', true);
        $("#writeReviewBox").addClass("writeReviewBox");
        console.log(vm.review);

        vm.user = JSON.parse(localUserData);
        var userData = localStorage['userInfo'];
        var loggedInUser = JSON.parse(userData);
        sendReview(loggedInUser.username, vm.user.username, vm.review);
    }

    function sendReview(loggedInUsername, otherUsername, review){
        OtherAccountService.sendReview(loggedInUsername, otherUsername, review)
            .then(
                function(r) {
                    console.log(r);
                },
                function(errResponse){
                    console.error('this review could not be saved');
                });
    }

    function editReview() {
        $("#writeReviewBox").attr('readonly', false);
        $("#writeReviewBox").removeClass("writeReviewBox");
    }

    //CHECKS IF CURRENT ACCOUNT IS TAGGED FAVOURITE
    function checkIfFav(loggedInUsername, otherUsername) {
        //OtherAccountService.checkIfFav()
        OtherAccountService.checkIfFav(loggedInUsername, otherUsername)
            .then(
                function() {
                    console.log("this user is tagged fav");
                    $('.makeFav').toggleClass('redBackground');
                },
                function(errResponse){
                    console.error('this user is not tagged fav');
                });
    }

    //RESPONSIBLE FOR THE RED COLOR ON HEART ICON
    function addToFav() {
        $('.makeFav').toggleClass('redBackground');
        if ($(".makeFav").hasClass("redBackground")){
            sendFav();
        }else {
            deleteFav();
        }
    }

    //DELETES THE FAV USER
    function deleteFav() {
        var userData = localStorage['userInfo'];
        var loggedInUser = JSON.parse(userData);
        var favUser = JSON.parse(localUserData);
        deleteFavUser(loggedInUser.username, favUser.username);
    }

    //TAKES THE RECORD TO BE DELETED TO BACKEND
    function deleteFavUser(lUsername, fUsername){
        OtherAccountService.deleteFav(lUsername, fUsername)
            .then(
                function() {
                    console.log("Fav user deleted from databse");
                },
                function(errResponse){
                    console.error('Error while deleting fav user');
                });
    }

    //GETS DATA TO SEND TO SEND_FAV_USER
    function sendFav() {
        var userData = localStorage['userInfo'];
        var loggedInUser = JSON.parse(userData);
        var favUser = JSON.parse(localUserData);
        sendFavUser(loggedInUser.username, favUser.username);
    }

    //SENDS THE DATA RECEIVED TO BACKEND
    function sendFavUser(lUsername, fUsername){
        OtherAccountService.sendFavUser(lUsername, fUsername)
            .then(
                function() {
                    console.log("Fav user added to database");
                },
                function(errResponse){
                    console.error('Error while getting favUsername');
                });
    }

    //THE GO_BACK BUTTON
    function backToSearch() {
        $location.path('/userAccount/searchResults')
    }

    //GETS THE ACCOUNT PROFILE PICTURE ON LOAD
    function getProfilePic(username){
        UserAccountService.getProfilePic(username)
            .then(
                function(d) {
                    vm.userProfilePic =d;
                    vm.picPath1 = '/user'+d.picPath;
                },
                function(errResponse){
                    console.error('Error while getting profilePic');
                }
            );
    }

}
