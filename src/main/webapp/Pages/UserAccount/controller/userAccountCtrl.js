//THE FOLLOWING CONTROLLER CONTROLS THE USER ACCOUNT PAGE
angular
    .module('myApp')
    .controller('UserAccountCtrl', userAccountController);

userAccountController.$inject = ['ClientSignInService', 'UserAccountService', 'ClientSignupService', '$location'];

function userAccountController(ClientSignInService, UserAccountService, ClientSignupService, $location) {
    var vm = this;
    vm.user = ClientSignInService.getResponse();
    vm.editProfile = editProfile;
    vm.editContent = false;
    vm.updateProfile = updateProfile;
    vm.users = [];
    vm.logOut = logOut;

    var userData = localStorage['userInfo'];

    if(userData !== undefined) {
        vm.user = JSON.parse(userData);
    }

    window.onload = console.log("###***###", userData);

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
        updateUser(vm.user, vm.user.id);
        //localStorage['userInfo'] = JSON.stringify(vm.user);
    }

    function updateUser(user, id){
        UserAccountService.updateUser(user, id)
            .then(
                fetchAllUsers(),
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    }

    function fetchAllUsers() {
        ClientSignupService.fetchAllUsers()
            .then(
                function(d) {
                    vm.users = d;
                    //vm.index = (vm.users.length - 1);
                    console.log(vm.users);
                    //return d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function logOut() {
        $location.path('/');
    }
}
