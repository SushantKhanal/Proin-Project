//THE FOLLOWING CONTROLLER CONTROLS THE USER ACCOUNT PAGE
angular
    .module('myApp')
    .controller('NormalFollowingProCtrl', normalFollowingProCtrl);

normalFollowingProCtrl.$inject = ['ModalFactory', '$location', 'NormalAccountService', 'NormalFollowingProService'];

function normalFollowingProCtrl(ModalFactory, $location, NormalAccountService, NormalFollowingProService) {
    var vm = this;
    // vm.user = ClientSignInService.getResponse();
    vm.picPath1='';
    vm.sendCustomEmail = sendCustomEmail;

    var userData = localStorage['NormalUserInfo'];

    if(userData !== undefined) {
        vm.user = JSON.parse(userData);
    }

    getProfilePic(vm.user.username);
    fetchFollowings(vm.user.username);

    function sendCustomEmail(emailAddress) {
        var modalInstance = ModalFactory.open('Pages/FollowingPage/templates/sendCustomEmail.html', 'SendCustomEmailController', 'md', '$ctrl', emailAddress);
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

    function fetchFollowings(username) {
        NormalFollowingProService.fetchFollowings(username)
            .then(
                function(d) {
                    vm.followingsList =d;
                },
                function(errResponse){
                    console.error('Error while fetching followings');
                }
            );
    }

}
