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
                                    <h4 style="color:#00b1b1;">{{xxx.user.username}} </h4></span>
                                    <span><p>Aspirant</p></span>
                                </div>
                                <div class="clearfix"></div>
                                <hr style="margin:5px 0 5px 0;">


                                <div class="col-sm-5 col-xs-6 tital " >First Name:</div><div class="col-sm-7 col-xs-6 ">{{xxx.user.username}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Middle Name:</div><div class="col-sm-7"> {{xxx.user.username}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Last Name:</div><div class="col-sm-7"> {{xxx.user.username}}</div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Date Of Joining:</div><div class="col-sm-7">15 Jun 2016</div>

                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Date Of Birth:</div><div class="col-sm-7">11 Jun 1998</div>

                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Place Of Birth:</div><div class="col-sm-7">{{xxx.user.address}}</div>

                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Nationality:</div><div class="col-sm-7">Indian</div>

                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital " >Religion:</div><div class="col-sm-7">Hindu</div>

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
