//THE FOLLOWING CONTROLLER CONTROLS THE USER ACCOUNT PAGE
angular
    .module('myApp')
    .controller('NormalUserAccountCtrl', normalAccountController);

normalAccountController.$inject = ['ModalFactory', '$location', 'NormalAccountService'];

function normalAccountController(ModalFactory, $location, NormalAccountService) {
    var vm = this;
    // vm.user = ClientSignInService.getResponse();
    vm.picPath1='';
    vm.userAndReviews = '';
    vm.editProfile = editProfile;
    vm.editContent = false;
    vm.updateProfile = updateProfile;
    vm.users = [];
    vm.logOut = logOut;
    vm.searchResults =  searchResults;
    vm.changePicModal = changePicModal;
    vm.showFavourites = showFavourites;

    var userData = localStorage['NormalUserInfo'];

    if(userData !== undefined) {
        vm.user = JSON.parse(userData);
    }

    getProfilePic(vm.user.username);

    function showFavourites() {
        ModalFactory.open('Pages/NormalAccountPage/templates/favourites.html', 'FavouritesController', 'md', '$ctrl');
    }

    function getProfilePic(username){
        NormalAccountService.getProfilePic(username)
            .then(
                function(d) {
                    vm.userProfilePic =d;
                    if(d.picPath == undefined) {
                        return;
                    }
                    vm.picPath1 = '/user'+d.picPath+"?"+ new Date().getTime();
                },
                function(errResponse){
                    console.error('Error while getting profilePic');
                }
            );
    }

    //RESPONSIBLE FOR MODAL WINDOW POPUP
    function changePicModal () {
        var modalInstance = ModalFactory.open('Pages/NormalAccountPage/templates/profilePic.html', 'ProfilePicController', 'md', '$ctrl');
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
        updateUser(vm.user);
    }

    //TAKES THE CHANGES TO BACKEND, AND UPDATES VM.USER
    function updateUser(user){
        NormalAccountService.updateUser(user)
            .then(
                function(d) {
                    vm.user =d;
                    localStorage['NormalUserInfo'] = JSON.stringify(vm.user);
                },
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    }

    //TO LOG OUT
    function logOut() {
        localStorage['ifNormalloggedin'] = undefined;
        $location.path('/');
    }

    //WHEN CLICKED AT THE PLUS BUTTON AT THE BOTTOM RIGHT
    function searchResults() {
        $location.path('/NormalAccount/searchProClients')
    }
}
