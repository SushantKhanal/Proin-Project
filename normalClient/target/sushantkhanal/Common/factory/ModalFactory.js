(function(){
    angular
        .module("myApp")
        .factory('ModalFactory',['$uibModal', modalFactory]);
    function modalFactory($uibModal){
        return {
            open: function(template, controller, size, controllerAs, param) {
                return $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: template,
                    controller: controller,
                    controllerAs: controllerAs,
                    size: size,
                    resolve: {
                        param: function() {
                            return param;
                        },
                    }
                });
            }
        };
    }

})();