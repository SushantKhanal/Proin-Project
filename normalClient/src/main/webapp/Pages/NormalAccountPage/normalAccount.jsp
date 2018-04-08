<div class="UserAccount" ng-controller="NormalUserAccountCtrl as xxx">

    <%--#########--%>
    <div class="container">
        <div class="row">

            <div class="col-md-10 ">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>Normal User Profile</h4>
                    </div>

                    <div class="panel-body">

                        <div class="box box-info">

                            <div class="box-body">
                                <div class="col-sm-6">
                                    <div align="center">
                                        <img alt="User Pic"
                                             ng-src="{{xxx.picPath1==='' ? 'https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg':xxx.picPath1}}"
                                             id="profile-image1" class="img-circle img-responsive"
                                             ng-click="xxx.changePicModal()">
                                        <input id="profile-image-upload" class="hidden" type="file">
                                        <div style="color:#999;">click here to change profile image</div>
                                    </div>
                                    <br>

                                    <!-- /input-group -->
                                </div>

                                <div class="col-sm-6">
                                    <h4 style="color:#00b1b1;">{{xxx.user.firstName}} {{xxx.user.lastName}} </h4></span>
                                    <button class="btn-danger pull-right">Social</button>


                                    <span><p>Aspirant</p></span>
                                    <span style="color:#008000; font-size:12px; cursor: pointer;"
                                          ng-click="xxx.showFavourites()">Show Favourites</span>
                                    <br>

                                    <button class="btn btn-primary pull-right" ng-click="xxx.editProfile()">Edit
                                        Profile
                                    </button>
                                </div>
                                <div class="clearfix"></div>
                                <hr style="margin:5px 0 5px 0;">


                                <div class="col-sm-5 col-xs-6 tital ">First Name:</div>
                                <input class="col-sm-7 col-xs-6 userDetail updateDetail" ng-model="xxx.user.firstName"
                                       readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Last Name:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.lastName" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">username:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.username" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Date Of Birth:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.dob" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Bio:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.bio" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Email:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.email" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>


                                <div class="col-sm-5 col-xs-6 tital ">Date Of Joining:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.joinDate" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Place Of Birth:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.address" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Country:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.nation" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Agenda:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.agenda" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Academics:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.academics" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Experience:</div>
                                <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.experience" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <button class="btn btn-warning pull-left" ng-click="xxx.logOut()">Log Out</button>
                                &nbsp;

                                <div class="bot-border"></div>

                                <button ng-show="xxx.editContent" ng-click="xxx.updateProfile()"
                                        class="btn btn-danger pull-right">Save
                                </button>

                                <a class="btn btn-info btn-lg pull-right" ng-if="xxx.editContent == false"
                                   ng-click="xxx.searchResults()">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </a>

                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->

                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <%--##############--%>
</div>
