<div class="modal-header">
    <h2>Type your querry here</h2>
</div>

<div class="modal-body">
    <textarea style="width:500px; height: 60px;" ng-model = "$ctrl.msg"></textarea>
</div>

<div class="modal-footer">
    <div class="col-md-10 top-buffer">
        <button class="btn btn-primary pull-left" ng-click="$ctrl.sendRequest()">Send Request</button>
        <button class="btn btn-warning pull-right" ng-click="$ctrl.cancelModal()">Cancel</button>
    </div>
</div>
