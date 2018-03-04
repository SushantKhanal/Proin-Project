angular
    .module('myApp')
    .controller('UserAccountCtrl', userAccountController);

userAccountController.$inject = ['ClientSignInService'];

function userAccountController(ClientSignInService) {
    var vm = this;
    vm.user = ClientSignInService.getResponse();
}
