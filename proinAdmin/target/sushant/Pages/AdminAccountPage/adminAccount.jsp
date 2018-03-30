<div ng-controller="AdminAccountPageCtrl as xxx">
    <h1>{{xxx.welcomeMessage}}</h1>
    <button class="btn btn-primary" ng-click="xxx.showAccountRequests()">Show Account Requests</button>
    &nbsp;
    <button class="btn btn-primary" ng-click="xxx.sendEmail()">Send Email</button>

    <div class="bot-border"></div>
    <div>
        <h3>Search for client accounts here</h3>
        <input type="radio" name="radAnswer" ng-model="xxx.accountType" ng-value="1">Deleted Accounts
        &nbsp;
        <input type="radio" name="radAnswer" ng-model="xxx.accountType" ng-value="0">Active accounts
        <br/>
        {{xxx.accountType}}
        <input class="searchInput" type="text" ng-model="xxx.searchThis" ng-change="xxx.searchResults(xxx.accountType)"
               ng-model-options="{debounce: 500}"/>
        <select id="selectedCountry" ng-model="xxx.selectedCountry">
            <option value="">-- Select a Country --</option>
            <option ng-repeat="country in xxx.countries" value="{{country}}">{{country}}</option>
        </select>
        <ol>
            <div ng-show="xxx.showList" ng-repeat="profile in xxx.users">
                <li style="cursor: pointer; color:blue;" class="listOfResult" ng-click="xxx.displayProfile(profile)">{{profile}}</li>
            </div>
        </ol>
    </div>
    <div class="bot-border"></div>
    <button class="btn btn-danger" ng-click="xxx.logOut()">Log out</button>
</div>
