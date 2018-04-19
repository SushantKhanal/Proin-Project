<div ng-controller="SearchProCleintsCtrl as xxx">
    <%--<div>--%>
        <%--<button class="btn btn-danger" ng-click="xxx.fetchId()">Fetch 3 Unique Ids</button>--%>
    <%--</div>--%>

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
                                             id="profile-image1" class="img-circle img-responsive">
                                        <input id="profile-image-upload" class="hidden" type="file">
                                    </div>
                                    <br>

                                    <!-- /input-group -->
                                </div>

                                <div class="col-sm-6">
                                    <h4 style="color:#00b1b1;">{{xxx.normalUser.firstName}} {{xxx.normalUser.lastName}} </h4></span>

                                    <span><p>Aspirant</p></span>


                                </div>
                                <div class="clearfix"></div>
                                <hr style="margin:5px 0 5px 0;">


                            </div>
                            <div>
                                <br>
                                <button style="font-size:16px" ng-click="xxx.goBack()"><i class="fa fa-arrow-left"></i> Go Back</button>
                                <h3>Search for professionals here</h3>

                                <div id="arrowNavigation">
                                    <input class="searchInput" type="text" ng-model="xxx.searchThis" ng-change="xxx.searchResults()"
                                           ng-model-options="{debounce: 500}"/>
                                    <select id="selectedCountry" ng-model="xxx.selectedCountry">
                                        <option value="">-- Select a Country --</option>
                                        <option ng-repeat="country in xxx.countries" value="{{country}}">{{country}}</option>
                                    </select>
                                    <div ng-if="xxx.users !== ''">
                                        <table ng-table="xxx.tableParams" style="cursor: pointer; color:blue;" class="listOfResult table">
                                            <tr ng-repeat="profile in xxx.users">
                                                <td id="user{{$index}}" ng-click="xxx.displayProfile(profile.username)">
                                                    {{profile.username}} ({{profile.tags}})
                                                </td>
                                            </tr>
                                        </table>
                                        <uib-pagination
                                                ng-model="pagination.currentPage"
                                                items-per-page="3"
                                                total-items="xxx.noOfItems"
                                                max-size="5"
                                                ng-change="xxx.searchResults(xxx.accountType)"
                                                boundary-links="true">
                                        </uib-pagination>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box -->

                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>

