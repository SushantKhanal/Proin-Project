<div class="UserAccount" ng-controller="UserAccountCtrl as xxx">

    <%--#########--%>
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
                                    <button class="btn-danger pull-right" ng-click="xxx.takeToSocial()">Social</button>
                                    <button class="btn-basic pull-right" ng-click="xxx.addTags()">Add Tags</button>

                                    <span><p>Aspirant</p></span>
                                    <span style="color:#008000; font-size:12px; cursor: pointer;"
                                          ng-click="xxx.showFavourites()">Show Favourites</span>

                                    <button class="btn-warning" ng-click="xxx.checkFollowRequests()">Follow requests</button>
                                    <br>
                                    <button class="btn-primary pull-right" ng-click="xxx.editProfile()">Edit Profile
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

                                <div ng-if="xxx.user.clientType == 'Personal'">
                                    <div style="margin-left:40%; margin-right:40%; color:green;"
                                         class="col-sm-5 col-xs-6 tital ">Academics: <span
                                            ng-show="xxx.academics == '' " style="color:blue;"
                                            class="glyphicon glyphicon-plus" ng-click="xxx.addAcademics()"></span>
                                    </div>

                                    &nbsp; &nbsp;
                                    <div class="bot-border"></div>
                                    <div ng-show="xxx.academics !== ''">
                                        <div ng-repeat="academics in xxx.academics">
                                            <div style="border:2px solid black; margin-left: 10px;">
                                                <button ng-click="xxx.editAcademics(academics.id)"
                                                        class="btn-warning pull-left">Edit
                                                </button>
                                                <button ng-click="xxx.addAcademics()" class="btn-danger pull-right">Add
                                                    More
                                                </button>
                                                <div style="margin-left: 10px;">
                                                    <div style="margin-left:40%; margin-right:40%;"><b>Degree
                                                        {{xxx.academics.indexOf(academics) + 1}}</b></div>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>Degree</div>
                                                    <span>{{academics.degree}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>School</div>
                                                    <span>{{academics.school}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>Location</div>
                                                    <span>{{academics.location}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>From</div>
                                                    <span>{{academics.startDate | date}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>To</div>
                                                    <span>{{academics.endDate | date}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>Description</div>
                                                    <span>{{academics.description}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                </div>
                                            </div>
                                            <div class="bot-border"></div>
                                            <div class="bot-border"></div>
                                        </div>

                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="bot-border"></div>
                                    <div class="bot-border"></div>
                                </div>

                                <div ng-if="xxx.user.clientType == 'Personal'">
                                    <div class="col-sm-5 col-xs-6 tital "
                                         style="margin-left:40%; margin-right:40%; color:green;">Experience: <span
                                            ng-show="xxx.experience == '' " id="experience" style="color:blue;"
                                            class="glyphicon glyphicon-plus" ng-click="xxx.addExperience()"/>
                                    </div>
                                    &nbsp; &nbsp;
                                    <div class="bot-border"></div>
                                    <div ng-show="xxx.experience !== ''">
                                        <div ng-repeat="exp in xxx.experience">
                                            <div style="border:2px solid black; margin-left: 10px;">
                                                <button class="btn-warning pull-left"
                                                        ng-click="xxx.editExperience(exp.id)">Edit
                                                </button>
                                                <button class="btn-danger pull-right" ng-click="xxx.addExperience()">Add
                                                    More
                                                </button>
                                                <div style="margin-left: 10px;">
                                                    <div style="margin-left:40%; margin-right:40%;"><b>Job
                                                        {{xxx.experience.indexOf(exp) + 1}}</b></div>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>Title</div>
                                                    <span>{{exp.title}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>Company</div>
                                                    <span>{{exp.company}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>Location</div>
                                                    <span>{{exp.location}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>From</div>
                                                    <span>{{exp.startDate | date}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>To</div>
                                                    <span>{{exp.endDate | date}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                    <div>Description</div>
                                                    <span>{{exp.description}}</span>
                                                    <div class="clearfix"></div>
                                                    <div class="bot-border"></div>
                                                </div>
                                            </div>
                                            <div class="bot-border"></div>
                                            <div class="bot-border"></div>
                                        </div>

                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="bot-border"></div>
                                    <div class="bot-border"></div>

                                </div>

                                <div ng-if="xxx.user.clientType == 'Corporate'">
                                    <div class="col-sm-5 col-xs-6 tital ">marketDomain:</div>
                                    <input class="col-sm-7 userDetail updateDetail" ng-model="xxx.user.marketDomain"
                                           readonly/>
                                    <div class="clearfix"></div>
                                    <div class="bot-border"></div>
                                </div>
                                <button class="btn btn-warning pull-left" ng-click="xxx.logOut()">Log Out</button>
                                &nbsp;
                                <button class="btn btn-primary showReviewsBtn" ng-click="xxx.showReviews()">
                                    {{xxx.reviewsText}}
                                </button>
                                <button class="btn btn-warning pull-right" ng-click="xxx.uploadDocuments()">Upload documents</button>
                                <div class="bot-border"></div>
                                <div ng-show="xxx.allowReviews">
                                    <ol>
                                        <li ng-repeat="userReview in xxx.userAndReviews">
                                            <span style="cursor: pointer; color: blue; font-size: 14px;"
                                                  ng-click="xxx.takeToAccount(userReview.loggedInUsername)">{{userReview.loggedInUsername}}: </span>
                                            <span>{{userReview.rating}} Star Rating</span>
                                            <h4>{{userReview.review}}</h4>
                                            <div class="clearfix"></div>
                                            <div class="bot-border"></div>
                                        </li>
                                    </ol>
                                </div>

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
