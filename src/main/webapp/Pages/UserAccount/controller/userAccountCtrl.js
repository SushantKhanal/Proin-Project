//THE FOLLOWING CONTROLLER CONTROLS THE USER ACCOUNT PAGE
angular
    .module('myApp')
    .controller('UserAccountCtrl', userAccountController);

userAccountController.$inject = ['ClientSignInService', 'UserAccountService', 'ModalFactory', '$location', 'AddAcademicsService'];

function userAccountController(ClientSignInService, UserAccountService, ModalFactory, $location, AddAcademicsService) {
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
    vm.addAcademics = addAcademics;
    vm.academics = '';
    vm.experience = '';


    var userData = localStorage['userInfo'];

    if(userData !== undefined) {
        vm.user = JSON.parse(userData);
    }

    getProfilePic(vm.user.username);

    getAllAcademics();

    function getAllAcademics() {
        AddAcademicsService.getAllAcademics(vm.user.username)
            .then(
                function(response) {
                    for(r of response) {
                        r.startDate = new Date(r.startDate);
                        r.endDate = new Date(r.endDate);
                    }
                    // r.startDate = new Date(r.startDate);
                    // r.endDate = new Date(r.endDate);
                    vm.academics = response;
                },
                function(errResponse){
                    alert('Academics could not be retrieved');
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
        // experiencePopUp.result.then(function () {
        //     getAllExperience(); //function not created yet
        // },function(){
        //     console.log('modal was dismissed, instead of saved');
        // })
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
