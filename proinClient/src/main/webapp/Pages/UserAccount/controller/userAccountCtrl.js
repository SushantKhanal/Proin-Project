//THE FOLLOWING CONTROLLER CONTROLS THE USER ACCOUNT PAGE
angular
    .module('myApp')
    .controller('UserAccountCtrl', userAccountController);

userAccountController.$inject = ['ClientSignInService', 'UserAccountService', 'ModalFactory', '$location', 'AddAcademicsService', 'AddExperienceService', 'OtherAccountService'];

function userAccountController(ClientSignInService, UserAccountService, ModalFactory, $location, AddAcademicsService, AddExperienceService, OtherAccountService) {
    var vm = this;
    vm.user = ClientSignInService.getResponse();
    vm.picPath1='';
    vm.editProfile = editProfile;
    vm.editContent = false;
    vm.updateProfile = updateProfile;
    vm.users = [];
    vm.logOut = logOut;
    vm.searchResults =  searchResults;
    vm.changePicModal = changePicModal;
    vm.showFavourites = showFavourites;
    vm.addTags = addTags;
    vm.addExperience = addExperience;
    vm.editAcademics = editAcademics;
    vm.editExperience = editExperience;
    vm.addAcademics = addAcademics;
    vm.showReviews = showReviews;
    vm.checkFollowRequests = checkFollowRequests;
    vm.takeToSocial = takeToSocial;
    vm.uploadDocuments = uploadDocuments;
    vm.deleteDocument = deleteDocument;
    vm.allowReviews = false;
    vm.reviewsText = 'Show Reviews';
    vm.academics = '';
    vm.experience = '';
    vm.docNames = [];

    vm.userAndReviews = '';
    vm.seeMoreReviews = seeMoreReviews;
    vm.seeLess = seeLess;
    vm.showSeeLess = false;
    vm.moreReviews = true;
    vm.page = 0;
    vm.totalReviewsFetched = vm.page * 2;
    var userData = localStorage['userInfo'];

    if(userData !== undefined) {
        vm.user = JSON.parse(userData);
    }

    getProfilePic(vm.user.username);

    getAllAcademics();

    getAllExperience();

    checkForUploadedDocs();

    showReviews();

    function deleteDocument(id) {
        console.log(id);
        UserAccountService.deleteDocument(id)
            .then(function(){
                checkForUploadedDocs();
            },function(error){
                alert("document not deleted");
            })
    }

    function checkForUploadedDocs() {
        userData = JSON.parse(localStorage['userInfo']);
        username = userData.username;
        UserAccountService.checkForUploadedDocs(username)
            .then(function (r) {
                vm.docNames=[];
                for(element of r) {
                    var docName = element.docPath;
                    docName = docName.split('proinProjectdoc/')[1];
                    var obj = {
                        id: element.id,
                        docName: docName,
                    };
                    vm.docNames.push(obj);
                }
            }, function (error) {
                console.log(error);
            })
    }

    function uploadDocuments() {
        var modalInstance = ModalFactory.open('Pages/UserAccount/templates/uploadDocuments.html', 'UploadDocController', 'md', '$ctrl');
        modalInstance.result.then(
            function(response){
                checkForUploadedDocs();
            },function(errResponse){

            }
        )
    }

    function takeToSocial() {
        $location.path('/userAccount/followers');
        console.log("take to page where followers and following is shown");
    }

    function checkFollowRequests() {
        ModalFactory.open('Pages/UserAccount/templates/showFollowRequests.html', 'ShowFollowRequestsController', 'sm', '$ctrl');
    }

    //GETS AVAILABLE REVIEWS

    function showReviews() {
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

    function getAllAcademics() {
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

    function editAcademics(id) {
        AddAcademicsService.getAcademicFromId(id)
            .then(
                function(d) {
                    console.log(d);
                    localStorage['academicsToBeEdited'] = JSON.stringify(d);
                    var academicsPopup = ModalFactory.open('Pages/UserAccount/templates/editAcademics.html', 'EditAcademicsController', 'md', '$ctrl');
                    academicsPopup.result.then(function(){
                        localStorage['academicsToBeEdited']=undefined;
                        getAllAcademics();
                    },function(){
                        localStorage['academicsToBeEdited'] = undefined;
                        console.log('modal was dismissed, instead of saved');
                    })
                },
                function(errResponse){
                    console.error('Error while editing Academics');
                }
            );

    }

///Add the functionalities required for the below function
    function editExperience(id) {
        AddExperienceService.getExperienceFromId(id)
            .then(
                function(d) {
                    console.log(d);
                    localStorage['experienceToBeEdited'] = JSON.stringify(d);
                    var academicsPopup = ModalFactory.open('Pages/UserAccount/templates/editExperience.html', 'EditExperienceController', 'md', '$ctrl');
                    academicsPopup.result.then(function(){
                        localStorage['experienceToBeEdited']=undefined;
                        getAllExperience();
                    },function(){
                        localStorage['experienceToBeEdited'] = undefined;
                        console.log('modal was dismissed, instead of saved');
                    })
                },
                function(errResponse){
                    console.error('Error while editing Experience');
                }
            );
    }

    function addAcademics() {
        var academicsPopUp = ModalFactory.open('Pages/UserAccount/templates/addAcademics.html', 'AddAcademicsController', 'md', '$ctrl');
        academicsPopUp.result.then(function(){
            getAllAcademics();
        },function(){
            console.log('modal was dismissed, instead of saved');
        })
    }


    function addExperience() {
       var experiencePopUp = ModalFactory.open('Pages/UserAccount/templates/addExperience.html', 'AddExperienceController', 'md', '$ctrl');
        experiencePopUp.result.then(function () {
            getAllExperience(); //function not created yet
        },function(){
            console.log('modal was dismissed, instead of saved');
        })
    }

    function addTags() {
        ModalFactory.open('Pages/UserAccount/templates/addTags.html', 'AddTagsController', 'md', '$ctrl')
    }

    function showFavourites() {
        ModalFactory.open('Pages/UserAccount/templates/favourites.html', 'FavouritesController', 'md', '$ctrl')
    }

    function getProfilePic(username){
        UserAccountService.getProfilePic(username)
            .then(
                function(d) {
                    vm.userProfilePic =d;
                    vm.picPath1 = '/user'+d.picPath+"?"+ new Date().getTime();
                },
                function(errResponse){
                    console.error('Error while getting profilePic');
                }
            );
    }

    //RESPONSIBLE FOR MODAL WINDOW POPUP
    function changePicModal () {
        var modalInstance = ModalFactory.open('Pages/UserAccount/templates/profilePic.html', 'ProfilePicController', 'md', '$ctrl');
        modalInstance.result.then(
            function(response){
                getProfilePic(vm.user.username);
            },function(errResponse){

            }
        )
    }

    //MAKES THE PROFILE EDITABLE
    function editProfile() {
        vm.editContent = true;
        $(".userDetail").attr('readonly', false);
        $(".userDetail").removeClass("updateDetail");
        console.log("you decided to edit your profile");
    }

    //TURNS THE PROFILE BACK TO UNEDITABLE, CALLS UPDATEUSER(USER,ID)
    function updateProfile() {
        vm.editContent = false;
        $(".userDetail").attr('readonly', true);
        $(".userDetail").addClass("updateDetail");
        console.log("update this user", vm.user);
        updateUser(vm.user, vm.user.id);
        localStorage['userInfo'] = JSON.stringify(vm.user);
    }

    //TAKES THE CHANGES TO BACKEND, AND UPDATES VM.USER
    function updateUser(user, id){
        UserAccountService.updateUser(user, id)
            .then(
                function(d) {
                    vm.user =d;
                },
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    }

    //TO LOG OUT
    function logOut() {
        localStorage['ifloggedin'] = undefined;
        $location.path('/');
    }

    //WHEN CLICKED AT THE PLUS BUTTON AT THE BOTTOM RIGHT
    function searchResults() {
        $location.path('/userAccount/searchResults')
    }
}
