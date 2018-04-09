//THE FOLLOWING CONTROLLER CONTROLS THE USER ACCOUNT PAGE
angular
    .module('myApp')
    .controller('NormalFollowersCtrl', normalFollowersCtrl);

normalFollowersCtrl.$inject = ['ClientSignInService', 'UserAccountService', '$location', 'NormalFollowersService', 'ModalFactory'];

function normalFollowersCtrl(ClientSignInService, UserAccountService, $location, NormalFollowersService, ModalFactory) {
    var vm = this;
    vm.user = ClientSignInService.getResponse();
    vm.picPath1='';
    vm.sendCustomEmail = sendCustomEmail;

    //follow request sent status = 0
    //follow request accepted status = 1
    //unfollowed status = 2
    //ignored request status = 3

    var userData = localStorage['userInfo'];

    if(userData !== undefined) {
        vm.user = JSON.parse(userData);
    }

    getProfilePic(vm.user.username);
    fetchFollowers(vm.user.username);

    function sendCustomEmail(emailAddress) {
        var modalInstance = ModalFactory.open('Pages/FollowersPage/templates/sendCustomEmail.html', 'SendCustomEmailController', 'md', '$ctrl', emailAddress);
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

    function fetchFollowers(username) {
        NormalFollowersService.fetchFollowers(username)
            .then(
                function(d) {
                    vm.followersList = d;
                    console.log(d);
                },
                function(errResponse){
                    console.error('Error while fetching followers');
                }
            );
    }

}
