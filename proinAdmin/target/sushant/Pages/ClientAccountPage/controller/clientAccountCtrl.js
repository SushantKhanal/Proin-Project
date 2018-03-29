angular
    .module('myApp')
    .controller('ClientAccountCtrl', clientAccountController);

clientAccountController.$inject = ['$location', 'ClientAccountService', 'ModalFactory'];

function clientAccountController($location, ClientAccountService, ModalFactory) {

    var vm = this;
    vm.GoBack = GoBack;
    vm.picPath1 = '';
    vm.getReviews = getReviews;
    vm.userAndReviews = '';
    vm.tags = '';
    vm.showReviews = false;
    vm.showFavourites = showFavourites;
    vm.deleteAccount = deleteAccount;

    vm.review;

    var localUserData = localStorage['adminSeesClient'];

    if(localUserData !== undefined) {
        vm.user = JSON.parse(localUserData);
        getProfilePic(vm.user.username);
    }

    getTags();
    getAllAcademics();
    getAllExperience();

    function deleteAccount() {

        var r = confirm("Are you sure you want to delete this account?");
        if (r == true) {
            console.log("You pressed OK!");
            ClientAccountService.deleteThisAccount(vm.user.id)
                .then(function(){
                    console.log("You have successfully deleted this account");
                },function () {
                    console.log("Trouble deleting this account.");
                })

        } else {
            console.log("You pressed Cancel!");
        }
    }

    function showFavourites() {
        ModalFactory.open('Pages/ClientAccountPage/templates/favourites.html', 'FavouritesController', 'md', '$ctrl')
    }

    function getAllAcademics() {
        vm.user = JSON.parse(localUserData);
        ClientAccountService.getAllAcademics(vm.user.username)
            .then(
                function(response) {
                    for(r of response) {
                        r.startDate = new Date(r.startDate);
                        r.endDate = new Date(r.endDate);
                    }
                    vm.academics = response;
                },
                function(errResponse){
                    alert('Academics could not be retrieved');
                });
    }

    function getAllExperience() {
        ClientAccountService.getAllExperience(vm.user.username)
            .then(
                function(response) {
                    for(r of response) {
                        r.startDate = new Date(r.startDate);
                        r.endDate = new Date(r.endDate);
                    }
                    vm.experience = response;
                },
                function(errResponse){
                    alert('Experience data could not be retrieved');
                });
    }

    function getTags() {
        ClientAccountService.receiveTags(vm.user.username)
            .then(
                function(r) {
                    vm.tags = r.tags;
                },
                function(errResponse){
                    console.error('this review could not be saved');
                });
    }

    //GETS AVAILABLE REVIEWS
    function getReviews() {
        vm.allowReview = false;
        vm.showReviews = true;
        vm.user = JSON.parse(localUserData);
        if (vm.userAndReviews !== ''){
            vm.showReviews = false;
            vm.userAndReviews = '';
            return;
        }
        ClientAccountService.getReviews(vm.user.username)
            .then(
                function(r) {
                    console.log(r);
                    vm.userAndReviews = r;
                },
                function(errResponse){
                    console.error('Error while getting reviews');
                });
    }


    //GETS THE ACCOUNT PROFILE PICTURE ON LOAD
    function getProfilePic(username){
        ClientAccountService.getProfilePic(username)
            .then(
                function(d) {
                    vm.userProfilePic =d;
                    vm.picPath1 = '/user'+d.picPath;
                },
                function(errResponse){
                    console.error('Error while getting profilePic');
                }
            );
    }

    //THE GO_BACK BUTTON
    function GoBack() {
        $location.path('/adminAccount')
    }


}
