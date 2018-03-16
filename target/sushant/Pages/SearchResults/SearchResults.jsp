<div ng-controller="SearchResultCtrl as xxx">

    <div>

        <button style="font-size:16px" ng-click="xxx.goBack()"><i class="fa fa-arrow-left"></i> Go Back</button>
        <h3>Search for other professionals here</h3>
        <input class = "searchInput" type="text" ng-model="xxx.searchThis"/>
        <select id="selectedCountry" ng-model="country" ng-change="xxx.country">
            <option value="">-- Select a Country --</option>
            <option ng-repeat="country in xxx.countries" value="{{country}}">{{country}}</option>
        </select>
        <button class="btn-primary" ng-click="xxx.searchResults()">Search</button>

        <div ng-show="xxx.showList" ng-repeat="profile in xxx.users">
            <span class="listOfResult" ng-click="xxx.displayProfile(profile)">{{profile.firstName}} {{profile.lastName}}</span>
        </div>

    </div>


    <%--<div class="UserAccount" ng-show="xxx.showAccount">--%>

        <%--&lt;%&ndash;#########&ndash;%&gt;--%>
        <%--<div class="container">--%>
            <%--<div class="row">--%>

                <%--<div class="col-md-10 ">--%>

                    <%--<div class="panel panel-default">--%>
                        <%--<div class="panel-heading">--%>
                            <%--<h4>User Profile</h4>--%>
                        <%--</div>--%>


                        <%--<div class="panel-body">--%>

                            <%--<div class="box box-info">--%>

                                <%--<div class="box-body">--%>
                                    <%--<div class="col-sm-6">--%>
                                        <%--<div align="center"><img alt="User Pic"--%>
                                                                 <%--ng-src="{{xxx.picPath1==='' ? 'https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg':xxx.picPath1}}"--%>
                                                                 <%--id="profile-image1" class="img-circle img-responsive">--%>

                                            <%--<input id="profile-image-upload" class="hidden" type="file">--%>
                                            <%--<div style="color:#999;">click here to change profile image</div>--%>
                                            <%--<!--Upload Image Js And Css-->--%>

                                        <%--</div>--%>

                                        <%--<br>--%>

                                        <%--<!-- /input-group -->--%>
                                    <%--</div>--%>
                                    <%--<div class="col-sm-6">--%>
                                        <%--<h4 style="color:#00b1b1;">{{xxx.user.firstName}} {{xxx.user.lastName}}</h4></span>--%>
                                        <%--<span><p>Aspirant</p></span>--%>

                                    <%--</div>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<hr style="margin:5px 0 5px 0;">--%>


                                    <%--<div class="col-sm-5 col-xs-6 tital ">First Name:</div>--%>
                                    <%--&lt;%&ndash;<div class="col-sm-7 col-xs-6 userDetail updateDetail">{{xxx.user.firstName}}</div>&ndash;%&gt;--%>
                                    <%--<input class="col-sm-7 col-xs-6 userDetail updateDetail"  ng-model="xxx.user.firstName" readonly/>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<div class="bot-border"></div>--%>

                                    <%--<div class="col-sm-5 col-xs-6 tital ">Last Name:</div>--%>
                                    <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail"> {{xxx.user.lastName}}</div>&ndash;%&gt;--%>
                                    <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.lastName" readonly/>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<div class="bot-border"></div>--%>

                                    <%--<div class="col-sm-5 col-xs-6 tital ">username:</div>--%>
                                    <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail"> {{xxx.user.username}}</div>&ndash;%&gt;--%>
                                    <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.username" readonly/>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<div class="bot-border"></div>--%>

                                    <%--<div class="col-sm-5 col-xs-6 tital ">Date Of Birth:</div>--%>
                                    <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail"> {{xxx.user.dob | date}}</div>&ndash;%&gt;--%>
                                    <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.dob" readonly/>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<div class="bot-border"></div>--%>

                                    <%--<div class="col-sm-5 col-xs-6 tital ">Bio:</div>--%>
                                    <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail"> {{xxx.user.bio}}</div>&ndash;%&gt;--%>
                                    <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.bio" readonly/>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<div class="bot-border"></div>--%>

                                    <%--<div class="col-sm-5 col-xs-6 tital ">Email:</div>--%>
                                    <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail"> {{xxx.user.email}}</div>&ndash;%&gt;--%>
                                    <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.email" readonly/>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<div class="bot-border"></div>--%>


                                    <%--<div class="col-sm-5 col-xs-6 tital ">Date Of Joining:</div>--%>
                                    <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail">{{xxx.user.joinDate | date}}</div>&ndash;%&gt;--%>
                                    <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.joinDate" readonly/>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<div class="bot-border"></div>--%>

                                    <%--<div class="col-sm-5 col-xs-6 tital ">Place Of Birth:</div>--%>
                                    <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail">{{xxx.user.address}}</div>&ndash;%&gt;--%>
                                    <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.address" readonly/>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<div class="bot-border"></div>--%>

                                    <%--<div class="col-sm-5 col-xs-6 tital ">Country:</div>--%>
                                    <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail">{{xxx.user.nation}}</div>&ndash;%&gt;--%>
                                    <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.nation" readonly/>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<div class="bot-border"></div>--%>

                                    <%--<div class="col-sm-5 col-xs-6 tital ">Agenda:</div>--%>
                                    <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail"> {{xxx.user.agenda}}</div>&ndash;%&gt;--%>
                                    <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.agenda" readonly/>--%>
                                    <%--<div class="clearfix"></div>--%>
                                    <%--<div class="bot-border"></div>--%>

                                    <%--<div ng-if="xxx.user.clientType == 'Personal'">--%>
                                        <%--<div class="col-sm-5 col-xs-6 tital ">Academics:</div>--%>
                                        <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail"> {{xxx.user.academics}}</div>&ndash;%&gt;--%>
                                        <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.academics" readonly/>--%>
                                        <%--<div class="clearfix"></div>--%>
                                        <%--<div class="bot-border"></div>--%>
                                    <%--</div>--%>

                                    <%--<div ng-if="xxx.user.clientType == 'Personal'">--%>
                                        <%--<div class="col-sm-5 col-xs-6 tital ">Experience:</div>--%>
                                        <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail"> {{xxx.user.experience}}</div>&ndash;%&gt;--%>
                                        <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.experience" readonly/>--%>
                                        <%--<div class="clearfix"></div>--%>
                                        <%--<div class="bot-border"></div>--%>
                                    <%--</div>--%>

                                    <%--<div ng-if="xxx.user.clientType == 'Corporate'">--%>
                                        <%--<div class="col-sm-5 col-xs-6 tital ">marketDomain:</div>--%>
                                        <%--&lt;%&ndash;<div class="col-sm-7 userDetail updateDetail"> {{xxx.user.marketDomain}}</div>&ndash;%&gt;--%>
                                        <%--<input class="col-sm-7 userDetail updateDetail"  ng-model="xxx.user.marketDomain" readonly/>--%>
                                        <%--<div class="clearfix"></div>--%>
                                        <%--<div class="bot-border"></div>--%>
                                    <%--</div>--%>

                                    <%--<button ng-click="xxx.backToSearch()" class="btn btn-danger pull-right">Go Back</button>--%>

                                    <%--<!-- /.box-body -->--%>
                                <%--</div>--%>
                                <%--<!-- /.box -->--%>

                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>

            <%--</div>--%>
        <%--</div>--%>
        <%--&lt;%&ndash;##############&ndash;%&gt;--%>
    <%--</div>--%>

</div>

