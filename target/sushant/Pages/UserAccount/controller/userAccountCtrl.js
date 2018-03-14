//THE FOLLOWING CONTROLLER CONTROLS THE USER ACCOUNT PAGE
angular
    .module('myApp')
    .controller('UserAccountCtrl', userAccountController);

userAccountController.$inject = ['ClientSignInService', 'UserAccountService', 'ProfilePicModalFactory', '$location'];

function userAccountController(ClientSignInService, UserAccountService, ProfilePicModalFactory, $location) {
    var vm = this;
    vm.user = ClientSignInService.getResponse();
    vm.editProfile = editProfile;
    vm.editContent = false;
    vm.updateProfile = updateProfile;
    vm.users = [];
    vm.logOut = logOut;
    vm.searchResults =  searchResults;
    vm.changePicModal = changePicModal;

    var userData = localStorage['userInfo'];

    if(userData !== undefined) {
        vm.user = JSON.parse(userData);
    }

    //responsible for modal window popup
    function changePicModal () {
        ProfilePicModalFactory.open('Pages/UserAccount/templates/profilePic.html', 'ProfilePicController', 'md', '$ctrl');
    }

    //makes the profile editable
    function editProfile() {
        vm.editContent = true;
        $(".userDetail").attr('readonly', false);
        $(".userDetail").removeClass("updateDetail");
        console.log("you decided to edit your profile");
    }

    //turns the profile back to uneditable, calls updateUser(user,id)
    function updateProfile() {
        vm.editContent = false;
        $(".userDetail").attr('readonly', true);
        $(".userDetail").addClass("updateDetail");
        console.log("update this user", vm.user);
        updateUser(vm.user, vm.user.id);
        localStorage['userInfo'] = JSON.stringify(vm.user);
    }
    //takes the changes to backend, and updates vm.user
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


    function logOut() {
        localStorage['ifloggedin'] = undefined;
        $location.path('/');
    }

    //when clicked at the plus button at the bottom right
    function searchResults() {
        $location.path('/userAccount/searchResults')
    }
}
