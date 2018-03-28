<div ng-controller="SearchResultCtrl as xxx">

    <div>
        <button style="font-size:16px" ng-click="xxx.goBack()"><i class="fa fa-arrow-left"></i> Go Back</button>
        <h3>Search for other professionals here</h3>
        <input class = "searchInput" type="text" ng-model="xxx.searchThis" ng-change="xxx.searchResults()" ng-model-options="{debounce: 500}"/>
        <select id="selectedCountry" ng-model="country" ng-change="xxx.country">
            <option value="">-- Select a Country --</option>
            <option ng-repeat="country in xxx.countries" value="{{country}}">{{country}}</option>
        </select>

        <ol>
            <div ng-show="xxx.showList" ng-repeat="profile in xxx.users">
                <li style="cursor: pointer; color:blue;" class="listOfResult" ng-click="xxx.displayProfile(profile)">{{profile}}</li>
            </div>
        </ol>


    </div>

</div>

