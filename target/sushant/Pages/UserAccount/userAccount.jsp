<div class="UserAccount" ng-controller="UserAccountCtrl as xxx">

    <%--#########--%>
    <div class="container">
        <div class="row">

            <div class="col-md-10 ">

                <div class="panel panel-default">
                    <div class="panel-heading"><h4>User Profile</h4></div>
                    <div class="panel-body">

                        <div class="box box-info">

                            <div class="box-body">
                                <div class="col-sm-6">
                                    <div align="center"><img alt="User Pic"
                                                             src="https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg"
                                                             id="profile-image1" class="img-circle img-responsive">

                                        <input id="profile-image-upload" class="hidden" type="file">
                                        <div style="color:#999;">click here to change profile image</div>
                                        <!--Upload Image Js And Css-->

                                    </div>

                                    <br>

                                    <!-- /input-group -->
                                </div>
                                <div class="col-sm-6">
                                    <h4 style="color:#00b1b1;">{{xxx.user.firstName}} </h4></span>
                                    <span><p>Aspirant</p></span>
                                    <button class="btn btn-primary pull-right" ng-click="xxx.editProfile()">Edit
                                        Profile
                                    </button>
                                </div>
                                <div class="clearfix"></div>
                                <hr style="margin:5px 0 5px 0;">


                                <div class="col-sm-5 col-xs-6 tital ">First Name:</div>
                                <%--<div class="col-sm-7 col-xs-6 userDetail">{{xxx.user.firstName}}</div>--%>
                                <input class="col-sm-7 col-xs-6 userDetail"  ng-model="xxx.user.firstName" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Last Name:</div>
                                <%--<div class="col-sm-7 userDetail"> {{xxx.user.lastName}}</div>--%>
                                <input class="col-sm-7 userDetail"  ng-model="xxx.user.lastName" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">username:</div>
                                <%--<div class="col-sm-7 userDetail"> {{xxx.user.username}}</div>--%>
                                <input class="col-sm-7 userDetail"  ng-model="xxx.user.username" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Date Of Birth:</div>
                                <%--<div class="col-sm-7 userDetail"> {{xxx.user.dob | date}}</div>--%>
                                <input class="col-sm-7 userDetail"  ng-model="xxx.user.dob" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Bio:</div>
                                <%--<div class="col-sm-7 userDetail"> {{xxx.user.bio}}</div>--%>
                                <input class="col-sm-7 userDetail"  ng-model="xxx.user.bio" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Email:</div>
                                <%--<div class="col-sm-7 userDetail"> {{xxx.user.email}}</div>--%>
                                <input class="col-sm-7 userDetail"  ng-model="xxx.user.email" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>


                                <div class="col-sm-5 col-xs-6 tital ">Date Of Joining:</div>
                                <%--<div class="col-sm-7 userDetail">{{xxx.user.joinDate | date}}</div>--%>
                                <input class="col-sm-7 userDetail"  ng-model="xxx.user.joinDate" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Place Of Birth:</div>
                                <%--<div class="col-sm-7 userDetail">{{xxx.user.address}}</div>--%>
                                <input class="col-sm-7 userDetail"  ng-model="xxx.user.address" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Country:</div>
                                <%--<div class="col-sm-7 userDetail">{{xxx.user.nation}}</div>--%>
                                <input class="col-sm-7 userDetail"  ng-model="xxx.user.nation" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Agenda:</div>
                                <%--<div class="col-sm-7 userDetail"> {{xxx.user.agenda}}</div>--%>
                                <input class="col-sm-7 userDetail"  ng-model="xxx.user.agenda" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital ">Client Type:</div>
                                <%--<div class="col-sm-7 userDetail"> {{xxx.user.clientType}}</div>--%>
                                <input class="col-sm-7 userDetail"  ng-model="xxx.user.clientType" readonly/>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div ng-if="xxx.user.clientType == 'Personal'">
                                    <div class="col-sm-5 col-xs-6 tital ">Academics:</div>
                                    <%--<div class="col-sm-7 userDetail"> {{xxx.user.academics}}</div>--%>
                                    <input class="col-sm-7 userDetail"  ng-model="xxx.user.academics" readonly/>
                                    <div class="clearfix"></div>
                                    <div class="bot-border"></div>
                                </div>

                                <div ng-if="xxx.user.clientType == 'Personal'">
                                    <div class="col-sm-5 col-xs-6 tital ">Experience:</div>
                                    <%--<div class="col-sm-7 userDetail"> {{xxx.user.experience}}</div>--%>
                                    <input class="col-sm-7 userDetail"  ng-model="xxx.user.experience" readonly/>
                                    <div class="clearfix"></div>
                                    <div class="bot-border"></div>
                                </div>

                                <div ng-if="xxx.user.clientType == 'Corporate'">
                                    <div class="col-sm-5 col-xs-6 tital ">marketDomain:</div>
                                    <%--<div class="col-sm-7 userDetail"> {{xxx.user.marketDomain}}</div>--%>
                                    <input class="col-sm-7 userDetail"  ng-model="xxx.user.marketDomain" readonly/>
                                    <div class="clearfix"></div>
                                    <div class="bot-border"></div>
                                </div>
                                <button ng-show="xxx.editContent" ng-click="xxx.updateProfile()" class="btn btn-danger pull-right">Save</button>
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
