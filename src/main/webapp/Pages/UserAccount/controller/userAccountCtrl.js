//THE FOLLOWING CONTROLLER CONTROLS THE USER ACCOUNT PAGE
angular
    .module('myApp')
    .controller('UserAccountCtrl', userAccountController);

userAccountController.$inject = ['ClientSignInService'];

function userAccountController(ClientSignInService) {
    var vm = this;
    vm.user = ClientSignInService.getResponse();
    vm.editProfile = editProfile;
    vm.editContent = false;

    function editProfile() {
        vm.editContent = true;
        $(".userDetail").attr('contenteditable', true);
        console.log("you decided to edit your profile");
    }
}
