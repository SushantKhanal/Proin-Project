<div class="UserAccount" ng-controller="UserAccountCtrl as xxx">

    <%--#########--%>
    <div class="container">
        <div class="row">

            <div class="col-md-10 ">

                <div class="panel panel-default">
                    <div class="panel-heading">  <h4 >User Profile</h4></div>
                    <div class="panel-body">

                        <div class="box box-info">

                            <div class="box-body">
                                <div class="col-sm-6">
                                    <div  align="center"> <img alt="User Pic" src="https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg" id="profile-image1" class="img-circle img-responsive">

                                        <input id="profile-image-upload" class="hidden" type="file">
                                        <div style="color:#999;" >click here to change profile image</div>
                                        <!--Upload Image Js And Css-->

                                    </div>

                                    <br>

                                    <!-- /input-group -->
                                </div>
                                <div class="col-sm-6">
                                    <h4 style="color:#00b1b1;">{{xxx.user.firstName}} </h4></span>
                                    <span><p>Aspirant</p></span>
                                    <button class="btn btn-primary pull-right" ng-click="xxx.editProfile()">Edit Profile</button>
                                </div>
                                <div class="clearfix"></div>
                                <hr style="margin:5px 0 5px 0;">


                                <div class="col-sm-5 col-xs-6 tital " >First Name:</div><div class="col-sm-7 col-xs-6 ">{{xxx.user.firstName}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Last Name:</div><div class="col-sm-7"> {{xxx.user.lastName}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Date Of Birth:</div><div class="col-sm-7"> {{xxx.user.dob | date}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Bio:</div><div class="col-sm-7"> {{xxx.user.bio}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Email:</div><div class="col-sm-7"> {{xxx.user.email}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>


                                <div class="col-sm-5 col-xs-6 tital " >Date Of Joining:</div><div class="col-sm-7">{{xxx.user.joinDate | date}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Place Of Birth:</div><div class="col-sm-7">{{xxx.user.address}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Country:</div><div class="col-sm-7">{{xxx.user.nation}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Agenda:</div><div class="col-sm-7"> {{xxx.user.agenda}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Client Type:</div><div class="col-sm-7"> {{xxx.user.clientType}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div ng-if="xxx.user.clientType == 'Personal'">
                                    <div class="col-sm-5 col-xs-6 tital " >Academics:</div><div class="col-sm-7"> {{xxx.user.academics}}</div>
                                    <div class="clearfix"></div>
                                    <div class="bot-border"></div>
                                </div>

                                <div ng-if="xxx.user.clientType == 'Personal'">
                                    <div class="col-sm-5 col-xs-6 tital " >Experience:</div><div class="col-sm-7"> {{xxx.user.experience}}</div>
                                    <div class="clearfix"></div>
                                    <div class="bot-border"></div>
                                </div>

                                <div ng-if="xxx.user.clientType == 'Corporate'">
                                    <div class="col-sm-5 col-xs-6 tital " >marketDomain:</div><div class="col-sm-7"> {{xxx.user.marketDomain}}</div>
                                    <div class="clearfix"></div>
                                    <div class="bot-border"></div>
                                </div>

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
