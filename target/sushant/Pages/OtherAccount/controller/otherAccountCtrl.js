angular
    .module('myApp')
    .controller('OtherAccountCtrl', otherAccountController);

otherAccountController.$inject = ['$location','UserAccountService', 'OtherAccountService', 'FavouritesService'];

function otherAccountController($location, UserAccountService, OtherAccountService, FavouritesService) {
    var vm = this;

    vm.backToSearch = backToSearch;

    vm.picPath1 = '';

    vm.favUsername = '';

    vm.addToFav = addToFav;

    vm.allowReview = false;

    vm.writeReview = writeReview;

    vm.saveReview = saveReview;

    vm.editReview = editReview;

    vm.getReviews = getReviews;

    vm.takeToAccount = takeToAccount;

    vm.userAndReviews = '';
    vm.showReviews = false;

    vm.review;


    var localUserData = localStorage['localOtherUser'];

    if(localUserData !== undefined) {
        vm.user = JSON.parse(localUserData);
        getProfilePic(vm.user.username);
        var userData = localStorage['userInfo'];
        var loggedInUser = JSON.parse(userData);
        checkIfFav(loggedInUser.username, vm.user.username);
    }

    function takeToAccount(username){
        //console.log(username);
        FavouritesService.getUserProfile(username)
            .then(
                function(d) {
                    vm.user = d;
                    localStorage['localOtherUser'] = JSON.stringify(vm.user);
                    // var url = 'http://localhost:8080/#!/searchResults/otherAccount';
                    // window.open(url, '_blank');
                    // //win.focus();
                    $location.path('/searchResults/otherUser');
                },
                function(errResponse){
                    console.error('Error while fetching fav user names');
                }
            );
    }

    //GETS AVAILABLE REVIEWS
    function getReviews() {
        vm.allowReview = false;
        vm.showReviews = true;
        vm.user = JSON.parse(localUserData);
        if (vm.userAndReviews !== ''){
            vm.showReviews = false;
            vm.userAndReviews = '';
            //$scope.$apply();
            return;
        }
        OtherAccountService.getReviews(vm.user.username)
            .then(
                function(r) {
                    console.log(r);
                    vm.userAndReviews = r;
                },
                function(errResponse){
                    console.error('Error while getting reviews');
                });
    }

    //ALLOW USER TO WRITE A REVIEW
    function writeReview() {

        //#######################################//
        //CODE FOR RATING
        var $star_rating = $('.star-rating .fa');

        var SetRatingStar = function() {
            var ratingValue = parseInt($star_rating.siblings('input.rating-value').val());
             $star_rating.each(function() {
                if (ratingValue >= parseInt($(this).data('rating'))) {
                    //var x = parseInt($(this).data('rating'));
                    $(this).removeClass('fa-star-o').addClass('fa-star');
                } else {
                    //var x = parseInt($(this).data('rating'));
                    $(this).removeClass('fa-star').addClass('fa-star-o');
                }
            });
        };

        $star_rating.on('click', function() {
            var ratingValue = $(this).data('rating');
            $star_rating.siblings('input.rating-value').val(ratingValue);
            SetRatingStar();
            vm.numOfStars = $('.fa-star').length;
            console.log(vm.numOfStars);
        });


        //#######################################//

        vm.showReviews = false;
        $("#writeReviewBox").attr('readonly', false);
        $("#writeReviewBox").removeClass("writeReviewBox");
        vm.allowReview = !vm.allowReview;
    }

    // ALLOWS USER TO SAVE REVIEW
    function saveReview() {
        console.log(vm.numOfStars);
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
