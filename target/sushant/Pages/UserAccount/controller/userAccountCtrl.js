//THE FOLLOWING CONTROLLER CONTROLS THE USER ACCOUNT PAGE
angular
    .module('myApp')
    .controller('UserAccountCtrl', userAccountController);

userAccountController.$inject = ['ClientSignInService'];

function userAccountController(ClientSignInService) {
    var vm = this;
    vm.user = ClientSignInService.getResponse();
    vm.editProfile = editProfile;

    function editProfile() {
        console.log("you decided to edit your profile");
    }
}
