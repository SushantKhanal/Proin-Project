angular
    .module('myApp')
    .controller('OtherAccountCtrl', otherAccountController);

otherAccountController.$inject = ['$location', 'ModalFactory', 'AddTagsService', 'UserAccountService', 'OtherAccountService', 'FavouritesService', 'AddAcademicsService', 'AddExperienceService'];

function otherAccountController($location, ModalFactory, AddTagsService, UserAccountService, OtherAccountService, FavouritesService, AddAcademicsService, AddExperienceService) {
    var vm = this;

    vm.backToSearch = backToSearch;

    vm.picPath1 = '';

    vm.favUsername = '';

    vm.addToFav = addToFav;

    vm.allowReview = false;

    vm.writeReview = writeReview;

    vm.saveReview = saveReview;

    vm.getReviews = getReviews;

    vm.takeToAccount = takeToAccount;

    vm.tags = '';
    vm.showReviews = false;
    vm.addExperience = addExperience;
    vm.addAcademics = addAcademics;

    vm.userAndReviews = '';
    vm.seeMoreReviews = seeMoreReviews;
    vm.seeLess = seeLess;
    vm.showSeeLess = false;
    vm.moreReviews = true;
    vm.page = 0;
    vm.totalReviewsFetched = vm.page * 2;
    vm.review;

    getTags();

    var localUserData = localStorage['localOtherUser'];

    if(localUserData !== undefined) {
        vm.user = JSON.parse(localUserData);
        getProfilePic(vm.user.username);
        var userData = localStorage['userInfo'];
        var loggedInUser = JSON.parse(userData);
        checkIfFav(loggedInUser.username, vm.user.username);
    }

    getAllAcademics();
    getAllExperience();
    getReviews();
    checkForUploadedDocs();

    function checkForUploadedDocs() {
        OtherAccountService.checkForUploadedDocs(vm.user.username)
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

    function getAllAcademics() {
        vm.user = JSON.parse(localUserData);
        AddAcademicsService.getAllAcademics(vm.user.username)
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
        AddExperienceService.getAllExperience(vm.user.username)
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

    function addExperience() {
        ModalFactory.open('Pages/OtherAccount/templates/experience.html', 'ShowExperienceController', 'md', '$ctrl')
    }
    function addAcademics() {
        ModalFactory.open('Pages/OtherAccount/templates/academics.html', 'ShowAcademicsController', 'md', '$ctrl')
    }

    function getTags() {
        var localUserData = localStorage['localOtherUser'];
        var user = JSON.parse(localUserData);
        AddTagsService.receiveTags(user.username)
            .then(
                function(r) {
                    vm.tags = r.tags;
                },
                function(errResponse){
                    console.error('this review could not be saved');
                });
    }

    function takeToAccount(username){
        FavouritesService.getUserProfile(username)
            .then(
                function(d) {
                    vm.user = d;
                    localStorage['localOtherUser'] = JSON.stringify(vm.user);

                    $location.path('/searchResults/otherUser');
                },
                function(errResponse){
                    console.error('Error while fetching fav user names');
                }
            );
    }

    // //GETS AVAILABLE REVIEWS
    // function getReviews() {
    //     vm.allowReview = false;
    //     vm.showReviews = true;
    //     vm.user = JSON.parse(localUserData);
    //     if (vm.userAndReviews !== ''){
    //         vm.showReviews = false;
    //         vm.userAndReviews = '';
    //         return;
    //     }
    //     OtherAccountService.getReviews(vm.user.username)
    //         .then(
    //             function(r) {
    //                 console.log(r);
    //                 vm.userAndReviews = r.reviewInfoList;
    //             },
    //             function(errResponse){
    //                 console.error('Error while getting reviews');
    //             });
    // }

    //GETS AVAILABLE REVIEWS

    function getReviews() {
        vm.moreReviews = true;
        vm.reviewsText = "Hide Reviews";
        vm.page++;

        OtherAccountService.getReviews(vm.page, vm.user.username)
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
        OtherAccountService.getReviews(vm.page, vm.user.username)
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

        vm.showReviews = false;
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
        var userData = localStorage['userInfo'];
        var loggedInUser = JSON.parse(userData);
        sendReview(loggedInUser.username, vm.user.username, vm.review, vm.numOfStars);
        vm.allowReview = false;
    }

    function sendReview(loggedInUsername, otherUsername, review, rating){
        OtherAccountService.sendReview(loggedInUsername, otherUsername, review, rating)
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
