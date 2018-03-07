<div ng-controller="SearchResultCtrl as xxx">
    <button style="font-size:16px" ng-click="xxx.goBack()"><i class="fa fa-arrow-left"></i> Go Back</button>
    <h3>Search for other professionals here</h3>
    <input class = "searchInput" type="text" ng-model="xxx.searchThis"/>
    <select id="selectedCountry" ng-model="country" ng-change="xxx.country">
        <option value="">-- Select a Country --</option>
        <option ng-repeat="country in xxx.countries" value="{{country}}">{{country}}</option>
    </select>
    <button class="btn-primary" ng-click="xxx.searchResults()">Search</button>
</div>

