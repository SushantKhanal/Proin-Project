<div ng-controller="SearchProCleintsCtrl as xxx">
<h1>Search ProClients Page</h1>
    <div>
        <button class="btn btn-danger" ng-click="xxx.fetchId()">Fetch 3 Unique Ids</button>
    </div>
    <div>
        <br>
        <button style="font-size:16px" ng-click="xxx.goBack()"><i class="fa fa-arrow-left"></i> Go Back</button>
        <h3>Search for other professionals here</h3>
        <input class="searchInput" type="text" ng-model="xxx.searchThis" ng-change="xxx.searchResults()"
               ng-model-options="{debounce: 500}"/>
        <select id="selectedCountry" ng-model="xxx.selectedCountry">
            <option value="">-- Select a Country --</option>
            <option ng-repeat="country in xxx.countries" value="{{country}}">{{country}}</option>
        </select>
        {{xxx.selectedCountry}}
        <ol style="cursor: pointer; color:blue;" class="listOfResult">
            <div ng-show="xxx.showList" ng-repeat="profile in xxx.users">
                <li ng-click="xxx.displayProfile(profile)">
                    {{profile}}
                </li>
            </div>
        </ol>

    </div>

</div>

