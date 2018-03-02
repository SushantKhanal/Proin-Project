<div class="generic-container" ng-controller="ClientSignupController as ctrl">
    <div class="panel panel-default">
        <button type="button" class="close pull-right" aria-label="Close" ng-click="ctrl.close()">
            <span aria-hidden="true">&times;</span>
        </button>
        <div class="panel-heading"><span class="lead">SignUp Form </span></div>
        <div class="formcontainer">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.user.id" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label">Name</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.user.username" name="uname" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label">Password</label>
                        <div class="col-md-7">
                            <input type="password" ng-model="ctrl.user.password" name="uname" class="password form-control input-sm" required ng-minlength="3"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label">Address</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.user.address" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label">Email</label>
                        <div class="col-md-7">
                            <input type="email" ng-model="ctrl.user.email" name="email" class="email form-control input-sm" placeholder="Enter your Email" required/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.email.$error.required">This is a required field</span>
                                <span ng-show="myForm.email.$invalid">This field is invalid </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label">Agenda</label>
                        <div class="col-md-7">
                            <input type="Agenda" ng-model="ctrl.user.agenda" name="agenda" class="form-control input-sm" placeholder="Enter your Purpose" required ng-minlength="4"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.agenda.$error.minlength">Minimum length required is 4</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label">Client Type</label>
                        <div class="col-md-7">
                            <button id="personalButton" type="button" ng-click="ctrl.ifPersonal()" class="btn btn-sm">Personal</button>
                            <button id="corporateButton" type="button" ng-click="ctrl.ifCorporate()" class="btn btn-sm">Corporate</button>
                        </div>
                    </div>
                </div>
                <%--If the client is of personal type, the following divs get displayed--%>
                <div ng-show="ctrl.personal" class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label">Academics</label>
                        <div class="col-md-7">
                            <input type="Academics" ng-model="ctrl.user.academics" name="academics" class="form-control input-sm academics" placeholder="Enter your Academics" ng-required="ctrl.requirePersonal" ng-minlength="5"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.academics.$error.minlength">Minimum length required is 5</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div ng-show="ctrl.personal" class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label">Work Experience</label>
                        <div class="col-md-7">
                            <input type="Experience" ng-model="ctrl.user.experience" name="experience" class="form-control input-sm workExp" placeholder="Enter your Experience" ng-required="ctrl.requirePersonal" ng-minlength="5"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.experience.$error.minlength">Minimum length required is 5</span>
                            </div>
                        </div>
                    </div>
                </div>
                <%--If the client is of corporate type, the following div gets displayed--%>
                <div ng-show="ctrl.corporate" class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label">Market Domain</label>
                        <div class="col-md-7">
                            <input type="MarketDomain" ng-model="ctrl.user.marketDomain" name="marketDomain" class="form-control input-sm marketDomain" placeholder="Enter your Market Domain" ng-required="ctrl.requireCorporate" ng-minlength="5"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.marketDomain.$error.minlength">Minimum length required is 5</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="SUBMIT" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"/>
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
</div>
