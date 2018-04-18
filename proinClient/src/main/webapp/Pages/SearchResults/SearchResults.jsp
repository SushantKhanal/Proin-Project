<div ng-controller="SearchResultCtrl as xxx">

    <div class="container">
        <div class="row">

            <div class="col-md-10 ">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3>Search for other professionals here</h3>
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
                                    <h4 style="color:#00b1b1;">{{xxx.proUser.firstName}} {{xxx.proUser.lastName}} </h4></span>

                                    <span><p>Aspirant</p></span>
                                </div>
                                <div class="clearfix"></div>
                                <hr style="margin:5px 0 5px 0;">
                                <div>
                                    <button style="font-size:16px" ng-click="xxx.goBack()"><i class="fa fa-arrow-left"></i> Go Back</button>
                                </div>
                                <div class="bot-border"></div>

                                <div id="arrowNavigation">
                                    <input id="proSearch" class = "searchInput" type="text" ng-model="xxx.searchThis" ng-change="xxx.searchResults()" ng-model-options="{debounce: 500}"/>
                                    <select id="selectedCountry" ng-model="country" ng-change="xxx.country">
                                        <option value="">-- Select a Country --</option>
                                        <option ng-repeat="country in xxx.countries" value="{{country}}">{{country}}</option>
                                    </select>

                                    <ol>
                                        <div ng-show="xxx.showList" ng-repeat="profile in xxx.users">
                                            <li id="user{{$index}}" style="cursor: pointer; color:blue;" class="listOfResult" ng-click="xxx.displayProfile(profile.username)">
                                                {{profile.username}}: {{profile.tags}}
                                            </li>
                                        </div>
                                    </ol>
                                </div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>


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

