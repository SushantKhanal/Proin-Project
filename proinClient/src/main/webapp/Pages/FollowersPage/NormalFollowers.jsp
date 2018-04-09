<h4>This is where the list of Normal followers will be shown</h4>
<div ng-controller="NormalFollowersCtrl as xxx">
    <div class="container">
        <div class="row">

            <div class="col-md-10 ">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>User Profile</h4>
                    </div>

                    <div class="panel-body">

                        <div class="box box-info">

                            <div class="box-body">
                                <div class="col-sm-6">
                                    <div align="center">
                                        <img alt="User Pic"
                                             ng-src="{{xxx.picPath1==='' ? 'https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg':xxx.picPath1}}"
                                             id="profile-image1" class="img-circle img-responsive">
                                        <input id="profile-image-upload" class="hidden" type="file">
                                    </div>
                                    <br>
                                    <!-- /input-group -->
                                </div>

                                <div class="col-sm-6">
                                    <h4 style="color:#00b1b1;">{{xxx.user.firstName}} {{xxx.user.lastName}} </h4></span>

                                    <span><p>Aspirant</p></span>
                                </div>
                                <div class="clearfix"></div>
                                <hr style="margin:5px 0 5px 0;">

                                <div class="col-sm-5 col-xs-6 tital "><h4 style="color: seagreen; ">Normal Followers</h4></div>
                                <%--<input class="col-sm-7 col-xs-6 userDetail updateDetail" ng-model="xxx.user.firstName"--%>
                                       <%--readonly/>--%>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital "><span style="color: blue;">Username</span></div>
                                <span style="color: blue;" class="col-sm-5 col-xs-6 tital">Email</span>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div ng-repeat="follower in xxx.followersList">
                                    <div class="col-sm-5 col-xs-6 tital "><p>{{follower.username}}</p></div>
                                    <span class="col-sm-7">{{follower.email}} <button class="btn-primary" ng-click="xxx.sendCustomEmail(follower.email)">Send Email</button></span>
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
</div>
