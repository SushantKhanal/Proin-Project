<div ng-controller="AdminAccountPageCtrl as xxx">
    <div class="container">
        <div class="row">

            <div class="col-md-10 ">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h1 style="color:green;">{{xxx.welcomeMessage}}</h1>
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
                                    <h4 style="color:#00b1b1;">{{xxx.admin.firstName}} {{xxx.admin.lastName}} </h4></span>
                                </div>
                                <div class="clearfix"></div>
                                <hr style="margin:5px 0 5px 0;">

                                <%--<div class="col-sm-5 col-xs-6 tital "></div>--%>

                                <div id="arrowNavigation">
                                    <h3>Search for client accounts here</h3>
                                    <input type="radio" name="radAnswer" ng-model="xxx.accountType" ng-change="xxx.seekResults()" ng-value="1">Deleted Accounts
                                    &nbsp;
                                    <input type="radio" name="radAnswer" ng-model="xxx.accountType" ng-change="xxx.seekResults()" ng-value="0">Active accounts
                                    <br/>
                                    <input class="searchInput" type="text" ng-model="xxx.searchThis" ng-change="xxx.searchResults(xxx.accountType)"
                                           ng-model-options="{debounce: 500}"/>
                                    <select id="selectedCountry" ng-model="xxx.selectedCountry">
                                        <option value="">-- Select a Country --</option>
                                        <option ng-repeat="country in xxx.countries" value="{{country}}">{{country}}</option>
                                    </select>
                                    <div ng-if="xxx.users !== ''">
                                        <table ng-table="xxx.tableParams" style="cursor: pointer; color:blue;" class="listOfResult table">
                                            <tr ng-repeat="user in xxx.users">
                                                <td id="user{{$index}}" ng-click="xxx.displayProfile(user.username)">
                                                    {{user.username}} ({{user.tags}})
                                                </td>
                                            </tr>
                                        </table>
                                        <uib-pagination
                                                ng-model="pagination.currentPage"
                                                items-per-page="3"
                                                total-items="noOfItems"
                                                max-size="5"
                                                ng-change="xxx.searchResults(xxx.accountType)"
                                                boundary-links="true">
                                        </uib-pagination>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="bot-border"></div>

                                <div class="col-sm-5 col-xs-6 tital "></div>
                                <div class="bot-border"></div>

                                <%--<div class="col-sm-6">--%>
                                    <%--<h1>Hello</h1>--%>
                                <%--</div>--%>

                                <button class="btn-warning" ng-click="xxx.showAccountRequests()">{{xxx.requestButton}}</button>
                                &nbsp;
                                <button class="btn-danger" ng-click="xxx.fetchAdminRequests()">{{xxx.requestingAdminsButton}}</button>
                                <ol ng-show="xxx.requestingUsers !== '' && xxx.showClientRequests == true">
                                    <div ng-repeat="profile in xxx.requestingUsers">
                                        <br/>
                                        <li class="listOfResult">
                                            <span style="cursor: pointer; color:blue; margin-bottom: 2px" ng-click="xxx.displayRequestingUser(profile)">{{profile}}</span>
                                            <br/>
                                            <button ng-click="xxx.approveRequest(profile)">Approve</button>
                                            <button ng-click="xxx.denyRequest(profile)">Reject</button>
                                            <br/>
                                        </li>
                                    </div>
                                    <br/>
                                    <h4 ng-show="xxx.requestingUsers == ''">No account requests to show</h4>
                                </ol>

                                <ol ng-show="xxx.requestingAdmins !== '' && xxx.showAdminRequests == true">
                                    <div ng-repeat="profile in xxx.requestingAdmins">
                                        <br/>
                                        <li class="listOfResult">
                                            <span style="cursor: pointer; color:blue; margin-bottom: 2px">{{profile}}</span>
                                            <br/>
                                            <button ng-click="xxx.approveAdminRequest(profile)">Approve</button>
                                            <button ng-click="xxx.rejectAdminRequest(profile)">Reject</button>
                                            <br/>
                                        </li>
                                    </div>
                                    <br/>
                                    <h4 ng-show="xxx.requestingAdmins == ''">No admin requests to show</h4>
                                </ol>

                                <div class="bot-border"></div>
                                <button class="btn btn-primary pull-right" ng-click="xxx.logOut()">Log out</button>
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


<%--////////////////////////////////////////////////////////////////////--%>

<%--<div id="adminAccountPage" ng-controller="AdminAccountPageCtrl as xxx">--%>
    <%--<h1 style="color:green;">{{xxx.welcomeMessage}}</h1>--%>
    <%--&lt;%&ndash;<button class="btn btn-primary" ng-click="xxx.sendEmail()">Send Email</button>&ndash;%&gt;--%>

    <%--<div class="bot-border"></div>--%>
    <%--<div id="arrowNavigation">--%>
        <%--<h3>Search for client accounts here</h3>--%>
        <%--<input type="radio" name="radAnswer" ng-model="xxx.accountType" ng-change="xxx.seekResults()" ng-value="1">Deleted Accounts--%>
        <%--&nbsp;--%>
        <%--<input type="radio" name="radAnswer" ng-model="xxx.accountType" ng-change="xxx.seekResults()" ng-value="0">Active accounts--%>
        <%--<br/>--%>
        <%--<input class="searchInput" type="text" ng-model="xxx.searchThis" ng-change="xxx.searchResults(xxx.accountType)"--%>
               <%--ng-model-options="{debounce: 500}"/>--%>
        <%--<select id="selectedCountry" ng-model="xxx.selectedCountry">--%>
            <%--<option value="">-- Select a Country --</option>--%>
            <%--<option ng-repeat="country in xxx.countries" value="{{country}}">{{country}}</option>--%>
        <%--</select>--%>
        <%--<div ng-if="xxx.users !== ''">--%>
            <%--<table ng-table="xxx.tableParams" style="cursor: pointer; color:blue;" class="listOfResult table">--%>
                <%--<tr ng-repeat="user in xxx.users">--%>
                    <%--<td id="user{{$index}}" ng-click="xxx.displayProfile(user)">{{user}}</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
            <%--<uib-pagination--%>
                    <%--ng-model="pagination.currentPage"--%>
                    <%--items-per-page="3"--%>
                    <%--total-items="noOfItems"--%>
                    <%--max-size="5"--%>
                    <%--ng-change="xxx.searchResults(xxx.accountType)"--%>
                    <%--boundary-links="true">--%>
            <%--</uib-pagination>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<div class="bot-border"></div>--%>
    <%--<button class="btn-warning" ng-click="xxx.showAccountRequests()">{{xxx.requestButton}}</button>--%>
    <%--&nbsp;--%>
    <%--<button class="btn-danger" ng-click="xxx.fetchAdminRequests()">{{xxx.requestingAdminsButton}}</button>--%>
    <%--<ol ng-show="xxx.requestingUsers !== '' && xxx.showClientRequests == true">--%>
        <%--<div ng-repeat="profile in xxx.requestingUsers">--%>
            <%--<br/>--%>
            <%--<li class="listOfResult">--%>
                <%--<span style="cursor: pointer; color:blue; margin-bottom: 2px" ng-click="xxx.displayRequestingUser(profile)">{{profile}}</span>--%>
                <%--<br/>--%>
                <%--<button ng-click="xxx.approveRequest(profile)">Approve</button>--%>
                <%--<button ng-click="xxx.denyRequest(profile)">Reject</button>--%>
                <%--<br/>--%>
            <%--</li>--%>
        <%--</div>--%>
        <%--<br/>--%>
        <%--<h4 ng-show="xxx.requestingUsers == ''">No account requests to show</h4>--%>
    <%--</ol>--%>

    <%--<ol ng-show="xxx.requestingAdmins !== '' && xxx.showAdminRequests == true">--%>
        <%--<div ng-repeat="profile in xxx.requestingAdmins">--%>
            <%--<br/>--%>
            <%--<li class="listOfResult">--%>
                <%--<span style="cursor: pointer; color:blue; margin-bottom: 2px">{{profile}}</span>--%>
                <%--<br/>--%>
                <%--<button ng-click="xxx.approveAdminRequest(profile)">Approve</button>--%>
                <%--<button ng-click="xxx.rejectAdminRequest(profile)">Reject</button>--%>
                <%--<br/>--%>
            <%--</li>--%>
        <%--</div>--%>
        <%--<br/>--%>
        <%--<h4 ng-show="xxx.requestingAdmins == ''">No admin requests to show</h4>--%>
    <%--</ol>--%>

    <%--<div class="bot-border"></div>--%>
    <%--<button class="btn btn-danger" ng-click="xxx.logOut()">Log out</button>--%>
<%--</div>--%>
