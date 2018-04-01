<div ng-controller="AdminAccountPageCtrl as xxx">
    <h1>{{xxx.welcomeMessage}}</h1>
    &nbsp;
    <button class="btn btn-primary" ng-click="xxx.sendEmail()">Send Email</button>

    <div class="bot-border"></div>
    <div>
        <h3>Search for client accounts here</h3>
        <input type="radio" name="radAnswer" ng-model="xxx.accountType" ng-value="1">Deleted Accounts
        &nbsp;
        <input type="radio" name="radAnswer" ng-model="xxx.accountType" ng-value="0">Active accounts
        <br/>

        <input class="searchInput" type="text" ng-model="xxx.searchThis" ng-change="xxx.searchResults(xxx.accountType)"
               ng-model-options="{debounce: 500}"/>
        <select id="selectedCountry" ng-model="xxx.selectedCountry">
            <option value="">-- Select a Country --</option>
            <option ng-repeat="country in xxx.countries" value="{{country}}">{{country}}</option>
        </select>

        <ol style="cursor: pointer; color:blue;" class="listOfResult">
            <div ng-show="xxx.showList" ng-repeat="profile in xxx.users">
                <li  ng-click="xxx.displayProfile(profile)">
                    {{profile}}
                </li>
            </div>
        </ol>

    </div>
    <div class="bot-border"></div>
    <button class="btn btn-primary" ng-click="xxx.showAccountRequests()">{{xxx.requestButton}}</button>
    <ol ng-show="xxx.requestingUsers !== '' && xxx.showClientRequests == true">
        <div ng-repeat="profile in xxx.requestingUsers">
            <br/>
            <li class="listOfResult">
                <span style="cursor: pointer; color:blue; margin-bottom: 2px" ng-click="xxx.displayProfile(profile)">{{profile}}</span>
                <br/>
                <button>Approve</button>
                <button>Reject</button>
                <br/>
            </li>
        </div>
        <br/>
        <button class="btn">Submit</button>
        <br/>
    </ol>
    <button class="btn btn-danger" ng-click="xxx.logOut()">Log out</button>
</div>
