angular
    .module('myApp')
    .controller('ClientAccountCtrl', clientAccountController);

clientAccountController.$inject = ['$location', 'ClientAccountService', 'ModalFactory'];

function clientAccountController($location, ClientAccountService, ModalFactory) {

    var vm = this;
    vm.goBack = goBack;
    vm.picPath1 = '';
    vm.getReviews = getReviews;
    vm.userAndReviews = '';
    vm.tags = '';
    vm.showReviews = false;
    vm.showFavourites = showFavourites;
    vm.deleteAccount = deleteAccount;
    vm.whetherDelete = "Delete account";
    vm.status = '';

    vm.review;

    var localUserData = localStorage['adminSeesClient'];

    if(localUserData !== undefined) {
        vm.user = JSON.parse(localUserData);
        getProfilePic(vm.user.username);
    }

    getTags();
    getAllAcademics();
    getAllExperience();
    checkIfDeleted();

    function checkIfDeleted() {

        ClientAccountService.checkIfDeleted(vm.user.username)
            .then(function(r){
                vm.status = r;
                if(r==0){
                    vm.whetherDelete = "Undo delete account";
                }else {
                    vm.whetherDelete = "Delete account";
                }
            },function () {
                console.log("Could not get account status");
            })
    }
//DELETES OR RECOVERS ACCOUNT, BASED ON VM.STATUS
    function deleteAccount() {

        var r = confirm("Are you sure you want to go ahead?");
        if (r == true) {
            console.log("You pressed OK!");
            if (vm.status==0){
                ClientAccountService.undoDeleteThisAccount(vm.user.username)
                    .then(function(){
                        console.log("You have successfully recovered this account");
                        checkIfDeleted();
                    },function () {
                        console.log("Trouble recovering the account");
                    })
            } else {
                ClientAccountService.deleteThisAccount(vm.user.username)
                    .then(function(){
                        console.log("You have successfully deleted this account");
                        checkIfDeleted();
                    },function () {
                        console.log("Trouble deleting this account.");
                    })
            }


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
    function goBack() {
        $location.path('/adminAccount')
    }


}
