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
    vm.updateProfile = updateProfile;

    function editProfile() {
        vm.editContent = true;
        $(".userDetail").attr('readonly', false);
        $(".userDetail").removeClass("updateDetail");
        console.log("you decided to edit your profile");
    }

    function updateProfile() {
        $(".userDetail").attr('readonly', true);
        $(".userDetail").addClass("updateDetail");
        console.log("update this user", vm.user);
    }
}
