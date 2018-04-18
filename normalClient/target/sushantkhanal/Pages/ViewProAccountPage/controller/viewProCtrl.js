angular
    .module('myApp')
    .controller('ProAccountCtrl', proAccountController);

proAccountController.$inject = ['$location', 'ModalFactory', 'ProAccountService'];

function proAccountController($location, ModalFactory, ProAccountService) {

    var vm = this;

    vm.backToSearch = backToSearch;

    vm.picPath1 = '';

    vm.favUsername = '';

    vm.addToFav = addToFav;

    vm.allowReview = false;

    vm.writeReview = writeReview;

    vm.saveReview = saveReview;

    vm.getReviews = getReviews;

    vm.unFollow = unFollow;

    vm.ifFollow = "Send Follow Request";

    vm.beforeFollow = true;

    vm.userAndReviews = '';
    vm.tags = '';
    vm.sendFollowRequest = sendFollowRequest;
    vm.seeMoreReviews = seeMoreReviews;
    vm.seeLess = seeLess;
    vm.showSeeLess = false;
    vm.moreReviews = true;
    vm.page = 0;
    vm.totalReviewsFetched = vm.page * 2;

    //follow request sent status = 0
    //follow request accepted status = 1
    //unfollowed status = 2
    //ignored request status = 3

    vm.docNames = [];



    vm.review;

    var localUserData = localStorage['localProUser'];
    var userData = localStorage['NormalUserInfo'];
    var loggedInUser = JSON.parse(userData);

    if(localUserData !== undefined) {
        vm.user = JSON.parse(localUserData);
        getProfilePic(vm.user.username);
        checkIfFav(loggedInUser.username, vm.user.username);
    }

    getTags();
    getAllAcademics();
    getAllExperience();
    checkIfFollowed();
    checkForUploadedDocs();
    getReviews();

    function checkForUploadedDocs() {
        ProAccountService.checkForUploadedDocs(vm.user.username)
            .then(function (r) {
                vm.docNames=[];
                vm.docPaths=[];
                for(element of r) {
                    var docName = element.docPath;
                    vm.docPaths.push("http://localhost:8080"+'/proUser'+docName);
                    docName = docName.split('proinProjectdoc/')[1];
                    var obj = {
                        id: element.id,
                        docName: docName,
                    };
                    vm.docNames.push(obj);
                }
                console.log("docNames",vm.docNames);
                console.log("docPaths",vm.docPaths);
            }, function (error) {
                console.log(error);
            })
    }

    function checkIfFollowed() {
        ProAccountService.checkIfFollowed(loggedInUser.username, vm.user.username)
            .then(
                function(response) {
                    var r = response.sendThis;
                    if(r == "pending"){
                        vm.ifFollow = "Follow Request Sent";
                        vm.isRequestSent = true;
                    } else if(r == "noRequestFound"){
                        vm.ifFollow = "Send Follow Request";
                        vm.isRequestSent = false;
                        vm.beforeFollow = true;
                    } else if(r == "accepted") {
                        vm.ifFollow = "Following";
                        vm.isRequestSent = true;
                        vm.beforeFollow = false;
                    }
                },
                function(errResponse){

                });
    }

    
    function sendFollowRequest() {
        var modalInstance = ModalFactory.open('Pages/ViewProAccountPage/templates/followRequest.jsp', 'FollowRequestController', 'md', '$ctrl');
        modalInstance.result.then(
            function(response){
                checkIfFollowed();
            },function(errResponse){

            }
        )
    }

    function unFollow() {
        var r = confirm("Are you sure you want to unfollow " + vm.user.username + "?");
        if (r==true) {
            ProAccountService.unFollow(loggedInUser.username, vm.user.username)
                .then(function(){
                    console.log("Unfollowed ", vm.user.username);
                    checkIfFollowed();
                },function(){
                    console.log("error");
                })
        }
    }

    function getAllAcademics() {
        vm.user = JSON.parse(localUserData);
        ProAccountService.getAllAcademics(vm.user.username)
            .then(
                function(response) {
                    for(r of response) {
                        r.startDate = new Date(r.startDate);
                        r.endDate = new Date(r.endDate);
                    }
                    vm.academics = response;
                },
                function(errResponse){
                    alert('Academics could not be retrieved');
                });
    }

    function getAllExperience() {
        ProAccountService.getAllExperience(vm.user.username)
            .then(
                function(response) {
                    for(r of response) {
                        r.startDate = new Date(r.startDate);
                        r.endDate = new Date(r.endDate);
                    }
                    vm.experience = response;
                },
                function(errResponse){
                    alert('Experience data could not be retrieved');
                });
    }

    function getTags() {
        var localUserData = localStorage['localProUser'];
        var user = JSON.parse(localUserData);
        ProAccountService.receiveTags(user.username)
            .then(
                function(r) {
                    vm.tags = r.tags;
                },
                function(errResponse){
                    console.error('this review could not be saved');
                });
    }

    //GETS AVAILABLE REVIEWS

    function getReviews() {
            vm.moreReviews = true;
            vm.reviewsText = "Hide Reviews";
            vm.page++;
            vm.user = JSON.parse(localUserData);

            ProAccountService.getReviews(vm.page, vm.user.username)
                .then(
                    function(r) {
                        vm.reviews = r.reviewInfoList;
                        vm.userAndReviews = vm.reviews;
                        vm.totalLength = r.totalSize;
                    },
                    function(errResponse){
                        console.error('Error while getting reviews');
                    });

    }

    //SEE MORE REVIEWS
    function seeMoreReviews() {
        if(vm.userAndReviews.length >= vm.totalLength){
            vm.moreReviews = false;
        }
        vm.showSeeLess=true;
        vm.page++;
        ProAccountService.getReviews(vm.page, vm.user.username)
            .then(
                function(r) {
                    vm.userAndReviews = vm.userAndReviews.concat(r.reviewInfoList);
                },
                function(errResponse){
                    console.error('Error while getting reviews');
                });
    }

    //SEE LESS
    function seeLess() {
        vm.page = 1;
        vm.userAndReviews = vm.reviews;
        vm.showSeeLess = false;
        vm.moreReviews = true;
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
                    $(this).removeClass('fa-star-o').addClass('fa-star');
                } else {
                    $(this).removeClass('fa-star').addClass('fa-star-o');
                }
            });
        };

        $star_rating.on('click', function() {
            var ratingValue = $(this).data('rating');
            $star_rating.siblings('input.rating-value').val(ratingValue);
            SetRatingStar();
            vm.numOfStars = $('.fa-star').length;
        });


        //#######################################//

        // vm.showReviews = false;
        $("#writeReviewBox").attr('readonly', false);
        $("#writeReviewBox").removeClass("writeReviewBox");
        vm.allowReview = !vm.allowReview;
    }

    // ALLOWS USER TO SAVE REVIEW
    function saveReview() {
        console.log(vm.numOfStars);
        //$("#writeReviewBox").attr('readonly', true);
        $("#writeReviewBox").addClass("writeReviewBox");
        console.log(vm.review);

        vm.user = JSON.parse(localUserData);
        var userData = localStorage['NormalUserInfo'];
        var loggedInUser = JSON.parse(userData);
        sendReview(loggedInUser.username, vm.user.username, vm.review, vm.numOfStars);
        vm.allowReview = false;
    }

    function sendReview(loggedInUsername, otherUsername, review, rating){
        ProAccountService.sendReview(loggedInUsername, otherUsername, review, rating)
            .then(
                function() {
                    vm.review = '';
                    vm.page=0;
                    getReviews();
                },
                function(errResponse){
                    console.error('this review could not be saved');
                });
    }


    //CHECKS IF CURRENT ACCOUNT IS TAGGED FAVOURITE
    function checkIfFav(loggedInUsername, otherUsername) {
        ProAccountService.checkIfFav(loggedInUsername, otherUsername)
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
        var userData = localStorage['NormalUserInfo'];
        var loggedInUser = JSON.parse(userData);
        var favUser = JSON.parse(localUserData);
        deleteFavUser(loggedInUser.username, favUser.username);
    }

    //TAKES THE RECORD TO BE DELETED TO BACKEND
    function deleteFavUser(lUsername, fUsername){
        ProAccountService.deleteFav(lUsername, fUsername)
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
        var userData = localStorage['NormalUserInfo'];
        var loggedInUser = JSON.parse(userData);
        var localUserData = localStorage['localProUser'];
        var favUser = JSON.parse(localUserData);
        sendFavPro(loggedInUser.username, favUser.username);
    }

    //SENDS THE DATA RECEIVED TO BACKEND
    function sendFavPro(lUsername, fUsername){
        ProAccountService.sendFav(lUsername, fUsername)
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
        $location.path('/NormalAccount/searchProClients')
    }

    //GETS THE ACCOUNT PROFILE PICTURE ON LOAD
    function getProfilePic(username){
        ProAccountService.getProfilePic(username)
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
