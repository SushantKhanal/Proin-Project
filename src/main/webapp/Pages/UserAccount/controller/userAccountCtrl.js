//THE FOLLOWING CONTROLLER CONTROLS THE USER ACCOUNT PAGE
angular
    .module('myApp')
    .controller('UserAccountCtrl', userAccountController);

userAccountController.$inject = ['ClientSignInService', 'UserAccountService', 'ModalFactory', '$location'];

function userAccountController(ClientSignInService, UserAccountService, ModalFactory, $location) {
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

    var userData = localStorage['userInfo'];

    if(userData !== undefined) {
        vm.user = JSON.parse(userData);
    }

    getProfilePic(vm.user.username);

    function addExperience() {
        ModalFactory.open('Pages/UserAccount/templates/addExperience.html', 'AddExperienceController', 'md', '$ctrl')
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
